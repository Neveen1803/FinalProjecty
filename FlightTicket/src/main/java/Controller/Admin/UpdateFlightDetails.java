package Controller.Admin;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Model.Flight.Flight;
@WebServlet(name="UpdateFlightDetails" , urlPatterns = {"/Admin/UpdateFlightDetails"})
public class UpdateFlightDetails extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader br=request.getReader();
		StringBuffer sb=new StringBuffer();
		String line;
		while((line=br.readLine())!=null) {
			sb.append(line);
		}
		String json=sb.toString();
		
		JSONObject jobj=null;
		JSONParser parser=new JSONParser();
		try {
			jobj=(JSONObject) parser.parse(json);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String FlightId=(String) jobj.get("FlightId");
		Flight flight=new Flight();
		flight.setFlightNumber(FlightId);
		Flight newFlight=new Flight();
		newFlight=flight.getFlightDetailsWithFlightId();
		JSONObject obj=new JSONObject();
		if(newFlight!=null) {
			obj.put("FlightId", newFlight.getFlightNumber());
			obj.put("Flight", newFlight.getFlightName());
			obj.put("Departure", newFlight.getDeparture());
			obj.put("Arrival", newFlight.getArrival());
			obj.put("DepartureTime",""+newFlight.getDeparture_TIme());
			obj.put("ArrivalTime",""+newFlight.getArrival_Time());
			obj.put("NoOfSeats",""+ newFlight.getSeats().getCapacity());
			obj.put("Price", ""+newFlight.getPrice());
		}
		else {
			obj.put("FlightId", "null");	
			}
		response.getWriter().append(obj.toString());
	}

}
