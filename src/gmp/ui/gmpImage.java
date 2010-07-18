package gmp.ui;

import java.awt.image.BufferedImage;

/** Interface defines the minimum set of functions that an 'Image'
 component must implement in any GMP user-interface */
public interface gmpImage {
    /** The method will set the BufferedImage part of GMP Image by reading it
     * from specified URLand return true if successfull
     * @param path String
     * @return boolean
     */
    public boolean setImage(String path);
    /** Returns the BufferedImage object that is part of this GMP Image
     * @return boolean
     */
    public BufferedImage getImage();
    /** The method will repaint the Image and return true if successfull
     * @return boolean
     */
    public boolean Refresh();
}
