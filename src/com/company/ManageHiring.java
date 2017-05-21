package com.company;
import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;


/**
 * Created on 18/5/17.
 */
public class ManageHiring {

    public static Scanner keyboard = new Scanner(System.in);
    public static List<Vehicle> vehs = new ArrayList<>();
    public static List<Customer> customers = new ArrayList<>();

    public static int countVeh = 0;
    public static int countAddNewVeh = 0;

    public static int countCus = 0;
    public static int countAddNewCus = 0;

    public static void main(String[] args) throws StatusException, OdometerException, IOException, ParseException {

        inputProjects();                 // Part C - Section III - Reading from files
//        addVehicle();
        showMenu();                      // Part B - Section III - Show Menu
        outputProjects();                // Part C - Section III - Writing to files

        for (int i=0; i<vehs.size(); i++) {
            vehs.get(i).print();
        }
    }

    // Part C - Section III - Show Menu:
    public static void showMenu() throws StatusException, OdometerException {
        int choice = choiceMenu();
        while (choice > 0 && choice < 9) {
            switch (choice) {
                case 1:
                    addVehicle();
                    break;
                case 2:
                    addCustomer();
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
                    break;
            }
            choice = anymoreTrans();
        }
    }
    // Part C - Section III - Display Main Menu and Return Menu Choice
    public static int choiceMenu() {
        printMenu();
        int optionMenu = keyboard.nextInt();
        keyboard.nextLine();
        while (!(optionMenu > 0 && optionMenu < 9)) {
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
                break;
        }
        return anymoreChoice;
    }

    // Part C - Section III - (1) - Adding new Vehicle:
    public static void addVehicle() {
        System.out.println("You are adding new Vehicle: ");
        if (!isPremiumVeh()) {
            addNormalVeh();
        } else {
            addPremiumVeh();
        }
    }

    // Part C - Section III - (1) - Adding new Vehicle - Vehicle Premium Or Not:
    public static boolean isPremiumVeh() {
        boolean returnBool = false;
        System.out.println("Is it Premium Vehicle? (Y/N) ");
        String isPremium = keyboard.nextLine();
        switch (isPremium.toUpperCase()) {
            case "Y":
                returnBool = true;
                break;
            case "N":
                returnBool = false;
                break;
        }
        return returnBool;
    }

    // Part C - Section III - (1) - Adding new Normal Vehicle:
    public static void addNormalVeh() {
        System.out.println("You are adding new Normal Vehicle");
        String newVehID = addNewVehID();
        String newDes = addNewDes();
        double newDailyRate = addNewDailyRate();
        double newOdo = addNewOdo();

        Vehicle newVeh = new Vehicle(newVehID, newDes, newDailyRate, newOdo);
        vehs.add((countVeh + countAddNewVeh), newVeh);
        countAddNewVeh++;
    }

    // Part C - Section III - (1) - Adding new Premium Vehicle:
    public static void addPremiumVeh() {
        System.out.println("You are adding new Premium Vehicle");
        String newVehID = addNewVehID();
        String newDes = addNewDes();
        double newDailyRate = addNewDailyRate();
        double newOdo = addNewOdo();
        int newDailyMileage = addDailyMileage();
        int newServiceLength = addServiceLength();
        int newOdoLastService = addOdoLastService();

        PremiumVehicle newVeh = new PremiumVehicle(newVehID, newDes, newDailyRate, newOdo, newDailyMileage, newServiceLength, newOdoLastService);
        vehs.add((countVeh + countAddNewVeh), newVeh);
        countAddNewVeh++;
    }

    // Part C - Section III - (1) - Adding new Vehicle - Add New VehicleID:
    public static String addNewVehID() {
        String enterVehID = enterVehicleID();
        while (!vehIsValidation(enterVehID)) {
            enterVehID = enterVehicleID();
        }
        return enterVehID;
    }

    // Part C - Section III - (1) - Adding new Vehicle - Add New Description:
    public static String addNewDes() {
        System.out.println("Enter New Description: ");
        String newDescription = keyboard.nextLine();
        return newDescription;
    }

    // Part C - Section III - (1) - Adding new Vehicle - Add New Daily Rate:
    public static double addNewDailyRate() {
        System.out.println("Enter New Daily Rate: ");
        double newDailyRate = keyboard.nextDouble();
        return newDailyRate;
    }

