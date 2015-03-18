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

    public static final String CLIENT_ID = "client_id";
    public static final String FROM = "from";
    public String token;
    public String userId;
    public String email;
    protected ObjectMapper mapper = new ObjectMapper();
    @Before
    public void invokeApp() throws JSONException {

        token = WebDriverProperties.getToken();
        userId = WebDriverProperties.getUserId(token);
        email = WebDriverProperties.getProperty("MCI_IDENTITY_EMAIL");
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
                .header(CLIENT_ID, userId.trim())
                .header(FROM, email.trim())
                .body(json)
                .when().post("/patients")
                .andReturn();
        System.out.println(response.getBody().asString());
        JSONObject jsonObject = new JSONObject(response.getBody().asString());

        return jsonObject.get("id").toString();
    }

    protected void updatePatient(String json, String hid) {

        given().contentType("Application/json")
                .pathParam("hid", hid)
                .header(WebDriverProperties.getProperty("MCI_API_TOKEN_NAME"), token.trim())
                .header(CLIENT_ID, userId.trim())
                .header("from", email.trim())
                .body(json)
                .when().put("/patients/{hid}")
                .then().assertThat().statusCode(202);
    }

    protected String getRandomNID()
    {
        return "9000000" + String.valueOf(System.currentTimeMillis()).substring(7);
    }

    protected String getRandomBirthRegNo()
    {
        return "90000001234" + String.valueOf(System.currentTimeMillis()).substring(7);
    }

    protected String getRandomUID()
    {
        return "90000" + String.valueOf(System.currentTimeMillis()).substring(7);
    }



    @After
    public void tearDown() {
        RestAssured.reset();
    }
}
