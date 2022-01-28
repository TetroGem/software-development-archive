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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a collection of BibTeX references.
 * Objects from this class can be used to keep track of all references associated with a paper.
 * Once created, references to books and articles can be added to the object.
 * In addition, the class provides a mechanism for updating and removing
 * references that have already been added to the object.
 * @author singletonb
 * @version 1
 */
public class ReferenceHolder {

    /**
     * List to store the references
     */
    private final List<Reference> references;

    /**
     * Constructor for ReferenceHolder object
     */
    public ReferenceHolder() {
        references = new ArrayList<>();
    }

    /**
     * Add a book reference to the bibliography
     * @param book the book reference to be added
     */
    public void addReference(Book book) {
        references.add(book);
    }

    /**
     * Add an article reference to the bibliography
     * @param article the article reference to be added
     */
    public void addReference(Article article) {
        references.add(article);
    }

    /**
     * Removes the reference with the specified ID.
     * @param uniqueID the id for the reference to be updated
     * @return returns true if and only if the desired reference was removed
     */
    public boolean removeReference(String uniqueID) {
        boolean removed = false;
        for(int i = 0; i < references.size() && !removed; i++) {
            if(references.get(i).getMyUniqueID().equals(uniqueID)) {
                references.remove(i);
                removed = true;
            }
        }
        return removed;
    }

    /**
     * Asks the reference corresponding to the uniqueID to update itself.
     * If no reference with uniqueID is found, no update is performed and the method returns false.
     * @param uniqueID the id for the reference to be updated
     * @param out Output stream to prompt the user for input
     * @param in Input stream to read user input
     * @return returns true if and only if the desired reference was updated
     */
    public boolean updateReference(String uniqueID, PrintStream out, Scanner in) {
        boolean updated = false;
        for(int i = 0; i < references.size() && !updated; i++) {
            if(references.get(i).getMyUniqueID().equals(uniqueID)) {
                references.get(i).promptForUpdate(out, in);
                updated = true;
            }
        }
        return updated;
    }

    /**
     * Return a string containing all BibTeX entries in the reference list
     * @return a string containing all BibTeX entries
     */
    @Override
    public String toString() {
        StringBuilder referenceList = new StringBuilder();
        for(Reference reference : references) {
            referenceList.append(reference);
            if(references.indexOf(reference) != references.size() - 1) {
                referenceList.append("\n");
            }
        }
        return referenceList.toString();
    }

}