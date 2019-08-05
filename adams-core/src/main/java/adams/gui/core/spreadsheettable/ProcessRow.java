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
 * ProcessRow.java
 * Copyright (C) 2015-2019 University of Waikato, Hamilton, NZ
 */

package adams.gui.core.spreadsheettable;

import adams.gui.core.spreadsheettable.SpreadSheetTablePopupMenuItemHelper.TableState;

/**
 * Interface for plugins that process a row.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public interface ProcessRow
  extends SpreadSheetTablePopupMenuItem {

  /**
   * Processes the specified row.
   *
   * @param state	the table state
   * @return		true if successful
   */
  public boolean processRow(TableState state);
}
