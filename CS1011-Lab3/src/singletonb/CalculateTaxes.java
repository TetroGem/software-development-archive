/*
 * Course: CS-1011
 * Fall 2021
 * Lab 3 - Conditionals
 * Name: Benjamin Singleton
 * Created: 09/21/21
 */

package singletonb;

import java.util.Locale;
import java.util.Scanner;

public class CalculateTaxes {

    public static void main(String[] args) {

        //create variables
            boolean joint=false;
            //maximum amounts per bracket
            int[] maxSingle={9950,40525,86375,164925,209425,523600,-1};
            int[] maxJoint={19900,81050,172750,329850,418850,628300,-1};
            double[] taxPercent={0.10,0.12,0.22,0.24,0.32,0.35,0.37};

        //create scanner
        Scanner in=new Scanner(System.in);

        //create input from user variable (set dummy value to active while loop)
        String input="n";
        var input2="test";

        //keep prompting until valid response is entered
        while(!input.equals("s")&&!input.equals("j")){

            System.out.println("Are you a single or married joint filer? ['s'/'j']");
            input=in.nextLine().toLowerCase(Locale.ROOT);

        }
        //exits once set to either 's' or 'j'

        joint=input.equals("j"); //set value of joint based on input
        input="n"; //reset input to dummy value

        //keep prompting until valid salary is entered
        while(!input.replace("$","").matches("^[0-9]*\\.?[0-9]+$")){

            System.out.println("Enter an estimate of your earned income for 2021");
            input=in.nextLine().replace("$","");

        }

        double salary=Double.parseDouble(input); //set salary to input

        //set up variables for calculating taxes
        double moneyLeft=salary;
        double totalTaxes=0;
        int[] maxBrackets=joint?maxJoint:maxSingle;

        for(int i=0; i<maxBrackets.length; i++){

            double bracketRange=maxBrackets[i]-(i>0?maxBrackets[i-1]:0);
            double amountInBracket=0;

            if(moneyLeft-bracketRange<0||maxBrackets[i]<0){ //money remaining to be taxed does not exceed bracket range OR in the final bracket (rest of the money will be taxed)
                amountInBracket=moneyLeft; //set the amount in bracket to the remainder
                moneyLeft=0; //set money remaining to 0
            }
            else{ //money remaining exceeds bracket range
                moneyLeft -= bracketRange; //remove amount in bracket from remainder
                amountInBracket = bracketRange; //set the amount thats in the bracket to the total bracket size
            }

            //add taxes of this bracket to total
            totalTaxes+=amountInBracket*taxPercent[i];

            //break out of loop if out of money
            if(moneyLeft<=0){
                break;
            }

        }

        //output final results to user
        System.out.println("Your estimated taxes for 2021 are $"+totalTaxes);
        System.out.println("This results in an "+((double)Math.round(totalTaxes/salary*10000)/100)+"% effective tax rate.");

    }

}
