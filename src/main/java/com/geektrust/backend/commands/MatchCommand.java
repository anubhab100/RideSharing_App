package com.geektrust.backend.commands;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import com.geektrust.backend.dto.MatchResult;
import com.geektrust.backend.exceptions.NoAvailableDriversException;
import com.geektrust.backend.services.RideService;

public class MatchCommand implements ICommand {
    private final RideService rideService;

    public MatchCommand(RideService rideService) {
        this.rideService = rideService;
    }

    @Override
    public void execute(List<String> tokens) {
     // Extract parameters from tokens and call corresponding service method
     if (tokens.size() < 2) {
        System.out.println("Invalid command format: " + tokens);
        return;
    }
    String riderId = tokens.get(1);
    try {
        MatchResult matchResult = rideService.match(riderId);
        if (matchResult == null || matchResult.getMatchedDriverIds().isEmpty()) {
            throw new NoAvailableDriversException("NO_DRIVERS_AVAILABLE");
        } else {
            List<String> matchedDriverIds = matchResult.getMatchedDriverIds();
            Collections.sort(matchedDriverIds, new Comparator<String>() {
                @Override
                public int compare(String driverId1, String driverId2) {
                    // Extract numeric parts
                    int id1 = Integer.parseInt(driverId1.substring(1));
                    int id2 = Integer.parseInt(driverId2.substring(1));
        
                    // Reverse comparison logic for descending order
                    return id2 - id1; // Compare numeric parts with reversed order
                }
            });
            StringBuilder output = new StringBuilder("DRIVERS_MATCHED ");
            for (int i = 0; i < matchedDriverIds.size(); i++) {
                output.append(matchedDriverIds.get(i));
                if (i < matchedDriverIds.size() - 1) {
                    output.append(" "); // Add space as a delimiter between driver IDs
                }
            }
            System.out.println(output.toString());
        }
    } catch (NoAvailableDriversException e) {
        System.out.println(e.getMessage());
    }
    }

}
