package tests.api;

import categories.ApiTest;
import data.PatientDataSetUp;
import data.PatientDataStore;
import domain.Patient;
import org.hamcrest.Matchers;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import utils.WebDriverProperties;


import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.responseContentType;


/**
 * Created by ashutosh on 24/12/14.
 */
public class UpdatePatientTests extends PatientDataSetUp {

    String hid;
    String ApprovalURL="/catchments/100409/approvals/{hid}/";
    protected Patient primaryPatient;
    PatientDataStore dataStore = new PatientDataStore();
    JSONObject updatedValue= new JSONObject();
    JSONObject AddNewValue= new JSONObject();
    /**
     * Created by ashutosh on 26/12/14.
     * API verify that if required approval field of patient is updated then after accepting the update request
     * corresponding patient detail is updated
     */

    @Category(ApiTest.class)
    @Ignore
    @Test
    public void verifyUpdatedPatientApprovalApproved() throws JSONException, InterruptedException {

        primaryPatient= dataStore.defaultPatient;

        JSONObject person = createPatientDataJsonToPost(primaryPatient);
        given().contentType("application/json")
               .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
               .body(person.toString())
               .when().post("/patients")
               .then().assertThat().statusCode(201);

        System.out.println("Patient created in MCI ");


        hid=(given().pathParam("bin_brn", primaryPatient.getBinBRN())
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .when().get("/patients?bin_brn={bin_brn}")
                .andReturn().print().substring(38, 57));

        System.out.println(hid);
        updatedValue.put("given_name", "updated");
      System.out.println(updatedValue.get("given_name"));

        given().contentType("Application/json")
                .pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .body(updatedValue.toString())
                .when().put("/patients/{hid}")
                .then().assertThat().statusCode(202);

        System.out.println("Patient is mark for updatation");



        given().contentType("Application/json")
                .pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .body(updatedValue.toString())
                .when().put(ApprovalURL)
                .then().assertThat().statusCode(202);

        System.out.println("Patient detail is approved for update");

        given().pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .when().get("/patients/{hid}")
                .andReturn().body().print();

        given().pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .when().get("/patients/{hid}")
                .then()
                .body("given_name", Matchers.equalTo(updatedValue.get("given_name")));


        System.out.println("Verify that patient data is approved");
    }

    /**
     * Created by ashutosh on 31/12/14.
     * API verify that if required approval field of patient is updated then after rejecting the update request
     * corresponding patient detail should not updated
     */


    @Category(ApiTest.class)
    @Test
    public void verifyUpdatedPatientApprovalRejected() throws JSONException, InterruptedException {

        primaryPatient= dataStore.defaultPatient;

        JSONObject person = createPatientDataJsonToPost(primaryPatient);
        given().contentType("Application/json")
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .body(person.toString())
                .when().post("/patients")
                .then().assertThat().statusCode(201);

        System.out.println("Patient created in MCI ");


        hid=(given().pathParam("bin_brn", primaryPatient.getBinBRN())
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .when().get("/patients?bin_brn={bin_brn}")
                .andReturn().print().substring(38,57));

        System.out.println(hid);
        updatedValue.put("given_name", "updated");
        System.out.println(updatedValue.get("given_name"));

        given().contentType("Application/json")
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .pathParam("hid", hid)
                .body(updatedValue.toString())
                .when().put("/patients/{hid}")
                .then().assertThat().statusCode(202);

        System.out.println("Patient is mark for updatation");

        given().contentType("Application/json")
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .pathParam("hid", hid)
                .body(updatedValue.toString())
                .when().delete(ApprovalURL)
                .then().assertThat().statusCode(202);

        System.out.println("Patient  detail is rejected for update");

        given().pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .when().get("/patients/{hid}")
                .andReturn().body().print();

        given().pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .when().get("/patients/{hid}")
                .then()
                .body("given_name", Matchers.equalTo(primaryPatient.getGiven_name()));


        System.out.println("Verify that patient update data is rejected");
    }

