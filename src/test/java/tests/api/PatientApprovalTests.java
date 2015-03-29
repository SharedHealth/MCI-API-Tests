package tests.api;

import categories.ApiTest;
import data.PatientDataSetUp;
import domain.Patient;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import utils.WebDriverProperties;

import static com.jayway.restassured.RestAssured.given;
import static utils.FileUtil.asString;


public class PatientApprovalTests extends PatientDataSetUp {

    String ApprovalURL = "/catchments/302602/approvals/{hid}/";
    JSONObject updatedValue = new JSONObject();
    JSONObject AddNewValue = new JSONObject();

    @Category(ApiTest.class)
    @Test
    public void verifyGivenNameUpdateUsingPatientApprovalAcceptProcess() throws Exception {

        String json = asString("jsons/patient/full_payload_without_ids.json");
        String hid = createPatient(json);

        updatedValue.put("given_name", "updated");
        updatePatient(updatedValue.toString(), hid);
        System.out.println("hid:" + hid);
        System.out.println(updatedValue.toString());
        given().contentType("Application/json")
                .pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .header(CLIENT_ID, userId.trim())
                .header(FROM, email.trim())
                .body(updatedValue.toString())
                .when().put(ApprovalURL)
                .then().assertThat().statusCode(202);

        given().pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .header(CLIENT_ID, userId.trim())
                .header(FROM, email.trim())
                .when().get("/patients/{hid}")
                .then()
                .body("given_name", Matchers.equalTo(updatedValue.get("given_name")));

    }

    @Category(ApiTest.class)
    @Test
    public void verifyGivenNameUpdateUsingPatientApprovalRejectProcess() throws Exception {

        String json = asString("jsons/patient/full_payload_without_ids.json");
        String hid = createPatient(json);
        Patient patient = getPatientObjectFromString(json);

        updatedValue.put("given_name", "updated");
        updatePatient(updatedValue.toString(), hid);

        given().contentType("Application/json")
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .header(CLIENT_ID, userId.trim())
                .header(FROM, email.trim())
                .pathParam("hid", hid)
                .body(updatedValue.toString())
                .when().delete(ApprovalURL)
                .then().assertThat().statusCode(202);

        given().pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .header(CLIENT_ID, userId.trim())
                .header(FROM, email.trim())
                .when().get("/patients/{hid}")
                .then()
                .body("given_name", Matchers.equalTo(patient.getGivenName()));

    }

    @Category(ApiTest.class)
    @Test
    public void verifyOccupationUpdateUsingPatientApprovalAcceptProcess() throws Exception {

        String json = asString("jsons/patient/full_payload_without_ids.json");
        String hid = createPatient(json);

        AddNewValue.put("occupation", "03");
        updatePatient(AddNewValue.toString(), hid);

        given().contentType("application/json")
                .pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .header(CLIENT_ID, userId.trim())
                .header(FROM, email.trim())
                .body(AddNewValue.toString())
                .when().put(ApprovalURL)
                .then().assertThat().statusCode(202);


        given().pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .header(CLIENT_ID, userId.trim())
                .header(FROM, email.trim())
                .when().get("/patients/{hid}")
                .then()
                .body("occupation", Matchers.equalTo(AddNewValue.get("occupation")));


    }

    @Category(ApiTest.class)
    @Test
    public void verifyOccupationUpdateUsingPatientApprovalRejectProcess() throws Exception {

        String json = asString("jsons/patient/full_payload_without_ids.json");
        String hid = createPatient(json);
        Patient patient = getPatientObjectFromString(json);

        AddNewValue.put("occupation", "03");
        updatePatient(AddNewValue.toString(), hid);


        given().contentType("Application/json")
                .pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .header(CLIENT_ID, userId.trim())
                .header(FROM, email.trim())
                .body(AddNewValue.toString())
                .when().delete(ApprovalURL)
                .then().assertThat().statusCode(202);

        given().pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .header(CLIENT_ID, userId.trim())
                .header(FROM, email.trim())
                .when().get("/patients/{hid}")
                .then()
                .body("occupation", Matchers.equalTo(patient.getOccupation()));


        System.out.println("Verify that patient new data is rejected");
    }

