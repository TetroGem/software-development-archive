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
 * This class describes a Rectangle object that extends the abstract Shape object
 * @author singletonb
 * @version 1.0
 */
public class Rectangle extends Shape {
    protected final double height;
    protected final double width;

    /**
     * Constructor for the Rectangle object
     * @param x The lower left corner x-value of the rectangle
     * @param y the lower left corner y-value of the rectangle
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     * @param color the color of the rectangle
     */
    public Rectangle(double x, double y, double width, double height, Color color) {
        super(x, y, color);
        this.width = width;
        this.height = height;
    }

    /**
     * A method that draws the Rectangle using the WinPlotterFX
     * @param plotter an instance of a WinPlotterFX object
     */
    @Override
    public void draw(WinPlotterFX plotter) {
        setPenColor(plotter);
        line(plotter, x, y, x + width, y);
        line(plotter, x + width, y, x + width, y + height);
        line(plotter, x + width, y + height, x, y + height);
        line(plotter, x, y + height, x, y);
    }
}