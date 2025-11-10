
package com.autodesk.client.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Objects;


public class UserAtMe {

    @JsonProperty("userId")
    private String userId;
    @JsonProperty("userName")
    private String userName;
    @JsonProperty("emailId")
    private String emailId;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("emailVerified")
    private Boolean emailVerified;
    @JsonProperty("2FaEnabled")
    private Boolean _2FaEnabled;
    @JsonProperty("countryCode")
    private String countryCode;
    @JsonProperty("language")
    private String language;
    @JsonProperty("optin")
    private Boolean optin;
    @JsonProperty("lastModified")
    private String lastModified;
    @JsonProperty("profileImages")
    private ProfileImages profileImages;
    @JsonProperty("ldapInfo")
    private LdapInfo ldapInfo;
    @JsonProperty("socialUserInfoList")
    private List<Object> socialUserInfoList = null;
    @JsonProperty("twoFactorAuthType")
    private String twoFactorAuthType;
    @JsonProperty("contactMode")
    private String contactMode;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     *
     */
    public UserAtMe() {
    }

    /**
     *
     * @param socialUserInfoList
     * @param lastName
     * @param profileImages
     * @param optin
     * @param emailId
     * @param language
     * @param userName
     * @param userId
     * @param twoFactorAuthType
     * @param contactMode
     * @param firstName
     * @param emailVerified
     * @param ldapInfo
     * @param _2FaEnabled
     * @param countryCode
     * @param lastModified
     */
    public UserAtMe(String userId, String userName, String emailId, String firstName, String lastName, Boolean emailVerified, Boolean _2FaEnabled, String countryCode, String language, Boolean optin, String lastModified, ProfileImages profileImages, LdapInfo ldapInfo, List<Object> socialUserInfoList, String twoFactorAuthType, String contactMode) {
        super();
        this.userId = userId;
        this.userName = userName;
        this.emailId = emailId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailVerified = emailVerified;
        this._2FaEnabled = _2FaEnabled;
        this.countryCode = countryCode;
        this.language = language;
        this.optin = optin;
        this.lastModified = lastModified;
        this.profileImages = profileImages;
        this.ldapInfo = ldapInfo;
        this.socialUserInfoList = socialUserInfoList;
        this.twoFactorAuthType = twoFactorAuthType;
        this.contactMode = contactMode;
    }

    @JsonProperty("userId")
    public String getUserId() {
        return userId;
    }

    @JsonProperty("userId")
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @JsonProperty("userName")
    public String getUserName() {
        return userName;
    }

    @JsonProperty("userName")
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @JsonProperty("emailId")
    public String getEmailId() {
        return emailId;
    }

    @JsonProperty("emailId")
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @JsonProperty("firstName")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("lastName")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("emailVerified")
    public Boolean getEmailVerified() {
        return emailVerified;
    }

    @JsonProperty("emailVerified")
    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    @JsonProperty("2FaEnabled")
    public Boolean get2FaEnabled() {
        return _2FaEnabled;
    }

    @JsonProperty("2FaEnabled")
    public void set2FaEnabled(Boolean _2FaEnabled) {
        this._2FaEnabled = _2FaEnabled;
    }

    @JsonProperty("countryCode")
    public String getCountryCode() {
        return countryCode;
    }

    @JsonProperty("countryCode")
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @JsonProperty("language")
    public String getLanguage() {
        return language;
    }

    @JsonProperty("language")
    public void setLanguage(String language) {
        this.language = language;
    }

    @JsonProperty("optin")
    public Boolean getOptin() {
        return optin;
    }

    @JsonProperty("optin")
    public void setOptin(Boolean optin) {
        this.optin = optin;
    }

    @JsonProperty("lastModified")
    public String getLastModified() {
        return lastModified;
    }

    @JsonProperty("lastModified")
    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    @JsonProperty("profileImages")
    public ProfileImages getProfileImages() {
        return profileImages;
    }

