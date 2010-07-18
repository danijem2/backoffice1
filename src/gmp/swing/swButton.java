package gmp.swing;

import gmp.ui.gmpButton;
import javax.swing.JButton;

    /** Swing implementation of the GMP interface gmpButton */
public class swButton 
        implements gmpButton
{
  private JButton button = null;

  public JButton getJButton(){ return this.button;}

  public swButton(JButton b){
    this.button = b;
  }

    public void Execute(){
        this.getJButton().doClick();
    }
    public void setEnable(boolean b){
     this.getJButton().setEnabled(b);
    }
}




