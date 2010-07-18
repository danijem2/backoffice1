package gmp.swing;

import javax.swing.JList;
import javax.swing.DefaultListModel;

/** The List that can be refreshed dynamically. So its items ae not hardcoded.
 *  Class specific to Swing implementation of GMP. */
public class MutableList extends JList {
    public MutableList() {
	super(new DefaultListModel());
    }
    public DefaultListModel getContents() {
	return (DefaultListModel)getModel();
    }
}
