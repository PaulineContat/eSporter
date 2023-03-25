package modeles;

import java.util.List;

import connexion.ConnexionBDD;
import modele.Equipe;

public class ModeleAdminEquipes {

	private List<Equipe> equipes;
	
	public ModeleAdminEquipes() {
		this.equipes = ConnexionBDD.getEquipes();
	}
	
	public List<Equipe> getEquipes(){
		return this.equipes;
	}

	public void updateData() {
		this.equipes = ConnexionBDD.getEquipes();
	}
}