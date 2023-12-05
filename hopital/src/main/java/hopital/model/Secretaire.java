package hopital.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import hopital.dao.DAOpatient;
import hopital.dao.DAOpatientJDBC;
import hopital.util.JdbcContext;


public class Secretaire extends Compte {
	
	public List<Patient> patients = new ArrayList<>();

	public Secretaire(String login, String password, MedSec compte) {
		super(login, password, compte);
		// TODO Auto-generated constructor stub
	}

	public void AjouterFileDAttente(Patient patient) {
		DAOpatient daoPatient = JdbcContext.getDaoPatientJdbc();
		Scanner sct = new Scanner(System.in);
		System.out.println("Votre ID : (Si vous n'en avez pas tapé 0)");
		int ID = sct.nextInt();
		if (daoPatient.findByKey(ID) != null) {
			patients.add(patient);
		} else {
			Scanner sc = new Scanner(System.in);
			System.out.println("entrez votre nom");
			String nom = sc.nextLine();
			Scanner scn = new Scanner(System.in);
			System.out.println("entrez votre prénom");
			String prenom = scn.nextLine();
			Patient x = new Patient(nom, prenom) ;
			patients.add(x);
			
			daoPatient.insert(x);
		}	
	}
	
	public void AfficherFileDAttente() {
		for(Patient unPatient:patients) {
			System.out.println(unPatient.getNomPatient());
		}
	}
	
	public void PartirEnPause() {
		
	}
}
