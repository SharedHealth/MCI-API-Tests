package tests.api;

import categories.ApiTest;
import data.PatientDataSetUp;
import data.PatientDataStore;
import domain.Patient;
import org.hamcrest.Matchers;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import utils.WebDriverProperties;


import static com.jayway.restassured.RestAssured.given;


public class PatientApprovalTests extends PatientDataSetUp {

    String ApprovalURL="/catchments/100409/approvals/{hid}/";
    protected Patient primaryPatient;
    PatientDataStore dataStore = new PatientDataStore();
    JSONObject updatedValue= new JSONObject();
    JSONObject AddNewValue= new JSONObject();

    @Category(ApiTest.class)
    @Test
    public void verifyGivenNameUpdateUsingPatientApprovalAcceptProcess() throws JSONException, InterruptedException {

        primaryPatient= dataStore.defaultPatient;

        JSONObject patient = createPatientDataJsonToPost(primaryPatient);
        String hid = createPatient(patient);
        updatedValue.put("given_name", "updated");
        updatePatient(updatedValue, hid);

        given().contentType("Application/json")
                .pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .body(updatedValue.toString())
                .when().put(ApprovalURL)
                .then().assertThat().statusCode(202);

        given().pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .when().get("/patients/{hid}")
                .then()
                .body("given_name", Matchers.equalTo(updatedValue.get("given_name")));

    }

    @Category(ApiTest.class)
    @Test
    public void verifyGivenNameUpdateUsingPatientApprovalRejectProcess() throws JSONException, InterruptedException {

        primaryPatient= dataStore.defaultPatient;

        JSONObject patient = createPatientDataJsonToPost(primaryPatient);
        String hid = createPatient(patient);
        updatedValue.put("given_name", "updated");
        updatePatient(updatedValue, hid);

        given().contentType("Application/json")
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .pathParam("hid", hid)
                .body(updatedValue.toString())
                .when().delete(ApprovalURL)
                .then().assertThat().statusCode(202);

        given().pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .when().get("/patients/{hid}")
                .then()
                .body("given_name", Matchers.equalTo(primaryPatient.getGiven_name()));

    }

    @Category(ApiTest.class)
    @Test
    public void verifyOccupationUpdateUsingPatientApprovalAcceptProcess() throws JSONException, InterruptedException {

        primaryPatient= dataStore.defaultPatient;

        JSONObject patient = createPatientDataJsonToPost(primaryPatient);
        String hid = createPatient(patient);

        AddNewValue.put("occupation", "02");
        updatePatient(AddNewValue, hid);

        given().contentType("application/json")
                .pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .body(AddNewValue.toString())
                .when().put(ApprovalURL)
                .then().assertThat().statusCode(202);


        given().pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .when().get("/patients/{hid}")
                .then()
                .body("occupation", Matchers.equalTo(AddNewValue.get("occupation")));


    }

    @Category(ApiTest.class)
    @Test
    public void verifyOccupationUpdateUsingPatientApprovalRejectProcess() throws JSONException, InterruptedException {

        primaryPatient= dataStore.defaultPatient;

        JSONObject patient = createPatientDataJsonToPost(primaryPatient);
        String hid = createPatient(patient);

        AddNewValue.put("occupation", "02");
        updatePatient(AddNewValue, hid);


        given().contentType("Application/json")
                .pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .body(AddNewValue.toString())
                .when().delete(ApprovalURL)
                .then().assertThat().statusCode(202);

        given().pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .when().get("/patients/{hid}")
                .then()
                .body("occupation", Matchers.nullValue());


        System.out.println("Verify that patient new data is rejected");
    }

