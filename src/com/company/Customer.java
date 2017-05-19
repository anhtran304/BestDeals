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

    public Customer() {
        this("C000000", "Default Name", "0449159695");
    }

    public Customer(String customerID, String customerName, String customerPhone) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
    }

    public String convertToString() {
        String convertOutput;
        convertOutput = this.customerID + ", " + this.customerName + ", " + this.customerPhone + ", ";
        return convertOutput;
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

    public String getCustomerPhone() {
        return customerPhone;
    }
}
