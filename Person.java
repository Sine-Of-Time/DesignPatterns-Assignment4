package assignment04;

import java.io.Serializable;

public class Person implements Serializable {
	private static final long serialVersionUID = -203415156396350169L;
	private String name;
	private String streetAddress;
	private String city;
	private String stateAndZip;
	private String country;
	private String dateOfBirth;
	private String ssnOrItin;
	public Person (String name, String dob, String num) {
		this.name = name;
		dateOfBirth = dob;
		ssnOrItin = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String nameIn) {
		name = nameIn;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String cityIn) {
		city = cityIn;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String countryIn) {
		country = countryIn;
	}
	public String getStateAndZip() {
		return stateAndZip;
	}
	public void setStateAndZip(String stateAndZipIn) {
		stateAndZip = stateAndZipIn;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddressIn) {
		streetAddress = streetAddressIn;
	}
	public String getSsnOrItin() {
		return ssnOrItin;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
}
