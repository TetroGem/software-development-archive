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
 * Represents bibliographical information about an article published in a journal
 * @author singletonb
 * @version 1
 */
public class Article extends Reference {
    /**
     * The page number that the article ends on
     */
    private int endingPage;

    /**
     * The page number that the article starts on
     */
    private int startingPage;

    /**
     * The name of the journal the article was published in
     */
    private String journal;

    /**
     * Prompts the user for initial attribute values of the article object.
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
        out.printf("Enter the%s title of the journal%n", updating);
        journal = in.nextLine();
        if(!setStartingPage(receiveInt(in, out,
                String.format("Enter the%s first page number of the article", updating)))) {
            System.out.println("Starting page was not set, must not be a negative integer" +
                    "or greater than the current ending page");
        }
        if(!setEndingPage(receiveInt(in, out,
                String.format("Enter the%s last page number of the article", updating)))) {
            System.out.println("Ending page was not set, must not be" +
                    "less than the current starting page");
        }
    }

    /**
     * Prompts the user to update the attributes of the article object.
     * @param out Output stream to prompt the user for input. Typically this will be System.out
     * @param in Input stream to read user input.
     */
    @Override
    public void promptForUpdate(PrintStream out, Scanner in) {
        updating = true;
        promptToInitialize(out, in);
    }

    /**
     * The page number that the article starts on
     * @return page number for last page
     */
    public int getEndingPage() {
        return endingPage;
    }

    /**
     * Gets first page of article
     * @return page number for first page
     */
    public int getStartingPage() {
        return startingPage;
    }

    /**
     * Gets name of journal
     * @return name of the journal article was published in
     */
    public String getJournal() {
        return journal;
    }

    /**
     * Returns a String of information in BibTeX format.
     * @return String of BibTeX information
     */
    @Override
    public String toString() {
        return String.format("""
                @ARTICLE { %s,
                author = "%s",
                title = "%s",
                journal = "%s",
                pages = "%s",
                year = "%d"
                }""", myUniqueID, author, title, journal,
                startingPage + "-" + endingPage, publicationYear);
    }

    /**
     * Sets the ending page of the article. If the desired ending
     * page is less than the starting page, no change is made.
     * @param endingPage Page number of the last page of the article
     * @return returns true if the page was changed.
     */
    private boolean setEndingPage(int endingPage) {
        boolean pageChanged = false;
        if(endingPage >= startingPage){
            this.endingPage = endingPage;
            pageChanged = true;
        }
        return pageChanged;
    }

    /**
     * Sets first page of the article. If the desired starting page is not
     * positive or is greater than the current ending page, no change is made.
     * @param startingPage Page number of the first page of the article.
     * @return returns true if the page was changed.
     */
    private boolean setStartingPage(int startingPage) {
        boolean pageChanged = false;
        if((startingPage <= endingPage || endingPage == 0) && startingPage > 0){
            this.startingPage = startingPage;
            pageChanged = true;
        }
        return pageChanged;
    }
}