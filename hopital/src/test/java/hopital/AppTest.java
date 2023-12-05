package hopital;

import java.time.LocalDate;

import hopital.dao.DAOcompte;
import hopital.dao.DAOpatient;
import hopital.dao.DAOvisite;
import hopital.model.Compte;
import hopital.model.MedSec;
import hopital.model.Patient;
import hopital.model.Visite;
import hopital.util.JdbcContext;

public class AppTest {
	public static void main(String[] args) {
		/* TEST DAOPATIENT */
		DAOpatient daoPatient = JdbcContext.getDaoPatientJdbc();
		Patient ugo = new Patient(6,"BISMARA", "Hugo");
		daoPatient.insert(ugo);
//		ugo.setPrenomPatient("Ugo");
//		daoPatient.update(ugo);
//		daoPatient.delete(ugo);
//		System.out.println(daoPatient.findByKey(ugo.getPatientID()));
//		System.out.println(daoPatient.findAll());
		
		/* TEST DAOCOMPTE */
		DAOcompte daoCompte = JdbcContext.getDaoCompteJdbc();
		Compte c = new Compte(3,"1111","1234",MedSec.medecin);
		daoCompte.insert(c);
		c.setLogin("Ugo");
		daoCompte.update(c);
		daoCompte.delete(c);
//		System.out.println(daoCompte.findByKey(2));
//		System.out.println(daoCompte.findAll());
		
		/* TEST DAOVISITE*/
//		DAOvisite daoVisite = JdbcContext.getDaoVisiteJdbc();
//		Visite v = new Visite(ugo,c,30,2,LocalDate.of(2023, 12, 5));
//		daoVisite.insert(v);
		
	}
}
