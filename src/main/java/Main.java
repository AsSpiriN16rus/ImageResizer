import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;

public class Main {

    private static int newWidth = 300;

    public static void main(String[] args) {
        String srcFolder = "ImageResizer\\src\\imgBefore";
        String dstFolder = "ImageResizer\\src\\imgAfter";

        int numberOfCores = Runtime.getRuntime().availableProcessors();
        File srcDir = new File(srcFolder);

        long start = System.currentTimeMillis();

        File[] files = srcDir.listFiles();

        int from = 0;

        for (int i = 0; i < (numberOfCores < files.length ? numberOfCores : files.length) ; i++) {
            final int to = (from + (int) Math.ceil((files.length - from) / (double) (numberOfCores - i)));
            File[] files3 = Arrays.copyOfRange(files, from, to);
            File[] files1 = new File[to];
            System.arraycopy(files3, 0, files1, 0 , files3.length);
            ImageResizer resizer = new ImageResizer(files1, newWidth, dstFolder, start);
            new Thread(resizer).start();
            from = to;
        }

    }

}
