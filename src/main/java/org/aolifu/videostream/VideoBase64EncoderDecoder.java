package org.aolifu.videostream;

import java.io.*;
import java.nio.file.Files;  
import java.nio.file.Paths;  
import java.util.Base64;  
  
public class VideoBase64EncoderDecoder {  
  
    // 编码视频文件为Base64字符串  
    public static String encodeVideoToBase64(String inputVideoPath) throws IOException {  
        byte[] videoBytes = Files.readAllBytes(Paths.get(inputVideoPath));  
        return Base64.getEncoder().encodeToString(videoBytes);  
    }  
  
    // 解码Base64字符串为视频文件  
    public static void decodeBase64ToVideo(String base64String, String outputVideoPath) throws IOException {  
        byte[] videoBytes = Base64.getDecoder().decode(base64String);  
        Files.write(Paths.get(outputVideoPath), videoBytes);  
    }  
  
    public static void main(String[] args) {  
        String inputVideoPath =  System.getProperty("user.home") + "/Downloads/temp/output-cut1.mp4";
        String outputVideoPath =  System.getProperty("user.home") + "/Downloads/temp/output-cut1-copy1.mp4";
  
        try {  
            // 编码视频为Base64字符串  
            String base64Video = encodeVideoToBase64(inputVideoPath);  
            System.out.println("Base64 encoded video size: " + base64Video.length());  
  
            // 解码Base64字符串为视频文件  
            decodeBase64ToVideo(base64Video, outputVideoPath);  
            System.out.println("Decoded video saved to: " + outputVideoPath);  
  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
}