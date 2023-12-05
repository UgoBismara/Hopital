package generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AppTest {
	public static void main(String[] args) {

		Map<Auteur, Collection<Livre>> bibliotheque = new HashMap<>();
		Collection<Livre> livreHugo = new ArrayList<>();

		livreHugo.add(new Livre("les miserables", "gallimard"));
		livreHugo.add(new Livre("notre-dame de paris", "inconnu"));

		bibliotheque.put(new Auteur("victor", "hugo"), livreHugo);

		Collection<Livre> livreGrange = new ArrayList<>();
		livreGrange.add(new Livre("les rivieres pourpres", "albain michel"));
		livreGrange.add(new Livre("le serment des limbes", "albain michel"));

		bibliotheque.put(new Auteur("jean christophe", "grange"), livreGrange);

		// recuperation de livre

		Collection<Livre> collectionDeVictorHugo = bibliotheque.get(new Auteur("victor", "hugo"));

		for(Livre livre:collectionDeVictorHugo) {
			System.out.println(livre.getTitre());
		}
//		DemoGeneric<String> demoAvecString = new DemoGeneric<String>("toto");
//		
//		DemoGeneric<Integer> demoAvecInteger=new DemoGeneric<Integer>(12);
//		
//		String s="toto";
//		Integer integer=100;
//		
//		Integer unInteger=null;
//		int unInt=0;
//		
//		// boolean  =>Boolean
//		// byte		=>Byte
//		// short	=>Short
//		// int		=>Integer
//		// long		=>Long
//		// float	=>Float
//		// double	=>Double
//		
//		// char		=>Character
//		
//		Map<String, String> langageDeProgrammation=new HashMap<>();
//		
//		langageDeProgrammation.put("java", "langage objet");
//		langageDeProgrammation.put("c", "langage à l'origine des langages moderne et du systeme unix");
//		langageDeProgrammation.put("c#", "objet à la sauce microsoft");
//		
//		System.out.println(langageDeProgrammation.get("java"));
//		
//		//parcours à partir des cles
//		for(String key: langageDeProgrammation.keySet()) {
//			System.out.println(langageDeProgrammation.get(key));
//		}

//		//parcours à partir des elements de la map
//		for(String element: langageDeProgrammation.values()) {
//			System.out.println(element);
//		}

	}
}
