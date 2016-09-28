import java.awt.CardLayout;
import javax.swing.*;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Cursor;
import java.awt.Color;



public class UserGUI {
	private JFrame windowFrame;
	private JPanel baseLayer, loginScreen, reserveRoomTab, checkInTab, checkOutTab, viewBillTab, viewOccupationTab, modifyBillTab, manageStaffTab, logoutTab1, logoutTab2, logoutTab3, homeTab1, homeTab2, homeTab3;
	private CardLayout card;
	private JTextField userName, userLastNameSearch;
	private JPasswordField passWord;
	private JTabbedPane frontDeskView, maintenanceView, managerView;
	private JButton logIn, userSearch, addUser, logOut1, logOut2, logOut3;
	private UserDAO userDAO;
	private RoomDAO roomDAO;
	private GuestDAO guestDAO;
	private StayDAO stayDAO;
	private BillDAO billDAO;
	private JTable userTable;
	private welcomeLabel welcome1, welcome2, welcome3;
	private JScrollPane manageStaffScrollPane;
	private JButton updateUser;
	private JButton btnRemoveUser;
	private JButton viewAvailableRooms;
	private JButton btnViewAllRooms;
	private JScrollPane reserveRoomScrollPane;
	private JTable reserveRoomTable;
	private JButton viewReservations;
	private JButton manageGuest;
	private JButton btnViewGuestList;
	private JButton btnReserve;
	private JScrollPane scrollPane;
	private JTable guestTable;
	private JTable checkInTable;
	private JScrollPane scrollPane_1;
	private JButton viewAllReservationsIn;
	private JTextField lastTextIn;
	private JButton lastNameInBtn;
	private JButton checkOutBtn;
	private JTable checkOutTable;
	private JButton viewAllReservationsOut;
	private JButton cancelReservation;
	private JButton checkInButton;
	private JScrollPane scrollPane_2;
	private JScrollPane scrollPane_3;
	private JTable frontBillTable;
	private JButton viewAllBillFront;
	private JScrollPane scrollPane_4;
	private JTable maintenenceRoomView;
	private JButton maintenanceRoomBtn;
	private JButton cleanRoomBtn;
	private JTextField modifyAvailableText;
	private JButton btnModifyAvail;
	private JScrollPane scrollPane_5;
	private JTable managerBillTable;
	private JButton btnNewButton;
	private JTextField totalText;
	private JButton btnModifyTotal;
	private JButton btnDropCharge;
	private JButton backToManager;
	private JButton backToManagerView2;

	
	
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new UserGUI();
			}
		});
	}
	
	public UserGUI(){
		try{
			userDAO = new UserDAO();
			roomDAO = new RoomDAO();
			guestDAO = new GuestDAO();
			stayDAO = new StayDAO();
			billDAO = new BillDAO();
		}catch(Exception exc){
			JOptionPane.showInputDialog(this);
		}
		
		card = new CardLayout();
		windowFrame = new JFrame();
		windowFrame.setTitle("Hotel Reservation System");
		card = new CardLayout();
		baseLayer = new JPanel();
		baseLayer.setLayout(card);
		
		//creation of the login screen and it's components
		loginScreen = new JPanel();
		loginScreen.setBackground(SystemColor.activeCaption);
		logIn = new JButton();
		loginScreen.setLayout(null);
		userName = new JTextField();
		userName.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		userName.setBackground(SystemColor.info);
		userName.setBounds(218, 175, 148, 36);
		loginScreen.add(userName);
		passWord = new JPasswordField();
		passWord.setBackground(SystemColor.info);
		passWord.setBounds(218, 239, 148, 36);
		loginScreen.add(passWord);
		
		
		
		JLabel lblNewLabel = new JLabel("User Login");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblNewLabel.setBounds(165, 63, 231, 49);
		loginScreen.add(lblNewLabel);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUsername.setBounds(77, 175, 131, 37);
		loginScreen.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(110, 239, 98, 36);
		loginScreen.add(lblPassword);
		
		logIn = new JButton("Login");
		logIn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		logIn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		logIn.setBackground(SystemColor.inactiveCaptionBorder);
		logIn.setBounds(218, 297, 148, 36);
		logIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String username = userName.getText();
					String manager = new String("manager");
					String frontDesk = new String("frontDesk");
					String maintenance = new String("maintenance");
					char[] temp = passWord.getPassword();
					String password = new String(temp);
					String userType = userDAO.userLogin(username, password);
					String firstName = userDAO.userLoginName(username, password);
					
					
					
					if(userType.equals(manager)){
						welcome3 = new welcomeLabel(firstName);
						welcome3.setBounds(48, 107, 478, 56);
						homeTab3.add(welcome3);
						managerView.setSelectedIndex(0);
						card.show(baseLayer, "4");
					}else if(userType.equals(frontDesk)){
						welcome1 = new welcomeLabel(firstName);
						welcome1.setBounds(48, 107, 478, 56);
						homeTab1.add(welcome1);
						frontDeskView.setSelectedIndex(0);
						card.show(baseLayer, "2");
					}else if(userType.equals(maintenance)){
						welcome2 = new welcomeLabel(firstName);
						welcome2.setBounds(48, 107, 478, 56);
						homeTab2.add(welcome2);
						maintenanceView.setSelectedIndex(0);
						card.show(baseLayer, "3");
					}
					
					userName.setText("");
					passWord.setText("");
				}catch(Exception exc){
					JOptionPane.showMessageDialog(loginScreen, "incorrect username or password");
					userName.setText("");
					passWord.setText("");
			}
			}});
		loginScreen.add(logIn);
		
		//creation of the frontDeskView and its contents
		frontDeskView = new JTabbedPane();
		frontDeskView.setBackground(SystemColor.activeCaption);
		homeTab1 = new JPanel();
		homeTab1.setBackground(SystemColor.activeCaption);
		reserveRoomTab = new JPanel();
		reserveRoomTab.setBackground(SystemColor.activeCaption);
		checkInTab = new JPanel();
		checkInTab.setBackground(SystemColor.activeCaption);
		checkOutTab = new JPanel();
		checkOutTab.setBackground(SystemColor.activeCaption);
		viewBillTab = new JPanel();
		viewBillTab.setBackground(SystemColor.activeCaption);
		logoutTab1 = new JPanel();
		logoutTab1.setBackground(SystemColor.activeCaption);
		logoutTab1.setLayout(null);
		logOut1 = new JButton();
		logOut1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				homeTab1.remove(welcome1);
				DefaultTableModel empty = new DefaultTableModel();
				reserveRoomTable.setModel(empty);
				guestTable.setModel(empty);
				checkInTable.setModel(empty);
				checkOutTable.setModel(empty);
				frontBillTable.setModel(empty);
				card.show(baseLayer, "1");
			}
		});
		logOut1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		logOut1.setText("confirm");
		logOut1.setBounds(226, 186, 131, 56);
		logoutTab1.add(logOut1);
		frontDeskView.add(homeTab1);
		homeTab1.setLayout(null);
		
		backToManager = new JButton("Return to Manager View");
		backToManager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(baseLayer, "4");
			}
		});
		backToManager.setFont(new Font("Tahoma", Font.PLAIN, 14));
		backToManager.setBounds(187, 339, 231, 31);
		frontDeskView.setBackgroundAt(0, SystemColor.inactiveCaptionBorder);
		frontDeskView.setTitleAt(0, "Home");
		frontDeskView.add(reserveRoomTab);
		reserveRoomTab.setLayout(null);
		
		viewAvailableRooms = new JButton("View available rooms");
		viewAvailableRooms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					List<Room> rooms = roomDAO.searchAvailableRooms();
					RoomTableModel model = new RoomTableModel(rooms);
					reserveRoomTable.setModel(model);
				}catch(Exception exc){
					
				}
			}	
		});
		viewAvailableRooms.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		viewAvailableRooms.setFont(new Font("Tahoma", Font.PLAIN, 14));
		viewAvailableRooms.setBounds(23, 11, 171, 30);
		reserveRoomTab.add(viewAvailableRooms);
		
		reserveRoomScrollPane = new JScrollPane();
		reserveRoomScrollPane.setBounds(10, 49, 569, 166);
		reserveRoomTab.add(reserveRoomScrollPane);
		
		reserveRoomTable = new JTable();
		reserveRoomTable.setAutoCreateRowSorter(true);
		reserveRoomScrollPane.setViewportView(reserveRoomTable);
		
		btnViewAllRooms = new JButton("View all rooms");
		btnViewAllRooms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					List<Room> rooms = roomDAO.getAllRooms();
					RoomTableModel model = new RoomTableModel(rooms);
					reserveRoomTable.setModel(model);
				}catch(Exception exc){
					
				}
			}
		});
		btnViewAllRooms.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnViewAllRooms.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnViewAllRooms.setBounds(204, 11, 171, 30);
		reserveRoomTab.add(btnViewAllRooms);
		
		viewReservations = new JButton("View Reservations");
		viewReservations.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		viewReservations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
				int row = reserveRoomTable.getSelectedRow();
				int row2 = guestTable.getSelectedRow();
				//Guest tempGuest = (Guest) guestTable.getValueAt(row2, GuestTableModel.
				List<Stay> stays = null;
				
					if((row < 0) && (row2 < 0)){
						stays = stayDAO.getAllStays();
						if(stays.isEmpty()){
							JOptionPane.showMessageDialog(frontDeskView, "there are no reservations");
						}else{
							StayTableModel model = new StayTableModel(stays);
							reserveRoomTable.setModel(model);
						}
					}else if((row >= 0) && (row2 < 0)){
						Room tempRoom = (Room) reserveRoomTable.getValueAt(row, RoomTableModel.OBJECT_COL);
						int tempRoomNum = tempRoom.getRoomNumber();
						stays = stayDAO.getStayByRoom(tempRoomNum);
						if(stays.isEmpty()){
							JOptionPane.showMessageDialog(frontDeskView, "This room has no reservations");
						}else{
							StayTableModel model = new StayTableModel(stays);
							reserveRoomTable.setModel(model);
						}
					}else if((row < 0) && (row2 >= 0)){
						Guest tempGuest = (Guest) guestTable.getValueAt(row2, GuestTableModel.OBJECT_COL);
						int tempGuestNum = tempGuest.getDriversLicense();
						stays = stayDAO.getStayByGuest(tempGuestNum);
						if(stays.isEmpty()){
							JOptionPane.showMessageDialog(frontDeskView, "This guest has no reservations");
						}else{
							StayTableModel model = new StayTableModel(stays);
							reserveRoomTable.setModel(model);
						}
					}else if((row >= 0) && (row2 >= 0)){
						Room tempRoom = (Room) reserveRoomTable.getValueAt(row, RoomTableModel.OBJECT_COL);
						int tempRoomNum = tempRoom.getRoomNumber();
						Guest tempGuest = (Guest) guestTable.getValueAt(row2, GuestTableModel.OBJECT_COL);
						int tempGuestNum = tempGuest.getDriversLicense();
						stays = stayDAO.getStayByRoomAndGuest(tempRoomNum, tempGuestNum);
						if(stays.isEmpty()){
							JOptionPane.showMessageDialog(frontDeskView, "No Reservations match the Room and Guest criteria");
						}else{
							StayTableModel model = new StayTableModel(stays);
							reserveRoomTable.setModel(model);
						}
					}
				}catch(Exception exc){
					//reserveRoomTable.setModel(new DefaultTableModel());
				}
			}
		});
		viewReservations.setFont(new Font("Tahoma", Font.PLAIN, 14));
		viewReservations.setBounds(385, 11, 171, 29);
		reserveRoomTab.add(viewReservations);
		
		manageGuest = new JButton("Guest Management");
		manageGuest.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		manageGuest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = guestTable.getSelectedRow();
				
				if(row < 0){
					AddGuestDialog dialog = new AddGuestDialog(UserGUI.this, guestDAO);
					dialog.setVisible(true);
				}else{
					Guest tempGuest = (Guest) guestTable.getValueAt(row, GuestTableModel.OBJECT_COL);
					AddGuestDialog dialog = new AddGuestDialog(UserGUI.this, guestDAO, tempGuest, true);
					dialog.setVisible(true);
				}
			}
		});
		manageGuest.setFont(new Font("Tahoma", Font.PLAIN, 14));
		manageGuest.setBounds(204, 226, 171, 30);
		reserveRoomTab.add(manageGuest);
		
		btnViewGuestList = new JButton("View Guest List");
		btnViewGuestList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnViewGuestList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
				List<Guest> guests = guestDAO.getAllGuest();
				GuestTableModel model = new GuestTableModel(guests);
				guestTable.setModel(model);
				}catch(Exception exc){
					
				}
			}
		});
		btnViewGuestList.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnViewGuestList.setBounds(23, 226, 171, 30);
		reserveRoomTab.add(btnViewGuestList);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 267, 569, 165);
		reserveRoomTab.add(scrollPane);
		
		guestTable = new JTable();
		guestTable.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(guestTable);
		
		btnReserve = new JButton("Reserve");
		btnReserve.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnReserve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					int row = reserveRoomTable.getSelectedRow();
					int row2 = guestTable.getSelectedRow();
					if((row < 0) || (row2 < 0)){
						JOptionPane.showMessageDialog(frontDeskView, "You must select both a Room and a Guest");		
					}else {
						Room tempRoom = (Room) reserveRoomTable.getValueAt(row, RoomTableModel.OBJECT_COL);
						int tempRoomNum = tempRoom.getRoomNumber();
						Guest tempGuest = (Guest) guestTable.getValueAt(row2, GuestTableModel.OBJECT_COL);
						int tempGuestNum = tempGuest.getDriversLicense();
						AddStayDialog newStay = new AddStayDialog(UserGUI.this, stayDAO, tempRoomNum, tempGuestNum);
						newStay.setVisible(true);
					}
				}catch(Exception exc){
					
				}
			}
		});
		btnReserve.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnReserve.setBounds(385, 226, 171, 30);
		reserveRoomTab.add(btnReserve);
		frontDeskView.setBackgroundAt(1, SystemColor.inactiveCaptionBorder);
		frontDeskView.setTitleAt(1, "Reserve Room");
		frontDeskView.add(checkInTab);
		checkInTab.setLayout(null);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(47, 111, 490, 173);
		checkInTab.add(scrollPane_1);
		
		checkInTable = new JTable();
		checkInTable.setAutoCreateRowSorter(true);
		scrollPane_1.setViewportView(checkInTable);
		
		viewAllReservationsIn = new JButton("View All Reservations");
		viewAllReservationsIn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		viewAllReservationsIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					List<Stay> stays = stayDAO.getAllStays();
					StayTableModel model = new StayTableModel(stays);
					checkInTable.setModel(model);
				}catch(Exception exc){
					
				}
			}
		});
		viewAllReservationsIn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		viewAllReservationsIn.setBounds(362, 69, 175, 31);
		checkInTab.add(viewAllReservationsIn);
		
		checkInButton = new JButton("Check-in");
		checkInButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		checkInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int row = checkInTable.getSelectedRow();
					DifferenceInDates tempDiff;
					if (row < 0){
						JOptionPane.showMessageDialog(frontDeskView, "you must select a reservation");
					}else{
						Stay tempStay = (Stay) checkInTable.getValueAt(row, StayTableModel.OBJECT_COL);
						java.sql.Date dateIn = tempStay.getCheckIn();
						java.sql.Date dateOut = tempStay.getCheckOut();
						int guestId = tempStay.getGuestId();
						tempDiff = new DifferenceInDates(dateIn, dateOut);
						int daysTotal = (int) tempDiff.getTotalDays();
						int stayId = tempStay.getStayId();
						int roomNum = tempStay.getRoomNum();
						int priceNight = stayDAO.getStayPricePerNight(roomNum);
						int cardPayment = stayDAO.getPayment(guestId);
						String transaction = new String("false");
						String occupation = new String("occupied");
						int priceTotal = priceNight * daysTotal;
						Bill tempBill = new Bill(priceNight, cardPayment, stayId, daysTotal, priceTotal, transaction);
						billDAO.addBill(tempBill);
						roomDAO.updateRoomOccupation(occupation, roomNum);
						JOptionPane.showMessageDialog(frontDeskView, "The guest has been checked in");
					}
				}catch(Exception exc){
					JOptionPane.showMessageDialog(frontDeskView, "this guest has already been checked in.");
				}
			}
		});
		checkInButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		checkInButton.setBounds(362, 295, 175, 31);
		checkInTab.add(checkInButton);
		
		lastTextIn = new JTextField();
		lastTextIn.setBackground(SystemColor.info);
		lastTextIn.setToolTipText("Enter guest's last name");
		lastTextIn.setBounds(47, 69, 136, 31);
		checkInTab.add(lastTextIn);
		lastTextIn.setColumns(10);
		
		lastNameInBtn = new JButton("Search by Name");
		lastNameInBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lastNameInBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String lastName = lastTextIn.getText();
					
					List<Stay> stays = null;
					
					if (lastName != null && lastName.trim().length() > 0){
						stays = stayDAO.searchStayByName(lastName);
					}else{
						stays = stayDAO.getAllStays();
					}
					
					StayTableModel model = new StayTableModel(stays);
					
					checkInTable.setModel(model);
					
					lastTextIn.setText("");
					
				}catch (Exception exc){
					//JOptionPane.showInputDialog(UserGUI.this);
				}
			}
		});
		lastNameInBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lastNameInBtn.setBounds(193, 69, 159, 31);
		checkInTab.add(lastNameInBtn);
		
		cancelReservation = new JButton("Cancel Reservation");
		cancelReservation.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cancelReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int row = checkInTable.getSelectedRow();
					
					if(row < 0){
						JOptionPane.showMessageDialog(frontDeskView, "you must select a reservation", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					int response = JOptionPane.showConfirmDialog(frontDeskView, "Delete this Reservation?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					
					if (response != JOptionPane.YES_OPTION){
						return;
					}
					
					Stay tempStay = (Stay) checkInTable.getValueAt(row, StayTableModel.OBJECT_COL);
					
					stayDAO.deleteStay(tempStay.getStayId());
					
					refreshStaysView();
				}catch(Exception exc){
					JOptionPane.showMessageDialog(frontDeskView, "Reservation deleted successfully.");
					
				}
			}
		});
		cancelReservation.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cancelReservation.setBounds(46, 295, 175, 31);
		checkInTab.add(cancelReservation);
		frontDeskView.setBackgroundAt(2, SystemColor.text);
		frontDeskView.setTitleAt(2, "Check-in");
		frontDeskView.add(checkOutTab);
		checkOutTab.setLayout(null);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(37, 94, 507, 246);
		checkOutTab.add(scrollPane_2);
		
		checkOutTable = new JTable();
		checkOutTable.setAutoCreateRowSorter(true);
		scrollPane_2.setViewportView(checkOutTable);
		
		checkOutBtn = new JButton("Check-out and Pay");
		checkOutBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		checkOutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int row = checkOutTable.getSelectedRow();
					
					if(row < 0){
						JOptionPane.showMessageDialog(frontDeskView, "you must select a reservation");
					}else{
						Stay tempStay = (Stay) checkOutTable.getValueAt(row, StayTableModel.OBJECT_COL);
						int stayId = tempStay.getStayId();
						int roomNum = tempStay.getRoomNum();
						String transaction = new String("true");
						String occupation = new String("needsClean");
						roomDAO.updateRoomOccupation(occupation, roomNum);
						billDAO.updateBillTransaction(transaction, stayId);
						JOptionPane.showMessageDialog(frontDeskView, "The guest has been checked out.");
						refreshRoomsView();	
					}
				}catch(Exception exc){
					JOptionPane.showMessageDialog(frontDeskView, "This guest's bill had ben dropped my management.");
				}
			}
		});
		checkOutBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		checkOutBtn.setBounds(189, 351, 181, 28);
		checkOutTab.add(checkOutBtn);
		
		viewAllReservationsOut = new JButton("View All Reservations");
		viewAllReservationsOut.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		viewAllReservationsOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String tempFalse = new String("false");
					List<Stay> stays = stayDAO.searchCheckedIn(tempFalse);
					StayTableModel model = new StayTableModel(stays);
					checkOutTable.setModel(model);
				}catch(Exception exc){
					
				}
			}
		});
		viewAllReservationsOut.setFont(new Font("Tahoma", Font.PLAIN, 14));
		viewAllReservationsOut.setBounds(362, 55, 182, 28);
		checkOutTab.add(viewAllReservationsOut);
		frontDeskView.setBackgroundAt(3, SystemColor.text);
		frontDeskView.setTitleAt(3, "Check-out");
		frontDeskView.add(viewBillTab);
		viewBillTab.setLayout(null);
		
		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(54, 97, 478, 186);
		viewBillTab.add(scrollPane_3);
		
		frontBillTable = new JTable();
		frontBillTable.setAutoCreateRowSorter(true);
		scrollPane_3.setViewportView(frontBillTable);
		
		viewAllBillFront = new JButton("View All Bills");
		viewAllBillFront.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		viewAllBillFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					List<Bill> bills = billDAO.getAllBills();
					if(bills.isEmpty()){
						JOptionPane.showMessageDialog(frontDeskView, "currently, there are no bills.");
					}else{
						BillTableModel model = new BillTableModel(bills);
						frontBillTable.setModel(model);
					}
				}catch(Exception exc){
					
				}
			}
		});
		viewAllBillFront.setFont(new Font("Tahoma", Font.PLAIN, 14));
		viewAllBillFront.setBounds(369, 60, 163, 30);
		viewBillTab.add(viewAllBillFront);
		frontDeskView.setBackgroundAt(4, SystemColor.text);
		frontDeskView.setTitleAt(4, "View Bill");
		frontDeskView.add(logoutTab1);
		frontDeskView.setBackgroundAt(5, SystemColor.text);
		
		JLabel lblNewLabel_1 = new JLabel("Are you sure you would like to log out?");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNewLabel_1.setBounds(48, 107, 478, 56);
		logoutTab1.add(lblNewLabel_1);
		frontDeskView.setTitleAt(5, "Logout");
		
		//creation of the maintenanceView and it's components
		maintenanceView = new JTabbedPane();
		homeTab2 = new JPanel();
		homeTab2.setBackground(SystemColor.activeCaption);
		viewOccupationTab = new JPanel();
		viewOccupationTab.setBackground(SystemColor.activeCaption);
		logoutTab2 = new JPanel();
		logoutTab2.setBackground(SystemColor.activeCaption);
		logoutTab2.setLayout(null);
		logOut2 = new JButton();
		logOut2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		logOut2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homeTab2.remove(welcome2);
				DefaultTableModel empty = new DefaultTableModel();
				maintenenceRoomView.setModel(empty);
				card.show(baseLayer, "1");
			}
		});
		logOut2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		logOut2.setText("confirm");
		logOut2.setBounds(238, 196, 123, 56);
		logoutTab2.add(logOut2);
		maintenanceView.add(homeTab2);
		homeTab2.setLayout(null);
		
		backToManagerView2 = new JButton("Back to Manager View");
		backToManagerView2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(baseLayer, "4");
			}
		});
		backToManagerView2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		backToManagerView2.setBounds(165, 328, 261, 36);
		maintenanceView.setBackgroundAt(0, SystemColor.text);
		maintenanceView.setTitleAt(0, "Home");
		maintenanceView.add(viewOccupationTab);
		viewOccupationTab.setLayout(null);
		
		scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(10, 91, 569, 260);
		viewOccupationTab.add(scrollPane_4);
		
		maintenenceRoomView = new JTable();
		maintenenceRoomView.setAutoCreateRowSorter(true);
		scrollPane_4.setViewportView(maintenenceRoomView);
		
		maintenanceRoomBtn = new JButton("View All Rooms");
		maintenanceRoomBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		maintenanceRoomBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					List<Room> rooms = roomDAO.getAllRooms();
					RoomTableModel model = new RoomTableModel(rooms);
					maintenenceRoomView.setModel(model);
				}catch(Exception exc){
					
				}
			}
		});
		maintenanceRoomBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		maintenanceRoomBtn.setBounds(10, 57, 174, 29);
		viewOccupationTab.add(maintenanceRoomBtn);
		
		cleanRoomBtn = new JButton("Clean Room");
		cleanRoomBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cleanRoomBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int row = maintenenceRoomView.getSelectedRow();
					
					if(row < 0){
						JOptionPane.showMessageDialog(maintenanceView, "You must first select a room.");
					}else{
						Room tempRoom = (Room) maintenenceRoomView.getValueAt(row, RoomTableModel.OBJECT_COL);
						int roomNum = tempRoom.getRoomNumber();
						String clean = new String("notOccupied");
						roomDAO.updateRoomOccupation(clean, roomNum);	
						JOptionPane.showMessageDialog(maintenanceView, "The room has been cleaned.");
						refreshMaintenanceRooms();
					}
				}catch(Exception exc){
					
				}
			}
		});
		cleanRoomBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cleanRoomBtn.setBounds(393, 57, 186, 29);
		viewOccupationTab.add(cleanRoomBtn);
		
		modifyAvailableText = new JTextField();
		modifyAvailableText.setBackground(SystemColor.info);
		modifyAvailableText.setBounds(10, 355, 211, 29);
		viewOccupationTab.add(modifyAvailableText);
		modifyAvailableText.setColumns(10);
		
		btnModifyAvail = new JButton("Modify Availability");
		btnModifyAvail.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnModifyAvail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int row = maintenenceRoomView.getSelectedRow();
					
					if(row < 0){
						JOptionPane.showMessageDialog(maintenanceView, "You must first select a room to modify.");
						
					}else{
						Room tempRoom = (Room) maintenenceRoomView.getValueAt(row, RoomTableModel.OBJECT_COL);
						int roomNum = tempRoom.getRoomNumber();
						String reason = modifyAvailableText.getText();
						if (reason != null && reason.trim().length() > 0){
							roomDAO.modifyRoomAvailability(reason, roomNum);
							JOptionPane.showMessageDialog(maintenanceView, "The room's availibility has been changed accordingly.");
							refreshMaintenanceRooms();
							modifyAvailableText.setText("");
						}else{
							JOptionPane.showMessageDialog(maintenanceView, "You must enter an update into the text field.");
						}
					}
				}catch(Exception exc){
					
				}
			}
		});
		btnModifyAvail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnModifyAvail.setBounds(228, 355, 199, 29);
		viewOccupationTab.add(btnModifyAvail);
		maintenanceView.setBackgroundAt(1, SystemColor.text);
		maintenanceView.setTitleAt(1, "View Occupation");
		maintenanceView.add(logoutTab2);
		maintenanceView.setBackgroundAt(2, SystemColor.text);
		maintenanceView.setTitleAt(2, "Logout");
		
		JLabel lblAreYouSure = new JLabel("Are you sure you would like to logout?");
		lblAreYouSure.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblAreYouSure.setHorizontalAlignment(SwingConstants.CENTER);
		lblAreYouSure.setBounds(10, 96, 569, 56);
		logoutTab2.add(lblAreYouSure);
		
		
		//creation of the managerView and it's contents
		managerView = new JTabbedPane();
		homeTab3 = new JPanel();
		homeTab3.setBackground(SystemColor.activeCaption);
		modifyBillTab = new JPanel();
		modifyBillTab.setBackground(SystemColor.activeCaption);
		managerView.add(homeTab3);
		homeTab3.setLayout(null);
		managerView.setTitleAt(0, "Home");
		managerView.add(modifyBillTab);
		modifyBillTab.setLayout(null);
		
		scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(10, 83, 569, 133);
		modifyBillTab.add(scrollPane_5);
		
		managerBillTable = new JTable();
		scrollPane_5.setViewportView(managerBillTable);
		
		btnNewButton = new JButton("View Bills");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					List<Bill> bills = billDAO.getAllBills();
					if(bills.isEmpty()){
						JOptionPane.showMessageDialog(managerView, "currently, there are no bills.");
					}else{
						BillTableModel model = new BillTableModel(bills);
						managerBillTable.setModel(model);
					}
				}catch(Exception exc){
					
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(10, 47, 153, 31);
		modifyBillTab.add(btnNewButton);
		
		totalText = new JTextField();
		totalText.setBackground(SystemColor.info);
		totalText.setBounds(227, 223, 176, 31);
		modifyBillTab.add(totalText);
		totalText.setColumns(10);
		
		btnModifyTotal = new JButton("Modify Total");
		btnModifyTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int row = managerBillTable.getSelectedRow();
					
					if(row < 0){
						JOptionPane.showMessageDialog(managerView, "You must first select a bill to edit.", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					int tempTotal = Integer.parseInt(totalText.getText());
					Bill tempBill = (Bill) managerBillTable.getValueAt(row, BillTableModel.OBJECT_COL);
					
					int billId = tempBill.getBillId();
					
					billDAO.updateBillTotal(tempTotal, billId);
					
					JOptionPane.showMessageDialog(managerView, "the bill's total has been updated");
					totalText.setText("");
					refreshManagerBillView();
				}catch(Exception exc){
					
				}
			}
		});
		btnModifyTotal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnModifyTotal.setBounds(412, 223, 167, 31);
		modifyBillTab.add(btnModifyTotal);
		
		btnDropCharge = new JButton("Drop Charge");
		btnDropCharge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int row = managerBillTable.getSelectedRow();
					
					if(row < 0){
						JOptionPane.showMessageDialog(frontDeskView, "you must select a bill", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					int response = JOptionPane.showConfirmDialog(managerView, "Drop this charge?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					
					if (response != JOptionPane.YES_OPTION){
						return;
					}
					
					Bill tempBill = (Bill) managerBillTable.getValueAt(row, BillTableModel.OBJECT_COL);
					
					billDAO.deleteBill(tempBill.getBillId());
					
					refreshManagerBillView();
				}catch(Exception exc){
					JOptionPane.showMessageDialog(managerView, "Bill deleted successfully.");	
				}
			}
		});
		btnDropCharge.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDropCharge.setBounds(426, 47, 153, 31);
		modifyBillTab.add(btnDropCharge);
		managerView.setTitleAt(1, "Modify Bill");
		
		
		
		
		manageStaffTab = new JPanel();
		manageStaffTab.setBackground(SystemColor.activeCaption);
		userLastNameSearch = new JTextField();
		userLastNameSearch.setToolTipText("enter user's last name");
		userLastNameSearch.setBackground(SystemColor.info);
		userLastNameSearch.setSize(155, 29);
		userLastNameSearch.setLocation(10, 11);
		userSearch = new JButton();
		userSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String lastName = userLastNameSearch.getText();
					
					List<User> users = null;
					
					if (lastName != null && lastName.trim().length() > 0){
						users = userDAO.searchUsers(lastName);
					}else{
						users = userDAO.getAllUsers();
					}
					
					UserTableModel model = new UserTableModel(users);
					
					userTable.setModel(model);
					
					userLastNameSearch.setText("");
					
				}catch (Exception exc){
					//JOptionPane.showInputDialog(UserGUI.this);
				}
			}
		});
		userSearch.setText("Search");
		userSearch.setBackground(UIManager.getColor("Button.background"));
		userSearch.setBounds(169, 11, 132, 29);
		addUser = new JButton();
		addUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addUser.setText("Add User");
		addUser.setBounds(22, 403, 121, 29);
		addUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddUserDialog dialog = new AddUserDialog(UserGUI.this, userDAO);
				dialog.setVisible(true);
			}
		});
		manageStaffTab.setLayout(null);
		manageStaffTab.add(userLastNameSearch);
		manageStaffTab.add(userSearch);
		manageStaffTab.add(addUser);
		managerView.add(manageStaffTab);
		
		manageStaffScrollPane = new JScrollPane();
		manageStaffScrollPane.setForeground(SystemColor.window);
		manageStaffScrollPane.setBackground(SystemColor.window);
		manageStaffScrollPane.setBounds(10, 51, 569, 341);
		manageStaffTab.add(manageStaffScrollPane);
		
		userTable = new JTable();
		userTable.setAutoCreateRowSorter(true);
		userTable.setBackground(Color.WHITE);
		userTable.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		userTable.setGridColor(Color.GRAY);
		manageStaffScrollPane.setViewportView(userTable);
		
		updateUser = new JButton("Update User");
		updateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = userTable.getSelectedRow();
				
				if(row < 0){
					JOptionPane.showMessageDialog(managerView, "You must select a User");
					return;
				}
				
				User tempUser = (User) userTable.getValueAt(row, UserTableModel.OBJECT_COL);
				AddUserDialog dialog = new AddUserDialog(UserGUI.this, userDAO, tempUser, true);
				dialog.setVisible(true);
			}
		});
		updateUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		updateUser.setBounds(153, 403, 115, 28);
		manageStaffTab.add(updateUser);
		
		btnRemoveUser = new JButton("Remove User");
		btnRemoveUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int row = userTable.getSelectedRow();
					
					if(row < 0){
						JOptionPane.showMessageDialog(managerView, "you must select a user", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					int response = JOptionPane.showConfirmDialog(managerView, "Delete this User?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					
					if (response != JOptionPane.YES_OPTION){
						return;
					}
					
					User tempUser = (User) userTable.getValueAt(row, UserTableModel.OBJECT_COL);
					
					userDAO.deleteUser(tempUser.getId());
					
					refreshUsersView();
				}catch(Exception exc){
					JOptionPane.showMessageDialog(managerView, "user deleted successfully.");
					
				}
			}
		});
		btnRemoveUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRemoveUser.setBounds(278, 403, 155, 29);
		manageStaffTab.add(btnRemoveUser);
		managerView.setTitleAt(2, "Manage Staff");
		logoutTab3 = new JPanel();
		logoutTab3.setBackground(SystemColor.activeCaption);
		logoutTab3.setLayout(null);
		logOut3 = new JButton();
		logOut3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homeTab3.remove(welcome3);
				DefaultTableModel empty = new DefaultTableModel();
				userTable.setModel(empty);
				managerBillTable.setModel(empty);
				card.show(baseLayer, "1");
			}
		});
		logOut3.setText("confirm");
		logOut3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		logOut3.setBounds(212, 194, 146, 58);
		logoutTab3.add(logOut3);
		managerView.add(logoutTab3);
		
		JLabel lblNewLabel_2 = new JLabel("Are you sure you would like to log out?");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNewLabel_2.setBounds(10, 107, 555, 64);
		logoutTab3.add(lblNewLabel_2);
		managerView.setTitleAt(3, "Logout");
		
		
		//adding all components to the card
		baseLayer.add(loginScreen, "1");
		baseLayer.add(frontDeskView, "2");
		baseLayer.add(maintenanceView, "3");
		baseLayer.add(managerView, "4");
	    card.show(baseLayer, "1");
		
		
		//finalizing the frame
		windowFrame.getContentPane().add(baseLayer);
		windowFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		windowFrame.setSize(600,500);
		windowFrame.setLocationRelativeTo(null);
		windowFrame.setResizable(false);
		windowFrame.setVisible(true);
			
	}
	
	public void refreshUsersView() {
		try{
			List<User> users = userDAO.getAllUsers();
			UserTableModel model = new UserTableModel(users);
			userTable.setModel(model);
		}catch(Exception exc){
			//JOptionPane.showInputDialog(this);
		}
	}
	
	public void refreshGuestsView() {
		try{
			List<Guest> guests = guestDAO.getAllGuest();
			GuestTableModel model = new GuestTableModel(guests);
			guestTable.setModel(model);
		}catch(Exception exc){
			//JOptionPane.showInputDialog(this);
		}
	}

	public void refreshStaysView() {
		try{
			List<Stay> stays = stayDAO.getAllStays();
			StayTableModel model = new StayTableModel(stays);
			reserveRoomTable.setModel(model);
			checkInTable.setModel(model);
		}catch(Exception exc){
			
		}
		
	}
	
	public void refreshRoomsView(){
		try{
			List<Room> rooms = roomDAO.getAllRooms();
			RoomTableModel model = new RoomTableModel(rooms);
			reserveRoomTable.setModel(model);
		}catch(Exception exc){
			
		}
	}
	
	public void refreshMaintenanceRooms(){
		try{
			List<Room> rooms = roomDAO.getAllRooms();
			RoomTableModel model = new RoomTableModel(rooms);
			maintenenceRoomView.setModel(model);
			
		}catch(Exception exc){
			
		}
	}
	
	public void refreshManagerBillView(){
		try{
		List<Bill> bills = billDAO.getAllBills();
		BillTableModel model = new BillTableModel(bills);
		managerBillTable.setModel(model);
		}catch(Exception exc){
			
		}
	}
	
	
}
