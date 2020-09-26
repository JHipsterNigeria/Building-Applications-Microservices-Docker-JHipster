package com.jhipster.nigeria.extended.dto;

import java.math.BigDecimal;


public class BalanceResponseDto extends BaseProcessedData {

    private String accountNumber;

    private String accountName;

    private String currency;

    private BigDecimal availableBalance;

    private BigDecimal ledgerBalance;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }


    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }


    public String getCurrency() {
        return currency;
    }


    public void setCurrency(String currency) {
        this.currency = currency;
    }


    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }


    public void setAvailableBalance(BigDecimal availableBalance) {
        this.availableBalance = availableBalance;
    }


    public BigDecimal getLedgerBalance() {
        return ledgerBalance;
    }

    public void setLedgerBalance(BigDecimal ledgerBalance) {
        this.ledgerBalance = ledgerBalance;
    }
}
