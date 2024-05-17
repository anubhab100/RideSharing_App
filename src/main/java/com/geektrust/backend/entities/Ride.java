package com.geektrust.backend.entities;
public class Ride {
    private final String rideId;
    private final String riderId;
    private final String driverId;
    private int timeTakenInMin=0;
    private RideStatus status;

    public Ride(String rideId, String riderId, String driverId) {
        this.rideId = rideId;
        this.riderId = riderId;
        this.driverId = driverId;
        this.status = RideStatus.STARTED; // Assuming the ride is initially started
    }

    // Getters for fields
    public String getRideId() {
        return rideId;
    }

    public String getRiderId() {
        return riderId;
    }

    public String getDriverId() {
        return driverId;
    }

    public RideStatus getStatus() {
        return status;
    }

    public void setStatus(RideStatus status) {
        this.status = status;
    }
    public void setTimeTaken(int timeTakenInMin){
        this.timeTakenInMin=timeTakenInMin;
    }
    public int getTimeTaken(){
        return timeTakenInMin;
    }

    @Override
    public String toString() {
        return "Ride [driverId=" + driverId + ", rideId=" + rideId + ", riderId=" + riderId
                + ", status=" + status + "]";
    }
}
