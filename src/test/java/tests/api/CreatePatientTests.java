package tests.api;

import categories.ApiTest;
import data.PatientDataSetUp;
import domain.Patient;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import utils.WebDriverProperties;

import static com.jayway.restassured.RestAssured.given;
import static utils.FileUtil.asString;

public class CreatePatientTests extends PatientDataSetUp {

    @Category(ApiTest.class)
    @Test
    public void verifyCreatePatient() throws Exception {

        String json = asString("jsons/patient/full_payload_without_ids.json");
        String hid = createPatient(json);
        Patient patient = getPatientObjectFromString(json);

        given().header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .header(CLIENT_ID, userId.trim())
                .header("from", email.trim())
                .when().get("/patients/" + hid)
                .then()
                .body("hid", Matchers.notNullValue())
                .body("given_name", Matchers.equalTo(patient.getGivenName()))
                .body("sur_name", Matchers.equalTo(patient.getSurName()))
                .body("date_of_birth", Matchers.equalTo(patient.getDateOfBirth()))
                .body("gender", Matchers.equalTo(patient.getGender()))
                .body("present_address.address_line", Matchers.equalTo(patient.getAddress().getAddressLine()))
                .body("present_address.division_id", Matchers.equalTo(patient.getAddress().getDivisionId()))
                .body("present_address.district_id", Matchers.equalTo(patient.getAddress().getDistrictId()))
                .body("present_address.upazila_id", Matchers.equalTo(patient.getAddress().getUpazilaId()))
                .body("present_address.city_corporation_id", Matchers.equalTo(patient.getAddress().getCityCorporationId()))
                .body("present_address.union_or_urban_ward_id", Matchers.equalTo(patient.getAddress().getUnionOrUrbanWardId()));

    }


    @Category(ApiTest.class)
    @Test
    public void verifyCreatePatientErrorForInvalidNID() throws Exception {

        String json = asString("jsons/patient/full_payload_without_ids.json");
        Patient patient = getPatientObjectFromString(json);
        patient.setNationalId("invalid");
        String updatedJson = getJsonFromObject(patient);

        given().contentType("application/json")
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .header(CLIENT_ID, userId.trim())
                .header("from", email.trim())
                .body(updatedJson)
                .when().post("/patients")
                .then()
                .assertThat().statusCode(400)
                .body("error_code", Matchers.equalTo(1000))
                .body("errors[0].code", Matchers.equalTo(1002))
                .body("errors[0].field", Matchers.equalTo("nid"));
    }

}
