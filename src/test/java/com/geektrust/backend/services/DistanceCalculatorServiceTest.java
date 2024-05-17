package com.geektrust.backend.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DistanceCalculatorServiceTest {

    @Test
    void testCalculateDistance() {
        DistanceCalculatorService calculator = new DistanceCalculatorService();

        // Test case 1: (0, 0) to (3, 4)
        assertEquals(5.0, calculator.calculateDistance(0, 0, 3, 4));

        // Test case 2: (1, 1) to (4, 5)
        assertEquals(5.0, calculator.calculateDistance(1, 1, 4, 5));

        // Test case 3: (0, 0) to (0, 0)
        assertEquals(0.0, calculator.calculateDistance(0, 0, 0, 0));
    }

}
