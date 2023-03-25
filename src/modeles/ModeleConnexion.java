package modeles;

import connexion.ConnexionBDD;
import modele.Compte;

public class ModeleConnexion {

	public ModeleConnexion() {
		ConnexionBDD.connecting();
	}

	public Compte getCompte(String id) {
		Compte compte = ConnexionBDD.getCompteFromId(id);
		return compte;
	}
}
