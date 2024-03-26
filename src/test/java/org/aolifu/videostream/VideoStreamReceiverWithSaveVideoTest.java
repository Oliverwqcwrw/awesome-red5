package org.aolifu.videostream;

import org.junit.jupiter.api.Test;

public class VideoStreamReceiverWithSaveVideoTest {

    @Test
    public void receiveVideoStreamWithSaveTest() throws Exception {
        String rtmpUrl = "rtmp://localhost/live/livestream"; // 替换为你的RTMP流地址
        String outputFile = System.getProperty("user.home") + "/Downloads/temp/output2.mp4"; // 输出视频文件的路径
        VideoStreamReceiverWithSaveVideo.receiveVideoStreamWithSave(rtmpUrl, outputFile);
    }
}
