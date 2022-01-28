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
 * This class describes a Triangle object that extends the abstract Shape object
 * @author singletonb
 * @version 1.0
 */
public class Triangle extends Shape {
    protected final double base;
    protected final double height;

    /**
     * Constructor for the Triangle object
     * @param x The lower left corner x-value of the Triangle
     * @param y The lower left corner y-value of the Triangle
     * @param base The length of the base of the Triangle
     * @param height The height of the Triangle
     * @param color The color of the Triangle
     */
    public Triangle(double x, double y, double base, double height, Color color) {
        super(x, y, color);
        this.base = base;
        this.height = height;
    }

    /**
     * A method that draws the Triangle using the WinPlotterFX
     * @param plotter an instance of a WinPlotterFX object
     */
    @Override
    public void draw(WinPlotterFX plotter) {
        setPenColor(plotter);
        line(plotter, x, y, x + base, y);
        line(plotter, x + base, y, x + base / 2, y + height);
        line(plotter, x + base / 2, y + height, x, y);
    }
}