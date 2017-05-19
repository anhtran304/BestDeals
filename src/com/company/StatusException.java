package com.company;

/**
 * Created on 14/5/17.
 */

public class StatusException extends Exception {
    private String status;

    public StatusException(String status) {
        this.status = status;
    }

    public String toString() {
        return this.status;
    }
}