    @Category(ApiTest.class)
    @Test
    public void verifyMultipleFieldUpdateUsingPatientApprovalAcceptProcess() throws Exception {

        String json = asString("jsons/patient/full_payload_without_ids.json");
        String hid = createPatient(json);

        updatedValue.put("given_name", "updated");
        updatedValue.put("gender", "O");
        updatePatient(updatedValue.toString(), hid);

        given().contentType("application/json")
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .header(CLIENT_ID, userId.trim())
                .header(FROM, email.trim())
                .pathParam("hid", hid)
                .body(updatedValue.toString())
                .when().put(ApprovalURL)
                .then().assertThat().statusCode(202);

        given().pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .header(CLIENT_ID, userId.trim())
                .header(FROM, email.trim())
                .when().get("/patients/{hid}")
                .then()
                .body("given_name", Matchers.equalTo(updatedValue.get("given_name")))
                .body("gender", Matchers.equalTo(updatedValue.get("gender")));


    }

    @Category(ApiTest.class)
    @Test
    public void verifyMultipleFieldUpdateUsingPatientApprovalRejectProcess() throws Exception {

        String json = asString("jsons/patient/full_payload_without_ids.json");
        String hid = createPatient(json);
        Patient patient = getPatientObjectFromString(json);

        updatedValue.put("given_name", "updated");
        updatedValue.put("gender", "O");
        updatePatient(updatedValue.toString(), hid);

        given().contentType("Application/json")
                .pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .header(CLIENT_ID, userId.trim())
                .header(FROM, email.trim())
                .body(updatedValue.toString())
                .when().delete(ApprovalURL)
                .then().assertThat().statusCode(202);

        given().pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .header(CLIENT_ID, userId.trim())
                .header(FROM, email.trim())
                .when().get("/patients/{hid}")
                .then()
                .body("given_name", Matchers.equalTo(patient.getGivenName()))
                .body("gender", Matchers.equalTo(patient.getGender()));


        System.out.println("Verify that patient data is rejected");
    }

    @Category(ApiTest.class)
    @Test
    public void verifyMultiplePatientUpdateWithSameFieldUsingPatientApprovalAcceptProcess() throws Exception {

        String json = asString("jsons/patient/full_payload_without_ids.json");
        String hid = createPatient(json);
        Patient patient = getPatientObjectFromString(json);

        for (int i = 1; i <= 3; i++) {
            updatedValue.put("given_name", "Firstupdated" + i);
            updatePatient(updatedValue.toString(), hid);
        }

        given().contentType("Application/json")
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .header(CLIENT_ID, userId.trim())
                .header(FROM, email.trim())
                .pathParam("hid", hid)
                .body(updatedValue.toString())
                .when().put(ApprovalURL)
                .then().assertThat().statusCode(202);

        given().pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .header(CLIENT_ID, userId.trim())
                .header(FROM, email.trim())
                .when().get("/patients/{hid}")
                .then()
                .body("given_name", Matchers.equalTo(updatedValue.get("given_name")));

        System.out.println("Verify that patient data is approved");
    }

