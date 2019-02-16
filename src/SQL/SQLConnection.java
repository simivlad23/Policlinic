package SQL;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLConnection {
	public Connection getConnection() throws Exception {
		try {
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/lantpoliclinici";
			String username = "root";
			String password = "";
			Class.forName(driver);
			
			Connection conn = DriverManager.getConnection(url, username, password);
			
			return conn;
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
}
