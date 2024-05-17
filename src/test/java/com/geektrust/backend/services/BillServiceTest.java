package com.geektrust.backend.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.geektrust.backend.dto.BillData;
import com.geektrust.backend.entities.Driver;
import com.geektrust.backend.entities.Rider;
import com.geektrust.backend.exceptions.RideSharingException;
import com.geektrust.backend.repositories.BillRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("BillService Test")
public class BillServiceTest {

    private BillService billService;
    private BillRepository billRepository;

    @BeforeEach
    public void setUp() {
        billRepository = mock(BillRepository.class);
        billService = new BillService(billRepository);
    }

    @Test
    @DisplayName("Get All Bills")
    public void getAllBills() {
        // Arrange
        List<BillData> expectedBills = new ArrayList<>();
        // Mocking the behavior of the bill repository
        when(billRepository.getAllBills()).thenReturn(expectedBills);

        // Act
        List<BillData> actualBills = billService.getAllBills();

        // Assert
        assertEquals(expectedBills, actualBills);
    }

    @Test
    @DisplayName("Get All Bills - Exception")
    public void getAllBills_Exception() {
        // Mocking the behavior of the bill repository to throw an exception
        when(billRepository.getAllBills()).thenThrow(new RuntimeException());

        // Act & Assert
        RideSharingException exception = org.junit.jupiter.api.Assertions.assertThrows(RideSharingException.class,
                () -> billService.getAllBills());
        assertEquals("Failed to retrieve all bills", exception.getMessage());
    }

    @Test
    @DisplayName("Calculate Bill")
    public void calculateBill() {
        // Arrange
        Rider rider = new Rider("R001", 0, 0);
        Driver driver = new Driver("D001", 0, 0);
        double distance = 10.0;
        int timeTakenInMin = 30;
        double expectedAmount = 50 + (6.5 * distance) + (2 * timeTakenInMin);
        expectedAmount *= 1.20;
        expectedAmount = Math.round(expectedAmount * 100.0) / 100.0;

        // Act
        double actualAmount = billService.calculateBill(rider, driver, distance, timeTakenInMin);

        // Assert
        assertEquals(expectedAmount, actualAmount);
    }
}
