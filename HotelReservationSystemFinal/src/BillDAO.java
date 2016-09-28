import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillDAO {
	private Connection myConn;
	
	public BillDAO() throws Exception{
		myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoteldatabase", "root", "xyyx111");
		System.out.println("DB connection successful to: hoteldatabase-hotel_bill");
	}
	
	public List<Bill> getAllBills() throws Exception{
		List<Bill> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null; 
		
		try{
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from hotel_bill");
			
			while (myRs.next()){
				Bill tempBill = convertRowToBill(myRs);
				list.add(tempBill);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	public List<Bill> getPayedBills(String string) throws Exception{
		List<Bill> list = new ArrayList<>();
		
		PreparedStatement myStmt = null;
		ResultSet myRs = null; 
		
		try{
			myStmt = myConn.prepareStatement("");
			myRs = myStmt.executeQuery("select * from hotel_bill where transaction_made=?");
			myStmt.setString(1, string);
			
			
			while (myRs.next()){
				Bill tempBill = convertRowToBill(myRs);
				list.add(tempBill);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	
	
	private Bill convertRowToBill(ResultSet myRs) throws SQLException {
		int nightRate = myRs.getInt("price_per_night");
		int paymentCard = myRs.getInt("payment_method");
		int billId = myRs.getInt("bill_id");
		int daysTotal = myRs.getInt("days_total");
		int total = myRs.getInt("total");
		String transaction = myRs.getString("transaction_made");
		
		Bill tempBill = new Bill(nightRate, paymentCard, billId, daysTotal, total, transaction);
		
		return tempBill;
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
	
	public void addBill(Bill theBill) throws Exception{
		PreparedStatement myStmt = null;
		
		try{
			myStmt = myConn.prepareStatement("insert into hotel_bill" + " (price_per_night, payment_method, bill_id, days_total, total, transaction_made)" + " values (?, ?, ?, ?, ?, ?)");
			
			myStmt.setInt(1, theBill.getNightlyRate());
			myStmt.setInt(2, theBill.getCardPayment());
			myStmt.setInt(3, theBill.getBillId());
			myStmt.setInt(4, theBill.getDaysTotal());
			myStmt.setInt(5, theBill.getTotal());
			myStmt.setString(6, theBill.getTransactionStatus());
			
			myStmt.executeUpdate();
		}
		finally{
			close(myStmt, null);
		}
	}
	
	public void updateBillTransaction(String transaction, int billId) throws SQLException{
		PreparedStatement myStmt = null;
		
		try{
			myStmt = myConn.prepareStatement("update hotel_bill" + " set transaction_made=?" + " where bill_id=?");
			
			myStmt.setString(1, transaction);
			myStmt.setInt(2, billId);
			
			myStmt.executeUpdate();
		}
		finally{
			close(myStmt, null);
		}
	}
	
	public void updateBillTotal(int newTotal, int billId) throws SQLException{
		PreparedStatement myStmt = null;
		
		try{
			myStmt = myConn.prepareStatement("update hotel_bill" + " set total=?" + " where bill_id=?");
			
			myStmt.setInt(1, newTotal);
			myStmt.setInt(2, billId);
			
			myStmt.executeUpdate();
		}
		finally{
			close(myStmt, null);
		}
	}
	
	public void deleteBill(int billId)throws SQLException{
		PreparedStatement myStmt = null;
		
		try{
			myStmt = myConn.prepareStatement("delete from hotel_bill where bill_id=?");
			myStmt.setInt(1, billId);
			myStmt.executeUpdate();
		}
		finally{
			close(myStmt, null);
		}
	}
}
