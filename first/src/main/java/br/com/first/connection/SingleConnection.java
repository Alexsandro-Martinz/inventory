package br.com.first.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {

	private static String bank = "jdbc:postgresql://localhost:5432/first?autoReconnect=true";
	private static String user = "postgres";
	private static String password = "admin";
	private static Connection connection = null;

	public SingleConnection() {
		connect();
	}

	static {
		connect();
	}

	private static void connect() {
		try {

			if (connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(bank, user, password);
				connection.setAutoCommit(false);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		return connection;
	}

}
