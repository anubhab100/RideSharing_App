package com.geektrust.backend.commands;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

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

@DisplayName("BillCommandTest")
@ExtendWith(MockitoExtension.class)
public class BillCommandTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Mock
    RideService rideServiceMock;

    @InjectMocks
    BillCommand billCommand;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("execute method of BillCommand Should Print Bill to Console If Command Format is Valid")
    public void execute_ShouldPrintBill_GivenValidCommandFormat() {
        // Arrange
        String rideId = "R123";
        String expectedBill = "Bill for ride " + rideId + " is $100";
        List<String> tokens = List.of("BILL", rideId);
        // Mocking the RideService to return expected bill amount
        when(rideServiceMock.generateBill(rideId)).thenReturn(expectedBill);

        // Act
        billCommand.execute(tokens);

        // Assert
        // Verify that generateBill method of RideService is called exactly once with the expected rideId
        verify(rideServiceMock, times(1)).generateBill(rideId);
        // Verify that the expected bill is printed to the console
        Assertions.assertEquals(expectedBill, outputStreamCaptor.toString().trim());
    }

    @Test
    @DisplayName("execute method of BillCommand Should Print Error Message To Console If Command Format is Invalid")
    public void execute_ShouldPrintErrorMessage_GivenInvalidCommandFormat() {
        // Arrange
        List<String> tokens = List.of("BILL"); // Invalid command format

        // Act
        billCommand.execute(tokens);

        // Assert
        // Verify that no method of RideService is called
        verify(rideServiceMock, times(0)).generateBill(null);
        // Verify that an error message is printed to the console
        Assertions.assertEquals("Invalid command format: " + tokens, outputStreamCaptor.toString().trim());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
