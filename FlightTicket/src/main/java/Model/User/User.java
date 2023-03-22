package Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.protocol.Resultset;

import Dbconnection.DBConnection;
import Model.dbconnection.dbconnnection;

public class User {
	public User(String name, String password, String mobileNumber, String role) {
		
		Name = name;
		Password = password;
		MobileNumber = mobileNumber;
		Role = role;
	}
	public User() {
		
	}
	String Name;
	String Password;
	String MobileNumber;
	String Role;
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getMobileNumber() {
		return MobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		MobileNumber = mobileNumber;
	}
	public String getRole() {
		return Role;
	}
	public void setRole(String role) {
		Role = role;
	}
	
	public User UserDbconnection() {
		Connection con=dbconnnection.getDBConnection();
			User user=null;
			PreparedStatement psmt;
			try {
				psmt = con.prepareStatement("select *  from User where User_Name=? and User_Password=?");
				psmt.setString(1, Name);
				psmt.setString(2, Password);
				ResultSet rs=psmt.executeQuery();
				if(rs.next()) {
					user=new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("UserDbconnection "+e.getMessage());
			}
		return user;
		
	}
	
	public User GetUserWithUserName() {
		Connection con=dbconnnection.getDBConnection();
		User user=null;
		PreparedStatement psmt;
		try {
			psmt = con.prepareStatement("select *  from User where User_Name=?");
			psmt.setString(1, Name);
			ResultSet rs=psmt.executeQuery();
			if(rs.next()) {
				user=new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("UserDbconnection "+e.getMessage());
		}
	return user;
	}
	
	
	
	
	
	
	
	
	public boolean ValidateUser() {
		System.out.println("Validate");
		System.out.println(Name+""+Password+""+MobileNumber);
		try {
			Connection con=dbconnnection.getDBConnection();
			PreparedStatement psmt=con.prepareStatement("Select User_Name,User_Password from User where User_Name=? and User_Password=? and MobileNumber=?");
			psmt.setString(1, Name);
			psmt.setString(2, Password);
			psmt.setString(3, MobileNumber);
			ResultSet rs=psmt.executeQuery();
			if(rs.next()) {
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();

		}
		return true;
	}
	
	public void insertUser() {
		System.out.println("InsertUser");
		System.out.println(Name+","+Password+","+MobileNumber+","+Role);

		try {
			Connection con=dbconnnection.getDBConnection();
			PreparedStatement psmt=con.prepareStatement(" insert into User (User_Name,User_Password,MobileNumber,User_Role ) value (?,?,?,?)");
			psmt.setString(1, Name);
			psmt.setString(2, Password);
			psmt.setString(3, MobileNumber);
			psmt.setString(4, "User");
			psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
