package hopital.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.time.LocalDate;

import hopital.model.Visite;
import hopital.util.JdbcContext;

public class DAOvisiteJDBC implements DAOvisite {

	@Override
	public void insert(Visite obj) {
		PreparedStatement ps = null;
		try {
			ps = JdbcContext.getConnection().prepareStatement(
					"insert into visite(idpatient, idmedecin, coutVisite, numeroSalle, dateVisite) values(?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, obj.getPatient().getPatientID());
			ps.setInt(2, obj.getMedecin().getID());
			ps.setDouble(3, obj.getCoutVisite());
			ps.setInt(4, obj.getSalle());
			ps.setDate(5, Date.valueOf(obj.getDateVisite()));
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				obj.setNumeroVisite(rs.getInt(1));
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcContext.closeConnection();
	}

	@Override
	public void update(Visite obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Visite obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteByKey(Integer key) {
		// TODO Auto-generated method stub

	}

	@Override
	public Visite findByKey(Integer key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Visite> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
