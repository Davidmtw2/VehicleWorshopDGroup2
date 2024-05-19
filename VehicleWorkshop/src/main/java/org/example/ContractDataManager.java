package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContractDataManager {
    public static void saveContract(Contract contract) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/contracts.csv", true))) {
            if (contract instanceof SalesContract) {
                SalesContract salesContract = (SalesContract) contract;
                writer.write(String.format("SALE|%s|%s|%s|%d|%d|%s|%s|%s|%d|%.2f|%.2f|%.2f|%s|%.2f\n",
                        salesContract.getDate(), salesContract.getCustomerName(), salesContract.getCustomerEmail(),
                        salesContract.getVehicleSold().getVin(), salesContract.getVehicleSold().getYear(),
                        salesContract.getVehicleSold().getMake(), salesContract.getVehicleSold().getModel(),
                        salesContract.getVehicleSold().getVehicleType(), salesContract.getVehicleSold().getOdometer(),
                        salesContract.getVehicleSold().getPrice(), salesContract.getTotalPrice(),
                        salesContract.getMonthlyPayment(), salesContract.isFinanced() ? "YES" : "NO",
                        salesContract.getMonthlyPayment()));
            }
            else if (contract instanceof LeaseContract) {
                LeaseContract leaseContract = (LeaseContract) contract;
                writer.write(String.format("LEASE|%s|%s|%s|%d|%d|%s|%s|%s|%d|%.2f|%.2f|%.2f\n",
                        leaseContract.getDate(), leaseContract.getCustomerName(), leaseContract.getCustomerEmail(),
                        leaseContract.getVehicleSold().getVin(), leaseContract.getVehicleSold().getYear(),
                        leaseContract.getVehicleSold().getMake(), leaseContract.getVehicleSold().getModel(),
                        leaseContract.getVehicleSold().getVehicleType(), leaseContract.getVehicleSold().getOdometer(),
                        leaseContract.getVehicleSold().getPrice(), leaseContract.getTotalPrice(),
                        leaseContract.getMonthlyPayment()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
