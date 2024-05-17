package com.geektrust.backend.services;

import com.geektrust.backend.entities.Rider;
import com.geektrust.backend.repositories.RiderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RiderServiceTest {

    @Mock
    private RiderRepository riderRepository;

    @InjectMocks
    private RiderService riderService;

    @Test
    void testAddRider() {
        // Given
        String riderId = "R001";
        int xCoordinate = 8;
        int yCoordinate = 12;

        // When
        riderService.addRider(riderId, xCoordinate, yCoordinate);

        // Then
        verify(riderRepository, times(1)).save(any(Rider.class));
    }
}