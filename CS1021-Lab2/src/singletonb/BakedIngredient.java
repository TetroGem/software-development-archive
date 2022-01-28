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
 * A baked version of another Ingredient
 * @author singletonb
 */
public class BakedIngredient implements Ingredient {
    private final Ingredient baseIngredient;
    private final double expansionFactor;

    /**
     * Constructs a BakedIngredient, a baked version of a specified Ingredient
     * @param ingredient the ingredient to be baked
     * @param expansionFactor the multiplier by which the volume of the ingredient
     *                        will expand after being baked
     */
    public BakedIngredient(Ingredient ingredient, double expansionFactor) {
        this.baseIngredient = ingredient;
        this.expansionFactor = expansionFactor;
    }

    @Override
    public double getCalories() {
        return baseIngredient.getCalories();
    }

    /**
     * Gets the volume of this ingredient after being baked by multiplying
     * the base ingredient's volume by its expansionFactor
     * @return the volume of this ingredient (in cups) after being baked
     */
    @Override
    public double getCups() {
        return baseIngredient.getCups() * expansionFactor;
    }

    @Override
    public String getName() {
        return "Baked " + baseIngredient.getName();
    }

    @Override
    public boolean isDry() {
        return true;
    }

    @Override
    public void printRecipe() {
        System.out.printf("""
                        ====================================================
                        %s
                        ====================================================
                        Ingredient to be baked: %s
                        Cups: %s Cups
                        Energy: %d Calories%n%n""",
                getName(), baseIngredient.getName(),
                CUP_FORMAT.format(getCups()), (int)getCalories());
        baseIngredient.printRecipe();
    }
}
