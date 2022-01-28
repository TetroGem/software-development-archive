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
 * Represents bibliographical information about a book
 * @author singletonb
 * @version 1
 */
public class Book extends Reference {

    /**
     * Name of the publisher of the book
     */
    private String publisher;

    /**
     * Prompts the user for initial attribute values of the book object.
     * @param out Output stream to prompt the user for input. Typically this will be System.out
     * @param in Input stream to read user input.
     */
    @Override
    public void promptToInitialize(PrintStream out, Scanner in) {
        final String updating = this.updating ? " updated" : "";
        out.printf("Enter the%s author of the reference%n", updating);
        author = in.nextLine();
        out.printf("Enter the%s title of the reference%n", updating);
        title = in.nextLine();
        publicationYear = receiveInt(in, out,
                String.format("Enter the%s copyright year of the reference", updating));
        out.printf("Enter the%s publisher of the book%n", updating);
        publisher = in.nextLine();
    }

    /**
     * Prompts the user to update the attributes of the book object.
     * @param out Output stream to prompt the user for input. Typically this will be System.out
     * @param in Input stream to read user input.
     */
    @Override
    public void promptForUpdate(PrintStream out, Scanner in) {
        updating = true;
        promptToInitialize(out, in);
    }

    /**
     * Gets the name of the publisher of this book
     * @return Name of the publisher
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Returns a String of information in BibTeX format.
     * @return String of BibTeX information
     */
    @Override
    public String toString() {
        return String.format("""
                @BOOK { %s,
                author = "%s",
                title = "%s",
                publisher = "%s",
                year = "%d"
                }""", myUniqueID, author, title, publisher, publicationYear);
    }
}
