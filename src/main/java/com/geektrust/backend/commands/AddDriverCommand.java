package com.geektrust.backend.commands;

import java.util.List;
import com.geektrust.backend.exceptions.DriverNotFoundException;
import com.geektrust.backend.services.DriverService;

public class AddDriverCommand implements ICommand {
    private final DriverService driverService;

    public AddDriverCommand(DriverService driverService) {
        this.driverService = driverService;
    }

    @Override
    public void execute(List<String> tokens) {
        // Extracting parameters from tokens and calling corresponding service method
        if (tokens.size() < 4) {
            System.out.println("Invalid command format: " + tokens);
            return;
        }
        String driverId = tokens.get(1);
        int xCoordinate = Integer.parseInt(tokens.get(2));
        int yCoordinate = Integer.parseInt(tokens.get(3));
        try {
            driverService.addDriver(driverId, xCoordinate, yCoordinate);
        } catch (DriverNotFoundException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }
    }

