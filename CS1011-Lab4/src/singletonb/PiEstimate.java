/*
 * Course: CS-1011
 * Fall 2021
 * Lab 4 - Loops
 * Name: Benjamin Singleton
 * Created: 09/28/21
 */

package singletonb;

import java.util.Locale;
import java.util.Scanner;

public class PiEstimate {

    public static double calcCircle(int width, String mode){

        double totalInCircle=0;

        for (int y = width; y >= -1; y--) {

            for (int x = -1; x < width+1; x++) {

                boolean pixelInCircle = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) <= width - 0.5;
                int outOfBounds=(y >= width || y <= -1 ? 3 : 1) * (x >= width || x <= -1 ? 2 : 1);

                if(mode.equals("draw")) {
                    switch (outOfBounds) {

                        case 6:
                            System.out.print("+");
                            break;

                        case 2:
                            System.out.print("|");
                            break;

                        case 3:
                            System.out.print("-");
                            break;

                    }

                    if(outOfBounds==1) {

                        if (pixelInCircle) {
                            System.out.print("*");
                            totalInCircle++;
                        } else {
                            System.out.print(" ");
                        }

                    }
                }
                else{
                    if (pixelInCircle&&outOfBounds==1) totalInCircle++;
                }

            }

            if(mode.equals("draw")) {
                System.out.print("\n");
            }

        }

        if(mode.equals("draw")){
            return totalInCircle;
        }
        else{
            return (totalInCircle/Math.pow(width,2))*4;
        }

    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        boolean inProgram=true;

        while(inProgram) {

            System.out.println("Would you like to draw an image [I] or estimate PI within a minimum error [E]?");
            String choice=in.nextLine().toLowerCase(Locale.ROOT);

            if(choice.equals("i")) {

                System.out.println("What is the desired width of the image? (in pixels)");
                int width = in.nextInt();

                double inCircle = calcCircle(width, "draw");
                double estimate = inCircle / Math.pow(width, 2) * 4;
                System.out.println("Using this image, PI is approximately:");
                System.out.println("( " + (int) inCircle + " / " + (int) Math.pow(width, 2) + " ) * 4 = " + estimate);

            }
            else if(choice.equals("e")) {

                System.out.println("What is your minimum desired error for PI?");
                double minError = in.nextDouble();

                int startWidth=1;
                while (calcCircle(startWidth, "estimate") - Math.PI > minError) {

                    startWidth++;
                    if (startWidth % 100 == 0) {
                        double curError = (calcCircle(startWidth, "estimate") - Math.PI);
                        System.out.println("~ Looked through " + startWidth + " circles... (Current Error: " + curError + ")");
                    }

                }

                double estimate = calcCircle(startWidth, "estimate");
                System.out.println("An estimate of " + estimate + " was achieved with a width of " + startWidth + " (Error: " + (estimate - Math.PI) + ")");
                //System.out.println("prev: "+(calcCircle(startWidth-1, "estimate")-Math.PI));

            }

            System.out.println("Would you like to continue using this program? [Y/N]");
            in.nextLine();
            inProgram=!in.nextLine().toLowerCase(Locale.ROOT).equals("n");

        }

    }

}
