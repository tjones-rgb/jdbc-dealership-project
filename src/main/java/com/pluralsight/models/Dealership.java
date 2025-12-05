package com.pluralsight.models;

import java.util.ArrayList;
import java.util.List;

public class Dealership {

        private String name, address, phone;
        private ArrayList<Vehicle> inventory;

        public Dealership(String name, String address, String phone) {
            this.name = name;
            this.address = address;
            this.phone = phone;
            inventory = new ArrayList<Vehicle>();
        }

        public List<Vehicle> getVehiclesByPrice(int min, int max) {
            ArrayList<Vehicle> filtered = new ArrayList<Vehicle>();
            for (Vehicle v: inventory) {
                if (v.getPrice() > min && v.getPrice() < max) {
                    filtered.add(v);
                }
            }
            return filtered;
        }

        public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
            ArrayList<Vehicle> filtered = new ArrayList<Vehicle>();
            for (Vehicle v: inventory) {
                if (v.getMake().equalsIgnoreCase(make) || v.getModel().equalsIgnoreCase(model)) {
                    filtered.add(v);
                }
            }
            return filtered;
        }

        public List<Vehicle> getVehiclesByYear(int min, int max) {
            ArrayList<Vehicle> filtered = new ArrayList<Vehicle>();
            for (Vehicle v: inventory) {
                if (v.getYear() > min && v.getYear() < max) {
                    filtered.add(v);
                }
            }
            return filtered;
        }

        public List<Vehicle> getVehiclesByColor(String color) {
            ArrayList<Vehicle> filtered = new ArrayList<Vehicle>();
            for (Vehicle v: inventory) {
                if (v.getColor().equalsIgnoreCase(color)) {
                    filtered.add(v);
                }
            }
            return filtered;
        }

        public List<Vehicle> getVehiclesByMileage(int min, int max) {
            ArrayList<Vehicle> filtered = new ArrayList<Vehicle>();
            for (Vehicle v: inventory) {
                if (v.getOdometer() > min && v.getOdometer() < max) {
                    filtered.add(v);
                }
            }
            return filtered;
        }

        public List<Vehicle> getVehiclesByType(String vehicleType) {
            ArrayList<Vehicle> filtered = new ArrayList<Vehicle>();
            for (Vehicle v: inventory) {
                if (v.getVehicleType().equalsIgnoreCase(vehicleType)) {
                    filtered.add(v);
                }
            }
            return filtered;
        }

        public String getName() {
            return name;
        }

        public String getAddress() {
            return address;
        }

        public String getPhone() {
            return phone;
        }

        public Vehicle getVehicleByVin(int vin) {
            Vehicle filtered = null;
            for (Vehicle v: inventory) {
                if (v.getVin() == vin) {
                    filtered = v;
                }
            }
            return filtered;
        }

        public List<Vehicle> getAllVehicles() {
            return this.inventory;
        }

        public void addVehicle(Vehicle v) {
            this.inventory.add(v);
        }

        public void removeVehicle(Vehicle v) {
            this.inventory.remove(v);
        }
    }

