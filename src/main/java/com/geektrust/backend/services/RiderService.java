package com.geektrust.backend.services;

import com.geektrust.backend.entities.Rider;
import com.geektrust.backend.repositories.RiderRepository;

public class RiderService {
    private final RiderRepository riderRepository;

    public RiderService(RiderRepository riderRepository) {
        this.riderRepository = riderRepository;
    }

    public void addRider(String riderId, int xCoordinate, int yCoordinate) {
        Rider rider = new Rider(riderId, xCoordinate, yCoordinate);
        riderRepository.save(rider);
    }

    // Add other methods related to riders here
}
