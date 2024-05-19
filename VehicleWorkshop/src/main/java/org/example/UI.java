package org.example;

import java.util.Scanner;

public class UI {

    private static Dealership sorting;

    public static String userInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void homeScreen() {
        sorting = DealershipFileManager.getDealership();
        while (true) {
            System.out.println("""
                    Welcome to the Dealership
                    Please select from the following options.
                    1.) Filter vehicles by price
                    2.) Filter vehicles by make and model
                    3.) Filter vehicles by year
                    4.) Filter vehicles by color
                    5.) Filter vehicles by mileage
                    6.) Filter vehicles by vehicle type
                    7.) Show all vehicles
                    8.) Add a vehicle
                    9.) Remove a Vehicle
                    10.) Sell/Lease a Vehicle
                    0.) Exit software
                    """);

            switch (userInput().toLowerCase()) {
                case "1" -> processGetByPriceRequest();
                case "2" -> processGetByMakeModelRequest();
                case "3" -> processGetByYearRequest();
                case "4" -> processGetByColorRequest();
                case "5" -> processGetByMileageRequest();
                case "6" -> processGetByVehicleTypeRequest();
                case "7" -> processAllVehicleRequest();
                case "8" -> processAddVehicleRequest();
                case "9" -> processRemoveVehicleRequest();
                case "10" -> processSellLeaseVehicleRequest();
                case "0" -> System.exit(0);
                default -> System.out.println("Please select from the menu using the numerical values listed");
            }
        }
    }

    private static void processSellLeaseVehicleRequest() {
        Scanner scanner = new Scanner(System.in);

        // Prompt user to enter the VIN of the vehicle to sell or lease
        System.out.println("Enter the VIN of the vehicle to sell/lease:");
        int vin = Integer.parseInt(scanner.nextLine());

        // Find the vehicle in the inventory based on the VIN
        Vehicle vehicle = sorting.getAllVehicles().stream()
                .filter(v -> v.getVin() == vin)
                .findFirst()
                .orElse(null);

        // If vehicle is not found, display a message and return
        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        // Prompt user to specify if it's a sale or a lease
        System.out.println("Is this a sale or a lease? (Enter 'sale' or 'lease')");
        String contractType = scanner.nextLine().toLowerCase();

        // Prompt user to enter customer details
        System.out.println("Enter customer name:");
        String customerName = scanner.nextLine();

        System.out.println("Enter customer email:");
        String customerEmail = scanner.nextLine();

        // Prompt user to enter the date of the contract
        System.out.println("Enter the date (YYYYMMDD):");
        String date = scanner.nextLine();

        Contract contract;
        // Create a SalesContract if the type is 'sale'
        if (contractType.equals("sale")) {
            System.out.println("Is this financed? (yes/no)");
            boolean isFinanced = scanner.nextLine().equalsIgnoreCase("yes");
            contract = new SalesContract(date, customerName, customerEmail, vehicle, isFinanced);
        } else {
            // Create a LeaseContract if the type is 'lease'
            contract = new LeaseContract(date, customerName, customerEmail, vehicle);
        }

        // Save the contract to the contracts file
        ContractDataManager.saveContract(contract);
        // Remove the vehicle from the inventory
        sorting.removeVehicle(vehicle);
        // Save the updated dealership inventory
        DealershipFileManager.saveDealership(sorting);
    }

    private static void processGetByPriceRequest() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Search within a Price Range");
        System.out.println("Enter highest price:");
        String maxPriceInput = scanner.nextLine();

        System.out.println("Enter lowest price:");
        String minPriceInput = scanner.nextLine();

        Double maxPrice = 9999999.00;
        Double minPrice = 0.00;

        try {
            if (!maxPriceInput.isEmpty()) {
                maxPrice = Double.parseDouble(maxPriceInput);
            }
        } catch (NumberFormatException ex) {
            System.out.println("Not a numerical input. Sending back to home screen!");
        }

        try {
            if (!minPriceInput.isEmpty()) {
                minPrice = Double.parseDouble(minPriceInput);
            }
        } catch (NumberFormatException ex) {
            System.out.println("Not a numerical input. Sending back to home screen!");
        }

