package com.geektrust.backend.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.geektrust.backend.dto.BillData;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("BillRepository Test")
public class BillRepositoryTest {

    private BillRepository billRepository;

    @BeforeEach
    public void setUp() {
        billRepository = new BillRepository();
    }

    @Test
    @DisplayName("Save and Retrieve Bill Data")
    public void saveAndRetrieveBillData() {
        // Arrange
        BillData billData1 = new BillData("RIDE-001", 10, 20, 30);
        BillData billData2 = new BillData("RIDE-002", 15, 25, 35);

        // Act
        BillRepository.saveBill(billData1);
        BillRepository.saveBill(billData2);

        // Assert
        List<BillData> allBills = billRepository.getAllBills();
        assertEquals(2, allBills.size());
        assertEquals(billData1, allBills.get(0));
        assertEquals(billData2, allBills.get(1));
    }

    @AfterEach
    public void tearDown() {
        // Clearing data after each test
        billRepository = null;
    }
}