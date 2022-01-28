/*
 * Course: CS1021 - 021
 * Winter 2021
 * Lab 4 - Inheritance with Shapes
 * Name: Benjamin Singleton
 * Created: 01/05/2022
 * Modified: 01/26/2022
 */
package singletonb;

import edu.msoe.winplotterfx.WinPlotterFX;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Scanner;

/**
 * This class draws a face out of a bunch of rectangles.
 * @author taylor
 * @version 20220126
 */
public class FaceMaker extends Application {
    /**
     * The dimensions of the window created
     */
    public static final int WINDOW_SIZE = 600;

    /**
     * The number of pixels between each gridline
     */
    public static final int GRID_INCREMENT = WINDOW_SIZE / 10;

    /**
     * The size of the head to be drawn
     */
    public static final int HEAD_SIZE = 400;

    private static final Scanner IN = new Scanner(System.in);
    private static int headType;
    private static int leftEyeType;
    private static int rightEyeType;
    private static int noseType;
    private static int mouthType;

    /**
     * Launches the JavaFX application
     * @param args ignored
     */
    public static void main(String[] args) {
        promptForShapeTypes();
        launch(args);
    }

    /**
     * Use the Shape class and its descendants to draw a face.
     * @param stage Default stage given to a JavaFX program. Unused.
     */
    @Override
    public void start(Stage stage) {
        WinPlotterFX plotter = new WinPlotterFX();
        plotter.setWindowTitle("Face Maker");
        plotter.setWindowSize(WINDOW_SIZE, WINDOW_SIZE);
        plotter.setBackgroundColor(Color.BLACK.getRed(), Color.BLACK.getGreen(),
                Color.BLACK.getBlue());
        final boolean showGrid = true;
        plotter.setGrid(showGrid, GRID_INCREMENT, GRID_INCREMENT, Color.GRAY);
        Shape head = createHead();
        Shape leftEye = createLeftEye();
        Shape rightEye = createRightEye();
        Shape nose = createNose();
        Shape mouth = createMouth();

        head.draw(plotter);
        leftEye.draw(plotter);
        rightEye.draw(plotter);
        nose.draw(plotter);
        mouth.draw(plotter);

        plotter.showPlotter();
    }

    /**
     * Creates and returns a shape representing the head
     * @return shape representing the head
     */
    private Shape createHead() {
        final int xLeft = (WINDOW_SIZE - HEAD_SIZE) / 2;
        final int yBottom = (WINDOW_SIZE - HEAD_SIZE) / 2;
        return createShape(headType, xLeft, yBottom, HEAD_SIZE, HEAD_SIZE, Color.WHITE, "head");
    }

    /**
     * Creates and returns a shape representing the right eye
     * @return shape representing the right eye
     */
    private Shape createRightEye() {
        final double scaleFactor = 0.15;
        final double size = scaleFactor * HEAD_SIZE;
        final double yBottom = (double)WINDOW_SIZE / 2 + ((size * 3) / 2);
        final double xLeft = (double)WINDOW_SIZE / 2 + size;
        return createShape(rightEyeType, xLeft, yBottom,
                size, size, Color.WHITE, "right eye");
    }

    /**
     * Creates and returns a shape representing the left eye
     * @return shape representing the left eye
     */
    private Shape createLeftEye() {
        final double scaleFactor = 0.15;
        final double size = scaleFactor * HEAD_SIZE;
        final double yBottom = (double)WINDOW_SIZE / 2 + size * 3 / 2;
        final double xLeft = (double)WINDOW_SIZE / 2 - size * 2;
        return createShape(leftEyeType, xLeft, yBottom,
                size, size, Color.WHITE, "left eye");
    }

    /**
     * Creates and returns a shape representing the nose
     * @return shape representing the nose
     */
    private Shape createNose() {
        final double scaleFactor = 0.2;
        final double size = scaleFactor * HEAD_SIZE;
        final double xLeft = (double)WINDOW_SIZE / 2 - size / 2;
        final double yBottom = (double)WINDOW_SIZE / 2;
        return createShape(noseType, xLeft, yBottom,
                size, size, Color.WHITE, "nose");
    }

    /**
     * Creates and returns a shape representing the mouth
     * @return shape representing the mouth
     */
    private Shape createMouth() {
        final double scaleFactor = 0.3;
        final double size = scaleFactor * HEAD_SIZE;
        final double xLeft = (int)(WINDOW_SIZE / 2) - size / 2;
        final double yBottom = (int)(WINDOW_SIZE / 2) - size * 3 / 2;
        return createShape(mouthType, xLeft, yBottom,
                size, size, Color.WHITE, "mouth");
    }

    private Shape createShape(int type, double x, double y,
                              double width, double height, Color color, String name) {
        final int circle = 2;
        final int triangle = 3;
        final int labeledRect = 4;
        final int labeledTriangle = 5;
        final int point = 6;
        final int random = 7;
        if(type == random) {
            type = (int)(Math.random() * (random - 1) + 1);
        }

        Shape shape;
        switch(type) {
            default -> {
                shape = new Rectangle(x, y, width, height, color);
            }
            case circle -> {
                shape = new Circle(x, y, width, height, color);
            }
            case triangle -> {
                shape = new Triangle(x, y, width, height, color);
            }
            case labeledRect -> {
                shape = new LabeledRectangle(x, y, width, height, color, name);
            }
            case labeledTriangle -> {
                shape = new LabeledTriangle(x, y, width, height, color, name);
            }
            case point -> {
                shape = new Point(x, y, color);
            }
        }

        return shape;
    }

    private static void promptForShapeTypes() {
        int sameTypes = -1;
        do {
            System.out.println("Would you like to use the same shape " +
                    "for each feature or different ones? [1] Same, [2] Different");
            String input = IN.nextLine();
            switch(input) {
                case "1" -> {
                    sameTypes = 1;
                }
                case "2" -> {
                    sameTypes = 0;
                }
            }
        } while(sameTypes == -1);

        final String shapeOptions = "[1] Rectangle, [2] Circle, [3] Triangle, " +
                "[4] Labeled Rectangle, [5] Labeled Triangle, [6] Point, [7] Random";
        if(sameTypes == 1) {
            System.out.println("Which shape? " + shapeOptions);
            final int type = inputType();
            headType = type;
            leftEyeType = type;
            rightEyeType = type;
            noseType = type;
            mouthType = type;
        } else {
            System.out.println("Which head shape? " + shapeOptions);
            headType = inputType();
            System.out.println("Which left eye shape? " + shapeOptions);
            leftEyeType = inputType();
            System.out.println("Which right eye shape? " + shapeOptions);
            rightEyeType = inputType();
            System.out.println("Which nose shape? " + shapeOptions);
            noseType = inputType();
            System.out.println("Which mouth shape? " + shapeOptions);
            mouthType = inputType();
        }
    }

    private static int inputType(){
        final int random = 7;
        int type = random;
        if(IN.hasNextInt()) {
            int input = IN.nextInt();
            if(input > 0 && input < random + 1) {
                type = input;
            }
        }
        IN.nextLine();
        return type;
    }
}
