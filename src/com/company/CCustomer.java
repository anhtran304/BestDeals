package com.company;

/**
 * Created on 18/5/17.
 */

// Corporate customers are allowed to hire as many vehicles as required.
// DiscountRate input by percent, E.g: 10 -> 10%;

public class CCustomer extends Customer{
    private float discountRate;

    public CCustomer(String customerID, String customerName, String customerPhone, float discountRate) {
        super(customerID, customerName, customerPhone);
        this.discountRate = discountRate;
    }

    @Override
    public String convertToString() {
        return super.convertToString() + this.discountRate;
    }

    @Override
    public double getDiscount(double amount) {
        double copDiscountAmount;
        copDiscountAmount = amount * this.discountRate;
        return copDiscountAmount;
    }

    public float getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(float discountRate) {
        this.discountRate = discountRate;
    }
}
