
public class Guest {
	private String firstName, lastName, address;
	private int cardOnFile, phoneNumber, driversLicense;
	public Guest(String firstName, String lastName, int driversLicense, int phoneNumber, String address,
			int cardOnFile) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.cardOnFile = cardOnFile;
		this.phoneNumber = phoneNumber;
		this.driversLicense = driversLicense;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getCardOnFile() {
		return cardOnFile;
	}
	public void setCardOnFile(int cardOnFile) {
		this.cardOnFile = cardOnFile;
	}
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getDriversLicense() {
		return driversLicense;
	}
	public void setDriversLicense(int driversLicense) {
		this.driversLicense = driversLicense;
	}

}
