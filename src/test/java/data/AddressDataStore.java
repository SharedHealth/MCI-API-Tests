package data;

import domain.Address;
import domain.Patient;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ashutosh on 22/12/14.
 */
public class AddressDataStore {


    public Address defaultAddress = new Address.AddressBuilder()
                .addressLine1("Test1")
                .division("10")
                .district("04")
                .upazilla("09")
                .cityCorporation("99")
                .union_or_urban_ward("13")
                .build();

    public Address getAddressForBarisal=new Address.AddressBuilder()

            .addressLine1("Barisal")
            .division("10")
            .district("04")
            .upazilla("18")
            .cityCorporation("16")
            .union_or_urban_ward("01")
            .build();

    public Address getAddressForDhaka=new Address.AddressBuilder()

            .addressLine1("Dhaka")
            .division("30")
            .district("26")
            .upazilla("18")
            .cityCorporation("99")
            .union_or_urban_ward("42")
            .build();

    public JSONObject getAddressJsonForBarisal() {
        JSONObject present_address = new JSONObject();
        try {
            present_address.put("address_line", "Test1");
            present_address.put("division_id", "10");
            present_address.put("district_id", "09");
            present_address.put("upazila_id", "18");
            present_address.put("city_corporation_id", "16");
            present_address.put("union_or_urban_ward_id", "01");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return present_address;
    }

    public JSONObject getAddressJsonForDhaka() {
        JSONObject present_address = new JSONObject();
        try {
            present_address.put("address_line", "Test");
            present_address.put("division_id", "30");
            present_address.put("district_id", "26");
            present_address.put("upazila_id", "18");
            present_address.put("city_corporation_id", "99");
            present_address.put("union_or_urban_ward_id", "42");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return present_address;
    }

    public JSONObject getAddressJsonForKhulna() {
        JSONObject present_address = new JSONObject();
        try {
            present_address.put("address_line", "Test");
            present_address.put("division_id", "40");
            present_address.put("district_id", "47");
            present_address.put("upazila_id", "53");
            present_address.put("city_corporation_id", "99");
            present_address.put("union_or_urban_ward_id", "10");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return present_address;
    }

    public JSONObject getAddressJsonForChittagong() {
        JSONObject present_address = new JSONObject();
        try {
            present_address.put("address_line", "Test");
            present_address.put("division_id", "20");
            present_address.put("district_id", "19");
            present_address.put("upazila_id", "81");
            present_address.put("city_corporation_id", "99");
            present_address.put("union_or_urban_ward_id", "72");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return present_address;
    }

    public JSONObject getAddressJsonForRajshahi() {
        JSONObject present_address = new JSONObject();
        try {
            present_address.put("address_line", "Test");
            present_address.put("division_id", "50");
            present_address.put("district_id", "70");
            present_address.put("upazila_id", "66");
            present_address.put("city_corporation_id", "99");
            present_address.put("union_or_urban_ward_id", "89");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return present_address;
    }

    public JSONObject getAddressJsonForRangpur() {
        JSONObject present_address = new JSONObject();
        try {
            present_address.put("address_line", "Test");
            present_address.put("division_id", "55");
            present_address.put("district_id", "32");
            present_address.put("upazila_id", "30");
            present_address.put("city_corporation_id", "33");
            present_address.put("union_or_urban_ward_id", "01");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return present_address;
    }

    public JSONObject getAddressJsonForSylhet() {
        JSONObject present_address = new JSONObject();
        try {
            present_address.put("address_line", "Test");
            present_address.put("division_id", "55");
            present_address.put("district_id", "32");
            present_address.put("upazila_id", "30");
            present_address.put("city_corporation_id", "33");
            present_address.put("union_or_urban_ward_id", "01");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return present_address;
    }

//    public Patient defaultPatient = new Patient()
//            .withNid("9000000" + id1)
//            .withBRN("90000001234" + id1)
//            .withGiven_name("A" + id1)
//            .withSur_name("ATEST")
//            .withDateOfBirth("2000-03-01")
//            .withGender("M");
//
//    public Patient patientWithInvalidNID = new Patient()
//            .withNid("invalid")
//            .withBRN("90000001234" + id1)
//            .withGiven_name("A" + id1)
//            .withSur_name("ATEST")
//            .withDateOfBirth("2000-03-01")
//            .withGender("M");
//
//}
}