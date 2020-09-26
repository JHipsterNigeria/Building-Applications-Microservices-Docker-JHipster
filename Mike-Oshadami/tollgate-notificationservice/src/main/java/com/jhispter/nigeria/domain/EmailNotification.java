package com.jhispter.nigeria.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

import com.jhispter.nigeria.domain.enumeration.Status;

/**
 * A EmailNotification.
 */
@Entity
@Table(name = "email_notification")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EmailNotification implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "cc_email_address")
    private String ccEmailAddress;

    @Column(name = "subject")
    private String subject;

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

    public String getEmailAddress() {
        return emailAddress;
    }

    public EmailNotification emailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getCcEmailAddress() {
        return ccEmailAddress;
    }

    public EmailNotification ccEmailAddress(String ccEmailAddress) {
        this.ccEmailAddress = ccEmailAddress;
        return this;
    }

    public void setCcEmailAddress(String ccEmailAddress) {
        this.ccEmailAddress = ccEmailAddress;
    }

    public String getSubject() {
        return subject;
    }

    public EmailNotification subject(String subject) {
        this.subject = subject;
        return this;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Status getStatus() {
        return status;
    }

    public EmailNotification status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Instant getDateCreated() {
        return dateCreated;
    }

    public EmailNotification dateCreated(Instant dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public void setDateCreated(Instant dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Instant getDateSent() {
        return dateSent;
    }

    public EmailNotification dateSent(Instant dateSent) {
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
        if (!(o instanceof EmailNotification)) {
            return false;
        }
        return id != null && id.equals(((EmailNotification) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EmailNotification{" +
            "id=" + getId() +
            ", emailAddress='" + getEmailAddress() + "'" +
            ", ccEmailAddress='" + getCcEmailAddress() + "'" +
            ", subject='" + getSubject() + "'" +
            ", status='" + getStatus() + "'" +
            ", dateCreated='" + getDateCreated() + "'" +
            ", dateSent='" + getDateSent() + "'" +
            "}";
    }
}
