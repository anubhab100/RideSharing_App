package com.geektrust.backend.services;

import com.geektrust.backend.dto.MatchResult;
import com.geektrust.backend.entities.Driver;
import com.geektrust.backend.entities.Rider;
import com.geektrust.backend.repositories.DriverRepository;
import com.geektrust.backend.repositories.RideRepository;
import com.geektrust.backend.repositories.RiderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RideServiceTest {

    @Mock
    private DriverRepository driverRepository;

    @Mock
    private RiderRepository riderRepository;

    @Mock
    private RideRepository rideRepository;

    @Mock
    private DistanceCalculatorService distanceCalculatorService;

    @Mock
    private BillService billCalculatorService;

    @InjectMocks
    private RideService rideService;

    @Test
    void testMatch_Successful() {
        // Given
        String riderId = "R001";
        Rider rider = new Rider(riderId, 0, 0);
        when(riderRepository.findById(riderId)).thenReturn(java.util.Optional.of(rider));

        List<Driver> availableDrivers = new ArrayList<>();
        Driver driver1 = new Driver("D001", 5, 5);
        Driver driver2 = new Driver("D002", 10, 10);
        availableDrivers.add(driver1);
        availableDrivers.add(driver2);
        when(driverRepository.findAll()).thenReturn(availableDrivers);

        MatchResult expectedMatchResult = new MatchResult(List.of("D001", "D002"));

        // When
        MatchResult matchResult = rideService.match(riderId);

        // Then
        assertEquals(expectedMatchResult.getMatchedDriverIds(), matchResult.getMatchedDriverIds());
    }

    @Test
    void testMatch_NoAvailableDrivers() {
        // Given
        String riderId = "R001";
        Rider rider = new Rider(riderId, 0, 0);
        when(riderRepository.findById(riderId)).thenReturn(java.util.Optional.of(rider));
        when(driverRepository.findAll()).thenReturn(new ArrayList<>());

        // When
        MatchResult matchResult = rideService.match(riderId);

        // Then
        assertEquals(null, matchResult);
    }

}