/*
 * Course: CS-1011
 * Fall 2021
 * Lab 2 - Small Programs
 * Name: Benjamin Singleton
 * Created: 09/14/21
 */
package singletonb;

/**
 * A collection of CodingBat exercises for students to get practice with
 */
public class Lab2 {
    public static int NUM_PENNIES = 100;
    public static void main(String[] args) {
        int[] negateTests = {2, 0,-3, 7, -299182, 47, 9, 13, -13};
        double[] penniesTests = {2.18, 58.72, 0.772, 13.1, 0.0};
        String[] lastHalfTests = {"this", "answer", "apples", "sing", "question", "we", "food",
        "applesauce", "Virginia", "This is fun!", "foodmaster", "pillow"};
        String[] initTestString = {"x", "y", "z", "foo", "apple", "barley", "quiz", "a", "b",
                "c", "d", "e"};
        int[] initTestInts = {5, 10, 13, -1, 12, 100, 63, 14, 11, 12, 29, 17};
        int[] fractionTest = {3, 3, 1, 2, 2, 4, 1, 4, 6, 14};

        System.out.println("Testing code...\n");
        for(int i = 0 ; i < negateTests.length; ++i) {
            System.out.println(negateTests[i] + " negated is " + negate(negateTests[i]));
        }
        System.out.println();

        for(int i = 0; i < penniesTests.length; ++i) {
            System.out.println("$" + penniesTests[i] + " has " + howManyPennies(penniesTests[i])
                    + " pennies.");
        }
        System.out.println();

        for(int i = 0; i < lastHalfTests.length; ++i) {
            System.out.println("The last half of \"" + lastHalfTests[i] + "\" is \"" +
                    lastHalf(lastHalfTests[i]) + "\"");
        }
        System.out.println();

        for(int i = 0; i < initTestString.length; ++i) {
            System.out.println("Initialization " + (i + 1) + " is " +
                    makeInitialization(initTestString[i], initTestInts[i]));
        }
        System.out.println();

        int i;
        for(i = 0; i < fractionTest.length; ++i) {
            int num = fractionTest[i];
            int den = fractionTest[++i];
            System.out.println(num + "/" + den + " is " + fraction(num, den));
        }

        System.out.println("Tests completed.");
    }


// PASTE YOUR CODE HERE

    public static int negate(int value) {
        return -value;
    }

    public static double fraction(int numerator, int denominator) {
        return (double)numerator/denominator;
    }

    static String makeInitialization(String name, int value) {
        return "int "+name+" = "+value+";";
    }

    public static int howManyPennies(double dollars) {
        return (int)(dollars*100.0);
    }

    static String lastHalf(String str) {
        return str.substring(str.length()/2,str.length());
    }

}