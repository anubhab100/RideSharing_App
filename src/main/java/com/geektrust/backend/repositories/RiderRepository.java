package com.geektrust.backend.repositories;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.geektrust.backend.entities.Rider;

public class RiderRepository {
    private final Map<String, Rider> riderMap;

    public RiderRepository() {
        this.riderMap = new HashMap<>();
    }

    public Rider save(Rider rider) {
        riderMap.put(rider.getRiderId(), rider);
        return rider;
    }

    public List<Rider> findAll() {
        return riderMap.values().stream().collect(Collectors.toList());
    }

    public Optional<Rider> findById(String riderId) {
        return Optional.ofNullable(riderMap.get(riderId));
    }
}
