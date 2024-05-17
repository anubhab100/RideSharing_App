package com.geektrust.backend.commands;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import com.geektrust.backend.exceptions.RideSharingException;
import com.geektrust.backend.services.RideService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("StartRideCommandTest")
@ExtendWith(MockitoExtension.class)
public class StartRideCommandTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Mock
    RideService rideServiceMock;

    @InjectMocks
    StartRideCommand startRideCommand;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("execute method of StartRideCommand Should Print Start Message To Console If Ride Starts Successfully")
    public void execute_ShouldPrintStartMessage_GivenSuccessfulRideStart() {
        // Arrange
        String rideId = "R123";
        int n = 2;
        String riderId = "R456";
        String expectedMessage = "Ride " + rideId + " started with " + n + " riders assigned to rider " + riderId;
        when(rideServiceMock.startRide(rideId, n, riderId)).thenReturn(expectedMessage);

        // Act
        startRideCommand.execute(List.of("START-RIDE", rideId, Integer.toString(n), riderId));

        // Assert
        verify(rideServiceMock, times(1)).startRide(rideId, n, riderId);
        Assertions.assertEquals(expectedMessage, outputStreamCaptor.toString().trim());
    }

    @Test
    @DisplayName("execute method of StartRideCommand Should Print Error Message To Console If Ride Sharing Exception Occurs")
    public void execute_ShouldPrintErrorMessage_GivenRideSharingException() {
        // Arrange
        String rideId = "R123";
        int n = 2;
        String riderId = "R456";
        String errorMessage = "No available drivers for ride " + rideId;
        when(rideServiceMock.startRide(rideId, n, riderId)).thenThrow(new RideSharingException(errorMessage));

        // Act
        startRideCommand.execute(List.of("START-RIDE", rideId, Integer.toString(n), riderId));

        // Assert
        verify(rideServiceMock, times(1)).startRide(rideId, n, riderId);
        Assertions.assertEquals("Error occurred: " + errorMessage, outputStreamCaptor.toString().trim());
    }

    @Test
    @DisplayName("execute method of StartRideCommand Should Print Error Message To Console If Command Format is Invalid")
    public void execute_ShouldPrintErrorMessage_GivenInvalidCommandFormat() {
        // Act
        startRideCommand.execute(List.of("START-RIDE")); // Invalid command format

        // Assert
        verify(rideServiceMock, times(0)).startRide(null, 0, null);
        Assertions.assertEquals("Invalid command format: [START-RIDE]", outputStreamCaptor.toString().trim());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
