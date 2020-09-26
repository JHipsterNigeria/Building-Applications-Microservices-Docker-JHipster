package com.jhipster.nigeria.extended.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jhipster.nigeria.domain.enumeration.ProfileType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

public class CustomerRegistrationRequestDto {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String email;

    @NotNull
    @Size(min = 10, max = 15)
    private String phoneNumber;

    private String address;

    private String bvn;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;

    private String photo;

    @NotNull
    private ProfileType profileType;

    private String schemeCodeId;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getBvn() {
        return bvn;
    }

    public void setBvn(String bvn) {
        this.bvn = bvn;
    }


    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public ProfileType getProfileType() {
        return profileType;
    }

    public void setProfileType(ProfileType profileType) {
        this.profileType = profileType;
    }

    public String getSchemeCodeId() {
        return schemeCodeId;
    }

    public void setSchemeCodeId(String schemeCodeId) {
        this.schemeCodeId = schemeCodeId;
    }

    @Override
    public String toString() {
        return "CustomerRegistrationRequestDto{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", address='" + address + '\'' +
            ", bvn='" + bvn + '\'' +
            ", dateOfBirth=" + dateOfBirth +
            ", photo='" + photo + '\'' +
            ", profileType=" + profileType +
            ", schemeCodeId='" + schemeCodeId + '\'' +
            '}';
    }
}
