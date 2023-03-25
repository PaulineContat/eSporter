package modeles;

import connexion.ConnexionBDD;
import modele.Gestionnaire;

public class ModeleAccueilGestionnaire {

	private Gestionnaire gestionnaire;
	
	public ModeleAccueilGestionnaire(String identifiantGestionnaire) {
		ConnexionBDD.connecting();
		this.gestionnaire = ConnexionBDD.getGestionnaireFromIdGestionnaire(identifiantGestionnaire);
	}
	
	public String getPrenom() {
		return this.gestionnaire.getPrenom();
	}
	
	public String getNom() {
		return this.gestionnaire.getNom();
	}
	
}