    @Category(ApiTest.class)
    @Test
    public void verifyPhoneBlockDataUpdateUsingPatientApprovalAcceptProcess() throws Exception {

        String json = asString("jsons/patient/full_payload_without_ids.json");
        String hid = createPatient(json);

        String beforeUpdate = given().pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .header(CLIENT_ID, userId.trim())
                .header(FROM, email.trim())
                .when().get("/patients/{hid}")
                .andReturn().body().print();

        String number = String.valueOf(System.currentTimeMillis()).substring(7);
        updatedValue.put("country_code", "050");
        updatedValue.put("area_code", "02");
        updatedValue.put("number", number);
        updatedValue.put("extension", "3245");
        AddNewValue.put("phone_number", updatedValue);

        updatePatient(AddNewValue.toString(), hid);
        given().contentType("application/json")
                .pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .header(CLIENT_ID, userId.trim())
                .header(FROM, email.trim())
                .body(AddNewValue.toString())
                .when().put(ApprovalURL)
                .then().assertThat().statusCode(202);


        String afterUpdateApproved = given().pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .header(CLIENT_ID, userId.trim())
                .header(FROM, email.trim())
                .when().get("/patients/{hid}")
                .andReturn().body().print();

        given().pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .header(CLIENT_ID, userId.trim())
                .header(FROM, email.trim())
                .when().get("/patients/{hid}")
                .then()
                .body("phone_number.country_code", Matchers.equalTo(updatedValue.get("country_code")))
                .body("phone_number.area_code", Matchers.equalTo(updatedValue.get("area_code")))
                .body("phone_number.number", Matchers.equalTo(updatedValue.get("number")))
                .body("phone_number.extension", Matchers.equalTo(updatedValue.get("extension")));

        Assert.assertFalse(beforeUpdate.equals(afterUpdateApproved));
    }

    @Category(ApiTest.class)
    @Test
    public void verifyPhoneBlockDataUpdateUsingPatientApprovalRejectProcess() throws Exception {

        String json = asString("jsons/patient/full_payload_without_ids.json");
        String hid = createPatient(json);
        Patient patient = getPatientObjectFromString(json);

        String beforeUpdate = given().pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .header(CLIENT_ID, userId.trim())
                .header(FROM, email.trim())
                .when().get("/patients/{hid}")
                .andReturn().body().print();
        System.out.println("beforeUpdate:" + beforeUpdate);
        System.out.println(hid);
        String number = String.valueOf(System.currentTimeMillis()).substring(7);
        updatedValue.put("country_code", "050");
        updatedValue.put("area_code", "02");
        updatedValue.put("number", number);
        updatedValue.put("extension", "3245");
        AddNewValue.put("phone_number", updatedValue);

        updatePatient(AddNewValue.toString(), hid);
        given().contentType("application/json")
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .header(CLIENT_ID, userId.trim())
                .header(FROM, email.trim())
                .pathParam("hid", hid)
                .body(AddNewValue.toString())
                .when().delete(ApprovalURL)
                .then().assertThat().statusCode(202);


        given().pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .header(CLIENT_ID, userId.trim())
                .header(FROM, email.trim())
                .when().get("/patients/{hid}")
                .then()
                .body("phone_number.country_code", Matchers.equalTo(patient.getPhoneNumber().getCountryCode()))
                .body("phone_number.area_code", Matchers.equalTo(patient.getPhoneNumber().getAreaCode()))
                .body("phone_number.number", Matchers.equalTo(patient.getPhoneNumber().getNumber()))
                .body("phone_number.extension", Matchers.equalTo(patient.getPhoneNumber().getExtension()));

    }


