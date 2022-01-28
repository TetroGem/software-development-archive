/*
 * Course: CS1021 - 021
 * Winter 2021
 * Lab 2 - Interfaces
 * Name: Benjamin Singleton
 * Created: 12/07/2021
 * Modified: 12/08/2021
 */
package singletonb;

import java.text.DecimalFormat;

/**
 * An interface for all ingredient types
 * @author singletonb
 */
public interface Ingredient {
    /**
     * Formats cup amounts of ingredients to two decimal places,
     * hiding trailing zeros if they are present
     */
    DecimalFormat CUP_FORMAT = new DecimalFormat("0.##");

    /**
     * Gets the amount of calories the ingredient contains
     * @return the amount of calories the ingredient contains
     */
    double getCalories();

    /**
     * Gets the volume of the ingredient (in cups)
     * @return the volume of the ingredient (in cups)
     */
    double getCups();

    /**
     * Gets the name of the ingredient
     * @return the name of the ingredient
     */
    String getName();

    /**
     * Checks if the ingredient is dry
     * @return true if the ingredient is dry
     */
    boolean isDry();

    /**
     * Prints the recipe for this ingredient and any that it may consist of
     */
    void printRecipe();
}