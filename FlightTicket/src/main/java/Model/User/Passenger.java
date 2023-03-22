package Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import Model.dbconnection.dbconnnection;

public class Passenger {
	String FirstName;
	String LastName;
	String Gender;
	Date DateOfBirth;
	String MobileNumber;
	int BookingId;
	public int getBookingId() {
		return BookingId;
	}

	public void setBookingId(int bookingId) {
		BookingId = bookingId;
	}
	String SelectedSeat;
	
	public Passenger(String firstName, String lastName, String gender, Date dateOfBirth, String mobileNumber,
			String selectedSeat,int bookingId) {
		super();
		FirstName = firstName;
		LastName = lastName;
		Gender = gender;
		DateOfBirth = dateOfBirth;
		MobileNumber = mobileNumber;
			SelectedSeat=selectedSeat;
			BookingId=bookingId;
	}
	
	public String getSelectedSeat() {
		return SelectedSeat;
	}

	public void setSelectedSeat(String selectedSeat) {
		SelectedSeat = selectedSeat;
	}


	public Passenger() {
		
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public Date getDateOfBirth() {
		return DateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		DateOfBirth = dateOfBirth;
	}
	public String getMobileNumber() {
		return MobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		MobileNumber = mobileNumber;
	}
	public void InsertIntoPassengerTable() {
		//create table Passenger (FirstName varchar(30),LastName varchar(30),Gender enum("Male","Female"),DateOfBirth Date,MobileNumber char(10));
		Connection con=dbconnnection.getDBConnection();
		try {
			PreparedStatement psmt=con.prepareStatement("Insert into Passenger values(?,?,?,?,?,?,?)");
			psmt.setString(1, FirstName);
			psmt.setString(2, LastName);
			psmt.setString(3, Gender);
			psmt.setDate(4, new java.sql.Date(DateOfBirth.getTime()));
			psmt.setString(5, MobileNumber);
			psmt.setString(6, SelectedSeat);
			psmt.setInt(7, BookingId);
			psmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void DeleteFromPassengerTable() {
		//create table Passenger (FirstName varchar(30),LastName varchar(30),Gender enum("Male","Female"),DateOfBirth Date,MobileNumber char(10));
		Connection con=dbconnnection.getDBConnection();
		try {
			PreparedStatement psmt=con.prepareStatement("Delete From Passenger where MobileNumber=? and SelectedSeat=? and BookingId=?");
			psmt.setString(1, MobileNumber);
			psmt.setString(2, SelectedSeat);
			psmt.setInt(3, BookingId);
			psmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
