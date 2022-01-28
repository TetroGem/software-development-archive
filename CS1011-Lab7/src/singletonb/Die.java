/*
 * Course: CS1011 - 011
 * Fall 2021
 * Lab 7 - Battle Simulator 3000
 * Name: Benjamin Singleton
 * Created: 10/18/2021
 * Modified: 10/25/2021
 */
package singletonb;

import java.util.Random;

/**
 * A Die that can be rolled to generate a random value between 1 and the amount of sides
 * on the Die, and is able to output its current value it is rolled on.
 *
 * @author Benjamin Singleton
 * @version 2021.10.25
 */
public class Die {

    private final int numSides;
    private int currentValue;
    private final Random generator;

    /**
     * Constructs a new Die that can be rolled and stores the value it lands on
     * @param numSides the number of sides on the die
     */
    public Die(int numSides) {
        this.numSides = numSides;
        generator = new Random();
        roll();
    }

    public int getCurrentValue() {
        return currentValue;
    }

    /**
     * Rolls the Die, changing its current value to a random number
     * between 1 and the number of sides it has
     */
    public void roll() {
        currentValue = generator.nextInt(numSides) + 1;
    }

}