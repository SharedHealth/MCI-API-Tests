package data;

import domain.Address;
import domain.Patient;
import org.json.JSONException;
import org.json.JSONObject;

public class PatientDataStore {


    protected AddressDataStore address = new AddressDataStore();





    private  String id1 = String.valueOf(System.currentTimeMillis()).substring(7);

    public Patient defaultPatient = new Patient()
            .withNid("9000000" + id1)
            .withBinBRN("90000001234" + id1)
            .withGiven_name("A" + id1)
            .withSur_name("ATEST")
            .withDateOfBirth("2000-03-01")
            .withGender("M")
            .address(address.defaultAddress)
            .build();

    public Patient barisalPatient = new Patient()
            .withNid("9000000" + id1)
            .withBinBRN("90000001234" + id1)
            .withGiven_name("A" + id1)
            .withSur_name("ATEST")
            .withDateOfBirth("2000-03-01")
            .withGender("M")
            .address(address.getAddressForBarisal)
            .build();

    public Patient dhakaPatient = new Patient()
            .withNid("9000000" + id1)
            .withBinBRN("90000001234" + id1)
            .withGiven_name("A" + id1)
            .withSur_name("ATEST")
            .withDateOfBirth("2000-03-01")
            .withGender("M")
            .address(address.getAddressForDhaka)
            .build();

}
