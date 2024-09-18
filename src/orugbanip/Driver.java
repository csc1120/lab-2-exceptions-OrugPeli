/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * Die class
 * Name: Peli Orugbani
 * Last Updated: 9/08/2024
 */
package orugbanip;

import java.util.Arrays;
import java.util.Scanner;
/**
 *This class is going to create multiple dice objects and roll them a user inputted number of
 *times.Store the frequency, and a sum of each roll will be put onto a formated text displaying
 *the deviation of sum from each other.
 */
public class Driver {
    private static final int MIN_DICE = 2;
    private static final int MAX_DICE = 10;
    private static final int MIN_SIDES = 2;
    private static final int MAX_SIDES = 100;

    public static void main(String[] args) {
        int[] inputs = getInput();
        assert inputs != null;
        Die[] diceArray = createDice(inputs[0], inputs[1]);
        int[] diceRolls = rollDice(diceArray, inputs[2]);
        int max = findMax(diceRolls);
        report(inputs, diceRolls, max);


    }

    /**
     * This Method is asks the user to input the three numbers used to get how many dice,
     * how many sides each dice has, and how many rolls of those dice.An exception will be thrown
     * if the following user submitted information creates a die that is not valid
     * or if the information is not properly submitted
     *
     * @return int[]: the inputs that the user sent in
     */
    public static int[] getInput() {
        Scanner in = new Scanner(System.in);
        int[] inputs;
        do {
            System.out.println("Please enter the number of dice to roll," +
                    " how many sides the dice have," +
                    " and how many rolls to complete," +
                    " complete, separating the values by a space " +
                    "Example: '2 6 1000'");
            String input = in.nextLine();
            String[] split = input.split(" ");
            inputs = validate(split);
            if (inputs != null) {
                System.out.println("Valid input received: " + Arrays.toString(inputs));
                return new int[] {inputs[0], inputs[1], inputs[2]};
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        } while (in.hasNextInt());
        return null;
    }

    /**
     * This helper method helps check if the information the user submitted
     * are valid numbers a nd falls within the valid ranges for a die object
     * An exception is thrown if there invalid inputs
     *
     * @param str : The inputs of the user in a String array
     * @return int[] returns a valid int array
     */
    public static int[] validate(String[] str) {
        int[] inputs;
        try {
            if (str.length != 3) {
                System.err.println("Invalid Input: Expected 3 inputs but received " + str.length);
                return null;
            }
            int firstValue = Integer.parseInt(str[0]);
            int secondValue = Integer.parseInt(str[1]);
            int thirdValue = Integer.parseInt(str[2]);
            if (firstValue < Driver.MIN_DICE || firstValue > Driver.MAX_DICE) {
                System.err.println("Invalid Input: First value must be greater than or equal" +
                        " to 2 and less than or equal to 10");
                return null;
            }
            if (secondValue > MAX_SIDES || secondValue < MIN_SIDES) {
                System.err.println("Invalid input: Illegal number of sides: " + secondValue);
                return null;
            }
            inputs = new int[] {firstValue, secondValue, thirdValue};
            return inputs;
        } catch (NumberFormatException e) {
            System.err.println("Invalid input. Please enter real whole numbers.");
        }
        return null;
    }

    /**
     * This method creates an array of Dice
     *
     * @param numDice  : number of dice that are to be created
     * @param numSides : the number of sides each die will have
     * @return Die[]: an array of dice
     */
    public static Die[] createDice(int numDice, int numSides) {

        Die[] dice = new Die[numDice];
        for (int i = 0; i < numDice; i++) {
            dice[i] = new Die(numSides);
        }
        return dice;
    }

    /**
     * This method rolls the dice array and collects the sum of and frequency of each roll
     *
     * @param die      : array of dice that are to be rolled
     * @param numRolls : The number of rolls that are to be completed
     * @return int[] returns an int array containing the sum of rolls
     */
    public static int[] rollDice(Die[] die, int numRolls) {
        int[] frequency = new int[die[0].getNumSides() * die.length];
        int sum = 0;
        for (int i = 0; i < numRolls; i++) {

            for (Die value : die) {
                value.roll();
                sum += value.getCurrentValue();
            }
            frequency[sum-1]++;
            sum = 0;
        }
        return frequency;
    }

    /**
     * This method uses linear search to find the highest sum of the rolls
     *
     * @param rolls : array containing the sum of the rolls
     * @return int: the highest sum in the roll array
     */
    public static int findMax(int[] rolls) {
        int max = rolls[0];
        for (int roll : rolls) {
            if (roll > max) {
                max = roll;
            }
        }
        return max;
    }

    /**
     * This method formats all the data that has been collected into a nicely formated table
     *
     * @param die : the number of dice that were created
     * @param rolls   : the sum of the rolls that collected
     * @param max     : the highest sum of the rolls
     */
    public static void report(int[] die, int[] rolls, int max) {
        final int devi = 10;
        int deviations = max / devi;
        if (deviations == 0) {
            deviations = 1;
        }
        for (int i = die[0]; i < die[0]*die[1]; i++) {

            System.out.printf("%2d:%-8d", i, rolls[i-die[0]]);
            System.out.print("*".repeat(Math.max(0, rolls[i - die[0]] / deviations)));
            System.out.println();
        }
    }
}