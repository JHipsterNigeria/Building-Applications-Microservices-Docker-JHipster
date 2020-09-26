package com.jhispter.nigeria.service.dto;

import java.time.Instant;
import java.io.Serializable;
import com.jhispter.nigeria.domain.enumeration.Status;

/**
 * A DTO for the {@link com.jhispter.nigeria.domain.EmailNotification} entity.
 */
public class EmailNotificationDTO implements Serializable {
    
    private Long id;

    private String emailAddress;

    private String ccEmailAddress;

    private String subject;

    private Status status;

    private Instant dateCreated;

    private Instant dateSent;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getCcEmailAddress() {
        return ccEmailAddress;
    }

    public void setCcEmailAddress(String ccEmailAddress) {
        this.ccEmailAddress = ccEmailAddress;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Instant getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Instant dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Instant getDateSent() {
        return dateSent;
    }

    public void setDateSent(Instant dateSent) {
        this.dateSent = dateSent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EmailNotificationDTO)) {
            return false;
        }

        return id != null && id.equals(((EmailNotificationDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EmailNotificationDTO{" +
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
