package tests.api;

import categories.ApiTest;
import data.PatientDataSetUp;
import data.PatientDataStore;
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

    protected Patient primaryPatient;
    JSONObject updatedValue= new JSONObject();
    PatientDataStore dataStore = new PatientDataStore();

    @Category(ApiTest.class)
    @Test
    public void verifyPatientUpdateWithFullUpdatedPayload() throws JSONException, InterruptedException {

        primaryPatient= dataStore.defaultPatient;

        JSONObject patient = createPatientDataJsonToPost(primaryPatient);
        String hid = createPatient(patient);
        String json = asString("jsons/patient/full_payload_with_updated_data.json");
        updatePatientByJsonData(hid, json);

        given().pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .when().get("/patients/{hid}")
                .then()
                .body("sur_name", Matchers.equalTo("AymaanAymaan"))
                .body("date_of_birth", Matchers.equalTo("1990-02-28"))
                .body("bin_brn", Matchers.equalTo("12345678901234590"))
                .body("status", Matchers.equalTo("2"))
                .body("confidential", Matchers.equalTo("Yes"))
                .body("household_code", Matchers.equalTo("5678"));

    }

    @Category(ApiTest.class)
    @Test
    public void verifyPatientPartialUpdate() throws JSONException, InterruptedException {

        primaryPatient= dataStore.defaultPatient;

        JSONObject patient = createPatientDataJsonToPost(primaryPatient);
        String hid = createPatient(patient);
        updatedValue.put("sur_name", "updated");
        updatePatient(updatedValue, hid);

        given().pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .when().get("/patients/{hid}")
                .then()
                .body("sur_name", Matchers.equalTo("updated"));

    }

}
