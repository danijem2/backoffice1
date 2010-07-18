package gmp.swing;

import java.io.*;
import javax.imageio.*;
import java.awt.image.*;

public class PhotoOperation {

    public static BufferedImage readImageFromFile(File file)
       throws IOException
    {
        return ImageIO.read(file);
    }

    public static void writeImageToJPG
       (File file,BufferedImage bufferedImage)
          throws IOException
    {
        ImageIO.write(bufferedImage,"jpg",file);
    }
}
