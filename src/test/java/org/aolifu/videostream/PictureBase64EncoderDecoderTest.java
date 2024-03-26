package org.aolifu.videostream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class PictureBase64EncoderDecoderTest {

    @Test
    public void encodeAndDecodeTest() throws IOException {
        String imagePath = System.getProperty("user.home") + "/Downloads/temp/test.png"; // 替换为你的图片路径
        String base64Image = PictureBase64EncoderDecoder.encodeImageToBase64(imagePath);
        System.out.println(base64Image);

        String outputPath =System.getProperty("user.home") + "/Downloads/temp/test2.png";// 替换为你希望保存的输出文件路径
        PictureBase64EncoderDecoder.decodeBase64ToImage(base64Image, outputPath);
    }
}
