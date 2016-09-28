import java.util.List;
import javax.swing.table.AbstractTableModel;
//import org.jfree.ui.SortableTableModel;

public class BillTableModel extends AbstractTableModel{
	public static final int OBJECT_COL = -1;
	private static final int NIGHT_RATE = 0;
	private static final int CARD_PAY = 1;
	private static final int BILL_ID = 2;
	private static final int DAYS_TOTAL = 3;
	private static final int TOTAL = 4;
	private static final int TRANSACTION = 5;
	

	private String[] columnNames = { "Nightly Rate", "Payment Card", "Bill ID",
	"Stay Length", "Bill Total", "Transaction Status"};
	private List<Bill> bills;
	
	public BillTableModel(List<Bill> theBills) {
	bills = theBills;
	}
	
	@Override
	public int getColumnCount() {
	return columnNames.length;
	}
	
	@Override
	public int getRowCount() {
	return bills.size();
	}
	
	@Override
	public String getColumnName(int col) {
	return columnNames[col];
	}
	
	@Override
	public Object getValueAt(int row, int col) {
	
	Bill tempBill = bills.get(row);
	
	switch (col) {
	case NIGHT_RATE:
		return tempBill.getNightlyRate();
	case CARD_PAY:
		return tempBill.getCardPayment();
	case BILL_ID:
		return tempBill.getBillId();
	case DAYS_TOTAL:
		return tempBill.getDaysTotal();
	case TOTAL:
		return tempBill.getTotal();
	case TRANSACTION:
		return tempBill.getTransactionStatus();
	case OBJECT_COL:
		return tempBill;
	default:
		return tempBill.getBillId();
		
	}
	}
	
	@Override
	public Class getColumnClass(int c) {
	return getValueAt(0, c).getClass();
	}

}