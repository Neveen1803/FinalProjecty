package Controller.Flight;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Model.User.Payment;
@WebServlet(name="PaymentDetails",urlPatterns = {"/User/PaymentDetails"})
public class PaymentDetails extends HttpServlet{

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
		JSONObject jobj=null;
		JSONParser parser=new JSONParser();
		try {
			jobj=(JSONObject) parser.parse(json);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Payment payment=new Payment();
		payment.setCardNumber((String) jobj.get("CardNumber"));
		payment.setCsv((String) jobj.get("Csv"));
		payment.setExpDate((String) jobj.get("ExpDate"));
		payment.setBookingMobileNumber(MobileNumber);
		payment.setPrice(Double.parseDouble((String) jobj.get("Price")));
		payment.InsertPaymentDetails();
		System.out.println(payment);
	}
}
