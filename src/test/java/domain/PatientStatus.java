package domain;

import com.fasterxml.jackson.annotation.JsonProperty;


public class PatientStatus {

    @JsonProperty("type")
    private String type;

    @JsonProperty("date_of_death")
    private String dateOfDeath;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(String dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }
}
