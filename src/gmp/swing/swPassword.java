package gmp.swing;

import javax.swing.JPasswordField;
import gmp.ui.gmpPassword;

    /** Swing implementation of the GMP interface gmpPassword*/
public class swPassword 
  implements gmpPassword {

   private JPasswordField pass = null;

   public swPassword (JPasswordField p){
     super();
     this.pass = p;
    }

    public char [] get(){
     char [] ret =  this.pass.getPassword();
     this.pass.setText(null);
     return ret;
    }
}

