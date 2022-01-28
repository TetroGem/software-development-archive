/*
 * Course: CS1011 - 011
 * Fall 2021
 * Lab 9 - Lots of Parking
 * Name: Benjamin Singleton
 * Created: 10/26/2021
 * Modified: 11/2/2021
 */
package singletonb;

import java.text.DecimalFormat;

/**
 * A ParkingLot that will keep track of stats relating to it, such as capacity and closing times
 * @author Benjamin Singleton
 */
public class ParkingLot {


    /**
     * The percentage of fullness the ParkingLot will close one it reaches
     */
    public static final double CLOSED_THRESHOLD = 80.0;
    /**
     * A DecimalFormatter to properly format percentages for percentFull in the toString() method
     */
    public static final DecimalFormat PERCENTAGE_FORMATTER = new DecimalFormat("#.#");
    private final String name;
    private int capacity;
    private int carsInLot;
    private double percentFull;
    private int numberOfSpotsRemaining;
    private int latestTimestamp;
    private int closingTimestamp;
    private boolean closed;
    private int minutesClosed;

    /**
     * One parameter constructor for a ParkingLot, only sets for capacity
     * @param capacity maximum capacity of the ParkingLot
     */
    public ParkingLot(int capacity) {
        this("test", capacity);
    }

    /**
     * Master constructor for the ParkingLot, initializes the name and capacity
     * @param name name of the ParkingLot
     * @param capacity maximum capacity of the ParkingLot
     */
    public ParkingLot(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        carsInLot = 0;
        latestTimestamp = 0;
        closingTimestamp = 0;
        closed = isClosed();
        minutesClosed = 0;
        updateLot();
    }

    @Override
    public String toString() {
        if(isClosed()) {
            return String.format("Status for %s parking lot: %d vehicles (CLOSED)",
                    name, carsInLot, percentFull);
        } else {
            return String.format("Status for %s parking lot: %d vehicles (%s%%)",
                    name, carsInLot, PERCENTAGE_FORMATTER.format(percentFull));
        }
    }

    public int getMinutesClosed() {
        return minutesClosed;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfSpotsRemaining() {
        return numberOfSpotsRemaining;
    }

    public double getPercentFull() {
        return percentFull;
    }

    public boolean isClosed() {
        return percentFull >= CLOSED_THRESHOLD;
    }

    /**
     * Adds a car to the ParkingLot if a valid timestamp is given,
     * and updates statistics about the ParkingLot
     * @param timestamp the timestamp the car entered the ParkingLot
     */
    public void markVehicleEntry(int timestamp) {
        if(verifyTimestamp(timestamp)) {
            carsInLot++;
            updateLot();
        }
    }

    /**
     * Removes a car from the ParkingLot if a valid timestamp is given,
     * and updates statistics about the ParkingLot
     * @param timestamp the timestamp the car exited the ParkingLot
     */
    public void markVehicleExit(int timestamp) {
        if(verifyTimestamp(timestamp)) {
            carsInLot--;
            updateLot();
        }
    }

    private void updateLot() {
        final double decimalToPercent = 100.0;
        percentFull = (double)carsInLot / capacity * decimalToPercent;
        numberOfSpotsRemaining = capacity - carsInLot;
        if(!closed && isClosed()) {
            closingTimestamp = latestTimestamp;
            closed = true;
        }
        if(closed && !isClosed()) {
            minutesClosed += latestTimestamp - closingTimestamp;
            closed = false;
        }
    }

    private boolean verifyTimestamp(int timestamp) {
        final boolean verified = timestamp >= latestTimestamp;
        if(verified) {
            latestTimestamp = timestamp;
        }
        return verified;
    }

}
