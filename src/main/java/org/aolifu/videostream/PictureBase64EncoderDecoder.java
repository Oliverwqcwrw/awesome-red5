package org.aolifu.videostream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

public class PictureBase64EncoderDecoder {

    public static void decodeBase64ToImage(String base64Image, String outputPath) throws IOException {
        byte[] imageBytes = Base64.getDecoder().decode(base64Image);
        File outputFile = new File(outputPath);
        try (FileOutputStream fos = new FileOutputStream(outputFile)) {
            fos.write(imageBytes);
            fos.flush();
        }
    }

    public static String encodeImageToBase64(String imagePath) throws IOException {
        File file = new File(imagePath);
        FileInputStream fis = new FileInputStream(file);
        byte[] byteArray = new byte[(int) file.length()];
        fis.read(byteArray);
        fis.close();
        return Base64.getEncoder().encodeToString(byteArray);
    }
}
