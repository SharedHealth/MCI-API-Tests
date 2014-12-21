package domain;


public class PatientDetails {

    private String given_name;
    private String sur_name;
    private String dateOfBirth;
    private String nid;
    private String gender;

    public PatientDetails( String given_name, String sur_name, String dateOfBirth,String nid,String gender) {
        this.given_name = given_name;
        this.sur_name = sur_name;
        this.dateOfBirth = dateOfBirth;
        this.nid = nid;
        this.gender = gender;
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

    public String getNid() {
        return nid;
    }
    public String getGender() {
        return gender;
    }


}
