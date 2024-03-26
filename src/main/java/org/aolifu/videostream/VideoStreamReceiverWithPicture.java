package org.aolifu.videostream;

import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class VideoStreamReceiverWithPicture {

    /**
     * @param rtmpUrl RTMP流地址
     * @param saveFrameAfter 保存图片的间隔帧数
     * @throws IOException
     */
    public static void savePicture(String rtmpUrl, int saveFrameAfter) throws IOException {
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(rtmpUrl);
        grabber.start();

        CanvasFrame canvas = new CanvasFrame("Video Stream", 1);
        canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        // 创建一个Java2DFrameConverter实例，用于转换Frame为BufferedImage
        Java2DFrameConverter converter = new Java2DFrameConverter();

        // 计数器，用于决定是否保存当前帧为图片 15s视频1000帧
        int frameCount = 0;
        while (true) {
            Frame frame = grabber.grab();
            if (frame != null) {
                canvas.showImage(frame);
                if (frame.image == null) {
                    continue;
                }
                // 检查是否应该保存当前帧为图片
                if (frameCount % saveFrameAfter == 0) {
                    BufferedImage bufferedImage = converter.convert(frame);
                    // 创建文件路径和名称，这里以当前时间戳和帧数为文件名
                    String filename = System.getProperty("user.home") + "/Downloads/temp/"+ "frame_" + System.currentTimeMillis() + "_" + frameCount + ".png";
                    File file = new File(filename);
                    // 使用ImageIO保存BufferedImage为图片文件
                    ImageIO.write(bufferedImage, "png", file);
                    System.out.println("Saved frame as " + file.getAbsolutePath());
                }
                frameCount++;
            }
            if (frameCount > 1000) {
                grabber.stop();
                grabber.release();
                break;
            }
        }
    }
}