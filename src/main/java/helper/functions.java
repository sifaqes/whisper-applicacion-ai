package helper;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class functions {

	public static int[] get_resulution(double d, double e) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int[] result = new int[2];
        result[0] = (int) (screenSize.width * d);;
        result[1] = (int) (screenSize.height * e);
        return result;
	}

	public static long result = 0;
	public static long get_size(String url) {
		
        Thread thread = new Thread(() -> {
            try {

                Path path = Paths.get(url);
                long fileSize = Files.size(path);
                result = fileSize;
            } catch (Exception e) {
            	result = 0;
                e.printStackTrace();
            }
        });
        thread.start();

        try {
            thread.join();
            return result;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return 0;
        }
        
	}
}
