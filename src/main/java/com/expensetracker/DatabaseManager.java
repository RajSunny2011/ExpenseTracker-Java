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
        System.out.println("Transaction ID   Transaction Date   Transaction Type   Transaction Category   Transaction Amount   Transaction Description");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
        final Object[][] table = new String[100][]; 
        int rowIndex = 0;
    
        try (Scanner scanner = new Scanner(obj);) {
            while (scanner.hasNextLine()) {
                String transaction = scanner.nextLine();
                // Split the transaction line by commas
                String[] transactionData = transaction.split(",");
    
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