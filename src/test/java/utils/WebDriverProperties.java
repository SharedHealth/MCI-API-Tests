package utils;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.RestAssuredConfig;
import com.jayway.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Properties;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.config.EncoderConfig.encoderConfig;
import static org.apache.commons.lang3.StringUtils.isNotBlank;


public class WebDriverProperties {

    public static String getProperty(String propertyName) {
        if (isNotBlank(System.getProperty(propertyName))) {
            return System.getProperty(propertyName);
        } else {
            Properties properties = loadProperties();
            return properties.getProperty(propertyName);
        }
    }

    public static String getToken() throws JSONException {

        RestAssured.baseURI = WebDriverProperties.getProperty("MCI_IDENTITY_SERVER_URI");
        RestAssured.basePath = "";
        RestAssured.port = Integer.parseInt(WebDriverProperties.getProperty("MCI_IDENTITY_PORT"));
        RestAssured.rootPath = "";
        RestAssured.config = new RestAssuredConfig().encoderConfig(encoderConfig().defaultContentCharset("UTF-8"));


        Response response = given().contentType("application/x-www-form-urlencoded")
                .header("X-Auth-Token", WebDriverProperties.getProperty("MCI_IDENTITY_TOKEN"))
                .header("client_id", WebDriverProperties.getProperty("MCI_IDENTITY_CLIENT_ID"))
                .formParam("email", WebDriverProperties.getProperty("MCI_IDENTITY_EMAIL"))
                .formParam("password", WebDriverProperties.getProperty("MCI_IDENTITY_PASSWORD"))
                .post("/signin").andReturn();

        System.out.println(response.getBody().asString());
        JSONObject jsonObject = new JSONObject(response.getBody().asString());

        return jsonObject.get("access_token").toString();

    }

    public static String getUserId(String token) throws JSONException {
        RestAssured.baseURI = WebDriverProperties.getProperty("MCI_IDENTITY_SERVER_URI");
        RestAssured.basePath = "";
        RestAssured.port = Integer.parseInt(WebDriverProperties.getProperty("MCI_IDENTITY_PORT"));
        RestAssured.rootPath = "";
        RestAssured.config = new RestAssuredConfig().encoderConfig(encoderConfig().defaultContentCharset("UTF-8"));

        Response response = given().contentType("application/x-www-form-urlencoded")
                .header("X-Auth-Token", WebDriverProperties.getProperty("MCI_IDENTITY_TOKEN"))
                .header("client_id", WebDriverProperties.getProperty("MCI_IDENTITY_CLIENT_ID"))
                .get("/token/" + token).andReturn();

        System.out.println(response.getBody().asString());
        JSONObject jsonObject = new JSONObject(response.getBody().asString());

        return jsonObject.get("id").toString();

    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try {
            properties.load(WebDriverProperties.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
