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

@DisplayName("StopRideCommandTest")
@ExtendWith(MockitoExtension.class)
public class StopRideCommandTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Mock
    RideService rideServiceMock;

    @InjectMocks
    StopRideCommand stopRideCommand;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("execute method of StopRideCommand Should Print Stop Message To Console If Ride Stops Successfully")
    public void execute_ShouldPrintStopMessage_GivenSuccessfulRideStop() {
        // Arrange
        String rideId = "R123";
        int destinationXCoordinate = 20;
        int destinationYCoordinate = 30;
        int timeTakenInMin = 15;
        String expectedMessage = "Ride " + rideId + " stopped at (" + destinationXCoordinate + "," + destinationYCoordinate + ") in " + timeTakenInMin + " minutes";
        when(rideServiceMock.stopRide(rideId, destinationXCoordinate, destinationYCoordinate, timeTakenInMin)).thenReturn(expectedMessage);

        // Act
        stopRideCommand.execute(List.of("STOP-RIDE", rideId, Integer.toString(destinationXCoordinate), Integer.toString(destinationYCoordinate), Integer.toString(timeTakenInMin)));

        // Assert
        verify(rideServiceMock, times(1)).stopRide(rideId, destinationXCoordinate, destinationYCoordinate, timeTakenInMin);
        Assertions.assertEquals(expectedMessage, outputStreamCaptor.toString().trim());
    }

    @Test
    @DisplayName("execute method of StopRideCommand Should Print Error Message To Console If Ride Sharing Exception Occurs")
    public void execute_ShouldPrintErrorMessage_GivenRideSharingException() {
        // Arrange
        String rideId = "R123";
        int destinationXCoordinate = 20;
        int destinationYCoordinate = 30;
        int timeTakenInMin = 15;
        String errorMessage = "Ride " + rideId + " has already stopped";
        when(rideServiceMock.stopRide(rideId, destinationXCoordinate, destinationYCoordinate, timeTakenInMin)).thenThrow(new RideSharingException(errorMessage));

        // Act
        stopRideCommand.execute(List.of("STOP-RIDE", rideId, Integer.toString(destinationXCoordinate), Integer.toString(destinationYCoordinate), Integer.toString(timeTakenInMin)));

        // Assert
        verify(rideServiceMock, times(1)).stopRide(rideId, destinationXCoordinate, destinationYCoordinate, timeTakenInMin);
        Assertions.assertEquals("Error occurred: " + errorMessage, outputStreamCaptor.toString().trim());
    }

    @Test
    @DisplayName("execute method of StopRideCommand Should Print Error Message To Console If Command Format is Invalid")
    public void execute_ShouldPrintErrorMessage_GivenInvalidCommandFormat() {
        // Act
        stopRideCommand.execute(List.of("STOP-RIDE")); // Invalid command format

        // Assert
        verify(rideServiceMock, times(0)).stopRide(null, 0, 0, 0);
        Assertions.assertEquals("Invalid command format: [STOP-RIDE]", outputStreamCaptor.toString().trim());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
