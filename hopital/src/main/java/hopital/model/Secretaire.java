package hopital.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import hopital.dao.DAOpatient;
import hopital.util.JdbcContext;

public class Secretaire extends Compte {

	static List<Patient> listeAttente = new ArrayList<>();

	public static List<Patient> getListeAttente() {
		return listeAttente;
	}
	
	public static void setListeAttente(List<Patient> listeAttente) {
		Secretaire.listeAttente = listeAttente;
	}

	public Secretaire(String login, String password, MedSec compte) {
		super(login, password, compte);
		// TODO Auto-generated constructor stub
	}
	
	
	public void AjouterFileDAttente(int ID) {
		DAOpatient daoPatient = JdbcContext.getDaoPatientJdbc();
		if (daoPatient.findByKey(ID) != null) {
			listeAttente.add(daoPatient.findByKey(ID));
		} else {
			Scanner sc = new Scanner(System.in);
			System.out.println("entrez votre nom");
			String nom = sc.nextLine();
			Scanner scn = new Scanner(System.in);
			System.out.println("entrez votre prénom");
			String prenom = scn.nextLine();
			Patient x = new Patient(nom, prenom);
			listeAttente.add(x);
			daoPatient.insert(x);
		}
	}
	
	public void AfficherFileDAttente() {
		for (Patient unPatient : listeAttente) {
			System.out.println(unPatient.getNomPatient());
		}
	}

	public void PartirEnPause() {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream("ListeAttente");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(this.listeAttente);
			oos.close();
			fos.close();
			listeAttente.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void retourPause() {
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream("ListeAttente"));
			List<Patient> patients = (List<Patient>) ois.readObject();
			for (Patient patient : patients) {
				listeAttente.add(patient);
			}
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
