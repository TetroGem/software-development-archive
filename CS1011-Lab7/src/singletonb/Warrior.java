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
 * The character the player plays as, stores information about their Dice and current
 * hit points, as well as parses input in order to calculate attack damage and player damage
 */
public class Warrior {

    private int hitPoints;
    private final Die d20;
    private final Die d10;
    private final Die d8;
    private final Die d4;

    /**
     * Creates a Warrior with 4 Dice and runs setInitialHitPoints to calculate their
     * starting health
     */
    public Warrior() {
        final int d20Sides = 20;
        final int d10Sides = 10;
        final int d8Sides = 8;
        final int d4Sides = 4;
        d20 = new Die(d20Sides);
        d10 = new Die(d10Sides);
        d8 = new Die(d8Sides);
        d4 = new Die(d4Sides);
        hitPoints = setInitialHitPoints();
    }

    public int getHitPoints() {
        return hitPoints;
    }

    /**
     * Damages the warrior
     * @param damage how much damage will be afflicted
     */
    public void takeDamage(int damage) {
        hitPoints -= damage;
    }

    /**
     * Calculates how much damage an attack from the warrior will do
     * @param type the attack that will be used
     * @return damage from the attack
     */
    public int attack(int type) {
        final int swordChance = 12;
        final int shieldChance = 6;
        int damage = 0;
        String weaponName = "";
        d20.roll();
        int hitRoll = d20.getCurrentValue();
        switch(type) {
            case 1 -> {
                weaponName = "Trusty Sword";
                if(hitRoll >= swordChance) {
                    for(int i = 0; i < 2; i++) {
                        d8.roll();
                        damage += d8.getCurrentValue();
                    }
                }
            }
            case 2 -> {
                weaponName = "Shield of Light";
                if(hitRoll >= shieldChance) {
                    d4.roll();
                    damage += d4.getCurrentValue();
                }
            }
        }

        if(damage > 0) {
            System.out.printf("\nYou hit the Mugwump with your %s for %d point(s) of damage!\n",
                    weaponName, damage);
        } else {
            System.out.printf("\nYou swing your %s and miss the foul creature!\n", weaponName);
        }

        return damage;
    }

    /**
     * Calculates the initial hit points for the warrior
     * @return the warrior's initial hit points
     */
    private int setInitialHitPoints() {
        final int rollTimes = 4;
        int initialHitPoints = 0;
        for(int i = 0; i < rollTimes; i++) {
            d10.roll();
            initialHitPoints += d10.getCurrentValue();
        }
        return initialHitPoints;
    }

}
