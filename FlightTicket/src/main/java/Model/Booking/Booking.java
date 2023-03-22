package Model.Booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Model.Flight.Flight;
import Model.User.User;
import Model.dbconnection.dbconnnection;

public class Booking {
	String MobileNumber;
	String FlightNumber;
	int Travalers;
	Date DeprtureDate;
	Date ArrivalDate;
	public int getTravalers() {
		return Travalers;
	}

	public Booking(String mobileNumber, String flightNumber,String trip, int travalers, Date deprtureDate, Date arrivalDate) {
		super();
		MobileNumber = mobileNumber;
		FlightNumber = flightNumber;
		Travalers = travalers;
		DeprtureDate = deprtureDate;
		ArrivalDate = arrivalDate;
		Trip = trip;
	}

	public void setTravalers(int travalers) {
		Travalers = travalers;
	}

	public Date getDeprtureDate() {
		return DeprtureDate;
	}

	public void setDeprtureDate(Date deprtureDate) {
		DeprtureDate = deprtureDate;
	}

	public Date getArrivalDate() {
		return ArrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		ArrivalDate = arrivalDate;
	}

	public Booking() {
		
	}
	
	public String getMobileNumber() {
		return MobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		MobileNumber = mobileNumber;
	}

	public String getFlightNumber() {
		return FlightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		FlightNumber = flightNumber;
	}




	public String getTrip() {
		return Trip;
	}




	public void setTrip(String trip) {
		Trip = trip;
	}




	String Trip;
	



	public void AddToTempBooking() {
	Connection con=dbconnnection.getDBConnection();
	PreparedStatement psmt;
	try {
		psmt = con.prepareStatement("insert into TemporaryBooking values(?,?,?,?,?,?)");
		psmt.setString(1, MobileNumber);
		psmt.setString(2, FlightNumber);
		psmt.setString(3, Trip);
		psmt.setInt(4,Travalers);
		psmt.setDate(5,new java.sql.Date(DeprtureDate.getTime()));
		psmt.setDate(6,new java.sql.Date(ArrivalDate.getTime()));
		psmt.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	public List<Booking> getBookingFromDb(){
		Connection con=dbconnnection.getDBConnection();
		List<Booking> books=new ArrayList<Booking>();
		PreparedStatement psmt;
		Booking book;
		try {
			psmt = con.prepareStatement("select * from TemporaryBooking where MobileNumber=?");
			psmt.setString(1, MobileNumber);
			ResultSet rs=psmt.executeQuery();
			while(rs.next()) {
				book=new Booking(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getDate(5),rs.getDate(6));
				books.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return books;
		
	}
	
	public void DeleteInfoOfTempBooking() {
		Connection con=dbconnnection.getDBConnection();
		try {
			PreparedStatement psmt=con.prepareStatement("Delete from TemporaryBooking where MobileNumber=?");
			psmt.setString(1, MobileNumber);
			psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return MobileNumber+" "+FlightNumber+" "+Trip+" "+Travalers+" "+DeprtureDate+" "+ArrivalDate;
	}
}

