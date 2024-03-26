package org.aolifu.videostream;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.junit.jupiter.api.Test;

public class VideoStreamReceiverTest {

    @Test
    public void receiveStreamTest() throws FFmpegFrameGrabber.Exception {
        String rtmpUrl = "rtmp://localhost/live/livestream"; // 替换为你的RTMP流地址
        VideoStreamReceiver.receiveStream(rtmpUrl);
    }
}
