package gmp.ui;

/** Interface defines the minimum set of functions that a 'Choice'
 component must implement in any GMP user-interface. Typical Choice implementation are
 RadioButton groups */
public interface gmpChoice
{
    /** Returns String that the user choosed
     * @return String
     */
    public String get();
   /** It shall be possible to enable/disbale the Choice. Implemetation of this method will
     * enable or disable the Choice
     * @param b boolean
     */
    public void setEnabled(boolean b);
}