    @Category(ApiTest.class)
    @Test
    public void verifyMultipleFieldUpdateUsingPatientApprovalAcceptProcess() throws JSONException, InterruptedException {

        primaryPatient= dataStore.defaultPatient;

        JSONObject patient = createPatientDataJsonToPost(primaryPatient);
        String hid = createPatient(patient);

        updatedValue.put("given_name", "updated");
        updatedValue.put("gender", "O");
        updatePatient(updatedValue, hid);

        given().contentType("application/json")
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .pathParam("hid", hid)
                .body(updatedValue.toString())
                .when().put(ApprovalURL)
                .then().assertThat().statusCode(202);

        given().pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .when().get("/patients/{hid}")
                .then()
                .body("given_name", Matchers.equalTo(updatedValue.get("given_name")))
                .body("gender", Matchers.equalTo(updatedValue.get("gender")));


    }

    @Category(ApiTest.class)
    @Test
    public void verifyMultipleFieldUpdateUsingPatientApprovalRejectProcess() throws JSONException, InterruptedException {

        primaryPatient= dataStore.defaultPatient;

        JSONObject patient = createPatientDataJsonToPost(primaryPatient);
        String hid = createPatient(patient);

        updatedValue.put("given_name", "updated");
        updatedValue.put("gender", "O");
        updatePatient(updatedValue, hid);

        given().contentType("Application/json")
                .pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .body(updatedValue.toString())
                .when().delete(ApprovalURL)
                .then().assertThat().statusCode(202);

        given().pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .when().get("/patients/{hid}")
                .then()
                .body("given_name", Matchers.equalTo(primaryPatient.getGiven_name()))
                .body("gender", Matchers.equalTo(primaryPatient.getGender()));


        System.out.println("Verify that patient data is rejected");
    }

    @Category(ApiTest.class)
    @Test
    public void verifyMultiplePatientUpdateWithSameFieldUsingPatientApprovalAcceptProcess() throws JSONException, InterruptedException {

        primaryPatient= dataStore.defaultPatient;

        JSONObject patient = createPatientDataJsonToPost(primaryPatient);
        String hid = createPatient(patient);

        for(int i=1; i<=3; i++) {
            updatedValue.put("given_name", "Firstupdated"+i);
            updatePatient(updatedValue, hid);
        }

        given().contentType("Application/json")
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .pathParam("hid", hid)
                .body(updatedValue.toString())
                .when().put(ApprovalURL)
                .then().assertThat().statusCode(202);

        given().pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .when().get("/patients/{hid}")
                .then()
                .body("given_name", Matchers.equalTo(updatedValue.get("given_name")));

        System.out.println("Verify that patient data is approved");
    }

    @Category(ApiTest.class)
    @Test
    public void verifyPhoneBlockDataUpdateUsingPatientApprovalAcceptProcess() throws JSONException, InterruptedException {

        primaryPatient= dataStore.defaultPatient;

        JSONObject patient = createPatientDataJsonToPost(primaryPatient);
        String hid = createPatient(patient);

        String beforeUpdate=given().pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .when().get("/patients/{hid}")
                .andReturn().body().print();

        String number = String.valueOf(System.currentTimeMillis()).substring(7);
        updatedValue.put("country_code", "88");
        updatedValue.put("area_code", "02");
        updatedValue.put("number", number);
        updatedValue.put("extension", "3245");
        AddNewValue.put("phone_number", updatedValue);

        updatePatient(AddNewValue, hid);
        given().contentType("application/json")
                .pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .body(AddNewValue.toString())
                .when().put(ApprovalURL)
                .then().assertThat().statusCode(202);


        String afterUpdateApproved=given().pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .when().get("/patients/{hid}")
                .andReturn().body().print();

        given().pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .when().get("/patients/{hid}")
                .then()
                .body("phone_number.country_code", Matchers.equalTo(updatedValue.get("country_code")))
                .body("phone_number.area_code", Matchers.equalTo(updatedValue.get("area_code")))
                .body("phone_number.number", Matchers.equalTo(updatedValue.get("number")))
                .body("phone_number.extension", Matchers.equalTo(updatedValue.get("extension")));

        Assert.assertFalse(beforeUpdate.equals(afterUpdateApproved));
    }

