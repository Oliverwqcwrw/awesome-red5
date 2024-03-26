package org.aolifu.videostream;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class VideoStreamReceiverWithPictureTest {

    @Test
    public void savePictureTest() throws IOException {
        String rtmpUrl = "rtmp://localhost/live/livestream"; // 替换为你的RTMP流地址
        VideoStreamReceiverWithPicture.savePicture(rtmpUrl, 100);
    }
}
