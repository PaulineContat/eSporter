package modeles;

import connexion.ConnexionBDD;
import modele.Arbitre;
import modele.Compte;
import modele.Personne;
import vues.VueAdminArbitres;;

public class ModeleAjoutArbitre {

	private VueAdminArbitres vue;

	public ModeleAjoutArbitre(VueAdminArbitres vue) {
		this.vue = vue;
	}

	public String getId(String prenom, String nom) {
		return Personne.generateId(prenom, nom);
	}

	public boolean idAlreadyExists(String id) {
		return ConnexionBDD.getArbitreFromIdArbitre(id) != null;
	}

	public void ajouterArbitre(String prenom, String nom, String tel, String mdp) {
		ConnexionBDD.enregistrerArbitre(new Arbitre(Personne.generateId(prenom, nom), nom, prenom, tel, Compte.passwordHash(mdp)));
	}

	public void refresh() {
		this.vue.afficherArbitre();
	}

}
