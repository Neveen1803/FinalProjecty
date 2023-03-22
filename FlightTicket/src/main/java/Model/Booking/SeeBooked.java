package Model.Booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.exceptions.ClosedOnExpiredPasswordException;

import Controller.Flight.PassengerDetails;
import Model.Flight.Flight;
import Model.User.Passenger;
import Model.User.User;
import Model.dbconnection.dbconnnection;

public class SeeBooked {
	Flight flight;
	Passenger passenger;
	BookingSeat booking;
	User user;
	public SeeBooked(Flight flight, Passenger passenger, BookingSeat booking, User user) {
		super();
		this.flight = flight;
		this.passenger = passenger;
		this.booking = booking;
		this.user = user;
	}

public SeeBooked() {
	// TODO Auto-generated constructor stub
}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Flight getFlight() {
		return flight;
	}
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	public Passenger getPassenger() {
		return passenger;
	}
	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}
	public BookingSeat getBooking() {
		return booking;
	}
	public void setBooking(BookingSeat booking) {
		this.booking = booking;
	}
	public List<SeeBooked> SeeBookingFromDB() {
		Connection con=dbconnnection.getDBConnection();
		
		List<SeeBooked> SeeBookedList=new ArrayList<SeeBooked>();
		try {
			Statement smt=con.createStatement();
			ResultSet rs=smt.executeQuery("select P.FirstName,P.LastName,P.MobileNumber,P.SelectedSeat,F.Flight_Id,B.DeparturDate,B.ArrivalDate,F.Flight_Name,F.Departure_city,F.Arrival_city,F.Departure_time,F.Arrival_time,U.User_Name,F.Price,B.MobileNumber from Passenger as P,Booking as B,Flights as F,User as U where B.BookingId=P.BookingId and B.Flight_Id=F.Flight_Id and U.MobileNumber=B.MobileNumber;");
			while(rs.next()) {
				Flight flightdetails=new Flight();
				Passenger passengerDetails=new Passenger();
				BookingSeat book=new BookingSeat();
				User userdetails=new User();
				passengerDetails.setFirstName(rs.getString(1));
				passengerDetails.setLastName(rs.getString(2));
				passengerDetails.setMobileNumber(rs.getString(3));
				passengerDetails.setSelectedSeat(rs.getString(4));
				flightdetails.setFlightNumber(rs.getString(5));
				book.setDeparturDate(rs.getDate(6));
				book.setArrivalDate(rs.getDate(7));
				flightdetails.setFlightName(rs.getString(8));
				flightdetails.setDeparture(rs.getString(9));
				flightdetails.setArrival(rs.getString(10));
				flightdetails.setDeparture_TIme(rs.getTime(11));
				flightdetails.setArrival_Time(rs.getTime(12));
				userdetails.setName(rs.getString(13));
				flightdetails.setPrice(rs.getDouble(14));
				book.setMobileNumber(rs.getString(15));
				
				SeeBooked sb=new SeeBooked(flightdetails, passengerDetails, book, userdetails);
				SeeBookedList.add(sb);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SeeBookedList;
	}
	public List<SeeBooked> ViewTickets(){
Connection con=dbconnnection.getDBConnection();
		
		List<SeeBooked> SeeBookedList=new ArrayList<SeeBooked>();
		try {
			PreparedStatement psmt=con.prepareStatement("select P.FirstName,P.LastName,P.MobileNumber,P.SelectedSeat,B.MobileNumber,B.Flight_Id,F.Flight_Name,B.DeparturDate,F.Departure_city,F.Arrival_city,F.Departure_Time,F.Arrival_Time from Passenger as P,Booking as B,Flights as F where B.BookingId=P.BookingId and B.MobileNumber=? and B.Flight_Id=F.Flight_Id");
			psmt.setString(1, user.getMobileNumber());
			ResultSet rs=psmt.executeQuery();
			while(rs.next()) {
				Flight flightdetails=new Flight();
				Passenger passengerDetails=new Passenger();
				BookingSeat book=new BookingSeat();
				User userdetails=new User();
				passengerDetails.setFirstName(rs.getString(1));
				passengerDetails.setLastName(rs.getString(2));
				passengerDetails.setMobileNumber(rs.getString(3));
				passengerDetails.setSelectedSeat(rs.getString(4));
				userdetails.setMobileNumber(rs.getString(5));
				flightdetails.setFlightNumber(rs.getString(6)); 
				flightdetails.setFlightName(rs.getString(7));
				book.setDeparturDate(rs.getDate(8));
				flightdetails.setDeparture(rs.getString(9));
				flightdetails.setArrival(rs.getString(10));
				flightdetails.setDeparture_TIme(rs.getTime(11));
				flightdetails.setArrival_Time(rs.getTime(12));
				
				SeeBooked sb=new SeeBooked(flightdetails, passengerDetails, book, userdetails);
				SeeBookedList.add(sb);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SeeBookedList;
	}
	public List<SeeBooked> ViewTicketsToCancel(){
		Connection con=dbconnnection.getDBConnection();
				
				List<SeeBooked> SeeBookedList=new ArrayList<SeeBooked>();
				try {
					PreparedStatement psmt=con.prepareStatement("select P.FirstName,P.LastName,P.MobileNumber,P.SelectedSeat,B.MobileNumber,B.Flight_Id,F.Flight_Name,B.DeparturDate,F.Departure_city,F.Arrival_city,F.Departure_Time,F.Arrival_Time,B.BookingId from Passenger as P,Booking as B,Flights as F where B.BookingId=P.BookingId and B.MobileNumber=? and B.Flight_Id=F.Flight_Id ");
					psmt.setString(1, user.getMobileNumber());
					ResultSet rs=psmt.executeQuery();
					while(rs.next()) {
						Flight flightdetails=new Flight();
						Passenger passengerDetails=new Passenger();
						BookingSeat book=new BookingSeat();
						User userdetails=new User();
						passengerDetails.setFirstName(rs.getString(1));
						passengerDetails.setLastName(rs.getString(2));
						passengerDetails.setMobileNumber(rs.getString(3));
						passengerDetails.setSelectedSeat(rs.getString(4));
						userdetails.setMobileNumber(rs.getString(5));
						flightdetails.setFlightNumber(rs.getString(6)); 
						flightdetails.setFlightName(rs.getString(7));
						book.setDeparturDate(rs.getDate(8));
						flightdetails.setDeparture(rs.getString(9));
						flightdetails.setArrival(rs.getString(10));
						flightdetails.setDeparture_TIme(rs.getTime(11));
						flightdetails.setArrival_Time(rs.getTime(12));
						book.setBookingId(rs.getInt(13));
						SeeBooked sb=new SeeBooked(flightdetails, passengerDetails, book, userdetails);
						SeeBookedList.add(sb);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return SeeBookedList;
			}

}
