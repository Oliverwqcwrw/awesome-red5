package org.aolifu.videostream;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class VideoBase64EncoderDecoderTest {

    @Test
    public void encodeAndDecodeTest() throws IOException {
        String inputVideoPath =  System.getProperty("user.home") + "/Downloads/temp/output-cut1.mp4";
        String outputVideoPath =  System.getProperty("user.home") + "/Downloads/temp/output-cut1-copy1.mp4";
        // 编码视频为Base64字符串
        String base64Video = VideoBase64EncoderDecoder.encodeVideoToBase64(inputVideoPath);
        System.out.println("Base64 encoded video size: " + base64Video.length());

        // 解码Base64字符串为视频文件
        VideoBase64EncoderDecoder.decodeBase64ToVideo(base64Video, outputVideoPath);
        System.out.println("Decoded video saved to: " + outputVideoPath);
    }
}
