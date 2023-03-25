package controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import modele.Jeu;
import modeles.ModeleSupprModifJeu;
import vues.VueSupprModifJeu;

public class ControleurSupprModifJeu implements ActionListener {

	private VueSupprModifJeu vue;
	private ModeleSupprModifJeu modele;
	
	public ControleurSupprModifJeu(VueSupprModifJeu vue, ModeleSupprModifJeu modele) {
		this.vue = vue;
		this.modele = modele;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(e.getSource() instanceof JButton) {
			JButton bouton = (JButton) source;
			String buttonText = bouton.getText();
			switch(buttonText) {
			case "Valider les modifications":
				if (this.vue.getNom() != "") {
					 this.modele.modifierJeu(new Jeu(this.modele.getJeu().getIdJeu(),this.vue.getNom(),this.vue.getNbJoueurs()));
					 if (this.modele.modifEnregistree()) {
						 this.vue.infoModifEnregistree();
						 this.modele.refresh();
					 } else {
						 this.vue.erreurModifNonEnregistree();
					 }
				 } else {
					 this.vue.erreurChampsNonRemplis();
				 }
				break;
			case "Supprimer le jeu":
				this.modele.supprimerJeu();
				this.modele.refresh();
				this.vue.infoJeuSupprime();
				this.vue.dispose();
				break;
			case "Fermer":
				this.vue.dispose();
				break;
			}
		}
	}
	
}
