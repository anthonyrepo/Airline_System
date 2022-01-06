import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

public class AdminEditOrViewMenu {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminEditOrViewMenu window = new AdminEditOrViewMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AdminEditOrViewMenu() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JButton btnNewButton = new JButton("Edit flight information.");
		btnNewButton.setBounds(56, 80, 323, 35);
		
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				new AdminEditCancelSearchOrDropdownMenu();
			}
		});
		
		frame.getContentPane().add(btnNewButton);
		
		JButton btnViewReservations = new JButton("View reservations");
		btnViewReservations.setBounds(56, 136, 323, 35);
		
		btnViewReservations.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				new AdminViewSearchOrDropdownMenu();
			}
		});
		
		frame.getContentPane().add(btnViewReservations);
		
		JLabel lblWelcomeAdmin = new JLabel("Welcome, Admin.");
		lblWelcomeAdmin.setBounds(128, 33, 172, 26);
		frame.getContentPane().add(lblWelcomeAdmin);
	}
}
