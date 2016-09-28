import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class welcomeLabel extends JLabel {
		public welcomeLabel(String fname){
			setFont(new Font("Tahoma", Font.PLAIN, 30));
			setHorizontalAlignment(SwingConstants.CENTER);
			setText("Welcome, " + fname +"!");	
		}
}
