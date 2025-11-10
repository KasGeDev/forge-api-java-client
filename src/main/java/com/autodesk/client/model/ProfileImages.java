package com.autodesk.client.model;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Objects;

public class ProfileImages {

    @JsonProperty("sizeX20")
    private String sizeX20;
    @JsonProperty("sizeX40")
    private String sizeX40;
    @JsonProperty("sizeX50")
    private String sizeX50;
    @JsonProperty("sizeX58")
    private String sizeX58;
    @JsonProperty("sizeX80")
    private String sizeX80;
    @JsonProperty("sizeX120")
    private String sizeX120;
    @JsonProperty("sizeX160")
    private String sizeX160;
    @JsonProperty("sizeX176")
    private String sizeX176;
    @JsonProperty("sizeX240")
    private String sizeX240;
    @JsonProperty("sizeX360")
    private String sizeX360;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     *
     */
    public ProfileImages() {
    }

    /**
     *
     * @param sizeX80
     * @param sizeX120
     * @param sizeX58
     * @param sizeX176
     * @param sizeX160
     * @param sizeX20
     * @param sizeX360
     * @param sizeX240
     * @param sizeX40
     * @param sizeX50
     */
    public ProfileImages(String sizeX20, String sizeX40, String sizeX50, String sizeX58, String sizeX80, String sizeX120, String sizeX160, String sizeX176, String sizeX240, String sizeX360) {
        super();
        this.sizeX20 = sizeX20;
        this.sizeX40 = sizeX40;
        this.sizeX50 = sizeX50;
        this.sizeX58 = sizeX58;
        this.sizeX80 = sizeX80;
        this.sizeX120 = sizeX120;
        this.sizeX160 = sizeX160;
        this.sizeX176 = sizeX176;
        this.sizeX240 = sizeX240;
        this.sizeX360 = sizeX360;
    }

    @JsonProperty("sizeX20")
    public String getSizeX20() {
        return sizeX20;
    }

    @JsonProperty("sizeX20")
    public void setSizeX20(String sizeX20) {
        this.sizeX20 = sizeX20;
    }

    @JsonProperty("sizeX40")
    public String getSizeX40() {
        return sizeX40;
    }

    @JsonProperty("sizeX40")
    public void setSizeX40(String sizeX40) {
        this.sizeX40 = sizeX40;
    }

    @JsonProperty("sizeX50")
    public String getSizeX50() {
        return sizeX50;
    }

    @JsonProperty("sizeX50")
    public void setSizeX50(String sizeX50) {
        this.sizeX50 = sizeX50;
    }

    @JsonProperty("sizeX58")
    public String getSizeX58() {
        return sizeX58;
    }

    @JsonProperty("sizeX58")
    public void setSizeX58(String sizeX58) {
        this.sizeX58 = sizeX58;
    }

    @JsonProperty("sizeX80")
    public String getSizeX80() {
        return sizeX80;
    }

    @JsonProperty("sizeX80")
    public void setSizeX80(String sizeX80) {
        this.sizeX80 = sizeX80;
    }

    @JsonProperty("sizeX120")
    public String getSizeX120() {
        return sizeX120;
    }

    @JsonProperty("sizeX120")
    public void setSizeX120(String sizeX120) {
        this.sizeX120 = sizeX120;
    }

    @JsonProperty("sizeX160")
    public String getSizeX160() {
        return sizeX160;
    }

    @JsonProperty("sizeX160")
    public void setSizeX160(String sizeX160) {
        this.sizeX160 = sizeX160;
    }

    @JsonProperty("sizeX176")
    public String getSizeX176() {
        return sizeX176;
    }

    @JsonProperty("sizeX176")
    public void setSizeX176(String sizeX176) {
        this.sizeX176 = sizeX176;
    }

    @JsonProperty("sizeX240")
    public String getSizeX240() {
        return sizeX240;
    }

    @JsonProperty("sizeX240")
    public void setSizeX240(String sizeX240) {
        this.sizeX240 = sizeX240;
    }

    @JsonProperty("sizeX360")
    public String getSizeX360() {
        return sizeX360;
    }

    @JsonProperty("sizeX360")
    public void setSizeX360(String sizeX360) {
        this.sizeX360 = sizeX360;
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
        sb.append("ProfileImages{");
        sb.append("sizeX20=").append(sizeX20).append(", ");
        sb.append("sizeX40=").append(sizeX40).append(", ");
        sb.append("sizeX50=").append(sizeX50).append(", ");
        sb.append("sizeX58=").append(sizeX58).append(", ");
        sb.append("sizeX80=").append(sizeX80).append(", ");
        sb.append("sizeX120=").append(sizeX120).append(", ");
        sb.append("sizeX160=").append(sizeX160).append(", ");
        sb.append("sizeX176=").append(sizeX176).append(", ");
        sb.append("sizeX240=").append(sizeX240).append(", ");
        sb.append("sizeX360=").append(sizeX360).append(", ");
        sb.append("additionalProperties=").append(additionalProperties);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(sizeX20, sizeX40, sizeX50, sizeX58, sizeX80, sizeX120, sizeX160, sizeX176,
                sizeX240, sizeX360, additionalProperties);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ProfileImages rhs = (ProfileImages) obj;
        return Objects.equals(this.sizeX20, rhs.sizeX20)
                && Objects.equals(this.sizeX40, rhs.sizeX40)
                && Objects.equals(this.sizeX50, rhs.sizeX50)
                && Objects.equals(this.sizeX58, rhs.sizeX58)
                && Objects.equals(this.sizeX80, rhs.sizeX80)
                && Objects.equals(this.sizeX120, rhs.sizeX120)
                && Objects.equals(this.sizeX160, rhs.sizeX160)
                && Objects.equals(this.sizeX176, rhs.sizeX176)
                && Objects.equals(this.sizeX240, rhs.sizeX240)
                && Objects.equals(this.sizeX360, rhs.sizeX360)
                && Objects.equals(this.additionalProperties, rhs.additionalProperties);
    }

}