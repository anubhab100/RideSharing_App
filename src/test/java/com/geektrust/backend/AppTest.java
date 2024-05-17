package com.geektrust.backend;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("App Test")
class AppTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    private void provideInput(String data) {
        System.setIn(new ByteArrayInputStream(data.getBytes()));
    }
    @Test
    @DisplayName("Test Case 1")
    void runTestCase1() {
        // Arrange
        List<String> arguments = new ArrayList<>(List.of("INPUT_FILE=input.txt"));

        // Input
        String input = "ADD_DRIVER D1 1 1\n" +
                       "ADD_DRIVER D2 4 5\n" +
                       "ADD_DRIVER D3 2 2\n" +
                       "ADD_RIDER R1 0 0\n" +
                       "MATCH R1\n" +
                       "START_RIDE RIDE-001 2 R1\n" +
                       "STOP_RIDE RIDE-001 4 5 32\n" +
                       "BILL RIDE-001";

        // Act
        provideInput(input);
        App.main(arguments.toArray(new String[0]));

        // Assert
        String expectedOutput = "DRIVERS_MATCHED D3 D1\n"+
        "RIDE_STARTED RIDE-001\n"+
        "RIDE_STOPPED RIDE-001\n"+
        "BILL RIDE-001 D3 186.72";
        Assertions.assertEquals(expectedOutput.trim(), outputStreamCaptor.toString().trim());
    }

}