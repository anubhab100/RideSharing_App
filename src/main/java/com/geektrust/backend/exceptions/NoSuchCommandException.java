package com.geektrust.backend.exceptions;
public class NoSuchCommandException extends RideSharingException {
    public NoSuchCommandException(String message) {
        super(message);
    }

    public NoSuchCommandException(String message, Throwable cause) {
        super(message, cause);
    }
}