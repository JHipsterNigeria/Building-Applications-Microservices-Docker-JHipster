package com.jhipster.nigeria.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.jhipster.nigeria.domain.enumeration.TransactionType;

/**
 * Holds the details for every  transaction.
 */
@Entity
@Table(name = "transaction_request")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TransactionRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "amount", precision = 21, scale = 2, nullable = false)
    private BigDecimal amount;

    @NotNull
    @Column(name = "channel", nullable = false)
    private String channel;

    @NotNull
    @Column(name = "currency", nullable = false)
    private String currency;

    @NotNull
    @Column(name = "source_account", nullable = false)
    private String sourceAccount;

    @NotNull
    @Column(name = "source_account_bank_code", nullable = false)
    private String sourceAccountBankCode;

    @Column(name = "source_account_name")
    private String sourceAccountName;

    @NotNull
    @Column(name = "source_narration", nullable = false)
    private String sourceNarration;

    @NotNull
    @Column(name = "destination_account", nullable = false)
    private String destinationAccount;

    @NotNull
    @Column(name = "destination_account_bank_code", nullable = false)
    private String destinationAccountBankCode;

    @Column(name = "destination_account_name")
    private String destinationAccountName;

    @NotNull
    @Column(name = "destination_narration", nullable = false)
    private String destinationNarration;

    /**
     * The rest API endpoint so send notification of transactions to.See web hook for details
     */
    @Column(name = "status_web_hook")
    private String statusWebHook;

    /**
     * Uniquely identifies a transaction
     */
    @Column(name = "transaction_ref")
    private String transactionRef;

    @Column(name = "response_code")
    private String responseCode;

    @Column(name = "response_message")
    private String responseMessage;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false)
    private TransactionType transactionType;

    /**
     * Possibly to be used as a descriminator in a multi tenant situation
     */
    @NotNull
    @Column(name = "scheme_owner_id", nullable = false)
    private String schemeOwnerId;

    @OneToMany(mappedBy = "transactionRequest")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<VirtualAccount> accounts = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public TransactionRequest amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getChannel() {
        return channel;
    }

    public TransactionRequest channel(String channel) {
        this.channel = channel;
        return this;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getCurrency() {
        return currency;
    }

    public TransactionRequest currency(String currency) {
        this.currency = currency;
        return this;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getSourceAccount() {
        return sourceAccount;
    }

    public TransactionRequest sourceAccount(String sourceAccount) {
        this.sourceAccount = sourceAccount;
        return this;
    }

    public void setSourceAccount(String sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public String getSourceAccountBankCode() {
        return sourceAccountBankCode;
    }

    public TransactionRequest sourceAccountBankCode(String sourceAccountBankCode) {
        this.sourceAccountBankCode = sourceAccountBankCode;
        return this;
    }

    public void setSourceAccountBankCode(String sourceAccountBankCode) {
        this.sourceAccountBankCode = sourceAccountBankCode;
    }

    public String getSourceAccountName() {
        return sourceAccountName;
    }

    public TransactionRequest sourceAccountName(String sourceAccountName) {
        this.sourceAccountName = sourceAccountName;
        return this;
    }

    public void setSourceAccountName(String sourceAccountName) {
        this.sourceAccountName = sourceAccountName;
    }

    public String getSourceNarration() {
        return sourceNarration;
    }

    public TransactionRequest sourceNarration(String sourceNarration) {
        this.sourceNarration = sourceNarration;
        return this;
    }

    public void setSourceNarration(String sourceNarration) {
        this.sourceNarration = sourceNarration;
    }

    public String getDestinationAccount() {
        return destinationAccount;
    }

    public TransactionRequest destinationAccount(String destinationAccount) {
        this.destinationAccount = destinationAccount;
        return this;
    }

    public void setDestinationAccount(String destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    public String getDestinationAccountBankCode() {
        return destinationAccountBankCode;
    }

    public TransactionRequest destinationAccountBankCode(String destinationAccountBankCode) {
        this.destinationAccountBankCode = destinationAccountBankCode;
        return this;
    }

    public void setDestinationAccountBankCode(String destinationAccountBankCode) {
        this.destinationAccountBankCode = destinationAccountBankCode;
    }

    public String getDestinationAccountName() {
        return destinationAccountName;
    }

    public TransactionRequest destinationAccountName(String destinationAccountName) {
        this.destinationAccountName = destinationAccountName;
        return this;
    }

    public void setDestinationAccountName(String destinationAccountName) {
        this.destinationAccountName = destinationAccountName;
    }

    public String getDestinationNarration() {
        return destinationNarration;
    }

    public TransactionRequest destinationNarration(String destinationNarration) {
        this.destinationNarration = destinationNarration;
        return this;
    }

    public void setDestinationNarration(String destinationNarration) {
        this.destinationNarration = destinationNarration;
    }

    public String getStatusWebHook() {
        return statusWebHook;
    }

    public TransactionRequest statusWebHook(String statusWebHook) {
        this.statusWebHook = statusWebHook;
        return this;
    }

    public void setStatusWebHook(String statusWebHook) {
        this.statusWebHook = statusWebHook;
    }

    public String getTransactionRef() {
        return transactionRef;
    }

    public TransactionRequest transactionRef(String transactionRef) {
        this.transactionRef = transactionRef;
        return this;
    }

    public void setTransactionRef(String transactionRef) {
        this.transactionRef = transactionRef;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public TransactionRequest responseCode(String responseCode) {
        this.responseCode = responseCode;
        return this;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public TransactionRequest responseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
        return this;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public TransactionRequest transactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
        return this;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getSchemeOwnerId() {
        return schemeOwnerId;
    }

    public TransactionRequest schemeOwnerId(String schemeOwnerId) {
        this.schemeOwnerId = schemeOwnerId;
        return this;
    }

    public void setSchemeOwnerId(String schemeOwnerId) {
        this.schemeOwnerId = schemeOwnerId;
    }

    public Set<VirtualAccount> getAccounts() {
        return accounts;
    }

    public TransactionRequest accounts(Set<VirtualAccount> virtualAccounts) {
        this.accounts = virtualAccounts;
        return this;
    }

    public TransactionRequest addAccount(VirtualAccount virtualAccount) {
        this.accounts.add(virtualAccount);
        virtualAccount.setTransactionRequest(this);
        return this;
    }

    public TransactionRequest removeAccount(VirtualAccount virtualAccount) {
        this.accounts.remove(virtualAccount);
        virtualAccount.setTransactionRequest(null);
        return this;
    }

    public void setAccounts(Set<VirtualAccount> virtualAccounts) {
        this.accounts = virtualAccounts;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TransactionRequest)) {
            return false;
        }
        return id != null && id.equals(((TransactionRequest) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TransactionRequest{" +
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
