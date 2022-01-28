/*
 * Course: CS1021 - 021
 * Winter 2021
 * Lab 3 - Keeping our Sources Straight
 * Name: Benjamin Singleton
 * Created: 12/30/2021
 * Modified: 01/03/2022
 */
package singletonb;

import java.util.Scanner;

/**
 * Driver for Lab 3 objects, prompts the user to create,
 * update, export, or remove BibTeX references.
 * @author singletonb
 */
public class Lab3 {
    private static final Scanner IN = new Scanner(System.in);
    private static boolean programRunning = true;
    private static final ReferenceHolder REFERENCE_HOLDER = new ReferenceHolder();

    public static void main(String[]args) {
        while(programRunning) {
            System.out.println("""
                    Enter 0 to exit the program.
                    Enter 1 to enter a new book into the reference set.
                    Enter 2 to enter a new article into the reference set.
                    Enter 3 to update a reference.
                    Enter 4 to printout the entries in Bibtex format.
                    Enter 5 to remove a reference.""");

            String input = IN.nextLine();
            switch(input) {
                case "0" -> quitProgram();
                case "1" -> addBook();
                case "2" -> addArticle();
                case "3" -> updateReference();
                case "4" -> printEntries();
                case "5" -> removeReference();
            }
        }

        System.out.println("Goodbye");
    }

    private static void quitProgram() {
        programRunning = false;
    }

    private static void addBook() {
        final Book book = new Book();
        book.promptToInitialize(System.out, IN);
        REFERENCE_HOLDER.addReference(book);
    }

    private static void addArticle() {
        final Article article = new Article();
        article.promptToInitialize(System.out, IN);
        REFERENCE_HOLDER.addReference(article);
    }

    private static void updateReference() {
        System.out.println("Enter the ID of the reference you want to update");
        String updateID = IN.nextLine();
        if(!REFERENCE_HOLDER.updateReference(updateID, System.out, IN)) {
            System.out.printf("Could not find reference with ID %s", updateID);
        }

    }

    private static void printEntries() {
        System.out.println(REFERENCE_HOLDER.toString());
    }

    private static void removeReference() {
        System.out.println("Enter the ID of the reference you want to remove");
        String removeID = IN.nextLine();
        if(REFERENCE_HOLDER.removeReference(removeID)) {
            System.out.printf("Removed reference with ID %s", removeID);
        } else {
            System.out.printf("Could not find reference with ID %s", removeID);
        }
    }
}
