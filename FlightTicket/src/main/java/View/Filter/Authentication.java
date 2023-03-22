package View.Filter;

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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;

import Dbconnection.DBConnection;

/**
 * Servlet Filter implementation class Authentication
 */
//@WebFilter(filterName="/Authentication")
public class Authentication extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public Authentication() {
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

		// pass the request along the filter chain
		HttpServletRequest serReq=(HttpServletRequest) request;
		Cookie[] cookie=serReq.getCookies();
		String SessionId="";
		for(int i=0;i<cookie.length;i++) {
			SessionId=cookie[i].getValue();
			System.out.println("getSession "+SessionId);
		}
		String MobileNumber="";
		try {
			PreparedStatement smt=DBConnection.con.prepareStatement("Select UserPhoneNumber from SessionTable where SessionId=?");
			smt.setString(1, SessionId);
			ResultSet rs=smt.executeQuery();
			JSONObject jobj=new JSONObject();
			if(rs.next()) {
				MobileNumber=rs.getString(1);
				request.setAttribute("MobileNumber", MobileNumber);
				chain.doFilter(request, response);
				
			}
			else {
				request.setAttribute("MobileNumber", "Not a Number");
				System.out.println("Invalid User");
				response.getWriter().append("Invalid User");
			}
		
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
}
