package Controller.Login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import Model.User.SessionTable;

public class CheckLogin extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		JSONObject jobj=new JSONObject();
		String Role=(String) request.getAttribute("Role");

		jobj.put("Role",Role);
		response.getWriter().append(jobj.toString());
		
	}
	
}
