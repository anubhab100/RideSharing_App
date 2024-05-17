package com.geektrust.backend.services;
import java.util.List;
import com.geektrust.backend.dto.BillData;
import com.geektrust.backend.entities.Driver;
import com.geektrust.backend.entities.Rider;
import com.geektrust.backend.exceptions.RideSharingException;
import com.geektrust.backend.repositories.BillRepository;

public class BillService {

    private final BillRepository billRepository;

    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }
    public void saveBill(BillData billData) {
        try {
            BillRepository.saveBill(billData);
        } catch (Exception e) {
            throw new RideSharingException("Failed to save bill data", e);
        }
    }

    public List<BillData> getAllBills() {
        try {
            return billRepository.getAllBills();
        } catch (Exception e) {
            throw new RideSharingException("Failed to retrieve all bills", e);
        }
    }
    
    public double calculateBill(Rider rider, Driver driver, double distance,int timeTakenInMin) {
        double baseFare = 50; // Base fare for every ride
        double distanceCharge = 6.5 * distance; // Additional charge for distance traveled
        double timeCharge = 2 * timeTakenInMin; // Additional charge for time taken
    
        // Calculating the total amount without tax
        double totalAmount = baseFare + distanceCharge + timeCharge;
    
        // Applying service tax (20%)
        double amountWithTax = totalAmount * 1.20;
    
        // Round to 2 decimal places
       return Math.round(amountWithTax * 100.00) / 100.00;
    }
    
}
