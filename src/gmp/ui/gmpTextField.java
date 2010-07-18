package gmp.ui;

/** Interface defines the minimum set of functions that a 'TextField'
 component must implement in any GMP user-interface */
public interface gmpTextField {
      /** Returns the String contained in TextField
     * @return String
     */
    public String get();
    /** Sets the String to be the the text of the TextField
     * Returns true if successfull.
     * @param s String
     * @return boolean
     */
    public boolean set(String s);
        /** It shall be possible to enable/disbale editing of the TextField.
     * Implemetation of this method will
     * enable or disable it.
     * @param b boolean
     */
    public void Editable (boolean b);
        /** The method will repaint the TextField and return true if successfull
     * @return boolean
     */
    public boolean Refresh();
}
