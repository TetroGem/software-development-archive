/*
 * Course: CS-1011-011
 * Fall 2021
 * Lab 6 - Simple Class
 * Name: Benjamin Singleton
 * Created: 10/12/21
 */
package singletonb;

/**
 * Stores information about the amount of square feet,
 * full bathrooms, half bathrooms, bedrooms, windows,
 * and garages a house has and either returns information
 * about them, updates their values, or calculates the
 * cost a hypothetical building with those specifications
 * would cost.
 *
 * @author Benjamin Singleton
 * @version 2021.10.12
 */
public class BuildingCostEstimator {

    private int squareFeet;
    private int numFullBaths;
    private int numHalfBaths;
    private int numBeds;
    private int numWindows;
    private double numGarages;

    public int getSquareFeet() {
        return squareFeet;
    }

    public int getNumFullBaths() {
        return numFullBaths;
    }

    public int getNumHalfBaths() {
        return numHalfBaths;
    }

    public int getNumBedrooms() {
        return numBeds;
    }

    public int getNumWindows() {
        return numWindows;
    }

    public double getNumGarages() {
        return numGarages;
    }

    public void setSquareFeet(int squareFeet) {
        this.squareFeet = squareFeet;
    }

    public void setNumFullBaths(int numFullBaths) {
        this.numFullBaths = numFullBaths;
    }

    public void setNumHalfBaths(int numHalfBaths) {
        this.numHalfBaths = numHalfBaths;
    }

    public void setNumBedrooms(int numBeds) {
        this.numBeds = numBeds;
    }

    public void setNumWindows(int numWindows) {
        this.numWindows = numWindows;
    }

    public void setNumGarages(double numGarages) {
        this.numGarages = numGarages;
    }

    /**
     * Calculate how much a house with the set number of each
     * element would cost to build
     * @return the cost to build the house specified
     */
    public double costToBuild(){

        final int costPerSquareFoot = 135;
        final int costPerFullBath = 20000;
        final int costPerHalfBath = 7000;
        final int costPerBedroom = 3000;
        final int costPerWindow = 1000;
        final int costPerGarage = 8000;

        double cost = costPerSquareFoot * squareFeet;
        cost += costPerFullBath * numFullBaths;
        cost += costPerHalfBath * numHalfBaths;
        cost += costPerBedroom * numBeds;
        cost += costPerWindow * numWindows;
        cost += costPerGarage * numGarages;

        return cost;

    }

}
