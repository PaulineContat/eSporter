package modeles;

import java.util.List;

import connexion.ConnexionBDD;
import modele.Joueur;
import modele.Rencontre;

public class ModeleAdminJoueur {
	private List<Joueur> joueurs ;
	
	public ModeleAdminJoueur() {
		ConnexionBDD.connecting();
	}
	
	public List<Joueur> getJoueurs() {
		this.joueurs = ConnexionBDD.getJoueurs();
		return this.joueurs;
	}

	public Joueur getJoueur(int index) {
		return joueurs.get(index);
	}
}
