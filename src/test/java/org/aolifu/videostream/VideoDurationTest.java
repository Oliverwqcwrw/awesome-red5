package org.aolifu.videostream;

import org.junit.jupiter.api.Test;

public class VideoDurationTest {

    @Test
    public void getVideoDurationTest() {
        String videoFilePath = System.getProperty("user.home") + "/Downloads/temp/output.mp4"; // 替换为你的视频文件路径
        long videoDuration = VideoDuration.getVideoDuration(videoFilePath);
        System.out.println("Video duration: " + videoDuration + " seconds");
    }
}
