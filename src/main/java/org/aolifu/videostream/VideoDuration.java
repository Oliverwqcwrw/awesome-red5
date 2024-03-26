package org.aolifu.videostream;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FrameGrabber;

public class VideoDuration {

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
