/*
 * Course: CS1021 - 021
 * Winter 2021
 * Lab 2 - Interfaces
 * Name: Benjamin Singleton
 * Created: 12/07/2021
 * Modified: 12/08/2021
 */
package singletonb;

/**
 * A basic ingredient with a default volume
 * @author singletonb
 */
public class SimpleIngredient implements Ingredient {
    private final double calories;
    private final double cups;
    private final boolean isDry;
    private final String name;

    /**
     * Constructs a SimpleIngredient
     * @param calories the number of calories in the specified volume of this ingredient
     * @param cups the volume of this ingredient
     * @param isDry true if this ingredient is dry
     * @param name the name of this ingredient
     */
    public SimpleIngredient(double calories, double cups, boolean isDry, String name) {
        this.calories = calories;
        this.cups = cups;
        this.isDry = isDry;
        this.name = name;
    }

    @Override
    public double getCalories() {
        return calories;
    }

    @Override
    public double getCups() {
        return cups;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isDry() {
        return isDry;
    }

    @Override
    public void printRecipe() {
        System.out.printf("""
                        ====================================================
                        %s
                        ====================================================
                        Cups: %s Cups
                        Energy: %d Calories%n%n""",
                getName(), CUP_FORMAT.format(getCups()), (int)getCalories());
    }
}