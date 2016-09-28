import java.util.List;
import javax.swing.table.AbstractTableModel;
//import org.jfree.ui.SortableTableModel;

public class StayTableModel extends AbstractTableModel{
	public static final int OBJECT_COL = -1;
	private static final int ROOM_NUM_COL = 0;
	private static final int DATE_IN = 1;
	private static final int DATE_OUT = 2;
	private static final int RESERVATION_ID = 3;
	private static final int GUEST_ID = 4;
	
	

	private String[] columnNames = { "Room #", "Check in", "Check out",
	"Reservation ID", "Guest ID"};
	private List<Stay> stays;
	
	public StayTableModel(List<Stay> theStays) {
	stays = theStays;
	}
	
	@Override
	public int getColumnCount() {
	return columnNames.length;
	}
	
	@Override
	public int getRowCount() {
	return stays.size();
	}
	
	@Override
	public String getColumnName(int col) {
	return columnNames[col];
	}
	
	@Override
	public Object getValueAt(int row, int col) {
	
	Stay tempStay = stays.get(row);
	
	switch (col) {	
	case ROOM_NUM_COL:
		return tempStay.getRoomNum();
	case DATE_IN:
		return tempStay.getCheckIn();
	case DATE_OUT:
		return tempStay.getCheckOut();
	case RESERVATION_ID:
		return tempStay.getStayId();
	case GUEST_ID:
		return tempStay.getGuestId();
	case OBJECT_COL:
		return tempStay;
	default:
		return tempStay.getStayId();
	}
	}
	
	@Override
	public Class getColumnClass(int c) {
	return getValueAt(0, c).getClass();
	}

}
