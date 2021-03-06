import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class AUS_Welcome extends JFrame{
	
	JLabel lbl1;
	
	JButton btn1;
	JButton btn2;
	
	public AUS_Welcome() {
		
		super("Austin-Bergstrom International Airport (AUS)");
		
		setLayout(new GridLayout(3, 1));
		
		lbl1 = new JLabel("Welcome to Austin-Bergstrom International Airport's Information Screen!");
		
		btn1 = new JButton("Arrivals");
		btn1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					new AUS_Arrivals();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		btn2 = new JButton("Departures");
		btn2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					new AUS_Departures();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		add(lbl1);
		add(btn1);
		add(btn2);
		
		setSize(600, 300);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
}