    @Category(ApiTest.class)
    @Test
    public void verifyNewlyPatientDataApproved() throws JSONException, InterruptedException {

        primaryPatient= dataStore.defaultPatient;

        JSONObject person = createPatientDataJsonToPost(primaryPatient);
        given().contentType("Application/json")
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .body(person.toString())
                .when().post("/patients")
                .then().assertThat().statusCode(201);

        System.out.println("Patient created in MCI ");


        hid=(given().pathParam("bin_brn", primaryPatient.getBinBRN())
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .when().get("/patients?bin_brn={bin_brn}")
                .andReturn().print().substring(38,57));

        System.out.println(hid);
        AddNewValue.put("occupation", "02");
        System.out.println(AddNewValue.get("occupation"));

        given().contentType("Application/json")
                .pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .body(AddNewValue.toString())
                .when().put("/patients/{hid}")
                .then().assertThat().statusCode(202);

        System.out.println("Patient's new data is marked for updation");



        given().contentType("Application/json")
                .pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .body(AddNewValue.toString())
                .when().put(ApprovalURL)
                .then().assertThat().statusCode(202);

        System.out.println("Patient detail is approved for new data");

        given().pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .when().get("/patients/{hid}")
                .andReturn().body().print();

        given().pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .when().get("/patients/{hid}")
                .then()
                .body("occupation", Matchers.equalTo(AddNewValue.get("occupation")));


        System.out.println("Verify that patient new data is approved");
    }

    @Category(ApiTest.class)
    @Test
    public void verifyNewlyPatientDataRejected() throws JSONException, InterruptedException {

        primaryPatient= dataStore.defaultPatient;

        JSONObject person = createPatientDataJsonToPost(primaryPatient);
        given().contentType("Application/json")
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .body(person.toString())
                .when().post("/patients")
                .then().assertThat().statusCode(201);

        System.out.println("Patient created in MCI ");


        hid=(given().pathParam("bin_brn", primaryPatient.getBinBRN())
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .when().get("/patients?bin_brn={bin_brn}")
                .andReturn().print().substring(38,57));

        System.out.println(hid);
        AddNewValue.put("occupation", "02");
        System.out.println(AddNewValue.get("occupation"));

        given().contentType("Application/json")
                .pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .body(AddNewValue.toString())
                .when().put("/patients/{hid}")
                .then().assertThat().statusCode(202);

        System.out.println("Patient's new data is marked for updation");



        given().contentType("Application/json")
                .pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .body(AddNewValue.toString())
                .when().delete(ApprovalURL)
                .then().assertThat().statusCode(202);

        System.out.println("Patient detail is rejected for new data");

        given().pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .when().get("/patients/{hid}")
                .andReturn().body().print();

        given().pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .when().get("/patients/{hid}")
                .then()
                .body("occupation", Matchers.nullValue());


        System.out.println("Verify that patient new data is rejected");
    }

    @Category(ApiTest.class)
    @Test
    public void verifyMultipleUpdatedPatientApprovalApproved() throws JSONException, InterruptedException {

        primaryPatient= dataStore.defaultPatient;

        JSONObject person = createPatientDataJsonToPost(primaryPatient);
        given().contentType("Application/json")
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .body(person.toString())
                .when().post("/patients")
                .then().assertThat().statusCode(201);

        System.out.println("Patient created in MCI ");


        hid=(given().pathParam("bin_brn", primaryPatient.getBinBRN())
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .when().get("/patients?bin_brn={bin_brn}")
                .andReturn().print().substring(38,57));

        System.out.println(hid);
        updatedValue.put("given_name", "updated");
        updatedValue.put("gender", "O");
        System.out.println(updatedValue.get("gender"));

        given().contentType("Application/json")
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .pathParam("hid", hid)
                .body(updatedValue.toString())
                .when().put("/patients/{hid}")
                .then().assertThat().statusCode(202);

        System.out.println("Patient is mark for updatation");



        given().contentType("Application/json")
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .pathParam("hid", hid)
                .body(updatedValue.toString())
                .when().put(ApprovalURL)
                .then().assertThat().statusCode(202);

        System.out.println("Patient details is approved for update");

        given().pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .when().get("/patients/{hid}")
                .andReturn().body().print();

        given().pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .when().get("/patients/{hid}")
                .then()
                .body("given_name", Matchers.equalTo(updatedValue.get("given_name")))
                .body("gender", Matchers.equalTo(updatedValue.get("gender")));


        System.out.println("Verify that patient data is approved");
    }

