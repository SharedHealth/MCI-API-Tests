package tests.api;

import categories.ApiTest;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.RestAssuredConfig;
import data.PatientDataStore;
import domain.Patient;
import org.hamcrest.Matchers;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import static com.jayway.restassured.RestAssured.basic;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.config.EncoderConfig.encoderConfig;

public class CreatePatientTests {

    protected Patient primaryPatient;

    @Before
    public void setUp() {

        RestAssured.baseURI = "http://172.18.46.56";
        RestAssured.baseURI = "https://bdshr-mci-qa.twhosted.com";
//        RestAssured.baseURI = WebDriverProperties.getProperty("mciURL");
//        RestAssured.port = 8081;
        RestAssured.basePath = "/api/v1";
        RestAssured.authentication = basic("mci", "password");
        RestAssured.rootPath = "";
        RestAssured.config = new RestAssuredConfig().encoderConfig(encoderConfig().defaultContentCharset("UTF-8"));
    }

    @Category(ApiTest.class)
    @Test
    public void verifyCreatePatient() {

        PatientDataStore dataStore = new PatientDataStore();
        primaryPatient = dataStore.defaultPatient;


        JSONObject person = createPatientDataJsonToPost(primaryPatient);
        given().contentType("application/json")
                .body(person.toString())
                .when().post("/patients")
                .then().assertThat().statusCode(201);
        System.out.println("Patient with NID " + primaryPatient.getNid() + " created in MCI ");

        given().pathParam("nid", primaryPatient.getNid())
                .when().get("/patients?nid={nid}")
                .then()
                .body("results.hid[0]", Matchers.notNullValue())
                .body("results.nid[0]", Matchers.equalTo(primaryPatient.getNid()))
                .body("results.given_name[0]", Matchers.equalTo(primaryPatient.getGiven_name()))
                .body("results.sur_name[0]", Matchers.equalTo(primaryPatient.getSur_name()))
                .body("results.date_of_birth[0]", Matchers.equalTo(primaryPatient.getDateOfBirth()))
                .body("results.gender[0]", Matchers.equalTo(primaryPatient.getGender()))
                .body("results.present_address.address_line[0]", Matchers.equalTo("test"))
                .body("results.present_address.division_id[0]", Matchers.equalTo("10"))
                .body("results.present_address.district_id[0]", Matchers.equalTo("09"))
                .body("results.present_address.upazila_id[0]", Matchers.equalTo("18"))
                .body("results.present_address.city_corporation_id[0]", Matchers.equalTo("16"))
                .body("results.present_address.union_or_urban_ward_id[0]", Matchers.equalTo("01"));

        System.out.println("Patient with NID " + primaryPatient.getNid() + " verified in MCI");
    }


    @Category(ApiTest.class)
    @Test
    public void verifyCreatePatientErrorForInvalidNID() {

        PatientDataStore dataStore = new PatientDataStore();
        primaryPatient = dataStore.defaultPatient.withNid("invalid").build();

        JSONObject person = createPatientDataJsonToPost(primaryPatient);
        given().contentType("application/json")
                .body(person.toString())
                .when().post("/patients")
                .then()
//                .log().body(true)
                .assertThat().statusCode(400)
                .body("error_code", Matchers.equalTo(1000))
                .body("errors.code[0]", Matchers.equalTo(1002))
                .body("errors.field[0]", Matchers.equalTo("nid"));
    }

    private JSONObject createPatientDataJsonToPost(Patient primaryPatient) {
        JSONObject person = new JSONObject();
        JSONObject present_address = new JSONObject();
        try {
            person.put("nid", primaryPatient.getNid());
            person.put("given_name", primaryPatient.getGiven_name());
            person.put("sur_name", primaryPatient.getSur_name());
            person.put("date_of_birth", primaryPatient.getDateOfBirth());
            person.put("gender", primaryPatient.getGender());
            present_address.put("address_line", "test");
            present_address.put("division_id", "10");
            present_address.put("district_id", "09");
            present_address.put("upazila_id", "18");
            present_address.put("city_corporation_id", "16");
            present_address.put("union_or_urban_ward_id", "01");
            person.put("present_address", present_address);

        } catch (JSONException e) {
            e.printStackTrace();
        }
//        System.out.println(person.toString());
        return person;
    }

    @After
    public void tearDown() {
        RestAssured.reset();
    }

}
