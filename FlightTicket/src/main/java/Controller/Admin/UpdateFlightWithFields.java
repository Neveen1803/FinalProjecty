package Controller.Admin;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Model.Flight.Flight;
import Model.Flight.Seat;
@WebServlet(name="UpdateFlightWithFields",urlPatterns = {"/Admin/UpdateFlightWithFields"})
public class UpdateFlightWithFields extends HttpServlet {

	private static final long serialVersionUID = 1L;

		@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			BufferedReader br=request.getReader();
			StringBuffer sb=new StringBuffer();
			String Line;
			while((Line=br.readLine())!=null) {
				sb.append(Line);
			}
			String json=sb.toString();
			JSONObject obj=null;
			JSONParser parser=new JSONParser();
			try {
				obj=(JSONObject) parser.parse(json);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String Name=(String) obj.get("Name");
			String Value=(String)obj.get("Value");
			String FlightId=(String) obj.get("FlightId");
			System.out.println(Name+" "+Value);
			Flight flight=new Flight();
			flight.setFlightNumber(FlightId);
			if(Name.equals("Departure")) {
				flight.setDeparture(Value);
				flight.updateDeparture();
			}
			else if(Name.equals("Arrival")) {
				flight.setArrival(Value);
				flight.updateArrival();
			}
			else if(Name.equals("DepartureTime")) {

				DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
				Date depTime;
				try {
					depTime = dateFormat.parse(Value+":00");
					Time time2=new Time(depTime.getTime());
					flight.setDeparture_TIme(time2);
					flight.updateDepartureTime();
				} catch (java.text.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

			}
			else if(Name.equals("ArrivalTime")) {
				DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
				Date arrTime;
				try {
					arrTime = dateFormat.parse(Value+":00");
					Time time2=new Time(arrTime.getTime());
					flight.setArrival_Time(time2);
					flight.updateArrivalTime();
				} catch (java.text.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			else if(Name.equals("PriceForSeat")) {
				double db=Double.parseDouble(Value);
				flight.setPrice(db);
				flight.updatePrice();
			}
			else if(Name.equals("selectSeat")) {
				Seat seat=new Seat();
				seat.setCapacity(Value);
					flight.setSeats(seat);
					flight.updateFlightCapacity();
			}
}
			
		}

