package Controller.Login;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.mysql.cj.Session;

import Dbconnection.DBConnection;
import Model.User.SessionTable;
import Model.User.User;

/**
 * Servlet implementation class login
 */
//@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */

//	}
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		
	
			String name=request.getParameter("name");
			String password=request.getParameter("password");
			User user=new User();
			user.setName(name);
			user.setPassword(password);
			User newUser=user.UserDbconnection();
//	System.out.println(newUser);
			if(newUser!=null) {
				String MobileNumber=newUser.getMobileNumber();
				UUID randoUUId=UUID.randomUUID();
				Cookie ck=new Cookie("SessionId", ""+randoUUId);
				response.addCookie(ck);
				//SessionTable Class
				
				SessionTable st=new SessionTable(""+randoUUId,MobileNumber);
				st.sessionDBConnection();
				
				JSONObject jobj=new JSONObject();
				jobj.put("Role",newUser.getRole());
				response.getWriter().append(jobj.toString());
				}
			
				else {
					JSONObject jobj=new JSONObject();
					jobj.put("Role","No User");
					response.getWriter().append(jobj.toString());
				}
		
	
}
		

}



