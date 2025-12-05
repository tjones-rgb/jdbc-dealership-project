package com.pluralsight.ui;

import com.pluralsight.Contract;
import com.pluralsight.ContractFileManager;
import com.pluralsight.DealershipFileManager;
import com.pluralsight.models.Dealership;
import com.pluralsight.models.LeaseContract;
import com.pluralsight.models.SalesContract;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;
    private Scanner scanner = new Scanner(System.in);

    public UserInterface() {
    }

    private void init() {
        dealership = DealershipFileManager.getDealership();
    }

    public void display() {
        init();

        do {
            homeMenuScreen();

            System.out.print("Type in an number: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> processGetByPriceRequest();
                case 2 -> processGetByMakeModelRequest();
                case 3 -> processGetByYearRequest();
                case 4 -> processGetByColorRequest();
                case 5 -> processGetByMileageRequest();
                case 6 -> processGetByVehicleTypeRequest();
                case 7 -> processGetAllVehiclesRequest();
                case 8 -> processAddVehicleRequest();
                case 9 -> processRemoveVehicleRequest();
                case 10 -> processSellOrLeaseVehicle();
                case 99 -> System.exit(0);
                default -> {
                    System.out.println("Invalid option entered, please try again...");
                }
            }
        } while(true);
    }

    private void homeMenuScreen() {
        System.out.println("""
                    1 - Find vehicles within a price range
                    2 - Find vehicles by make / model
                    3 - Find vehicles by year range
                    4 - Find vehicles by color
                    5 - Find vehicles by mileage range
                    6 - Find vehicles by type (car, truck, SUV, van)
                    7 - List ALL vehicles
                    8 - Add a vehicle
                    9 - Remove a vehicle
                    99 - Quit
                    """);
    }

    private void displayVehicles(List<Vehicle> vehicles) {
        for (Vehicle v: vehicles) {
            System.out.println(v);
        }
        System.out.println();
    }

    public void processGetByPriceRequest() {
        System.out.print("Please enter the max price: ");
        int max = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Please enter the min price: ");
        int min = scanner.nextInt();
        scanner.nextLine();

        displayVehicles(dealership.getVehiclesByPrice(min, max));
    }

    public void processGetByMakeModelRequest() {
        System.out.print("Please enter the make: ");
        String make = scanner.nextLine();

        System.out.print("Please enter the model: ");
        String model = scanner.nextLine();

        displayVehicles(dealership.getVehiclesByMakeModel(make, model));
    }

    public void processGetByYearRequest() {
        System.out.print("Please enter the max year: ");
        int max = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Please enter the min year: ");
        int min = scanner.nextInt();
        scanner.nextLine();

        displayVehicles(dealership.getVehiclesByYear(min, max));
    }

    public void processGetByColorRequest() {
        System.out.print("Please enter the color: ");
        String color = scanner.nextLine();

        displayVehicles(dealership.getVehiclesByColor(color));
    }

    public void processGetByMileageRequest() {
        System.out.print("Please enter the max mileage: ");
        int max = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Please enter the min mileage: ");
        int min = scanner.nextInt();
        scanner.nextLine();

        displayVehicles(dealership.getVehiclesByMileage(min, max));
    }

    public void processGetByVehicleTypeRequest() {
        System.out.print("Please enter the type: ");
        String type = scanner.nextLine();

        displayVehicles(dealership.getVehiclesByType(type));
    }

    public void processGetAllVehiclesRequest() {
        displayVehicles(dealership.getAllVehicles());
    }

    public void processAddVehicleRequest() {
        System.out.print("Please enter the vin: ");
        int vin = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Please enter the year: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Please enter the odometer: ");
        int odometer = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Please enter the make: ");
        String make = scanner.nextLine();

        System.out.print("Please enter the model: ");
        String model = scanner.nextLine();

        System.out.print("Please enter the type: ");
        String vehicleType = scanner.nextLine();

        System.out.print("Please enter the color: ");
        String color = scanner.nextLine();

        System.out.print("Please enter the price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        dealership.addVehicle(new Vehicle(vin, year, color, make, model, vehicleType,odometer, price));
        DealershipFileManager.saveDealership(dealership);
    }

    public void processRemoveVehicleRequest() {
        System.out.print("Please enter the vin of the car you want to remove: ");
        int vin = scanner.nextInt();
        scanner.nextLine();

        dealership.removeVehicle(dealership.getVehicleByVin(vin));
        DealershipFileManager.saveDealership(dealership);
    }
    public void processSellOrLeaseVehicle(){
        System.out.println("Enter Vin of vehicle to sell or lease: ");
        int vin = scanner.nextInt();

        Vehicle vehicle = dealership.getVehicleByVin(vin);
        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }
        System.out.println("Customer name:");
        String name = scanner.nextLine();

        System.out.println("Customer email");
        String email = scanner.nextLine();

        System.out.print("Is this a sale or lease? (Sale Or Lease): ");
        String type = scanner.nextLine().toLowerCase();

        String date = java.time.LocalDate.now().toString();
        Contract contract = null;

        if (type.equals("sale")) {
            System.out.println("Would you like financing? ");
            boolean finance = scanner.nextLine().equalsIgnoreCase("yes");

            contract = new SalesContract(date, name, email, vehicle, finance);
        } else if (type.equals("lease")) {
            int currentYear = java.time.Year.now().getValue();
            if (currentYear - vehicle.getYear() > 3) {
                System.out.println("Vehicle too old to lease.");
                return;
            }
            contract = new LeaseContract(date, name, email,vehicle);
        }else {
            System.out.println("Invalid contact type");
            return;
        }
        ContractFileManager.saveContract(contract);
        dealership.removeVehicle(vehicle);
        DealershipFileManager.saveDealership(dealership);

        System.out.println("Contract saved");
    }
}
