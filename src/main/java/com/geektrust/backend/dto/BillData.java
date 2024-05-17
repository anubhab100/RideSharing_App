package com.geektrust.backend.dto;
public class BillData {
    private String rideId;
    private int destinationXCoordinate;
    private int destinationYCoordinate;
    private int timeTakenInMin;

    // Constructors, getters, and setters

    public BillData(String rideId, int destinationXCoordinate, int destinationYCoordinate, int timeTakenInMin) {
        this.rideId = rideId;
        this.destinationXCoordinate = destinationXCoordinate;
        this.destinationYCoordinate = destinationYCoordinate;
        this.timeTakenInMin = timeTakenInMin;
    }

    public String getRideId() {
        return rideId;
    }

    public void setRideId(String rideId) {
        this.rideId = rideId;
    }

    public int getDestinationXCoordinate() {
        return destinationXCoordinate;
    }

    public void setDestinationXCoordinate(int destinationXCoordinate) {
        this.destinationXCoordinate = destinationXCoordinate;
    }

    public int getDestinationYCoordinate() {
        return destinationYCoordinate;
    }

    public void setDestinationYCoordinate(int destinationYCoordinate) {
        this.destinationYCoordinate = destinationYCoordinate;
    }

    public int getTimeTakenInMin() {
        return timeTakenInMin;
    }

    public void setTimeTakenInMin(int timeTakenInMin) {
        this.timeTakenInMin = timeTakenInMin;
    }
}
