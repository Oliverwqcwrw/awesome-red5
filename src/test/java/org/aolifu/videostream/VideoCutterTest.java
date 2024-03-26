package org.aolifu.videostream;

import org.junit.jupiter.api.Test;

public class VideoCutterTest {

    @Test
    public void cutVideoTest() throws Exception {
        String inputFile = System.getProperty("user.home") + "/Downloads/temp/output.mp4";
        String outputFile = System.getProperty("user.home") + "/Downloads/temp/output-cut5.mp4";
        int startTime = 5; // 单位：秒
        int duration = 10; // 单位：秒
        VideoCutter.cutVideo(inputFile, outputFile, startTime, duration);
    }
}
