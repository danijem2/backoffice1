package gmp.swing;

import gmp.terminal.local.*;
import gmp.ui.gmpList;

import javax.swing.JList;
import javax.swing.JScrollPane;

    /** Swing implementation of the GMP interface gmpList */
public class swList 
        implements gmpList
{
   private JList list = null;
   private JScrollPane jpane = null;
   private boolean multichoice = false;

   public JList getJList(){ return this.list;}
   public JScrollPane getJScrollPane(){ return this.jpane;}

   public swList (JScrollPane p, JList l){
     super();
     this.jpane = p;
     this.list = l;
     this.setMultichoice(false);
    }

   public swList (){
     super();
     this.jpane = null;
     this.setMultichoice(false);
    }

   public swList (boolean b){
     super();
     this.jpane = null;
     this.setMultichoice(b);
    }

   public swList (JScrollPane p, boolean b){
     super();
     this.jpane = p;
     this.setMultichoice(b);
    }

   public swList (JScrollPane p){
     super();
     this.jpane = p;
     this.setMultichoice(false);
    }

    public String getValue(){
      String ret = this.getJList().getSelectedValue().toString();
        if(ret==null) ret = " ";
      return ret;
    }

    public void setMultichoice(boolean b){
      this.multichoice = b;
    }

    public String [] getValues(){
      String retn [] = new String [gmpList.MAX_LIST];
      Object os [] = this.getJList().getSelectedValues();
      int ix = 0;
      while( ix< os.length && ix< gmpList.MAX_LIST && os[ix] != null){
        retn[ix] = os[ix].toString();
        }
      return retn;
    }

    public boolean Refresh(String [] s){
      boolean retn = false;
        MutableList mls = new MutableList();
           for (int ix = 0; ix< s.length && s[ix] != null; ix++){
             mls.getContents().addElement(s[ix]);
        }
        this.getJScrollPane().add(mls);
        this.getJScrollPane().setViewportView(mls);
      return true;
    }

    public boolean Refresh(gmprow r [], String tag){
      boolean retn = false;
        MutableList mls = new MutableList();
       int ix = 0;

           for (ix = 0; ix< r.length && r[ix] != null; ix++){
              //gmpplatform.Log("swListRefresh() : length"+r.length+"|tag|"+r[ix].write());
                if( r[ix] == null) {
                     gmpplatform.Log(" FATAL swList LIST refresh r[ix]" + ix + "is NULL"); }
             mls.getContents().addElement(r[ix].getValue(tag));
        }
        this.getJScrollPane().add(mls);
        this.getJScrollPane().setViewportView(mls);
      return true;
    }

    public boolean Refresh(gmpfile f, String tag){
      boolean ret = false;
      gmprow [] rw = f.dump();
      MutableList mls = new MutableList();
           for (int ix = 0; ix< rw.length && rw[ix] != null; ix++){
             mls.getContents().addElement(rw[ix].getValue(tag));
        }
        this.getJScrollPane().add(mls);
        this.getJScrollPane().setViewportView(mls);
      return true;
    }

    public void setEnabled(boolean b){
        this.getJList().setEnabled(b);
        this.getJScrollPane().setEnabled(b);
    }

}


