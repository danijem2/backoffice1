package backoffice1;

import java.io.*;

public class ExtensionFileFilter extends javax.swing.filechooser.FileFilter {
  private String ext = "";
  public ExtensionFileFilter(String extension){
     ext = extension.toUpperCase();
    }
        @Override
        public boolean accept(File file) {
            // Allow only directories, or files with ".txt" extension
            return file.isDirectory() || ((file.getAbsolutePath()).toUpperCase()).endsWith("."+ext);
        }
        @Override
        public String getDescription() {
            // This description will be displayed in the dialog,
            // hard-coded = ugly, should be done via I18N
            return "Photo accepted with extension ."+ext;
        }
    }