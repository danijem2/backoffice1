package gmp.swing;

import javax.swing.JLabel;
import javax.swing.JPanel;
import gmp.ui.gmpImage;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;
import java.io.IOException;

import gmp.terminal.local.gmpplatform;

    /** Swing implementation of the GMP interface gmpImage */
public class swImage extends JLabel
  implements gmpImage
{
    private BufferedImage image = null;
    //private JPanel panel = null;

   public swImage(){
     this.image = null;
    // this.panel = null;
   }

    public swImage(JPanel p){
     this.image = null;
    // this.panel = p;
   }

    public swImage(JPanel p, String path){
    // this.panel = p;
      if( this.setImage(path) == false ) {
     //    this.panel = null;
       }
     }

    public swImage(JPanel p, BufferedImage bufi){
    // this.panel = p;
     this.image = bufi;
     }
   


   //public JPanel getPanel(){ return this.panel;}

   public boolean setImage(String path){
     boolean retn = true;
     URL urllocal  = null;
     try {
        urllocal = new URL(path);
        this.image = ImageIO.read(urllocal);
	 }
	catch(IOException e) {
           gmpplatform.Log(" setImage() ImageIO IOException " + e.getMessage());
           this.image = null;
           retn = false;
         }    
      return retn;
    }

   public boolean setPanel(JPanel p){
     boolean retn = true;
     //this.panel = p;
     return retn;
   }

    public BufferedImage getImage(){ return this.image; }
    
    public boolean Refresh(){
      this.repaint();
      return true;
     }
}
