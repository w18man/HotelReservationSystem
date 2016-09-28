import java.sql.Date;


public class DifferenceInDates {
	private java.sql.Date dateIn, dateOut;

	public DifferenceInDates(Date inDate, Date outDate){
		this.dateIn = inDate;
		this.dateOut = outDate;
	}
	

	public long getTotalDays(){
		long diffInDays = (dateOut.getTime() - dateIn.getTime())/86400000;
		return diffInDays;
	}
}
