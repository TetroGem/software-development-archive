/*
 * Course: CS-1011
 * Fall 2021
 * Lab 1 - First Program
 * Name: Benjamin Singleton
 * Created: 09/02/21
 */
package singletonb;

import java.util.Scanner;

/**
 * Class containing the entire program for lab 1 in Fall 2021 CS-1011 course. 
 * @author taylor
 * @version 2021-09-02
 */
public class StudyTime {

    /**
     * Simple program to determine help set study expectations for a new
     * college student.
     * @param args ignored
     */
    public static void main(String[] args) {
        // Create a "reference variable"/object to gather data
        // from the keyboard
        Scanner in = new Scanner(System.in);

        // Request data from the user
        System.out.print("Enter the number of credits you are taking this term: ");
        int creditsTaken = in.nextInt();
        System.out.print("Enter the number of hours you plan to spend studying each week: ");
        int plannedHours = in.nextInt();

        // Calculate recommended hours of study
        int recommendedHoursLow = creditsTaken * 2;
        int recommendedHoursHigh = creditsTaken * 3;

        System.out.println("You are taking " + creditsTaken
                + " credits this term and plan to study " + plannedHours
                + " hours per week.");

        System.out.print("You plan to study ");
        if(plannedHours < recommendedHoursLow) {
            System.out.print("less than");
        } else if(plannedHours > recommendedHoursHigh) {
            System.out.print("more than");
        } else {
            System.out.print("in the range of");
        }
        System.out.println(" the recommended " + recommendedHoursLow + " - "
                + recommendedHoursHigh + " hours per week.");

    }

}