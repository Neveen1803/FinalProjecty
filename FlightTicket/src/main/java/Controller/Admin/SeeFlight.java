package Controller.Admin;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import Dbconnection.DBConnection;
import Model.Flight.Flight;

public class SeeFlight extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
@Override
public void init() throws ServletException {
	
}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	JSONArray jsonarr=new JSONArray();
	Flight flight=new Flight();
	List<Flight> flightarr=flight.showAvailableFlight();
	for(int i=0;i<flightarr.size();i++) {
		JSONObject jobj=new JSONObject();
		jobj.put("FlightNumber", flightarr.get(i).getFlightNumber());
		jobj.put("Flight", flightarr.get(i).getFlightName());
		jobj.put("Departure", flightarr.get(i).getDeparture());
		jobj.put("Arrival", flightarr.get(i).getArrival());
		jobj.put("DepartureTime",""+flightarr.get(i).getDeparture_TIme());
		jobj.put("ArrivalTime",""+flightarr.get(i).getArrival_Time());
		jobj.put("NoOfSeats",""+ flightarr.get(i).getSeats().getCapacity());
		jobj.put("Price", ""+flightarr.get(i).getPrice());
		jsonarr.add(jobj);
	}
	resp.getWriter().append(jsonarr.toString());
	System.out.println(jsonarr.toString());
	
	
	}
	
	
	
	
	
	
}
