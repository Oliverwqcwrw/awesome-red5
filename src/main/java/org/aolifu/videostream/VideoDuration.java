package org.aolifu.videostream;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FrameGrabber;

public class VideoDuration {

    public static void main(String[] args) {
        String videoFilePath = System.getProperty("user.home") + "/Downloads/temp/output.mp4"; // 替换为你的视频文件路径
        long videoDuration = getVideoDuration(videoFilePath);
        System.out.println("Video duration: " + videoDuration + " seconds");
    }

    public static long getVideoDuration(String videoFilePath) {

        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(videoFilePath);

        try {
            grabber.start();
            long videoDuration = (long) (grabber.getLengthInFrames() / grabber.getFrameRate());
            return videoDuration;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                grabber.stop();
            } catch (FrameGrabber.Exception ex) {
                ex.printStackTrace();
            }
        }
        return 0;
    }
}
