package org.aolifu.videostream;

import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.javacv.*;  
  
import java.io.File;  
  
public class VideoCutter {  
    public static void main(String[] args) throws Exception {  
        String inputFile = System.getProperty("user.home") + "/Downloads/temp/output.mp4";
        String outputFile = System.getProperty("user.home") + "/Downloads/temp/output-cut3.mp4";
        int startTime = 0; // 单位：秒  
        int duration = 10; // 单位：秒  
  
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(inputFile);
        grabber.start();  
  
        FrameRecorder recorder = new FFmpegFrameRecorder(outputFile, grabber.getImageWidth(), grabber.getImageHeight(), grabber.getAudioChannels());  
        recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);  
        recorder.setFormat("mp4");  
        recorder.setSampleRate(grabber.getSampleRate());  
        recorder.start();

        Frame frame;
        long startTimeMicros = startTime * 1000L * 1000L; // 转换为微秒
        long endTimeMicros = startTimeMicros + duration * 1000L * 1000L; // 转换为微秒

        grabber.setTimestamp(startTimeMicros); // 跳转到开始位置

        while ((frame = grabber.grabFrame()) != null) {
            long timestamp = grabber.getTimestamp(); // 获取当前帧的时间戳
            if (timestamp >= startTimeMicros && timestamp <= endTimeMicros) {
                recorder.record(frame);
            } else if (timestamp > endTimeMicros) {
                break;
            }
        }
        recorder.stop();
        grabber.stop();
    }  
}