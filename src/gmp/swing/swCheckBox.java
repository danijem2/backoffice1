package gmp.swing;

import javax.swing.JCheckBox;
import gmp.ui.gmpCheckBox;
    /** Swing implementation of the GMP interface gmpCheckBox*/
public class swCheckBox 
        implements gmpCheckBox
{

  private JCheckBox box = null;

  public JCheckBox getJCheckBox(){ return this.box;}

  public swCheckBox(JCheckBox b){
    super();
    this.box = b;
  }
  public swCheckBox (){
     super();
    }

    public boolean get(){
    return this.getJCheckBox().isSelected();
    }
    public void set(boolean b){
      boolean ret = false;
      this.getJCheckBox().setSelected(b);
    }
}


