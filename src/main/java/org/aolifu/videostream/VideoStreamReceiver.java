package org.aolifu.videostream;

import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;

public class VideoStreamReceiver {

    public static void receiveStream(String rtmpUrl) throws FFmpegFrameGrabber.Exception {
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(rtmpUrl);
        grabber.start();

        CanvasFrame canvas = new CanvasFrame("Video Stream", 1);
        canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

        while (grabber.grab() != null) {
            Frame frame = grabber.grab();
            if (frame != null) {
                canvas.showImage(frame);
            }
        }
        grabber.stop();
    }
}
