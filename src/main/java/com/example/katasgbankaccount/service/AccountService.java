package com.example.katasgbankaccount.service;

import com.example.katasgbankaccount.model.BankAccount;
import com.example.katasgbankaccount.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AccountService {

    private final Map<String, BankAccount> accounts = new ConcurrentHashMap<>();

    public BankAccount createAccount(String accountNumber) {
        BankAccount newAccount = new BankAccount(accountNumber);
        accounts.put(accountNumber, newAccount);
        return newAccount;
    }

    public BankAccount getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public void deposit(String accountNumber, double amount) {
        BankAccount account = getAccount(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException("Account not found.");
        }
        account.deposit(amount);
    }

    public void withdraw(String accountNumber, double amount) {
        BankAccount account = getAccount(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException("Account not found.");
        }
        account.withdraw(amount);
    }

    public List<Transaction> getHistory(String accountNumber) {
        BankAccount account = getAccount(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException("Account not found.");
        }
        return account.getHistory();
    }
}
