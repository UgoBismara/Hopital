package hopital.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Secretaire extends Compte {
	
	List<Patient> patients = new ArrayList<>();

	public Secretaire(String login, String password, MedSec compte) {
		super(login, password, compte);
		// TODO Auto-generated constructor stub
	}

	public void AjouterFileDAttente(Patient patient) {
		if (patient.getPatientID()!=null) {
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
