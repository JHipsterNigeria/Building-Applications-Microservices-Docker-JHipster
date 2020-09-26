package com.jhipster.nigeria.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import com.jhipster.nigeria.domain.enumeration.ProfileType;
import com.jhipster.nigeria.domain.enumeration.AccountStatus;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.BigDecimalFilter;

/**
 * Criteria class for the {@link com.jhipster.nigeria.domain.VirtualAccount} entity. This class is used
 * in {@link com.jhipster.nigeria.web.rest.VirtualAccountResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /virtual-accounts?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class VirtualAccountCriteria implements Serializable, Criteria {
    /**
     * Class for filtering ProfileType
     */
    public static class ProfileTypeFilter extends Filter<ProfileType> {

        public ProfileTypeFilter() {
        }

        public ProfileTypeFilter(ProfileTypeFilter filter) {
            super(filter);
        }

        @Override
        public ProfileTypeFilter copy() {
            return new ProfileTypeFilter(this);
        }

    }
    /**
     * Class for filtering AccountStatus
     */
    public static class AccountStatusFilter extends Filter<AccountStatus> {

        public AccountStatusFilter() {
        }

        public AccountStatusFilter(AccountStatusFilter filter) {
            super(filter);
        }

        @Override
        public AccountStatusFilter copy() {
            return new AccountStatusFilter(this);
        }

    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter customerId;

    private StringFilter extCustomerId;

    private StringFilter currency;

    private StringFilter accountNumber;

    private BigDecimalFilter availableBalance;

    private BigDecimalFilter holdBalance;

    private BigDecimalFilter minimumBalance;

    private ProfileTypeFilter accountType;

    private AccountStatusFilter status;

    private LongFilter accountHolderId;

    private LongFilter transactionRequestId;

    public VirtualAccountCriteria() {
    }

    public VirtualAccountCriteria(VirtualAccountCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.customerId = other.customerId == null ? null : other.customerId.copy();
        this.extCustomerId = other.extCustomerId == null ? null : other.extCustomerId.copy();
        this.currency = other.currency == null ? null : other.currency.copy();
        this.accountNumber = other.accountNumber == null ? null : other.accountNumber.copy();
        this.availableBalance = other.availableBalance == null ? null : other.availableBalance.copy();
        this.holdBalance = other.holdBalance == null ? null : other.holdBalance.copy();
        this.minimumBalance = other.minimumBalance == null ? null : other.minimumBalance.copy();
        this.accountType = other.accountType == null ? null : other.accountType.copy();
        this.status = other.status == null ? null : other.status.copy();
        this.accountHolderId = other.accountHolderId == null ? null : other.accountHolderId.copy();
        this.transactionRequestId = other.transactionRequestId == null ? null : other.transactionRequestId.copy();
    }

    @Override
    public VirtualAccountCriteria copy() {
        return new VirtualAccountCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getCustomerId() {
        return customerId;
    }

    public void setCustomerId(StringFilter customerId) {
        this.customerId = customerId;
    }

    public StringFilter getExtCustomerId() {
        return extCustomerId;
    }

    public void setExtCustomerId(StringFilter extCustomerId) {
        this.extCustomerId = extCustomerId;
    }

    public StringFilter getCurrency() {
        return currency;
    }

    public void setCurrency(StringFilter currency) {
        this.currency = currency;
    }

    public StringFilter getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(StringFilter accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimalFilter getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(BigDecimalFilter availableBalance) {
        this.availableBalance = availableBalance;
    }

    public BigDecimalFilter getHoldBalance() {
        return holdBalance;
    }

    public void setHoldBalance(BigDecimalFilter holdBalance) {
        this.holdBalance = holdBalance;
    }

    public BigDecimalFilter getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(BigDecimalFilter minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public ProfileTypeFilter getAccountType() {
        return accountType;
    }

    public void setAccountType(ProfileTypeFilter accountType) {
        this.accountType = accountType;
    }

    public AccountStatusFilter getStatus() {
        return status;
    }

    public void setStatus(AccountStatusFilter status) {
        this.status = status;
    }

    public LongFilter getAccountHolderId() {
        return accountHolderId;
    }

    public void setAccountHolderId(LongFilter accountHolderId) {
        this.accountHolderId = accountHolderId;
    }

    public LongFilter getTransactionRequestId() {
        return transactionRequestId;
    }

    public void setTransactionRequestId(LongFilter transactionRequestId) {
        this.transactionRequestId = transactionRequestId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final VirtualAccountCriteria that = (VirtualAccountCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(customerId, that.customerId) &&
            Objects.equals(extCustomerId, that.extCustomerId) &&
            Objects.equals(currency, that.currency) &&
            Objects.equals(accountNumber, that.accountNumber) &&
            Objects.equals(availableBalance, that.availableBalance) &&
            Objects.equals(holdBalance, that.holdBalance) &&
            Objects.equals(minimumBalance, that.minimumBalance) &&
            Objects.equals(accountType, that.accountType) &&
            Objects.equals(status, that.status) &&
            Objects.equals(accountHolderId, that.accountHolderId) &&
            Objects.equals(transactionRequestId, that.transactionRequestId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        customerId,
        extCustomerId,
        currency,
        accountNumber,
        availableBalance,
        holdBalance,
        minimumBalance,
        accountType,
        status,
        accountHolderId,
        transactionRequestId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VirtualAccountCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (customerId != null ? "customerId=" + customerId + ", " : "") +
                (extCustomerId != null ? "extCustomerId=" + extCustomerId + ", " : "") +
                (currency != null ? "currency=" + currency + ", " : "") +
                (accountNumber != null ? "accountNumber=" + accountNumber + ", " : "") +
                (availableBalance != null ? "availableBalance=" + availableBalance + ", " : "") +
                (holdBalance != null ? "holdBalance=" + holdBalance + ", " : "") +
                (minimumBalance != null ? "minimumBalance=" + minimumBalance + ", " : "") +
                (accountType != null ? "accountType=" + accountType + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (accountHolderId != null ? "accountHolderId=" + accountHolderId + ", " : "") +
                (transactionRequestId != null ? "transactionRequestId=" + transactionRequestId + ", " : "") +
            "}";
    }

}
