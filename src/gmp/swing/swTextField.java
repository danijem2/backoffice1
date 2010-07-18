package gmp.swing;

import gmp.ui.gmpTextField;
import javax.swing.JTextField;

    /** Swing implementation of the GMP interface gmpTextField */
public class swTextField 
        implements gmpTextField
{
     private JTextField txt = null;
     public JTextField getTxt(){ return this.txt;}

     public swTextField (JTextField t){
     super();
     this.txt = t;
     this.Editable(t.isEditable());
     }

     public swTextField (){
     super();
     this.Editable(true);
     }

     public swTextField ( boolean b){
     super();
     this.Editable(b);
     }

    public String get(){
      return this.getTxt().getText();
    }
    public boolean set(String s){
      boolean ret = true;
      this.getTxt().setText(s);
      return true;
    }
    public void Editable (boolean b){
      this.getTxt().setEditable(b);
      this.getTxt().setEnabled(b);
    }
    public boolean Refresh(){
    boolean ret = true;
     this.getTxt().repaint();
     return ret;
    }
}
