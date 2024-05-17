package com.geektrust.backend.services;

import com.geektrust.backend.entities.Driver;
import com.geektrust.backend.repositories.DriverRepository;

public class  DriverService {
    private final DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public void addDriver(String driverId, int xCoordinate, int yCoordinate) {
        Driver driver = new Driver(driverId, xCoordinate, yCoordinate);
        driverRepository.save(driver);
    }

}
