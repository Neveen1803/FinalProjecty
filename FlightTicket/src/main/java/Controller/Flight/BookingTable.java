package Controller.Flight;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Model.Booking.BookingSeat;
import Model.Flight.Flight;
@WebServlet(name="BookingTable",urlPatterns = {"/User/BookingTable"})
public class BookingTable extends HttpServlet {
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
		Gson gson = new Gson();
	    Type type = new TypeToken<Object[]>(){}.getType();
	    Object[] objects = gson.fromJson(json, type);
	    for (Object obj : objects) {
	    	 
	    	  Map<String, Object> map = (Map<String, Object>) obj;
	    	 BookingSeat book=new BookingSeat();
	    	  for (Map.Entry<String, Object> entry : map.entrySet()) {
	    	    String key = entry.getKey();
	    	    Object value = entry.getValue();
	    	    System.out.println(key +" "+value);
	    	    
	    	    if(key.equals("FlightId")) {
	    	    	book.setFlightId((String) value);
	    	    }
	    	    else if(key.equals("DeparturDate")) {
	    	    	 try {
	    	    		 
						Date date1=new SimpleDateFormat("yyyy-MM-dd").parse((String) value);
						book.setDeparturDate(date1);
					} catch (java.text.ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  
	    	    }
	    	    else if(key.equals("ArrivalDate")) {
	    	    	 try {
							Date date1=new SimpleDateFormat("yyyy-MM-dd").parse((String) value);
							book.setArrivalDate(date1);
							
						} catch (java.text.ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}  
	    	    }
	    	    else if(key.equals("SelectedSeat")) {
	    	    	book.setSelectedSeat((String) value);
	    	    }
	    	    book.setMobileNumber(MobileNumber);
	    	    
	    	  }
	    	  book.insertIntoBookingTable();
	    }
		response.getWriter().append("success");
	}
}
