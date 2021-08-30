package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// class for a reusable connection to the company database. 
public class DBConnection {

	private final static String URL = "jdbc:mysql://localhost:3306/Company_Data?serverTimezone=UTC";
	private final static String USERNAME = "henry";
	private final static String PASSWORD = "henry";
	private static Connection connection;
	private static DBConnection instance;
	
	private DBConnection (Connection connection) {
		this.connection = connection;
	}
	
	public static synchronized Connection getConnection() {
		if (instance == null) {
			try {
				connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
				instance = new DBConnection(connection);
				System.out.println("Connection Successful");
			}
			catch (SQLException e ) {
				e.printStackTrace();
			}
		}
		return DBConnection.connection;
	}
}