    @Category(ApiTest.class)
    @Test
    public void verifyMultipleUpdatedPatientApprovalRejected() throws JSONException, InterruptedException {

        primaryPatient= dataStore.defaultPatient;

        JSONObject person = createPatientDataJsonToPost(primaryPatient);
        given().contentType("Application/json")
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .body(person.toString())
                .when().post("/patients")
                .then().assertThat().statusCode(201);

        System.out.println("Patient created in MCI ");


        hid=(given().pathParam("bin_brn", primaryPatient.getBinBRN())
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .when().get("/patients?bin_brn={bin_brn}")
                .andReturn().print().substring(38,57));

        System.out.println(hid);
        updatedValue.put("given_name", "updated");
        updatedValue.put("gender", "O");
        System.out.println(updatedValue.get("gender"));

        given().contentType("Application/json")
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .pathParam("hid", hid)
                .body(updatedValue.toString())
                .when().put("/patients/{hid}")
                .then().assertThat().statusCode(202);

        System.out.println("Patient is mark for updatation");



        given().contentType("Application/json")
                .pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .body(updatedValue.toString())
                .when().delete(ApprovalURL)
                .then().assertThat().statusCode(202);

        System.out.println("Patient details is rejected for update");

        given().pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .when().get("/patients/{hid}")
                .andReturn().body().print();

        given().pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .when().get("/patients/{hid}")
                .then()
                .body("given_name", Matchers.equalTo(primaryPatient.getGiven_name()))
                .body("gender", Matchers.equalTo(primaryPatient.getGender()));


        System.out.println("Verify that patient data is rejected");
    }

    @Category(ApiTest.class)
    @Test
    public void verifyMultiUpdatedPatientApprovalApproved() throws JSONException, InterruptedException {

        primaryPatient= dataStore.defaultPatient;

        JSONObject person = createPatientDataJsonToPost(primaryPatient);
        given().contentType("Application/json")
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .body(person.toString())
                .when().post("/patients")
                .then().assertThat().statusCode(201);

        System.out.println("Patient created in MCI ");


        hid=(given().pathParam("bin_brn", primaryPatient.getBinBRN())
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .when().get("/patients?bin_brn={bin_brn}")
                .andReturn().print().substring(38,57));

        System.out.println(hid);

        for(int i=1; i<=3; i++) {
            updatedValue.put("given_name", "Firstupdated"+i);

            given().contentType("Application/json")
                    .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                    .pathParam("hid", hid)
                    .body(updatedValue.toString())
                    .when().put("/patients/{hid}")
                    .then().assertThat().statusCode(202);
            System.out.println(updatedValue.get("given_name"));

        }


        System.out.println("Patient is mark for updatation");



        given().contentType("Application/json")
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .pathParam("hid", hid)
                .body(updatedValue.toString())
                .when().put(ApprovalURL)
                .then().assertThat().statusCode(202);

        System.out.println("Patient details is approved for update");

        given().pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .when().get("/patients/{hid}")
                .andReturn().body().print();

        given().pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .when().get("/patients/{hid}")
                .then()
                .body("given_name", Matchers.equalTo(updatedValue.get("given_name")));


        System.out.println("Verify that patient data is approved");
    }

    @Category(ApiTest.class)
    @Test
    public void verifyNewlyPatientBlockDataApproved() throws JSONException, InterruptedException {

        primaryPatient= dataStore.defaultPatient;

        JSONObject person = createPatientDataJsonToPost(primaryPatient);
        given().contentType("application/json")
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token)
                .body(person.toString())
                .when().post("/patients")
                .then().assertThat().statusCode(201);

        System.out.println("Patient created in MCI ");


        hid=(given().pathParam("bin_brn", primaryPatient.getBinBRN())
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .when().get("/patients?bin_brn={bin_brn}")
                .andReturn().print().substring(38,57));

        String beforeUpdate=given().pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .when().get("/patients/{hid}")
                .andReturn().body().print();

        System.out.println(hid);
        String number = String.valueOf(System.currentTimeMillis()).substring(7);
        updatedValue.put("country_code", "88");
        updatedValue.put("area_code", "02");
        updatedValue.put("number", number);
        updatedValue.put("extension", "3245");
        AddNewValue.put("phone_number", updatedValue);
        System.out.println(AddNewValue);

        given().contentType("Application/json")
                .pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .body(AddNewValue.toString())
                .when().put("/patients/{hid}")
                .then().assertThat().statusCode(202);

        System.out.println("Patient's new block of data is marked for updation");



        given().contentType("Application/json")
                .pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .body(AddNewValue.toString())
                .when().put(ApprovalURL)
                .then().assertThat().statusCode(202);

        System.out.println("Patient detail is approved for new block of data");

        given().pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .when().get("/patients/{hid}")
                .andReturn().body().print();

        String afterUpdateApproved=given().pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .when().get("/patients/{hid}")
                .andReturn().body().print();



        given().pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .when().get("/patients/{hid}")
                .then()
                .body("phone_number.country_code", Matchers.equalTo(updatedValue.get("country_code")))
                .body("phone_number.area_code", Matchers.equalTo(updatedValue.get("area_code")))
                .body("phone_number.number", Matchers.equalTo(updatedValue.get("number")))
                .body("phone_number.extension", Matchers.equalTo(updatedValue.get("extension")));

