package controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import modele.Ecurie;
import modele.Jeu;
import modeles.ModeleAjoutEcurie;
import vues.VueAjoutEcurie;

public class ControleurAjoutEcurie implements ActionListener {

	private VueAjoutEcurie vue;
	private ModeleAjoutEcurie modele;

	public ControleurAjoutEcurie(VueAjoutEcurie vue, ModeleAjoutEcurie modele) {
		this.vue = vue;
		this.modele = modele;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source instanceof JButton) {
			JButton button = (JButton) source;
			String buttonText = button.getText();
			switch(buttonText) {
			case "Valider":
				if(this.vue.tousLesChampsCorrects()) {
					if(!this.modele.idAlreadyExists(Ecurie.generateId(this.vue.getNom()))) {
						this.modele.ajouterEcurie(this.vue.getNom(), this.vue.getPwd(), this.vue.getStatut());
						if(this.modele.ajoutEnregistree()) {
							this.vue.infoEcurieEnregistree();
							this.modele.refresh();
							this.vue.dispose();
						} else {
							this.vue.erreurEcurieNonEnregistree();
						}
					} else {
						this.vue.erreurEcurieExisteDeja();
					}
				} else if(!this.vue.nomEntre()) {
					this.vue.erreurNomNonEntre();
				} else if(this.vue.getPwd().isEmpty()) {
					this.vue.erreurPwdNonEntre();
				} else if(!this.vue.statutEntre()){
					this.vue.erreurStatutNonEntre();
				} else if(!this.vue.mdpCorrect()) {
					this.vue.erreurMdpDifferents();
				}
				break;
			case "Annuler":
				this.vue.dispose();
				break;
			}
		}
	}
}

