package com.jhipster.nigeria.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import com.jhipster.nigeria.domain.enumeration.TransactionType;

/**
 * A DTO for the {@link com.jhipster.nigeria.domain.TransactionRequest} entity.
 */
@ApiModel(description = "Holds the details for every  transaction.")
public class TransactionRequestDTO implements Serializable {
    
    private Long id;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private String channel;

    @NotNull
    private String currency;

    @NotNull
    private String sourceAccount;

    @NotNull
    private String sourceAccountBankCode;

    private String sourceAccountName;

    @NotNull
    private String sourceNarration;

    @NotNull
    private String destinationAccount;

    @NotNull
    private String destinationAccountBankCode;

    private String destinationAccountName;

    @NotNull
    private String destinationNarration;

    /**
     * The rest API endpoint so send notification of transactions to.See web hook for details
     */
    @ApiModelProperty(value = "The rest API endpoint so send notification of transactions to.See web hook for details")
    private String statusWebHook;

    /**
     * Uniquely identifies a transaction
     */
    @ApiModelProperty(value = "Uniquely identifies a transaction")
    private String transactionRef;

    private String responseCode;

    private String responseMessage;

    @NotNull
    private TransactionType transactionType;

    /**
     * Possibly to be used as a descriminator in a multi tenant situation
     */
    @NotNull
    @ApiModelProperty(value = "Possibly to be used as a descriminator in a multi tenant situation", required = true)
    private String schemeOwnerId;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(String sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public String getSourceAccountBankCode() {
        return sourceAccountBankCode;
    }

    public void setSourceAccountBankCode(String sourceAccountBankCode) {
        this.sourceAccountBankCode = sourceAccountBankCode;
    }

    public String getSourceAccountName() {
        return sourceAccountName;
    }

    public void setSourceAccountName(String sourceAccountName) {
        this.sourceAccountName = sourceAccountName;
    }

    public String getSourceNarration() {
        return sourceNarration;
    }

    public void setSourceNarration(String sourceNarration) {
        this.sourceNarration = sourceNarration;
    }

    public String getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(String destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    public String getDestinationAccountBankCode() {
        return destinationAccountBankCode;
    }

    public void setDestinationAccountBankCode(String destinationAccountBankCode) {
        this.destinationAccountBankCode = destinationAccountBankCode;
    }

    public String getDestinationAccountName() {
        return destinationAccountName;
    }

    public void setDestinationAccountName(String destinationAccountName) {
        this.destinationAccountName = destinationAccountName;
    }

    public String getDestinationNarration() {
        return destinationNarration;
    }

    public void setDestinationNarration(String destinationNarration) {
        this.destinationNarration = destinationNarration;
    }

    public String getStatusWebHook() {
        return statusWebHook;
    }

    public void setStatusWebHook(String statusWebHook) {
        this.statusWebHook = statusWebHook;
    }

    public String getTransactionRef() {
        return transactionRef;
    }

    public void setTransactionRef(String transactionRef) {
        this.transactionRef = transactionRef;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getSchemeOwnerId() {
        return schemeOwnerId;
    }

    public void setSchemeOwnerId(String schemeOwnerId) {
        this.schemeOwnerId = schemeOwnerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TransactionRequestDTO)) {
            return false;
        }

        return id != null && id.equals(((TransactionRequestDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TransactionRequestDTO{" +
            "id=" + getId() +
            ", amount=" + getAmount() +
            ", channel='" + getChannel() + "'" +
            ", currency='" + getCurrency() + "'" +
            ", sourceAccount='" + getSourceAccount() + "'" +
            ", sourceAccountBankCode='" + getSourceAccountBankCode() + "'" +
            ", sourceAccountName='" + getSourceAccountName() + "'" +
            ", sourceNarration='" + getSourceNarration() + "'" +
            ", destinationAccount='" + getDestinationAccount() + "'" +
            ", destinationAccountBankCode='" + getDestinationAccountBankCode() + "'" +
            ", destinationAccountName='" + getDestinationAccountName() + "'" +
            ", destinationNarration='" + getDestinationNarration() + "'" +
            ", statusWebHook='" + getStatusWebHook() + "'" +
            ", transactionRef='" + getTransactionRef() + "'" +
            ", responseCode='" + getResponseCode() + "'" +
            ", responseMessage='" + getResponseMessage() + "'" +
            ", transactionType='" + getTransactionType() + "'" +
            ", schemeOwnerId='" + getSchemeOwnerId() + "'" +
            "}";
    }
}
