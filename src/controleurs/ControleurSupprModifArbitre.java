package controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import modele.Arbitre;
import modeles.ModeleSupprModifArbitre;
import vues.VueSupprModifArbitre;

public class ControleurSupprModifArbitre implements ActionListener {

	private VueSupprModifArbitre vue;
	private ModeleSupprModifArbitre modele;

	public ControleurSupprModifArbitre(VueSupprModifArbitre vue, ModeleSupprModifArbitre modele) {
		this.vue = vue;
		this.modele = modele;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		switch (button.getText()) {
			case "Valider les modifications":
				if (this.vue.tousLesChampsCorrects()) {
					this.modele.modifierArbitre(new Arbitre(modele.getId(), vue.getNom(), vue.getPrenom(), vue.getTelephone(), modele.getMdp()));
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
			case "Supprimer":
				this.modele.supprimerArbitre();
				this.modele.refresh();
				this.vue.infoArbitreSupprime();
				this.vue.dispose();
				break;
			case "Fermer":
				this.vue.dispose();
				break;
		}
	}

}
