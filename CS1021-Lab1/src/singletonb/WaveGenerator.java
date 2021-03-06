/*
 * Course: CS1021 - 021
 * Winter 2021
 * Lab 1 - Wav Files
 * Name: Benjamin Singleton
 * Created: 11/29/2021
 * Modified: 12/03/2021
 */
package singletonb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import us.msoe.csse.taylor.audio.WavFile;
import us.msoe.csse.taylor.audio.WavPlayer;

/**
 * Supplies users with various options on how to play, edit, or create wav files
 *
 * @author Benjamin Singleton
 */
public class WaveGenerator {
    /**
     * The default sample rate all new audio files will be created at
     */
    public static final int DEFAULT_SAMPLE_RATE = 8000;

    /**
     * The default amount of valid bits all new audio files will be created with
     */
    public static final int DEFAULT_VALID_BITS = 8;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        boolean programRunning = true;
        while(programRunning) {
            System.out.println("""
                    Please select an option:
                    - 0: Exit program
                    - 1: Reverse wav file
                    - 2: Create mono wav file of one frequency
                    - 3: Create stereo wav file of two frequencies
                    - 4: Play a wav file
                    - 5: Create a song""");
            String input = in.nextLine();
            switch(input) {
                // exit program
                case "0" -> {
                    programRunning = false;
                }

                // reverse wav file
                case "1" -> {
                    // prompt user for data
                    System.out.print("Enter wav filename: ");
                    String filename = in.nextLine();

                    // create wav files
                    WavFile wavFile = new WavFile("sounds/" + filename + ".wav");
                    WavFile reversedWavFile = new WavFile("sounds/" + filename + "Rev.wav",
                            wavFile.getNumChannels(), wavFile.getNumFrames(),
                            wavFile.getValidBits(), wavFile.getSampleRate());

                    // generate samples and save wav file
                    ArrayList<Double> reversedSamples = reverseArray(wavFile.getSamples());
                    saveWavFile(reversedWavFile, reversedSamples,
                            "Saved reversed file as " + filename + "Rev!\n");
                }

                // create one-tone wav file
                case "2" -> {
                    // prompt user for data
                    System.out.print("Enter wav filename: ");
                    String filename = in.nextLine();
                    System.out.print("Enter frequency: ");
                    double frequency = Double.parseDouble(in.nextLine());
                    System.out.println("Enter sound shape: (sine / square)");
                    String shape = in.nextLine();

                    // create wav file
                    WavFile newWavFile = new WavFile("sounds/" + filename + ".wav",
                            1, DEFAULT_SAMPLE_RATE, DEFAULT_VALID_BITS, DEFAULT_SAMPLE_RATE);

                    // generate samples and save wav file
                    ArrayList<Double> samples = generateTone(frequency,
                            newWavFile.getSampleRate(), 1, shape);
                    saveWavFile(newWavFile, samples,
                            "Saved tone at " + frequency + "hz as " + filename + "!\n");
                }

                // create two-tone wav file
                case "3" -> {
                    // prompt user for data
                    System.out.print("Enter wav filename: ");
                    String filename = in.nextLine();
                    System.out.print("Enter left frequency: ");
                    double leftFrequency = Double.parseDouble(in.nextLine());
                    System.out.print("Enter right frequency: ");
                    double rightFrequency = Double.parseDouble(in.nextLine());
                    System.out.println("Enter left sound shape: (sine / square)");
                    String leftShape = in.nextLine();
                    System.out.println("Enter right shape: (sine / square)");
                    String rightShape = in.nextLine();

                    // create wav file
                    WavFile newWavFile = new WavFile("sounds/" + filename + ".wav", 2,
                            DEFAULT_SAMPLE_RATE, DEFAULT_VALID_BITS, DEFAULT_SAMPLE_RATE);

                    // generate samples and save wav file
                    ArrayList<Double> leftSamples = generateTone(leftFrequency,
                            newWavFile.getSampleRate(), 1, leftShape);
                    ArrayList<Double> rightSamples = generateTone(rightFrequency,
                            newWavFile.getSampleRate(), 1, rightShape);
                    saveWavFile(newWavFile, interleaveSamples(leftSamples, rightSamples),
                            "Saved tones at " + leftFrequency + "hz and " +
                                    rightFrequency + "hz as " + filename + "!\n");
                }

                // play wav file
                case "4" -> {
                    System.out.print("Enter wav filename: ");
                    String filename = in.nextLine();
                    WavPlayer.play("sounds/" + filename + ".wav");
                }

                // create song
                case "5" -> {
                    // prompt user for data
                    System.out.print("Enter wav filename: ");
                    String filename = in.nextLine();
                    System.out.println("""
                            Enter note sequence: (00 = Rest)
                            Example sequence:
                            F3 F3 C4 C4 D4 D4 C4 00 A#4 A#4 A4 A4 G3 G3 F3 00""");
                    String sequence = in.nextLine();
                    System.out.println("Enter sound shape: (sine / square)");
                    String shape = in.nextLine();

                    // generate samples
                    ArrayList<Double> samples = parseSequence(sequence.split(" "),
                            DEFAULT_SAMPLE_RATE, shape);

                    // create and save wav file
                    WavFile newWavFile = new WavFile("sounds/" + filename + ".wav",
                            1, samples.size(), DEFAULT_VALID_BITS, DEFAULT_SAMPLE_RATE);
                    saveWavFile(newWavFile, samples, "Saved song as " + filename + "!\n");
                }

                default -> {
                    System.out.println("Invalid selection");
                }
            }
        }
    }

    /**
     * Creates a new array, with all of its indexes reversed from the original array
     * @param arr the original array
     * @return a reversed copy of the original array
     */
    public static ArrayList<Double> reverseArray(ArrayList<Double> arr) {
        ArrayList<Double> reversedArr = new ArrayList<>();
        for(int i = 0; i < arr.size(); i++) {
            reversedArr.add(arr.get(arr.size() - 1 - i));
        }
        return reversedArr;
    }

    /**
     * Generates samples of a specified tone to be used by WavFiles
     * @param freq the frequency (in hertz) of the tone
     * @param sampleRate the sample rate of the tone
     * @param seconds the length (in seconds) the tone will be produced for
     * @param shape the shape of the soundwave the tone will be played with (sine / square)
     * @return the samples used by a WavFile for this tone
     */
    public static ArrayList<Double> generateTone(double freq, long sampleRate,
                                                 double seconds, String shape) {
        ArrayList<Double> samples = new ArrayList<>();
        for(int i = 0; i < Math.round(sampleRate * seconds); i++) {
            double sinAt = Math.sin(2 * Math.PI * i * (freq / sampleRate));
            if (shape.equals("square")) {
                samples.add((double) (Math.round((sinAt + 1) / 2) * 2 - 1));
            } else {
                samples.add(sinAt);
            }
        }
        return samples;
    }

    /**
     * Interleaves two sets of samples together, to be used by
     * WavFiles with two channels for stereo playback
     * @param leftSamples the samples to be played through the left speaker
     * @param rightSamples the samples to be played through the right speaker
     * @return the interleaved samples
     */
    public static ArrayList<Double> interleaveSamples(ArrayList<Double> leftSamples,
                                                      ArrayList<Double> rightSamples) {
        ArrayList<Double> interleavedSamples = new ArrayList<>();
        for(int i = 0; i < leftSamples.size(); i++) {
            interleavedSamples.add(leftSamples.get(i));
            interleavedSamples.add(rightSamples.get(i));
        }
        return interleavedSamples;
    }

    /**
     * Turns a sequence of notes names and octaves into WavFile samples
     * @param sequence A String of notes, separated by spaces,
     *                 representing the tones that will be generated in order
     * @param sampleRate the sample rate the tones will be produced at
     * @param shape The shape of the soundwave that tones will be played with (sine / square)
     * @return the samples used by a WavFile of this song
     */
    public static ArrayList<Double> parseSequence(String[] sequence, long sampleRate,
                                                  String shape) {
        // A4 = 440hz
        // 00 = Rest (Silence)
        final String[] notes = {"A", "A#", "B", "C", "C#", "D",
                "D#", "E", "F", "F#", "G", "G#", "0"};
        final double[] frequencies = {440, 466.16, 493.88, 523.25, 554.37, 587.33,
                622.25, 659.15, 698.46, 739.99, 783.99, 830.61, 0};
        final double defaultNoteLength = 0.5;

        ArrayList<Double> samples = new ArrayList<>();
        for (String s : sequence) {
            int octave = Integer.parseInt(s.substring(s.length() - 1));
            String note = s.substring(0, s.length() - 1);
            double freq = frequencies[Arrays.asList(notes).indexOf(note)] * Math.pow(2, octave - 4);
            samples.addAll(generateTone(freq, sampleRate, defaultNoteLength, shape));
        }
        return samples;
    }

    /**
     * Sets the samples of a wave file and closes it, then printing a success message to the user
     * @param file the WavFile object to have samples saved to
     * @param samples the samples that will be saved to the WavFile object
     * @param message the message printed to the user after saving
     */
    public static void saveWavFile(WavFile file, ArrayList<Double> samples, String message) {
        file.setSamples(samples);
        file.close();
        System.out.println(message);
    }
}