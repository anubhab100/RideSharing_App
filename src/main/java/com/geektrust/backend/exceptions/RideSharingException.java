package com.geektrust.backend.exceptions;
public class RideSharingException extends RuntimeException {
    public RideSharingException(String message) {
        super(message);
    }

    public RideSharingException(String message, Throwable cause) {
        super(message, cause);
    }
}
