import java.util.List;
import javax.swing.table.AbstractTableModel;
//import org.jfree.ui.SortableTableModel;

public class GuestTableModel extends AbstractTableModel{
	public static final int OBJECT_COL = -1;
	private static final int FIRST_NAME_COL = 0;
	private static final int LAST_NAME_COL = 1;
	private static final int DRIVERS_LICENSE_COL = 2;
	private static final int PHONE_NUM_COL = 3;
	private static final int ADDRESS_COL = 4;
	private static final int CARD_ON_FILE_COL = 5;
	

	private String[] columnNames = { "First Name", "Last Name", "Drivers License",
	"Phone #", "Address", "Credit Card #"};
	private List<Guest> guests;
	
	public GuestTableModel(List<Guest> theGuests) {
	guests = theGuests;
	}
	
	@Override
	public int getColumnCount() {
	return columnNames.length;
	}
	
	@Override
	public int getRowCount() {
	return guests.size();
	}
	
	@Override
	public String getColumnName(int col) {
	return columnNames[col];
	}
	
	@Override
	public Object getValueAt(int row, int col) {
	
	Guest tempGuest = guests.get(row);
	
	switch (col) {
	case LAST_NAME_COL:
		return tempGuest.getLastName();
	case FIRST_NAME_COL:
		return tempGuest.getFirstName();
	case DRIVERS_LICENSE_COL:
		return tempGuest.getDriversLicense();
	case PHONE_NUM_COL:
		return tempGuest.getPhoneNumber();
	case ADDRESS_COL:
		return tempGuest.getAddress();
	case CARD_ON_FILE_COL:
		return tempGuest.getCardOnFile();
	case OBJECT_COL:
		return tempGuest;
	default:
		return tempGuest.getLastName();
		
	}
	}
	
	@Override
	public Class getColumnClass(int c) {
	return getValueAt(0, c).getClass();
	}

}