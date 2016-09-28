
public class Room {
	private int roomNumber, nightlyRate;
	private String view, roomType, availabilityStatus, occupationStatus;
	
	public Room(int roomNum, int nightlyRat, String roomType, String roomView, String availabilityStatus, String occupationalStatus){
		super();
		this.roomNumber = roomNum;
		this.nightlyRate = nightlyRat;
		this.roomType = roomType;
		this.view = roomView;
		this.availabilityStatus = availabilityStatus;
		this.occupationStatus = occupationalStatus;
	}
	
	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	public int getNightlyRate() {
		return nightlyRate;
	}
	public void setNightlyRate(int nightlyRate) {
		this.nightlyRate = nightlyRate;
	}
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public String getAvailabilityStatus() {
		return availabilityStatus;
	}
	public void setAvailabilityStatus(String availabilityStatus) {
		this.availabilityStatus = availabilityStatus;
	}
	public String getOccupationStatus() {
		return occupationStatus;
	}
	public void setOccupationStatus(String occupationStatus) {
		this.occupationStatus = occupationStatus;
	} 

}
