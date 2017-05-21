package com.company;

/**
 * Created on 18/5/17.
 */

// Corporate customers are allowed to hire as many vehicles as required.
// DiscountRate input by percent, E.g: 10 -> 10%;

public class CCustomer extends Customer{
    private double discountRate;

    public CCustomer(String customerID, String customerName, String customerPhone, double discountRate) {
        super(customerID, customerName, customerPhone);
        this.discountRate = discountRate;
    }

    public CCustomer(String customerID, String customerName, String customerPhone, double pastMileage, double discountRate) {
        super(customerID, customerName, customerPhone, pastMileage);
        this.discountRate = discountRate;
    }

    @Override
    public String convertToString() {
        return super.convertToString() + ", " + this.discountRate;
    }

    @Override
    public double getDiscount(double amount) {
        double discountAmount;
        if (this.discountRate != 0) {
            discountAmount = amount * this.discountRate;
        } else {
            discountAmount = super.getDiscount(amount);
        }
        return discountAmount;
    }

    @Override
    public boolean isCor() {
        return true;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(float discountRate) {
        this.discountRate = discountRate;
    }
}