    @Category(ApiTest.class)
    @Test(expected = JSONException.class)
    public void verifyPhoneBlockDataUpdateUsingPatientApprovalRejectProcess() throws JSONException, InterruptedException {

        primaryPatient= dataStore.defaultPatient;


        JSONObject patient = createPatientDataJsonToPost(primaryPatient);
        String hid = createPatient(patient);

        String beforeUpdate=given().pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .when().get("/patients/{hid}")
                .andReturn().body().print();
        System.out.println("beforeUpdate:" + beforeUpdate);
        System.out.println(hid);
        String number = String.valueOf(System.currentTimeMillis()).substring(7);
        updatedValue.put("country_code", "88");
        updatedValue.put("area_code", "02");
        updatedValue.put("number", number);
        updatedValue.put("extension", "3245");
        AddNewValue.put("phone_number", updatedValue);

        updatePatient(AddNewValue, hid);
        given().contentType("application/json")
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .pathParam("hid", hid)
                .body(AddNewValue.toString())
                .when().delete(ApprovalURL)
                .then().assertThat().statusCode(202);


        String afterUpdateRejected=given().pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .when().get("/patients/{hid}")
                .andReturn().body().print();
        JSONObject updatedPatient = new JSONObject(afterUpdateRejected);
        updatedPatient.get("phone_number");

    }

    @Category(ApiTest.class)
    @Test
    public void verifySearchAfterUpdatedPatientDataWithNameAddress() throws JSONException, InterruptedException {

        primaryPatient= dataStore.defaultPatient;


        JSONObject patient = createPatientDataJsonToPost(primaryPatient);
        String hid = createPatient(patient);

        updatedValue.put("given_name", "updated");
        updatePatient(updatedValue, hid);

        given().pathParam("name", updatedValue.get("given_name"))
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .when().get("/patients?given_name={name}&present_address=100409")
                .then()
                .body("results.given_name[0]", Matchers.equalTo(updatedValue.get("given_name")))
                .body("results.present_address.address_line[0]", Matchers.equalTo("Test1"))
                .body("results.present_address.division_id[0]", Matchers.equalTo("10"))
                .body("results.present_address.district_id[0]", Matchers.equalTo("04"))
                .body("results.present_address.upazila_id[0]", Matchers.equalTo("09"));

        System.out.println("Verify that patient is searched with name & location");


    }
    @Category(ApiTest.class)
    @Test
    public void verifySearchAfterBlockUpdateByPhoneNo() throws JSONException, InterruptedException {

        primaryPatient= dataStore.defaultPatient;
        JSONObject patient = createPatientDataJsonToPost(primaryPatient);
        String hid = createPatient(patient);

        String number = String.valueOf(System.currentTimeMillis()).substring(7);
        updatedValue.put("country_code", "88");
        updatedValue.put("area_code", "02");
        updatedValue.put("number", number);
        updatedValue.put("extension", "3245");
        AddNewValue.put("phone_number", updatedValue);

        updatePatient(AddNewValue, hid);

        given().contentType("application/json")
                .pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .body(AddNewValue.toString())
                .when().put(ApprovalURL)
                .then().assertThat().statusCode(202);

        given().pathParam("phoneNumber", updatedValue.get("number"))
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .when().get("/patients?phone_no={phoneNumber}")
                .print();

        given().pathParam("phoneNumber", updatedValue.get("number"))
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .when().get("/patients?phone_no={phoneNumber}")
                .then()
                .body("results.given_name[0]", Matchers.equalTo(primaryPatient.getGiven_name()))
                .body("results.present_address.address_line[0]", Matchers.equalTo("Test1"))
                .body("results.present_address.division_id[0]", Matchers.equalTo("10"))
                .body("results.present_address.district_id[0]", Matchers.equalTo("04"))
                .body("results.present_address.upazila_id[0]", Matchers.equalTo("09"))
                .body("results.phone_number.number[0]", Matchers.equalTo(updatedValue.get("number")));

        System.out.println("Verify that patient is searched with phone_no");

    }


}
