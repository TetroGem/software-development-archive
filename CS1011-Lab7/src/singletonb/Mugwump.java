/*
 * Course: CS1011 - 011
 * Fall 2021
 * Lab 7 - Battle Simulator 3000
 * Name: Benjamin Singleton
 * Created: 10/18/2021
 * Modified: 10/25/2021
 */
package singletonb;

/**
 * The enemy the warrior will be fighting against in the Battle Simulator,
 * storing all of its Dice and stats, and includes methods pertaining to its
 * AI when attacks and the stats of its different attack types
 */
public class Mugwump {

    private int hitPoints;
    private final int maxHitPoints;
    private final Die d100;
    private final Die d20;
    private final Die d10;
    private final Die d6;

    // add methods here

    /**
     * Creates a Mugwump, and stores information about each type of Die it can use and
     * its current and maximum hit points
     */
    public Mugwump() {
        final int d100Sides = 100;
        final int d20Sides = 20;
        final int d10Sides = 10;
        final int d6Sides = 6;
        d100 = new Die(d100Sides);
        d20 = new Die(d20Sides);
        d10 = new Die(d10Sides);
        d6 = new Die(d6Sides);
        maxHitPoints = setInitialHitPoints();
        hitPoints = maxHitPoints;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    /**
     * Removes hit points from the mugwump
     * @param damage how much damage will be received
     */
    public void takeDamage(int damage) {
        hitPoints -= damage;
    }

    /**
     * Calculates the initial hit points for the mugwump
     * @return the mugwump's initial hit points
     */
    private int setInitialHitPoints() {
        final int rollTimes = 6;
        int initialHitPoints = 0;
        for(int i = 0; i < rollTimes; i++) {
            d10.roll();
            initialHitPoints += d10.getCurrentValue();
        }
        return initialHitPoints;
    }

    /**
     * This method handles the attack logic
     * @return the amount of damage an attack has caused, 0 if the attack misses or 
     *         if the Mugwump heals itself
     */
    public int attack() {
        // initialize attack chances
        final int clawsChance = 13;
        final int fangsChance = 16;
        // get attack type from AI
        int attackType = ai();
        boolean miss = false;
        // roll attack die
        d20.roll();
        int attackRoll = d20.getCurrentValue();
        int attackChance = 0;
        int rolls = 0;
        String attackName = "";
        switch(attackType) {
            case 1 -> {
                attackChance = clawsChance;
                rolls = 2;
                attackName = "Razor-Sharp Claws";
            }
            case 2 -> {
                attackChance = fangsChance;
                rolls = 3;
                attackName = "Fangs of Death";
            }
            case 3 -> {
                attackChance = 1;
                rolls = 1;
                attackName = "Lick Wounds";
            }
        }
        if(attackRoll < attackChance) {
            miss = true;
        }
        // determine results of attack
        int damage = 0;
        int healAmount = 0;
        String attackResult;
        if(!miss) {
            for (int i = 0; i < rolls; i++) {
                d6.roll();
                damage += d6.getCurrentValue();
            }
            attackResult = String.format("deals %d point(s) of damage", damage);
        } else {
            attackResult = "misses";
        }
        if(attackType == 3){
            healAmount = hitPoints + damage > maxHitPoints ? maxHitPoints - hitPoints : damage;
            damage = 0;
        }
        // output attack message
        if(attackType == 3) {
            System.out.printf("The Mugwump licks their wounds and" +
                    "heals %d hit point(s)!\n", healAmount);
        } else {
            System.out.printf("The Mugwump uses their %s and %s!\n", attackName, attackResult);
        }
        // return the damage
        hitPoints += healAmount;
        return damage;
    }

    /**
     * This method determines what action the Mugwump performs
     * @return 1 for a Claw attack, 2 for a Bite, and 3 if the Mugwump licks its wounds
     */
    private int ai() {
        final int clawsChance = 60;
        final int fangsChance = 25;
        d100.roll();
        int chance = d100.getCurrentValue();
        if(chance <= clawsChance){
            return 1; // razor-sharp claws
        } else if(chance <= clawsChance + fangsChance) {
            return 2; // fangs of death
        } else {
            return 3; // lick wounds
        }
    }
}