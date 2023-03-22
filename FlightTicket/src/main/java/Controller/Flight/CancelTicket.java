package Controller.Flight;

import java.io.BufferedReader;
import java.io.IOException;
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
import Model.Booking.BookingSeat;
import Model.User.Passenger;
@WebServlet(name="CancelTicket",urlPatterns = {"/User/CancelTicket"})
public class CancelTicket extends HttpServlet{

	private static final long serialVersionUID = 1L;
		@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			String MobileNumber=(String)request.getAttribute("MobileNumber");
			BufferedReader br=request.getReader();
			StringBuffer sb=new StringBuffer();
			String line;
			while((line=br.readLine())!=null) {
				sb.append(line);
			}
			String json=sb.toString();
			System.out.println(json);
			JSONObject jobj=null;
			JSONParser parser=new JSONParser();
			try {
				jobj=(JSONObject) parser.parse(json);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			String PassengerNumber=(String) jobj.get("PassengerNumber");
			String SelectedSeat=(String)jobj.get("SelectedSeat");	
			String FlightId=(String)jobj.get("FlightId");
			String DepartureDate=(String)jobj.get("DepartureDate");
			String DepartureTime=(String)jobj.get("DepartureTime");
			String ArrivalTime=(String)jobj.get("ArrivalTime");
			int BookingId=Integer.parseInt((String)jobj.get("BookingId"));
			
			BookingSeat booking=new BookingSeat();
			booking.setMobileNumber(MobileNumber);
			try {
				Date DepDate= new SimpleDateFormat("yyyy-MM-dd").parse((String) jobj.get("DepartureDate"));
				booking.setDeparturDate(DepDate);

			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			booking.setFlightId(FlightId);
			
			Passenger passenger=new Passenger();
			passenger.setMobileNumber(PassengerNumber);
			passenger.setBookingId(BookingId); 
			passenger.setSelectedSeat(SelectedSeat);
			System.out.println(PassengerNumber+" "+BookingId+" "+SelectedSeat);
			passenger.DeleteFromPassengerTable();
			

		}
}
