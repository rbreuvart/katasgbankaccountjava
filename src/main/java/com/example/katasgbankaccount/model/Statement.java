package com.example.katasgbankaccount.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Statement {

    private List<Transaction> transactions;

    public Statement() {
        this.transactions = new ArrayList<>();
    }

    public void addTransaction(LocalDateTime date, double amount, double balance) {
        this.transactions.add(new Transaction(date, amount, balance));
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
