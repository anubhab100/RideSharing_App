package com.geektrust.backend.entities;
public class Rider {
    private final String riderId;
    private final int xCoordinate;
    private final int yCoordinate;

    public Rider(String riderId, int xCoordinate, int yCoordinate) {
        this.riderId = riderId;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    // Getters for fields
    public String getRiderId() {
        return riderId;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    @Override
    public String toString() {
        return "Rider [riderId=" + riderId + ", xCoordinate=" + xCoordinate + ", yCoordinate="
                + yCoordinate + "]";
    }
}