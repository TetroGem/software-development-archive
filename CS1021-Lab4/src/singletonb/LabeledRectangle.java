/*
 * Course: CS1021 - 021
 * Winter 2021
 * Lab 4 - Inheritance with Shapes
 * Name: Benjamin Singleton
 * Created: 01/05/2022
 * Modified: 01/10/2022
 */
package singletonb;

import edu.msoe.winplotterfx.WinPlotterFX;
import javafx.scene.paint.Color;

/**
 * This class describes a LabeledRectangle object with a text label in the lower left corner.
 * The class extends the Rectangle object
 * @author singletonb
 * @version 1.0
 */
public class LabeledRectangle extends Rectangle {
    private final String name;

    /**
     * Constructor for the Labeled Rectangle object
     * @param x The lower left corner x-value of the rectangle
     * @param y the lower left corner y-value of the rectangle
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     * @param color the color of the rectangle
     * @param name the text assigned to the label
     */
    public LabeledRectangle(double x, double y, double width,
                            double height, Color color, String name) {
        super(x, y, width, height, color);
        this.name = name;
    }

    /**
     * A method that draws the Labeled Rectangle using the WinPlotterFX
     * @param plotter an instance of a WinPlotterFX object
     */
    @Override
    public void draw(WinPlotterFX plotter) {
        super.draw(plotter);
        plotter.printAt(x, y, name);
    }
}
