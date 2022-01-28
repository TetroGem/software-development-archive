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
 * An Ingredient with a specific measured volume
 * @author singletonb
 */
public class Measure implements Ingredient {
    private final Ingredient baseIngredient;
    private final int numerator;
    private final int denominator;

    /**
     * Master constructor
     * Constructs a Measure, a version of an Ingredient
     * with the volume of numerator / denominator cups
     * @param numerator the numerator of the fraction of the volume (in cups)
     * @param denominator the denominator of the fraction of the volume (in cups)
     * @param ingredient the Ingredient to be measured
     */
    public Measure(int numerator, int denominator, Ingredient ingredient) {
        this.baseIngredient = ingredient;
        this.numerator = numerator;
        this.denominator = denominator;
    }

    /**
     * Constructs a Measure with numerator and denominator, while defaulting denominator to 1
     * @param numerator the volume of this ingredient (in cups)
     * @param ingredient the Ingredient to be measured
     */
    public Measure(int numerator, Ingredient ingredient) {
        this(numerator, 1, ingredient);
    }

    /**
     * Gets the amount of calories in the specified measure of Ingredient
     * @return the amount of calories in the measured Ingredient
     */
    @Override
    public double getCalories() {
        return baseIngredient.getCalories() / baseIngredient.getCups() * this.getCups();
    }

    @Override
    public double getCups() {
        return (double)numerator / denominator;
    }

    /**
     * Gets the name of the Measure, being the name of the Ingredient measured
     * with the volume of the measure appended to the start
     * @return the name of the Measure
     */
    @Override
    public String getName() {
        return formatQuantity() + " " + baseIngredient.getName();
    }

    @Override
    public boolean isDry() {
        return baseIngredient.isDry();
    }

    @Override
    public void printRecipe() {
        System.out.printf("""
                        ====================================================
                        %s
                        ====================================================
                        Measured ingredient: %s
                        Cups: %s (%s Cups)
                        Energy: %d Calories%n%n""",
                getName(), baseIngredient.getName(), formatQuantity(),
                CUP_FORMAT.format(getCups()), (int)getCalories());
        baseIngredient.printRecipe();
    }

    private String formatQuantity() {
        return numerator == denominator ? "1 Cup"
                : (denominator == 1 ? numerator : numerator + "/" + denominator) + " Cups";
    }
}