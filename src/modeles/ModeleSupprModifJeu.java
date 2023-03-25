package modeles;

import connexion.ConnexionBDD;
import modele.Jeu;
import vues.VueAdminJeux;

public class ModeleSupprModifJeu {

	private Jeu jeu;
	private VueAdminJeux vue;
	private Jeu jeuModifie;
	
	public ModeleSupprModifJeu(Jeu jeu, VueAdminJeux vue) {
		this.jeu = jeu;
		this.vue = vue;
	}
	
	public Jeu getJeu() {
		return this.jeu;
	}
	
	public void refresh() {
		this.vue.afficherJeux();
	}
	
	public void modifierJeu(Jeu jeuModifie) {
		this.jeuModifie = jeuModifie;
		ConnexionBDD.modifierJeu(jeuModifie);
	}
	
	public void supprimerJeu() {
		ConnexionBDD.supprimerJeu(this.jeu);
	}
	
	public boolean modifEnregistree() {
		Jeu jeuBDD = ConnexionBDD.getJeuFromIdJeu(this.jeu.getIdJeu());
		return (jeuBDD.getNom().equals(this.jeuModifie.getNom()) && jeuBDD.getNbJoueurs() == this.jeuModifie.getNbJoueurs());
	}
}
