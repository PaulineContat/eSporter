package controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import modeles.ModeleAjoutArbitre;
import vues.VueAjoutArbitre;

public class ControleurAjoutArbitre implements ActionListener {

	private VueAjoutArbitre vue;
	private ModeleAjoutArbitre modele;

	public ControleurAjoutArbitre(VueAjoutArbitre vue, ModeleAjoutArbitre modele) {
		this.vue = vue;
		this.modele = modele;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		if (button.getText() == "Annuler") {
			this.vue.dispose();
		} else {
			if (this.vue.tousLesChampsCorrects()) {
				if (!this.modele.idAlreadyExists(this.modele.getId(this.vue.getPrenom(), this.vue.getNom()))) {
					this.modele.ajouterArbitre(this.vue.getPrenom(), this.vue.getNom(), this.vue.getTelephone(), this.vue.getMDP());
					this.modele.refresh();
					this.vue.dispose();
				} else {
					this.vue.erreurIdentifiantExisteDeja();
				}
			} else {
				this.vue.erreurChampsNonRemplis();
			}
		}
	}

}
