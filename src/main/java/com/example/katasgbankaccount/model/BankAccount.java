package com.example.katasgbankaccount.model;

import java.util.List;

public class BankAccount {

    private String accountNumber;
    private double balance;
    private Statement statement;

    public BankAccount(String accountNumber) {
        this.accountNumber = accountNumber;
        this.statement = new Statement();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Statement getStatement() {
        return statement;
    }

    public List<Transaction> getHistory() {
        return this.statement.getTransactions();
    }
}
