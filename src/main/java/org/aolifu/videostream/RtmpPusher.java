package org.aolifu.videostream;

import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.javacv.*;

import static org.bytedeco.ffmpeg.global.avformat.avformat_network_deinit;
import static org.bytedeco.ffmpeg.global.avformat.avformat_network_init;
import static org.bytedeco.ffmpeg.global.avutil.AV_SAMPLE_FMT_FLTP;

public class RtmpPusher {

    public static void push(String inputFile, String rtmpUrl) throws Exception {
        // 初始化网络（如果使用的是RTMP/RTSP等网络协议）
        avformat_network_init();
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(inputFile);
        grabber.start();

        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(rtmpUrl, grabber.getImageWidth(), grabber.getImageHeight(), grabber.getAudioChannels());
        recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264); // 设置视频编码器为H.264
        recorder.setFrameRate(grabber.getFrameRate());
        recorder.setSampleFormat(AV_SAMPLE_FMT_FLTP);
        recorder.setSampleRate(grabber.getSampleRate());
        recorder.setAudioChannels(grabber.getAudioChannels());
        recorder.setAudioCodec(avcodec.AV_CODEC_ID_AAC); // 设置音频编码器为AAC
        recorder.setFormat("flv"); // 设置输出格式为flv
        recorder.start();

        Frame frame;
        while ((frame = grabber.grabFrame()) != null) {
            recorder.record(frame);
        }

        recorder.stop();
        grabber.stop();
        avformat_network_deinit(); // 清理网络初始化
    }
}