package com.geektrust.backend.commands;

import java.util.List;
import com.geektrust.backend.exceptions.RideSharingException;
import com.geektrust.backend.services.RideService;

public class StartRideCommand implements ICommand {
    private final RideService rideService;

    public StartRideCommand(RideService rideService) {
        this.rideService = rideService;
    }

    @Override
    public void execute(List<String> tokens) {
        // Extract parameters from tokens and call corresponding service method
        if (tokens.size() < 4) {
            System.out.println("Invalid command format: " + tokens);
            return;
        }
        String rideId = tokens.get(1);
        int n = Integer.parseInt(tokens.get(2));
        String riderId = tokens.get(3);
        try {
            System.out.println(rideService.startRide(rideId, n, riderId));
        } catch (RideSharingException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }
}
