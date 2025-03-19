package com.moneyflow;

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

    // to be used when there alred exists transactions in the database
    public Transaction(int lastTransactionId, String transactionDate, String transactionType, String transactionCategory, double transactionAmount, String transactionDescription) {
        this.transactionId = ++lastTransactionId + "";
        transactionCount = lastTransactionId;
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

    public String getTransactionData(){
        return this.transactionId + "," + this.transactionDate + "," + this.transactionType + "," + this.transactionCategory + "," + this.transactionAmount + "," + this.transactionDescription + "\n";
    }

    public String getFormatedTransactionData(){
        return String.format("%-5s %-10s %-20s", this.transactionType, this.transactionCategory, this.transactionAmount);
    }
}
