package modeles;

import java.util.List;

import connexion.ConnexionBDD;
import modele.Jeu;

public class ModeleAdminJeux {

	private List<Jeu> jeux;
	
	public ModeleAdminJeux() {
		this.jeux = ConnexionBDD.getJeux();
	}
	
	public List<Jeu> getJeux(){
		return this.jeux;
	}

	public void updateData() {
		this.jeux = ConnexionBDD.getJeux();
	}
}