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
 * AbstractRectangleBasedAnnotator.java
 * Copyright (C) 2020 University of Waikato, Hamilton, NZ
 */

package adams.gui.visualization.object.annotator;

import adams.core.base.BaseRegExp;
import adams.data.report.AbstractField;
import adams.data.report.Report;
import adams.flow.transformer.locateobjects.LocatedObjects;
import adams.gui.visualization.image.SelectionRectangle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Annotators that use a rectangle based approach.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public abstract class AbstractRectangleBasedAnnotator
  extends AbstractAnnotator {

  private static final long serialVersionUID = 3536654827455977474L;

  /** the key for the X location. */
  public final static String KEY_X = LocatedObjects.KEY_X;

  /** the key for the Y location. */
  public final static String KEY_Y = LocatedObjects.KEY_Y;

  /** the key for the width. */
  public final static String KEY_WIDTH = LocatedObjects.KEY_WIDTH;

  /** the key for the height. */
  public final static String KEY_HEIGHT = LocatedObjects.KEY_HEIGHT;

  /** the key for the Xs of the polygon. */
  public final static String KEY_POLY_X = LocatedObjects.KEY_POLY_X;

  /** the key for the Ys of the polygon. */
  public final static String KEY_POLY_Y = LocatedObjects.KEY_POLY_Y;

  /** the prefix for the objects. */
  protected String m_Prefix;

  /** the number of digits to use for left-padding the index. */
  protected int m_NumDigits;

  /** the current rectangles. */
  protected List<SelectionRectangle> m_Locations;

  /**
   * Adds options to the internal list of options.
   */
  @Override
  public void defineOptions() {
    super.defineOptions();

    m_OptionManager.add(
      "prefix", "prefix",
      getDefaultPrefix());

    m_OptionManager.add(
      "num-digits", "numDigits",
      getDefaultNumDigits(), 0, null);
  }

  /**
   * Resets the scheme.
   */
  @Override
  protected void reset() {
    super.reset();

    m_Locations = null;
  }

  /**
   * Returns the default prefix to use for the objects.
   *
   * @return		the default
   */
  protected String getDefaultPrefix() {
    return "Object.";
  }

  /**
   * Sets the prefix to use for the objects.
   *
   * @param value 	the prefix
   */
  public void setPrefix(String value) {
    m_Prefix = value;
    reset();
  }

  /**
   * Returns the prefix to use for the objects.
   *
   * @return 		the prefix
   */
  public String getPrefix() {
    return m_Prefix;
  }

  /**
   * Returns the tip text for this property.
   *
   * @return 		tip text for this property suitable for
   * 			displaying in the GUI or for listing the options.
   */
  public String prefixTipText() {
    return "The prefix to use for the fields in the report.";
  }

  /**
   * Returns the default number of digits to use.
   *
   * @return		the default
   */
  protected int getDefaultNumDigits() {
    return 4;
  }

  /**
   * Sets the number of digits to use for the left-padded index.
   *
   * @param value 	the number of digits
   */
  public void setNumDigits(int value) {
    m_NumDigits = value;
    reset();
  }

  /**
   * Returns the number of digits to use for the left-padded index.
   *
   * @return 		the number of digits
   */
  public int getNumDigits() {
    return m_NumDigits;
  }

  /**
   * Returns the tip text for this property.
   *
   * @return 		tip text for this property suitable for
   * 			displaying in the GUI or for listing the options.
   */
  public String numDigitsTipText() {
    return "The number of digits to use for left-padding the index with zeroes.";
  }

  /**
   * Returns all the values stored in the report under this index.
   *
   * @param report	the report to look up the index in
   * @param index	the index to retrieve the values for
   * @return		the values
   */
  protected Map<String,Object> valuesForIndex(Report report, int index) {
    Map<String,Object>  result;
    BaseRegExp regexp;

    result = new HashMap<>();
    regexp = new BaseRegExp(m_Prefix + "[0]*" + index + "\\..*");
    for (AbstractField field: report.getFields()) {
      if (regexp.isMatch(field.getName()))
        result.put(field.getName().replaceAll(regexp.getValue(), ""), report.getValue(field));
    }

    return result;
  }

  /**
   * Removes the specified index from the report.
   *
   * @return		true if successfully removed
   */
  protected boolean removeIndex(Report report, int index) {
    boolean		result;
    BaseRegExp		regexp;
    List<AbstractField>	remove;

    result = false;
    regexp = new BaseRegExp(m_Prefix + "[0]*" + index + "\\..*");
    remove = new ArrayList<>();
    for (AbstractField field: report.getFields()) {
      if (regexp.isMatch(field.getName()))
        remove.add(field);
    }
    if (remove.size() > 0) {
      result = true;
      for (AbstractField field: remove)
        report.removeValue(field);
    }

    return result;
  }

  /**
   * Determines the last index used with the given prefix.
   */
  protected int findLastIndex(Report report) {
    int			result;
    List<AbstractField>	fields;
    String		name;
    int			current;

    result = 0;
    fields = report.getFields();

    for (AbstractField field: fields) {
      if (field.getName().startsWith(m_Prefix)) {
        name = field.getName().substring(m_Prefix.length());
        if (name.indexOf('.') > -1)
          name = name.substring(0, name.indexOf('.'));
        try {
          current = Integer.parseInt(name);
          if (current > result)
            result = current;
        }
        catch (Exception e) {
          // ignored
        }
      }
    }

    return result;
  }

  /**
   * Retruns all currently stored locations.
   *
   * @param report	the report to get the locations from
   * @return		the locations
   */
  protected List<SelectionRectangle> getLocations(Report report) {
    List<SelectionRectangle>	result;
    List<AbstractField>		fields;
    String			name;
    SelectionRectangle		rect;

    result = new ArrayList<>();
    fields = report.getFields();

    for (AbstractField field: fields) {
      if (field.getName().startsWith(m_Prefix)) {
        name = field.getName().substring(m_Prefix.length());
        if (name.indexOf('.') > -1)
          name = name.substring(0, name.indexOf('.'));
        try {
          rect = new SelectionRectangle(
            report.getDoubleValue(m_Prefix + name + KEY_X).intValue(),
            report.getDoubleValue(m_Prefix + name + KEY_Y).intValue(),
            report.getDoubleValue(m_Prefix + name + KEY_WIDTH).intValue(),
            report.getDoubleValue(m_Prefix + name + KEY_HEIGHT).intValue(),
            Integer.parseInt(name));
          if (!result.contains(rect))
            result.add(rect);
        }
        catch (Exception e) {
          // ignored
        }
      }
    }

    return result;
  }
}