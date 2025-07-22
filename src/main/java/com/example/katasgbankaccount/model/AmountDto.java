package com.example.katasgbankaccount.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Amount for transaction")
public class AmountDto {

    @ApiModelProperty(notes = "The amount for the transaction", example = "500.75", required = true)
    private double amount;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
