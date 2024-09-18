/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * Die class
 * Name: Peli Orugbani
 * Last Updated: 9/08/2024
 */
package orugbanip;
/**
 * Creates the custom DieNotRolledException Class that extends from the Exception class;
 */
public class DieNotRolledException extends RuntimeException {
    /**
     * Default Constructor
     */
    public DieNotRolledException(){

    }
    /**
     * Parameter Constructor that takes in a message
     * @param message used for the getMessage() method in Exception class
     */
    public DieNotRolledException(String message){
        super(message);
    }
}