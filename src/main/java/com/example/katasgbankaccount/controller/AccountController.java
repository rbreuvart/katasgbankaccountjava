package com.example.katasgbankaccount.controller;

import com.example.katasgbankaccount.model.AmountDto;
import com.example.katasgbankaccount.model.Transaction;
import com.example.katasgbankaccount.service.AccountService;
import com.example.katasgbankaccount.service.StatementPrinter;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@Api(value = "Bank Account Management System", description = "Operations pertaining to bank accounts in the Bank Account Management System")
public class AccountController {

    private final AccountService accountService;
    private final StatementPrinter statementPrinter;

    public AccountController(AccountService accountService, StatementPrinter statementPrinter) {
        this.accountService = accountService;
        this.statementPrinter = statementPrinter;
    }

    @PostMapping("/{accountNumber}")
    @ApiOperation(value = "Create a new bank account", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created account"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public ResponseEntity<String> createAccount(
            @ApiParam(value = "Account Number to create", required = true) @PathVariable String accountNumber) {
        accountService.createAccount(accountNumber);
        return ResponseEntity.ok("Account created successfully.");
    }

    @PostMapping("/{accountNumber}/deposit")
    @ApiOperation(value = "Deposit an amount into a bank account", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deposited"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public ResponseEntity<String> deposit(
            @ApiParam(value = "Account Number to deposit into", required = true) @PathVariable String accountNumber,
            @RequestBody AmountDto amount) {
        accountService.deposit(accountNumber, amount.getAmount());
        return ResponseEntity.ok("Deposit successful.");
    }

    @PostMapping("/{accountNumber}/withdraw")
    @ApiOperation(value = "Withdraw an amount from a bank account", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully withdrew"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public ResponseEntity<String> withdraw(
            @ApiParam(value = "Account Number to withdraw from", required = true) @PathVariable String accountNumber,
            @RequestBody AmountDto amount) {
        accountService.withdraw(accountNumber, amount.getAmount());
        return ResponseEntity.ok("Withdrawal successful.");
    }

    @GetMapping("/{accountNumber}/history")
    @ApiOperation(value = "View a list of transactions for an account", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public ResponseEntity<List<Transaction>> getHistory(
            @ApiParam(value = "Account Number to get history from", required = true) @PathVariable String accountNumber) {
        return ResponseEntity.ok(accountService.getHistory(accountNumber));
    }

    @GetMapping("/{accountNumber}/print")
    @ApiOperation(value = "Get a printable statement for an account", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved statement"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public ResponseEntity<String> getPrintStatement(
            @ApiParam(value = "Account Number to print statement for", required = true) @PathVariable String accountNumber) {
        return ResponseEntity.ok(statementPrinter.printStatement(accountService.getAccount(accountNumber)));
    }
}
