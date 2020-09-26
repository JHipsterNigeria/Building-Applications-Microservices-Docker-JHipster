package com.jhipster.nigeria.service.dto;

import io.swagger.annotations.ApiModel;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import com.jhipster.nigeria.domain.enumeration.ProfileType;
import com.jhipster.nigeria.domain.enumeration.AccountStatus;

/**
 * A DTO for the {@link com.jhipster.nigeria.domain.VirtualAccount} entity.
 */
@ApiModel(description = "Wallet account information")
public class VirtualAccountDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String customerId;

    @NotNull
    private String extCustomerId;

    @NotNull
    private String currency;

    @NotNull
    private String accountNumber;

    private BigDecimal availableBalance;

    private BigDecimal holdBalance;

    private BigDecimal minimumBalance;

    @NotNull
    private ProfileType accountType;

    @NotNull
    private AccountStatus status;


    private Long accountHolderId;

    private String accountHolderEmail;

    private Long transactionRequestId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getExtCustomerId() {
        return extCustomerId;
    }

    public void setExtCustomerId(String extCustomerId) {
        this.extCustomerId = extCustomerId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(BigDecimal availableBalance) {
        this.availableBalance = availableBalance;
    }

    public BigDecimal getHoldBalance() {
        return holdBalance;
    }

    public void setHoldBalance(BigDecimal holdBalance) {
        this.holdBalance = holdBalance;
    }

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(BigDecimal minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public ProfileType getAccountType() {
        return accountType;
    }

    public void setAccountType(ProfileType accountType) {
        this.accountType = accountType;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public Long getAccountHolderId() {
        return accountHolderId;
    }

    public void setAccountHolderId(Long profileId) {
        this.accountHolderId = profileId;
    }

    public String getAccountHolderEmail() {
        return accountHolderEmail;
    }

    public void setAccountHolderEmail(String profileEmail) {
        this.accountHolderEmail = profileEmail;
    }

    public Long getTransactionRequestId() {
        return transactionRequestId;
    }

    public void setTransactionRequestId(Long transactionRequestId) {
        this.transactionRequestId = transactionRequestId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VirtualAccountDTO)) {
            return false;
        }

        return id != null && id.equals(((VirtualAccountDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VirtualAccountDTO{" +
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
            ", accountHolderId=" + getAccountHolderId() +
            ", accountHolderEmail='" + getAccountHolderEmail() + "'" +
            ", transactionRequestId=" + getTransactionRequestId() +
            "}";
    }
}
