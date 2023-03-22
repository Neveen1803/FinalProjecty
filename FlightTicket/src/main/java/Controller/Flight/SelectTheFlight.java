package Controller.Flight;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Model.Booking.Booking;
import Model.Flight.Flight;

public class SelectTheFlight extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int TravalerCount=0;
		BufferedReader br=request.getReader();
		StringBuffer sb=new StringBuffer();
		String line;
		while((line=br.readLine())!=null) {
			sb.append(line);
		}
		String json=sb.toString();
		String DepartDate,ArrDate;
		Date date1 = null,date2=null;
		Gson gson=new Gson();
		Type type=new TypeToken<Object[]>(){}.getType();
		Object[] objects=gson.fromJson(json, type);
		String Trip="";
		String FlightNumber=null;
		
		String MobileNumber=(String)request.getAttribute("MobileNumber");

		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		
		
		for(Object obj:objects) {
			Map<String,Object> map=(Map<String, Object>) obj;
			
			Flight flight=new Flight();
			for(Map.Entry<String, Object> entry:map.entrySet()) {
				String key=entry.getKey();
				Object value=entry.getValue();
//				System.out.println(key+" "+value);
		 if(key.equals("DepartureCity")) {
	    	 flight.setDeparture((String)value);
	    }
	    else if(key.equals("ArrivalCity")) {
    	    flight.setArrival((String)value);
	    }
	    else if(key.equals("DepartureTime")) {
	    	Date DepTime;
			try {
				DepTime = dateFormat.parse((String) value);
				Time time1=new Time(DepTime.getTime());
	    	    flight.setDeparture_TIme(time1);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    else if(key.equals("ArrivalTime")) {
	    	Date ArrTime;
			try {
				ArrTime = dateFormat.parse((String) value);
				Time time1=new Time(ArrTime.getTime());
	    	    flight.setArrival_Time(time1);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    }
	    else if(key.equals("Trip")) {
	    	Trip=(String)value;
	    }
	    else if(key.equals("Travalers")) {
	    	TravalerCount=(int)(double)value;
    	
	    }    else if(key.equals("DepartureDate")) {
	    	 DepartDate=(String) value;
	    	DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
	    	try {
				date1= df.parse(DepartDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    }
	    else if(key.equals("ArrivalDate")) {
	    	ArrDate=(String) value;
	    	DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
	    	try {
	    		date2= df.parse(ArrDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
			}
			FlightNumber=flight.GetFlightNumberFromDb();
			Booking tempBooking=new Booking(MobileNumber, FlightNumber, Trip,TravalerCount,date1,date2);
//			System.out.println(tempBooking);
			tempBooking.AddToTempBooking();
			
		}
		
	}
	
	
	
	
	
	
	
	
	
	
}
