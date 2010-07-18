package gmp.swing;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;

import javax.swing.JTextArea;
import gmp.ui.gmpTextArea;
    /** Swing implementation of the GMP interface gmpTextArea */
public class swTextArea extends JTextArea
   implements gmpTextArea {

    private JTextArea textarea = null;
    public JTextArea getTextArea(){ return this.textarea;}

    public swTextArea (JTextArea a){
     super();
     this.textarea = a;
     this.file = null;
     this.logname = null;
     this.clear();
    }


    public swTextArea (JTextArea a,String fname){
     super();
     this.textarea = a;
     this.logname = fname;
        try {
            this.file = new PrintWriter(new FileWriter(this.logname));
        }
        catch (IOException ex) {
            this.logname = null;
        }
     this.clear();
    }

    private PrintWriter file = null;
    private String logname = null;

    public String getLogName(){ return this.logname;}

    public PrintWriter getFile() { return this.file;}

    public boolean AppendText(String s){
      this.append(s);
        if (this.getFile() != null){
        this.getFile().write(s);
        this.getFile().flush();
      }
      return this.Refresh();
    }

    public void clear(){
      this.setText(null);
      }

    public boolean StartLog(){
       boolean ret = true;
        try {
            this.file = new PrintWriter(new FileWriter(this.getLogName()));
        }
        catch (IOException ex) {
            ret = false;
        }
       return ret;
    }

    public boolean StopLog(){
    boolean ret = true;
      this.getFile().flush();
      this.getFile().close();
      return ret;
    }

    public boolean Refresh(){
      this.repaint();
      return true;
    }
}
