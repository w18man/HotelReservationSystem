import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class AddGuestDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField firstNameTextField;
	private JTextField lastNameTextField;
	private JTextField driversTextField;
	private JTextField addressTextField;
	private JTextField cardTextField;
	private GuestDAO guestDAO;
	private UserGUI userGUI;
	private Guest previousGuest = null;
	private boolean updateMode = false;
	private JTextField phoneTextField;
	
	public AddGuestDialog(UserGUI theUserGUI, GuestDAO theGuestDAO, Guest thePreviousGuest, boolean theUpdateMode){
		this();
		guestDAO = theGuestDAO;
		userGUI = theUserGUI;
		previousGuest = thePreviousGuest;
		updateMode = theUpdateMode;
		
		if(updateMode){
			setTitle("Update Guest");
			populateGui(previousGuest);
		}
	}
	
	private void populateGui(Guest theGuest){
		firstNameTextField.setText(theGuest.getFirstName());
		lastNameTextField.setText(theGuest.getLastName());
		driversTextField.setText(Integer.toString(theGuest.getDriversLicense()));
		phoneTextField.setText(Integer.toString(theGuest.getPhoneNumber()));
		addressTextField.setText(theGuest.getAddress());
		cardTextField.setText(Integer.toString(theGuest.getCardOnFile()));
	}
	
	
	public AddGuestDialog(UserGUI theUserGUI, GuestDAO theGuestDAO){
		this(theUserGUI, theGuestDAO, null, false); 
	}

	/**
	 * Create the dialog.
	 */
	public AddGuestDialog() {
		setTitle("Add Guest");
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.activeCaption);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblFirstName = new JLabel("First Name:");
			lblFirstName.setHorizontalAlignment(SwingConstants.RIGHT);
			lblFirstName.setBounds(11, 14, 88, 14);
			contentPanel.add(lblFirstName);
		}
		{
			firstNameTextField = new JTextField();
			firstNameTextField.setBackground(SystemColor.info);
			firstNameTextField.setBounds(109, 11, 320, 20);
			contentPanel.add(firstNameTextField);
			firstNameTextField.setColumns(10);
		}
		{
			JLabel lblLastName = new JLabel("Last Name:");
			lblLastName.setHorizontalAlignment(SwingConstants.RIGHT);
			lblLastName.setBounds(12, 40, 87, 14);
			contentPanel.add(lblLastName);
		}
		{
			lastNameTextField = new JTextField();
			lastNameTextField.setBackground(SystemColor.info);
			lastNameTextField.setBounds(109, 37, 320, 20);
			contentPanel.add(lastNameTextField);
			lastNameTextField.setColumns(10);
		}
		{
			JLabel lblUsername = new JLabel("Drivers license #:");
			lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
			lblUsername.setBounds(0, 66, 99, 14);
			contentPanel.add(lblUsername);
		}
		{
			driversTextField = new JTextField();
			driversTextField.setBackground(SystemColor.info);
			driversTextField.setBounds(109, 63, 320, 20);
			contentPanel.add(driversTextField);
			driversTextField.setColumns(10);
		}
		{
			JLabel lblPassword = new JLabel("Phone #:");
			lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
			lblPassword.setBounds(16, 92, 83, 14);
			contentPanel.add(lblPassword);
		}
		{
			phoneTextField = new JTextField();
			phoneTextField.setColumns(10);
			phoneTextField.setBackground(SystemColor.info);
			phoneTextField.setBounds(109, 89, 320, 20);
			contentPanel.add(phoneTextField);
		}
		{
			JLabel lblUserType = new JLabel("Address:");
			lblUserType.setHorizontalAlignment(SwingConstants.RIGHT);
			lblUserType.setBounds(13, 118, 86, 14);
			contentPanel.add(lblUserType);
		}
		{
			addressTextField = new JTextField();
			addressTextField.setBackground(SystemColor.info);
			addressTextField.setBounds(109, 115, 320, 20);
			contentPanel.add(addressTextField);
			addressTextField.setColumns(10);
		}
		{
			JLabel lblUserId = new JLabel("Card on File:");
			lblUserId.setHorizontalAlignment(SwingConstants.RIGHT);
			lblUserId.setBounds(26, 144, 73, 14);
			contentPanel.add(lblUserId);
		}
		{
			cardTextField = new JTextField();
			cardTextField.setBackground(SystemColor.info);
			cardTextField.setBounds(109, 141, 320, 20);
			contentPanel.add(cardTextField);
			cardTextField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(SystemColor.control);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Apply");
				okButton.setBackground(SystemColor.menu);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						saveGuest();
					}
				});
				okButton.setActionCommand("Apply");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		
		}
		
		protected void saveGuest(){
			String firstName = firstNameTextField.getText();
			String lastName = lastNameTextField.getText();
			int driversLicense = Integer.parseInt(driversTextField.getText());
			int phoneNumber = Integer.parseInt(phoneTextField.getText());
			String address = addressTextField.getText();
			int cardPayment = Integer.parseInt(cardTextField.getText());
			Guest tempGuest = null;
			
			if(updateMode){
				tempGuest = previousGuest;
				tempGuest.setFirstName(firstName);
				tempGuest.setLastName(lastName);
				tempGuest.setDriversLicense(driversLicense);
				tempGuest.setPhoneNumber(phoneNumber);
				tempGuest.setAddress(address);
				tempGuest.setCardOnFile(cardPayment);
			} else{
				tempGuest = new Guest(firstName, lastName, driversLicense, phoneNumber, address, cardPayment);
			}
			
			try{
				if(updateMode){
					guestDAO.updateGuest(tempGuest);
				}else{
					guestDAO.addGuest(tempGuest);
				}
				
				setVisible(false);
				dispose();
				userGUI.refreshGuestsView();
			}catch(Exception exc){
				//JOptionPane.show
			}
		}
	}
