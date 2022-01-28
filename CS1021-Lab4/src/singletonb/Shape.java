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
 * An abstract class that describes a generic shape
 * which includes it's color and location on a grid.
 * @author singletonb
 * @version 1.0
 */
public abstract class Shape {
    private Color color;
    protected final double x;
    protected final double y;

    /**
     * Constructor for the Shape class
     * @param x starting x-coordinate for the Shape
     * @param y starting y-coordinate for the Shape
     * @param color the color of the Shape
     */
    public Shape(double x, double y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    /**
     * An abstract method declaration for drawing the subclasses in a WinPlotterFX object
     * @param plotter WinPlotterFX object that we are drawing the Shape in
     */
    public abstract void draw(WinPlotterFX plotter);

    /**
     * This method sets the Pen color for the WinPlotterFX object to match the color of the Shape
     * @param plotter WinPlotter object that we are drawing the Shape in
     */
    public void setPenColor(WinPlotterFX plotter) {
        plotter.setPenColor(color.getRed(), color.getGreen(), color.getBlue());
    }

    /**
     * Setter method for the color of a Shape
     * @param color the RGB Color of the Shape
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Draws a line from one set of coordinates to another
     * @param plotter WinPlotter object that we are drawing the line in
     * @param x1 starting x-coordinate for the line
     * @param y1 starting y-coordinate for the line
     * @param x2 ending x-coordinate for the line
     * @param y2 ending y-coordinate for the line
     */
    public void line(WinPlotterFX plotter, double x1, double y1, double x2, double y2) {
        plotter.moveTo(x1, y1);
        plotter.drawTo(x2, y2);
    }
}