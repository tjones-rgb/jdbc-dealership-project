package com.pluralsight;

import java.io.*;
import java.util.ArrayList;

public class DealershipFileManager {
    private static final String FILE_NAME = "inventory.csv";

    public static Dealership getDealership() {
        Dealership dealership = null;
        ArrayList<Vehicle> inventory = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            // Read the first line: dealership info
            String headerLine = reader.readLine();
            if (headerLine != null) {
                String[] dealerParts = headerLine.split("\\|");
                String name = dealerParts[0];
                String address = dealerParts[1];
                String phone = dealerParts[2];

                dealership = new Dealership(name, address, phone);
            }

            // Read each remaining line: vehicle info
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                int vin = Integer.parseInt(parts[0]);
                int year = Integer.parseInt(parts[1]);
                String make = parts[2];
                String model = parts[3];
                String vehicleType = parts[4];
                String color = parts[5];
                int odometer = Integer.parseInt(parts[6]);
                double price = Double.parseDouble(parts[7]);

                Vehicle v = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
                dealership.addVehicle(v);
            }

        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }

        return dealership;
    }

    public static void saveDealership(Dealership dealership) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            // Write dealership header line
            writer.write(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone());
            writer.newLine();

            // Write all vehicle lines
            for (Vehicle v : dealership.getAllVehicles()) {
                writer.write(v.getVin() + "|" +
                        v.getYear() + "|" +
                        v.getMake() + "|" +
                        v.getModel() + "|" +
                        v.getVehicleType() + "|" +
                        v.getColor() + "|" +
                        v.getOdometer() + "|" +
                        v.getPrice());
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}