        System.out.println("Vin|Year|Model|Make|Color|VehicleType|Odometer|Price");
        for (Vehicle x : sorting.getVehiclesByPrice(minPrice, maxPrice)) {
            System.out.println(x);
        }
    }

    private static void processGetByMakeModelRequest() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Search by Make & Model");
        System.out.println("Enter desired make:");
        String make = scanner.nextLine();

        System.out.println("Enter desired model:");
        String model = scanner.nextLine();

        System.out.println("Vin|Year|Model|Make|Color|VehicleType|Odometer|Price");
        for (Vehicle x : sorting.getVehiclesByMakeModel(make, model)) {
            System.out.println(x);
        }
    }

    private static void processGetByYearRequest() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Search within a Year Range");
        System.out.println("Enter latest year:");
        String maxYearInput = scanner.nextLine();

        System.out.println("Enter earliest year:");
        String minYearInput = scanner.nextLine();

        int maxYear = 3000;
        int minYear = 1900;

        try {
            if (!maxYearInput.isEmpty()) {
                maxYear = Integer.parseInt(maxYearInput);
            }
        } catch (NumberFormatException ex) {
            System.out.println("Not a numerical input. Sending back to home screen!");
        }

        try {
            if (!minYearInput.isEmpty()) {
                minYear = Integer.parseInt(minYearInput);
            }
        } catch (NumberFormatException ex) {
            System.out.println("Not a numerical input. Sending back to home screen!");
        }

        System.out.println("Vin|Year|Model|Make|Color|VehicleType|Odometer|Price");
        for (Vehicle x : sorting.getVehiclesByYear(minYear, maxYear)) {
            System.out.println(x);
        }
    }

    private static void processGetByColorRequest() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Search by Color");
        System.out.println("Enter desired color:");
        String color = scanner.nextLine();

        System.out.println("Vin|Year|Model|Make|Color|VehicleType|Odometer|Price");
        for (Vehicle x : sorting.getVehiclesByColor(color)) {
            System.out.println(x);
        }
    }

    private static void processGetByMileageRequest() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Search within a Mileage Range");
        System.out.println("Enter highest mileage:");
        String maxOdometerInput = scanner.nextLine();

        System.out.println("Enter lowest mileage:");
        String minOdometerInput = scanner.nextLine();

        int maxOdometer = 999999;
        int minOdometer = 0;

        try {
            if (!maxOdometerInput.isEmpty()) {
                maxOdometer = Integer.parseInt(maxOdometerInput);
            }
        } catch (NumberFormatException ex) {
            System.out.println("Not a numerical input. Sending back to home screen!");
        }

        try {
            if (!minOdometerInput.isEmpty()) {
                minOdometer = Integer.parseInt(minOdometerInput);
            }
        } catch (NumberFormatException ex) {
            System.out.println("Not a numerical input. Sending back to home screen!");
        }

        System.out.println("Vin|Year|Model|Make|Color|VehicleType|Odometer|Price");
        for (Vehicle x : sorting.getVehiclesByMileage(minOdometer, maxOdometer)) {
            System.out.println(x);
        }
    }

    private static void processGetByVehicleTypeRequest() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter vehicle type:");
        String vehicleType = scanner.nextLine();

        System.out.println("Vin|Year|Model|Make|Color|VehicleType|Odometer|Price");
        for (Vehicle x : sorting.getVehiclesByType(vehicleType)) {
            System.out.println(x);
        }
    }

    private static void processAllVehicleRequest() {
        System.out.println("Vin|Year|Model|Make|Color|VehicleType|Odometer|Price");
        for (Vehicle x : sorting.getAllVehicles()) {
            System.out.println(x);
        }
    }

    private static void processAddVehicleRequest() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("Input VIN:");
                int vin = Integer.parseInt(scanner.nextLine());

                System.out.println("Input year:");
                int year = Integer.parseInt(scanner.nextLine());

                System.out.println("Input model:");
                String model = scanner.nextLine();

                System.out.println("Input make:");
                String make = scanner.nextLine();

                System.out.println("Input color:");
                String color = scanner.nextLine();

                System.out.println("Input vehicle type:");
                String vehicleType = scanner.nextLine();

                System.out.println("Input odometer mileage:");
                int odometer = Integer.parseInt(scanner.nextLine());

                System.out.println("Input price:");
                double price = Double.parseDouble(scanner.nextLine());

                Vehicle addVehicle = new Vehicle(vin, year, model, make, color, vehicleType, odometer, price);
                sorting.addVehicle(addVehicle);
            } catch (NumberFormatException ex) {
                System.out.println("Not a numerical input. Sending back to home screen!");
            }
            DealershipFileManager.saveDealership(sorting);
            homeScreen();
        }
    }

    private static void processRemoveVehicleRequest() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the VIN of the vehicle being removed:");
        String vinInput = scanner.nextLine();
        int vin = Integer.parseInt(vinInput);

        boolean removed = false;
        for (int x = 0; x < sorting.getAllVehicles().size() && !removed; x++) {
            if (sorting.getAllVehicles().get(x).getVin() == vin) {
                sorting.removeVehicle(sorting.getAllVehicles().get(x));
                removed = true;
            }
        }
        DealershipFileManager.saveDealership(sorting);
    }
}