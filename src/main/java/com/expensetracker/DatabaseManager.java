package com.expensetracker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DatabaseManager {
    File obj;
    public DatabaseManager(String databasePath) {
        this.obj = new File(databasePath);
    }

    public boolean isDatabaseEmpty() {
        return obj.length() == 0;
    }

    // returns the last transaction id
    public int openDatabase() {
        if (isDatabaseEmpty()) {
            return -1;
        }
        try (Scanner scanner = new Scanner(obj);) {
            int lastTransactionId = 0;
            while (scanner.hasNextLine()) {
                String transaction = scanner.nextLine();
                String[] transactionData = transaction.split(",");
                lastTransactionId = Integer.parseInt(transactionData[0]);
            }
            return lastTransactionId;
        } catch (IOException e) {
            throw new RuntimeException("Error reading from database file.");
        }
    }

    public void addTransaction(Transaction transaction) {
        try (FileWriter writer = new FileWriter(obj, true)) {
            writer.write(transaction.getTransactionId() + "," + transaction.getTransactionDate() + "," + transaction.getTransactionType() + "," + transaction.getTransactionCategory() + "," + transaction.getTransactionAmount() + "," + transaction.getTransactionDescription() + "\n");
        } catch (IOException e) {
            throw new RuntimeException("Error writing to database file.");
        }
    }
    
    public void viewAllTransactions() {
        System.out.format("%-20s%-20s%-20s%-20s%-20s%-20s%n", "Transaction ID", "Date", "Type", "Category", "Amount", "Description");
        final Object[][] table = new String[100][]; 
        int rowIndex = 0;
        double transactionTotal = 0;
    
        try (Scanner scanner = new Scanner(obj);) {
            while (scanner.hasNextLine()) {
                String transaction = scanner.nextLine();
                // Split the transaction line by commas
                String[] transactionData = transaction.split(",");

                if (transactionData[2].equals("Income")) {
                    transactionTotal += Double.parseDouble(transactionData[4]);
                } else {
                    transactionTotal -= Double.parseDouble(transactionData[4]);
                }
    
                // Add this transaction to the table array
                table[rowIndex] = transactionData;
                rowIndex++;
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading from database file.");
        }
    
        // Print the table with formatted output
        for (int i = 0; i < rowIndex; i++) {
            Object[] row = table[i];
            System.out.format("%-20s%-20s%-20s%-20s%-20s%-20s%n", row);
        }
        System.out.println("Total: " + transactionTotal);
    }
    

public void viewRecentTransactions() {
    final Object[][] table = new String[100][]; 
    int rowIndex = 0;

    try (Scanner scanner = new Scanner(obj);) {
        while (scanner.hasNextLine()) {
            String transaction = scanner.nextLine();
            // Split the transaction line by commas
            String[] transactionData = transaction.split(",");

            // Add this transaction to the table array
            String[] data = {transactionData[5], transactionData[3], transactionData[2], transactionData[4]};
            table[rowIndex] = data;
            rowIndex++;
        }
    } catch (IOException e) {
        throw new RuntimeException("Error reading from database file.");
    }

    // Print the table with formatted output
   for(int i=rowIndex-1; i>=0;i--) {
    Object[] row= table[i];
    System.out.format("%-30a%-20s%-10s%-10s%n",row);
}
}}