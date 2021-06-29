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

/*
 * PropertiesIndexedSplitsRunsWriter.java
 * Copyright (C) 2021 University of Waikato, Hamilton, NZ
 */

package adams.data.io.output;

import adams.core.MessageCollection;
import adams.core.Properties;
import adams.core.Utils;
import adams.data.indexedsplits.IndexedSplit;
import adams.data.indexedsplits.IndexedSplits;
import adams.data.indexedsplits.IndexedSplitsRun;
import adams.data.indexedsplits.IndexedSplitsRuns;
import adams.data.indexedsplits.SplitIndices;
import adams.data.io.input.PropertiesIndexedSplitsRunsReader;
import adams.data.statistics.StatUtils;
import adams.env.Environment;

import java.io.File;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Writes runs of indexed splits as .props file.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public class PropertiesIndexedSplitsRunsWriter
  extends AbstractIndexedSplitsRunsWriter {

  private static final long serialVersionUID = -288879107378616639L;

  /**
   * Returns a string describing the object.
   *
   * @return a description suitable for displaying in the gui
   */
  @Override
  public String globalInfo() {
    return "Writes runs of indexed splits as .props file.";
  }

  /**
   * Returns a string describing the format (used in the file chooser).
   *
   * @return a description suitable for displaying in the
   * file chooser
   */
  @Override
  public String getFormatDescription() {
    return new PropertiesIndexedSplitsRunsReader().getFormatDescription();
  }

  /**
   * Returns the extension(s) of the format.
   *
   * @return the extension (without the dot!)
   */
  @Override
  public String[] getFormatExtensions() {
    return new PropertiesIndexedSplitsRunsReader().getFormatExtensions();
  }

  /**
   * Returns the default extension of the format.
   *
   * @return the default extension (without the dot!)
   */
  @Override
  public String getDefaultFormatExtension() {
    return new PropertiesIndexedSplitsRunsReader().getDefaultFormatExtension();
  }

  /**
   * Writes the split definitions to the specified reader.
   * The caller must close the writer object.
   *
   * @param writer the writer to write to
   * @param runs   the runs to write
   * @param errors for storing errors
   * @return whether successfully written
   */
  @Override
  protected boolean doWrite(Writer writer, IndexedSplitsRuns runs, MessageCollection errors) {
    Properties		props;
    int 		run;
    IndexedSplits 	splits;
    int			split;

    props = new Properties();

    // splits
    props.setInteger("runs", runs.size());
    for (run = 0; run < runs.size(); run++) {
      splits = runs.get(run).getSplits();
      props.setInteger("run." + run + ".id", runs.get(run).getID());
      props.setInteger("run." + run + ".splits", splits.size());
      for (split = 0; split < splits.size(); split++) {
	props.setInteger("run." + run + ".split." + split + ".id", splits.get(split).getID());
	props.setProperty("run." + run + ".split." + split + ".names", Utils.flatten(new ArrayList(splits.get(split).getIndices().keySet()), ","));
        for (String name : splits.get(split).getIndices().keySet()) {
          props.setProperty("run." + run + ".split." + split + "." + name,
            Utils.flatten(
              StatUtils.toNumberArray(splits.get(split).getIndices().get(name).getIndices()), ","));
	}
      }
    }

    // meta-data
    for (String key: runs.getMetaData().keySet())
      props.setProperty("metadata." + key, runs.getMetaData().get(key));

    try {
      props.store(writer, null);
      return true;
    }
    catch (Exception e) {
      return false;
    }
  }

  public static void main(String[] args) throws Exception {
    Environment.setEnvironmentClass(Environment.class);

    IndexedSplitsRuns runs = new IndexedSplitsRuns();
    runs.getMetaData().put("url", "https://github.com/fracpete");
    runs.getMetaData().put("author", "me");
    runs.getMetaData().put("dataset", "something cool");
    IndexedSplits splits = new IndexedSplits();
    IndexedSplit split = new IndexedSplit(0);
    splits.add(split);
    SplitIndices train = new SplitIndices("train", new int[]{0,1,2,3,4,5,6,7,8,9});
    SplitIndices test = new SplitIndices("test", new int[]{10,11,12,13,14,15,16,17,18,19});
    SplitIndices val = new SplitIndices("val", new int[]{20,21,22,23,24,25,26,27,28,29});
    split.add(train);
    split.add(test);
    split.add(val);
    runs.add(new IndexedSplitsRun(0, splits));

    MessageCollection errors = new MessageCollection();
    PropertiesIndexedSplitsRunsWriter writer = new PropertiesIndexedSplitsRunsWriter();
    if (!writer.write(new File("/home/fracpete/temp/runs.props"), runs, errors))
      System.out.println(errors);
  }
}
