import java.awt.EventQueue;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;

public class CustomerReservationsList {

	private JFrame frame;
	private ArrayList<String> customerReservations;

	public CustomerReservationsList(Flight inputFlight) throws IOException {
		initialize();
		if (Data.customerReservationsMap.get(inputFlight) == null) {
			JLabel lblEmpty = new JLabel("Empty");
			frame.getContentPane().add(lblEmpty, BorderLayout.CENTER);
			return;
		}
		else {
			customerReservations = Data.customerReservationsMap.get(inputFlight);
			customerReservations.add("yo");
			customerReservations.add("YOOOO");
			for (int i = 0; i < customerReservations.size(); i++) {
				System.out.println(customerReservations.get(i));
			}
		}
		
	} 

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerReservationsList window = new CustomerReservationsList();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}

	/**
	 * Create the application.
	 */
	public CustomerReservationsList() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 450, 300);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

}
