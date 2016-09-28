import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.UIManager;


public class AddStayDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField roomNumberText;
	private JFormattedTextField dateInText;
	private JFormattedTextField dateOutText;
	private JTextField guestIdText;
	private StayDAO stayDAO;
	private UserGUI userGUI;
	private JTextField stayIdText;
	private SimpleDateFormat formatDate = new SimpleDateFormat("yyyymmdd"); 
	
	public AddStayDialog(UserGUI theUserGUI, StayDAO theStayDAO, int roomNum, int guestId){
		this();
		stayDAO = theStayDAO;
		userGUI = theUserGUI;
		populateGUI(roomNum, guestId);
	}
	
	private void populateGUI(int roomNum, int guestId){
		roomNumberText.setText(Integer.toString(roomNum));
		guestIdText.setText(Integer.toString(guestId));
	}
	
	
	


	/**
	 * Create the dialog.
	 */
	public AddStayDialog() {
		setTitle("Add Stay");
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.activeCaption);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblFirstName = new JLabel("Room #:");
			lblFirstName.setHorizontalAlignment(SwingConstants.RIGHT);
			lblFirstName.setBounds(11, 14, 108, 14);
			contentPanel.add(lblFirstName);
		}
		{
			roomNumberText = new JTextField();
			roomNumberText.setEditable(false);
			roomNumberText.setBackground(SystemColor.info);
			roomNumberText.setBounds(129, 11, 300, 20);
			contentPanel.add(roomNumberText);
			roomNumberText.setColumns(10);
		}
		{
			JLabel lblLastName = new JLabel("Check-in Date:");
			lblLastName.setHorizontalAlignment(SwingConstants.RIGHT);
			lblLastName.setBounds(12, 40, 107, 14);
			contentPanel.add(lblLastName);
		}
		{
			dateInText = new JFormattedTextField(formatDate);
			dateInText.setToolTipText("date format (YYYYMMDD)");
			dateInText.setFocusLostBehavior(JFormattedTextField.PERSIST);
			dateInText.setColumns(10);
			dateInText.setBackground(SystemColor.info);
			dateInText.setBounds(129, 37, 300, 20);
			contentPanel.add(dateInText);
			
		}
		{
			JLabel lblUsername = new JLabel("Check-out Date:");
			lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
			lblUsername.setBounds(0, 66, 119, 14);
			contentPanel.add(lblUsername);
		}
		{
			dateOutText = new JFormattedTextField(formatDate);
			dateOutText.setToolTipText("date format (YYYYMMDD)");
			dateOutText.setFocusLostBehavior(JFormattedTextField.PERSIST);
			dateOutText.setColumns(10);
			dateOutText.setBackground(SystemColor.info);
			dateOutText.setBounds(129, 63, 300, 20);
			contentPanel.add(dateOutText);
			
		}
		{
			JLabel lblPassword = new JLabel("Reservation Id:");
			lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
			lblPassword.setBounds(16, 92, 103, 14);
			contentPanel.add(lblPassword);
		}
		{
			stayIdText = new JTextField();
			stayIdText.setColumns(10);
			stayIdText.setBackground(SystemColor.info);
			stayIdText.setBounds(129, 89, 300, 20);
			contentPanel.add(stayIdText);
		}
		{
			JLabel lblUserType = new JLabel("Guest Id:");
			lblUserType.setHorizontalAlignment(SwingConstants.RIGHT);
			lblUserType.setBounds(13, 118, 106, 14);
			contentPanel.add(lblUserType);
		}
		{
			guestIdText = new JTextField();
			guestIdText.setEditable(false);
			guestIdText.setBackground(SystemColor.info);
			guestIdText.setBounds(129, 115, 300, 20);
			contentPanel.add(guestIdText);
			guestIdText.setColumns(10);
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
						try {
							saveStay();
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
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
		
		protected void saveStay()throws ParseException{
			int roomNum = Integer.parseInt(roomNumberText.getText());
			Date parsed = formatDate.parse(dateInText.getText());
			java.sql.Date dateIn = new java.sql.Date(parsed.getTime());
			Date parsed2 = formatDate.parse(dateOutText.getText());
			java.sql.Date dateOut = new java.sql.Date(parsed2.getTime());
			int stayId = Integer.parseInt(stayIdText.getText());
			int guestId = Integer.parseInt(guestIdText.getText());
			Stay tempStay = new Stay(roomNum, dateIn, dateOut, stayId, guestId);
			
			
			
			try{
				stayDAO.addStay(tempStay);
				setVisible(false);
				dispose();
				userGUI.refreshStaysView();
				
			}catch(Exception exc){
				JOptionPane.showMessageDialog(AddStayDialog.this, "reservation time conflicts with a pre-existing reservation");
			}
		}
	}
