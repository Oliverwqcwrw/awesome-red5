package org.aolifu.videostream;

import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.javacv.*;
  

public class VideoMerger {  
    public static void main(String[] args) throws Exception {  
        // 输入视频文件路径  
        String[] inputVideoFiles = {
                System.getProperty("user.home") + "/Downloads/temp/output-cut1.mp4",
                System.getProperty("user.home") + "/Downloads/temp/output-cut2.mp4",
                System.getProperty("user.home") + "/Downloads/temp/output-cut3.mp4"
        };  
  
        // 输出视频文件路径  
        String outputVideoFile =  System.getProperty("user.home") + "/Downloads/temp/merge1.mp4";
  
        // 创建FFmpegFrameRecorder来写入合并后的视频  
        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(outputVideoFile, getWidth(inputVideoFiles[0]), getHeight(inputVideoFiles[0]));  
        recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
        recorder.setFormat("mp4");  
        recorder.setFrameRate(getFrameRate(inputVideoFiles[0]));  
        recorder.setSampleRate(getSampleRate(inputVideoFiles[0]));  
        recorder.setAudioChannels(getAudioChannels(inputVideoFiles[0]));  
        recorder.start();  
  
        for (String inputVideoFile : inputVideoFiles) {  
            FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(inputVideoFile);  
            grabber.start();  
  
            Frame frame;  
            while ((frame = grabber.grabFrame()) != null) {  
                recorder.record(frame);  
            }  
  
            grabber.stop();  
        }  
  
        recorder.stop();  
        recorder.release();  
    }  
  
    // 辅助方法，获取视频的宽度  
    private static int getWidth(String videoFile) throws Exception {  
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(videoFile);  
        grabber.start();  
        int width = grabber.getImageWidth();  
        grabber.stop();  
        return width;  
    }  
  
    // 辅助方法，获取视频的高度  
    private static int getHeight(String videoFile) throws Exception {  
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(videoFile);  
        grabber.start();  
        int height = grabber.getImageHeight();  
        grabber.stop();  
        return height;  
    }  
  
    // 辅助方法，获取视频的帧率  
    private static double getFrameRate(String videoFile) throws Exception {  
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(videoFile);  
        grabber.start();  
        double frameRate = grabber.getFrameRate();
        grabber.stop();  
        return frameRate;  
    }  
  
    // 辅助方法，获取音频的采样率  
    private static int getSampleRate(String videoFile) throws Exception {  
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(videoFile);  
        grabber.start();  
        int sampleRate = grabber.getSampleRate();  
        grabber.stop();  
        return sampleRate;  
    }  
  
    // 辅助方法，获取音频的通道数  
    private static int getAudioChannels(String videoFile) throws Exception {  
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(videoFile);  
        grabber.start();  
        int audioChannels = grabber.getAudioChannels();  
        grabber.stop();  
        return audioChannels;  
    }  
}