package controleurs;

import javax.swing.table.DefaultTableModel;

import connexion.ConnexionBDD;
import modele.Joueur;
import modele.Rencontre;
import modeles.ModeleAccueilJoueur;
import vues.VueAccueilJoueur;

public class ControleurAccueilJoueur {

	private ModeleAccueilJoueur modele;
	Joueur joueur;
	
	public ControleurAccueilJoueur(VueAccueilJoueur vue, String identifiantJoueur) {
		ConnexionBDD.connecting();
		this.joueur = ConnexionBDD.getJoueurFromIdJoueur(identifiantJoueur);
		this.modele = new ModeleAccueilJoueur(joueur);
	}
	
	public void remplirTableauRencontres(DefaultTableModel model) {
		for (Rencontre rencontre:this.modele.getRencontres()) {
			model.addRow(new Object[] {rencontre.getTournoi().getNom(),rencontre.getHeure(),rencontre.getEquipe1().getNom(), rencontre.getEquipe2().getNom()});
		}
	}
	
	public Joueur getJoueur() {
		return this.joueur;
	}
}
