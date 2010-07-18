package gmp.ui;

/** Interface defines the minimum set of functions that a 'Button'
 component must implement in any GMP user-interface */
public interface gmpButton
{
    /** Button must be able to execute (i.e. perform do_click event). Implementation
     of this method will execute the action*/
    public void Execute();
    /** It shall be possible to enable/disbale the Button. Implemetation of this method will
     * enable or disable the Button
     * @param b boolean
     */
    public void setEnable(boolean b);
}



