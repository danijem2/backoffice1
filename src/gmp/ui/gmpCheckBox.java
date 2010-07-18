package gmp.ui;

/** Interface defines the minimum set of functions that a 'CheckBox'
 component must implement in any GMP user-interface */
public interface gmpCheckBox
{
    /** Returns true if CheckBox is checked.
     * @return boolean
     */
    public boolean get();
    /** Sets that the CheckBox is or is not checked
     * @param b boolean
     */
    public void set(boolean b);
}


