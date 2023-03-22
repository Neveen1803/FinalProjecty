package Model.Flight;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import Dbconnection.DBConnection;
import Model.dbconnection.dbconnnection;

public class Seat {
	String Capacity;
	String Seat;
	public Seat(String capacity, String seat) {
		super();
		Capacity = capacity;
		Seat = seat;
	}
	public Seat() {
		
	}
	public String getCapacity() {
		return Capacity;
	}
	public void setCapacity(String capacity) {
		Capacity = capacity;
	}
	public String getSeat() {
		return Seat;
	}
	public void setSeat(String seat) {
		Seat = seat;
	}
	public String GetSeatFromDbWithCapacity() {
		Connection con=dbconnnection.getDBConnection();
		
			try {
				PreparedStatement psmt=con.prepareStatement("select Seats from Seats where Flight_capacity=?");
				psmt.setString(1, Capacity);
				ResultSet rs= psmt.executeQuery();
				if(rs.next()) {
					Seat=rs.getString(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	
		
		
		
		
		
		return Seat;
	}
}
