package org.aolifu.videostream;

import org.junit.jupiter.api.Test;

public class RtmpPusherTest {

    @Test
    public void pushTest() throws Exception {
        String inputFile = System.getProperty("user.home") + "/Downloads/temp/output-cut1.mp4";// 本地视频文件路径
        String rtmpUrl = "rtmp://localhost/live/myStream1"; // RTMP服务器地址和流名
        RtmpPusher.push(inputFile, rtmpUrl);
    }
}
