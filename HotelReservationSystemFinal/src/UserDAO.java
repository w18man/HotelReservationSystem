import java.util.ArrayList;
import java.sql.*;
import java.util.List;


public class UserDAO {
	private Connection myConn;
	
	public UserDAO() throws Exception{
		myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoteldatabase", "root", "xyyx111");
		System.out.println("DB connection successful to: hoteldatabase");
	}
	
	public List<User> getAllUsers() throws Exception{
		List<User> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null; 
		
		try{
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from user_info");
			
			while (myRs.next()){
				User tempUser = convertRowToUser(myRs);
				list.add(tempUser);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	public List<User> searchUsers(String lastName) throws Exception{
		List<User> list = new ArrayList<>();
		
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try{
			lastName += "%";
			myStmt = myConn.prepareStatement("select * from user_info where last_name like ?");
			
			myStmt.setString(1, lastName);
			
			myRs = myStmt.executeQuery();
			
			while(myRs.next()){
				User tempUser = convertRowToUser(myRs);
				list.add(tempUser);
			}
			
			return list;
			
		}
		finally{
			close(myStmt, myRs);
		}
	}
	
	private User convertRowToUser(ResultSet myRs) throws SQLException {
		String firstName = myRs.getString("first_name");
		String lastName = myRs.getString("last_name");
		String userName = myRs.getString("username");
		String passWord = myRs.getString("password");
		String userType = myRs.getString("user_type");
		int id = myRs.getInt("id");
		
		User tempUser = new User(firstName, lastName, userName, passWord, userType, id);
		
		return tempUser;
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
	
	public void addUser(User theUser) throws Exception{
		PreparedStatement myStmt = null;
		
		try{
			myStmt = myConn.prepareStatement("insert into user_info" + " (first_name, last_name, username, password, user_type, id)" + " values (?, ?, ?, ?, ?, ?)");
			
			myStmt.setString(1, theUser.getFirstName());
			myStmt.setString(2, theUser.getLastName());
			myStmt.setString(3, theUser.getUserName());
			myStmt.setString(4, theUser.getPassWord());
			myStmt.setString(5, theUser.getUserType());
			myStmt.setInt(6, theUser.getId());
			
			myStmt.executeUpdate();
		}
		finally{
			close(myStmt, null);
		}
	}
	
	public String userLogin(String username, String password) throws Exception{
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try{
			myStmt = myConn.prepareStatement("select user_type from user_info where username=? and password=?");
			
			myStmt.setString(1, username);
			myStmt.setString(2, password);
			
			myRs = myStmt.executeQuery();
			
			myRs.next();
			return myRs.getString("user_type");
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	public String userLoginName(String username, String password) throws Exception{
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try{
			myStmt = myConn.prepareStatement("select first_name from user_info where username=? and password=?");
			
			myStmt.setString(1, username);
			myStmt.setString(2, password);
			
			myRs = myStmt.executeQuery();
			
			myRs.next();
			return myRs.getString("first_name");
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	public void updateUser(User theUser) throws SQLException{
		PreparedStatement myStmt = null;
		
		try{
			myStmt = myConn.prepareStatement("update user_info" + " set first_name=?, last_name=?, username=?, password=?, user_type=?" + " where id=?");
			
			myStmt.setString(1, theUser.getFirstName());
			myStmt.setString(2, theUser.getLastName());
			myStmt.setString(3, theUser.getUserName());
			myStmt.setString(4, theUser.getPassWord());
			myStmt.setString(5, theUser.getUserType());
			myStmt.setInt(6, theUser.getId());
			
			myStmt.executeUpdate();
		}
		finally{
			close(myStmt, null);
		}
	}
	
	public void deleteUser(int userId)throws SQLException{
		PreparedStatement myStmt = null;
		
		try{
			myStmt = myConn.prepareStatement("delete from user_info where id=?");
			myStmt.setInt(1, userId);
			myStmt.executeUpdate();
		}
		finally{
			close(myStmt, null);
		}
	}
	

}
