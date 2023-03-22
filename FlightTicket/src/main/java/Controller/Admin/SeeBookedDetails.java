package Controller.Admin;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Model.Booking.SeeBooked;
@WebServlet(name="SeeBookedDetails",urlPatterns = {"/Admin/SeeBookedDetails"})
public class SeeBookedDetails extends HttpServlet{

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		SeeBooked sb=new SeeBooked();
		List<SeeBooked> SeeBookedList=sb.SeeBookingFromDB();
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<SeeBookedList.size();i++) {
			JSONObject jsonobject =new JSONObject();
			jsonobject.put("FlightId", SeeBookedList.get(i).getFlight().getFlightNumber());
			jsonobject.put("FlightName",SeeBookedList.get(i).getFlight().getFlightName() );
			jsonobject.put("Departure", SeeBookedList.get(i).getFlight().getDeparture());
			jsonobject.put("Arrival", SeeBookedList.get(i).getFlight().getArrival());
			jsonobject.put("Price",""+ SeeBookedList.get(i).getFlight().getPrice());
			jsonobject.put("PassengerName", SeeBookedList.get(i).getPassenger().getFirstName()+" "+SeeBookedList.get(i).getPassenger().getLastName());
			jsonobject.put("PassengerNumber", SeeBookedList.get(i).getPassenger().getMobileNumber());
			jsonobject.put("BookedUserNumber", SeeBookedList.get(i).getBooking().getMobileNumber());
			jsonobject.put("UserName", SeeBookedList.get(i).getUser().getName());
			jsonobject.put("DepartureDate", ""+SeeBookedList.get(i).getBooking().getDeparturDate());
			jsonobject.put("ArrivalDate",""+ SeeBookedList.get(i).getBooking().getArrivalDate());
			jsonobject.put("DepartureTime", ""+SeeBookedList.get(i).getFlight().getDeparture_TIme());
			jsonobject.put("ArrivalTime",""+ SeeBookedList.get(i).getFlight().getArrival_Time());
			jsonobject.put("SelectedSeat", SeeBookedList.get(i).getPassenger().getSelectedSeat());
			System.out.println(jsonobject);
			jsonArray.add(jsonobject);
		}
		response.getWriter().append(jsonArray.toString());
	}

}
