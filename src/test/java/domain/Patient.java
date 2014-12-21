package domain;

public class Patient {

    private String given_name;
    private String sur_name;
    private String dateOfBirth;
    private String nid;
    private String gender;


    public Patient withGiven_name(String given_name) {
        this.given_name = given_name;
        return this;
    }

    public Patient withSur_name(String sur_name) {
        this.sur_name = sur_name;
        return this;
    }

    public Patient withDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public Patient withNid(String nid) {
        this.nid = nid;
        return this;
    }

    public Patient withGender(String gender) {
        this.gender = gender;
        return this;
    }


    public String getNid() {
        return nid;
    }

    public String getGiven_name() {
        return given_name;
    }

    public String getSur_name() {
        return sur_name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public Patient build() {
        return this;
    }
}
