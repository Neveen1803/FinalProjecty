package Controller.Flight;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Model.Booking.BookingSeat;
import Model.User.Passenger;
@WebServlet(name="PassengerDetails",urlPatterns = {"/User/PassengerDetails"})
public class PassengerDetails extends HttpServlet{

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
		
		Gson gson = new Gson();
	    Type type = new TypeToken<Object[]>(){}.getType();
	    Object[] objects = gson.fromJson(json, type);
	    for (Object obj : objects) {
	    	 
	    	  Map<String, Object> map = (Map<String, Object>) obj;
	    	  Passenger passenger=new Passenger();
	    	  BookingSeat book=new BookingSeat();
	    	  for (Map.Entry<String, Object> entry : map.entrySet()) {
				String key=entry.getKey();
				Object value=entry.getValue();
				System.out.println(key+" - "+value);
				if(key.equals("FirstName")) {
					passenger.setFirstName((String) value);
				}else if(key.equals("LastName")) {
					passenger.setLastName((String) value);
				}
				else if(key.equals("Gender")) {
					passenger.setGender((String) value);
				}
				else if(key.equals("DateOfBirth")) {
				
					try {
					Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse((String) value);
						passenger.setDateOfBirth(date1);

					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if(key.equals("MobileNumber")) {
					passenger.setMobileNumber((String) value);
				}
				else if(key.equals("SelectedSeat")) {
					passenger.setSelectedSeat((String) value);
				}
				else if(key.equals("DeparturDate")) {
					try {
						System.out.println((String) value);
						Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse((String) value);
							book.setDeparturDate(date1);

						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}			
					}
				else if(key.equals("ArrivalDate")) {
					try {
						Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse((String) value);
							book.setArrivalDate(date1);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}		
				}
				else if(key.equals("FlightId")) {
					book.setFlightId((String) value);
				}
				
			}
	    	  book.setMobileNumber(MobileNumber);
				 System.out.println(MobileNumber);
				int ID=book.getBookingIdFromDB();
				System.out.println(ID);
				passenger.setBookingId(ID);
			passenger.InsertIntoPassengerTable();
		}
	}
}
