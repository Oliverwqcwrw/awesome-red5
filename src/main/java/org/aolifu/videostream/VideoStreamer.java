package org.aolifu.videostream;

import org.apache.mina.core.buffer.IoBuffer;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.opencv.opencv_core.Mat;
import org.red5.client.net.rtmp.INetStreamEventHandler;
import org.red5.client.net.rtmp.RTMPClient;
import org.red5.server.api.IConnection;
import org.red5.server.api.listeners.AbstractConnectionListener;
import org.red5.server.api.service.IPendingServiceCall;
import org.red5.server.api.service.IPendingServiceCallback;
import org.red5.server.messaging.IMessage;
import org.red5.server.net.rtmp.RTMPConnection;
import org.red5.server.net.rtmp.event.Notify;
import org.red5.server.net.rtmp.event.VideoData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VideoStreamer {
    private static final Logger logger = LoggerFactory.getLogger(VideoStreamer.class);

    private String videoPath;
    private String red5Url;
    private String streamName;

    public VideoStreamer(String videoPath, String red5Url, String streamName) {
        this.videoPath = videoPath;
        this.red5Url = red5Url;
        this.streamName = streamName;
    }

    public void stream() {
        RTMPClient rtmpClient = new RTMPClient();
        rtmpClient.connect("localhost", 1935, "live");
        RTMPConnection connection = rtmpClient.getConnection();
        connection.addListener(new AbstractConnectionListener() {
            @Override
            public void notifyConnected(IConnection conn) {
                rtmpClient.createStream(new IPendingServiceCallback() {
                    @Override
                    public void resultReceived(IPendingServiceCall call) {
                        Number streamId = (Number) call.getResult();
                        logger.info("Stream ID: {}", streamId);
                        rtmpClient.play(streamId.intValue(), streamName, 0, -1);
                    }
                });
                rtmpClient.publish(0, streamName, "live", new INetStreamEventHandler() {
                    @Override
                    public void onStreamEvent(Notify notify) {
                        logger.info("Stream event: {}", notify);
                    }
                });
                // 创建FFmpegFrameGrabber，用于从mp4文件中获取帧
                FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(videoPath);
                try {
                    grabber.start();
                    Frame frame;
                    Java2DFrameConverter converter = new Java2DFrameConverter();
                    OpenCVFrameConverter.ToMat matConverter = new OpenCVFrameConverter.ToMat();
                    while ((frame = grabber.grab()) != null) {
                        // 将Frame转换为Mat
                        Mat mat = matConverter.convert(frame);
                        // 将Mat转换为byte[]
                        byte[] bytes = new byte[(int) (mat.total() * mat.channels())];
                        mat.data().get(bytes);
                        // 将byte[]转换为IoBuffer
                        IoBuffer buffer = IoBuffer.wrap(bytes);
                        // 将IoBuffer转换为VideoData
                        VideoData videoData = new VideoData(buffer);
                        // 发送VideoData到Red5服务器
                        IMessage iMessage = (IMessage) new VideoData(buffer);
                        rtmpClient.publishStreamData(new Integer(0), iMessage);
                    }
                } catch (Exception e) {
                    logger.error("Error while streaming video: {}", e.getMessage());
                } finally {
                    try {
                        grabber.stop();
                    } catch (Exception e) {
                        logger.error("Error while stopping grabber: {}", e.getMessage());
                    }
                }
            }

            @Override
            public void notifyDisconnected(IConnection conn) {

            }
        });
    }

    public static void main(String[] args) {
        String videoPath = System.getProperty("user.home") + "/Downloads/document/mine/mp4/airpods3.mp4";
        String red5Url = "rtmp://localhost/live";
        String streamName = "myStream1";

        VideoStreamer streamer = new VideoStreamer(videoPath, red5Url, streamName);
        streamer.stream();
    }
}
