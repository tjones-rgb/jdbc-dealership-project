package com.pluralsight;

public abstract class Contract {
    String date;
    String CustomerName;
    String CustomerEmail;
    int VehicleSold;
    double TotalPrice;
    int MonthlyPayment;


    public Contract(String date, String customerName, String customerEmail, int vehicleSold) {
        this.date = date;
        CustomerName = customerName;
        CustomerEmail = customerEmail;
        VehicleSold = vehicleSold;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getCustomerEmail() {
        return CustomerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        CustomerEmail = customerEmail;
    }

    public int getVehicleSold() {
        return VehicleSold;
    }

    public void setVehicleSold(double vehicleSold) {
        VehicleSold = vehicleSold;
    }
    public abstract double getTotalPrice();
    public abstract double getMonthlyPayment();

}
