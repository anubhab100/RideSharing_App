package com.geektrust.backend.entities;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("RideTest")
public class RideTest {

    private Ride ride;

    private final String rideId = "R123";
    private final String riderId = "RD123";
    private final String driverId = "DR123";

    @BeforeEach
    public void setUp() {
        ride = new Ride(rideId, riderId, driverId);
    }

    @Test
    @DisplayName("Ride instantiation should be successful")
    public void shouldInstantiateRide() {
        assertNotNull(ride);
    }

    @Test
    @DisplayName("Ride getters should return correct values")
    public void shouldReturnCorrectValuesFromGetters() {
        assertEquals(rideId, ride.getRideId());
        assertEquals(riderId, ride.getRiderId());
        assertEquals(driverId, ride.getDriverId());
        assertEquals(RideStatus.STARTED, ride.getStatus());
    }

    @Test
    @DisplayName("Ride setters should update fields correctly")
    public void shouldUpdateFieldsCorrectly() {
        ride.setStatus(RideStatus.COMPLETED);
        assertEquals(RideStatus.COMPLETED, ride.getStatus());

        ride.setTimeTaken(15);
        assertEquals(15, ride.getTimeTaken());
    }

    @Test
    @DisplayName("Ride toString should return correct format")
    public void shouldReturnCorrectToString() {
        String expectedString = "Ride [driverId=" + driverId + ", rideId=" + rideId + ", riderId=" + riderId
                + ", status=" + RideStatus.STARTED + "]";
        assertEquals(expectedString, ride.toString());
    }
}