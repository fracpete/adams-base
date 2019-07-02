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
 * FreedmanDiaconisChoiceBinning.java
 * Copyright (C) 2019 University of Waikato, Hamilton, NZ
 */

package adams.data.binning.algorithm;

import adams.core.TechnicalInformation;
import adams.core.TechnicalInformation.Field;
import adams.core.TechnicalInformation.Type;
import adams.core.TechnicalInformationHandler;
import adams.data.binning.Bin;
import adams.data.binning.Binnable;
import adams.data.binning.operation.Values;
import adams.data.statistics.StatUtils;

import java.util.List;

/**
 * The Freedman-Diaconis' choice is based on the interquartile range. It
 * replaces 3.5σ of Scott's rule with 2 IQR, which is less sensitive than
 * the standard deviation to outliers in data.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public class FreedmanDiaconisChoiceBinning<T>
  extends AbstractEqualWidthBinningAlgorithm<T>
  implements TechnicalInformationHandler {

  private static final long serialVersionUID = -1486327441961729111L;

  /**
   * Returns a string describing the object.
   *
   * @return 			a description suitable for displaying in the gui
   */
  @Override
  public String globalInfo() {
    return "The Freedman-Diaconis' choice is based on the interquartile range. "
      + "It replaces 3.5σ of Scott's rule with 2 IQR, which is less sensitive "
      + "than the standard deviation to outliers in data.\n\n"
      + "For more information see:\n"
      + getTechnicalInformation();
  }

  /**
   * Returns an instance of a TechnicalInformation object, containing
   * detailed information about the technical background of this class,
   * e.g., paper reference or book this class is based on.
   *
   * @return 		the technical information about this class
   */
  public TechnicalInformation getTechnicalInformation() {
    TechnicalInformation 	result;

    result = new TechnicalInformation(Type.MISC);
    result.setValue(Field.YEAR, "2010");
    result.setValue(Field.AUTHOR, "WikiPedia");
    result.setValue(Field.TITLE, "Histogram");
    result.setValue(Field.HTTP, "http://en.wikipedia.org/wiki/Histogram");

    return result;
  }

  /**
   * Performs the actual bin generation on the provided objects.
   *
   * @param objects	the objects to bin
   * @return		the generated bins
   * @throws IllegalStateException	if binning fails
   */
  @Override
  protected List<Bin<T>> doGenerateBins(List<Binnable<T>> objects) {
    Number[]	values;
    double	iqr;

    values = Values.toNumberArray(objects);
    iqr    = StatUtils.iqr(values);
    if (isLoggingEnabled())
      getLogger().info("iqr=" + iqr);
    return doGenerateBins(objects, 2. * iqr / Math.pow(objects.size(), 1./3.));
  }
}
