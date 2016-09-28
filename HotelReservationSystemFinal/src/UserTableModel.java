import java.util.List;
import javax.swing.table.AbstractTableModel;
//import org.jfree.ui.SortableTableModel;

public class UserTableModel extends AbstractTableModel{
	public static final int OBJECT_COL = -1;
	private static final int FIRST_NAME_COL = 0;
	private static final int LAST_NAME_COL = 1;
	private static final int USERNAME_COL = 2;
	private static final int PASSWORD_COL = 3;
	private static final int USER_TYPE_COL = 4;
	private static final int ID_COL = 5;
	

	private String[] columnNames = { "First Name", "Last Name", "Username",
	"Password", "User Type", "ID"};
	private List<User> users;
	
	public UserTableModel(List<User> theUsers) {
	users = theUsers;
	}
	
	@Override
	public int getColumnCount() {
	return columnNames.length;
	}
	
	@Override
	public int getRowCount() {
	return users.size();
	}
	
	@Override
	public String getColumnName(int col) {
	return columnNames[col];
	}
	
	@Override
	public Object getValueAt(int row, int col) {
	
	User tempUser = users.get(row);
	
	switch (col) {
	case LAST_NAME_COL:
		return tempUser.getLastName();
	case FIRST_NAME_COL:
		return tempUser.getFirstName();
	case USERNAME_COL:
		return tempUser.getUserName();
	case PASSWORD_COL:
		return tempUser.getPassWord();
	case USER_TYPE_COL:
		return tempUser.getUserType();
	case ID_COL:
		return tempUser.getId();
	case OBJECT_COL:
		return tempUser;
	default:
		return tempUser.getLastName();
	}
	}
	
	@Override
	public Class getColumnClass(int c) {
	return getValueAt(0, c).getClass();
	}

}
