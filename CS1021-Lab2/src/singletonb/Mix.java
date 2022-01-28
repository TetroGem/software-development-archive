/*
 * Course: CS1021 - 021
 * Winter 2021
 * Lab 2 - Interfaces
 * Name: Benjamin Singleton
 * Created: 12/07/2021
 * Modified: 12/08/2021
 */
package singletonb;

import java.util.ArrayList;
import java.util.List;

/**
 * A mix of multiple Ingredients
 * @author singletonb
 */
public class Mix implements Ingredient {
    private final List<Ingredient> ingredients;
    private final String name;

    /**
     * Constructs the Mix, initializing its name and
     * an ArrayList to hold each Ingredient it consists of
     * @param name the name of this Mix
     */
    public Mix(String name) {
        this.ingredients = new ArrayList<>();
        this.name = name;
    }

    /**
     * Adds an Ingredient to the Mix
     * @param ingredient the Ingredient to be added
     */
    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    /**
     * Checks if this Mix contains a dry Ingredient
     * @return true if the Mix contains a dry Ingredient
     */
    public boolean hasDryIngredient() {
        boolean foundDryIngredient = false;
        for(int i = 0; i < ingredients.size() && !foundDryIngredient; i++) {
            foundDryIngredient = ingredients.get(i).isDry();
        }
        return foundDryIngredient;
    }

    /**
     * Checks if this Mix contains a wet Ingredient
     * @return true if the Mix contains a wet Ingredient
     */
    public boolean hasWetIngredient() {
        boolean foundWetIngredient = false;
        for(int i = 0; i < ingredients.size() && !foundWetIngredient; i++) {
            foundWetIngredient = !ingredients.get(i).isDry();
        }
        return foundWetIngredient;
    }

    /**
     * Sums up the amount of calories each Ingredient the Mix contains
     * to get the total calories in this Mix
     * @return the number of calories in this Mix
     */
    @Override
    public double getCalories() {
        double calories = 0;
        for(Ingredient ingredient : ingredients) {
            calories += ingredient.getCalories();
        }
        return calories;
    }

    /**
     * Sums up the volumes (in cups) of each Ingredient the Mix contains
     * to get the total volume of this Mix
     * @return the volume (in cups) of this Mix
     */
    @Override
    public double getCups() {
        double cups = 0;
        for(Ingredient ingredient : ingredients) {
            cups += ingredient.getCups();
        }
        return cups;
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * Checks if this Mix is dry, which will always be false if it contains a wet Ingredient
     * @return true if this Mix is dry
     */
    @Override
    public boolean isDry() {
        return !hasWetIngredient();
    }

    @Override
    public void printRecipe() {
        System.out.printf("""
                        ====================================================
                        %s
                        ====================================================%n""",
                getName());

        if(this.hasDryIngredient()) {
            System.out.println("Dry Ingredients:");
            for(Ingredient ingredient : ingredients) {
                if(ingredient.isDry()) {
                    System.out.println("  " + ingredient.getName());
                }
            }
            System.out.print("\n");
        }

        if(this.hasWetIngredient()) {
            System.out.println("Wet Ingredients:");
            for(Ingredient ingredient : ingredients) {
                if(!ingredient.isDry()) {
                    System.out.println("  " + ingredient.getName());
                }
            }
            System.out.print("\n");
        }

        System.out.printf("""
                Cups: %s Cups
                Energy: %d Calories%n%n""", CUP_FORMAT.format(getCups()), (int)getCalories());

        for(Ingredient ingredient : ingredients) {
            ingredient.printRecipe();
        }
    }
}
