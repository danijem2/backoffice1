package gmp.ui;

import gmp.terminal.local.*;
/** NOT IMPLEMENTED YET!!
 * Interface defines the minimum set of functions that a 'Table'
 component must implement in any GMP user-interface */
public interface gmpTable {
    /** Returns the table row that user has marked
     * @param row int
     * @return gmprow
     */
    public gmprow getRow(int row);
    /** Returns the column name
     * @return String
     */
    public String getIdColumnName();
    /** Returns the cell value in the form tag=value, where tag is the column name
     * @param row int
     * @param column int
     * @return gmptlv
     */
    public gmptlv getCell(int row, int column);
}
