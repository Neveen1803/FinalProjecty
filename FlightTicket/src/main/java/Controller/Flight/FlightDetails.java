package Controller.Flight;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import Model.Flight.Flight;

/**
 * Servlet implementation class FlightDetails
 */
//@WebServlet("/FlightDetails")
public class FlightDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FlightDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
				
		BufferedReader br=request.getReader();
		StringBuffer sb=new StringBuffer();
		String line;
		String Trip="";
		while((line=br.readLine())!=null) {
			System.out.println(line);
			sb.append(line);
		}
		String json=sb.toString();
		 Gson gson = new Gson();
		
		    Type type = new TypeToken<Object[]>(){}.getType();
		    Object[] objects = gson.fromJson(json, type);
		    
		 List<List<Flight>> listOfFlightList=new ArrayList<List<Flight>>(); 
		 List<Flight> ListOFFlight=new ArrayList<Flight>();
		 
		 
		    for (Object obj : objects) {
		    	 
		    	  Map<String, Object> map = (Map<String, Object>) obj;
		    	  Flight flight=new Flight();
		    	 
		    	  for (Map.Entry<String, Object> entry : map.entrySet()) {
		    	    String key = entry.getKey();
		    	    Object value = entry.getValue();
		    	    if(key.equals("departure")) {
		    	    	 flight.setDeparture((String)value);
		    	    }
		    	    else if(key.equals("arrival")) {
			    	    flight.setArrival((String)value);
		    	    }
		    	    else if(key.equals("Trip")) {
		    	    	Trip=(String)value;
		    	    }
		    	
		    	  }
		    	  //-----------------------------------------------
		    	  if(Trip.equals("OneWay")) {
		    		  listOfFlightList.add(flight.flightDbconnection());
		    	  }
		    	  else {
		    		  //this else part is to select one flight for search in roundTrip and multicity
//		    		  int min=0;
		    		  int max=flight.flightDbconnection().size();
		    		  System.out.println(max);
//		    		  int random=(int)(Math.random()*(max-min+1)+min);
		    		  Random rand=new Random();
		    		  int random=rand.nextInt(max);
		    		  ListOFFlight.add(flight.flightDbconnection().get(random));
		    		  
		    	  }
		    	}//end of for each loop
		    
		    
		    
		    JSONArray jsonarr=new JSONArray();
		    if(Trip.equals("OneWay")) {
		    	//this is to send flight details for One Way

		    	for(int i=0;i<listOfFlightList.size();i++) {
					  List<Flight> flightarr=listOfFlightList.get(i);
					  for(int j=0;j<flightarr.size();j++) {
							JSONObject jobj1=new JSONObject();
							jobj1.put("FlightNumber", flightarr.get(j).getFlightNumber());
							jobj1.put("Flight", flightarr.get(j).getFlightName());
							jobj1.put("Departure", flightarr.get(j).getDeparture());
							jobj1.put("Arrival", flightarr.get(j).getArrival());
							jobj1.put("DepartureTime", ""+flightarr.get(j).getDeparture_TIme());
							jobj1.put("ArrivalTime",""+ flightarr.get(j).getArrival_Time());
							jobj1.put("NoOfSeats",""+ flightarr.get(j).getSeats().GetSeatFromDbWithCapacity());
							jobj1.put("Price", ""+flightarr.get(j).getPrice());
							
							jsonarr.add(jobj1);
						}
				  }
		    }
		    else {
		    	//this is to send flight details for multi city and round trip
		    	List<Flight> flightarr=ListOFFlight;
				  for(int j=0;j<flightarr.size();j++) {
						JSONObject jobj1=new JSONObject();
						jobj1.put("FlightNumber", flightarr.get(j).getFlightNumber());
						jobj1.put("Flight", flightarr.get(j).getFlightName());
						jobj1.put("Departure", flightarr.get(j).getDeparture());
						jobj1.put("Arrival", flightarr.get(j).getArrival());
						jobj1.put("DepartureTime", ""+flightarr.get(j).getDeparture_TIme());
						jobj1.put("ArrivalTime",""+ flightarr.get(j).getArrival_Time());
						jobj1.put("NoOfSeats",""+ flightarr.get(j).getSeats().GetSeatFromDbWithCapacity());
						jobj1.put("Price", ""+flightarr.get(j).getPrice());
					
						jsonarr.add(jobj1);
					}
		    }
		  System.out.println(jsonarr);
		  response.getWriter().append(jsonarr.toString());

	}

}
