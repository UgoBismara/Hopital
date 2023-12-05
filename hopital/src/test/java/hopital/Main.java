package hopital;

import java.util.Scanner;

import hopital.dao.DAOcompte;
import hopital.dao.DAOpatient;
import hopital.model.Compte;
import hopital.model.MedSec;
import hopital.model.Medecin;
import hopital.model.Patient;
import hopital.model.Secretaire;
import hopital.util.JdbcContext;

public class Main {

	private static void seConnecter() {
		DAOcompte daoCompte = JdbcContext.getDaoCompteJdbc();
		Scanner sc = new Scanner(System.in);
		System.out.print("entrez votre login");
		String login = sc.nextLine();
		Scanner sc2 = new Scanner(System.in);
		System.out.print("entrez votre password");
		String password = sc.nextLine();
		Compte test = new Compte(login, password);
		for (Compte compte : daoCompte.findAll()) {
			if (compte.equals(test)) {
				System.out.println("Vous êtes connectés !");
				break;
			} else {
				System.out.println("Réessayez");
			}
		}
	}

	private static void MenuMetier(Compte c) {
		if (c.getCompte() == MedSec.medecin) {
			Medecin medecin = new Medecin(c.getLogin(), c.getPassword(), MedSec.medecin);
			System.out.println("Choix 1 : Appel de la méthode Rendre la salle dispo");
			System.out.println("Choix 2 : Appel de la méthode Visualiser la liste d'attente");
			System.out.println("Choix 3 : Appel de la méthode Sauvegarder la liste de visite");
			Scanner sc = new Scanner(System.in);
			System.out.print("Entrez votre choix : ");
			int choix = sc.nextInt();
			switch (choix) {
			case 1:
				medecin.salleDispo();
				break;
			case 2:
				medecin.VisualiserListedAttente();
				break;
			case 3:
				medecin.SauvegardeListeVisite();
				break;
			default:
				seConnecter();
			}
		} else if (c.getCompte() == MedSec.secretaire) {
			Secretaire secretaire = new Secretaire(c.getLogin(), c.getPassword(), MedSec.secretaire);
			System.out.println("Choix 1 : Appel de la méthode Ajouter patient à la file d'attente");
			System.out.println("Choix 2 : Appel de la méthode Afficher la file d'attente");
			System.out.println("Choix 3 : Appel de la méthode Prendre une pause");
			System.out.println("Choix 3 : Appel de la méthode Retour de pause");
			Scanner sc = new Scanner(System.in);
			System.out.print("Entrez votre choix : ");
			int choix = sc.nextInt();
			switch (choix) {
			case 1:
				Scanner scg = new Scanner(System.in);
				System.out.print("Entrez un ID : ");
				int ID = scg.nextInt();
				secretaire.AjouterFileDAttente(ID);
				break;
			case 2:
				secretaire.AfficherFileDAttente();
				break;
			case 3:
				secretaire.PartirEnPause();
				break;
			case 4 : secretaire.retourPause();
			break;
			default:
				seConnecter();
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		seConnecter();
		DAOcompte daoCompte = JdbcContext.getDaoCompteJdbc();
		DAOpatient daoPatient = JdbcContext.getDaoPatientJdbc();
		daoPatient.insert(daoPatient.findByKey(8));
		Secretaire estelle = new Secretaire("1111", "1234", MedSec.secretaire);
		Patient ugo = daoPatient.findByKey(1);
		Patient alex = new Patient("Alex", "Girardot");
		MenuMetier(daoCompte.findByKey(2));
//		MenuMetier(daoCompte.findByKey(1));
//		estelle.PartirEnPause();
//		estelle.retourPause();
	}

}
