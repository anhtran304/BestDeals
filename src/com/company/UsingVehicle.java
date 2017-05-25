package com.company;
import java.util.*;

/**
 * Created on 12/5/17.
 */
public class UsingVehicle {

    public static Scanner keyboard = new Scanner(System.in);
    public static Vehicle[] vehs = new Vehicle[5];

    public static void main(String[] args) throws StatusException, OdometerException {
        initVehs();                 // Part A - Section II - (i)
//        displayAll();             // Part A - Section II - (ii)
//        getFilterDailyRate();     // Part A - Section II - (iii)
//        hireVeh();                // Part A - Section II - (iv) - 1. Hire Vehicle
//        hireCompleteVeh();        // Part A - Section II - (iv) - 2. Complete Hire
//        serviceVeh();             // Part A - Section II - (iv) - 3. Service Vehicle
//        serviceCompleteVeh();     // Part A - Section II - (iv) - 4. Complete Service
        showMenu();               // Part A - Section II - (v) - Show menu and asking anymore transactions
//        printAllVehs();           // Part A - Section II - (vi) - Printing final detail of all vehicles with print method

    }

    // Part A - Section II - (i)
    public static void initVehs() {
        vehs[0] = new Vehicle("SAM134","Toyota Camry 02", 45.00, 140000);
        vehs[1] = new Vehicle("QKO156","Honda Accord 04", 65.0, 125000);
        vehs[2] = new Vehicle("TUV178","Toyota Starlet 02", 35.00, 180000);
        vehs[3] = new Vehicle("SAG132","Toyota Avalon 05", 52.0, 190000);
        vehs[4] = new Vehicle("PRE342","Honda Civic 97", 35.00, 145000);
    }

    // Part A - Section II - (ii)
    public static void displayAll() {
        System.out.println("All the vehicles: ");
        for (int i=0; i< vehs.length; i++) {
            System.out.println("ID: " + vehs[i].getID() + "  -  " + "Description: " + vehs[i].getDescription());
        }
    }

    // Part A - Section II - (iii)
    public static void getFilterDailyRate() {
        System.out.println("Searching for car with Daily Rate FROM: ");
        double floorRate = keyboard.nextDouble();
        while (floorRate < 0) {
            System.out.println("Daily Rate has to be greater than Zero. Daily Rate FROM: ");
            floorRate = keyboard.nextDouble();
        }
        System.out.println("Searching for car with Daily Rate TO: ");
        double ceilingRate = keyboard.nextDouble();
        while (ceilingRate < floorRate) {
            System.out.println("Upper Rate has to be greater Lower Rate. Daily Rate TO: ");
            ceilingRate = keyboard.nextDouble();
        }
        filterDailyRate(floorRate,ceilingRate);
    }

    public static void filterDailyRate(double floorRate, double ceilingRate) {
        int count = 0;
        System.out.println("List the vehicles with Daily Rate between " + floorRate + " and " + ceilingRate + ": ");
        for (int i=0; i<vehs.length; i++) {
            if (floorRate <= vehs[i].getDailyRate() && vehs[i].getDailyRate() <= ceilingRate) {
                System.out.println("ID: " + vehs[i].getID() + "  -  " + "Description: " + vehs[i].getDescription() + "  -  " + "Daily rate: " + vehs[i].getDailyRate());
                count++;
            }
        }
        if (count == 0) {
            System.out.println("Sorry find no car!");
        }
    }

    // Part A - Section II - (iv) - 1. Hire Vehicle
    public static void hireVeh() throws StatusException, OdometerException {
        System.out.println("You are hiring vehicle");
        Vehicle veh = getVehicle();
        try {
            if (!(veh.getStatus() == "A")) {
                throw new StatusException("Vehicle is not available");
            }
            veh.hire(enterHirerID());
            veh.print();
        } catch (StatusException e) {
            System.out.println("Vehicle could not be hired! " + e);
        }
    }

    // Part A - Section II - (iv) - 2. Complete Hire
    public static void hireCompleteVeh() throws StatusException, OdometerException {
        System.out.println("You are completing hiring vehicle");
        Vehicle veh = getVehicle();

        try {
            if (!(veh.getStatus() == "H")) {
                throw new StatusException("Vehicle is not being hired");
            }
            double enterOdo = enterOdoReading();
            while (enterOdo <= veh.getOdometer()) {
                System.out.println("Odometer has to be more than: " + veh.getOdometer());
                enterOdo = enterOdoReading();
            }
            System.out.println("Completed hiring vehicle and your charger is " + veh.hireComplete(enterOdo));
            veh.print();
        } catch (StatusException e) {
            System.out.println("Completing hiring vehicle could not be done! " + e);
        }
    }

