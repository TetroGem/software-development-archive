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
 * This class describes a Circle object that extends the abstract Shape object
 * @author singletonb
 * @version 1.0
 */
public class Circle extends Shape {
    private final double radius;
    private final double heightRadius;

    /**
     * Constructor for the Circle class
     * @param x x location of the center of the circle
     * @param y y location of the center of the circle
     * @param radius length of the radius of the circle
     * @param color the color of the circle
     */
    public Circle(double x, double y, double radius, Color color) {
        super(x - radius, y - radius, color);
        this.radius = radius;
        this.heightRadius = radius;
    }

    /**
     * Constructor for the Circle class
     * @param x The lower left corner x-value of the circle
     * @param y the lower left corner y-value of the circle
     * @param width the width of the circle
     * @param height the height of the circle
     * @param color the color of the circle
     */
    public Circle(double x, double y, double width, double height, Color color) {
        super(x, y, color);
        this.radius = width / 2;
        this.heightRadius = height / 2;
    }

    /**
     * A method that draws the circle using the WinPlotterFX
     * @param plotter an instance of a WinPlotterFX object
     */
    @Override
    public void draw(WinPlotterFX plotter) {
        setPenColor(plotter);
        final int degreesInCircle = 360;
        final int degreeStep = 1;
        for(int i = 0; i < degreesInCircle; i += degreeStep) {
            double angle = i * (Math.PI / ((double)degreesInCircle / 2));
            double nextAngle = (i + degreeStep) * (Math.PI / ((double)degreesInCircle / 2));
            line(plotter,
                    Math.cos(angle) * radius + x + radius,
                    Math.sin(angle) * heightRadius + y + heightRadius,
                    Math.cos(nextAngle) * radius + x + radius,
                    Math.sin(nextAngle) * heightRadius + y + heightRadius);
        }
    }
}