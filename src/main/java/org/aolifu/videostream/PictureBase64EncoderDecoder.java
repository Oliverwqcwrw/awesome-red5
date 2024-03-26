package org.aolifu.videostream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

public class PictureBase64EncoderDecoder {

    public static void main(String[] args) throws IOException {
        String imagePath = System.getProperty("user.home") + "/Downloads/temp/test.png"; // 替换为你的图片路径
        String base64Image = encodeImageToBase64(imagePath);
        System.out.println(base64Image);

        String outputPath =System.getProperty("user.home") + "/Downloads/temp/test2.png";// 替换为你希望保存的输出文件路径
        decodeBase64ToImage(base64Image, outputPath);
    }

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
