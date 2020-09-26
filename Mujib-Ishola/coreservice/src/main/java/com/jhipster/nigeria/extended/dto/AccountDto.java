package com.jhipster.nigeria.extended.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jhipster.nigeria.domain.enumeration.ProfileType;

import javax.validation.constraints.NotNull;
import java.util.Date;


public class AccountDto {

    private String requestId;

    @NotNull
    private String firstName;

    @NotNull
    private String surname;

    private String otherName;

    private String email;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dateOfBirth;

    private String bvn;

    @NotNull
    private String phone;

    private String street;

    private String country;

    private String nationality;

    @NotNull
    private String customerId;

    private String accountNumber;

    private String transactionPin;

    private String secretKey;

    @NotNull
    private String schemeCode;

    @NotNull
    private ProfileType accountType;


    private Boolean isRestrictedWallet;

    private String restrictedGroupId;


    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public ProfileType getAccountType() {
        return accountType;
    }

    public void setAccountType(ProfileType accountType) {
        this.accountType = accountType;
    }

    public String getSchemeCode() {
        return schemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        this.schemeCode = schemeCode;
    }

    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getOtherName() {
        return otherName;
    }


    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getBvn() {
        return bvn;
    }


    public void setBvn(String bvn) {
        this.bvn = bvn;
    }


    public String getPhone() {
        return phone;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getStreet() {
        return street;
    }


    public void setStreet(String street) {
        this.street = street;
    }


    public String getCountry() {
        return country;
    }


    public void setCountry(String country) {
        this.country = country;
    }


    public String getNationality() {
        return nationality;
    }


    public void setNationality(String nationality) {
        this.nationality = nationality;
    }


    public String getCustomerId() {
        return customerId;
    }


    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }


    public String getAccountNumber() {
        return accountNumber;
    }


    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getTransactionPin() {
        return transactionPin;
    }


    public void setTransactionPin(String transactionPin) {
        this.transactionPin = transactionPin;
    }


    public String getSecretKey() {
        return secretKey;
    }


    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Boolean getRestrictedWallet() {
        return isRestrictedWallet;
    }

    public void setRestrictedWallet(Boolean restrictedWallet) {
        isRestrictedWallet = restrictedWallet;
    }

    public String getRestrictedGroupId() {
        return restrictedGroupId;
    }

    public void setRestrictedGroupId(String restrictedGroupId) {
        this.restrictedGroupId = restrictedGroupId;
    }
}
