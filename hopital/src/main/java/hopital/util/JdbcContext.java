package hopital.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


//configuration du JDBC 
//on va faire la config 1 fois (evite de repeter le code)
public class JdbcContext {

	// pattern singleton
	private static Connection singleton = null;

	// Factory
	// Facade


	// bloc execute 1 fois
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// permet de recuperer un connection à 1 base
	// nouvelle si pas de connection
	// connection existante si elle existe
	public static Connection getConnection() {
		if (singleton == null) {
			try {
				singleton = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "root123@");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return singleton;
	}

	// permet de fermer une connection ouverte
	public static void closeConnection() {
		if (singleton != null) {
			try {
				singleton.close();
				singleton = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
