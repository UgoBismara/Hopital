package hopital.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import hopital.dao.DAOcompteJDBC;
import hopital.dao.DAOpatientJDBC;
import hopital.dao.DAOvisiteJDBC;

//configuration du JDBC 
//on va faire la config 1 fois (evite de repeter le code)
public class JdbcContext {

	// pattern singleton
	private static Connection singleton = null;

	// Factory
	// Facade

	private static DAOpatientJDBC daoPatientJdbc = new DAOpatientJDBC();
	private static DAOvisiteJDBC daoVisiteJdbc = new DAOvisiteJDBC();
	private static DAOcompteJDBC daoCompteJdbc = new DAOcompteJDBC();

	public static DAOcompteJDBC getDaoCompteJdbc() {
		return daoCompteJdbc;
	}

	public static void setDaoCompteJdbc(DAOcompteJDBC daoCompteJdbc) {
		JdbcContext.daoCompteJdbc = daoCompteJdbc;
	}

	public static DAOvisiteJDBC getDaoVisiteJdbc() {
		return daoVisiteJdbc;
	}

	public static void setDaoVisiteJdbc(DAOvisiteJDBC daoVisiteJdbc) {
		JdbcContext.daoVisiteJdbc = daoVisiteJdbc;
	}

	public static DAOpatientJDBC getDaoPatientJdbc() {
		return daoPatientJdbc;
	}

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
				singleton = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "root123@");
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
