package org.aolifu.videostream;

import org.bytedeco.ffmpeg.avcodec.AVPacket;
import org.bytedeco.ffmpeg.global.avcodec;  
import org.bytedeco.javacv.*;  
  
import java.io.File;  
  
public class AudioExtractor {  
    public static void main(String[] args) throws Exception {  
        String inputVideoFile = System.getProperty("user.home") + "/Downloads/temp/output-cut1.mp4";
        String outputAudioFile = System.getProperty("user.home") + "/Downloads/temp/audio.aac";
        extractAudio(inputVideoFile, outputAudioFile);
    }

    public static void extractAudio(String inputVideoFile, String outputAudioFile) throws Exception {
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(inputVideoFile);
        grabber.start();

        // 创建音频录制器
        FrameRecorder recorder = new FFmpegFrameRecorder(outputAudioFile, grabber.getAudioChannels());
        recorder.setSampleRate(grabber.getSampleRate());
        recorder.setFormat("aac"); // 设置输出音频格式为AAC
        recorder.setAudioCodec(avcodec.AV_CODEC_ID_AAC);
        recorder.start();

        Frame frame;
        AVPacket packet;
        while ((frame = grabber.grabFrame()) != null) {
            if (frame.image != null) {
                // 忽略视频帧，只处理音频帧
                continue;
            }

            if (frame.samples != null) {
                // 写入音频帧到输出文件
                recorder.record(frame);
            }
        }
        recorder.stop();
        grabber.stop();
    }
}