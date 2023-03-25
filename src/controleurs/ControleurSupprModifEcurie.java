package controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import modele.Ecurie;
import modeles.ModeleSupprModifEcurie;
import vues.VueSupprModifEcurie;

public class ControleurSupprModifEcurie implements ActionListener {

	private VueSupprModifEcurie vue;
	private ModeleSupprModifEcurie modele;
	
	public ControleurSupprModifEcurie(VueSupprModifEcurie vue, ModeleSupprModifEcurie modele) {
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
				if (this.vue.tousLesChampsCorrects()) {
					 this.modele.modifierEcurie(new Ecurie(this.modele.getEcurie().getCompte().getIdentifiant(),this.vue.getNom(), this.modele.getEcurie().getCompte().getMdp(), this.vue.getStatut()));
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
			case "Supprimer l'écurie":
				this.modele.supprimerEcurie();
				this.modele.refresh();
				this.vue.infoEcurieSupprime();
				this.vue.dispose();
				break;
			case "Fermer":
				this.vue.dispose();
				break;
			}
		}
	}

}