    // Part A - Section II - (iv) - 3. Service Vehicle
    public static void serviceVeh() throws StatusException {
        System.out.println("You are servicing vehicle");
        Vehicle veh = getVehicle();

        try {
            veh.service();
            veh.print();
        } catch (StatusException e) {
            System.out.println("Vehicle could not be serviced! " + e);
        }
    }

    // Part A - Section II - (iv) - 4. Complete Service
    public static void serviceCompleteVeh() throws StatusException, OdometerException {
        System.out.println("You are completing servicing vehicle");
        Vehicle veh = getVehicle();

        try {
            if (!veh.getStatus().equals("S")) {
                throw new StatusException("Vehicle is not being serviced");
            }
            double enterOdo = enterOdoReading();
            while (enterOdo < veh.getOdometer()) {
                System.out.println("Odometer has to be more than: " + veh.getOdometer());
                enterOdo = enterOdoReading();
            }
            veh.serviceComplete(enterOdo);
            veh.print();
        } catch (StatusException e) {
            System.out.println("Task could not be done! " + e);
        }
    }

    // Part A - Section II - (v) - Show menu and asking anymore transactions
    public static void showMenu() throws StatusException, OdometerException {
        int choice = choiceMenu();
        while (choice > 0 && choice < 7) {
            switch (choice) {
                case 1:
                    displayAll();
                    break;
                case 2:
                    hireVeh();
                    break;
                case 3:
                    hireCompleteVeh();
                    break;
                case 4:
                    serviceVeh();
                    break;
                case 5:
                    serviceCompleteVeh();
                    break;
                case 6:
                    System.exit(0);
            }
            choice = anymoreTrans();
        }
    }

    // Part A - Section II - (v) - Anymore transactions?
    public static int anymoreTrans() {
        int anymoreChoice = 0;
        while (anymoreChoice == 0) {
            try {
                System.out.println("Any more transactions? (Y/N): ");
                char yesNoChar = Character.toUpperCase(keyboard.next().charAt(0));
                keyboard.nextLine();
                if (yesNoChar == 'Y') {
                    anymoreChoice = choiceMenu();
                    break;
                } else if (yesNoChar == 'N') {
                    break;
                } else {
                    System.out.println("Your input is not exist!");
                    anymoreChoice = 0;
                }
            } catch (Exception e) {
                System.out.println("Your input is not exist!");
            }
        }
        return anymoreChoice;
    }

    // Part A - Section II - (vi) - Printing final detail of all vehicles with print method
    public static void printAllVehs() {
        System.out.println("All the vehicles: ");
        for (int i = 0; i < vehs.length; i++) {
            vehs[i].print();
        }
    }

    // Display Main Menu and Return Menu Choice
    public static int choiceMenu() {
        printMenu();
        int optionMenu;
        optionMenu = getMenu();
        while (!(optionMenu > 0 && optionMenu < 7)) {
            System.out.println("Your option is not exist! Back to Main Menu");
            printMenu();
            optionMenu = getMenu();
        }
        return optionMenu;
    }

    // Get Menu
    public static int getMenu(){
        int returnMenu = 0;
        try {
            char anymoreTrans = Character.toUpperCase(keyboard.next().charAt(0));
            keyboard.nextLine();
            returnMenu = Character.getNumericValue(anymoreTrans);
        } catch (Exception e) {
            System.out.println("Your input is not correct");
        } finally {
            return returnMenu;
        }
    }

    // Print Menu
    public static void printMenu() {
        System.out.println("      Vehicle Menu      ");
        System.out.println("Display Available Cars   1");
        System.out.println("Hire Vehicle             2");
        System.out.println("Complete Hire            3");
        System.out.println("Service Vehicle          4");
        System.out.println("Complete Service         5");
        System.out.println("Exit                     6");
        System.out.println("      Your Choice:      ");
    }

    // Return Vehicle if correctly Entering Vehicle ID:
    public static Vehicle getVehicle() {
        int vehPos = - 1;
        while (vehPos == -1) {
            vehPos = checkVehicleExist();
        }
        return vehs[vehPos];
    }

    // Check if Entering Vehicle ID is Exist in Vehs Array
    public static int checkVehicleExist() {
        int vehiclePostion = -1;
        System.out.println("Enter Vehicle ID: ");
        String vehicleID = keyboard.nextLine();
        for (int i = 0; i < vehs.length; i++) {
            if (vehs[i].getID().equals(vehicleID)) {
                vehiclePostion = i;
            }
        }
        if (vehiclePostion == -1) {
            System.out.println("Vehicle with ID: " + vehicleID + " is not exist!");
        }
        return vehiclePostion;
    }

    // Get HirerID
    public static String enterHirerID() {
        System.out.println("Enter Hirer ID: ");
        String enterHirerID = keyboard.nextLine();
        return enterHirerID;
    }

    // Get odometer Reading
    public static double enterOdoReading() {
        System.out.println("Enter Odometer Reading: ");
        double enterOdoReading = keyboard.nextDouble();
        return enterOdoReading;
    }

}
