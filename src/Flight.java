
public class Flight {
	
	int flightNo;
	String flightFrom;
	String flightTo;
	String departTime;
	String arrivalTime;
	String date;
	int reservations;
	int capacity;
	int fare;
	int airlineNo;
	
	public Flight() {

	}
	
	public Flight (int inputFlightNo, String inputFlightFrom, String inputFlightTo, String inputDepartTime, 
			String inputArrivalTime, String inputDate, int inputReservations, int inputCapacity, int inputFare,
			int inputAirlineNo) {
		flightNo = inputFlightNo;
		flightFrom = inputFlightFrom;
		flightTo = inputFlightTo;
		departTime = inputDepartTime;
		arrivalTime = inputArrivalTime;
		date = inputDate;
		reservations = inputReservations;
		capacity = inputCapacity;
		fare = inputFare;
		airlineNo = inputAirlineNo;
	}
	
	public String toString() {
		String returnString = "";
		returnString = flightNo + ", "
				+ flightFrom + ", "
				+ flightTo + ", "
				+ departTime + ", "
				+ arrivalTime + ", "
				+ date + ", "
				+ reservations + ", "
				+ capacity + ", "
				+ fare + ", "
				+ airlineNo;

		return returnString;
	}
	
	
}
