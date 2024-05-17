package com.geektrust.backend.repositories;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.geektrust.backend.entities.Driver;

public class DriverRepository {
    private final Map<String, Driver> driverMap;

    public DriverRepository() {
        this.driverMap = new HashMap<>();
    }

    public Driver save(Driver driver) {
        driverMap.put(driver.getDriverId(), driver);
        // System.out.println(driver);
        return driver;
    }

    public List<Driver> findAll() {
        return driverMap.values().stream().collect(Collectors.toList());
    }

    public Optional<Driver> findById(String driverId) {
        return Optional.ofNullable(driverMap.get(driverId));
    }
}
