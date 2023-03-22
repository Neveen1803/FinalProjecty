package Controller.Login;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import Dbconnection.DBConnection;

public class Logout extends HttpServlet{
		
	
	
		
	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String dbname=req.getServletContext().getInitParameter("dbname");
		String username=req.getServletContext().getInitParameter("username");
		String password=req.getServletContext().getInitParameter("password");
		System.out.println(dbname+" "+username+" "+password);
		
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				String dbconnection="jdbc:mysql://localhost/"+dbname;
				DBConnection.con=DriverManager.getConnection(dbconnection,username,password);
				System.out.println("connection success");
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		String SessionId = req.getParameter("SessionId");
		JSONObject obj = new JSONObject();
		try
		{
			PreparedStatement stmt = DBConnection.con.prepareStatement("delete from SessionTable where SessionId= ?");
			stmt.setString(1, SessionId);
			stmt.executeUpdate();
			obj.put("Message","User Logged Out");
			resp.getWriter().append(obj.toString());
			
		}
		catch(SQLException er)
		{
			er.getMessage();
		}
		catch(Exception er)
		{
			er.getMessage();
		}
		
	}
}
