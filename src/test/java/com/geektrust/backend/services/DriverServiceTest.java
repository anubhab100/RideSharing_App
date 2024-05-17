package com.geektrust.backend.services;

import com.geektrust.backend.entities.Driver;
import com.geektrust.backend.repositories.DriverRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DriverServiceTest {

    @Mock
    private DriverRepository driverRepository;

    @InjectMocks
    private DriverService driverService;

    @Test
    void testAddDriver() {
        // Given
        String driverId = "D001";
        int xCoordinate = 5;
        int yCoordinate = 10;

        // When
        driverService.addDriver(driverId, xCoordinate, yCoordinate);

        // Then
        verify(driverRepository, times(1)).save(any(Driver.class));
    }
}