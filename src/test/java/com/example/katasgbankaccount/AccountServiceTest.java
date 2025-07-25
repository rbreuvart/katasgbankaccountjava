package com.example.katasgbankaccount;

import com.example.katasgbankaccount.model.BankAccount;
import com.example.katasgbankaccount.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Test
    void testCreateAccount() {
        BankAccount account = accountService.createAccount("12345");
        assertNotNull(account);
        assertEquals("12345", account.getAccountNumber());
    }

    @Test
    void testDeposit() {
        accountService.createAccount("12345");
        accountService.deposit("12345", 100);
        assertEquals(100, accountService.getAccount("12345").getHistory().get(0).getBalance());
    }

    @Test
    void testWithdraw() {
        accountService.createAccount("12345");
        accountService.deposit("12345", 100);
        accountService.withdraw("12345", 50);
        assertEquals(50, accountService.getAccount("12345").getHistory().get(1).getBalance());
    }

    @Test
    void testGetHistory() {
        accountService.createAccount("12345");
        accountService.deposit("12345", 100);
        accountService.withdraw("12345", 50);
        assertEquals(2, accountService.getHistory("12345").size());
    }

    @Test
    void testDeleteAccount() {
        accountService.createAccount("12345");
        accountService.deleteAccount("12345");
        assertNull(accountService.getAccount("12345"));
    }
}
