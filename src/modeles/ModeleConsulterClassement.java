package modeles;

import java.util.List;

import connexion.ConnexionBDD;
import modele.Equipe;
import modele.Tournoi;

public class ModeleConsulterClassement {

	private List<Equipe> equipes;

	public ModeleConsulterClassement() {
		this.equipes = ConnexionBDD.getEquipesTri();
	}
	
	public List<Equipe> getEquipes(){
		return this.equipes;
	}
	
	public List<Tournoi> getTournoisFromEquipe(Equipe e){
		return ConnexionBDD.getTournoisFromEquipe(e);
	}
	
}
