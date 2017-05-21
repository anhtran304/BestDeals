package com.company;

/**
 * Created on 18/5/17.
 */

// Part C - Section II - Customer Class
// Check unique customerID

public class Customer {
    private String customerID;
    private String customerName;
    private String customerPhone;
    private double pastMileage;

    public Customer() {
        this("C12345", "Default", "0400000000", 0);
    }

    public Customer(String customerID, String customerName, String customerPhone) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.pastMileage = 0;
    }

    public Customer(String customerID, String customerName, String customerPhone, double pastMileage) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.pastMileage = pastMileage;
    }

    public String convertToString() {
        String convertOutput;
        convertOutput = this.customerID + ", " + this.customerName + ", " + this.customerPhone + ", " + this.pastMileage;
        return convertOutput;
    }

    public double getDiscount(double amount) {
        double discountAmout = 0;
        if (this.pastMileage >= 100000 && this.pastMileage <= 200000) {
            discountAmout = amount * 0.1;
        } else if (this.pastMileage > 200000) {
            discountAmout = amount * 0.2;
        }
        return discountAmout;
    }

    public boolean isCor() {
        return false;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public double getPastMileage() {
        return pastMileage;
    }

    public void setPastMileage(double newMileage) {
        this.pastMileage = this.pastMileage + newMileage;
    }
}
