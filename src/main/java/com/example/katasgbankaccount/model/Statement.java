package com.example.katasgbankaccount.model;

import java.util.ArrayList;
import java.util.List;

public class Statement {

    private List<Transaction> transactions;

    public Statement() {
        this.transactions = new ArrayList<>();
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }
}
