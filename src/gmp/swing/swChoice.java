package gmp.swing;

import java.util.Enumeration;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import gmp.ui.gmpChoice;

    /** Swing implementation of the GMP interface gmpChoice */
public class swChoice  
        implements gmpChoice
{
    private ButtonGroup group;

    public ButtonGroup getButtonGroup(){ return this.group;}

    public swChoice(ButtonGroup bg){
     super();
     this.group = bg;
    }

    public String get(){
      String retn = null;
      Enumeration elements = null;
      int ix = 0;

      elements = this.getButtonGroup().getElements();
      while(elements.hasMoreElements()){
        JRadioButton rbutton =  (JRadioButton)elements.nextElement();
        if( rbutton.isSelected() == true ) {
           retn = rbutton.getText() ;
           break;
        }
      }
      return retn;
    }


    public void setEnabled(boolean b){
      this.setEnabled(b);
    }
}

