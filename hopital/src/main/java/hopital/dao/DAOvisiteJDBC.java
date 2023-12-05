package hopital.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import hopital.model.Patient;
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
			if (obj.getPatient() != null) {
				ps.setInt(1, obj.getPatient().getPatientID());
			} else {
				ps.setNull(1, Types.INTEGER);
			}
			if (obj.getMedecin() != null) {
				ps.setInt(2, obj.getMedecin().getID());
			} else {
				ps.setNull(2, Types.INTEGER);
			}
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
		PreparedStatement ps = null;
		try {
			ps = JdbcContext.getConnection().prepareStatement(
					"update visite set idpatient=?, idmedecin=?, coutVisite=?, numeroSalle=?, dateVisite=? where numeroVisite=?",
					Statement.RETURN_GENERATED_KEYS);
			if (obj.getPatient() != null) {
				ps.setInt(1, obj.getPatient().getPatientID());
			} else {
				ps.setNull(1, Types.INTEGER);
			}
			if (obj.getMedecin() != null) {
				ps.setInt(2, obj.getMedecin().getID());
			} else {
				ps.setNull(2, Types.INTEGER);
			}
			ps.setDouble(3, obj.getCoutVisite());
			ps.setInt(4, obj.getSalle());
			ps.setDate(5, Date.valueOf(obj.getDateVisite()));
			ps.setInt(6, obj.getNumeroVisite());
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
	public void delete(Visite obj) {
		deleteByKey(obj.getNumeroVisite());
	}

	@Override
	public void deleteByKey(Integer key) {
		Connection connection = JdbcContext.getConnection();
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement("delete from visite where numeroVisite=?");
			ps.setInt(1, key);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcContext.closeConnection();
	}

	@Override
	public Visite findByKey(Integer key) {
		Visite visite = null;
		PreparedStatement ps = null;
		try {
			ps = JdbcContext.getConnection().prepareStatement(
					"select v.numeroVisite, v.idpatient, v.idmedecin, v.coutVisite, v.numeroSalle, v.dateVisite, p.nom, p.prenom"
							+ "from visite v left join patient p on v.idpatient=p.idpatient where idpatient=?");
			ps.setInt(1, key);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				visite = new Visite(rs.getInt("numeroVisite"), rs.getInt("numeroSalle"),
						rs.getDate("dateVisite") != null ? rs.getDate("dateVisite").toLocalDate() : null);
				if (rs.getInt("idpatient") != 0) {
					visite.setPatient(new Patient(rs.getInt("idpatient"), rs.getString("nom"), rs.getString("prenom")));
				}
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcContext.closeConnection();
		return visite;
	}

	@Override
	public List<Visite> findAll() {
		Connection connection = JdbcContext.getConnection();
		List<Visite> visites = new ArrayList<>();
		Visite visite = null;
		Statement st = null;
		try {
			st = connection.createStatement();
			ResultSet rs = st.executeQuery(
					"select v.numeroVisite, v.idpatient, v.idmedecin, v.coutVisite, v.numeroSalle, v.dateVisite, p.nom, p.prenom"
							+ "from visite v left join patient p on v.idpatient=p.idpatient)");
			while (rs.next()) {
					visite = new Visite(rs.getInt("numeroVisite"), rs.getInt("numeroSalle"),
							rs.getDate("dateVisite") != null ? rs.getDate("dateVisite").toLocalDate() : null);
					if (rs.getInt("idpatient") != 0) {
						visite.setPatient(new Patient(rs.getInt("idpatient"), rs.getString("nom"), rs.getString("prenom")));
					}
					visites.add(visite);
			} 
				st.close();
			} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcContext.closeConnection();
		return visites;
	}
}


