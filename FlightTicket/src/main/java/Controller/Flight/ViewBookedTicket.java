package Controller.Flight;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Model.Booking.SeeBooked;
import Model.User.User;
@WebServlet(name="ViewBookedTicket",urlPatterns = {"/User/ViewBookedTicket"})
public class ViewBookedTicket extends HttpServlet{

	private static final long serialVersionUID = 1L;
		
	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String MobileNumber=(String)request.getAttribute("MobileNumber");
		User user=new User();
		user.setMobileNumber(MobileNumber);
		SeeBooked sb=new SeeBooked();
		sb.setUser(user);
		List<SeeBooked> ViewTickets=sb.ViewTickets();
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<ViewTickets.size();i++) {
			JSONObject jobj=new JSONObject();
			jobj.put("Name",ViewTickets.get(i).getPassenger().getFirstName()+" "+ViewTickets.get(i).getPassenger().getLastName());
			jobj.put("PassengerNumber",ViewTickets.get(i).getPassenger().getMobileNumber());
			jobj.put("SelectedSeat",ViewTickets.get(i).getPassenger().getSelectedSeat());
			jobj.put("BookedUserNumber",ViewTickets.get(i).getUser().getMobileNumber());
			jobj.put("FlightId",ViewTickets.get(i).getFlight().getFlightNumber());
			jobj.put("FlightName",ViewTickets.get(i).getFlight().getFlightName());
			jobj.put("DepartureDate",""+ViewTickets.get(i).getBooking().getDeparturDate());
			jobj.put("Departure",ViewTickets.get(i).getFlight().getDeparture());
			jobj.put("Arrival",ViewTickets.get(i).getFlight().getArrival());
			jobj.put("DepartureTime",""+ViewTickets.get(i).getFlight().getDeparture_TIme());
			jobj.put("ArrivalTime",""+ViewTickets.get(i).getFlight().getArrival_Time());
			jsonArray.add(jobj);
		
		}
//		System.out.println(jsonArray.size());
		response.getWriter().append(jsonArray.toString());
	}
}