        Assert.assertFalse(beforeUpdate.equals(afterUpdateApproved));
        System.out.println("Verify that patient new block data is approved");
        System.out.println(beforeUpdate);
        System.out.println(afterUpdateApproved);
    }
    @Category(ApiTest.class)
    @Ignore
    public void verifyNewlyPatientBlockDataRejected() throws JSONException, InterruptedException {

        primaryPatient= dataStore.defaultPatient;

        JSONObject person = createPatientDataJsonToPost(primaryPatient);
        given().contentType("Application/json")
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .body(person.toString())
                .when().post("/patients")
                .then().assertThat().statusCode(201);

        System.out.println("Patient created in MCI ");

        hid=(given().pathParam("bin_brn", primaryPatient.getBinBRN())
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .when().get("/patients?bin_brn={bin_brn}")
                .andReturn().print().substring(38,57));

        String beforeUpdate=given().pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .when().get("/patients/{hid}")
                .andReturn().body().print();

        System.out.println(hid);
        String number = String.valueOf(System.currentTimeMillis()).substring(7);
        updatedValue.put("country_code", "88");
        updatedValue.put("area_code", "02");
        updatedValue.put("number", number);
        updatedValue.put("extension", "3245");
        AddNewValue.put("phone_number", updatedValue);
        System.out.println(AddNewValue);

        given().contentType("Application/json")
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .pathParam("hid", hid)
                .body(AddNewValue.toString())
                .when().put("/patients/{hid}")
                .then().assertThat().statusCode(202);

        System.out.println("Patient's new block of data is marked for updation");



        given().contentType("Application/json")
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .pathParam("hid", hid)
                .body(AddNewValue.toString())
                .when().delete(ApprovalURL)
                .then().assertThat().statusCode(202);

        System.out.println("Patient detail is rejected for updation");

        String afterUpdateRejected=given().pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .when().get("/patients/{hid}")
                .andReturn().body().print();

        System.out.println(beforeUpdate.equals(afterUpdateRejected));
        System.out.println(beforeUpdate);
        System.out.println(afterUpdateRejected);
        Assert.assertTrue(beforeUpdate.equals(afterUpdateRejected));

        System.out.println("Verify that patient new block data is not approved");
        System.out.println(beforeUpdate);
        System.out.println(afterUpdateRejected);
    }

    @Category(ApiTest.class)
    @Ignore
    @Test
    public void verifySearchAfterUpdatedPatientDataWithNameAddress() throws JSONException, InterruptedException {

        UpdatePatientTests updatePatientTests=new UpdatePatientTests();
        updatePatientTests.verifyUpdatedPatientApprovalApproved();

        given().pathParam("name", updatePatientTests.updatedValue.get("given_name"))
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .when().get("/patients?given_name={name}&present_address=100409")
                .then()
                .body("results.given_name[0]", Matchers.equalTo(updatePatientTests.updatedValue.get("given_name")))
                .body("results.present_address.address_line[0]", Matchers.equalTo("Test1"))
                .body("results.present_address.division_id[0]", Matchers.equalTo("10"))
                .body("results.present_address.district_id[0]", Matchers.equalTo("04"))
                .body("results.present_address.upazila_id[0]", Matchers.equalTo("09"));

        System.out.println("Verify that patient is searched with name & location");


    }
    @Category(ApiTest.class)
    @Ignore
    @Test
    public void verifySearchAfterBlockUpdateByPhoneNo() throws JSONException, InterruptedException {

        UpdatePatientTests updatePatientTests=new UpdatePatientTests();
        updatePatientTests.verifyNewlyPatientBlockDataApproved();

        given().pathParam("phoneNumber", updatePatientTests.updatedValue.get("number"))
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .when().get("/patients?phone_no={phoneNumber}")
                .print();
        given().pathParam("phoneNumber", updatePatientTests.updatedValue.get("number"))
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .when().get("/patients?phone_no={phoneNumber}")
                .then()
                .body("results.given_name[0]", Matchers.equalTo(updatePatientTests.primaryPatient.getGiven_name()))
                .body("results.present_address.address_line[0]", Matchers.equalTo("Test1"))
                .body("results.present_address.division_id[0]", Matchers.equalTo("10"))
                .body("results.present_address.district_id[0]", Matchers.equalTo("04"))
                .body("results.present_address.upazila_id[0]", Matchers.equalTo("09"))
                .body("results.phone_number.number[0]", Matchers.equalTo(updatePatientTests.updatedValue.get("number")));

        System.out.println("Verify that patient is searched with phone_no");

    }


}
