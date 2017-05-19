package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Created on 18/5/17.
 */
public class ManageHiring {

    public static Scanner keyboard = new Scanner(System.in);
    public static List<Vehicle> vehs = new ArrayList<>();
    public static int countVeh = 0;

    public static void main(String[] args) throws StatusException, OdometerException, FileNotFoundException {
        inputProjects();                 // Part C - Section III - Reading from files
//        showMenu();                      // Part B - Section III - Show Menu
//        outputProjects();                // Part C - Section III - Writing to files

    }

    // Part C - Section III - Show Menu:
    public static void showMenu() throws StatusException, OdometerException {
        int choice = choiceMenu();
        while (choice > 0 && choice < 7) {
            switch (choice) {
                case 1:
                    // Input validation must ensure that ID is 6 characters and unique
//                    addNewVehicle();
                    break;
                case 2:
                    // Input validation must ensure ID is 6 characters, unique and starts with C
//                    addNewCustomer();
                    break;
                case 3:
                    getFilterDailyRate();
                    break;
                case 4:
                    hireVeh();
                    break;
                case 5:
                    hireCompleteVeh();
                    break;
                case 6:
                    serviceVeh();
                    break;
                case 7:
                    serviceCompleteVeh();
                    break;
                case 8:
                    System.exit(0);
            }
            choice = anymoreTrans();
        }
    }
    // Part C - Section III - Display Main Menu and Return Menu Choice
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

    // Part C - Section III - Print Menu
    public static void printMenu() {
        System.out.println("        Vehicle Menu        ");
        System.out.println("Adding New Vehicle         1");
        System.out.println("Adding New Customer        2");
        System.out.println("Searching By Daily Rate    3");
        System.out.println("Hiring Vehicle             4");
        System.out.println("Complete Hire              5");
        System.out.println("Service Vehicle            6");
        System.out.println("Complete Service           7");
        System.out.println("Exit                       8");
        System.out.println("        Your Choice:        ");
    }

    // Part C - Section III - Print Menu - Anymore transactions?
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

    // Part C - Section III - (3) - Getting Floor and Ceiling for Searching by Daily Rate:
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

    // Part C - Section III - (3) - Searching by Daily Rate:
    public static void filterDailyRate(double floorRate, double ceilingRate) {
        int count = 0;
        System.out.println("List the vehicles with Daily Rate between " + floorRate + " and " + ceilingRate + ": ");
        for (int i=0; i<=vehs.size(); i++) {
            if (floorRate <= vehs.get(i).getDailyRate() && vehs.get(i).getDailyRate() <= ceilingRate) {
                System.out.println("ID: " + vehs.get(i).getID() + "  -  " + "Description: " + vehs.get(i).getDescription() + "  -  " + "Daily rate: " + vehs.get(i).getDailyRate());
                count++;
            }
        }
        if (count == 0) {
            System.out.println("Sorry find no car!");
        }
    }

    // Part C - Section III - (4) - Hire Vehicle
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

    // Part C - Section III - (4) - Hire Vehicle - Return Vehicle if correctly Entering Vehicle ID:
    public static Vehicle getVehicle() {
        int vehPos = - 1;
        while (vehPos == -1) {
            vehPos = checkVehicleExist();
        }
        return vehs.get(vehPos);
    }

    // Part C - Section III - (4) - Hire Vehicle - Check if Entering Vehicle ID is Exist in Vehs Array
    public static int checkVehicleExist() {
        int vehiclePostion = -1;
        System.out.println("Enter Vehicle ID: ");
        String vehicleID = keyboard.nextLine();
        for (int i = 0; i < vehs.size(); i++) {
            if (vehs.get(i).getID().equals(vehicleID)) {
                vehiclePostion = i;
            }
        }
        if (vehiclePostion == -1) {
            System.out.println("Vehicle with ID: " + vehicleID + " is not exist!");
        }
        return vehiclePostion;
    }

    // Part C - Section III - (4) - Hire Vehicle - Get HirerID
    public static String enterHirerID() {
        System.out.println("Enter Hirer ID: ");
        String enterHirerID = keyboard.nextLine();
        return enterHirerID;
    }

    // Part C - Section III - (5) - Complete Hire
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

    // Part C - Section III - (6) - Service Vehicle
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

    // Part C - Section III - (7) - Complete Service
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

    // Part C - Section III - (7) - Complete Service - Get odometer Reading
    public static double enterOdoReading() {
        System.out.println("Enter Odometer Reading: ");
        double enterOdoReading = keyboard.nextDouble();
        return enterOdoReading;
    }

    // Part C - Section III - Reading from Vehicle.txt:
    public static void inputProjects () throws FileNotFoundException {
        Scanner reader = new Scanner(System.in);
        File f = new File("vehicle.txt");
        if (f.isFile()) {
            Scanner sc = new Scanner(f);
            StringBuilder sb = new StringBuilder();
            while (sc.hasNextLine()) {
                vehs.add(countVeh, createVeh(sc.nextLine()));
                countVeh++;
            }
            sc.close();
        }
        else {
            System.out.println("could not find file: vehicle.txt");
        }
        reader.close();
    }

    // Part C - Section III - Reading from files - Create Vehicle Object from Reading Vehicle.txt
    public static Vehicle createVeh(String vehInput) {
        Vehicle vehsInput = new Vehicle();
        String[] vehArray = vehInput.trim().split("\\s*,\\s*");

        if (vehArray.length == 4) {
            vehsInput = new Vehicle(vehArray[0],vehArray[1], Double.parseDouble(vehArray[2]), Double.parseDouble(vehArray[3]));
        } else if (vehArray.length == 7) {
            vehsInput = new PremiumVehicle(vehArray[0],vehArray[1], Double.parseDouble(vehArray[2]), Double.parseDouble(vehArray[3]), Integer.parseInt(vehArray[4]), Integer.parseInt(vehArray[5]), Integer.parseInt(vehArray[6]));
        }
        return vehsInput;
    }

    // Part C - Section III - Writing to files:
    public static void outputProjects() {
        System.out.println("Writing to files...");
    }

}

