package com.rodolfodev.bookingsystem.exceptions;

public class BookingConflictException extends RuntimeException {

    public BookingConflictException(String message) {
        super(message);
    }
}
