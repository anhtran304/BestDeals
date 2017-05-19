package com.company;

/**
 * Created on 18/5/17.
 */

// Individual customers are allowed only one car for hire at any one time.

public class ICustomer extends Customer {
    private double pastMileage;

    public ICustomer(String customerID, String customerName, String customerPhone, double pastMileage) {
        super(customerID, customerName, customerPhone);
        this.pastMileage = 1;
    }


    @Override
    public String convertToString() {
        return super.convertToString() + this.pastMileage;
    }

    @Override
    public double getDiscount(double amount) {
        double discountAmout = 0;
        if (this.pastMileage >= 100000 && this.pastMileage <= 200000) {
            discountAmout = amount * 0.1;
        } else if (this.pastMileage > 200000) {
            discountAmout = amount * 0.2;
        }
        return discountAmout;
    }
}
