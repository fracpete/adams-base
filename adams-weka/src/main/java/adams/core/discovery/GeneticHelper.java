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
 * GeneticHelper.java
 * Copyright (C) 2015 University of Waikato, Hamilton, NZ
 */

package adams.core.discovery;

/**
 * Helper class for bit-string related operations.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 * @version $Revision$
 */
public class GeneticHelper {

  /**
   * Turns the bit string back into an integer.
   *
   * @param bits	the bit string (0s and 1s)
   * @param min		the minimum
   * @param max		the maximum
   * @return		the integer
   */
  public static int bitsToInt(String bits, int min, int max){
    double j = 0;
    for (int i = 0; i < bits.length(); i++) {
      if (bits.charAt(i) == '1') {
        j = j + Math.pow(2, bits.length() - i - 1);
      }
    }
    j += min;

    return Math.min((int) j, max);
  }

  /**
   * Turns an integer into a bitstring (0s and 1s).
   *
   * @param in		the integer to convert
   * @param min		the minimum
   * @param max		the maximum
   * @param numBits	the number of bits
   * @return		the bit string
   */
  public static String intToBits(int in, int min, int max, int numBits){
    in = in-min;
    in = Math.min(in, max - min);
    String bits = Integer.toBinaryString(in);
    while (bits.length() < numBits)
      bits="0"+bits;
    return bits;
  }

  /**
   * Turns a bit string (0s and 1s) into an integer array.
   *
   * @param bits	the string
   * @param min		the minimum
   * @param max		the maximum
   * @param numBits	the number of bits
   * @param size	the size of the array
   * @return		the reconstructed integer array
   */
  public static int[] bitsToIntArray(String bits, int min, int max, int numBits, int size){
    int ret[] = new int[size];
    for (int k = 0; k < size; k++) {
      int start = numBits * k;
      double j = 0;
      for (int i = start; i < start + numBits; i++) {
        if (bits.charAt(i) == '1') {
          j = j + Math.pow(2, start + numBits - i - 1);
        }
      }
      j += min;
      ret[k] = (Math.min((int)j, max));
    }
    return(ret);
  }

  /**
   * Creates a bit string (0s and 1s) from an integer array.
   *
   * @param ina		the integer array
   * @param min		the minimum
   * @param max		the maximum
   * @param numBits	the number of bits
   * @return		the bit string
   */
  public static String intArrayToBits(int[] ina, int min, int max, int numBits){
    StringBuilder buff = new StringBuilder();
    for (int i = 0; i < ina.length; i++) {
      int in = ina[i];
      in = in - min;
      in = Math.min(in, max - min);
      String bits = Integer.toBinaryString(in);
      while (bits.length() < numBits) {
        bits = "0" + bits;
      }
      buff.append(bits);
    }
    return buff.toString();
  }
}
