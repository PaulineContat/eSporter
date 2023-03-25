package controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import modele.Jeu;
import modeles.ModeleAjoutJeu;
import vues.VueAjoutJeu;

public class ControleurAjoutJeu implements ActionListener {

	private VueAjoutJeu vue;
	private ModeleAjoutJeu modele;

	public ControleurAjoutJeu(VueAjoutJeu vue, ModeleAjoutJeu modele) {
		this.vue = vue;
		this.modele = modele;
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source instanceof JButton) {
			JButton button = (JButton) source;
			String buttonText = button.getText();
			switch(buttonText) {
			case "Valider":
				if(this.vue.nomEntre()) {
					if(!this.modele.idAlreadyExists(Jeu.generateId(this.vue.getNomJeu()))) {
						this.modele.ajouterJeu(this.vue.getNomJeu(), this.vue.getNbJoueurs());
						this.modele.refresh();
						this.vue.dispose();
					} else {
						this.vue.erreurIdentifiantExisteDeja();
					}
				} else {
					this.vue.erreurChampsNonRemplis();
				}
				break;
			case "Annuler":
				this.vue.dispose();
				break;
			}
		}
	}
	
}
