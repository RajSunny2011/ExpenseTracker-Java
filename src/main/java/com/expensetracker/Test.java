package com.expensetracker;

public class Test {
    public static void main(String[] args) {
        DatabaseManager db = new DatabaseManager("database.csv");
        int id = db.openDatabase();
        Transaction t1 = new Transaction(id, "2021-01-01", "Income", "Salary", 50000, "Salary for January 2021");
        Transaction t2 = new Transaction("2021-01-02", "Expense", "Rent", 10000, "Rent for January 2021");
        db.addTransaction(t1);
        db.addTransaction(t2);
        db.viewAllTransactions();
    }
}