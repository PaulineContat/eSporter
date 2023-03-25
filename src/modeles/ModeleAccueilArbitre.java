package modeles;

import java.util.List;

import connexion.ConnexionBDD;
import modele.Arbitre;
import modele.Rencontre;
import vues.VueAccueilArbitre;

public class ModeleAccueilArbitre {
	private List<Rencontre> rencontres ;
	private Arbitre arbitre;
	
	public ModeleAccueilArbitre(String identifiantArbitre) {
		ConnexionBDD.connecting();
		this.arbitre = ConnexionBDD.getArbitreFromIdArbitre(identifiantArbitre);
		this.rencontres = ConnexionBDD.getRencontresDunArbitre(this.arbitre);
	}
	
	public List<Rencontre> getRencontres() {
		return this.rencontres;
	}

	public Rencontre getRencontre(int index) {
		return rencontres.get(index);
	}

	public String getPrenom() {
		return this.arbitre.getPrenom();
	}
	
	public String getNom() {
		return this.arbitre.getNom();
	}
}
