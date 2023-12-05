package hopital.model;

import java.util.Objects;

public class Compte {
	private String login;
	private String password;
	private MedSec compte;
	
	public Compte(String login, String password, MedSec compte) {
		this.login = login;
		this.password = password;
		this.compte = compte;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public MedSec getCompte() {
		return compte;
	}

	public void setCompte(MedSec compte) {
		this.compte = compte;
	}

	@Override
	public int hashCode() {
		return Objects.hash(login, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Compte other = (Compte) obj;
		return Objects.equals(login, other.login) && Objects.equals(password, other.password);
	}
	
	
}
