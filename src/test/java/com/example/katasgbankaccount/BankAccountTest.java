package com.example.katasgbankaccount;

import com.example.katasgbankaccount.model.BankAccount;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BankAccountTest {

    @Test
    void testDeposit() {
        BankAccount account = new BankAccount("12345");
        account.deposit(100);
        assertEquals(100, account.getHistory().get(0).getBalance());
    }

    @Test
    void testWithdraw() {
        BankAccount account = new BankAccount("12345");
        account.deposit(100);
        account.withdraw(50);
        assertEquals(50, account.getHistory().get(1).getBalance());
    }

    @Test
    void testWithdrawInsufficientFunds() {
        BankAccount account = new BankAccount("12345");
        account.deposit(100);
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(150));
    }
}
