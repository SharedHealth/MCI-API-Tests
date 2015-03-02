package data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.RestAssuredConfig;
import com.jayway.restassured.response.Response;
import domain.Patient;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import utils.WebDriverProperties;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.config.EncoderConfig.encoderConfig;


public class PatientDataSetUp {

    public String token;
    protected ObjectMapper mapper = new ObjectMapper();
    @Before
    public void invokeApp() throws JSONException {

        token = WebDriverProperties.getToken();
        RestAssured.baseURI = WebDriverProperties.getProperty("MCI_API_BASE_URI");
        RestAssured.port = Integer.parseInt(WebDriverProperties.getProperty("MCI_API_PORT"));
        RestAssured.basePath = WebDriverProperties.getProperty("MCI_API_BASE_PATH");
        RestAssured.rootPath = "";
        RestAssured.config = new RestAssuredConfig().encoderConfig(encoderConfig().defaultContentCharset("UTF-8"));
    }

    protected Patient getPatientObjectFromString(String json) throws Exception {
        return mapper.readValue(json, Patient.class);
    }

    protected String getJsonFromObject(Patient patient) throws Exception {
        return mapper.writeValueAsString(patient);
    }

    protected String createPatient(String json) throws JSONException {

        Response response = given().contentType("application/json")
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .body(json)
                .when().post("/patients")
                .andReturn();

        JSONObject jsonObject = new JSONObject(response.getBody().asString());

        return jsonObject.get("id").toString();
    }

    protected void updatePatient(String json, String hid) {

        given().contentType("Application/json")
                .pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"),token.trim())
                .body(json)
                .when().put("/patients/{hid}")
                .then().assertThat().statusCode(202);
    }



    @After
    public void tearDown() {
        RestAssured.reset();
    }
}
