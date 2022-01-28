/*
 * Course: CS1011 - 011
 * Fall 2021
 * Lab 5 - Library Classes
 * Name: Benjamin Singleton
 * Created: 10/05/2021
 */

package singletonb;

import java.util.Locale;
import java.util.Scanner;

/**
 * This program runs a game asking the user to pick between two machines,
 * one which increases the amount of money they will earn linearly,
 * and one exponentially. After they have chosen which one they want,
 * it will output how much money each generate after a random number of
 * weeks, and tell them if the machine they chose generated more money
 * than the other.
 */
public class GrowthRate {

    static final double VALUE_OF_PENNY = 0.01;

    public static void main(String[] args) {

        boolean programRunning = true;
        Scanner in = new Scanner(System.in);

        while(programRunning) {

            System.out.println("\nYou have managed to design two machines that will " +
                    "infinitely generate money,\nalthough because of your inability " +
                    "to properly construct anything, they are very fragile\nand will " +
                    "spontaneously combust at a random time between 1 and 40 weeks of use." +
                    "\nYou also only have enough savings to pay for the power bill to run " +
                    "one of the machines,\nand did not implement a button to turn them off " +
                    "once one has been activated.\n\nThe first machine you designed will " +
                    "generate a constant amount of money each week,\nbeing a random value " +
                    "between $0 and $5000.\nThe other will print a penny the first week," +
                    "\nAnd then print double the previous week's amount the next.");

            int machine = 0;

            do {

                System.out.println("\nWhich machine do you power on?");
                System.out.println("[A] The machine that prints out a constant amount");
                System.out.println("[B] The machine that prints out a twice the amount " +
                        "as the previous week");

                machine = switch (in.nextLine().toLowerCase(Locale.ROOT)) {

                    case "a" -> {
                        yield 1;
                    }
                    case "b" -> {
                        yield 2;
                    }
                    default -> {
                        yield 0;
                    }

                };

            } while (machine < 1 || machine > 2);

            System.out.println("You chose Machine " + (machine == 1 ? "A" : "B"));

            final int maximumNumberOfWeeks = 40;
            final int maximumAmountOfMoney = 5000;
            int weeksRan = (int) (Math.random() * maximumNumberOfWeeks) + 1;
            double constantMoney = Math.random() * maximumAmountOfMoney;

            for (int i = 1; i <= weeksRan; i++) {

                int penniesEarned = (int) Math.pow(2, i - 1);

                if ((penniesEarned * VALUE_OF_PENNY) <= (constantMoney * i)) {

                    System.out.printf("Week %02d,\t\tMachine A: $%.2f,\t\tMachine B: $%.2f\n",
                            i, (constantMoney * i), (penniesEarned * VALUE_OF_PENNY));

                }

            }

            int penniesEarned = (int) Math.pow(2, weeksRan - 1);
            double totalEarned = (machine == 1) ?
                    (constantMoney * weeksRan) :
                    (penniesEarned * VALUE_OF_PENNY);
            double totalMissed = (machine == 2) ?
                    (constantMoney * weeksRan) :
                    (penniesEarned * VALUE_OF_PENNY);

            System.out.printf("The machine blew up after %d" +
                    " weeks, netting you $%.2f", weeksRan, totalEarned);

            System.out.printf(", $%.2f %s than if you had chosen Machine %s",
                    Math.abs(totalEarned - totalMissed),
                    (totalEarned > totalMissed ? "more" : "less"), (machine == 1 ? "B" : "A"));

            String response = "";

            do {

                System.out.println("\nWould you like to play again? [Y/N]");
                response = in.nextLine().toLowerCase(Locale.ROOT);

            } while (!response.equals("y") && !response.equals("n"));

            if (response.equals("n")) {

                programRunning = false;

            }

        }

    }

}
