package edu.phystech;

public class IllegalMoveException extends Exception {
    IllegalMoveException(String errorMessage) {
        super(errorMessage);
    }
}
