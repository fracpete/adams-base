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
 * ColumnSubset.java
 * Copyright (C) 2014 University of Waikato, Hamilton, New Zealand
 */
package adams.data.spreadsheet.rowscore;

import adams.data.spreadsheet.Row;
import adams.data.spreadsheet.SpreadSheet;
import adams.data.spreadsheet.SpreadSheetColumnRange;

/**
 <!-- globalinfo-start -->
 <!-- globalinfo-end -->
 *
 <!-- options-start -->
 <!-- options-end -->
 *
 * @author  fracpete (fracpete at waikato dot ac dot nz)
 * @version $Revision$
 */
public class ColumnSubset
  extends AbstractRowScore {

  /** for serialization. */
  private static final long serialVersionUID = -9037884201569670797L;

  /** the row score to use on the subset. */
  protected AbstractRowScore m_RowScore;
  
  /** the subset of columns to use. */
  protected SpreadSheetColumnRange m_Columns;
  
  /** the header of the subset. */
  protected SpreadSheet m_Header;
  
  /**
   * Returns a string describing the object.
   *
   * @return 			a description suitable for displaying in the gui
   */
  @Override
  public String globalInfo() {
    return 
	"Applies the specified base row score algorithm to the specified "
	+ "subset of columns and returns the calculated score.";
  }

  /**
   * Adds options to the internal list of options.
   */
  @Override
  public void defineOptions() {
    super.defineOptions();

    m_OptionManager.add(
	    "row-score", "rowScore",
	    new ArrayStatistic());

    m_OptionManager.add(
	    "columns", "columns",
	    new SpreadSheetColumnRange(SpreadSheetColumnRange.ALL));
  }

  /**
   * Resets the scheme.
   */
  @Override
  protected void reset() {
    super.reset();
    
    m_Header = null;
  }
  
  /**
   * Sets the row score algorithm to use.
   *
   * @param value	the algorithm
   */
  public void setRowScore(AbstractRowScore value) {
    m_RowScore = value;
    reset();
  }

  /**
   * Returns the row score algorithm in use.
   *
   * @return		the algorithm
   */
  public AbstractRowScore getRowScore() {
    return m_RowScore;
  }

  /**
   * Returns the tip text for this property.
   *
   * @return 		tip text for this property suitable for
   * 			displaying in the GUI or for listing the options.
   */
  public String rowScoreTipText() {
    return "The row score algorithm to apply to the column subset.";
  }

  /**
   * Sets the range of columns to apply the row score algorithm to.
   *
   * @param value	the range
   */
  public void setColumns(SpreadSheetColumnRange value) {
    m_Columns = value;
    reset();
  }

  /**
   * Returns the range of columns to apply the row score algorithm to.
   *
   * @return		the range
   */
  public SpreadSheetColumnRange getColumns() {
    return m_Columns;
  }

  /**
   * Returns the tip text for this property.
   *
   * @return 		tip text for this property suitable for
   * 			displaying in the GUI or for listing the options.
   */
  public String columnsTipText() {
    return "The range of columns to apply the specified row score algorithm to.";
  }

  /**
   * Returns how many score values will get generated.
   * 
   * @return		the number of scores
   */
  @Override
  public int getNumScores() {
    return m_RowScore.getNumScores();
  }

  /**
   * Creates the header for the subset.
   *
   * @param sheet	the spreadsheet to generate the header from
   * @return		the header
   */
  protected SpreadSheet createHeader(SpreadSheet sheet) {
    SpreadSheet	result;
    int[]	indices;
    Row		header;
    
    result = sheet.newInstance();
    m_Columns.setData(sheet);
    indices = m_Columns.getIntIndices();
    
    header = result.getHeaderRow();
    for (int index: indices)
      header.addCell("" + result.getColumnCount()).assign(sheet.getHeaderRow().getCell(index));
    
    return result;
  }

  /**
   * Creates spreadsheet with a single row that contains the subset of columns.
   *
   * @param sheet	the spreadsheet to generate the score for
   * @param rowIndex	the row index
   * @return		the generated subset spreadsheet
   */
  protected SpreadSheet createSubset(SpreadSheet sheet, int rowIndex) {
    SpreadSheet	result;
    int		i;
    int[]	indices;
    Row		rowOld;
    Row		rowNew;
    
    result  = m_Header.getClone();
    indices = m_Columns.getIntIndices();
    rowOld  = sheet.getRow(rowIndex);
    rowNew  = result.addRow();
    for (i = 0; i < indices.length; i++)
      rowNew.addCell(i).assign(rowOld.getCell(indices[i]));
    
    return result;
  }

  /**
   * Performs the actual calculation of the row score.
   *
   * @param sheet	the spreadsheet to generate the score for
   * @param rowIndex	the row index
   * @return		the generated score, null in case of an error
   */
  @Override
  protected Double[] doCalculateScore(SpreadSheet sheet, int rowIndex) {
    Double[]	result;
    SpreadSheet	subset;
    
    result = null;
    
    // create header?
    if (m_Header == null)
      m_Header = createHeader(sheet);

    // create subset
    subset = createSubset(sheet, rowIndex);
    
    // calc score(s)
    result = m_RowScore.calculateScore(subset, 0);
    
    return result;
  }
}
