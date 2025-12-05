package com.pluralsight.models;

import com.pluralsight.Contract;

public class SalesContract extends Contract {


    private boolean isFinanced;
    private static final double SAlES_TAX_RATE = 0.05;
    private static final double RECORDING_FEE = 100.0;

    public SalesContract(String date, String customerName, String customerEmail, int vehicleSold, boolean isFinanced) {
        super(date, customerName, customerEmail, vehicleSold);
        this.isFinanced = isFinanced;
    }

    public boolean isFinanced() {
        return isFinanced;
    }
    private double getProcessingFee() {
        double fee;
        if (getVehicleSold().getPrice() < 10000){
            fee = 295.0;
        }else {
            return 495.0;
        }
        return fee;
    }
    private double getSalesTax(){
        return getVehicleSold().getPrice() * SAlES_TAX_RATE;
    }

    @Override
    public double getTotalPrice() {
        return 0;
    }

    @Override
    public double getMonthlyPayment() {
        if (!isFinanced) return 0.0;

        double price = getTotalPrice();
        double rate;
        int months;

        if (price >= 1000) {
            rate = 0.0425 / 12;
            months = 48;
        }else {
            rate = 0.0525 / 12;
            months = 24;
        }
        return (price * rate) / (1 - Math.pow(1 + rate, -months));
    }
}
