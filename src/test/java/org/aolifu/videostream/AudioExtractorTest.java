package org.aolifu.videostream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AudioExtractorTest {

    @Test
    public void extractAudioTest() throws Exception {
        String inputVideoFile = System.getProperty("user.home") + "/Downloads/temp/output-cut1.mp4";
        String outputAudioFile = System.getProperty("user.home") + "/Downloads/temp/audio2.aac";
        Assertions.assertDoesNotThrow(() -> AudioExtractor.extractAudio(inputVideoFile, outputAudioFile));
    }
}
