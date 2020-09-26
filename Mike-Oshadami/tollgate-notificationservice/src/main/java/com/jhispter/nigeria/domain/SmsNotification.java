package com.jhispter.nigeria.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

import com.jhispter.nigeria.domain.enumeration.Status;

/**
 * A SmsNotification.
 */
@Entity
@Table(name = "sms_notification")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SmsNotification implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "message")
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "date_created")
    private Instant dateCreated;

    @Column(name = "date_sent")
    private Instant dateSent;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public SmsNotification phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    public SmsNotification message(String message) {
        this.message = message;
        return this;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Status getStatus() {
        return status;
    }

    public SmsNotification status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Instant getDateCreated() {
        return dateCreated;
    }

    public SmsNotification dateCreated(Instant dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public void setDateCreated(Instant dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Instant getDateSent() {
        return dateSent;
    }

    public SmsNotification dateSent(Instant dateSent) {
        this.dateSent = dateSent;
        return this;
    }

    public void setDateSent(Instant dateSent) {
        this.dateSent = dateSent;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SmsNotification)) {
            return false;
        }
        return id != null && id.equals(((SmsNotification) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SmsNotification{" +
            "id=" + getId() +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", message='" + getMessage() + "'" +
            ", status='" + getStatus() + "'" +
            ", dateCreated='" + getDateCreated() + "'" +
            ", dateSent='" + getDateSent() + "'" +
            "}";
    }
}