    // Part C - Section III - (1) - Adding new Vehicle - Add New Odometer:
    public static double addNewOdo() {
        System.out.println("Enter New Odometer: ");
        double newOdo = keyboard.nextDouble();
        return newOdo;
    }

    // Part C - Section III - (1) - Adding new Premium Vehicle - Add Daily Mileage:
    public static int addDailyMileage() {
        System.out.println("Enter New Daily Mileage: ");
        int newDailyMileage = keyboard.nextInt();
        return newDailyMileage;
    }

    // Part C - Section III - (1) - Adding new Premium Vehicle - Add New Service Length:
    public static int addServiceLength() {
        System.out.println("Enter New Service Length: ");
        int newServiceLength = keyboard.nextInt();
        return newServiceLength;
    }

    // Part C - Section III - (1) - Adding new Premium Vehicle - Add New Odo Last Service:
    public static int addOdoLastService() {
        System.out.println("Enter Odometer Last Service : ");
        int newOdoLastService = keyboard.nextInt();
        return newOdoLastService;
    }

    // Part C - Section III - (1) - Adding new Vehicle - Input Validation:
    public static boolean vehIsValidation(String vehID) {
        boolean validation = false;
        int vehCheckPos = checkVehicleExist(vehID);

        if (vehID.length() != 6) {
            validation = false;
            System.out.println("VehicleID should be 6 characters!");
        } else if (vehID.length() == 6) {
            if (vehCheckPos != -1) {
                System.out.println("Vehicle ID is exist!");
            } else if (vehCheckPos == -1){
                validation = true;
            }
        }
        return validation;
    }

    // Part C - Section III - (2) - Adding new Customer:
    public static String addCustomer() {
        System.out.println("You are adding new Customer: ");
        String newCusID;
        if (!isCopCustomer()) {
            newCusID = addIndCus();
        } else {
            newCusID = addCopCus();
        }
        return newCusID;
    }

    // Part C - Section III - (2) - Adding new Customer - Corporate Customer Or Not:
    public static boolean isCopCustomer() {
        boolean returnBool = false;
        System.out.println("Is it Corporate Customer? (Y/N) ");
        String isCopCus = keyboard.nextLine();
        switch (isCopCus) {
            case "Y":
                returnBool = true;
                break;
            case "N":
                returnBool = false;
                break;
        }
        return returnBool;
    }

    // Part C - Section III - (2) - Adding new Customer - Individual Customer:
    public static String addIndCus() {
        System.out.println("You are adding new Individual Customer");
        String newID = addNewCusID();
        String newName = addNewCusName();
        String newPhone = addNewCusPhone();

        Customer newICus = new Customer(newID, newName, newPhone);
        customers.add((countCus + countAddNewCus), newICus);
        countAddNewCus++;
        return newID;
    }

    // Part C - Section III - (2) - Adding new Customer - Individual Customer:
    public static String addCopCus() {
        System.out.println("You are adding new Corporate Customer");
        String newID = addNewCusID();
        String newName = addNewCusName();
        String newPhone = addNewCusPhone();
        float newDiscountRate = addNewDiscountRate();

        while (newDiscountRate >= 1 || newDiscountRate <= 0) {
            System.out.println("Discount rate should smaller than 100%, and bigger than 0%");
            newDiscountRate = addNewDiscountRate();
        }

        CCustomer newICus = new CCustomer(newID, newName, newPhone, newDiscountRate);
        customers.add((countCus + countAddNewCus), newICus);
        countAddNewCus++;
        return newID;
    }

    // Part C - Section III - (2) - Adding new Customer - Add new customerID :
    public static String addNewCusID() {
        String cusID = enterCusID();
        while (!cusIsValidation(cusID)) {
            cusID = enterCusID();
        }
        return cusID;
    }

    // Part C - Section III - (2) - Adding new Customer - Enter new customerID:
    public static String enterCusID() {
        System.out.println("Enter Customer ID: ");
        String cusID = keyboard.nextLine();
        String capCusID = cusID.substring(0, 1).toUpperCase() + cusID.substring(1);
        return capCusID;
    }

