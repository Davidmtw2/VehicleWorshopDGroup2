package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DealershipFileManager {

    private String dealershipName;

    public DealershipFileManager(String dealershipName){
        this.dealershipName = dealershipName;
    }

    public String getDealershipName() {
        return dealershipName;
    }

    public void saveVehicles(List<Car> vehicles) {
        try {
            FileWriter writer = new FileWriter("vehicles.csv");
            for (Car vehicle : vehicles){
                writer.write(vehicle.toString() + "\n");
            }
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void updateVehicle(Car vehicle) {
        // Implementation for updating a vehicle in the future
    }

    public void deleteVehicle(int vin) {
        // Implementation for deleting a vehicle in the future
    }

    public static void main(String[] args) {
        // Test saving vehicles
        DealershipFileManager dealershipFileManager = new DealershipFileManager("ABC Dealership");
        List<Car> vehicles = new ArrayList<>();
        vehicles.add(new Car(10112, 1993, "Ford", "Explorer", "SUV", "Red", 525123, 995.00));
        vehicles.add(new Car(37846, 2001, "Ford", "Ranger", "truck", "Yellow", 172544, 1995.00));
        vehicles.add(new Car(44901, 2012, "Honda", "Civic", "SUV", "Gray", 103221, 6995.00));
        dealershipFileManager.saveVehicles(vehicles);
    }

    static class Car {
        private int vin;
        private int year;
        private String make;
        private String model;
        private String type;
        private String color;
        private int mileage;
        private double price;

        public Car(int vin, int year, String make, String model, String type, String color, int mileage, double price) {
            this.vin = vin;
            this.year = year;
            this.make = make;
            this.model = model;
            this.type = type;
            this.color = color;
            this.mileage = mileage;
            this.price = price;
        }

        @Override
        public String toString() {
            return String.format("%d|%d|%s|%s|%s|%s|%d|%.2f",
                    vin, year, make, model, type, color, mileage, price);
        }
    }
}
