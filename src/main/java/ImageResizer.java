import org.imgscalr.Scalr;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageResizer extends Thread {
    private File[] files;
    private int newWidth;
    private String dstFolder;
    public ImageResizer(File[] files, int newWidth, String dstFolder){
        this.files = files;
        this.newWidth = newWidth;
        this.dstFolder = dstFolder;
    }

    @Override
    public void run(){
        try{
            for (File file : files) {
                BufferedImage image = ImageIO.read(file);
                if (image == null) {
                    continue;
                }
                int newWidth = 300;
                BufferedImage scaledImage = Scalr.resize(image,
                        Scalr.Mode.FIT_TO_WIDTH, newWidth, Scalr.OP_ANTIALIAS);
                File newFile = new File(dstFolder + "/" + file.getName());
                ImageIO.write(scaledImage, "jpg", newFile);
                System.out.println("thread started. Copied: " + file.getName());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}


