package hopital.dao;

import hopital.model.Compte;

public interface DAOcompte extends DAOgeneric<Compte, Integer> {
    public int findIDByLogin(String login);
	
}
