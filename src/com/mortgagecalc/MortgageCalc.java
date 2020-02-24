package com.mortgagecalc;
import java.text.NumberFormat;
import java.util.Scanner;

public class MortgageCalc {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        final byte MONTHS_IN_YEAR = 12;
        final byte PERCENT = 100;
        double principal = 0;
        float annualInterestRate = 0;
        float monthlyInterestRate = 0;
        byte period = 0;
        int payments = 0;

        while (principal < 1_000) {
            System.out.println("Enter Principal (minimum $1,000): ");
            String input = scanner.nextLine();
            try {
                principal = Double.parseDouble(input);
            } catch (NumberFormatException e) {
                principal = 0;
                System.out.println("Please enter a valid number greater than or equal to $1,000");
            }
        }

        while (annualInterestRate < 1 || annualInterestRate > 30) {
            System.out.print("Enter Interest Rate (1-30): ");
            String input = scanner.nextLine();
            try {
                annualInterestRate = Float.parseFloat(input);
                monthlyInterestRate = annualInterestRate / PERCENT / MONTHS_IN_YEAR;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number greater than or equal to 1");
                annualInterestRate = 0;
            }
        }

        while (period < 1) {
            System.out.print("Period (Minimum 1 Year): ");
            String input = scanner.nextLine();
            try {
                period = Byte.parseByte(input);
                payments = period * MONTHS_IN_YEAR;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number greater than or equal to 1");
                period = 0;
            }
        }

        double mortgage = principal * (monthlyInterestRate * Math.pow(1 + monthlyInterestRate, payments))
                / (Math.pow(1 + monthlyInterestRate, payments) - 1);

        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.print("Mortgage: " + mortgageFormatted);
    }
}
