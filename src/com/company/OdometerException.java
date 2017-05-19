package com.company;

/**
 * Created on 14/5/17.
 */
public class OdometerException extends Exception {
    private String message;

    public OdometerException(String message) {
        this.message = message;
    }

    public String toString() {
        return this.message;
    }
}
