package com.jhipster.nigeria.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import com.jhipster.nigeria.domain.enumeration.TransactionType;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.BigDecimalFilter;

/**
 * Criteria class for the {@link com.jhipster.nigeria.domain.TransactionRequest} entity. This class is used
 * in {@link com.jhipster.nigeria.web.rest.TransactionRequestResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /transaction-requests?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class TransactionRequestCriteria implements Serializable, Criteria {
    /**
     * Class for filtering TransactionType
     */
    public static class TransactionTypeFilter extends Filter<TransactionType> {

        public TransactionTypeFilter() {
        }

        public TransactionTypeFilter(TransactionTypeFilter filter) {
            super(filter);
        }

        @Override
        public TransactionTypeFilter copy() {
            return new TransactionTypeFilter(this);
        }

    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private BigDecimalFilter amount;

    private StringFilter channel;

    private StringFilter currency;

    private StringFilter sourceAccount;

    private StringFilter sourceAccountBankCode;

    private StringFilter sourceAccountName;

    private StringFilter sourceNarration;

    private StringFilter destinationAccount;

    private StringFilter destinationAccountBankCode;

    private StringFilter destinationAccountName;

    private StringFilter destinationNarration;

    private StringFilter statusWebHook;

    private StringFilter transactionRef;

    private StringFilter responseCode;

    private StringFilter responseMessage;

    private TransactionTypeFilter transactionType;

    private StringFilter schemeOwnerId;

    private LongFilter accountId;

    public TransactionRequestCriteria() {
    }

    public TransactionRequestCriteria(TransactionRequestCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.amount = other.amount == null ? null : other.amount.copy();
        this.channel = other.channel == null ? null : other.channel.copy();
        this.currency = other.currency == null ? null : other.currency.copy();
        this.sourceAccount = other.sourceAccount == null ? null : other.sourceAccount.copy();
        this.sourceAccountBankCode = other.sourceAccountBankCode == null ? null : other.sourceAccountBankCode.copy();
        this.sourceAccountName = other.sourceAccountName == null ? null : other.sourceAccountName.copy();
        this.sourceNarration = other.sourceNarration == null ? null : other.sourceNarration.copy();
        this.destinationAccount = other.destinationAccount == null ? null : other.destinationAccount.copy();
        this.destinationAccountBankCode = other.destinationAccountBankCode == null ? null : other.destinationAccountBankCode.copy();
        this.destinationAccountName = other.destinationAccountName == null ? null : other.destinationAccountName.copy();
        this.destinationNarration = other.destinationNarration == null ? null : other.destinationNarration.copy();
        this.statusWebHook = other.statusWebHook == null ? null : other.statusWebHook.copy();
        this.transactionRef = other.transactionRef == null ? null : other.transactionRef.copy();
        this.responseCode = other.responseCode == null ? null : other.responseCode.copy();
        this.responseMessage = other.responseMessage == null ? null : other.responseMessage.copy();
        this.transactionType = other.transactionType == null ? null : other.transactionType.copy();
        this.schemeOwnerId = other.schemeOwnerId == null ? null : other.schemeOwnerId.copy();
        this.accountId = other.accountId == null ? null : other.accountId.copy();
    }

    @Override
    public TransactionRequestCriteria copy() {
        return new TransactionRequestCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public BigDecimalFilter getAmount() {
        return amount;
    }

    public void setAmount(BigDecimalFilter amount) {
        this.amount = amount;
    }

    public StringFilter getChannel() {
        return channel;
    }

    public void setChannel(StringFilter channel) {
        this.channel = channel;
    }

    public StringFilter getCurrency() {
        return currency;
    }

    public void setCurrency(StringFilter currency) {
        this.currency = currency;
    }

    public StringFilter getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(StringFilter sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public StringFilter getSourceAccountBankCode() {
        return sourceAccountBankCode;
    }

    public void setSourceAccountBankCode(StringFilter sourceAccountBankCode) {
        this.sourceAccountBankCode = sourceAccountBankCode;
    }

    public StringFilter getSourceAccountName() {
        return sourceAccountName;
    }

    public void setSourceAccountName(StringFilter sourceAccountName) {
        this.sourceAccountName = sourceAccountName;
    }

    public StringFilter getSourceNarration() {
        return sourceNarration;
    }

    public void setSourceNarration(StringFilter sourceNarration) {
        this.sourceNarration = sourceNarration;
    }

    public StringFilter getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(StringFilter destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    public StringFilter getDestinationAccountBankCode() {
        return destinationAccountBankCode;
    }

    public void setDestinationAccountBankCode(StringFilter destinationAccountBankCode) {
        this.destinationAccountBankCode = destinationAccountBankCode;
    }

    public StringFilter getDestinationAccountName() {
        return destinationAccountName;
    }

    public void setDestinationAccountName(StringFilter destinationAccountName) {
        this.destinationAccountName = destinationAccountName;
    }

    public StringFilter getDestinationNarration() {
        return destinationNarration;
    }

    public void setDestinationNarration(StringFilter destinationNarration) {
        this.destinationNarration = destinationNarration;
    }

    public StringFilter getStatusWebHook() {
        return statusWebHook;
    }

    public void setStatusWebHook(StringFilter statusWebHook) {
        this.statusWebHook = statusWebHook;
    }

    public StringFilter getTransactionRef() {
        return transactionRef;
    }

    public void setTransactionRef(StringFilter transactionRef) {
        this.transactionRef = transactionRef;
    }

    public StringFilter getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(StringFilter responseCode) {
        this.responseCode = responseCode;
    }

    public StringFilter getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(StringFilter responseMessage) {
        this.responseMessage = responseMessage;
    }

    public TransactionTypeFilter getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionTypeFilter transactionType) {
        this.transactionType = transactionType;
    }

    public StringFilter getSchemeOwnerId() {
        return schemeOwnerId;
    }

    public void setSchemeOwnerId(StringFilter schemeOwnerId) {
        this.schemeOwnerId = schemeOwnerId;
    }

    public LongFilter getAccountId() {
        return accountId;
    }

    public void setAccountId(LongFilter accountId) {
        this.accountId = accountId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final TransactionRequestCriteria that = (TransactionRequestCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(amount, that.amount) &&
            Objects.equals(channel, that.channel) &&
            Objects.equals(currency, that.currency) &&
            Objects.equals(sourceAccount, that.sourceAccount) &&
            Objects.equals(sourceAccountBankCode, that.sourceAccountBankCode) &&
            Objects.equals(sourceAccountName, that.sourceAccountName) &&
            Objects.equals(sourceNarration, that.sourceNarration) &&
            Objects.equals(destinationAccount, that.destinationAccount) &&
            Objects.equals(destinationAccountBankCode, that.destinationAccountBankCode) &&
            Objects.equals(destinationAccountName, that.destinationAccountName) &&
            Objects.equals(destinationNarration, that.destinationNarration) &&
            Objects.equals(statusWebHook, that.statusWebHook) &&
            Objects.equals(transactionRef, that.transactionRef) &&
            Objects.equals(responseCode, that.responseCode) &&
            Objects.equals(responseMessage, that.responseMessage) &&
            Objects.equals(transactionType, that.transactionType) &&
            Objects.equals(schemeOwnerId, that.schemeOwnerId) &&
            Objects.equals(accountId, that.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        amount,
        channel,
        currency,
        sourceAccount,
        sourceAccountBankCode,
        sourceAccountName,
        sourceNarration,
        destinationAccount,
        destinationAccountBankCode,
        destinationAccountName,
        destinationNarration,
        statusWebHook,
        transactionRef,
        responseCode,
        responseMessage,
        transactionType,
        schemeOwnerId,
        accountId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TransactionRequestCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (amount != null ? "amount=" + amount + ", " : "") +
                (channel != null ? "channel=" + channel + ", " : "") +
                (currency != null ? "currency=" + currency + ", " : "") +
                (sourceAccount != null ? "sourceAccount=" + sourceAccount + ", " : "") +
                (sourceAccountBankCode != null ? "sourceAccountBankCode=" + sourceAccountBankCode + ", " : "") +
                (sourceAccountName != null ? "sourceAccountName=" + sourceAccountName + ", " : "") +
                (sourceNarration != null ? "sourceNarration=" + sourceNarration + ", " : "") +
                (destinationAccount != null ? "destinationAccount=" + destinationAccount + ", " : "") +
                (destinationAccountBankCode != null ? "destinationAccountBankCode=" + destinationAccountBankCode + ", " : "") +
                (destinationAccountName != null ? "destinationAccountName=" + destinationAccountName + ", " : "") +
                (destinationNarration != null ? "destinationNarration=" + destinationNarration + ", " : "") +
                (statusWebHook != null ? "statusWebHook=" + statusWebHook + ", " : "") +
                (transactionRef != null ? "transactionRef=" + transactionRef + ", " : "") +
                (responseCode != null ? "responseCode=" + responseCode + ", " : "") +
                (responseMessage != null ? "responseMessage=" + responseMessage + ", " : "") +
                (transactionType != null ? "transactionType=" + transactionType + ", " : "") +
                (schemeOwnerId != null ? "schemeOwnerId=" + schemeOwnerId + ", " : "") +
                (accountId != null ? "accountId=" + accountId + ", " : "") +
            "}";
    }

}
