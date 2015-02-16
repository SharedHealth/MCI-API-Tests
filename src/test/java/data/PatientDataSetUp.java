package data;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.RestAssuredConfig;
import domain.Patient;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;

import static com.jayway.restassured.RestAssured.basic;
import static com.jayway.restassured.config.EncoderConfig.encoderConfig;

/**
 * Created by ashutosh on 24/12/14.
 */
public class PatientDataSetUp {


    @Before
    public void invokeApp() {





        // RestAssured.baseURI = "http://172.18.46.56";
        RestAssured.baseURI = "http://172.18.46.56:8081";
//      RestAssured.baseURI = WebDriverProperties.getProperty("mciURL");
//        RestAssured.port = 8081;
        RestAssured.basePath = "/api/v1";
        RestAssured.authentication = basic("mci", "password");
        RestAssured.rootPath = "";
        RestAssured.config = new RestAssuredConfig().encoderConfig(encoderConfig().defaultContentCharset("UTF-8"));
    }

    private void pendingApprovalURLbyHID(String hid){


    }

    protected JSONObject createPatientDataJsonToPost(Patient primaryPatient) {
        JSONObject person = new JSONObject();
        JSONObject present_address = new JSONObject();

        try {
            person.put("nid", primaryPatient.getNid());
            person.put("bin_brn", primaryPatient.getBinBRN());
            person.put("given_name", primaryPatient.getGiven_name());
            person.put("sur_name", primaryPatient.getSur_name());
            person.put("date_of_birth", primaryPatient.getDateOfBirth());
            person.put("gender", primaryPatient.getGender());
            //present_address.put("present_address", primaryPatient.getAddress());
            present_address.put("address_line", primaryPatient.getAddress().getAddressLine1());
            //present_address.put("address_line", "test");
            present_address.put("division_id", primaryPatient.getAddress().getDivision());
            present_address.put("district_id", primaryPatient.getAddress().getDistrict());
            present_address.put("upazila_id", primaryPatient.getAddress().getUpazilla());
            present_address.put("city_corporation_id", primaryPatient.getAddress().getCityCorporation());
            present_address.put("union_or_urban_ward_id", primaryPatient.getAddress().getUnion_or_urban_ward());
            person.put("present_address", present_address );

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