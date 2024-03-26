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
}