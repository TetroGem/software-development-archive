/*
 * Course: CS1011 - 011
 * Fall 2021
 * Lab 8 - ParkingLots
 * Name: Benjamin Singleton
 * Created: 11/6/2019
 * Modified: 10/26/2021
 */
package singletonb;

/**
 * Manages parking lots within a district.
 * @author Benjamin Singleton
 */
public class District {
    private final ParkingLot lot1;
    private final ParkingLot lot2;
    private final ParkingLot lot3;
    private int latestTimestamp;
    private int closingTimestamp;
    private boolean closed;
    private int minutesClosed;

    /**
     * Set up a district with three parking lots.
     * @param name1 Name of the first parking lot
     * @param capacity1 Maximum number of vehicles for the first parking lot
     * @param name2 Name of the second parking lot
     * @param capacity2 Maximum number of vehicles for the second parking lot
     * @param name3 Name of the third parking lot
     * @param capacity3 Maximum number of vehicles for the third parking lot
     */
    public District(String name1, int capacity1, String name2, int capacity2,
                    String name3, int capacity3) {
        lot1 = new ParkingLot(name1, capacity1);
        lot2 = new ParkingLot(name2, capacity2);
        lot3 = new ParkingLot(name3, capacity3);
        latestTimestamp = 0;
        closingTimestamp = 0;
        closed = isClosed();
        minutesClosed = 0;
    }

    /**
     * Display status information for all three lots.
     * @see ParkingLot#displayStatus() for the format for each.
     */
    public void displayStatus() {
        System.out.println("District status:");
        System.out.print("  ");
        lot1.displayStatus();
        System.out.print("  ");
        lot2.displayStatus();
        System.out.print("  ");
        lot3.displayStatus();
        System.out.println();
    }

    /**
     * Returns the number of remaining parking spots in the district
     * @return the number of remaining parking spots in the district
     */
    public int getNumberOfSpotsRemaining() {
        int numberOfSpotsRemaining = 0;
        for(int i = 0; i < 3; i++) {
            numberOfSpotsRemaining += findLot(i).getNumberOfSpotsRemaining();
        }
        return numberOfSpotsRemaining;
    }

    /**
     * Returns the amount of time all three lots have been
     * simultaneously closed.
     * @return number of minutes all three lots have been closed
     */
    public int getMinutesClosed() {
        return minutesClosed;
    }

    /**
     * Checks the status of all three lots in the district and
     * returns true if they are all closed and false otherwise.
     * @return whether all three lots in the district are closed
     */
    public boolean isClosed() {
        boolean parkingLotOpen = false;
        for(int i = 0; i < 3 && !parkingLotOpen; i++) {
            parkingLotOpen = !findLot(i).isClosed();
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
     * @param lotNumber Number of lot (should be 1, 2, or 3)
     * @param timestamp Entry timestamp in minutes since all lots were opened.
     */
    public void markVehicleEntry(int lotNumber, int timestamp) {
        checkTimestamp(timestamp);
        final ParkingLot accessedLot = findLot(lotNumber);
        accessedLot.markVehicleEntry(timestamp);
        updateDistrict();
    }

    /**
     * Record a vehicle exiting a lot at a specified timestamp.
     * <p></p>
     * This calls ParkingLot.markVehicleExit for the lot corresponding
     * to lotNumber (e.g., if lotNumber==1, call markVehicleExit on lot1).
     * <p></p>
     * If lotNumber is out of range, the method should return without
     * doing anything else.
     * @param lotNumber Number of lot (should be 1, 2, or 3)
     * @param timestamp Exit timestamp in minutes since all lots were opened.
     */
    public void markVehicleExit(int lotNumber, int timestamp) {
        checkTimestamp(timestamp);
        final ParkingLot accessedLot = findLot(lotNumber);
        accessedLot.markVehicleExit(timestamp);
        updateDistrict();
    }

    private ParkingLot findLot(int lotNumber) {
        return switch(lotNumber) {
            case 1 -> lot1;
            case 2 -> lot2;
            default -> lot3;
        };
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
