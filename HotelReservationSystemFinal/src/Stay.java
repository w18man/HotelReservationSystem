import java.sql.Date;

public class Stay {
	private int roomNum;
	private java.sql.Date checkIn, checkOut;
	private int stayId, guestId;
	public Stay(int roomNum, Date checkIn, Date checkOut, int stayId, int guestId) {
		super();
		this.roomNum = roomNum;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.stayId = stayId;
		this.guestId = guestId;
	}
	public int getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}
	public Date getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}
	public Date getCheckOut() {
		return checkOut;
	}
	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}
	public int getStayId() {
		return stayId;
	}
	public void setStayId(int stayId) {
		this.stayId = stayId;
	}
	public int getGuestId() {
		return guestId;
	}
	public void setGuestId(int guestId) {
		this.guestId = guestId;
	}
}
