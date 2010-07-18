package gmp.swing;

import gmp.terminal.local.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;
    /** Swing implementation of the GMP interface gmpTable */
public class swTable extends DefaultTableModel
  implements gmp.ui.gmpTable
{
    public swTable(){
      super();
      this.addColumn(this.getIdColumnName()); 
    }

    public String getIdColumnName(){ return "id";}

    public gmptlv getCell(int row, int column){
        gmptlv tlv = null;
        String value = (((Vector)this.dataVector.elementAt(row)).elementAt(column)).toString() ;
        String tag = this.getColumnName(column);
        tlv = new gmptlv(tag,value);
        return tlv;
    }

    public gmprow getRow(int row){
        String s = null;
        gmptlv tlv = null;
        Vector vec = (Vector)this.dataVector.elementAt(row);
        int column = 0;
        while (column<vec.size()){
          String value = (vec.elementAt(column)).toString();
          String tag = this.getColumnName(column);
          tlv = new gmptlv(tag,value);
          s = s + tlv.write();
         }
        return new gmprow(s);
    }

}
