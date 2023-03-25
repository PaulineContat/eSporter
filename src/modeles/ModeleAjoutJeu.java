package modeles;

import connexion.ConnexionBDD;
import modele.Jeu;
import vues.VueAdminJeux;

public class ModeleAjoutJeu {

	private VueAdminJeux vue;
	
	public ModeleAjoutJeu(VueAdminJeux vue) {
		this.vue = vue;
	}

	public boolean idAlreadyExists(String id) {
		return ConnexionBDD.getJeuFromIdJeu(id) != null;
	}
	
	public void ajouterJeu(String nom, int nbJoueurs) {
		ConnexionBDD.enregistrerJeu(new Jeu(Jeu.generateId(nom), nom, nbJoueurs));
	}
	
	public void refresh() {
		this.vue.afficherJeux();
	}
}