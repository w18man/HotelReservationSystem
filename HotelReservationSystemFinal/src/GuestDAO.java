
import java.sql.*;
import java.util.*;

public class GuestDAO {
	private Connection myConn;
	
	public GuestDAO() throws Exception{
		myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoteldatabase", "root", "xyyx111");
		System.out.println("DB connection successful to: hoteldatabase");
	}
	
	public List<Guest> getAllGuest() throws Exception{
		List<Guest> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null; 
		
		try{
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from hotel_guest");
			
			while (myRs.next()){
				Guest tempGuest = convertRowToGuest(myRs);
				list.add(tempGuest);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	private Guest convertRowToGuest(ResultSet myRs) throws SQLException {
		String firstName = myRs.getString("first_name");
		String lastName = myRs.getString("last_name");
		int driversLicense = myRs.getInt("drivers_license");
		int phoneNumber = myRs.getInt("phone_number");
		String address = myRs.getString("guest_address");
		int cardOnFile = myRs.getInt("card_on_file");
		
		Guest tempGuest = new Guest(firstName, lastName, driversLicense, phoneNumber, address, cardOnFile);
		
		return tempGuest;
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
	
	public void addGuest(Guest theGuest) throws Exception{
		PreparedStatement myStmt = null;
		
		try{
			myStmt = myConn.prepareStatement("insert into hotel_guest" + " (first_name, last_name, drivers_license, phone_number, guest_address, card_on_file)" + " values (?, ?, ?, ?, ?, ?)");
			
			myStmt.setString(1, theGuest.getFirstName());
			myStmt.setString(2, theGuest.getLastName());
			myStmt.setInt(3, theGuest.getDriversLicense());
			myStmt.setInt(4, theGuest.getPhoneNumber());
			myStmt.setString(5, theGuest.getAddress());
			myStmt.setInt(6, theGuest.getCardOnFile());
			
			myStmt.executeUpdate();
		}
		finally{
			close(myStmt, null);
		}
	}
	
	public void updateGuest(Guest theGuest) throws SQLException{
		PreparedStatement myStmt = null;
		
		try{
			myStmt = myConn.prepareStatement("update hotel_guest" + " set first_name=?, last_name=?, phone_number=?, guest_address=?, card_on_file=?" + " where drivers_license=?");
			
			myStmt.setString(1, theGuest.getFirstName());
			myStmt.setString(2, theGuest.getLastName());
			myStmt.setInt(6, theGuest.getDriversLicense());
			myStmt.setInt(3, theGuest.getPhoneNumber());
			myStmt.setString(4, theGuest.getAddress());
			myStmt.setInt(5, theGuest.getCardOnFile());
			
			myStmt.executeUpdate();
		}
		finally{
			close(myStmt, null);
		}
	}
	
}