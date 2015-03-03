package tests.api;

import categories.ApiTest;
import data.PatientDataSetUp;
import domain.Patient;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import utils.WebDriverProperties;

import static com.jayway.restassured.RestAssured.given;
import static utils.FileUtil.asString;


public class SearchPatientTests extends PatientDataSetUp {

    String ApprovalURL="/catchments/557364/approvals/{hid}/";
    JSONObject updatedValue= new JSONObject();
    JSONObject AddNewValue= new JSONObject();

    @Category(ApiTest.class)
    @Test
    public void verifySearchAfterUpdatedPatientDataUsingApprovalProcess() throws Exception {

        String json = asString("jsons/patient/full_payload_without_ids.json");
        String hid = createPatient(json);
        Patient patient = getPatientObjectFromString(json);

        updatedValue.put("given_name", "updated");
        updatePatient(updatedValue.toString(), hid);

        given().pathParam("name", patient.getGivenName())
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .when().get("/patients?given_name={name}&present_address=557364")
                .then()
                .body("results[0].given_name", Matchers.equalTo(patient.getGivenName()))
                .body("results[0].present_address.address_line", Matchers.equalTo(patient.getAddress().getAddressLine()))
                .body("results[0].present_address.division_id", Matchers.equalTo(patient.getAddress().getDivisionId()))
                .body("results[0].present_address.district_id", Matchers.equalTo(patient.getAddress().getDistrictId()))
                .body("results[0].present_address.upazila_id", Matchers.equalTo(patient.getAddress().getUpazilaId()));

        System.out.println("Verify that patient is searched with name & location");


    }
    @Category(ApiTest.class)
    @Test
    public void verifySearchAfterPhoneNoBlockUpdateUsingApprovalProcess() throws Exception {

        String json = asString("jsons/patient/full_payload_without_ids.json");
        String hid = createPatient(json);
        Patient patient = getPatientObjectFromString(json);

        String number = String.valueOf(System.currentTimeMillis()).substring(7);
        updatedValue.put("country_code", "88");
        updatedValue.put("area_code", "02");
        updatedValue.put("number", number);
        updatedValue.put("extension", "3245");
        AddNewValue.put("phone_number", updatedValue);

        updatePatient(AddNewValue.toString(), hid);

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
                .body("results[0].given_name", Matchers.equalTo(patient.getGivenName()))
                .body("results[0].present_address.address_line", Matchers.equalTo(patient.getAddress().getAddressLine()))
                .body("results[0].present_address.division_id", Matchers.equalTo(patient.getAddress().getDivisionId()))
                .body("results[0].present_address.district_id", Matchers.equalTo(patient.getAddress().getDistrictId()))
                .body("results[0].present_address.upazila_id", Matchers.equalTo(patient.getAddress().getUpazilaId()))
                .body("results[0].phone_number.number", Matchers.equalTo(updatedValue.get("number")));

        System.out.println("Verify that patient is searched with phone_no");

    }

    @Test
    public void verifySearchUsingNationalID() throws Exception {

        String json = asString("jsons/patient/full_payload.json");
        createPatient(json);
        Patient patient = getPatientObjectFromString(json);

        given().pathParam("nid", "1234567890123")
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .when().get("/patients?nid={nid}")
                .then()
                .body("results[0].given_name", Matchers.equalTo(patient.getGivenName()))
                .body("results[0].phone_number.number", Matchers.equalTo(patient.getPhoneNumber().getNumber()));

    }

    @Test
    public void verifySearchUsingBirthRegistrationNumber() throws Exception {

        String json = asString("jsons/patient/full_payload.json");
        createPatient(json);
        Patient patient = getPatientObjectFromString(json);

        given().pathParam("bin_brn", "12345678901234567")
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .when().get("/patients?bin_brn={bin_brn}")
                .then()
                .body("results[0].given_name", Matchers.equalTo(patient.getGivenName()))
                .body("results[0].phone_number.number", Matchers.equalTo(patient.getPhoneNumber().getNumber()));

    }

    @Test
    public void verifySearchUsingUserId() throws Exception {

        String json = asString("jsons/patient/full_payload.json");
        createPatient(json);
        Patient patient = getPatientObjectFromString(json);

        given().pathParam("uid", "11111111111")
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .when().get("/patients?uid={uid}")
                .then()
                .body("results[0].given_name", Matchers.equalTo(patient.getGivenName()))
                .body("results[0].phone_number.number", Matchers.equalTo(patient.getPhoneNumber().getNumber()));

    }

    @Test
    public void verifySearchUsingPresentAddressAndGivenName() throws Exception {

        String json = asString("jsons/patient/full_payload.json");
        createPatient(json);
        Patient patient = getPatientObjectFromString(json);

        given().pathParam("present_address", "557364")
                .pathParam("given_name", patient.getGivenName())
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .when().get("/patients?present_address={present_address}&given_name={given_name}")
                .then()
                .body("results[0].present_address.division_id", Matchers.equalTo(patient.getAddress().getDivisionId()))
                .body("results[0].present_address.district_id", Matchers.equalTo(patient.getAddress().getDistrictId()))
                .body("results[0].present_address.upazila_id", Matchers.equalTo(patient.getAddress().getUpazilaId()))
                .body("results[0].given_name", Matchers.equalTo(patient.getGivenName()))
                .body("results[0].phone_number.number", Matchers.equalTo(patient.getPhoneNumber().getNumber()));

    }

    @Test
    public void verifySearchUsingPresentAddressAndGivenNameAndSurName() throws Exception {

        String json = asString("jsons/patient/full_payload.json");
        createPatient(json);
        Patient patient = getPatientObjectFromString(json);

        given().pathParam("present_address", "557364")
                .pathParam("given_name", patient.getGivenName())
                .pathParam("sur_name", patient.getSurName())
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .when().get("/patients?present_address={present_address}&given_name={given_name}&sur_name={sur_name}")
                .then()
                .body("results[0].present_address.division_id", Matchers.equalTo(patient.getAddress().getDivisionId()))
                .body("results[0].present_address.district_id", Matchers.equalTo(patient.getAddress().getDistrictId()))
                .body("results[0].present_address.upazila_id", Matchers.equalTo(patient.getAddress().getUpazilaId()))
                .body("results[0].given_name", Matchers.equalTo(patient.getGivenName()))
                .body("results[0].phone_number.number", Matchers.equalTo(patient.getPhoneNumber().getNumber()));

    }

    @Test
    public void verifySearchUsingPhoneNo() throws Exception {

        String json = asString("jsons/patient/full_payload.json");
        createPatient(json);
        Patient patient = getPatientObjectFromString(json);

        given().pathParam("phone_no", patient.getPhoneNumber().getNumber())
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .when().get("/patients?phone_no={phone_no}")
                .then()
                .body("results[0].present_address.division_id", Matchers.equalTo(patient.getAddress().getDivisionId()))
                .body("results[0].present_address.district_id", Matchers.equalTo(patient.getAddress().getDistrictId()))
                .body("results[0].present_address.upazila_id", Matchers.equalTo(patient.getAddress().getUpazilaId()))
                .body("results[0].given_name", Matchers.equalTo(patient.getGivenName()))
                .body("results[0].phone_number.number", Matchers.equalTo(patient.getPhoneNumber().getNumber()));

    }
}
