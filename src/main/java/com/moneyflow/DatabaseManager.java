package com.moneyflow;

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
            writer.write(transaction.getTransactionData());
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
        Transaction[][] groupedTransactions = new Transaction[100][50]; // Maxi 100 datess and max 50 transactions per date
        String[] uniqueDates = new String[100]; // Store unique dates
        int[] transactionCounts = new int[100]; // for number of transactions per date
        int dateCount =0;
    
        try (Scanner scanner = new Scanner(this.obj)) { // doingbthis allows auto close
            while (scanner.hasNextLine()) {
                String[] transactionData = scanner.nextLine().split(",");
                String date = transactionData[1];
    
                // Convert data into Transaction object
                Transaction transaction = new Transaction(
                    Integer.parseInt(transactionData[0]), // Transaction ID
                    transactionData[1],                   // Date
                    transactionData[2],                   // Type (Income/Expense)
                    transactionData[3],                   // Category
                    Double.parseDouble(transactionData[4]), // Amount
                    transactionData[5]                    // Description
                );
    
                // Find existing date group or create new one
                int dateIndex = -1;
                for (int i = 0; i < dateCount; i++) {
                    if (uniqueDates[i].equals(date)) {
                        dateIndex = i;
                        break;
                    }
                }
    
                if (dateIndex == -1) {
                    dateIndex = dateCount;
                    uniqueDates[dateCount] = date;
                    transactionCounts[dateCount] = 0;
                    dateCount++;
                }
    
                // Add transaction to the correct date group
                int transIndex = transactionCounts[dateIndex];
                groupedTransactions[dateIndex][transIndex] = transaction;
                transactionCounts[dateIndex]++;
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading from database file.");
        }
    
        // Print transactions grouped by date (descending order)
        for (int i = dateCount - 1; i >= 0; i--) {
            String date = uniqueDates[i];
            double dailyTotal = 0;
    
            // Calculate daily total
            for (int j = 0; j < transactionCounts[i]; j++) {
                Transaction transaction = groupedTransactions[i][j];
                if (transaction.getTransactionType().equals("Income")) {
                    dailyTotal += transaction.getTransactionAmount();
                } else {
                    dailyTotal -= transaction.getTransactionAmount();
                }
            }
    
            // Print Date and Daily Total
            System.out.println(date + "  " + (dailyTotal >= 0 ? dailyTotal : "-" + Math.abs(dailyTotal)));
    
            // Print transactions for this date
            for (int j = 0; j < transactionCounts[i]; j++) {
                Transaction transaction = groupedTransactions[i][j];
                // String typeIcon = transaction.getTransactionType().equals("Income") ? "+" : "-";
                System.out.println(transaction.getFormatedTransactionData());
            }
            System.out.println();
        }
    }
}