package Controller.Admin;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Dbconnection.DBConnection;
import Model.Flight.Flight;
import Model.Flight.Seat;

/**
 * Servlet implementation class AddFlight
 */

public class AddFlight extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddFlight() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br=request.getReader();
		StringBuilder sb=new StringBuilder();
		String line;
		while((line=br.readLine())!=null) {
			sb.append(line);
		}
		String json=sb.toString();
		JSONParser parse=new JSONParser();
		JSONObject obj=null;
		try {
			 obj=(JSONObject) parse.parse(json);
		} catch (ParseException e) {
			// TODO Auto-generated catch block			e.printStackTrace();

			System.out.println();
			e.printStackTrace();
		}
		String Name=(String) obj.get("Name");
		String Depart=(String) obj.get("Depart");
		String Arrive=(String) obj.get("Arrive");
		String DepartTime= (String) obj.get("DepartTime")+":00";
		String ArriveTime=(String) obj.get("ArriveTime")+":00";
		String seats=(String) obj.get("Seats");
		String FlightId=(String) obj.get("FlightId");
		double Price=Double.parseDouble((String) obj.get("Price"));
		Flight flight=new Flight();
		flight.setFlightNumber(FlightId);
		flight.setFlightName(Name);
		flight.setDeparture(Depart);
		flight.setArrival(Arrive);
		 DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		 Date depTime;
		try {
			depTime = dateFormat.parse(DepartTime);
			 Date arrTime= dateFormat.parse(ArriveTime);
			 Time time1=new Time(depTime.getTime());
			 Time time2=new Time(arrTime.getTime());
			flight.setDeparture_TIme(time1);
			flight.setArrival_Time(time2);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("AddFlight "+e.getMessage());
			e.printStackTrace();
		}
		Seat seat=new Seat();
		seat.setCapacity(seats);
		flight.setSeats(seat);
		flight.setPrice(Price);
		flight.InsertFlight();
		

		}
		
//		
	}


