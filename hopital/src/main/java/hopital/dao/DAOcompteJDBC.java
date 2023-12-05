package hopital.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hopital.model.Compte;
import hopital.model.MedSec;
import hopital.model.Patient;
import hopital.util.JdbcContext;

public class DAOcompteJDBC implements DAOcompte {

	@Override
	public void insert(Compte obj) {
		PreparedStatement ps = null;
		try {
			ps = JdbcContext.getConnection().prepareStatement(
					"insert into compte(login, password,typeCompte) values(?,?,?)", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, obj.getLogin());
			ps.setString(2, obj.getPassword());
			ps.setString(3, obj.getCompte().name());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				obj.setID(rs.getInt(1));
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcContext.closeConnection();

	}

	@Override
	public void update(Compte obj) {
		PreparedStatement ps = null;
		try {
			ps = JdbcContext.getConnection().prepareStatement("update compte set login=?,password=?, typeCompte =? where idcompte=?");
			ps.setString(1, obj.getLogin());
			ps.setString(2, obj.getPassword());
			ps.setString(3, obj.getCompte().name());
			ps.setInt(4, obj.getID());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcContext.closeConnection();
	}

	@Override
	public void delete(Compte obj) {
		deleteByKey(obj.getID());

	}

	@Override
	public void deleteByKey(Integer key) {
		PreparedStatement ps = null;
		try {
			//Faire attention car le cas où l'id du compte est dans la table visite n'est pas pris en compte
			ps = JdbcContext.getConnection().prepareStatement("delete from compte where idcompte=?");
			ps.setInt(1, key);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcContext.closeConnection();

	}

	@Override
	public Compte findByKey(Integer key) {
		Compte compte = null;
		PreparedStatement ps = null;
		try {
			ps = JdbcContext.getConnection().prepareStatement("select * from compte where idcompte=?");
			ps.setInt(1, key);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				compte = new Compte(rs.getInt("idcompte"), rs.getString("login"), rs.getString("password"),MedSec.valueOf(rs.getString("typeCompte")));
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcContext.closeConnection();
		return compte;
	}

	@Override
	public List<Compte> findAll() {
		List<Compte> comptes = new ArrayList<>();
		Statement st = null;
		try {
			st = JdbcContext.getConnection().createStatement();
			ResultSet rs = st.executeQuery("select * from compte");
			while (rs.next()) {
				comptes.add(new Compte(rs.getInt("idcompte"), rs.getString("login"),rs.getString("password"),MedSec.valueOf(rs.getString("typeCompte"))));
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		JdbcContext.closeConnection();
		return comptes;
	}

}
