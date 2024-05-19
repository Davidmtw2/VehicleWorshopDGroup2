package org.example;

public class LeaseContract extends Contract {
    private static final double LEASE_FEE_RATE = 0.07;
    private static final double EXPECTED_ENDING_VALUE_RATE = 0.50;
    private static final double LEASE_INTEREST_RATE = 0.04;

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(date, customerName, customerEmail, vehicleSold);
        calculateTotalPrice();
        calculateMonthlyPayment();
    }

    @Override
    public void calculateTotalPrice() {
        double vehiclePrice = getVehicleSold().getPrice();
        double leaseFee = vehiclePrice * LEASE_FEE_RATE;
        double expectedEndingValue = vehiclePrice * EXPECTED_ENDING_VALUE_RATE;
        this.totalPrice = leaseFee + expectedEndingValue;
    }

    @Override
    public void calculateMonthlyPayment() {
        double vehiclePrice = getVehicleSold().getPrice();
        double loanAmount = vehiclePrice - getTotalPrice();
        int leaseTerm = 36;
        this.monthlyPayment = loanAmount * (LEASE_INTEREST_RATE / 12) / (1 - Math.pow(1 + LEASE_INTEREST_RATE / 12, -leaseTerm));
    }
}




