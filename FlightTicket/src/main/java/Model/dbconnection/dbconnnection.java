package Model.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;


public class dbconnnection {
static Connection con=null;
private dbconnnection() {
}
public static synchronized Connection getDBConnection() {
	try {
		if(con==null) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/BigProject","neveen-zstk305","jHu_UpMRi3u");
		}
	}
	catch(Exception ex) {
		ex.printStackTrace();
		System.out.println("Exception getBDConnection Singleton");
		
	}
	return con;
}
}