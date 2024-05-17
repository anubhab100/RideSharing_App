package com.geektrust.backend.entities;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("DriverTest")
public class DriverTest {

    private Driver driver;

    private final String driverId = "D123";
    private final int xCoordinate = 30;
    private final int yCoordinate = 40;

    @BeforeEach
    public void setUp() {
        driver = new Driver(driverId, xCoordinate, yCoordinate);
    }

    @Test
    @DisplayName("Driver instantiation should be successful")
    public void shouldInstantiateDriver() {
        assertNotNull(driver);
    }

    @Test
    @DisplayName("Driver getters should return correct values")
    public void shouldReturnCorrectValuesFromGetters() {
        assertEquals(driverId, driver.getDriverId());
        assertEquals(xCoordinate, driver.getXCoordinate());
        assertEquals(yCoordinate, driver.getYCoordinate());
        assertEquals(DriverStatus.AVAILABLE, driver.getStatus());
    }


    @Test
    @DisplayName("Driver toString should return correct format")
    public void shouldReturnCorrectToString() {
        String expectedString = "Driver [driverId=" + driverId + ", status=" + DriverStatus.AVAILABLE + ", xCoordinate="
                + xCoordinate + ", yCoordinate=" + yCoordinate + "]";
        assertEquals(expectedString, driver.toString());
    }
}