    @JsonProperty("profileImages")
    public void setProfileImages(ProfileImages profileImages) {
        this.profileImages = profileImages;
    }

    @JsonProperty("ldapInfo")
    public LdapInfo getLdapInfo() {
        return ldapInfo;
    }

    @JsonProperty("ldapInfo")
    public void setLdapInfo(LdapInfo ldapInfo) {
        this.ldapInfo = ldapInfo;
    }

    @JsonProperty("socialUserInfoList")
    public List<Object> getSocialUserInfoList() {
        return socialUserInfoList;
    }

    @JsonProperty("socialUserInfoList")
    public void setSocialUserInfoList(List<Object> socialUserInfoList) {
        this.socialUserInfoList = socialUserInfoList;
    }

    @JsonProperty("twoFactorAuthType")
    public String getTwoFactorAuthType() {
        return twoFactorAuthType;
    }

    @JsonProperty("twoFactorAuthType")
    public void setTwoFactorAuthType(String twoFactorAuthType) {
        this.twoFactorAuthType = twoFactorAuthType;
    }

    @JsonProperty("contactMode")
    public String getContactMode() {
        return contactMode;
    }

    @JsonProperty("contactMode")
    public void setContactMode(String contactMode) {
        this.contactMode = contactMode;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UserAtMe{");
        sb.append("userId=").append(userId).append(", ");
        sb.append("userName=").append(userName).append(", ");
        sb.append("emailId=").append(emailId).append(", ");
        sb.append("firstName=").append(firstName).append(", ");
        sb.append("lastName=").append(lastName).append(", ");
        sb.append("emailVerified=").append(emailVerified).append(", ");
        sb.append("_2FaEnabled=").append(_2FaEnabled).append(", ");
        sb.append("countryCode=").append(countryCode).append(", ");
        sb.append("language=").append(language).append(", ");
        sb.append("optin=").append(optin).append(", ");
        sb.append("lastModified=").append(lastModified).append(", ");
        sb.append("profileImages=").append(profileImages).append(", ");
        sb.append("ldapInfo=").append(ldapInfo).append(", ");
        sb.append("socialUserInfoList=").append(socialUserInfoList).append(", ");
        sb.append("twoFactorAuthType=").append(twoFactorAuthType).append(", ");
        sb.append("contactMode=").append(contactMode).append(", ");
        sb.append("additionalProperties=").append(additionalProperties);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(socialUserInfoList, lastName, profileImages, optin, emailId, language,
                userName, userId, twoFactorAuthType, contactMode, firstName, emailVerified, ldapInfo,
                _2FaEnabled, countryCode, lastModified, additionalProperties);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UserAtMe rhs = (UserAtMe) obj;
        return Objects.equals(this.socialUserInfoList, rhs.socialUserInfoList)
                && Objects.equals(this.lastName, rhs.lastName)
                && Objects.equals(this.profileImages, rhs.profileImages)
                && Objects.equals(this.optin, rhs.optin)
                && Objects.equals(this.emailId, rhs.emailId)
                && Objects.equals(this.language, rhs.language)
                && Objects.equals(this.userName, rhs.userName)
                && Objects.equals(this.userId, rhs.userId)
                && Objects.equals(this.twoFactorAuthType, rhs.twoFactorAuthType)
                && Objects.equals(this.contactMode, rhs.contactMode)
                && Objects.equals(this.firstName, rhs.firstName)
                && Objects.equals(this.emailVerified, rhs.emailVerified)
                && Objects.equals(this.ldapInfo, rhs.ldapInfo)
                && Objects.equals(this._2FaEnabled, rhs._2FaEnabled)
                && Objects.equals(this.countryCode, rhs.countryCode)
                && Objects.equals(this.lastModified, rhs.lastModified)
                && Objects.equals(this.additionalProperties, rhs.additionalProperties);
    }

}