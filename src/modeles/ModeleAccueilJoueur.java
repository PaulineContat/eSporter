package modeles;

import java.util.List;

import connexion.ConnexionBDD;
import modele.Joueur;
import modele.Rencontre;

public class ModeleAccueilJoueur {
	private List<Rencontre> rencontres ;
	private Joueur joueur;
	
	public ModeleAccueilJoueur(Joueur joueur) {
		this.joueur = joueur;
		ConnexionBDD.connecting();
		this.rencontres = ConnexionBDD.getRencontresDunJoueur(this.joueur);
	}
	
	public List<Rencontre> getRencontres() {
		return this.rencontres;
	}

	public Rencontre getRencontre(int index) {
		return rencontres.get(index);
	}
}
