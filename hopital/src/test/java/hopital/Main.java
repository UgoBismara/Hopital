package hopital;

import hopital.model.MedSec;
import hopital.model.Patient;
import hopital.model.Secretaire;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Secretaire estelle = new Secretaire("1111","1234",MedSec.secretaire);
		Patient ugo = new Patient("Ugo", "Bismara");
		estelle.AjouterFileDAttente(ugo);
		estelle.AfficherFileDAttente();
		estelle.PartirEnPause();
		estelle.retourPause();
		//Authentification
		//
	}

}
