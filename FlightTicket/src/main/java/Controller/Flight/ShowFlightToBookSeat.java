package Controller.Flight;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Model.Booking.Booking;
import Model.Booking.BookingSeat;
import Model.Flight.Flight;

public class ShowFlightToBookSeat extends HttpServlet{
 private static final long serialVersionUID = 1L;

@SuppressWarnings("unchecked")
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	 String MobileNumber=(String) request.getAttribute("MobileNumber");
	 Booking book=new Booking();
	 book.setMobileNumber(MobileNumber);
	 List<Booking> books=book.getBookingFromDb();
	 
	 JSONArray jsarray=new JSONArray();
	 System.out.println("size is "+books.size());
	for(int i=0;i<books.size();i++) {
		JSONObject jobj=new JSONObject();
		Flight flight=new Flight();
		flight.setFlightNumber(books.get(i).getFlightNumber());
		Flight gettingFlight=flight.getFlightDetailsWithFlightId();
		jobj.put("FlightId", gettingFlight.getFlightNumber());
		jobj.put("MobileNumber", MobileNumber);
		jobj.put("FlightName",	gettingFlight.getFlightName());
		jobj.put("Departure",	gettingFlight.getDeparture());
		jobj.put("Arrival", gettingFlight.getArrival());
		jobj.put("Trip", books.get(i).getTrip());
		jobj.put("Price", gettingFlight.getPrice());
		jobj.put("DepartureTime", ""+gettingFlight.getDeparture_TIme());
		jobj.put("ArrivalTime",""+ gettingFlight.getArrival_Time());
		jobj.put("Travalers",""+ books.get(i).getTravalers());
		jobj.put("FlightCapacity", gettingFlight.getSeats().getCapacity());
		jobj.put("DepartureDate", ""+books.get(i).getDeprtureDate());
		jobj.put("ArrivalDate", ""+books.get(i).getArrivalDate());
		jobj.put("seat", gettingFlight.getSeats().GetSeatFromDbWithCapacity());
		BookingSeat bookedseats=new BookingSeat();
		bookedseats.setFlightId(gettingFlight.getFlightNumber());
		bookedseats.setDeparturDate(books.get(i).getDeprtureDate());
		bookedseats.setArrivalDate(books.get(i).getArrivalDate());
		List<BookingSeat> ListOfseat=bookedseats.getTheSelectedSeat();
		System.out.println(ListOfseat);
		if(!ListOfseat.isEmpty()) {
			String str="";
			for(int j=0;j<ListOfseat.size();j++) {
				str+=ListOfseat.get(j).getSelectedSeat()+",";
			}
			jobj.put("Bookedseats", str);
		}
		else {
			jobj.put("Bookedseats", "");
		}
		//i want travaler count
		//System.out.println("My json is "+jobj);
		jsarray.add(jobj);
	}
	book.DeleteInfoOfTempBooking();
	System.out.println(jsarray.toJSONString());
	 response.getWriter().append(jsarray.toString());
}
}
