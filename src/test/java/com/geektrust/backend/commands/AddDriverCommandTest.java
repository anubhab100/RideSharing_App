package com.geektrust.backend.commands;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import com.geektrust.backend.exceptions.DriverNotFoundException;
import com.geektrust.backend.services.DriverService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("AddDriverCommandTest")
@ExtendWith(MockitoExtension.class)
public class AddDriverCommandTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Mock
    DriverService driverServiceMock;

    @InjectMocks
    AddDriverCommand addDriverCommand;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("execute method of AddDriverCommand Should Print Error Message To Console If Driver Not Found")
    public void execute_ShouldPrintErrorMessage_GivenDriverNotFound() {
        // Arrange
        String driverId = "D123";
        int xCoordinate = 10;
        int yCoordinate = 20;
        String expectedErrorMessage = "Driver not found for id: " + driverId;
        doThrow(new DriverNotFoundException(expectedErrorMessage)).when(driverServiceMock).addDriver(driverId,
                xCoordinate, yCoordinate);

        // Act
        addDriverCommand.execute(List.of("ADD-DRIVER", driverId, Integer.toString(xCoordinate),
                Integer.toString(yCoordinate)));

        // Assert
        Assertions.assertEquals("Error occurred: " + expectedErrorMessage, outputStreamCaptor.toString().trim());
        verify(driverServiceMock, times(1)).addDriver(driverId, xCoordinate, yCoordinate);
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}