package Model.Booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Model.dbconnection.dbconnnection;

public class BookingSeat {
	int BookingId;
	public int getBookingId() {
		return BookingId;
	}
	public void setBookingId(int bookingId) {
		BookingId = bookingId;
	}
	String MobileNumber;
	String FlightId;
	Date DeparturDate;
	Date ArrivalDate;
	String SelectedSeat;
	public BookingSeat(String mobileNumber, String flightId, Date departurDate, Date arrivalDate, String selectedSeat) {
		super();
		MobileNumber = mobileNumber;
		FlightId = flightId;
		DeparturDate = departurDate;
		ArrivalDate = arrivalDate;
		SelectedSeat = selectedSeat;
	
	}
	public BookingSeat() {
		
	}
	public String getMobileNumber() {
		return MobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		MobileNumber = mobileNumber;
	}
	public String getFlightId() {
		return FlightId;
	}
	public void setFlightId(String flightId) {
		FlightId = flightId;
	}
	public Date getDeparturDate() {
		return DeparturDate;
	}
	public void setDeparturDate(Date departurDate) {
		DeparturDate = departurDate;
	}
	public Date getArrivalDate() {
		return ArrivalDate;
	}
	public void setArrivalDate(Date arrivalDate) {
		ArrivalDate = arrivalDate;
	}
	public String getSelectedSeat() {
		return SelectedSeat;
	}
	public void setSelectedSeat(String selectedSeat) {
		SelectedSeat = selectedSeat;
	}
	public void insertIntoBookingTable() {
		Connection con=dbconnnection.getDBConnection();
		try {
			PreparedStatement psmt=con.prepareStatement("insert into Booking (MobileNumber,Flight_Id,DeparturDate,ArrivalDate,SelectedSeat)values (?,?,?,?,?)");
			psmt.setString(1, MobileNumber);
			psmt.setString(2, FlightId);
			psmt.setDate(3, new java.sql.Date(DeparturDate.getTime()));
			psmt.setDate(4, new java.sql.Date(ArrivalDate.getTime()));
			psmt.setString(5, SelectedSeat);
			psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<BookingSeat> getTheSelectedSeat() {
		System.out.println("getTheSelectedSeat method called");
		Connection con=dbconnnection.getDBConnection();
		List<BookingSeat> arr=new ArrayList<BookingSeat>();
		try {
			PreparedStatement psmt=con.prepareStatement("select * from Booking where Flight_Id=? and DeparturDate=? and ArrivalDate=?");
			psmt.setString(1, FlightId);
			psmt.setDate(2, new java.sql.Date(DeparturDate.getTime()));
			psmt.setDate(3, new java.sql.Date(ArrivalDate.getTime()));
			System.out.println(FlightId+" "+DeparturDate+" "+ArrivalDate);
			ResultSet rs=psmt.executeQuery();
			while(rs.next()) {
				System.out.println("getted");
				//String mobileNumber, String flightId, Date departurDate, Date arrivalDate, String selectedSeat)
				arr.add(new BookingSeat(rs.getString(2),rs.getString(3),rs.getDate(4),rs.getDate(5),rs.getString(6)));
				//System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getDate(3)+" "+rs.getDate(4)+" "+rs.getString(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}
	 public void CancelTicket() {
			Connection con=dbconnnection.getDBConnection();
			try {
				//update the selected seat don't delete......
				PreparedStatement psmt=con.prepareStatement("Delete from Booking where MobileNumber=? and Flight_Id=? and DeparturDate=? and ArrivalDate=?");
				psmt.setString(1, MobileNumber);
				psmt.setString(2,FlightId);
				psmt.setDate(3, new java.sql.Date(DeparturDate.getTime()));
				psmt.setDate(4, new java.sql.Date(ArrivalDate.getTime()));
				psmt.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	 }
	 public int getBookingIdFromDB() {
		 int id=0;
		 Connection con=dbconnnection.getDBConnection();
			try {
				PreparedStatement psmt=con.prepareStatement("select BookingId from Booking where MobileNumber=? and Flight_Id=? and DeparturDate=? and ArrivalDate=? order by BookingId desc limit 1");
				psmt.setString(1, MobileNumber);
				psmt.setString(2,FlightId);
				psmt.setDate(3, new java.sql.Date(DeparturDate.getTime()));
				psmt.setDate(4, new java.sql.Date(ArrivalDate.getTime()));
				System.out.println(MobileNumber+","+FlightId+","+new java.sql.Date(DeparturDate.getTime())+","+ new java.sql.Date(ArrivalDate.getTime()));
				ResultSet rs=psmt.executeQuery();
				if(rs.next()) {
					id=rs.getInt(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return id;
	 }
}
