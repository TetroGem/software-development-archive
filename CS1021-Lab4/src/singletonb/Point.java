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
import javafx.scene.paint.Color;

/**
 * This class describes a Point object that extends the abstract Shape object
 * NOTE: Implementation of this class is OPTIONAL
 * @author singletonb
 * @version 1.0
 */
public class Point extends Shape {

    /**
     * Constructor for the Point object
     * @param x x-coordinate for the Point
     * @param y y-coordinate for the Point
     * @param color the color of the Point
     */
    public Point(double x, double y, Color color) {
        super(x, y, color);
    }

    /**
     * A method that draws the Point using the WinPlotterFX
     * @param plotter an instance of a WinPlotterFX object
     */
    @Override
    public void draw(WinPlotterFX plotter) {
        setPenColor(plotter);
        plotter.drawPoint(x, y);
    }
}
