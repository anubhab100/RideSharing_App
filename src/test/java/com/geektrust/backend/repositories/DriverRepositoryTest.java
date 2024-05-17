package com.geektrust.backend.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import com.geektrust.backend.entities.Driver;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("DriverRepository Test")
public class DriverRepositoryTest {

    private DriverRepository driverRepository;

    @BeforeEach
    public void setUp() {
        driverRepository = new DriverRepository();
    }

    @Test
    @DisplayName("Save and Retrieve Driver")
    public void saveAndRetrieveDriver() {
        // Arrange
        Driver driver = new Driver("D001", 10, 20);

        // Act
        Driver savedDriver = driverRepository.save(driver);
        Optional<Driver> retrievedDriver = driverRepository.findById("D001");

        // Assert
        assertTrue(retrievedDriver.isPresent());
        assertEquals(driver, retrievedDriver.get());
        assertEquals(driver, savedDriver);
    }

    @Test
    @DisplayName("Find All Drivers")
    public void findAllDrivers() {
        // Arrange
        Driver driver1 = new Driver("D001", 10, 20);
        Driver driver2 = new Driver("D002", 15, 25);

        // Act
        driverRepository.save(driver1);
        driverRepository.save(driver2);
        List<Driver> allDrivers = driverRepository.findAll();

        // Assert
        assertEquals(2, allDrivers.size());
        assertTrue(allDrivers.contains(driver1));
        assertTrue(allDrivers.contains(driver2));
    }

    @Test
    @DisplayName("Find Driver By Id - Not Found")
    public void findDriverByIdNotFound() {
        // Act
        Optional<Driver> driver = driverRepository.findById("D001");

        // Assert
        assertFalse(driver.isPresent());
    }

    @AfterEach
    public void tearDown() {
        // Clearing data after each test
        driverRepository = null;
    }
}
