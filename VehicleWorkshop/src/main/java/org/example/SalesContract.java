package org.example;

public class SalesContract extends Contract{
    private static final double SALES_TAX_RATE = 0.05;
    private static final double RECORDING_FEE = 100.00;
    private static final double PROCESSING_FEE_UNDER_10000 = 295.00;
    private static final double PROCESSING_FEE_OVER_10000 = 495.00;
    private boolean isFinanced;

    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicleSold, boolean isFinanced) {
        super(date, customerName, customerEmail, vehicleSold);
        this.isFinanced = isFinanced;
        calculateTotalPrice();
        calculateMonthlyPayment();
    }
    public boolean isFinanced() {
        return isFinanced;
    }

    @Override
    public void calculateTotalPrice() {
        double vehiclePrice = getVehicleSold().getPrice();
        double salesTax = vehiclePrice * SALES_TAX_RATE;
        double processingFee = vehiclePrice <10000 ? PROCESSING_FEE_UNDER_10000 : PROCESSING_FEE_OVER_10000;
        this.totalPrice = vehiclePrice + salesTax + RECORDING_FEE + processingFee;
    }

    @Override
    public void calculateMonthlyPayment() {
        if (!isFinanced) {
            this.monthlyPayment = 0;
            return;
        }
        double vehiclePrice = getVehicleSold().getPrice();
        double loanAmount = vehiclePrice - getTotalPrice();
        double interestRate;
        int loanTerm;

        if (vehiclePrice >= 10000) {
            interestRate = 0.0425;
            loanTerm = 48;
        }
        else {
            interestRate = 0.0525;
            loanTerm = 24;
        }
        this.monthlyPayment = loanAmount * (interestRate / 12) / (1 - Math.pow(1 + interestRate / 12, -loanTerm));

    }


}
