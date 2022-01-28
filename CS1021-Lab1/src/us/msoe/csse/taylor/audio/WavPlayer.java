package us.msoe.csse.taylor.audio;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Loads and plays a .wav file.
 * @version 2015_12_17.1.2
 * @author t a y l o r@msoe.edu
 */
public class WavPlayer {

    /**
     * The size of the audio buffer.
     */
    private static final int BUFFER_SIZE = 64000;
    
    /**
     * Opens, reads, and plays the .wav file.
     */
    public static void play(String filename) {
        try (AudioInputStream audioIS = AudioSystem.getAudioInputStream(new File(filename))) {
            DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioIS.getFormat());
            if(AudioSystem.isLineSupported(dataLineInfo)) {
                SourceDataLine line = (SourceDataLine)AudioSystem.getLine(dataLineInfo);
                line.open(audioIS.getFormat());
                line.start();
                byte[] buffer = new byte[BUFFER_SIZE];
                int bytesRead = 0;
                int offset = 0;
                while(bytesRead != -1) {
                    bytesRead = audioIS.read(buffer);
                    if(bytesRead>0) {
                        line.write(buffer, offset, bytesRead);
                    }
                }
                line.drain();
            }
        } catch (UnsupportedAudioFileException e) {
            System.err.println("Encountered an audio file with the incorrect format.\n" + e.getMessage());
        } catch (LineUnavailableException e) {
            System.err.println("Encountered a problem accessing the audio system.\n" + e.getMessage());
        } catch (IOException e) {
            System.err.println("Encountered a problem accessing the audio file.\n" + e.getMessage());
        }

    }
}
