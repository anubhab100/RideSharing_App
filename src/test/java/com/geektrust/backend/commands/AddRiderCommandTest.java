package com.geektrust.backend.commands;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doNothing;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import com.geektrust.backend.services.RiderService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("AddRiderCommandTest")
@ExtendWith(MockitoExtension.class)
public class AddRiderCommandTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Mock
    RiderService riderServiceMock;

    @InjectMocks
    AddRiderCommand addRiderCommand;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("execute method of AddRiderCommand Should Add Rider If Command Format is Valid")
    public void execute_ShouldAddRider_GivenValidCommandFormat() {
        // Arrange
        String riderId = "R123";
        int xCoordinate = 10;
        int yCoordinate = 20;
        List<String> tokens = List.of("ADD-RIDER", riderId, Integer.toString(xCoordinate), Integer.toString(yCoordinate));
        // Mocking the RiderService to do nothing (i.e., simulate successful addition of rider)
        doNothing().when(riderServiceMock).addRider(riderId, xCoordinate, yCoordinate);

        // Act
        addRiderCommand.execute(tokens);

        // Assert
        // Verify that addRider method of RiderService is called exactly once with the expected arguments
        verify(riderServiceMock, times(1)).addRider(riderId, xCoordinate, yCoordinate);
        // Verify that no error message is printed to the console
        Assertions.assertTrue(outputStreamCaptor.toString().trim().isEmpty());
    }

    @Test
    @DisplayName("execute method of AddRiderCommand Should Print Error Message To Console If Command Format is Invalid")
    public void execute_ShouldPrintErrorMessage_GivenInvalidCommandFormat() {
        // Arrange
        List<String> tokens = List.of("ADD-RIDER"); // Invalid command format

        // Act
        addRiderCommand.execute(tokens);

        // Assert
        // Verify that no method of RiderService is called
        verify(riderServiceMock, times(0)).addRider(null, 0, 0);
        // Verify that an error message is printed to the console
        Assertions.assertEquals("Invalid command format: " + tokens, outputStreamCaptor.toString().trim());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}

