package org.aolifu.videostream;

import org.junit.jupiter.api.Test;

public class VideoMergerTest {

    @Test
    public void mergeVideoTest() throws Exception {
        // 输入视频文件路径
        String[] inputVideoFiles = {
                System.getProperty("user.home") + "/Downloads/temp/output-cut1.mp4",
                System.getProperty("user.home") + "/Downloads/temp/output-cut2.mp4",
                System.getProperty("user.home") + "/Downloads/temp/output-cut3.mp4"
        };

        // 输出视频文件路径
        String outputVideoFile = System.getProperty("user.home") + "/Downloads/temp/merge3.mp4";
        VideoMerger.mergeVideo(inputVideoFiles, outputVideoFile);
    }
}
