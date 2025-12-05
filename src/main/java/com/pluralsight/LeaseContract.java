package com.pluralsight;

public class LeaseContract extends Contract {

    private static final double LEASE_FEE_RATE = 0.07;
    private static final double INTEREST_RATE = 0.04 / 12;
    private static final int MONTHs = 36;

    public LeaseContract(String date, String customerName, String customerEmail, double vehicleSold) {
        super(date, customerName, customerEmail, vehicleSold);
    }
    private double getExpectedEndingValue() {
        return getVehicleSold().getPrice()* 0.5;
    }
    private double getLeaseFee() {
        return getVehicleSold().getPrice() * LEASE_FEE_RATE;
    }

    @Override
    public double getTotalPrice() {
        return getLeaseFee() + getVehicleSold().getPrice();
    }

    @Override
    public double getMonthlyPayment() {
        return 0;
    }

}