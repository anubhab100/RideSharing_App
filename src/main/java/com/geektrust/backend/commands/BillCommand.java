package com.geektrust.backend.commands;

import java.util.List;
import com.geektrust.backend.services.RideService;

public class BillCommand implements ICommand {
    private final RideService rideService;

    public BillCommand(RideService rideService) {
        this.rideService = rideService;
    }

    @Override
    public void execute(List<String> tokens) {
        if (tokens.size() < 2) {
            System.out.println("Invalid command format: " + tokens);
            return;
        }
        String rideId = tokens.get(1);
        System.out.println(rideService.generateBill(rideId));
    }
}
