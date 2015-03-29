package domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;

public class Patient {

    @JsonProperty("hid")
    private String healthId;

    @JsonProperty("nid")
    private String nationalId;

    @JsonProperty("bin_brn")
    private String birthRegistrationNumber;

    @JsonProperty("name_bangla")
    private String nameBangla;

    @JsonProperty("given_name")
    private String givenName;

    @JsonProperty("sur_name")
    private String surName;

    @JsonProperty("date_of_birth")
    private String dateOfBirth;

    private String gender;
    private String occupation;

    @JsonProperty("edu_level")
    private String educationLevel;

    private List<Relation> relations;

    private String uid;

    @JsonProperty("place_of_birth")
    private String placeOfBirth;

    private String religion;

    @JsonProperty("blood_group")
    private String bloodGroup;

    private String nationality;

    private String disability;

    private String ethnicity;

    @JsonProperty("present_address")
    private Address address;

    @JsonProperty("primary_contact")
    private String primaryContact;

    @JsonProperty("phone_number")
    private PhoneNumber phoneNumber;

    @JsonProperty("primary_contact_number")
    private PhoneNumber primaryContactNumber;

    @JsonProperty("permanent_address")
    private Address permanentAddress;

    @JsonProperty("marital_status")
    private String maritalStatus;

    @JsonProperty("status")
    private PatientStatus status;
    private String confidential;

    @JsonProperty("household_code")
    private String householdCode;

    @JsonProperty("created_at")
    private UUID createdAt;

    @JsonProperty("updated_at")
    @JsonIgnore
    private UUID updatedAt;

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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public List<Relation> getRelations() {
        return relations;
    }

    public void setRelations(List<Relation> relations) {
        this.relations = relations;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getDisability() {
        return disability;
    }

    public void setDisability(String disability) {
        this.disability = disability;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPrimaryContact() {
        return primaryContact;
    }

    public void setPrimaryContact(String primaryContact) {
        this.primaryContact = primaryContact;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public PhoneNumber getPrimaryContactNumber() {
        return primaryContactNumber;
    }

    public void setPrimaryContactNumber(PhoneNumber primaryContactNumber) {
        this.primaryContactNumber = primaryContactNumber;
    }

    public Address getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(Address permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getConfidential() {
        return confidential;
    }

    public void setConfidential(String confidential) {
        this.confidential = confidential;
    }

    public String getHouseholdCode() {
        return householdCode;
    }

    public void setHouseholdCode(String householdCode) {
        this.householdCode = householdCode;
    }

    public UUID getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(UUID createdAt) {
        this.createdAt = createdAt;
    }

    public UUID getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(UUID updatedAt) {
        this.updatedAt = updatedAt;
    }

    public PatientStatus getStatus() {
        return status;
    }

    public void setStatus(PatientStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "healthId='" + healthId + '\'' +
                ", nationalId='" + nationalId + '\'' +
                ", birthRegistrationNumber='" + birthRegistrationNumber + '\'' +
                ", nameBangla='" + nameBangla + '\'' +
                ", givenName='" + givenName + '\'' +
                ", surName='" + surName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", gender='" + gender + '\'' +
                ", occupation='" + occupation + '\'' +
                ", educationLevel='" + educationLevel + '\'' +
                ", relations=" + relations +
                ", uid='" + uid + '\'' +
                ", placeOfBirth='" + placeOfBirth + '\'' +
                ", religion='" + religion + '\'' +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", nationality='" + nationality + '\'' +
                ", disability='" + disability + '\'' +
                ", ethnicity='" + ethnicity + '\'' +
                ", address=" + address +
                ", primaryContact='" + primaryContact + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", primaryContactNumber=" + primaryContactNumber +
                ", permanentAddress=" + permanentAddress +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", status=" + status +
                ", confidential='" + confidential + '\'' +
                ", householdCode='" + householdCode + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

