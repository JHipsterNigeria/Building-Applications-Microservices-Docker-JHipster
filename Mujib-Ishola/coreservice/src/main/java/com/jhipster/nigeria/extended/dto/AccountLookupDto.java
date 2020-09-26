package com.jhipster.nigeria.extended.dto;

public class AccountLookupDto extends BaseProcessedData {

    private String accountNumber;

    private String accountName;

    private String bvn;


    public String getAccountNumber() {
        return accountNumber;
    }


    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }


    public String getAccountName() {
        return accountName;
    }


    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }


    public String getBvn() {
        return bvn;
    }


    public void setBvn(String bvn) {
        this.bvn = bvn;
    }
}
