package com.geektrust.backend.entities;

public class Driver {
    private final String driverId;
    private final int xCoordinate;
    private final int yCoordinate;
    private DriverStatus status;

    public Driver(String driverId, int xCoordinate, int yCoordinate) {
        this.driverId = driverId;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.status = DriverStatus.AVAILABLE; // Assuming the driver is initially available
    }

    public Driver(String driverId, int xCoordinate, int yCoordinate, DriverStatus status) {
        this.driverId = driverId;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.status = status;
    }

    // Getters for fields
    public String getDriverId() {
        return driverId;
    }


    public int getXCoordinate() {
        return xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public DriverStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Driver [driverId=" + driverId + ", status=" + status + ", xCoordinate="
                + xCoordinate + ", yCoordinate=" + yCoordinate + "]";
    }

}
