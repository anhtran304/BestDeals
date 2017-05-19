package com.company;

/**
 * Created on 18/5/17.
 */

// Part C - Section II - Customer Class
// Check unique customerID

public class Customer {
    private String customerID;
    private String customerName;
    private double customerPhone;

    public Customer(String customerID, String customerName, double customerPhone) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
    }

    public double getDiscount(double amount) {
        return amount;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getCustomerPhone() {
        return customerPhone;
    }
}
