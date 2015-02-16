package tests.api;

import categories.ApiTest;
import com.jayway.restassured.response.ValidatableResponse;
import data.PatientDataSetUp;
import data.PatientDataStore;
import domain.Patient;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static com.jayway.restassured.RestAssured.given;



public class CreatePatientTests extends PatientDataSetUp {

    protected Patient primaryPatient;
    PatientDataStore dataStore = new PatientDataStore();


    @Category(ApiTest.class)
    @Test
    public void verifyCreatePatient() {

        primaryPatient = dataStore.defaultPatient;

        JSONObject person = createPatientDataJsonToPost(primaryPatient);
        person.remove("bin_brn");
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
                .body("results.present_address.address_line[0]", Matchers.equalTo("Test1"))
                .body("results.present_address.division_id[0]", Matchers.equalTo("10"))
                .body("results.present_address.district_id[0]", Matchers.equalTo("04"))
                .body("results.present_address.upazila_id[0]", Matchers.equalTo("09"))
                .body("results.present_address.city_corporation_id[0]", Matchers.equalTo("99"))
                .body("results.present_address.union_or_urban_ward_id[0]", Matchers.equalTo("13"));

        System.out.println("Patient with NID " + primaryPatient.getNid() + " verified in MCI");
    }




    @Category(ApiTest.class)
    @Test
    public void verifyCreatePatientErrorForInvalidNID() {

        primaryPatient = dataStore.defaultPatient.withNid("invalid").build();

        JSONObject person = createPatientDataJsonToPost(primaryPatient);
        ValidatableResponse body = given().contentType("application/json")
                .body(person.toString())
                .when().post("/patients")
                .then()
//                .log().body(true)
                .assertThat().statusCode(400)
                .body("error_code", Matchers.equalTo(1000))
                .body("errors.code[0]", Matchers.equalTo(1002))
                .body("errors.field[0]", Matchers.equalTo("nid"));
        System.out.println("Patient with NID " + primaryPatient.getNid() + " is not available in MCI");
    }


    @Category(ApiTest.class)
    @Test

    public void verifyCreatePatientWithBRNID(){

        primaryPatient= dataStore.defaultPatient;

        JSONObject person = createPatientDataJsonToPost(primaryPatient);
        person.remove("nid");
        given().contentType("application/json")
                .body(person.toString())
                .when().post("/patients")
                .then().assertThat().statusCode(201);

        System.out.println("Patient with BRN " + primaryPatient.getBinBRN() + " created in MCI ");

        System.out.println(person.toString());
        given().pathParam("bin_brn", primaryPatient.getBinBRN())
                .when().get("/patients?bin_brn={bin_brn}")
                .then()

                .body("results.hid[0]", Matchers.notNullValue())
                .body("results.bin_brn[0]", Matchers.equalTo(primaryPatient.getBinBRN()))
                .body("results.given_name[0]", Matchers.equalTo(primaryPatient.getGiven_name()))
                .body("results.sur_name[0]", Matchers.equalTo(primaryPatient.getSur_name()))
                .body("results.date_of_birth[0]", Matchers.equalTo(primaryPatient.getDateOfBirth()))
                .body("results.gender[0]", Matchers.equalTo(primaryPatient.getGender()))
                .body("results.present_address.address_line[0]", Matchers.equalTo("Test1"))
                .body("results.present_address.division_id[0]", Matchers.equalTo("10"))
                .body("results.present_address.district_id[0]", Matchers.equalTo("04"))
                .body("results.present_address.upazila_id[0]", Matchers.equalTo("09"))
                .body("results.present_address.city_corporation_id[0]", Matchers.equalTo("99"))
                .body("results.present_address.union_or_urban_ward_id[0]", Matchers.equalTo("13"));






        System.out.println("Patient with BRN " + primaryPatient.getBinBRN() + " verified in MCI");


    }






}
