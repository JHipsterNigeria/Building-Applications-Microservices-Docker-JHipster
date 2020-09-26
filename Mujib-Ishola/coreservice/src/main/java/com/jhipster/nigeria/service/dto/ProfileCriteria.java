package com.jhipster.nigeria.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link com.jhipster.nigeria.domain.Profile} entity. This class is used
 * in {@link com.jhipster.nigeria.web.rest.ProfileResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /profiles?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ProfileCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LongFilter userId;

    private StringFilter login;

    private StringFilter phone;

    private StringFilter email;

    private StringFilter firstName;

    private StringFilter middleName;

    private StringFilter lastName;

    private StringFilter optActivationKey;

    private StringFilter activationKey;

    private StringFilter alias;

    private BooleanFilter emailNotifications;

    private BooleanFilter smsNotifications;

    private StringFilter lang;

    public ProfileCriteria() {
    }

    public ProfileCriteria(ProfileCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.userId = other.userId == null ? null : other.userId.copy();
        this.login = other.login == null ? null : other.login.copy();
        this.phone = other.phone == null ? null : other.phone.copy();
        this.email = other.email == null ? null : other.email.copy();
        this.firstName = other.firstName == null ? null : other.firstName.copy();
        this.middleName = other.middleName == null ? null : other.middleName.copy();
        this.lastName = other.lastName == null ? null : other.lastName.copy();
        this.optActivationKey = other.optActivationKey == null ? null : other.optActivationKey.copy();
        this.activationKey = other.activationKey == null ? null : other.activationKey.copy();
        this.alias = other.alias == null ? null : other.alias.copy();
        this.emailNotifications = other.emailNotifications == null ? null : other.emailNotifications.copy();
        this.smsNotifications = other.smsNotifications == null ? null : other.smsNotifications.copy();
        this.lang = other.lang == null ? null : other.lang.copy();
    }

    @Override
    public ProfileCriteria copy() {
        return new ProfileCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getUserId() {
        return userId;
    }

    public void setUserId(LongFilter userId) {
        this.userId = userId;
    }

    public StringFilter getLogin() {
        return login;
    }

    public void setLogin(StringFilter login) {
        this.login = login;
    }

    public StringFilter getPhone() {
        return phone;
    }

    public void setPhone(StringFilter phone) {
        this.phone = phone;
    }

    public StringFilter getEmail() {
        return email;
    }

    public void setEmail(StringFilter email) {
        this.email = email;
    }

    public StringFilter getFirstName() {
        return firstName;
    }

    public void setFirstName(StringFilter firstName) {
        this.firstName = firstName;
    }

    public StringFilter getMiddleName() {
        return middleName;
    }

    public void setMiddleName(StringFilter middleName) {
        this.middleName = middleName;
    }

    public StringFilter getLastName() {
        return lastName;
    }

    public void setLastName(StringFilter lastName) {
        this.lastName = lastName;
    }

    public StringFilter getOptActivationKey() {
        return optActivationKey;
    }

    public void setOptActivationKey(StringFilter optActivationKey) {
        this.optActivationKey = optActivationKey;
    }

    public StringFilter getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(StringFilter activationKey) {
        this.activationKey = activationKey;
    }

    public StringFilter getAlias() {
        return alias;
    }

    public void setAlias(StringFilter alias) {
        this.alias = alias;
    }

    public BooleanFilter getEmailNotifications() {
        return emailNotifications;
    }

    public void setEmailNotifications(BooleanFilter emailNotifications) {
        this.emailNotifications = emailNotifications;
    }

    public BooleanFilter getSmsNotifications() {
        return smsNotifications;
    }

    public void setSmsNotifications(BooleanFilter smsNotifications) {
        this.smsNotifications = smsNotifications;
    }

    public StringFilter getLang() {
        return lang;
    }

    public void setLang(StringFilter lang) {
        this.lang = lang;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ProfileCriteria that = (ProfileCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(userId, that.userId) &&
            Objects.equals(login, that.login) &&
            Objects.equals(phone, that.phone) &&
            Objects.equals(email, that.email) &&
            Objects.equals(firstName, that.firstName) &&
            Objects.equals(middleName, that.middleName) &&
            Objects.equals(lastName, that.lastName) &&
            Objects.equals(optActivationKey, that.optActivationKey) &&
            Objects.equals(activationKey, that.activationKey) &&
            Objects.equals(alias, that.alias) &&
            Objects.equals(emailNotifications, that.emailNotifications) &&
            Objects.equals(smsNotifications, that.smsNotifications) &&
            Objects.equals(lang, that.lang);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        userId,
        login,
        phone,
        email,
        firstName,
        middleName,
        lastName,
        optActivationKey,
        activationKey,
        alias,
        emailNotifications,
        smsNotifications,
        lang
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProfileCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (userId != null ? "userId=" + userId + ", " : "") +
                (login != null ? "login=" + login + ", " : "") +
                (phone != null ? "phone=" + phone + ", " : "") +
                (email != null ? "email=" + email + ", " : "") +
                (firstName != null ? "firstName=" + firstName + ", " : "") +
                (middleName != null ? "middleName=" + middleName + ", " : "") +
                (lastName != null ? "lastName=" + lastName + ", " : "") +
                (optActivationKey != null ? "optActivationKey=" + optActivationKey + ", " : "") +
                (activationKey != null ? "activationKey=" + activationKey + ", " : "") +
                (alias != null ? "alias=" + alias + ", " : "") +
                (emailNotifications != null ? "emailNotifications=" + emailNotifications + ", " : "") +
                (smsNotifications != null ? "smsNotifications=" + smsNotifications + ", " : "") +
                (lang != null ? "lang=" + lang + ", " : "") +
            "}";
    }

}
