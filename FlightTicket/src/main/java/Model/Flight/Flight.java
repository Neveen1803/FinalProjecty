package Model.Flight;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;

import Dbconnection.DBConnection;
import Model.User.User;
import Model.dbconnection.dbconnnection;

public class Flight {
	String FlightName;


public Flight( String flightNumber,String flightName, String departure, String arrival, Time departure_TIme,Time arrival_Time, Seat seats, double price, User last_modified_by) {
		super();
		FlightName = flightName;
		FlightNumber = flightNumber;
		Departure = departure;
		Arrival = arrival;
		Departure_TIme = departure_TIme;
		Arrival_Time = arrival_Time;
		this.seats = seats;
		Price = price;
		this.last_modified_by = last_modified_by;
	}
public Flight() {
	// TODO Auto-generated constructor stub
}
String FlightNumber;
	String Departure;
	String Arrival;
	Time Departure_TIme;
	Time Arrival_Time;
	Seat seats;
	double Price;
	User last_modified_by;
	
	
	
	
	
	
	public User getLast_modified_by() {
		return last_modified_by;
	}
	public void setLast_modified_by(User last_modified_by) {
		this.last_modified_by = last_modified_by;
	}
	public String getFlightName() {
		return FlightName;
	}
	public void setFlightName(String flightName) {
		FlightName = flightName;
	}
	public String getFlightNumber() {
		return FlightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		FlightNumber = flightNumber;
	}
	public String getDeparture() {
		return Departure;
	}
	public void setDeparture(String departure) {
		Departure = departure;
	}
	public String getArrival() {
		return Arrival;
	}
	public void setArrival(String arrival) {
		Arrival = arrival;
	}
	public Time getDeparture_TIme() {
		return Departure_TIme;
	}
	public void setDeparture_TIme(Time departure_TIme) {
		Departure_TIme = departure_TIme;
	}
	public Time getArrival_Time() {
		return Arrival_Time;
	}
	public void setArrival_Time(Time arrival_Time) {
		Arrival_Time = arrival_Time;
	}

