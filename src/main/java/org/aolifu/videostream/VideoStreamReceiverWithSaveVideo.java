package org.aolifu.videostream;

import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;

public class VideoStreamReceiverWithSaveVideo {

    public static void main(String[] args) throws Exception {
        String rtmpUrl = "rtmp://localhost/live/livestream"; // 替换为你的RTMP流地址
        String outputFile = System.getProperty("user.home") + "/Downloads/temp/output.mp4"; // 输出视频文件的路径
        receiveVideoStreamWithSave(rtmpUrl, outputFile);

    }

    public static void receiveVideoStreamWithSave(String rtmpUrl, String outputFile) throws Exception {
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(rtmpUrl);
        grabber.start();

        // 获取流的参数
        Frame frame = grabber.grab();
        if (frame == null) {
            throw new RuntimeException("无法从RTMP流中获取帧");
        }
        int imageHeight = grabber.getImageHeight();
        int imageWidth = grabber.getImageWidth();
        int audioChannels = grabber.getAudioChannels();
        int sampleRate = grabber.getSampleRate();
        int videoBitrate = grabber.getVideoBitrate();
        double frameRate = grabber.getFrameRate();


        // 创建录制器
        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(outputFile, imageWidth, imageHeight, audioChannels);
        recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264); // 设置视频编码
        recorder.setAudioCodec(avcodec.AV_CODEC_ID_AAC); // 设置音频编码
        recorder.setFormat("mp4"); // 设置输出格式
        recorder.setFrameRate(frameRate);
        recorder.setSampleRate(sampleRate);
        recorder.setVideoBitrate(videoBitrate);
        recorder.start();

        int i = 0;
        // 录制循环
        while ((frame = grabber.grab()) != null) {
            recorder.record(frame);
            // 停止录制和抓取
            if (i++ > 3000) {
                recorder.stop();
                break;
            }
        }
        grabber.stop();
    }
}
