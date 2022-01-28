/*
 * Course: CS1021 - 021
 * Winter 2021
 * Lab 3 - Keeping our Sources Straight
 * Name: Benjamin Singleton
 * Created: 12/30/2021
 * Modified: 01/03/2022
 */
package singletonb;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Represents bibliographical information about a reference
 * @author singletonb
 * @version 1
 */
public abstract class Reference {
    /**
     * Keeps track of how many instances of this type have been created.
     */
    private static int instanceCount = 0;

    /**
     * The person who wrote the reference
     */
    protected String author;

    /**
     * The unique ID assigned to this instance of the reference
     */
    protected final String myUniqueID;

    /**
     * Year of publication
     */
    protected int publicationYear;

    /**
     * The title for the reference
     */
    protected String title;
    protected boolean updating;

    /**
     * Assigns a unique ID
     */
    public Reference() {
        myUniqueID = "REF" + instanceCount;
        instanceCount++;
    }

    /**
     * Prompts the user for initial attribute values of the reference object.
     * @param out Output stream to prompt the user for input. Typically this will be System.out
     * @param in Input stream to read user input.
     */
    public abstract void promptToInitialize(PrintStream out, Scanner in);

    /**
     * Prompts the user to update the attributes of the reference object.
     * @param out Output stream to prompt the user for input. Typically this will be System.out
     * @param in Input stream to read user input.
     */
    public void promptForUpdate(PrintStream out, Scanner in) {

    }

    /**
     * Get the author
     * @return author of this reference
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Get the unique ID associated with this reference
     * @return the unique ID
     */
    public String getMyUniqueID() {
        return myUniqueID;
    }

    /**
     * Get publication year
     * @return publication year of this reference
     */
    public int getPublicationYear() {
        return publicationYear;
    }

    /**
     * Get title
     * @return title of this reference
     */
    public String getTitle() {
        return title;
    }
    
    private boolean intParsable(String str) {
        boolean isInteger = true;
        
        try {
            Integer.parseInt(str);
        } catch(NumberFormatException e) {
            isInteger = false;
        }
        
        return isInteger;
    }

    protected int receiveInt(Scanner in, PrintStream out, String prompt) {
        String input;
        do {
            out.println(prompt);
            input = in.nextLine();
        } while(!intParsable(input));
        return Integer.parseInt(input);
    }
}