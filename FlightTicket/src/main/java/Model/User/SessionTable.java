package Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.dbconnection.dbconnnection;

public class SessionTable {
	String SessionId;
	String MobileNumber;
	public SessionTable(String sessionId, String mobileNumber) {
		super();
		SessionId = sessionId;
		MobileNumber = mobileNumber;
	}
	public SessionTable(){
		
	}
	public String getSessionId() {
		return SessionId;
	}
	public void setSessionId(String sessionId) {
		SessionId = sessionId;
	}
	public String getMobileNumber() {
		return MobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		MobileNumber = mobileNumber;
	}
	public void sessionDBConnection() {
		Connection con=dbconnnection.getDBConnection();
		try {
			PreparedStatement psmt=con.prepareStatement("Insert into SessionTable values(?,?)");
			psmt.setString(1, SessionId);
			psmt.setString(2, MobileNumber);
			psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public String getMobileNumberFromSessionTable() {
		String number="";
		Connection con=dbconnnection.getDBConnection();
		try {
			PreparedStatement psmt=con.prepareStatement("Select MobileNumber from SessionTable where MonileNumber=?");
			psmt.setString(1, MobileNumber);
			ResultSet rs=psmt.executeQuery();
			if(rs.next()) {
				number=rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return number;
	}
	
}
