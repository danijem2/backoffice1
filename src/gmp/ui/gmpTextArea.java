package gmp.ui;

import java.io.PrintWriter;

/** Interface defines the minimum set of functions that a 'TextArea'
 component must implement in any GMP user-interface */
public interface gmpTextArea {
    /** Returns the String contained in TextArea
     * @return String
     */
    public String getText();
    /** Appends the String to the text already contained in the text area.
     * Returns true if successfull.
     * @param s String
     * @return boolean
     */
    public boolean AppendText(String s);
     /** It shall be possible to enable/disbale editing of the TextArea.
     * Implemetation of this method will
     * enable or disable it.
     * @param b boolean
     */
    public void setEditable (boolean b);
      /** It shall be possible to set the content of the TextArea to blank an clear it.
     */
    public void clear();
     /** Method will start writing everything that gets appended to TextArea
      * to the log file attached to this TextArea object
      * @return boolean. Returns true if successfull.
      */
    public boolean StartLog();
     /** Method will stop writing everything that gets appended to TextArea
      * to the log file attached to this TextArea object
      * @return boolean. Returns true if successfull.
      */
    public boolean StopLog();
      /** The method will repaint the TextArea and return true if successfull
     * @return boolean
     */
    public boolean Refresh();
        /** The method will return the PrintWriter object used as the log
         * attached to this TextArea object
         * @return PrintWriter
         */
    public PrintWriter getFile();
        /** The method will return the file name of the file used as the log
         * attached to this TextArea object
         * @return String
         */
    public String getLogName();
}
