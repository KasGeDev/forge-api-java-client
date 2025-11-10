package com.autodesk.client.model;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.Objects;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "displayName",
        "createTime",
        "createUserId",
        "createUserName",
        "lastModifiedTime",
        "lastModifiedUserId",
        "lastModifiedUserName",
        "lastModifiedTimeRollup",
        "objectCount",
        "hidden",
        "extension"
})
public class JsonApiAttributes {

    @JsonProperty("name")
    private String name;
    @JsonProperty("displayName")
    private String displayName;
    @JsonProperty("createTime")
    private String createTime;
    @JsonProperty("createUserId")
    private String createUserId;
    @JsonProperty("createUserName")
    private String createUserName;
    @JsonProperty("lastModifiedTime")
    private String lastModifiedTime;
    @JsonProperty("lastModifiedUserId")
    private String lastModifiedUserId;
    @JsonProperty("lastModifiedUserName")
    private String lastModifiedUserName;
    @JsonProperty("lastModifiedTimeRollup")
    private String lastModifiedTimeRollup;
    @JsonProperty("objectCount")
    private Integer objectCount;
    @JsonProperty("hidden")
    private Boolean hidden;
    @JsonProperty("extension")
    private BaseAttributesExtensionObject extension;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("displayName")
    public String getDisplayName() {
        return displayName;
    }

    @JsonProperty("displayName")
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @JsonProperty("createTime")
    public String getCreateTime() {
        return createTime;
    }

    @JsonProperty("createTime")
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @JsonProperty("createUserId")
    public String getCreateUserId() {
        return createUserId;
    }

    @JsonProperty("createUserId")
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    @JsonProperty("createUserName")
    public String getCreateUserName() {
        return createUserName;
    }

    @JsonProperty("createUserName")
    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    @JsonProperty("lastModifiedTime")
    public String getLastModifiedTime() {
        return lastModifiedTime;
    }

    @JsonProperty("lastModifiedTime")
    public void setLastModifiedTime(String lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    @JsonProperty("lastModifiedUserId")
    public String getLastModifiedUserId() {
        return lastModifiedUserId;
    }

    @JsonProperty("lastModifiedUserId")
    public void setLastModifiedUserId(String lastModifiedUserId) {
        this.lastModifiedUserId = lastModifiedUserId;
    }

    @JsonProperty("lastModifiedUserName")
    public String getLastModifiedUserName() {
        return lastModifiedUserName;
    }

    @JsonProperty("lastModifiedUserName")
    public void setLastModifiedUserName(String lastModifiedUserName) {
        this.lastModifiedUserName = lastModifiedUserName;
    }

    @JsonProperty("lastModifiedTimeRollup")
    public String getLastModifiedTimeRollup() {
        return lastModifiedTimeRollup;
    }

    @JsonProperty("lastModifiedTimeRollup")
    public void setLastModifiedTimeRollup(String lastModifiedTimeRollup) {
        this.lastModifiedTimeRollup = lastModifiedTimeRollup;
    }

    @JsonProperty("objectCount")
    public Integer getObjectCount() {
        return objectCount;
    }

    @JsonProperty("objectCount")
    public void setObjectCount(Integer objectCount) {
        this.objectCount = objectCount;
    }

    @JsonProperty("hidden")
    public Boolean getHidden() {
        return hidden;
    }

    @JsonProperty("hidden")
    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    @JsonProperty("extension")
    public BaseAttributesExtensionObject getExtension() {
        return extension;
    }

    @JsonProperty("extension")
    public void setExtension(BaseAttributesExtensionObject extension) {
        this.extension = extension;
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
        sb.append("JsonApiAttributes{");
        sb.append("name=").append(name).append(", ");
        sb.append("displayName=").append(displayName).append(", ");
        sb.append("createTime=").append(createTime).append(", ");
        sb.append("createUserId=").append(createUserId).append(", ");
        sb.append("createUserName=").append(createUserName).append(", ");
        sb.append("lastModifiedTime=").append(lastModifiedTime).append(", ");
        sb.append("lastModifiedUserId=").append(lastModifiedUserId).append(", ");
        sb.append("lastModifiedUserName=").append(lastModifiedUserName).append(", ");
        sb.append("lastModifiedTimeRollup=").append(lastModifiedTimeRollup).append(", ");
        sb.append("objectCount=").append(objectCount).append(", ");
        sb.append("hidden=").append(hidden).append(", ");
        sb.append("extension=").append(extension).append(", ");
        sb.append("additionalProperties=").append(additionalProperties);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(createUserId, objectCount, extension, lastModifiedTime, hidden, displayName,
                lastModifiedTimeRollup, createUserName, lastModifiedUserName, createTime, name,
                lastModifiedUserId, additionalProperties);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        JsonApiAttributes rhs = (JsonApiAttributes) obj;
        return Objects.equals(this.createUserId, rhs.createUserId)
                && Objects.equals(this.objectCount, rhs.objectCount)
                && Objects.equals(this.extension, rhs.extension)
                && Objects.equals(this.lastModifiedTime, rhs.lastModifiedTime)
                && Objects.equals(this.hidden, rhs.hidden)
                && Objects.equals(this.displayName, rhs.displayName)
                && Objects.equals(this.lastModifiedTimeRollup, rhs.lastModifiedTimeRollup)
                && Objects.equals(this.createUserName, rhs.createUserName)
                && Objects.equals(this.lastModifiedUserName, rhs.lastModifiedUserName)
                && Objects.equals(this.createTime, rhs.createTime)
                && Objects.equals(this.name, rhs.name)
                && Objects.equals(this.lastModifiedUserId, rhs.lastModifiedUserId)
                && Objects.equals(this.additionalProperties, rhs.additionalProperties);
    }

}