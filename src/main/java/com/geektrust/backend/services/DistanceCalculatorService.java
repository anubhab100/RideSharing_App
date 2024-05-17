package com.geektrust.backend.services;

public class DistanceCalculatorService {
    
    public double calculateDistance(int x1, int y1, int x2, int y2) {
        double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        return roundToTwoDecimalPlaces(distance);
    }

    private double roundToTwoDecimalPlaces(double value) {
        return Math.round(value * 100.0) / 100.0;
      
    }
}
