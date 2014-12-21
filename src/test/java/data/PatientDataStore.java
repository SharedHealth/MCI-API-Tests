package data;

import domain.Address;
import domain.Patient;
import org.json.JSONException;
import org.json.JSONObject;

public class PatientDataStore {


    public static Address defaultAddress = new Address.AddressBuilder()
            .division("Barisal")
            .district("Barguna")
            .upazilla("AMTALI")
            .cityCorporation("Unions of AMTALI Upazila")
            .union("AMTALI")
            .addressLine1("Test")
            .build();


    private  String id1 = String.valueOf(System.currentTimeMillis()).substring(7);

    public Patient defaultPatient = new Patient()
            .withNid("9000000" + id1)
            .withGiven_name("A" + id1)
            .withSur_name("ATEST")
            .withDateOfBirth("2000-03-01")
            .withGender("M");


    public JSONObject getAddressJsonForBarisal() {
        JSONObject present_address = new JSONObject();
        try {
            present_address.put("address_line", "Test");
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

}
