
public class Bill {
	private int nightlyRate, cardPayment, billId, daysTotal, total;
	private String transactionStatus;
	public Bill(int nightlyRate, int cardPayment, int billId, int daysTotal, int total, String transactionStatus) {
		super();
		this.nightlyRate = nightlyRate;
		this.cardPayment = cardPayment;
		this.billId = billId;
		this.daysTotal = daysTotal;
		this.total = total;
		this.transactionStatus = transactionStatus;
	}
	public int getNightlyRate() {
		return nightlyRate;
	}
	public void setNightlyRate(int nightlyRate) {
		this.nightlyRate = nightlyRate;
	}
	public int getCardPayment() {
		return cardPayment;
	}
	public void setCardPayment(int cardPayment) {
		this.cardPayment = cardPayment;
	}
	public int getBillId() {
		return billId;
	}
	public void setBillId(int billId) {
		this.billId = billId;
	}
	public int getDaysTotal() {
		return daysTotal;
	}
	public void setDaysTotal(int daysTotal) {
		this.daysTotal = daysTotal;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getTransactionStatus() {
		return transactionStatus;
	}
	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	
}
