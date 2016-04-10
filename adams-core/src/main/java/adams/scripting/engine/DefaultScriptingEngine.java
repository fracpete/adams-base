/*
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * DefaultScriptingEngine.java
 * Copyright (C) 2016 University of Waikato, Hamilton, NZ
 */

package adams.scripting.engine;

import adams.core.MessageCollection;
import adams.core.Utils;
import adams.env.Environment;
import adams.scripting.command.CommandUtils;
import adams.scripting.command.RemoteCommand;
import adams.scripting.command.RemoteCommandWithResponse;
import gnu.trove.list.array.TByteArrayList;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.Executors;
import java.util.logging.Level;

/**
 * Default implementation of scripting engine for remote commands.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 * @version $Revision$
 */
public class DefaultScriptingEngine
  extends AbstractScriptingEngine {

  private static final long serialVersionUID = -3763240773922918567L;

  /** the port to listen on. */
  protected int m_Port;

  /**
   * Returns a string describing the object.
   *
   * @return 			a description suitable for displaying in the gui
   */
  @Override
  public String globalInfo() {
    return "Default implementation of scripting engine for remote commands.";
  }

  /**
   * Adds options to the internal list of options.
   */
  @Override
  public void defineOptions() {
    super.defineOptions();

    m_OptionManager.add(
      "port", "port",
      12345, 1, 65535);
  }

  /**
   * Sets the port to listen on.
   *
   * @param value	the port to listen on
   */
  public void setPort(int value) {
    if (getOptionManager().isValid("port", value)) {
      m_Port = value;
      reset();
    }
  }

  /**
   * Returns the port to listen on.
   *
   * @return		the port listening on
   */
  public int getPort() {
    return m_Port;
  }

  /**
   * Returns the tip text for this property.
   *
   * @return 		tip text for this property suitable for
   * 			displaying in the gui
   */
  public String portTipText() {
    return "The port to listen on for remote connections.";
  }

  /**
   * Handles the client connection.
   *
   * @param client	the connection to handle
   */
  @Override
  protected void handleClient(Socket client) {
    InputStream		in;
    int			b;
    TByteArrayList 	bytes;
    String		data;
    RemoteCommand	cmd;
    MessageCollection	errors;

    // read data
    bytes = new TByteArrayList();
    try {
      in = client.getInputStream();
      while ((b = in.read()) != -1)
      	bytes.add((byte) b);
      client.close();
    }
    catch (Exception e) {
      getLogger().log(Level.SEVERE, "Failed to process client connection!", e);
      return;
    }
    if (bytes.isEmpty()) {
      getLogger().warning("No data received, ignoring connection!");
      return;
    }

    // instantiate command
    data   = new String(bytes.toArray());
    errors = new MessageCollection();
    cmd    = CommandUtils.parse(data, errors);

    if (cmd != null) {
      // permitted?
      if (!m_PermissionHandler.permitted(cmd)) {
	m_RequestHandler.requestRejected(cmd, "Not permitted!");
	return;
      }

      // handle command
      cmd.setApplicationContext(getApplicationContext());
      if (cmd.isRequest()) {
	cmd.handleRequest(this, m_RequestHandler);
      }
      else {
	if (cmd instanceof RemoteCommandWithResponse)
	  ((RemoteCommandWithResponse) cmd).handleResponse(this, m_ResponseHandler);
	else
	  getResponseHandler().responseFailed(cmd, "Command does not support response handling!");
      }
    }
    else {
      if (!errors.isEmpty())
	getLogger().severe("Failed to parse command:\n" + errors.toString());
      else
	getLogger().severe("Failed to parse command:\n" + data);
    }
  }

  /**
   * Executes the scripting engine.
   *
   * @return		error message in case of failure to start up or run,
   * 			otherwise null
   */
  @Override
  public String execute() {
    String		result;
    Socket		client;

    result    = null;
    m_Paused  = false;
    m_Stopped = false;

    // connect to port
    try {
      m_Server = new ServerSocket(m_Port);
      m_Server.setSoTimeout(m_Timeout);
    }
    catch (Exception e) {
      result   = Utils.handleException(this, "Failed to set up server socket!", e);
      m_Server = null;
    }

    // wait for connections
    if (m_Server != null) {
      // start up job queue
      m_Executor = Executors.newFixedThreadPool(m_MaxConcurrentJobs);

      while (!m_Stopped) {
	while (m_Paused && !m_Stopped) {
	  Utils.wait(this, this, 1000, 50);
	}

	try {
	  client = m_Server.accept();
	  if (client != null) {
	    handleClient(client);
	  }
	}
	catch (SocketTimeoutException t) {
	  // ignored
	}
	catch (Exception e) {
          if ((m_Server != null) && !m_Server.isClosed())
            Utils.handleException(this, "Failed to accept connection!", e);
	}
      }
    }

    closeSocket();

    if (!m_Executor.isTerminated()) {
      getLogger().info("Shutting down job queue...");
      m_Executor.shutdown();
      while (!m_Executor.isTerminated())
	Utils.wait(this, 1000, 100);
      getLogger().info("Job queue shut down");
    }

    return result;
  }

  /**
   * Starts the scripting engine from commandline.
   *
   * @param args  	additional options for the scripting engine
   */
  public static void main(String[] args) {
    runScriptingEngine(Environment.class, DefaultScriptingEngine.class, args);
  }
}
