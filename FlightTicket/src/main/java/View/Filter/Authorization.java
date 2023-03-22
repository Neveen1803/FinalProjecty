package View.Filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Dbconnection.DBConnection;

/**
 * Servlet Filter implementation class Authorization
 */
//@WebFilter(filterName="/Authorization")
public class Authorization extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public Authorization() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		// pass the request along the filter chain
		String dbname=request.getServletContext().getInitParameter("dbname");
		String username=request.getServletContext().getInitParameter("username");
		String password=request.getServletContext().getInitParameter("password");
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
		
		String MobileNumber=(String) request.getAttribute("MobileNumber");
		String Role="";
		PreparedStatement psmt;
		try {
			psmt = DBConnection.con.prepareStatement("Select User_Role from User where MobileNumber=?");
			psmt.setString(1, MobileNumber);
			ResultSet rs=psmt.executeQuery();
			if(rs.next()) {
				Role=rs.getString(1);
				request.setAttribute("Role", Role);
				System.out.println("Second Authorization");
				chain.doFilter(request, response);
			}else {
				Role="No User";
				request.setAttribute("Role", Role);
				System.out.println("Second Authorization");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */


}
