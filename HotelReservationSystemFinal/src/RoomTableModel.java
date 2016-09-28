import java.util.List;
import javax.swing.table.AbstractTableModel;
//import org.jfree.ui.SortableTableModel;

public class RoomTableModel extends AbstractTableModel{
	public static final int OBJECT_COL = -1;
	private static final int ROOM_NUMBER_COL = 0;
	private static final int PRICE_PER_NIGHT_COL = 1;
	private static final int ROOM_TYPE_COL = 2;
	private static final int ROOM_VIEW_COL = 3;
	private static final int AVAILABLE_COL = 4;
	private static final int OCCUPATION_COL = 5;
	

	private String[] columnNames = { "Room #", "Nightly Rate", "Room Type",
	"View", "Availability status", "Occupational status"};
	private List<Room> rooms;
	
	public RoomTableModel(List<Room> theRooms) {
	rooms = theRooms;
	}
	
	@Override
	public int getColumnCount() {
	return columnNames.length;
	}
	
	@Override
	public int getRowCount() {
	return rooms.size();
	}
	
	@Override
	public String getColumnName(int col) {
	return columnNames[col];
	}
	
	@Override
	public Object getValueAt(int row, int col) {
	
	Room tempRoom = rooms.get(row);
	
	switch (col) {
	case ROOM_NUMBER_COL:
		return tempRoom.getRoomNumber();
	case PRICE_PER_NIGHT_COL:
		return tempRoom.getNightlyRate();
	case ROOM_TYPE_COL:
		return tempRoom.getRoomType();
	case ROOM_VIEW_COL:
		return tempRoom.getView();
	case AVAILABLE_COL:
		return tempRoom.getAvailabilityStatus();
	case OCCUPATION_COL:
		return tempRoom.getOccupationStatus();
	case OBJECT_COL:
		return tempRoom;
	default:
		return tempRoom.getRoomNumber();
	}
	}
	
	@Override
	public Class getColumnClass(int c) {
	return getValueAt(0, c).getClass();
	}

}

