package edu.phystech;

public class InvalidCoordinatesException extends Exception {
    InvalidCoordinatesException(String errorMessage) {
        super(errorMessage);
    }
}
