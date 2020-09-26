package com.jhispter.nigeria.service.dto;

import java.time.Instant;
import java.io.Serializable;
import com.jhispter.nigeria.domain.enumeration.Status;

/**
 * A DTO for the {@link com.jhispter.nigeria.domain.SmsNotification} entity.
 */
public class SmsNotificationDTO implements Serializable {
    
    private Long id;

    private String phoneNumber;

    private String message;

    private Status status;

    private Instant dateCreated;

    private Instant dateSent;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
        if (!(o instanceof SmsNotificationDTO)) {
            return false;
        }

        return id != null && id.equals(((SmsNotificationDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SmsNotificationDTO{" +
            "id=" + getId() +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", message='" + getMessage() + "'" +
            ", status='" + getStatus() + "'" +
            ", dateCreated='" + getDateCreated() + "'" +
            ", dateSent='" + getDateSent() + "'" +
            "}";
    }
}
