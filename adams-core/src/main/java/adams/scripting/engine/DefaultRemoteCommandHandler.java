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
 * DefaultRemoteCommandHandler.java
 * Copyright (C) 2016 University of Waikato, Hamilton, NZ
 */

package adams.scripting.engine;

import adams.scripting.command.RemoteCommand;
import adams.scripting.command.RemoteCommandWithResponse;

/**
 * Default handler for remote commands.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 * @version $Revision$
 */
public class DefaultRemoteCommandHandler
  extends AbstractRemoteCommandHandler {

  /**
   * Returns a string describing the object.
   *
   * @return 			a description suitable for displaying in the gui
   */
  @Override
  public String globalInfo() {
    return "Default handler for remote commands.";
  }

  /**
   * Handles the command.
   *
   * @param cmd		the command to handle
   * @return		null if successful, otherwise error message
   */
  @Override
  protected String doHandle(RemoteCommand cmd) {
    cmd.setApplicationContext(m_Owner.getApplicationContext());
    if (cmd.isRequest()) {
      cmd.handleRequest(m_Owner, m_Owner.getRequestHandler());
    }
    else {
      if (cmd instanceof RemoteCommandWithResponse)
	((RemoteCommandWithResponse) cmd).handleResponse(m_Owner, m_Owner.getResponseHandler());
      else
	m_Owner.getResponseHandler().responseFailed(cmd, "Command does not support response handling!");
    }
    return null;
  }
}
