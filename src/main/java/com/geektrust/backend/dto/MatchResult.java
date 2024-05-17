package com.geektrust.backend.dto;

import java.util.List;

public class MatchResult {
    private final List<String> matchedDriverIds;

    public MatchResult(List<String> matchedDriverIds) {
        this.matchedDriverIds = matchedDriverIds;
    }

    public List<String> getMatchedDriverIds() {
        return matchedDriverIds;
    }

    @Override
    public String toString() {
        return "MatchResult [matchedDriverIds=" + matchedDriverIds + "]";
    }
}