	public Seat getSeats() {
		return seats;
	}
	public void setSeats(Seat seats) {
		this.seats = seats;
	}
	public double getPrice() {
		return Price;
	}
	public void setPrice(double price) {
		Price = price;
	}
	
	
	public List<Flight> flightDbconnection() {
		List<Flight> flightarr=new ArrayList<Flight>();
		Flight flight=null;
		Connection con=dbconnnection.getDBConnection();
		try {
			PreparedStatement psmt=con.prepareStatement("select * from Flights where  Departure_city =? and Arrival_city=?");
			psmt.setString(1, Departure);
			psmt.setString(2, Arrival);
			ResultSet rs= psmt.executeQuery();
			while(rs.next()) {
				User user=new User();
				Seat seat=new Seat();
				seat.setCapacity(rs.getString(7));
				user.setName(rs.getString(9));
				flight=new Flight(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getTime(5), rs.getTime(6), seat, rs.getDouble(8), user.GetUserWithUserName());
				flightarr.add(flight);
			}
	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flightarr;
	}
	
	public void InsertFlight() {
		Connection con=dbconnnection.getDBConnection();
		try {
			PreparedStatement psmt=con.prepareStatement("Insert into Flights (Flight_Id,Flight_Name,Departure_city,Arrival_city,Departure_time,Arrival_time,Flight_Seat_Capacity,Price,last_modified_by) values (?,?,?,?,?,?,?,?,?)");
			psmt.setString(1, FlightNumber);
			psmt.setString(2,FlightName);
			psmt.setString(3,Departure);
			psmt.setString(4,Arrival);
			psmt.setTime(5,Departure_TIme);
			psmt.setTime(6, Arrival_Time);
			psmt.setString(7, seats.getCapacity());
			psmt.setDouble(8, Price);
			psmt.setString(9, "Obadiah");
			psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public List<Flight> showAvailableFlight(){
		List<Flight> flightarr=new ArrayList<Flight>();
		Flight flight=null;
		Connection con=dbconnnection.getDBConnection();
		try {
			Statement smt=con.createStatement();
			ResultSet rs=smt.executeQuery("Select * from Flights");
			while(rs.next()) {
				User user=new User();
				user.setName(rs.getString(9));
				Seat seat=new Seat();
				seat.setCapacity(rs.getString(7));
				//String flightName, String flightNumber, String departure, String arrival, Time departure_TIme,Time arrival_Time, Seat seats, double price, User last_modified_by
				flight=new Flight(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getTime(5), rs.getTime(6),seat, rs.getDouble(8), user.GetUserWithUserName());
				flightarr.add(flight);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flightarr;
	}
	
	
	public String GetFlightNumberFromDb() {
		Connection con=dbconnnection.getDBConnection();
		String number=null;
		try {
			PreparedStatement psmt=con.prepareStatement("select Flight_Id from Flights where  Departure_city =? and Arrival_city=? and Departure_time =? and Arrival_time=? ");
			psmt.setString(1,Departure);
			psmt.setString(2,Arrival);
			psmt.setTime(3,Departure_TIme);
			psmt.setTime(4, Arrival_Time);
			ResultSet rs=psmt.executeQuery();
			while(rs.next()) {
				number=rs.getString(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return number;
	}
	
	public Flight getFlightDetailsWithFlightId() {
		Flight flight=null;
		Connection con=dbconnnection.getDBConnection();
		try {
			PreparedStatement psmt=con.prepareStatement("select * from Flights where  Flight_Id=?");
			psmt.setString(1, FlightNumber);
			ResultSet rs= psmt.executeQuery();
			while(rs.next()) {
				User user=new User();
				user.setName(rs.getString(9));
				Seat seat=new Seat();
				seat.setCapacity(rs.getString(7));
				flight=new Flight(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getTime(5), rs.getTime(6), seat, rs.getDouble(8), user.GetUserWithUserName());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flight;
	}
	public void updateDeparture() {
		Connection con=dbconnnection.getDBConnection();
		try {
			PreparedStatement psmt=con.prepareStatement("Update Flights set Departure_city=? where Flight_Id=?");
			psmt.setString(1, Departure);
			psmt.setString(2, FlightNumber);
			int a=psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
	public void updateArrival() {
		Connection con=dbconnnection.getDBConnection();
		try {
			PreparedStatement psmt=con.prepareStatement("Update Flights set Arrival_city=? where Flight_Id=?");
			psmt.setString(1, Arrival);
			psmt.setString(2, FlightNumber);
			int a=psmt.executeUpdate();

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	public void updateDepartureTime() {
		Connection con=dbconnnection.getDBConnection();
		try {
			PreparedStatement psmt=con.prepareStatement("Update Flights set Departure_time=? where Flight_Id=?");
			psmt.setTime(1, Departure_TIme);
			psmt.setString(2, FlightNumber);
			int a=psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	public void updateArrivalTime() {
		Connection con=dbconnnection.getDBConnection();
		try {
			PreparedStatement psmt=con.prepareStatement("Update Flights set Arrival_time=? where Flight_Id=?");
			psmt.setTime(1,Arrival_Time);
			psmt.setString(2, FlightNumber);
			int a=psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	public void updatePrice() {
		Connection con=dbconnnection.getDBConnection();
		System.out.println("updatePrice");
		try {
			PreparedStatement psmt=con.prepareStatement("Update Flights set Price=? where Flight_Id=?");
			psmt.setDouble(1,Price);
			psmt.setString(2, FlightNumber);
			int a=psmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	public void updateFlightCapacity() {
		Connection con=dbconnnection.getDBConnection();
		System.out.println("updateFlightCapacity ");
		try {
			PreparedStatement psmt=con.prepareStatement("Update Flights set Flight_Seat_Capacity=? where Flight_Id=?");
			psmt.setString(1,seats.getCapacity());
			psmt.setString(2, FlightNumber);
			int a=psmt.executeUpdate();
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	
		
	
}
