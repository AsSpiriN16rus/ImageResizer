

import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;

public class ImageResizer  implements Runnable {


    private File[] files;
    private int newWidth;
    private String dstFolder;
    private  long start;

    public ImageResizer(File[] files, int newWidth, String dstFolder, long start) {
        this.files = files;
        this.newWidth = newWidth;
        this.dstFolder = dstFolder;
        this.start = start;
    }

    @Override
    public void run() {
        try {
            for (File file : files) {
                if (file != null){
                    BufferedImage image = ImageIO.read(file);
                    if (image == null) {
                        continue;
                    }

                    int newHeight = (int) Math.round(
                            image.getHeight() / (image.getWidth() / (double) newWidth)
                    );

                    BufferedImage thumbnail = Scalr.resize(image, newWidth, newHeight, null);


                    File newFile = new File(dstFolder + "/" + file.getName());
                    ImageIO.write(thumbnail, "png", newFile);
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Finished after start : " + (System.currentTimeMillis() - start));
    }
}
