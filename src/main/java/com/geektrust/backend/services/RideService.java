package com.geektrust.backend.services;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import com.geektrust.backend.dto.BillData;
import com.geektrust.backend.dto.MatchResult;
import com.geektrust.backend.entities.Driver;
import com.geektrust.backend.entities.DriverStatus;
import com.geektrust.backend.entities.Ride;
import com.geektrust.backend.entities.RideStatus;
import com.geektrust.backend.entities.Rider;
import com.geektrust.backend.exceptions.RideSharingException;
import com.geektrust.backend.repositories.BillRepository;
import com.geektrust.backend.repositories.DriverRepository;
import com.geektrust.backend.repositories.RideRepository;
import com.geektrust.backend.repositories.RiderRepository;

public class RideService {
    private final DriverRepository driverRepository;
    private final RiderRepository riderRepository;
    private final RideRepository rideRepository;
    private final DistanceCalculatorService distanceCalculatorService;
    private final BillService billCalculatorService;

    public RideService(DriverRepository driverRepository, RiderRepository riderRepository, RideRepository rideRepository, BillService billCalculatorService, DistanceCalculatorService distanceCalculatorService) {
        this.driverRepository = driverRepository;
        this.riderRepository = riderRepository;
        this.rideRepository = rideRepository;
        this.distanceCalculatorService = distanceCalculatorService;
        this.billCalculatorService = billCalculatorService;
    }

    public MatchResult match(String riderId) {
        try {
            Rider rider = riderRepository.findById(riderId).orElse(null);
            if (rider == null) {
                return null;
            }

            List<Driver> availableDrivers = driverRepository.findAll().stream()
                .filter(driver -> isDriverAvailable(driver))
                .filter(driver -> {
                    double distance = distanceCalculatorService.calculateDistance(driver.getXCoordinate(), driver.getYCoordinate(), rider.getXCoordinate(), rider.getYCoordinate());
                    return distance <= 5;
                })
                .sorted(Comparator.comparing(driver -> distanceCalculatorService.calculateDistance(driver.getXCoordinate(), driver.getYCoordinate(), rider.getXCoordinate(), rider.getYCoordinate())))
                .collect(Collectors.toList());
            if (availableDrivers.isEmpty()) {
                return null;
            }

            List<String> matchedDriverIds = availableDrivers.stream().limit(5).map(Driver::getDriverId).collect(Collectors.toList());
            return new MatchResult(matchedDriverIds);
        } catch (Exception e) {
            throw new RideSharingException("Failed to match rider with drivers");
        }

    }

    public String startRide(String rideId, int n, String riderId) {
        try {
            Rider rider = riderRepository.findById(riderId).orElse(null);
            if (rider == null) {
                return "INVALID_RIDE";
            }

            MatchResult matchResult = match(riderId);
            if (matchResult == null || matchResult.getMatchedDriverIds().size() < n) {
                return "INVALID_RIDE";
            }

            String driverId = matchResult.getMatchedDriverIds().get(n - 1);
            Driver driver = driverRepository.findById(driverId).orElse(null);
            if (driver == null) {
                return "INVALID_RIDE";
            }

            Ride ride = new Ride(rideId, riderId, driverId);
            rideRepository.save(ride);
            return "RIDE_STARTED " + rideId;
        } catch (Exception e) {
            throw new RideSharingException("Failed to start ride", e);
        }
    }

    public String stopRide(String rideId, int destinationXCoordinate, int destinationYCoordinate, int timeTakenInMin) {
        try {
            Ride ride = rideRepository.findById(rideId).orElse(null);
            if (ride == null || ride.getStatus().equals(RideStatus.STOPPED)) {
                return "INVALID_RIDE";
            }

            Rider rider = riderRepository.findById(ride.getRiderId()).orElse(null);
            if (rider == null) {
                return "INVALID_RIDE";
            }

            Driver driver = driverRepository.findById(ride.getDriverId()).orElse(null);
            if (driver == null) {
                return "INVALID_RIDE";
            }

            double distance = distanceCalculatorService.calculateDistance(rider.getXCoordinate(), rider.getYCoordinate(), destinationXCoordinate, destinationYCoordinate);
            double amount = billCalculatorService.calculateBill(rider, driver, distance, timeTakenInMin);
            amount *= 1.20; // Adding 20% service tax
            amount = Math.round(amount * 100.0) / 100.0; // Round to 2 decimal places

            ride.setStatus(RideStatus.STOPPED);
            ride.setTimeTaken(timeTakenInMin);
            rideRepository.save(ride);
            BillData billData = new BillData(rideId, destinationXCoordinate, destinationYCoordinate, timeTakenInMin);
            BillRepository.saveBill(billData);
            return "RIDE_STOPPED " + rideId;
        } catch (Exception e) {
            throw new RideSharingException("Failed to stop ride", e);
        }
    }

    public String generateBill(String rideId) {
        try {
            Ride ride = rideRepository.findById(rideId).orElse(null);
            if (ride == null || ride.getStatus().equals(RideStatus.STARTED)) {
                return "INVALID_RIDE";
            }

            Rider rider = riderRepository.findById(ride.getRiderId()).orElse(null);
            if (rider == null) {
                return "INVALID_RIDE";
            }

            Driver driver = driverRepository.findById(ride.getDriverId()).orElse(null);
            if (driver == null) {
                return "INVALID_RIDE";
            }

            // Get bill data from the repository based on the rider ID
            List<BillData> billDataList = BillRepository.findByRiderId(rideId);

            if (billDataList == null || billDataList.isEmpty()) {
                return "INVALID_RIDE"; // Ride ID not found in the bill data
            }

            double totalDistance = 0;
            int totalTime = 0;

            // Iterate through bill data to calculate total distance and total time
            for (BillData billdata : billDataList) {
                totalDistance += distanceCalculatorService.calculateDistance(rider.getXCoordinate(), rider.getYCoordinate(), billdata.getDestinationXCoordinate(), billdata.getDestinationYCoordinate());
                totalTime += billdata.getTimeTakenInMin();
            }

            // Calculate the amount using the total distance and total time taken
            double amount = billCalculatorService.calculateBill(rider, driver, totalDistance, totalTime);
            return "BILL " + rideId + " " + driver.getDriverId() + " " + amount;
        } catch (Exception e) {
            throw new RideSharingException("Failed to generate bill", e);
        }
    }

    private boolean isDriverAvailable(Driver driver) {
        return driver.getStatus().equals(DriverStatus.AVAILABLE);
    }
}