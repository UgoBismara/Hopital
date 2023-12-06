package hopital;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

	public static String saisieString(String message) {
		Scanner sc = new Scanner(System.in);
		System.out.println(message);
		return sc.nextLine();
	}

	public static int saisieInt(String message) {
		Scanner sc = new Scanner(System.in);
		System.out.println(message);
		return sc.nextInt();
	}

	private static Compte seConnecter() {
		DAOcompte daoCompte = JdbcContext.getDaoCompteJdbc();
		Compte test = null;
		while (true) {
			String login = saisieString("entrez votre login");
			String password = saisieString("entrez votre password");
			test = new Compte(login, password);
			for (Compte compte : daoCompte.findAll()) {
				if (compte.equals(test)) {
					System.out.println("Vous êtes connectés !");
					test.setCompte(compte.getCompte());
					return test;
				}
			}
			System.out.println("Réessayez");
		}
	}

	private static void MenuMedecin(Medecin medecin) {
		int choix = 0;
		while (choix != 4) {
			System.out.println("Choix 1 : Appel de la méthode Rendre la salle dispo");
			System.out.println("Choix 2 : Appel de la méthode Visualiser la liste d'attente");
			System.out.println("Choix 3 : Appel de la méthode Sauvegarder la liste de visite");
			System.out.println("Choix 4 : Se déconnecter");
			choix = saisieInt("entrez votre choix");
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
			case 4:
				System.out.println("Deconnexion");
			default:
			}
		}
	}

	private static void MenuSecretaire(Secretaire secretaire) {
		int choix = 0;
		while (choix != 0) {
			System.out.println("Choix 1 : Appel de la méthode Ajouter patient à la file d'attente");
			System.out.println("Choix 2 : Appel de la méthode Afficher la file d'attente");
			System.out.println("Choix 3 : Appel de la méthode Prendre une pause");
			System.out.println("Choix 4 : Déconnexion");
			choix = saisieInt("entrez votre choix");
			switch (choix) {
			case 1:
				int ID2 = saisieInt("Entrez l'ID du patient à ajouter");
				secretaire.AjouterFileDAttente(ID2);
				break;
			case 2:
				secretaire.AfficherFileDAttente();
				break;
			case 3:
				secretaire.PartirEnPause();
				System.out.println("Vous êtes en pause !");
				saisieInt("tapez quelque chose pour revenir de pause");
				secretaire.retourPause();
				System.out.println("Fin de la pause");
				break;
			case 4:
				System.out.println("Deconnexion");
			default:
			}
		}
	}

	private static void MenuMetier(Compte c) {
		if (c.getCompte() == MedSec.medecin) {
			Medecin medecin = new Medecin(c.getID(),c.getLogin(), c.getPassword(), MedSec.medecin);
			medecin.setSalleChoisie(saisieInt("Choisissez la salle 1 ou 2"));
			MenuMedecin(medecin);
		} else if (c.getCompte() == MedSec.secretaire) {
			Secretaire secretaire = new Secretaire(c.getLogin(), c.getPassword(), MedSec.secretaire);
			MenuSecretaire(secretaire);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/* while (continue) */
//		DAOcompte daoCompte = JdbcContext.getDaoCompteJdbc();
		DAOpatient daoPatient = JdbcContext.getDaoPatientJdbc();
		/* daoPatient.insert(daoPatient.findByKey()); */
//		Secretaire estelle = new Secretaire("1111", "1234", MedSec.secretaire);
//		Patient ugo = daoPatient.findByKey(1);
//		Patient alex = new Patient("Alex", "Girardot");
		List<Patient> patients = new ArrayList<>(Arrays.asList(daoPatient.findByKey(1), new Patient("Alicia","Dorbani"),new Patient("Ugo","Bismara")));
		Secretaire.setListeAttente(patients);
		Compte compteConnecte = seConnecter();
		MenuMetier(compteConnecte);
//		MenuMetier(daoCompte.findByKey(1));
//		estelle.PartirEnPause();
//		estelle.retourPause();
	}
}
