package hopital.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import hopital.model.Patient;
import hopital.util.JdbcContext;

public class DAOpatientJDBC implements DAOpatient {

	@Override
	public void insert(Patient obj) {
		PreparedStatement ps = null;
		try {
			ps = JdbcContext.getConnection().prepareStatement("insert into patient(nom, prenom) values(?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, obj.getNomPatient());
			ps.setString(2, obj.getPrenomPatient());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				obj.setPatientID(rs.getInt(1));
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcContext.closeConnection();
	}


	@Override
	public void update(Patient obj) {
		PreparedStatement ps = null;
		try {
			ps = JdbcContext.getConnection().prepareStatement("update patient set nom=?,prenom=? where idpatient=?");
			ps.setString(1, obj.getNomPatient());
			ps.setString(2, obj.getPrenomPatient());
			ps.setInt(3, obj.getPatientID());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcContext.closeConnection();
		
	}

	@Override
	public void delete(Patient obj) {
		deleteByKey(obj.getPatientID());
		
	}

	@Override
	public void deleteByKey(Integer key) {
		PreparedStatement ps = null;
		try {
			//Faire attention car le cas où l'id du patient est dans la table visite n'est pas pris en compte
			ps = JdbcContext.getConnection().prepareStatement("delete from patient where idpatient=?");
			ps.setInt(1, key);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcContext.closeConnection();
		
	}

	@Override
	public Patient findByKey(Integer key) {
		Patient patient = null;
		PreparedStatement ps = null;
		try {
			ps = JdbcContext.getConnection().prepareStatement("select * from patient where idpatient=?");
			ps.setInt(1, key);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				patient = new Patient(rs.getInt("idpatient"), rs.getString("nom"), rs.getString("prenom"));
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcContext.closeConnection();
		return patient;
	}

	@Override
	public List<Patient> findAll() {
		List<Patient> patients = new ArrayList<>();
		Statement st = null;
		try {
			st = JdbcContext.getConnection().createStatement();
			ResultSet rs = st.executeQuery("select * from patient");
			while (rs.next()) {
				patients.add(new Patient(rs.getInt("idpatient"), rs.getString("nom"),rs.getString("prenom")));
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		JdbcContext.closeConnection();
		return patients;
	}

}
