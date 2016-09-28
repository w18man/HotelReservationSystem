import java.sql.*;
import java.util.*;

public class RoomDAO {
	private Connection myConn;
	
	public RoomDAO() throws Exception{
		myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoteldatabase", "root", "xyyx111");
		System.out.println("DB connection successful to: hoteldatabase");
	}
	
	public List<Room> getAllRooms() throws Exception{
		List<Room> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null; 
		
		try{
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from hotel_rooms");
			
			while (myRs.next()){
				Room tempRoom = convertRowToRoom(myRs);
				list.add(tempRoom);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	public List<Room> searchAvailableRooms() throws Exception{
		List<Room> list = new ArrayList<>();
		String available = "true";
		String occupation = "notOccupied";
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try{
			myStmt = myConn.prepareStatement("select * from hotel_rooms where available=? and occupation=?");
			myStmt.setString(1, available);
			myStmt.setString(2, occupation);
			
			myRs = myStmt.executeQuery();
			
			while(myRs.next()){
				Room tempRoom = convertRowToRoom(myRs);
				list.add(tempRoom);
			}
			
			return list;
			
		}
		finally{
			close(myStmt, myRs);
		}
	}
	
	private Room convertRowToRoom(ResultSet myRs) throws SQLException {
		int roomNum = myRs.getInt("room_number");
		int pricePerNight = myRs.getInt("price_per_night");
		String roomType = myRs.getString("room_type");
		String roomView = myRs.getString("room_view");
		String available = myRs.getString("available");
		String occupied = myRs.getString("occupation");
		
		
		
		Room tempRoom = new Room(roomNum, pricePerNight, roomType, roomView, available, occupied);
		
		return tempRoom;
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
	
	public void updateRoomOccupation(String occupation, int roomNum) throws SQLException{
		PreparedStatement myStmt = null;
		
		try{
			myStmt = myConn.prepareStatement("update hotel_rooms" + " set occupation=?" + " where room_number=?");
			
			myStmt.setString(1, occupation);
			myStmt.setInt(2, roomNum);
			
			myStmt.executeUpdate();
		}
		finally{
			close(myStmt, null);
		}
	}
	
	public void modifyRoomAvailability(String reason, int roomNum) throws SQLException{
		PreparedStatement myStmt = null;
		
		try{
			myStmt = myConn.prepareStatement("update hotel_rooms" + " set available=?" + " where room_number=?");
			
			myStmt.setString(1, reason);
			myStmt.setInt(2, roomNum);
			
			myStmt.executeUpdate();
		}
		finally{
			close(myStmt, null);
		}
	}
}
