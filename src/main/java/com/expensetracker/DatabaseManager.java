package com.expensetracker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DatabaseManager {
    File obj;
    public DatabaseManager(String databasePath) {
        this.obj = new File(databasePath);
        if (!obj.exists()) {
            System.out.println("Database file does not exist. Creating a new database file.");
            try {
                obj.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Error creating database file.");
            }
        } else {
            System.out.println("Database file exists.");
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
    
}
