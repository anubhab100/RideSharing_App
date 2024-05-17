package com.geektrust.backend.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import com.geektrust.backend.entities.Rider;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("RiderRepository Test")
public class RiderRepositoryTest {

    private RiderRepository riderRepository;

    @BeforeEach
    public void setUp() {
        riderRepository = new RiderRepository();
    }

    @Test
    @DisplayName("Save and Retrieve Rider")
    public void saveAndRetrieveRider() {
        // Arrange
        Rider rider = new Rider("R001", 10, 20);

        // Act
        Rider savedRider = riderRepository.save(rider);
        Optional<Rider> retrievedRider = riderRepository.findById("R001");

        // Assert
        assertTrue(retrievedRider.isPresent());
        assertEquals(rider, retrievedRider.get());
        assertEquals(rider, savedRider);
    }

    @Test
    @DisplayName("Find All Riders")
    public void findAllRiders() {
        // Arrange
        Rider rider1 = new Rider("R001", 10, 20);
        Rider rider2 = new Rider("R002", 15, 25);

        // Act
        riderRepository.save(rider1);
        riderRepository.save(rider2);
        List<Rider> allRiders = riderRepository.findAll();

        // Assert
        assertEquals(2, allRiders.size());
        assertTrue(allRiders.contains(rider1));
        assertTrue(allRiders.contains(rider2));
    }

    @Test
    @DisplayName("Find Rider By Id - Not Found")
    public void findRiderByIdNotFound() {
        // Act
        Optional<Rider> rider = riderRepository.findById("R001");

        // Assert
        assertFalse(rider.isPresent());
    }

    @AfterEach
    public void tearDown() {
        // Clearing data after each test
        riderRepository = null;
    }
}