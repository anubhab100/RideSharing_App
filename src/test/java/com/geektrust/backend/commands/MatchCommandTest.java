package com.geektrust.backend.commands;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.geektrust.backend.dto.MatchResult;
import com.geektrust.backend.exceptions.NoAvailableDriversException;
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

@DisplayName("MatchCommandTest")
@ExtendWith(MockitoExtension.class)
public class MatchCommandTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Mock
    RideService rideServiceMock;

    @InjectMocks
    MatchCommand matchCommand;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    // @Test
    // @DisplayName("execute method of MatchCommand Should Print Matched Drivers To Console If Match is Successful")
    // public void execute_ShouldPrintMatchedDrivers_GivenSuccessfulMatch() {
    //     // Arrange
    //     String riderId = "R123";
    //     MatchResult matchResult = new MatchResult(List.of("D1", "D2", "D3"));
    //     when(rideServiceMock.match(riderId)).thenReturn(matchResult);
    
    //     // Expected output
    //     StringBuilder expectedOutput = new StringBuilder("DRIVERS_MATCHED ");
    
    //     // Create a copy of the list for sorting (avoids modifying the original)
    //     List<String> matchedDriverIds = new ArrayList<>(matchResult.getMatchedDriverIds());
    //     List<String> sortedDriverIds  = new ArrayList<>();
    //     sortedDriverIds.addAll(matchedDriverIds);
      
    //     Collections.sort(sortedDriverIds, new Comparator<String>() {
    
    //         @Override
    //         public int compare(String driverId1, String driverId2) {
    //             // Extract numeric parts
    //             int id1 = Integer.parseInt(driverId1.substring(1));
    //             int id2 = Integer.parseInt(driverId2.substring(1));
    
    //             // Reverse comparison logic for descending order
    //             return id2 - id1; // Compare numeric parts with reversed order
    //         }
    //     });
    
    //     // Build expected output using the sorted list
    //     for (String driverId : sortedDriverIds) {
    //         expectedOutput.append(driverId);
    //         expectedOutput.append(" ");
    //     }
    
    //     // Act
    //     matchCommand.execute(List.of("MATCH", riderId));
    
    //     // Assert
    //     verify(rideServiceMock, times(1)).match(riderId);
    //     Assertions.assertEquals(expectedOutput.toString().trim(), outputStreamCaptor.toString().trim());
    // }
  

    @Test
    @DisplayName("execute method of MatchCommand Should Print Error Message To Console If No Available Drivers")
    public void execute_ShouldPrintErrorMessage_GivenNoAvailableDrivers() {
        // Arrange
        String riderId = "R123";
        when(rideServiceMock.match(riderId)).thenThrow(new NoAvailableDriversException("NO_DRIVERS_AVAILABLE"));

        // Act
        matchCommand.execute(List.of("MATCH", riderId));

        // Assert
        verify(rideServiceMock, times(1)).match(riderId);
        Assertions.assertEquals("NO_DRIVERS_AVAILABLE", outputStreamCaptor.toString().trim());
    }

    @Test
    @DisplayName("execute method of MatchCommand Should Print Error Message To Console If Command Format is Invalid")
    public void execute_ShouldPrintErrorMessage_GivenInvalidCommandFormat() {
        // Act
        matchCommand.execute(List.of("MATCH")); // Invalid command format

        // Assert
        verify(rideServiceMock, times(0)).match(null);
        Assertions.assertEquals("Invalid command format: [MATCH]", outputStreamCaptor.toString().trim());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
