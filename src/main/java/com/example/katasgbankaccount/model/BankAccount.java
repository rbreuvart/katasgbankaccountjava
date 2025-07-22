package com.example.katasgbankaccount.model;

import java.time.LocalDateTime;
import java.util.List;

public class BankAccount {

    private String accountNumber;
    private double balance;
    private Statement statement;

    public BankAccount(String accountNumber) {
        this.accountNumber = accountNumber;
        this.statement = new Statement();
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        this.balance += amount;
        this.statement.addTransaction(LocalDateTime.now(), amount, this.balance);
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        if (this.balance < amount) {
            throw new IllegalArgumentException("Insufficient funds.");
        }
        this.balance -= amount;
        this.statement.addTransaction(LocalDateTime.now(), -amount, this.balance);
    }

    public List<Transaction> getHistory() {
        return this.statement.getTransactions();
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
