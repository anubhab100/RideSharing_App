package com.geektrust.backend.repositories;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.geektrust.backend.entities.Ride;

public class RideRepository {
    private final Map<String, Ride> rideMap;

    public RideRepository() {
        this.rideMap = new HashMap<>();
    }

    public Ride save(Ride ride) {
        rideMap.put(ride.getRideId(), ride);
        return ride;
     
    }

    public List<Ride> findAll() {
        return rideMap.values().stream().collect(Collectors.toList());
    }

    public Optional<Ride> findById(String rideId) {
        return Optional.ofNullable(rideMap.get(rideId));
    }
}
