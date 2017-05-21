package com.company;

import java.util.Scanner;

/**
 * Created on 14/5/17.
 */
public class Manipulating {

    public static Scanner keyboard = new Scanner(System.in);
    public static Vehicle[] vehs = new Vehicle[6];

    public static void main(String[] args) throws StatusException, OdometerException {
        initVehs();                 // Part B - Section II - (i)
        showMenu();                 // Part B - Section II - (ii)
    }

    // Part B - Section II - (i)
    public static void initVehs() {
        vehs[0] = new Vehicle("QJT123","Starlet 99", 35.00, 190000,"A");
        vehs[1] = new PremiumVehicle("TUX132","BMW 05", 90.0,12000, "A", 100, 10000, 5000);
        vehs[2] = new Vehicle("PTU121","Holden 03", 60.00, 165000, "A");
        vehs[3] = new Vehicle("OCD856","Camry 04", 65.0, 230000, "A");
        vehs[4] = new PremiumVehicle("TEY749","Jaguar 06", 125.00, 27000, "A", 120, 12000, 20000);
        vehs[5] = new Vehicle("TYR852","Subaru 02", 60.0, 270000, "A");
    }

    // Part B - Section II - (ii) - Show Main Menu and Run task
    public static void showMenu() throws StatusException, OdometerException {
        int choice = choiceMenu();
        while (choice > 0 && choice < 7) {
            switch (choice) {
                case 1:
                    printAllVehs();
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
    // Part B - Section II - (ii) - Display Main Menu and Return Menu Choice
    public static int choiceMenu() {
        printMenu();
        int optionMenu = keyboard.nextInt();
        keyboard.nextLine();
        while (!(optionMenu > 0 && optionMenu < 7)) {
            System.out.println("Your option is not exist! Back to Main Menu");
            printMenu();
            optionMenu = keyboard.nextInt();
            keyboard.nextLine();
        }
        return optionMenu;
    }

    // Part B - Section II - (ii) - Printing menu
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

    // Part B - Section II - (ii) - 1. Printing All Vehicles
    public static void printAllVehs() {
        System.out.println("All the vehicles: ");
        for (int i = 0; i < vehs.length; i++) {
            vehs[i].print();
        }
    }
    // Part B - Section II - Anymore transactions?
    public static int anymoreTrans() {
        int anymoreChoice = 0;
        System.out.println("Any more transactions? (Y/N): ");
        char anymoreTrans = Character.toUpperCase(keyboard.next(".").charAt(0));
        switch (anymoreTrans) {
            case 'Y':
                anymoreChoice = choiceMenu();
                break;
            case 'N':
                System.exit(0);
                break;
        }
        return anymoreChoice;
    }

    // Part B - Section II - 2. Hire Vehicle
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
        } catch (OdometerException ep) {
            System.out.println("Vehicle could not be hired! " + ep);
        }
    }
    // Return Vehicle if correctly Entering Vehicle ID:
    public static Vehicle getVehicle() {
        int vehPos = - 1;
        while (vehPos == -1) {
            vehPos = checkVehicleExist();
        }
        return vehs[vehPos];
    }

    // Get HirerID
    public static String enterHirerID() {
        System.out.println("Enter Hirer ID: ");
        String enterHirerID = keyboard.nextLine();
        return enterHirerID;
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

    // Part B - Section II - 3. Complete Hire
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

    // Get odometer Reading
    public static double enterOdoReading() {
        System.out.println("Enter Odometer Reading: ");
        double enterOdoReading = keyboard.nextDouble();
        return enterOdoReading;
    }

    // Part B - Section II - 4. Service Vehicle
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

    // Part B - Section II - 4. Complete Service1

    public static void serviceCompleteVeh() throws StatusException, OdometerException {
        Vehicle veh = getVehicle();
        System.out.println("You are completing servicing vehicle");
        try {
            double enterOdo = enterOdoReading();
            while (enterOdo <= veh.getOdometer()) {
                System.out.println("Odometer has to be more than: " + veh.getOdometer());
                enterOdo = enterOdoReading();
            }
            veh.serviceComplete(enterOdo);
            veh.print();
        } catch (StatusException e) {
            System.out.println("Task could not be done! " + e);
        }
    }
}
