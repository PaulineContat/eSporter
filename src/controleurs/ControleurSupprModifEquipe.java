package controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import modele.Equipe;
import modeles.ModeleSupprModifEquipe;
import vues.VueSupprModifEquipe;

public class ControleurSupprModifEquipe implements ActionListener {

	VueSupprModifEquipe vue;
	ModeleSupprModifEquipe modele;
	
	public ControleurSupprModifEquipe(VueSupprModifEquipe vue, ModeleSupprModifEquipe modele) {
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
				if (!this.vue.getNom().isEmpty()) {
					this.modele.modifierEquipe(new Equipe(this.modele.getEquipe().getCompte().getIdentifiant(),this.vue.getNom(),this.modele.getEquipe().getCompte().getMdp(), this.modele.getEquipe().getJeu(), this.modele.getEquipe().getEcurie()));
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
			case "Supprimer l'équipe":
				this.modele.supprimerEquipe();
				this.modele.refresh();
				this.vue.infoEquipeSupprime();
				this.vue.dispose();
				break;
			case "Fermer":
				this.vue.dispose();
				break;
			}
		}
	}
}