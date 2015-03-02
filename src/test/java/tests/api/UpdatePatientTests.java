package tests.api;

import categories.ApiTest;
import data.PatientDataSetUp;
import domain.Patient;
import org.hamcrest.Matchers;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import utils.WebDriverProperties;

import static com.jayway.restassured.RestAssured.given;
import static utils.FileUtil.asString;


public class UpdatePatientTests extends PatientDataSetUp {

    JSONObject updatedValue= new JSONObject();

    @Category(ApiTest.class)
    @Test
    public void verifyPatientUpdateWithFullUpdatedPayload() throws Exception {

        String json = asString("jsons/patient/full_payload_without_ids.json");
        String hid = createPatient(json);

        String updatedJson = asString("jsons/patient/full_payload_with_updated_data.json");
        updatePatient(updatedJson, hid);

        Patient patient = getPatientObjectFromString(updatedJson);

        given().pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .when().get("/patients/{hid}")
                .then()
                .body("sur_name", Matchers.equalTo(patient.getSurName()))
                .body("date_of_birth", Matchers.equalTo(patient.getDateOfBirth()))
                .body("bin_brn", Matchers.equalTo(patient.getBirthRegistrationNumber()))
                .body("status", Matchers.equalTo(patient.getStatus()))
                .body("confidential", Matchers.equalTo(patient.getConfidential()))
                .body("household_code", Matchers.equalTo(patient.getHouseholdCode()));

    }

    @Category(ApiTest.class)
    @Test
    public void verifyPatientPartialUpdate() throws JSONException, InterruptedException {

        String json = asString("jsons/patient/full_payload_without_ids.json");
        String hid = createPatient(json);

        updatedValue.put("sur_name", "updated");
        updatePatient(updatedValue.toString(), hid);

        given().pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .when().get("/patients/{hid}")
                .then()
                .body("sur_name", Matchers.equalTo("updated"));

    }

}
