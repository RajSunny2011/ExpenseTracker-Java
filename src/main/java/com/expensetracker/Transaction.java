package com.expensetracker;

public class Transaction {
    static int transactionCount = 0;
    private String transactionId;
    private String transactionDate;
    private String transactionType;
    private String transactionCategory;
    private double transactionAmount;
    private String transactionDescription;

    public Transaction(String transactionDate, String transactionType, String transactionCategory, double transactionAmount, String transactionDescription) {
        this.transactionId = transactionCount + "";
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
        this.transactionCategory = transactionCategory;
        this.transactionAmount = transactionAmount;
        this.transactionDescription = transactionDescription;
        transactionCount++;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionCategory() {
        return transactionCategory;
    }

    public void setTransactionCategory(String transactionCategory) {
        this.transactionCategory = transactionCategory;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionDescription() {
        return transactionDescription;
    }

    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }
}
