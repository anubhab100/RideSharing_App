package com.geektrust.backend.commands;

import java.util.List;
import com.geektrust.backend.exceptions.RideSharingException;
import com.geektrust.backend.services.RideService;

public class StopRideCommand implements ICommand {
    private final RideService rideService;

    public StopRideCommand(RideService rideService) {
        this.rideService = rideService;
    }

    @Override
    public void execute(List<String> tokens) {
        // Extract parameters from tokens and call corresponding service method
        if (tokens.size() < 5) {
            System.out.println("Invalid command format: " + tokens);
            return;
        }
        String rideId = tokens.get(1);
        int destinationXCoordinate = Integer.parseInt(tokens.get(2));
        int destinationYCoordinate = Integer.parseInt(tokens.get(3));
        int timeTakenInMin = Integer.parseInt(tokens.get(4));
        try {
            System.out.println(rideService.stopRide(rideId, destinationXCoordinate, destinationYCoordinate, timeTakenInMin));
        } catch (RideSharingException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }
}
