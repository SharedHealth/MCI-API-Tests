package domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by administrator on 3/2/15.
 */
public class Relation {

    private String type;

    @JsonProperty("hid")
    private String healthId;

    @JsonProperty("nid")
    private String nationalId;

    private String uid;

    @JsonProperty("bin_brn")
    private String birthRegistrationNumber;

    @JsonProperty("name_bangla")
    private String nameBangla;

    @JsonProperty("given_name")
    private String givenName;

    @JsonProperty("sur_name")
    private String surName;

    @JsonProperty("marriage_id")
    private String marriageId;

    @JsonProperty("relational_status")
    private String relationalStatus;
    private String id;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHealthId() {
        return healthId;
    }

    public void setHealthId(String healthId) {
        this.healthId = healthId;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getBirthRegistrationNumber() {
        return birthRegistrationNumber;
    }

    public void setBirthRegistrationNumber(String birthRegistrationNumber) {
        this.birthRegistrationNumber = birthRegistrationNumber;
    }

    public String getNameBangla() {
        return nameBangla;
    }

    public void setNameBangla(String nameBangla) {
        this.nameBangla = nameBangla;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getMarriageId() {
        return marriageId;
    }

    public void setMarriageId(String marriageId) {
        this.marriageId = marriageId;
    }

    public String getRelationalStatus() {
        return relationalStatus;
    }

    public void setRelationalStatus(String relationalStatus) {
        this.relationalStatus = relationalStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
