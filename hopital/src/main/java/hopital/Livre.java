package generic;

public class Livre {
	private String titre;
	private String editeur;

	public Livre(String titre, String editeur) {
		this.titre = titre;
		this.editeur = editeur;
	}

	public String getTitre() {
		return titre;
	}

	public String getEditeur() {
		return editeur;
	}

}
