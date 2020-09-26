package com.jhipster.nigeria.extended.dto;



import com.jhipster.nigeria.service.dto.VirtualAccountDTO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomerProfileResponseDto extends BaseProcessedData {

    private String firstName;

    private String lastName;

    private String customerId;

    private Set<VirtualAccountDTO> accounts = new HashSet<>();

    private String bvn;

    private String kycLevel;

    private String phoneNumber;

    private String address;

    private String email;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Set<VirtualAccountDTO> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<VirtualAccountDTO> accounts) {
        this.accounts = accounts;
    }

    public String getBvn() {
        return bvn;
    }

    public void setBvn(String bvn) {
        this.bvn = bvn;
    }

    public String getKycLevel() {
        return kycLevel;
    }

    public void setKycLevel(String kycLevel) {
        this.kycLevel = kycLevel;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
