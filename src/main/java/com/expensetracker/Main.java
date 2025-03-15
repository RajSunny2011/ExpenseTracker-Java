package com.expensetracker;
import java.util.Scanner;

public class Main {
// Very Basic Needs to be improved
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the database path
        System.out.print("Enter the path to the database file: ");
        String databasePath = scanner.nextLine();
        DatabaseManager dbManager = new DatabaseManager(databasePath);

        while (true) {
            clearScreen(); // Clears the console before showing the menu
            
            // The menu
            System.out.println("Select an option:");
            System.out.println("1. Add a Transaction");
            System.out.println("2. View Recent Transactions");
            System.out.println("3. View All Transactions");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            clearScreen();

            switch (choice) {
                case 1 -> {
                    // Add a new transaction
                    System.out.print("Enter transaction date (YYYY-MM-DD): ");
                    String date = scanner.nextLine();
                    System.out.print("Enter transaction type (Income/Expense): ");
                    String type = scanner.nextLine();
                    System.out.print("Enter transaction category: ");
                    String category = scanner.nextLine();
                    System.out.print("Enter transaction amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter transaction description: ");
                    String description = scanner.nextLine();
                    Transaction transaction = new Transaction(date, type, category, amount, description);

                    // Add the transaction to the database
                    dbManager.addTransaction(transaction);
                    System.out.println("Transaction added successfully!");
                }

                case 2 -> {
                    // View recent transactions
                    System.out.println("Recent Transactions:");
                    dbManager.viewRecentTransactions();
                }

                case 3 -> {
                    // View all transactions
                    System.out.println("All Transactions:");
                    dbManager.viewAllTransactions();
                }

                case 4 -> {
                    // Exit the program
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                }

                default -> System.out.println("Invalid choice, try again.");
            }
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine(); // wait
        }
    }

    private static void clearScreen() {
        // Code to clear the screen (doesn't work on CMD)
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

