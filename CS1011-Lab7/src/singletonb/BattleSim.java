/*
 * Course: CS1011 - 011
 * Fall 2021
 * Lab 7 - Battle Simulator 3000
 * Name: Benjamin Singleton
 * Created: 10/18/2021
 * Modified: 10/25/2021
 */
package singletonb;

import java.util.Locale;
import java.util.Scanner;

/**
 * BattleSim Driver for Battle Simulator 3000
 */
public class BattleSim {
    /**
     * Ten-sided die to be used for checking initiative by all classes
     */
    public static final Die INITIATIVE_DIE = new Die(10);

    public static void main(String[] args) {
        // Local variables
        // Include any variable that will need to be accessed throughout the program here
        Warrior warrior;
        Mugwump mugwump;
        Scanner in = new Scanner(System.in);

        // sentinel value for the game loop
        boolean play;
        // String used to determine the winner of the epic battle
        String victor;
        // game loop
        do {
            // print the introduction and rules
            intro();

            // initialize game
            // Initialize the Warrior and Mugwump classes, set the current victor to "none"
            warrior = new Warrior();
            mugwump = new Mugwump();
            victor = "none";

            // while neither combatant has lost all of their hit points, report status and battle!
            while (victor.equalsIgnoreCase("none")) {
                report(warrior, mugwump);
                victor = battle(warrior, mugwump, in);
            }
            // declare the winner
            victory(victor);

            // ask to play again
            play = playAgain(in);

        } while(play);
        // Thank the user for playing your game
        System.out.println("Thank you for playing Battle Simulator 3000!");

    }

    /**
     * This method displays the introduction to the game and gives a description of the rules.
     */
    private static void intro() {
        // Write a suitable introduction to your game
        System.out.println("Welcome to Battle Simulator 3000! The world's more" +
                "low tech battle simulator!");
        System.out.println("You are a Valiant Warrior defending your humble village" +
                "from an evil Mugwump! Fight bravely,");
        System.out.println("or the citizens of your town will be the Mugwump's dinner!\n");
        System.out.println("You have your Trusty Sword, which deals decent damage," +
                "but can be tough to hit with sometimes.");
        System.out.println("You also have your Shield of Light, which is not as strong as" +
                "your sword, but is easier to deal");
        System.out.println("damage with.\n");
    }

    /**
     * This method handles the battle logic for the game.
     * @param warrior The Warrior of Light!
     * @param mugwump The Evil Mugwump!
     * @return The name of the victor, or "none", if the battle is still raging on
     */
    private static String battle(Warrior warrior, Mugwump mugwump, Scanner in) {
        // determine who attacks first (Roll! For! Initiative!) and store the result
        int firstAttack;
        firstAttack = initiative();
        // attack code
        switch(firstAttack) {
            // If the Warrior attacks first
            case 1 -> {
                // Warrior attacks and assigns the resulting damage to the mugwump
                System.out.println("\nThe Warrior attacks first!");
                int attackType = attackChoice(in);
                int warriorDamage = warrior.attack(attackType);
                mugwump.takeDamage(warriorDamage);
                // Check if the Mugwump has been defeated
                int mugwumpHitPoints = mugwump.getHitPoints();
                if(mugwumpHitPoints > 0){
                    // If not, Mugwump attacks!
                    int mugwumpDamage = mugwump.attack();
                    warrior.takeDamage(mugwumpDamage);
                }
            }
            // Otherwise, the Mugwump is first
            case 2 -> {
                // Mugwump attacks and assigns the resulting damage to the warrior
                System.out.println("\nThe Mugwump attacks first!");
                int mugwumpDamage = mugwump.attack();
                warrior.takeDamage(mugwumpDamage);
                // Check if the Warrior has been defeated
                int warriorHitPoints = warrior.getHitPoints();
                if(warriorHitPoints > 0){
                    // If not, Warrior attacks!
                    int attackType = attackChoice(in);
                    int warriorDamage = warrior.attack(attackType);
                    mugwump.takeDamage(warriorDamage);
                }
            }
        }

        // If neither combatant is defeated, the battle rages on!
        int warriorHitPoints = warrior.getHitPoints();
        int mugwumpHitPoints = mugwump.getHitPoints();
        if(warriorHitPoints <= 0){
            return "mugwump";
        } else if(mugwumpHitPoints <= 0) {
            return "warrior";
        } else {
            return "none";
        }
    }

    /**
     * This method reports the status of the combatants
     * @param warrior The Warrior of Light!
     * @param mugwump The Evil Mugwump!
     */
    private static void report(Warrior warrior, Mugwump mugwump) {
        System.out.printf("Warrior HP: %d\n", warrior.getHitPoints());
        System.out.printf("Mugwump HP: %d\n", mugwump.getHitPoints());
    }

    /**
     * This method asks the user what attack type they want to use and returns the result
     * @return 1 for sword, 2 for shield
     */
    private static int attackChoice(Scanner in) {
        int attackType;
        System.out.println("""
                How would you like to attack?
                1. Your Trusty Sword
                2. Your Shield of Light""");
        do {
            System.out.print("Enter choice: ");
            attackType = in.nextInt();
        } while (attackType < 1 || attackType > 2);
        return attackType;
    }

    /**
     * Determines which combatant attacks first and returns the result. In the case of a tie,
     * re-roll.
     * @return 1 if the warrior goes first, 2 if the mugwump goes first
     */
    private static int initiative() {
        int warriorInitiative;
        int mugwumpInitiative;
        // roll for initiative for both combatants
        do {
            INITIATIVE_DIE.roll();
            warriorInitiative = INITIATIVE_DIE.getCurrentValue();
            INITIATIVE_DIE.roll();
            mugwumpInitiative = INITIATIVE_DIE.getCurrentValue();
        } while(warriorInitiative == mugwumpInitiative);
        // until one initiative is greater than the other
        return warriorInitiative > mugwumpInitiative ? 1 : 2;
    }

    /**
     * This method declares the victor of the epic battle
     * @param victor the name of the victor of the epic battle
     */
    private static void victory(String victor) {
        System.out.println("\n");
        switch(victor) {
            case "warrior" -> System.out.println("The citizens cheer and invite" +
                    "you back to town for a feast as thanks for saving their lives (again)!\n");
            case "mugwump" -> System.out.println("The citizens do not cheer" +
                    "and do not invite you back to town for a feast as they are dead (again)!\n");
        }
    }

    /**
     * This method asks the user if they would like to play again
     * @param in Scanner
     * @return true if yes, false otherwise
     */
    private static boolean playAgain(Scanner in) {
        System.out.println("Would you like to play again? (Y)es/(N)o");
        in.nextLine();
        String userInput = in.nextLine().toLowerCase(Locale.ROOT);
        return userInput.length() != 0 && userInput.charAt(0) == 'y';
    }
}