    @Category(ApiTest.class)
    @Test
    public void verifySearchAfterBlockUpdateByPhoneNo() throws Exception {

        String json = asString("jsons/patient/full_payload_without_ids.json");
        String hid = createPatient(json);
        Patient patient = getPatientObjectFromString(json);

        String number = String.valueOf(System.currentTimeMillis()).substring(7);
        updatedValue.put("country_code", "050");
        updatedValue.put("area_code", "02");
        updatedValue.put("number", number);
        updatedValue.put("extension", "3245");
        AddNewValue.put("phone_number", updatedValue);

        updatePatient(AddNewValue.toString(), hid);

        given().contentType("application/json")
                .pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .header(CLIENT_ID, userId.trim())
                .header(FROM, email.trim())
                .body(AddNewValue.toString())
                .when().put(ApprovalURL)
                .then().assertThat().statusCode(202);

        given().pathParam("phoneNumber", updatedValue.get("number"))
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .header(CLIENT_ID, userId.trim())
                .header(FROM, email.trim())
                .when().get("/patients?phone_no={phoneNumber}")
                .print();

        given().pathParam("phoneNumber", updatedValue.get("number"))
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .header(CLIENT_ID, userId.trim())
                .header(FROM, email.trim())
                .when().get("/patients?phone_no={phoneNumber}")
                .then()
                .body("results[0].given_name", Matchers.equalTo(patient.getGivenName()))
                .body("results[0].present_address.address_line", Matchers.equalTo(patient.getAddress().getAddressLine()))
                .body("results[0].present_address.division_id", Matchers.equalTo(patient.getAddress().getDivisionId()))
                .body("results[0].present_address.district_id", Matchers.equalTo(patient.getAddress().getDistrictId()))
                .body("results[0].present_address.upazila_id", Matchers.equalTo(patient.getAddress().getUpazilaId()))
                .body("results[0].phone_number.number", Matchers.equalTo(updatedValue.get("number")));

        System.out.println("Verify that patient is searched with phone_no");

    }

    @Category(ApiTest.class)
    @Test
    public void verifyPrimaryContactFieldUpdateUsingPatientApprovalAcceptProcess() throws Exception {

        String json = asString("jsons/patient/full_payload_without_ids.json");
        String hid = createPatient(json);

        String number = String.valueOf(System.currentTimeMillis()).substring(7);
        updatedValue.put("country_code", "050");
        updatedValue.put("area_code", "02");
        updatedValue.put("number", number);
        updatedValue.put("extension", "3245");
        AddNewValue.put("primary_contact_number", updatedValue);

        updatePatient(AddNewValue.toString(), hid);

        given().contentType("application/json")
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .header(CLIENT_ID, userId.trim())
                .header(FROM, email.trim())
                .pathParam("hid", hid)
                .body(AddNewValue.toString())
                .when().put(ApprovalURL)
                .then().assertThat().statusCode(202);

        given().pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .header(CLIENT_ID, userId.trim())
                .header(FROM, email.trim())
                .when().get("/patients/{hid}")
                .then()
                .body("primary_contact_number.country_code", Matchers.equalTo(updatedValue.get("country_code")))
                .body("primary_contact_number.area_code", Matchers.equalTo(updatedValue.get("area_code")))
                .body("primary_contact_number.number", Matchers.equalTo(updatedValue.get("number")))
                .body("primary_contact_number.extension", Matchers.equalTo(updatedValue.get("extension")));


    }

    @Category(ApiTest.class)
    @Test
    public void verifyStatusBlockFieldUpdateUsingPatientApprovalAcceptProcess() throws Exception {

        String json = asString("jsons/patient/full_payload_without_ids.json");
        String hid = createPatient(json);

        String number = String.valueOf(System.currentTimeMillis()).substring(7);
        updatedValue.put("country_code", "050");
        updatedValue.put("area_code", "02");
        updatedValue.put("number", number);
        updatedValue.put("extension", "3245");
        AddNewValue.put("primary_contact_number", updatedValue);

        updatePatient(AddNewValue.toString(), hid);

        given().contentType("application/json")
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .header(CLIENT_ID, userId.trim())
                .header(FROM, email.trim())
                .pathParam("hid", hid)
                .body(AddNewValue.toString())
                .when().put(ApprovalURL)
                .then().assertThat().statusCode(202);

        given().pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .header(CLIENT_ID, userId.trim())
                .header(FROM, email.trim())
                .when().get("/patients/{hid}")
                .then()
                .body("primary_contact_number.country_code", Matchers.equalTo(updatedValue.get("country_code")))
                .body("primary_contact_number.area_code", Matchers.equalTo(updatedValue.get("area_code")))
                .body("primary_contact_number.number", Matchers.equalTo(updatedValue.get("number")))
                .body("primary_contact_number.extension", Matchers.equalTo(updatedValue.get("extension")));


    }


}
