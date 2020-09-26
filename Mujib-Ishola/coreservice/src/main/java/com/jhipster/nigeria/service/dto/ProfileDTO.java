package com.jhipster.nigeria.service.dto;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import java.io.Serializable;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.jhipster.nigeria.domain.Profile} entity.
 */
public class ProfileDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Long userId;

    @NotNull
    @Size(max = 100)
    private String login;

    @NotNull
    @Size(min = 8, max = 15)
    private String phone;

    private String email;

    @NotNull
    private String firstName;

    private String middleName;

    @NotNull
    private String lastName;

    private String optActivationKey;

    private String activationKey;

    private String alias;

    private Boolean emailNotifications;

    private Boolean smsNotifications;

    @Lob
    private byte[] photo;

    private String photoContentType;
    /**
     * Used for i18n on the platform
     */
    @NotNull
    @ApiModelProperty(value = "Used for i18n on the platform", required = true)
    private String lang;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOptActivationKey() {
        return optActivationKey;
    }

    public void setOptActivationKey(String optActivationKey) {
        this.optActivationKey = optActivationKey;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Boolean isEmailNotifications() {
        return emailNotifications;
    }

    public void setEmailNotifications(Boolean emailNotifications) {
        this.emailNotifications = emailNotifications;
    }

    public Boolean isSmsNotifications() {
        return smsNotifications;
    }

    public void setSmsNotifications(Boolean smsNotifications) {
        this.smsNotifications = smsNotifications;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getPhotoContentType() {
        return photoContentType;
    }

    public void setPhotoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProfileDTO)) {
            return false;
        }

        return id != null && id.equals(((ProfileDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProfileDTO{" +
            "id=" + getId() +
            ", userId=" + getUserId() +
            ", login='" + getLogin() + "'" +
            ", phone='" + getPhone() + "'" +
            ", email='" + getEmail() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", middleName='" + getMiddleName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", optActivationKey='" + getOptActivationKey() + "'" +
            ", activationKey='" + getActivationKey() + "'" +
            ", alias='" + getAlias() + "'" +
            ", emailNotifications='" + isEmailNotifications() + "'" +
            ", smsNotifications='" + isSmsNotifications() + "'" +
            ", photo='" + getPhoto() + "'" +
            ", lang='" + getLang() + "'" +
            "}";
    }
}
