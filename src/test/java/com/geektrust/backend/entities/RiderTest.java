package com.geektrust.backend.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("RiderTest")
public class RiderTest {

    private Rider rider;

    private final String riderId = "R123";
    private final int xCoordinate = 10;
    private final int yCoordinate = 20;

    @BeforeEach
    public void setUp() {
        rider = new Rider(riderId, xCoordinate, yCoordinate);
    }

    @Test
    @DisplayName("Rider instantiation should be successful")
    public void shouldInstantiateRider() {
        assertNotNull(rider);
    }

    @Test
    @DisplayName("Rider getters should return correct values")
    public void shouldReturnCorrectValuesFromGetters() {
        assertEquals(riderId, rider.getRiderId());
        assertEquals(xCoordinate, rider.getXCoordinate());
        assertEquals(yCoordinate, rider.getYCoordinate());
    }

    @Test
    @DisplayName("Rider toString should return correct format")
    public void shouldReturnCorrectToString() {
        String expectedString = "Rider [riderId=" + riderId + ", xCoordinate=" + xCoordinate + ", yCoordinate="
                + yCoordinate + "]";
        assertEquals(expectedString, rider.toString());
    }
}
