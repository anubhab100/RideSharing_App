package com.geektrust.backend.commands;

import java.util.List;
import com.geektrust.backend.services.RiderService;

public class AddRiderCommand implements ICommand {
    private final RiderService riderService;

    public AddRiderCommand(RiderService riderService) {
        this.riderService = riderService;
    }

    @Override
    public void execute(List<String> tokens) {
        // Extracting parameters from tokens and calling corresponding service method
        if (tokens.size() < 4) {
            System.out.println("Invalid command format: " + tokens);
            return;
        }
        String riderId = tokens.get(1);
        int xCoordinate = Integer.parseInt(tokens.get(2));
        int yCoordinate = Integer.parseInt(tokens.get(3));
        riderService.addRider(riderId, xCoordinate, yCoordinate);
    }

}

