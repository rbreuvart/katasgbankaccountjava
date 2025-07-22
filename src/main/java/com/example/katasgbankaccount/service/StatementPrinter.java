package com.example.katasgbankaccount.service;

import com.example.katasgbankaccount.model.BankAccount;
import com.example.katasgbankaccount.model.Transaction;
import org.springframework.stereotype.Service;

@Service
public class StatementPrinter {

    public String printStatement(BankAccount account) {
        if (account == null) {
            throw new IllegalArgumentException("Account not found.");
        }
        StringBuilder lines = new StringBuilder();
        for (Transaction transaction : account.getHistory()) {
            String line = String.format("Date: %s, Amount: %.2f, Balance: %.2f",
                    transaction.getDate(), transaction.getAmount(), transaction.getBalance());
            lines.append(line).append("\n");
        }
        return lines.toString();
    }
}
