package gmp.ui;

import gmp.terminal.local.*;
/** Interface defines the minimum set of functions that a 'List'
 component must implement in any GMP user-interface. This includes both single-choice
 and multi-choice lists. Lists are considered to be lists of Strings*/
public interface gmpList
{
    /** Maximum number of items in a list*/
    public static final int MAX_LIST = 2047;
    /** Gets the value selected from single-choice list
     * @return String
     */
    public String getValue();
    /** Sets this list to be a multichoice List
     * @param b boolean
     */
    public void setMultichoice(boolean b);
    /** Gets all the values selected from multi-choice list
     * @return String []
     */
    public String [] getValues();
    /** Refresh the list with an array of String. Each String in the array becomes
     * one item on the GMP list. Returns true if successfull.
     * @param s String []
     * @return boolean
     */
    public boolean Refresh(String [] s);
       /** Refresh the list with an array of gmprow objects. From each gmprow the
        * String value belonging to 'tag' becomes one item on the GMP list.
        * Returns true if successfull.
     * @param r gmprow []
        * @param tag String
        * @return boolean
     */
    public boolean Refresh(gmprow [] r, String tag);
        /** Refresh the list with a gmpfile. From each gmprow from one gmpfile line
         * is scanned, and its String value belonging to 'tag' becomes one item on
         * the GMP list. Returns true if successfull.
     * @param f gmpfile
        * @param tag String
        * @return boolean
     */
    public boolean Refresh(gmpfile f, String tag);
       /** It shall be possible to enable/disbale the List. Implemetation of this method will
     * enable or disable the List
     * @param b boolean
     */
    public void setEnabled(boolean b);
}


