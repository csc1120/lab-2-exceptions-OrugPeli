/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * Die class
 * Name: Peli Orugbani
 * Last Updated: 9/08/2024
 */
package orugbanip;

import java.util.Random;
/**
 * Die class for rolling random int values
 */
public class Die {
    private int currentValue;
    private int numSides;
    private final Random random;
    /**
     * Die constructor
     * @param numSides number of sides on the die
     */
    public Die(int numSides) {
        this.numSides = numSides;
        random = new Random();
    }
    /**
     * This method is for getting the currentValue for a die object and resets current Value to 0
     * throws the DieNotRolledException if currentValue outside the expected range
     * @return value of Die object
     */
    public int getCurrentValue() {
        int currValue = 0;
        try {
            if (currentValue > numSides || currentValue < 1) {
                throw new DieNotRolledException("Die must be rolled again");
            }
            currValue = currentValue;
            currentValue = 0;
        } catch (DieNotRolledException e) {
            System.err.println("DieNotRolledException: In getCurrentValue() method");
            System.err.println(e.getMessage());
        }
        return currValue;
    }
    /**
     * This method uses the Random class to generate a random integer value for the die
     */
    public void roll(){
        currentValue = random.nextInt(numSides) + 1;
    }

    public int getNumSides() {
        return numSides;
    }
}