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

public class AddUserDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField firstNameTextField;
	private JTextField lastNameTextField;
	private JTextField usernameTextField;
	private JTextField passwordTextField;
	private JTextField userTypeTextField;
	private JTextField userIDTextField;
	private UserDAO userDAO;
	private UserGUI userGUI;
	private User previousUser = null;
	private boolean updateMode = false;
	
	public AddUserDialog(UserGUI theUserGUI, UserDAO theUserDAO, User thePreviousUser, boolean theUpdateMode){
		this();
		userDAO = theUserDAO;
		userGUI = theUserGUI;
		previousUser = thePreviousUser;
		updateMode = theUpdateMode;
		
		if(updateMode){
			setTitle("Update User");
			populateGui(previousUser);
		}
	}
	
	private void populateGui(User theUser){
		firstNameTextField.setText(theUser.getFirstName());
		lastNameTextField.setText(theUser.getLastName());
		usernameTextField.setText(theUser.getUserName());
		passwordTextField.setText(theUser.getPassWord());
		userTypeTextField.setText(theUser.getUserType());
		userIDTextField.setText(Integer.toString(theUser.getId()));
	}
	
	
	public AddUserDialog(UserGUI theUserGUI, UserDAO theUserDAO){
		this(theUserGUI, theUserDAO, null, false); 
	}

	/**
	 * Create the dialog.
	 */
	public AddUserDialog() {
		setTitle("Add User");
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 434, 228);
		contentPanel.setBackground(SystemColor.activeCaption);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
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
			JLabel lblUsername = new JLabel("Username:");
			lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
			lblUsername.setBounds(14, 66, 85, 14);
			contentPanel.add(lblUsername);
		}
		{
			usernameTextField = new JTextField();
			usernameTextField.setBackground(SystemColor.info);
			usernameTextField.setBounds(109, 63, 320, 20);
			contentPanel.add(usernameTextField);
			usernameTextField.setColumns(10);
		}
		{
			JLabel lblPassword = new JLabel("Password:");
			lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
			lblPassword.setBounds(16, 92, 83, 14);
			contentPanel.add(lblPassword);
		}
		{
			passwordTextField = new JTextField();
			passwordTextField.setBackground(SystemColor.info);
			passwordTextField.setBounds(109, 89, 320, 20);
			contentPanel.add(passwordTextField);
			passwordTextField.setColumns(10);
		}
		{
			JLabel lblUserType = new JLabel("User Type:");
			lblUserType.setHorizontalAlignment(SwingConstants.RIGHT);
			lblUserType.setBounds(13, 118, 86, 14);
			contentPanel.add(lblUserType);
		}
		{
			userTypeTextField = new JTextField();
			userTypeTextField.setBackground(SystemColor.info);
			userTypeTextField.setBounds(109, 115, 320, 20);
			contentPanel.add(userTypeTextField);
			userTypeTextField.setColumns(10);
		}
		{
			JLabel lblUserId = new JLabel("User ID:");
			lblUserId.setHorizontalAlignment(SwingConstants.RIGHT);
			lblUserId.setBounds(26, 144, 73, 14);
			contentPanel.add(lblUserId);
		}
		{
			userIDTextField = new JTextField();
			userIDTextField.setBackground(SystemColor.info);
			userIDTextField.setBounds(109, 141, 320, 20);
			contentPanel.add(userIDTextField);
			userIDTextField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 228, 434, 33);
			buttonPane.setBackground(SystemColor.control);
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("Apply");
				okButton.setBounds(272, 5, 87, 23);
				okButton.setBackground(SystemColor.menu);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						saveUser();
					}
				});
				buttonPane.setLayout(null);
				okButton.setActionCommand("Apply");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setBounds(364, 5, 65, 23);
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
		
		protected void saveUser(){
			String firstName = firstNameTextField.getText();
			String lastName = lastNameTextField.getText();
			String userName = usernameTextField.getText();
			String passWord = passwordTextField.getText();
			String userType = userTypeTextField.getText();
			int userID = Integer.parseInt(userIDTextField.getText());
			User tempUser = null;
			
			if(updateMode){
				tempUser = previousUser;
				tempUser.setFirstName(firstName);
				tempUser.setLastName(lastName);
				tempUser.setUserName(userName);
				tempUser.setPassWord(passWord);
				tempUser.setUserType(userType);
				tempUser.setId(userID);	
			} else{
				tempUser = new User(firstName, lastName, userName, passWord, userType, userID);
			}
			
			try{
				if(updateMode){
					userDAO.updateUser(tempUser);
				}else{
					userDAO.addUser(tempUser);
				}
				
				setVisible(false);
				dispose();
				userGUI.refreshUsersView();
			}
			//User tempUser = new User(firstName, lastName, userName, passWord, userType, userID );
			/*try{
				userDAO.addUser(tempUser);
				setVisible(false);
				dispose();
				userGUI.refreshUsersView();
				
				JOptionPane.showInputDialog(userGUI);
			}*/catch(Exception exc){
				//JOptionPane.showInputDialog(userGUI);
			}
		}
	}


