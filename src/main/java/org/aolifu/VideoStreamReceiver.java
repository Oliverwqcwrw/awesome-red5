package org.aolifu;

import org.red5.client.net.rtmp.RTMPClient;
import org.red5.server.api.event.IEvent;
import org.red5.server.api.event.IEventDispatcher;
import org.red5.server.net.rtmp.RTMPConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VideoStreamReceiver {
    private static final Logger logger = LoggerFactory.getLogger(VideoStreamReceiver.class);

    private String red5Url;
    private String streamName;

    public VideoStreamReceiver(String red5Url, String streamName) {
        this.red5Url = red5Url;
        this.streamName = streamName;
    }

    public void receive() {
        RTMPClient rtmpClient = new RTMPClient();
        rtmpClient.connect("localhost", 1935, "live");
        RTMPConnection connection = rtmpClient.getConnection();
        rtmpClient.setStreamEventDispatcher(new IEventDispatcher() {
            @Override
            public void dispatchEvent(IEvent event) {
                logger.info("Event: {}", event);
            }
        });
    }

    public static void main(String[] args) {
        String red5Url = "rtmp://localhost/live";
        String streamName = "myStream1";

        VideoStreamReceiver receiver = new VideoStreamReceiver(red5Url, streamName);
        receiver.receive();
    }
}