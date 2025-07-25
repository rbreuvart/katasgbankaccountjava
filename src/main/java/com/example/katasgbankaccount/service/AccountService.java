package com.example.katasgbankaccount.service;

import com.example.katasgbankaccount.model.BankAccount;
import com.example.katasgbankaccount.model.Transaction;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        double newBalance = account.getBalance() + amount;
        account.setBalance(newBalance);
        account.getStatement().addTransaction(new Transaction(LocalDateTime.now(), amount, newBalance));
    }

    public void withdraw(String accountNumber, double amount) {
        BankAccount account = getAccount(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException("Account not found.");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        if (account.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient funds.");
        }
        double newBalance = account.getBalance() - amount;
        account.setBalance(newBalance);
        account.getStatement().addTransaction(new Transaction(LocalDateTime.now(), -amount, newBalance));
    }

    public List<Transaction> getHistory(String accountNumber) {
        BankAccount account = getAccount(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException("Account not found.");
        }
        return account.getHistory();
    }

    public void deleteAccount(String accountNumber) {
        BankAccount account = getAccount(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException("Account not found.");
        }
        if (account.getBalance() != 0) {
            throw new IllegalStateException("Cannot delete account with non-zero balance.");
        }
        accounts.remove(accountNumber);
    }
}
