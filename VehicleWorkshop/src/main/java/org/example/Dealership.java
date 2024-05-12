package org.example;

import java.util.ArrayList;
import java.util.List;

public class Dealership {
    private String name;
    private String address;
    private String phone;
 final2

    private ArrayList<Vehicle> inventory = new ArrayList<>() ;
 main
    //comment out below lines when FileManager Class is Complete
    Vehicle a = new Vehicle(10000,1993,"Ford","Explore","Suv","Red",525123,995.00);
    Vehicle b = new Vehicle(37846,2001,"Ford","Ranger","truck","Yellow",172544,1995.00);
    Vehicle c = new Vehicle (44901,2012,"Honda","Civic","SUV","Gray",103221,6995.00);








    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        //this.inventory = FileManganager.getDealership();
        //comment out below when FileManager Class is Complete
        inventory.add(a);
        inventory.add(b);
        inventory.add(c);


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ArrayList<Vehicle> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Vehicle> inventory) {
        this.inventory = inventory;
    }

    public List<Vehicle> getVehiclesByPrice(double min,double max){
        List<Vehicle> tempList = new ArrayList<>();
        for (Vehicle x : inventory){
            if (x.getPrice() <= max && x.getPrice() >= min){
                tempList.add(x);
            }
        }
        return tempList;
    }
    public List<Vehicle> getVehiclesByMakeModel(String make,String model){
        ArrayList<Vehicle> tempList = new ArrayList<>();
        for(Vehicle v : inventory){
            if (v.getMake().toLowerCase().contains(make.toLowerCase())
                    && v.getModel().toLowerCase().contains(model.toLowerCase())){
                tempList.add(v);
            }
        }
        return tempList;
    }
    public List<Vehicle> getVehiclesByYear(int min, int max){
        List<Vehicle> tempList = new ArrayList<>();
        for (Vehicle x : inventory){
            if (x.getYear() <= max && x.getYear() >= min){
                tempList.add(x);
            }
        }
        return tempList;
    }
    public List<Vehicle> getVehiclesByColor(String color){
        List<Vehicle> tempList = new ArrayList<>();
        if (color != null && !color.isEmpty()) {
            for (Vehicle x : inventory) {
                if (x.getVehicleType().toLowerCase().equals(color.toLowerCase())) {
                    tempList.add(x);
                }
            }
            return tempList;
        }
        else {
            return new ArrayList<>(inventory);
        }
    }
    public List<Vehicle> getVehiclesByMileage(int min,int max){
        List<Vehicle> tempList = new ArrayList<>();
        for (Vehicle x : inventory){
            if (x.getPrice() <= max && x.getPrice() >= min){
                tempList.add(x);
            }
        }
        return tempList;
    }
    public List<Vehicle> getVehiclesByType(String type){
        List<Vehicle> tempList = new ArrayList<>();
        if (type != null && !type.isEmpty()) {
            for (Vehicle x : inventory) {
                if (x.getVehicleType().toLowerCase().equals(type.toLowerCase())) {
                    tempList.add(x);
                }
            }
            return tempList;
        }
        else {
            return new ArrayList<>(inventory);
        }
    }

    public List<Vehicle> getAllVehicles(){
        return inventory;
    }
    public void addVehicle(Vehicle vehicle){
        if (vehicle == null){
            System.out.println("Sorry, cannot add a null vehicle.");
        }
        else if (!inventory.contains(vehicle)){
            System.out.println("Sorry, this vehicle is already in the inventory.");
        }
        else {
            inventory.add(vehicle);
            System.out.println("Vehicle added successfully");
            // DealershipFileManager.Save(): // Uncomment when FileManager is ready
        }

    }
    public void removeVehicle(Vehicle vehicle) {
        if (vehicle == null || !inventory.contains(vehicle)) {
            System.out.println("Sorry, Vehicle Could Not be Found");
        } else {
            inventory.remove(vehicle);
            System.out.println("Vehicle removed successfully.");
            // DealershipFileManager.save(); // Uncomment when FileManager is ready
        }
    }
    public String toString(){
        String output = "";
        for (Vehicle x : inventory){
            output.concat(x+"\n");
        }

        return output;
    }
}
