package org.example;

import java.util.*;

public class UI {

    private static Dealership sorting;
    public static String userInput(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

//    public static void dealershipSelect(){
//
//        while (true) {
//            System.out.println("dealership options");
//
//            switch (userInput().toLowerCase()){
//                case "a" -> DealershipFileManager.filePath = "path1";
//                case "b" -> DealershipFileManager.filePath = "path2";
//                case "c" -> DealershipFileManager.filePath = "path3";
//                case "x" -> System.exit(0);
//                default -> System.out.println("reselect prompt");
//            }
//
//        }
//    }

    public static void homeScreen(){
        sorting = DealershipFileManager.getDealership();
        while (true){
            System.out.println("Select your input");

            switch (userInput().toLowerCase()){
                case"1"-> processGetByPriceRequest();
                case"2"-> processGetByMakeModelRequest();
                case"3"-> processGetByYearRequest();
                case"4"-> processGetByColorRequest();
                case"5"-> processGetByMileageRequest();
                case"6"-> processGetByVehicleTypeRequest();
                case"7"-> processAllVehicleRequest();
                case"8"-> processAddVehicleRequest();
                case"9"-> processRemoveVehicleRequest();
//                case"0"-> dealershipSelect();
                case"x"-> System.exit(0);
                default-> System.out.println("reselect prompt");
            }
        }
    }
    private static void processGetByPriceRequest(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("input price range prompt");
        System.out.println("highest price");
        String maxPriceInput = scanner.nextLine();

        System.out.println("lowest price");
        String minPriceInput = scanner.nextLine();

        Double maxPrice = 9999999.00;
        Double minPrice = 0.00;

        try {
            if (!maxPriceInput.isEmpty()){
                maxPrice = Double.parseDouble(maxPriceInput);
            }
        }
        catch (NumberFormatException ex){
            System.out.println("not a numerical input prompt");
        }

        try {
            if (!minPriceInput.isEmpty()){
                minPrice = Double.parseDouble(minPriceInput);
            }
        }
        catch (NumberFormatException ex){
            System.out.println("not a numerical input prompt");
        }
            for(Vehicle x :sorting.getVehiclesByPrice(minPrice,maxPrice)){
                System.out.println(x);
            }

final2
        //System.out.println(sorting.getVehiclesByPrice(minPrice,maxPrice).toString());
        for (Vehicle x: sorting.getVehiclesByPrice(minPrice,maxPrice)) {
            System.out.println(x);
        }
 main
    }
    private static void processGetByMakeModelRequest(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("input make");
        String make = scanner.nextLine();

        System.out.println("input model");
        String model = scanner.nextLine();

        for (Vehicle x: sorting.getVehiclesByMakeModel(make,model)) {
            System.out.println(x);
        }

    }
    private static void processGetByYearRequest(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("input year range prompt");
        System.out.println("latest year");
        String maxYearInput = scanner.nextLine();

        System.out.println("earliest year");
        String minYearInput = scanner.nextLine();

        int maxYear = 3000;
        int minYear = 1900;

        try {
            if (!maxYearInput.isEmpty()){
                maxYear = Integer.parseInt(maxYearInput);
            }
        }
        catch (NumberFormatException ex){
            System.out.println("not a numerical input prompt");
        }

        try {
            if (!minYearInput.isEmpty()){
                minYear = Integer.parseInt(minYearInput);
            }
        }
        catch (NumberFormatException ex){
            System.out.println("not a numerical input prompt");
        }

        //System.out.println(sorting.getVehiclesByYear(minYear,maxYear).toString());
        for (Vehicle x: sorting.getVehiclesByYear(minYear,maxYear)) {
            System.out.println(x);
        }
    }
    private static void processGetByColorRequest(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("input color");
        String color = scanner.nextLine();

        for (Vehicle x: sorting.getVehiclesByColor(color)) {
            System.out.println(x);
        }
    }
    private static void processGetByMileageRequest(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("input mileage range prompt");
        System.out.println("highest mileage");
        String maxOdometerInput = scanner.nextLine();

        System.out.println("lowest mileage");
        String minOdometerInput = scanner.nextLine();

        int maxOdometer = 999999;
        int minOdometer = 0;

        try {
            if (!maxOdometerInput.isEmpty()){
                maxOdometer = Integer.parseInt(maxOdometerInput);
            }
        }
        catch (NumberFormatException ex){
            System.out.println("not a numerical input prompt");
        }

        try {
            if (!minOdometerInput.isEmpty()){
                minOdometer = Integer.parseInt(minOdometerInput);
            }
        }
        catch (NumberFormatException ex){
            System.out.println("not a numerical input prompt");
        }

        //System.out.println(sorting.getVehiclesByMileage(minOdometer,maxOdometer).toString());
        for (Vehicle x: sorting.getVehiclesByMileage(minOdometer,maxOdometer)) {
            System.out.println(x);
        }
    }
    private static void processGetByVehicleTypeRequest(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("input vehicle type");
        String vehicleType = scanner.nextLine();

        for (Vehicle x: sorting.getVehiclesByType(vehicleType)) {
            System.out.println(x);
        }
    }
    private static void processAllVehicleRequest(){
        for(Vehicle x : sorting.getAllVehicles()){
            System.out.println(x);
        }

    }
    private static void processAddVehicleRequest(){
        Scanner scanner = new Scanner(System.in);

        while (true) {

            try {
                System.out.println("input vin");
                int vin = Integer.parseInt(scanner.nextLine());

                System.out.println("input year");
                int year = Integer.parseInt(scanner.nextLine());

                System.out.println("input model");
                String model = scanner.nextLine();

                System.out.println("input make");
                String make = scanner.nextLine();

                System.out.println("input color");
                String color = scanner.nextLine();

                System.out.println("input vehicle type");
                String vehicleType = scanner.nextLine();

                System.out.println("input odometer mileage");
                int odometer = Integer.parseInt(scanner.nextLine());

                System.out.println("input price");
                double price = Double.parseDouble(scanner.nextLine());

                Vehicle addVehicle = new Vehicle(vin,year,model,make,color,vehicleType,odometer,price);
                sorting.addVehicle(addVehicle);
            }
            catch (NumberFormatException ex) {
                System.out.println("need numerical input");
            }
            DealershipFileManager.saveVehicles(sorting);
            homeScreen();
        }
    }
    private static void processRemoveVehicleRequest(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("input vin being removed");
        String vinInput = scanner.nextLine();
        int vin = Integer.parseInt(vinInput);

        boolean removed = false;
        for(int x = 0; x < sorting.getAllVehicles().size() && removed == false; x++){
            if (sorting.getAllVehicles().get(x).getVin() == vin) {
                sorting.removeVehicle(sorting.getAllVehicles().get(x));
                removed = true;
            }
        }
        DealershipFileManager.saveVehicles(sorting);
    }


}
