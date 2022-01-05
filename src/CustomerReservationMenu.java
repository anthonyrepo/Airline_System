import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;

public class CustomerReservationMenu {

	private JFrame frame;
	private ArrayList<Flight> reservedFlights = new ArrayList<Flight>(); 

	public static void main(String[] args) {
		  
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerReservationMenu window = new CustomerReservationMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CustomerReservationMenu() throws IOException {
		initialize();
	}

	private void initialize() throws IOException {
				
		// GUI setup
		frame = new JFrame();
		frame.setBounds(50, 50, 791, 671);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);
				
		String airlineString = "";
		String st;
		
		File file = new File("Airlines_Info.txt");
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while ((st = br.readLine()) != null) {
			    airlineString = airlineString.concat(st + "\n");
			 }
			
			airlineString = airlineString.replaceAll("[ \t]", " ").substring(0, airlineString.length());
			airlineString = airlineString.replaceAll("Airline 1", "");
			airlineString = airlineString.replaceAll("Airline 2", "");
			airlineString = airlineString.replaceAll("Airline 3", "");
			airlineString = airlineString.replaceAll("\n" + "\n", "");
			
			
			StringTokenizer myAirlineStringTokenizer = new StringTokenizer(airlineString, "\n");
			List<String> airlineTokens = new ArrayList<String>();
			
			int counter = 0;
			while (myAirlineStringTokenizer.hasMoreTokens()) {
				airlineTokens.add(myAirlineStringTokenizer.nextToken());
				counter++;
			}
			
			List<Flight> flights = new ArrayList<Flight>();
			
			HashMap<JButton, Flight> reserveButtonFlightMap = new HashMap<JButton, Flight>(); 
			
			for (int i = 0; i < airlineTokens.size(); i++) {
				String flightDataString = airlineTokens.get(i);
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
				
				flights.add(thisFlight);	
			}
			
			int y = 21;
			for (int i = 0; i < flights.size(); i++) {
				
				int currentAirlineNo = 1;
				System.out.println(Data.currentAirline);
				if (Data.currentAirline == "Airline 1") currentAirlineNo = 1; else
				if (Data.currentAirline == "Airline 2") currentAirlineNo = 2; else
				if (Data.currentAirline == "Airline 3") currentAirlineNo = 3;
				System.out.println(currentAirlineNo);
				
				if (flights.get(i).airlineNo == currentAirlineNo) {
					JLabel lblNewLabel = new JLabel(flights.get(i).toString());
					lblNewLabel.setBounds(0, 21+y, 500, 26);
					frame.getContentPane().add(lblNewLabel);
				}
				
				JButton myNewButton = new JButton("Reserve");
				myNewButton.setBounds(350+25, 21+y, 100, 26);
				frame.getContentPane().add(myNewButton);
				
				if (!(flights.get(i).airlineNo == currentAirlineNo)) {
					myNewButton.setVisible(false);
				}
				
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setBounds(350+25, 21+y, 100, 26);
				frame.getContentPane().add(cancelButton);
				checkCurrentCustomerReservations();
				cancelButton.setVisible(false);
				
				
				Flight myFlight = flights.get(i);	
				myFlight.reservations = myFlight.reservations+1;
				myNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						customerReserveFlight(myFlight, Data.currentCustomer);
						myNewButton.setVisible(false);
						cancelButton.setVisible(true);
					}
				});
				y = y+21;
			}
			
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} 
		
		JButton btnSaveChanges = new JButton("Save Changes And Exit");
		btnSaveChanges.setBounds(284, 551, 178, 35);
		frame.getContentPane().add(btnSaveChanges);
		btnSaveChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveChanges();
				checkCurrentCustomerReservations();
				
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		
	}
	
	private void checkCurrentCustomerReservations() {
		File file = new File("Customer1Reservations.txt");
		if (Data.currentCustomer == "customer1") file = new File("Customer1Reservations.txt"); else
		if (Data.currentCustomer == "customer2") file = new File("Customer2Reservations.txt"); else
		if (Data.currentCustomer == "customer3") file = new File("Customer3Reservations.txt");
		
		String reservationsString = "";
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String st;
			try {
				while ((st = br.readLine()) != null) 
				    reservationsString = reservationsString + st + "\n";
			} catch (IOException e) {
				e.printStackTrace();
			}  
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		reservationsString = reservationsString.replaceAll(",", "");
		//System.out.println(reservationsString.substring(0,52));
		ArrayList<String> reservationsStringTokens = new ArrayList<String>();
		StringTokenizer reservationsStringTokenizer = new StringTokenizer(reservationsString, "\n");
		while(reservationsStringTokenizer.hasMoreTokens()) {
			reservationsStringTokens.add(reservationsStringTokenizer.nextToken());
		}
		for (int i = 0; i < reservationsStringTokens.size(); i++) {
			
			ArrayList<String> flightDataTokens = new ArrayList<String>();
			StringTokenizer flightDataTokenizer = new StringTokenizer(reservationsStringTokens.get(i), " ");
			while (flightDataTokenizer.hasMoreTokens()) {
		         flightDataTokens.add(flightDataTokenizer.nextToken());
		     }
		}
		
	}

	private void customerReserveFlight(Flight inputFlight, String customer) {
		if (Data.currentCustomer == "customer1" && !Data.customer1List.contains(inputFlight)) 
			Data.customer1List.add(inputFlight); else
		if (Data.currentCustomer == "customer2" && !Data.customer2List.contains(inputFlight)) 
			Data.customer2List.add(inputFlight); else
		if (Data.currentCustomer == "customer3" && !Data.customer3List.contains(inputFlight)) 
			Data.customer3List.add(inputFlight);
		
		if (Data.customerReservationsMap.get(inputFlight) == null) {
			ArrayList<String> tempArrayList = new ArrayList<String>();
			tempArrayList.add(Data.currentCustomer);
			Data.customerReservationsMap.put(inputFlight, tempArrayList);
		}
		else {
			if (!Data.customerReservationsMap.get(inputFlight).contains(Data.currentCustomer))
				Data.customerReservationsMap.get(inputFlight).add(Data.currentCustomer);
		}
		
	}
	
	private void saveChanges() {
		List<Flight> currentCustomerList = new ArrayList<Flight>();
		if (Data.currentCustomer == "customer1") currentCustomerList = Data.customer1List; else
		if (Data.currentCustomer == "customer2") currentCustomerList = Data.customer2List; else
		if (Data.currentCustomer == "customer3") currentCustomerList = Data.customer3List;
		
		String reservationsString = "";
		File file;
		
		file = new File("Customer1Reservations.txt");
		
		if (Data.currentCustomer == "customer1") file = new File("Customer1Reservations.txt"); else
		if (Data.currentCustomer == "customer2") file = new File("Customer2Reservations.txt"); else
		if (Data.currentCustomer == "customer3") file = new File("Customer3Reservations.txt");
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String stringRead;
			while((stringRead = br.readLine()) != null) {
				reservationsString = reservationsString + stringRead + "\n";
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String str = "";
		for (int i = 0; i < currentCustomerList.size(); i++) {
			if (!reservationsString.contains(currentCustomerList.get(i).toString())) {
				str = str.replaceAll("/", " ");
				str = str.replaceAll(",", "");
				str = str + currentCustomerList.get(i).toString()+"\n";
				str = str.replaceAll("/", " ");
				str = str.replaceAll(",", "");
			}
		}
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
			writer.append(str);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
		String airlineString = "";
		
		File file2 = new File("Airlines_Info.txt");
		try {
			BufferedReader br2 = new BufferedReader(new FileReader(file2));
			String st;
			airlineString = "";

			try {
				while ((st = br2.readLine()) != null) {
				    airlineString = airlineString.concat(st + "\n");
				 }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//TODO: Following 5 lines are new
			airlineString = airlineString.replaceAll("[ \t]", " ").substring(0, airlineString.length());
			airlineString = airlineString.replaceAll("Airline 1", "");
			airlineString = airlineString.replaceAll("Airline 2", "");
			airlineString = airlineString.replaceAll("Airline 3", "");
			airlineString = airlineString.replaceAll("\n" + "\n", "");

			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		
	
	}
	
}
