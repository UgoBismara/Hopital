package hopital.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import hopital.dao.DAOvisite;
import hopital.util.JdbcContext;

public class Medecin extends Compte {
	
	int salleChoisie=0;
	int compteurVisite=0;
	
	public int getCompteurVisite() {
		return compteurVisite;
	}
	public void setCompteurVisite(int compteurVisite) {
		this.compteurVisite = compteurVisite;
	}
	
	public void setSalleChoisie(int salleChoisie) {
		this.salleChoisie = salleChoisie;
	}
	public int getSalleChoisie() {
		return salleChoisie;
	}
		
	
	public Medecin(String login, String password, MedSec compte) {
		super(login, password, compte);
	}

	
	public static List<Visite> listeVisite= new ArrayList<>();
	

	public void salleDispo() {
		Visite nouvelleVisite= new Visite(Secretaire.getListeAttente().get(1), this, salleChoisie, LocalDate.now());
		listeVisite.add(nouvelleVisite);
		setCompteurVisite(getCompteurVisite()+1);
		if(getCompteurVisite()==10) {
			SauvegardeListeVisite();
		}
	}
	
	public void VisualiserListedAttente() {
		
		System.out.println("Liste des patients: ");
		for(Patient patient : Secretaire.getListeAttente()){
		System.out.println(patient.getNomPatient()+" "+patient.getPrenomPatient());
		}
		System.out.println("Prochain patient :"+Secretaire.listeAttente.get(1).getNomPatient()+" "+Secretaire.listeAttente.get(1).getPrenomPatient());
	}
	
	public void SauvegardeListeVisite() {
		for(Visite visite:listeVisite) {
			DAOvisite daoVisite = JdbcContext.getDaoVisiteJdbc();
			daoVisite.insert(visite);
		}
		listeVisite.clear();
	}
}
