package com.jhipster.nigeria.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;

import com.jhipster.nigeria.domain.enumeration.ProfileType;

import com.jhipster.nigeria.domain.enumeration.AccountStatus;

/**
 * Wallet account information
 */
@Entity
@Table(name = "virtual_account")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VirtualAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "customer_id", nullable = false)
    private String customerId;

    @NotNull
    @Column(name = "ext_customer_id", nullable = false)
    private String extCustomerId;

    @NotNull
    @Column(name = "currency", nullable = false)
    private String currency;

    @NotNull
    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @Column(name = "available_balance", precision = 21, scale = 2)
    private BigDecimal availableBalance;

    @Column(name = "hold_balance", precision = 21, scale = 2)
    private BigDecimal holdBalance;

    @Column(name = "minimum_balance", precision = 21, scale = 2)
    private BigDecimal minimumBalance;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "account_type", nullable = false)
    private ProfileType accountType;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private AccountStatus status;

    @ManyToOne
    @JsonIgnoreProperties(value = "virtualAccounts", allowSetters = true)
    private Profile accountHolder;

    @ManyToOne
    @JsonIgnoreProperties(value = "accounts", allowSetters = true)
    private TransactionRequest transactionRequest;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public VirtualAccount customerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getExtCustomerId() {
        return extCustomerId;
    }

    public VirtualAccount extCustomerId(String extCustomerId) {
        this.extCustomerId = extCustomerId;
        return this;
    }

    public void setExtCustomerId(String extCustomerId) {
        this.extCustomerId = extCustomerId;
    }

    public String getCurrency() {
        return currency;
    }

    public VirtualAccount currency(String currency) {
        this.currency = currency;
        return this;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public VirtualAccount accountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }

    public VirtualAccount availableBalance(BigDecimal availableBalance) {
        this.availableBalance = availableBalance;
        return this;
    }

    public void setAvailableBalance(BigDecimal availableBalance) {
        this.availableBalance = availableBalance;
    }

    public BigDecimal getHoldBalance() {
        return holdBalance;
    }

    public VirtualAccount holdBalance(BigDecimal holdBalance) {
        this.holdBalance = holdBalance;
        return this;
    }

    public void setHoldBalance(BigDecimal holdBalance) {
        this.holdBalance = holdBalance;
    }

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public VirtualAccount minimumBalance(BigDecimal minimumBalance) {
        this.minimumBalance = minimumBalance;
        return this;
    }

    public void setMinimumBalance(BigDecimal minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public ProfileType getAccountType() {
        return accountType;
    }

    public VirtualAccount accountType(ProfileType accountType) {
        this.accountType = accountType;
        return this;
    }

    public void setAccountType(ProfileType accountType) {
        this.accountType = accountType;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public VirtualAccount status(AccountStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public Profile getAccountHolder() {
        return accountHolder;
    }

    public VirtualAccount accountHolder(Profile profile) {
        this.accountHolder = profile;
        return this;
    }

    public void setAccountHolder(Profile profile) {
        this.accountHolder = profile;
    }

    public TransactionRequest getTransactionRequest() {
        return transactionRequest;
    }

    public VirtualAccount transactionRequest(TransactionRequest transactionRequest) {
        this.transactionRequest = transactionRequest;
        return this;
    }

    public void setTransactionRequest(TransactionRequest transactionRequest) {
        this.transactionRequest = transactionRequest;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VirtualAccount)) {
            return false;
        }
        return id != null && id.equals(((VirtualAccount) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VirtualAccount{" +
            "id=" + getId() +
            ", customerId='" + getCustomerId() + "'" +
            ", extCustomerId='" + getExtCustomerId() + "'" +
            ", currency='" + getCurrency() + "'" +
            ", accountNumber='" + getAccountNumber() + "'" +
            ", availableBalance=" + getAvailableBalance() +
            ", holdBalance=" + getHoldBalance() +
            ", minimumBalance=" + getMinimumBalance() +
            ", accountType='" + getAccountType() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
