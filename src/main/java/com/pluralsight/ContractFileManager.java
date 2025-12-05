package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContractFileManager {
    private static final String FILE_NAME = "contracts.csv";

    public static void saveContract(Contract contract) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {

            Vehicle v = contract.getVehicleSold();

            if (contract instanceof SalesContract sale) {
                writer.write("SALE|" + contract.getDate() + "|" + contract.getCustomerName() + "|" +
                        contract.getCustomerEmail() + "|" + v.getVin() + "|" + v.getYear() + "|" +
                        v.getMake() + "|" + v.getModel() + "|" + v.getVehicleType() + "|" + v.getColor() + "|" +
                        v.getOdometer() + "|" + v.getPrice() + "|" +
                        (v.getPrice() * 0.05) + "|100.00|" +
                        (v.getPrice() < 10000 ? "295.00" : "495.00") + "|" +
                        sale.getTotalPrice() + "|" + (sale.isFinanced() ? "YES" : "NO") + "|" +
                        sale.getMonthlyPayment());
            } else if (contract instanceof LeaseContract lease) {
                writer.write("LEASE|" + contract.getDate() + "|" + contract.getCustomerName() + "|" +
                        contract.getCustomerEmail() + "|" + v.getVin() + "|" + v.getYear() + "|" +
                        v.getMake() + "|" + v.getModel() + "|" + v.getVehicleType() + "|" + v.getColor() + "|" +
                        v.getOdometer() + "|" + v.getPrice() + "|" +
                        (v.getPrice() * 0.5) + "|" + (v.getPrice() * 0.07) + "|" +
                        lease.getTotalPrice() + "|" + lease.getMonthlyPayment());
            }

            writer.newLine();

        } catch (IOException e) {
            System.out.println("Error saving contract: " + e.getMessage());
        }
    }
}
