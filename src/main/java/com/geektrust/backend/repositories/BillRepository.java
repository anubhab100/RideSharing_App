package com.geektrust.backend.repositories;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.geektrust.backend.dto.BillData;

public class BillRepository {
    private static Map<String, List<BillData>> billMap;

    public BillRepository() {
        billMap = new HashMap<>();
    }

    public static void saveBill(BillData billData) {
        String riderId = billData.getRideId();
        List<BillData> riderBills = billMap.getOrDefault(riderId, new ArrayList<>());
        riderBills.add(billData);
        billMap.put(riderId, riderBills);
    }

    public List<BillData> getAllBills() {
        List<BillData> allBills = new ArrayList<>();
        for (List<BillData> riderBills : billMap.values()) {
            allBills.addAll(riderBills);
        }
        return allBills;
    }

    public static List<BillData> findByRiderId(String riderId) {
        return billMap.getOrDefault(riderId, new ArrayList<>());
    }
}

    // Additional methods can be added as per requirements, such as finding bills by ID, etc.
