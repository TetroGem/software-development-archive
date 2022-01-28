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
 * This class describes a LabeledTriangle object with a text label in the lower left corner.
 * The class extends the Triangle object
 * @author singletonb
 * @version 1.0
 */
public class LabeledTriangle extends Triangle {
    private final String name;

    /**
     * Constructor for the Labeled Triangle object
     * @param x The lower left corner x-value of the Triangle
     * @param y The lower left corner y-value of the Triangle
     * @param base The length of the base of the Triangle
     * @param height The height of the Triangle
     * @param color The color of the Triangle
     * @param name the text assigned to the label
     */
    public LabeledTriangle(double x, double y, double base,
                           double height, Color color, String name) {
        super(x, y, base, height, color);
        this.name = name;
    }

    /**
     * A method that draws the Labeled Triangle using the WinPlotterFX
     * @param plotter an instance of a WinPlotterFX object
     */
    @Override
    public void draw(WinPlotterFX plotter) {
        super.draw(plotter);
        plotter.printAt(x, y, name);
    }
}