    // Part C - Section III - (2) - Adding new Customer - Input Validation:
    public static boolean cusIsValidation(String cusID) {
        boolean validation = false;
        int cusCheckPos = checCusExist(cusID);

        if (cusID.length() != 6) {
            validation = false;
            System.out.println("Customer ID should be 6 characters!");
        } else if (cusID.length() == 6 && cusID.charAt(0) != 'C') {
            validation = false;
            System.out.println("Customer ID has to be started with C");
        } else if (cusID.length() == 6 && cusID.charAt(0) == 'C') {
            if (cusCheckPos != -1) {
                System.out.println("Customer ID is exist!");
            } else if (cusCheckPos == -1){
                validation = true;
            }
        }
        return validation;
    }

    // Part C - Section III - (2) - Adding new Customer - Check if Entering Customer ID is Exist in Customers Array:
    public static int checCusExist(String cusID) {
        int cusPostion = -1;
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getCustomerID().equals(cusID)) {
                cusPostion = i;
            }
        }
        return cusPostion;
    }

    // Part C - Section III - (2) - Adding new Customer - Add new Customer Name:
    public static String addNewCusName() {
        System.out.println("Enter New Customer Name: ");
        String newCusName = keyboard.nextLine();
        return newCusName;
    }

    // Part C - Section III - (2) - Adding new Customer - Add new Customer Phone:
    public static String addNewCusPhone() {
        System.out.println("Enter New Customer Phone: ");
        String newCusPhone = keyboard.nextLine();
        return newCusPhone;
    }

    // Part C - Section III - (2) - Adding new Corporate Customer - Add new Discount Rate:
    public static float addNewDiscountRate() {
        System.out.println("How many percent (%) for Corporate Customer Discount Rate? ");
        float newDiscountRate= keyboard.nextFloat();
        return newDiscountRate/100;
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
        for (int i=0; i< vehs.size(); i++) {
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
            if (!veh.getStatus().equals("A")) {
                throw new StatusException("Vehicle is not available");
            }
            String hirerID = enterHirerID();
            if (checCusExist(hirerID) != -1) {
                veh.hire(hirerID);
                veh.print();
            } else if (checCusExist(hirerID) == -1){
                System.out.println("Customer ID is not exist");
                // Do you want to add newCus (Y/N)?
                veh.hire(addCustomer());
                veh.print();
            }

        } catch (StatusException e) {
            System.out.println("Vehicle could not be hired! " + e);
        } catch (OdometerException e) {
            System.out.println(e);
        }
    }

    // Part C - Section III - (4) - Hire Vehicle - Return Vehicle if correctly Entering Vehicle ID:
    public static Vehicle getVehicle() {
        int vehPos = checkVehicleExist(enterVehicleID());
        while (vehPos == - 1) {
            System.out.println("Vehicle is not exist!");
            vehPos = checkVehicleExist(enterVehicleID());
        }
        return vehs.get(vehPos);
    }

    // Part C - Section III - (4) - Hire Vehicle - Entering Vehicle ID:
    public static String enterVehicleID() {
        System.out.println("Enter Vehicle ID: ");
        String vehicleID = keyboard.nextLine();
        return vehicleID;
    }

    // Part C - Section III - (4) - Hire Vehicle - Check if Entering Vehicle ID is Exist in Vehs Array:
    public static int checkVehicleExist(String vehicleID) {
        int vehiclePostion = -1;
        for (int i = 0; i < vehs.size(); i++) {
            if (vehs.get(i).getID().equals(vehicleID)) {
                vehiclePostion = i;
            }
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
            double enterOdo = enterOdoReading();
            while (enterOdo <= veh.getOdometer()) {
                System.out.println("Odometer has to be more than: " + veh.getOdometer());
                enterOdo = enterOdoReading();
            }
            double diffOdo = enterOdo - veh.getOdometer();
            double charger = veh.hireComplete(enterOdo);
            double discount = customers.get(checCusExist(veh.getHirer())).getDiscount(charger);
            System.out.println("Completed hiring vehicle and your charger is: " + charger);
            System.out.println("Your discount is: " + discount);
            customers.get(checCusExist(veh.getHirer())).setPastMileage((diffOdo));
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

    // Part C - Section III - (7) - Complete Service - Get odometer Reading
    public static double enterOdoReading() {
        System.out.println("Enter Odometer Reading: ");
        double enterOdoReading = keyboard.nextDouble();
        return enterOdoReading;
    }

    // Part C - Section III - Reading from vehicle.txt and customer.txt:
    public static void inputProjects () throws IOException {
        File fileVeh = new File("vehicle.txt");
        if (!fileVeh.exists()) {
            fileVeh.createNewFile();
        }
        if (fileVeh.isFile()) {
            Scanner sc = new Scanner(fileVeh);
            while (sc.hasNextLine()) {
                vehs.add(countVeh, getVeh(sc.nextLine()));
                countVeh++;
            }
            sc.close();
        }

        File fileCus = new File("customer.txt");
        if (fileCus.isFile()) {
            Scanner sc = new Scanner(fileCus);
            while (sc.hasNextLine()) {
                customers.add(countCus, getCus(sc.nextLine()));
                countCus++;
            }
            sc.close();
        }
    }

    // Part C - Section III - Reading from files - Get Input Vehicle Object from Reading Vehicle.txt
    public static Vehicle getVeh(String vehInput) {
        Vehicle vehObjectInput = new Vehicle();
        String[] vehArray = vehInput.trim().split("\\s*,\\s*");
        if (vehArray.length == 7) {
            if (vehArray[4].equals("H")) {
                vehObjectInput = new Vehicle(vehArray[0],vehArray[1], Double.parseDouble(vehArray[2]), Double.parseDouble(vehArray[3]), vehArray[4], vehArray[5]);
                SimpleDateFormat formatDateHirer = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
                try
                {
                    Date dateHirer = formatDateHirer.parse(vehArray[6]);
                    vehObjectInput.setDateHire(dateHirer);
                }
                catch (ParseException ex)
                {
                    System.out.println("Exception "+ex);
                }
            }
        } else if (vehArray.length == 5) {
            vehObjectInput = new Vehicle(vehArray[0], vehArray[1], Double.parseDouble(vehArray[2]), Double.parseDouble(vehArray[3]), vehArray[4]);
        } else if (vehArray.length == 10) {
            if (vehArray[4].equals("H")) {
                vehObjectInput = new PremiumVehicle(vehArray[0],vehArray[1], Double.parseDouble(vehArray[2]), Double.parseDouble(vehArray[3]), vehArray[4], vehArray[5], Double.parseDouble(vehArray[7]), Double.parseDouble(vehArray[8]), Double.parseDouble(vehArray[9]));
                SimpleDateFormat formatDateHirer = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
                try
                {
                    Date dateHirer = formatDateHirer.parse(vehArray[6]);
                    vehObjectInput.setDateHire(dateHirer);
                }
                catch (ParseException ex)
                {
                    System.out.println("Exception "+ex);
                }
            }
        }  else if (vehArray.length == 8) {
            vehObjectInput = new PremiumVehicle(vehArray[0],vehArray[1], Double.parseDouble(vehArray[2]), Double.parseDouble(vehArray[3]), vehArray[4], Double.parseDouble(vehArray[5]), Double.parseDouble(vehArray[6]), Double.parseDouble(vehArray[7]));
        }
        return vehObjectInput;
    }

    // Part C - Section III - Reading from files - Get Input Vehicle Object from Reading Vehicle.txt
    public static Customer getCus(String cusInput) {
        Customer cusObjectInput = new Customer();
        String[] cusArray = cusInput.trim().split("\\s*,\\s*");

        if (cusArray.length == 4) {
            cusObjectInput = new Customer(cusArray[0],cusArray[1], cusArray[2], Double.parseDouble(cusArray[3]));

        } else if (cusArray.length == 5) {
            cusObjectInput = new CCustomer(cusArray[0],cusArray[1], cusArray[2],Double.parseDouble(cusArray[3]), Double.parseDouble(cusArray[4]));
        }
        return cusObjectInput;
    }

    // Part C - Section III - Writing to files:
    public static void outputProjects() throws IOException {
        writingToVeh();
        writingToCus();
    }

    // Part C - Section III - Writing to files - Writing to vehicle.txt:
    public static void writingToVeh() {
        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            File file = new File("vehicle.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            String newLine = System.getProperty("line.separator");
            for (int i=0; i<vehs.size(); i++) {
                if (!vehs.get(i).getID().equals("000000")) {
                    bw.write(vehs.get(i).convertToString() + newLine);
                }
            }
            System.out.println("Done writing to vehicle.txt");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    // Part C - Section III - Writing to files - Writing to customer.txt:
    public static void writingToCus() {
        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            File file = new File("customer.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            String newLine = System.getProperty("line.separator");

            for (int i=0; i<customers.size(); i++) {
                bw.write(customers.get(i).convertToString() + newLine);
            }
            System.out.println("Done writing to customer.txt");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}

