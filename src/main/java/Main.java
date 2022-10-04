import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String srcFolder = "/Users/aliceklim/Desktop/src";
        String dstFolder = "/Users/aliceklim/Desktop/dst";
        int cores = Runtime.getRuntime().availableProcessors();
        int newWidth = 300;

        File srcDir = new File(srcFolder);
        long startTime = System.currentTimeMillis();
        File[] files = srcDir.listFiles();
        int remainder = files.length % cores;
        int chunksNum = (int) (Math.floor(files.length / cores));
        int start = 0;
        int end = 0;
        List<File[]>fileArrayList = new ArrayList<>();

        for (int i = 0; i < cores; i++){
            end += chunksNum;
            if (i == cores - 1){
                fileArrayList.add(Arrays.copyOfRange(files, start, files.length));
            } else {
                fileArrayList.add(Arrays.copyOfRange(files, start, end));
                start += chunksNum;
            }
        }
        for (int i = 0; i < cores; i++){
            ImageResizer imgResizer = new ImageResizer(fileArrayList.get(i), newWidth, dstFolder);
            imgResizer.start();
            System.out.println(i);
        }
        System.out.println("Duration: " + (System.currentTimeMillis() - startTime));
    }
}

