import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class AdminEditCancelMenu {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminEditCancelMenu window = new AdminEditCancelMenu();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AdminEditCancelMenu() throws IOException {
		initialize();
	}

	private void initialize() throws IOException {
		
		// GUI stuff
		JFrame frame;
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 753, 578);
		frame.getContentPane().setLayout(null);
		
		// Parsing Stuff
		//\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		String airlineString = "";
		
		File file = new File("Airlines_Info.txt");
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String st;
			airlineString = "";

			while ((st = br.readLine()) != null) {
			    airlineString = airlineString.concat(st + "\n");
			 }
			//TODO: Following 5 lines are new
			airlineString = airlineString.replaceAll("[ \t]", " ").substring(0, airlineString.length());
			airlineString = airlineString.replaceAll("Airline 1", "");
			airlineString = airlineString.replaceAll("Airline 2", "");
			airlineString = airlineString.replaceAll("Airline 3", "");
			airlineString = airlineString.replaceAll("\n" + "\n", "");

			System.out.println(airlineString);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		ArrayList<String> airlineStringTokens = new ArrayList<String>();
		StringTokenizer airlineStringTokenizer = new StringTokenizer(airlineString, "\n");
		while(airlineStringTokenizer.hasMoreTokens()) {
			airlineStringTokens.add(airlineStringTokenizer.nextToken());
		}
		
		HashMap<JButton, Flight> editButtonFlightMap = new HashMap<JButton, Flight>(); 
		HashMap<JButton, Flight> cancelButtonFlightMap = new HashMap<JButton, Flight>();
 		
		int y = -100;
		for (int i = 0; i < airlineStringTokens.size(); i++) {
			JLabel lblAirlineInfo = new JLabel(airlineStringTokens.get(i));
			lblAirlineInfo.setBounds(0, y, 424, 229);
			frame.getContentPane().add(lblAirlineInfo);
			y = y+24;
			
			String flightDataString = airlineStringTokens.get(i);
			StringTokenizer myFlightDataStringTokenizer = new StringTokenizer(flightDataString, " ");
			List<String> flightDataTokens = new ArrayList<String>();
			while (myFlightDataStringTokenizer.hasMoreTokens()) {
				flightDataTokens.add(myFlightDataStringTokenizer.nextToken());
			}
			
			Flight thisFlight =  new Flight(Integer.parseInt(flightDataTokens.get(0)),
					flightDataTokens.get(1),
					flightDataTokens.get(2),
					flightDataTokens.get(3),
					flightDataTokens.get(4),
					flightDataTokens.get(5),
					Integer.parseInt(flightDataTokens.get(6).substring(0,flightDataTokens.get(6).indexOf("/"))),
					Integer.parseInt(flightDataTokens.get(6).substring(flightDataTokens.get(6).indexOf("/")+1)),
					Integer.parseInt(flightDataTokens.get(7)),
					Integer.parseInt(flightDataTokens.get(8))
					);
			//\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
			JButton myNewButton = new JButton("Edit");
			myNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						new AdminEditFlightMenu(thisFlight);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
			myNewButton.setBounds(340, y+80, 90, 23);
			frame.getContentPane().add(myNewButton);
			
			JButton myNewButton2 = new JButton("Cancel");
			myNewButton2.setBounds(340+90, y+80, 90, 23);
			myNewButton2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					removeFlight(thisFlight);
				}
			});
				
			frame.getContentPane().add(myNewButton2);			
			
			JButton btnSaveChanges = new JButton("Save Changes");
			btnSaveChanges.setBounds(184, 500, 178, 35);
			btnSaveChanges.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					saveChanges();
				}
			});
			frame.getContentPane().add(btnSaveChanges);
			
			JButton btnAddNewFlight = new JButton("Make New Flight");
			btnAddNewFlight.setBounds(184, 450, 178, 35);
			btnAddNewFlight.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						new AdminEditFlightMenu(new Flight());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
			frame.getContentPane().add(btnAddNewFlight);
			
		}
	}
	
	private void removeFlight(Flight thisFlight) {
		System.out.println("Removing: " + thisFlight.toString());
		if (Data.currentCustomer == "customer1") Data.customer1List.remove(thisFlight); else
		if (Data.currentCustomer == "customer2") Data.customer2List.remove(thisFlight); else
		if (Data.currentCustomer == "customer3") Data.customer3List.remove(thisFlight);
		Data.customerReservationsMap.remove(thisFlight);	
	}
	
	private void saveChanges() {
		String airlineString = "";
		
		File file = new File("Airlines_Info.txt");
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String st;
			airlineString = "";
			try {
				while ((st = br.readLine()) != null) {
				    airlineString = airlineString.concat(st + "\n");
				 }
			} catch (IOException e) {
				e.printStackTrace();
			}
			airlineString = airlineString.replaceAll("[ \t]", " ").substring(0, airlineString.length());
			airlineString = airlineString.replaceAll("Airline 1", "");
			airlineString = airlineString.replaceAll("Airline 2", "");
			airlineString = airlineString.replaceAll("Airline 3", "");
			airlineString = airlineString.replaceAll("\n" + "\n", "");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		String writeToFileString = ""; 
		for (Map.Entry<Integer, Flight> entry : Data.allFlightsMap.entrySet()) {
			String checkString = entry.getValue().toString().replaceAll(",", " ");
			System.out.println(checkString);
			System.out.println(airlineString.substring(0, 50));
			if (!airlineString.contains(checkString))
				System.out.println("airlineString doesnt contain checkString");
				//writeToFileString = "\n" + entry.getValue().toString();
		}
		
		System.out.println(writeToFileString);
		
		
	}

}
