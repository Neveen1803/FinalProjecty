package Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Model.dbconnection.dbconnnection;

public class Payment {
	String CardNumber;
	String ExpDate;
	String csv;
	String BookingMobileNumber;
	double Price;

	public double getPrice() {
		return Price;
	}
	public void setPrice(double price) {
		Price = price;
	}
	public Payment(String cardNumber, String expDate, String csv, String bookingMobileNumber, double price) {
		super();
		CardNumber = cardNumber;
		ExpDate = expDate;
		this.csv = csv;
		BookingMobileNumber = bookingMobileNumber;
		Price = price;
	}
	public Payment() {
		// TODO Auto-generated constructor stub
	}
	public String getCardNumber() {
		return CardNumber;
	}
	public void setCardNumber(String cardNumber) {
		CardNumber = cardNumber;
	}
	public String getExpDate() {
		return ExpDate;
	}
	public void setExpDate(String expDate) {
		ExpDate = expDate;
	}
	public String getCsv() {
		return csv;
	}
	public void setCsv(String csv) {
		this.csv = csv;
	}
	public String getBookingMobileNumber() {
		return BookingMobileNumber;
	}
	public void setBookingMobileNumber(String bookingMobileNumber) {
		BookingMobileNumber = bookingMobileNumber;
	}
	
	public void InsertPaymentDetails() {
		Connection con=dbconnnection.getDBConnection();
		try {
			PreparedStatement psmt=con.prepareStatement("Insert into Payment values(?,?,?,?,?");
			psmt.setString(1, CardNumber);
			psmt.setString(2, ExpDate);
			psmt.setString(3,csv);
			psmt.setString(4, BookingMobileNumber);
			psmt.setDouble(5, Price);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public String toString() {
		return "Payment [CardNumber=" + CardNumber + ", ExpDate=" + ExpDate + ", csv=" + csv + ", BookingMobileNumber="
				+ BookingMobileNumber + "]";
	}
	
}
