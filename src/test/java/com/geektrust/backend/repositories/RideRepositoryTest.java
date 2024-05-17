package com.geektrust.backend.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import com.geektrust.backend.entities.Ride;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("RideRepository Test")
public class RideRepositoryTest {

    private RideRepository rideRepository;

    @BeforeEach
    public void setUp() {
        rideRepository = new RideRepository();
    }

    @Test
    @DisplayName("Save and Retrieve Ride")
    public void saveAndRetrieveRide() {
        // Arrange
        Ride ride = new Ride("R001", "Rider001", "Driver001");

        // Act
        Ride savedRide = rideRepository.save(ride);
        Optional<Ride> retrievedRide = rideRepository.findById("R001");

        // Assert
        assertTrue(retrievedRide.isPresent());
        assertEquals(ride, retrievedRide.get());
        assertEquals(ride, savedRide);
    }

    @Test
    @DisplayName("Find All Rides")
    public void findAllRides() {
        // Arrange
        Ride ride1 = new Ride("R001", "Rider001", "Driver001");
        Ride ride2 = new Ride("R002", "Rider002", "Driver002");

        // Act
        rideRepository.save(ride1);
        rideRepository.save(ride2);
        List<Ride> allRides = rideRepository.findAll();

        // Assert
        assertEquals(2, allRides.size());
        assertTrue(allRides.contains(ride1));
        assertTrue(allRides.contains(ride2));
    }

    @Test
    @DisplayName("Find Ride By Id - Not Found")
    public void findRideByIdNotFound() {
        // Act
        Optional<Ride> ride = rideRepository.findById("R001");

        // Assert
        assertFalse(ride.isPresent());
    }

    @AfterEach
    public void tearDown() {
        // Clearing data after each test
        rideRepository = null;
    }
}
