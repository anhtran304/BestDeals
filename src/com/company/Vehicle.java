package com.company;

import java.util.Date;

/**
 * Created on 9/5/17.
 */

public class Vehicle {
    private String ID;
    private String hirer;
    private String description;
    private String status;
    private double dailyRate;
    private double odometer;
    private Date dateHire;

    public Vehicle() {
        this("000000", "Default", 0, 0);
    }

    public Vehicle(String ID, String description, double dailyRate, double odometer) {
        this.ID = ID;
        this.description = description;
        this.status = "A";
        this.dailyRate = dailyRate;
        this.odometer = odometer;
    }


    public boolean hire(String hirerID) throws StatusException, OdometerException {
            if (this.status == "H") {
                throw new StatusException("Vehicle is being Hired");
            } else if (this.status == "S") {
                throw new StatusException("Vehicle is being Serviced");
            }
            else {
                this.hirer = hirerID;
                this.dateHire = DateTime.getCurrentTime();
                this.status = "H";
                return true;
            }
        }

    public boolean service() throws StatusException {
        if (this.status == "H") {
            throw new StatusException("Vehicle is being hired");
        } else if (this.status == "S") {
            throw new StatusException("Vehicle is being serviced");
        }
        else {
            this.status = "S";
            return true;
        }
    }

    public boolean serviceComplete(double odo) throws StatusException, OdometerException {
        if (this.status == "H") {
            throw new StatusException("Vehicle is being hired");
        } else if (this.status == "A") {
            throw new StatusException("Vehicle is not being serviced");
        } else if (odo < this.odometer) {
            throw new OdometerException("Odometer reading has to be more than " + this.odometer);
        }
        else if (odo > this.odometer) {
            this.odometer = odo;
            this.status = "A";
            }
        return true;
    }

    public double hireComplete(double odo) throws StatusException, OdometerException {
        double basedCharge = -1.0;
        if (this.status == "A" || this.status == "S") {
            throw new StatusException("Vehicle is not being hired");
        } else if (odo <= this.odometer) {
            throw new OdometerException("Odometer reading has to be more than " + this.odometer);
        } else if (odo > this.odometer) {
            basedCharge = this.dailyRate * DateTime.diffDays(DateTime.getCurrentTime(), this.dateHire);
            this.status = "A";
            this.odometer = odo;
        }
        return basedCharge;
    }

    public void print() {
        System.out.println("**********    VEHICLE DETAILS    **********");
        System.out.println(DateTime.getCurrentTime());
        System.out.println("Vehicle ID: " + this.ID + "   " + "Description: " + this.description + "   " + "Status: " + this.status + "   " + "Daily Rate: " + this.dailyRate + "   " + "Odometer Reading: " + this.odometer);
        if (status == "H") {
            System.out.println("Hirer: " + this.hirer + "   " + "Date/Time of hire: " + this.dateHire);
        }
    }

    public String convertToString() {
        String convertOutput;
        convertOutput = this.getID() + ", " + this.getDescription() + ", " + this.getDailyRate() + ", " + this.getOdometer();
        return convertOutput;
    }

    public String getID() {
        return ID;
    }

    public String getHirer() {
        return hirer;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public double getOdometer() {
        return odometer;
    }

    public Date getDateHire() {
        return dateHire;
    }
}
