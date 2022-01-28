/*
 * Course: CS1011 - 011
 * Fall 2021
 * Lab 9 - Lots of Parking
 * Name: Benjamin Singleton
 * Created: 11/6/2019
 * Modified: 11/2/2021
 */
package singletonb;

/**
 * Manages parking lots within a district.
 * @author Benjamin Singleton
 */
public class District {

    /**
     * The maximum amount of lots a District can have
     */
    public static final int MAX_LOTS = 20;
    private ParkingLot[] lots;
    private int numLots;
    private int latestTimestamp;
    private int closingTimestamp;
    private boolean closed;
    private int minutesClosed;

    /**
     * Constructs a new District with a MAX_LOTS amount of ParkingLots
     */
    public District() {
        lots = new ParkingLot[MAX_LOTS];
    }

    @Override
    public String toString() {
        String districtString = "District status:\n";
        for(int i = 0; i < numLots; i++) {
            districtString += "  " + getLot(i).toString() + "\n";
        }
        return districtString;
    }

    /**
     * Adds a new ParkingLot to the District
     * @param name the name of the ParkingLot
     * @param capacity the capacity of the ParkingLot
     * @return the index of the ParkingLot in the lots array
     */
    public int addLot(String name, int capacity) {
        int newIndex = numLots;
        if(newIndex < MAX_LOTS) {
            lots[newIndex] = new ParkingLot(name, capacity);
            numLots++;
        }
        // return the index of the new lot or -1 if the lot was not added.
        return newIndex < MAX_LOTS ? newIndex : -1;
    }

    /**
     * Gets a ParkingLot from the lots array and returns it
     * @param index the index of the ParkingLot in lots
     * @return the ParkingLot object
     */
    public ParkingLot getLot(int index) {
        return lots[index];
    }

    /**
     * Returns the number of remaining parking spots in the district
     * @return the number of remaining parking spots in the district
     */
    public int getNumberOfSpotsRemaining() {
        int numberOfSpotsRemaining = 0;
        for(int i = 0; i < numLots; i++) {
            numberOfSpotsRemaining += getLot(i).getNumberOfSpotsRemaining();
        }
        return numberOfSpotsRemaining;
    }

    /**
     * Returns the amount of time all lots have been
     * simultaneously closed.
     * @return number of minutes all lots have been closed
     */
    public int getMinutesClosed() {
        return minutesClosed;
    }

    /**
     * Checks the status of all lots in the district and
     * returns true if they are all closed and false otherwise.
     * @return whether all lots in the district are closed
     */
    public boolean isClosed() {
        boolean parkingLotOpen = false;
        for(int i = 0; i < numLots && !parkingLotOpen; i++) {
            parkingLotOpen = !getLot(i).isClosed();
        }
        return !parkingLotOpen;
    }

    /**
     * Record a vehicle entering a lot at a specified timestamp.
     * <p></p>
     * This calls ParkingLot.markVehicleEntry for the lot corresponding
     * to lotNumber (e.g., if lotNumber==1, call markVehicleEntry on lot1).
     * <p></p>
     * If lotNumber is out of range, the method should return without
     * doing anything else.
     * @param lotNumber Number of lot (should be an index of lots less than numLots)
     * @param timestamp Entry timestamp in minutes since all lots were opened.
     */
    public void markVehicleEntry(int lotNumber, int timestamp) {
        checkTimestamp(timestamp);
        final ParkingLot accessedLot = getLot(lotNumber);
        accessedLot.markVehicleEntry(timestamp);
        updateDistrict();
    }

    /**
     * Record a vehicle exiting a lot at a specified timestamp.
     * <p></p>
     * This calls ParkingLot.markVehicleExit for the lot corresponding
     * to lotNumber (e.g., if lotNumber==1, call markVehicleExit on lots[1]).
     * <p></p>
     * If lotNumber is out of range, the method should return without
     * doing anything else.
     * @param lotNumber Number of lot (should be an index of lots less than numLots)
     * @param timestamp Exit timestamp in minutes since all lots were opened.
     */
    public void markVehicleExit(int lotNumber, int timestamp) {
        if(lotNumber >= 0 && lotNumber < numLots) {
            checkTimestamp(timestamp);
            final ParkingLot accessedLot = getLot(lotNumber);
            accessedLot.markVehicleExit(timestamp);
            updateDistrict();
        }
    }

    private void updateDistrict() {
        if(!closed && isClosed()) {
            closingTimestamp = latestTimestamp;
            closed = true;
        }
        if(closed && !isClosed()) {
            minutesClosed += latestTimestamp - closingTimestamp;
            closed = false;
        }
    }

    private void checkTimestamp(int timestamp) {
        final boolean verified = timestamp >= latestTimestamp;
        if(verified) {
            latestTimestamp = timestamp;
        }
    }

}
