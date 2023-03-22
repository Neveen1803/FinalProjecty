package Controller.Login;

import java.io.BufferedReader;
import java.io.IOException;
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
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.mysql.cj.xdevapi.Statement;

import Dbconnection.DBConnection;
import Model.User.SessionTable;
import Model.User.User;

/**
 * Servlet implementation class signup
 */
//@WebServlet("/signup")
public class signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public signup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

		BufferedReader br=request.getReader();
		StringBuilder sb=new StringBuilder();
		String line;
		while((line=br.readLine())!=null) {
			sb.append(line);
		}
		String json=sb.toString();
		System.out.println(json);
		JSONParser parser=new JSONParser();
		JSONObject obj=null;
		try {
			obj=(JSONObject) parser.parse(json);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String UserName=(String) obj.get("name");
		String Password=(String) obj.get("password");
		String confirmPassword=(String) obj.get("confirmPassword");
		String PhoneNumber=(String) obj.get("phoneNumber") ;
		
		
	
		User user=new User(UserName, Password, PhoneNumber, "User");
		
		
	
			if(user.ValidateUser()) {
				user.insertUser();
				UUID randid=UUID.randomUUID();
				Cookie cookie=new Cookie("SessionId", ""+randid);
				response.addCookie(cookie);
				
				SessionTable st=new SessionTable(""+randid, PhoneNumber);
				st.sessionDBConnection();
				
				JSONObject jobj=new JSONObject();
				jobj.put("Role", "User");
				response.getWriter().append(jobj.toString());
				System.out.println(jobj.toString());
				System.out.println("validate pass");
			}
			else {
				JSONObject jobj=new JSONObject();
				jobj.put("Role","No User");
				response.getWriter().append(jobj.toString());
				System.out.println("validate failed");
			}
			
	}
	


}
