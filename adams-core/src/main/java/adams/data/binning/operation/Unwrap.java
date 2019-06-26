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
 * Unwrap.java
 * Copyright (C) 2019 University of Waikato, Hamilton, NZ
 */

package adams.data.binning.operation;

import adams.data.binning.Binnable;

import java.util.ArrayList;
import java.util.List;

/**
 * For unwrapping the payloads.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public class Unwrap {

  /**
   * Unwraps the payloads from the binnable list.
   *
   * @param data	the data to unwrap
   * @param <T>		the type of payload
   * @return		the payload list
   */
  public static <T> List<T> unwrap(List<Binnable<T>> data) {
    List<T>	result;

    result = new ArrayList<>();
    for (Binnable<T> b: data)
      result.add(b.getPayload());

    return result;
  }
}
