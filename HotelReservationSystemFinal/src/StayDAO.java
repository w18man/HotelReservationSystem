import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class StayDAO {
	private Connection myConn = null;
	
	
	public StayDAO() throws Exception{
		myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoteldatabase", "root", "xyyx111");
		System.out.println("DB connection successful to: hoteldatabase");
	}
	
	public List<Stay> getAllStays() throws Exception{
		List<Stay> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null; 
		
		try{
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from hotel_stay");
			
			while (myRs.next()){
				Stay tempStay = convertRowToStay(myRs);
				list.add(tempStay);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	public List<Stay> getStayByRoom(int roomNum) throws Exception{
		List<Stay> list = new ArrayList<>();
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try{
			myStmt = myConn.prepareStatement("select * from hotel_stay where stay_room=?");
			myStmt.setInt(1, roomNum);
			
			myRs = myStmt.executeQuery();
			
			while(myRs.next()){
				Stay tempStay = convertRowToStay(myRs);
				list.add(tempStay);
			}
			
			return list;
		}
		finally{
			close(myStmt, myRs);
		}
	}
	
	public List<Stay> getStayByGuest(int guestId) throws Exception{
		List<Stay> list = new ArrayList<>();
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try{
			myStmt = myConn.prepareStatement("select * from hotel_stay where stay_guest_id=?");
			myStmt.setInt(1, guestId);
			
			myRs = myStmt.executeQuery();
			
			while(myRs.next()){
				Stay tempStay = convertRowToStay(myRs);
				list.add(tempStay);
			}
			
			return list;
		}
		finally{
			close(myStmt, myRs);
		}
	}
	
	public List<Stay> getStayByRoomAndGuest(int roomNum, int guestId) throws Exception{
		List<Stay> list = new ArrayList<>();
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try{
			myStmt = myConn.prepareStatement("select * from hotel_stay where stay_room=? and stay_guest_id=?");
			myStmt.setInt(1, roomNum);
			myStmt.setInt(2, guestId);
			
			myRs = myStmt.executeQuery();
			
			while(myRs.next()){
				Stay tempStay = convertRowToStay(myRs);
				list.add(tempStay);
			}
			
			return list;
		}
		finally{
			close(myStmt, myRs);
		}
	}
	
	private static void close(Connection myConn, Statement myStmt, ResultSet myRs) throws SQLException{
		if (myRs != null){
			myRs.close();
		}
		if (myStmt != null){
			
		}
		if (myConn != null){
			myConn.close();
		}
	}
	
	private void close(Statement myStmt, ResultSet myRs) throws SQLException{
		close(null, myStmt, myRs);
	}
	
	private Stay convertRowToStay(ResultSet myRs) throws SQLException {
		int roomNum = myRs.getInt("stay_room");
		Date inDate = myRs.getDate("date_in");
		Date outDate = myRs.getDate("date_out");
		int stayId = myRs.getInt("stay_id");
		int guestId = myRs.getInt("stay_guest_id");
		
		Stay tempStay = new Stay(roomNum, inDate, outDate, stayId, guestId);
		
		return tempStay;
	}
	
	public void addStay(Stay theStay) throws Exception{
		PreparedStatement myStmt = null;
		try{
			myStmt = myConn.prepareStatement("insert into hotel_stay" + " (stay_room, date_in, date_out, stay_id, stay_guest_id)" + " values (?, ?, ?, ?, ?)");
			
			myStmt.setInt(1, theStay.getRoomNum());
			myStmt.setDate(2, theStay.getCheckIn());
			myStmt.setDate(3, theStay.getCheckOut());
			myStmt.setInt(4, theStay.getStayId());
			myStmt.setInt(5, theStay.getGuestId());
			
			myStmt.executeUpdate();
		}
		finally{
			close(myStmt, null);
		}
	}
	
	public List<Stay> searchStayByName(String lastName) throws Exception{
		List<Stay> list = new ArrayList<>();
		
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try{
			lastName += "%";
			myStmt = myConn.prepareStatement("select * from hotel_stay, hotel_guest where stay_guest_id=drivers_license and last_name like ?");
			
			myStmt.setString(1, lastName);
			
			myRs = myStmt.executeQuery();
			
			while(myRs.next()){
				Stay tempStay = convertRowToStay(myRs);
				list.add(tempStay);
			}
			
			return list;
			
		}
		finally{
			close(myStmt, myRs);
		}
	}
	
	public List<Stay> searchCheckedIn(String transaction) throws Exception{
		List<Stay> list = new ArrayList<>();
		
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try{
			myStmt = myConn.prepareStatement("select * from hotel_stay, hotel_bill where stay_id=bill_id and transaction_made=?");
			
			myStmt.setString(1, transaction);
			
			myRs = myStmt.executeQuery();
			
			while(myRs.next()){
				Stay tempStay = convertRowToStay(myRs);
				list.add(tempStay);
			}
			
			return list;
			
		}
		finally{
			close(myStmt, myRs);
		}
	}
	
	public List<Stay> searchCheckedOut(String transaction) throws Exception{
		List<Stay> list = new ArrayList<>();
		
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try{
			myStmt = myConn.prepareStatement("select * from hotel_stay, hotel_bill where stay_id=bill_id and transaction_made!=?");
			
			myStmt.setString(1, transaction);
			
			myRs = myStmt.executeQuery();
			
			while(myRs.next()){
				Stay tempStay = convertRowToStay(myRs);
				list.add(tempStay);
			}
			
			return list;
			
		}
		finally{
			close(myStmt, myRs);
		}
	}
	
	public void deleteStay(int stayId)throws SQLException{
		PreparedStatement myStmt = null;
		
		try{
			myStmt = myConn.prepareStatement("delete from hotel_stay where stay_id=?");
			myStmt.setInt(1, stayId);
			myStmt.executeUpdate();
		}
		finally{
			close(myStmt, null);
		}
	}
	
	public int getStayPricePerNight(int roomNum) throws Exception{
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try{
			myStmt = myConn.prepareStatement("select price_per_night from hotel_rooms where room_number=?");
			myStmt.setInt(1, roomNum);
			
			myRs = myStmt.executeQuery();
			
			myRs.next();
			return myRs.getInt("price_per_night");
		}
		finally{
			close(myStmt, myRs);
		}
	}
	
	public int getPayment(int guestId) throws Exception{
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try{
			myStmt = myConn.prepareStatement("select card_on_file from hotel_guest where drivers_license=?");
			myStmt.setInt(1, guestId);
			
			myRs = myStmt.executeQuery();
			
			myRs.next();
			return myRs.getInt("card_on_file");
		}
		finally{
			close(myStmt, myRs);
		}
	}
	
}
