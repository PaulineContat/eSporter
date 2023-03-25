package controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modele.Compte;
import modeles.ModeleConnexion;
import vues.VueConnexion;

public class ControleurConnexion implements ActionListener{

	private VueConnexion vue;
	private ModeleConnexion modele;

	public ControleurConnexion(VueConnexion vue, ModeleConnexion modele) {
		this.vue = vue;
		this.modele=modele;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (this.vue.idEntre() && this.vue.pwdEntre()) {
			Compte compte = this.modele.getCompte(this.vue.getId());
			if (compte == null) {
				this.vue.erreurCompteInexistant();
			} else {
				switch (compte.connexion(this.vue.getPwd())) {
				case -1 :
					this.vue.erreurCompteBloque();
					break;
				case 0 :
					this.vue.erreurMauvaisMdp(compte.getNbEssais());
					this.vue.resetPwd();
					break;
				case 1 :
					this.vue.ouvreAccueil(compte);
				}
			}
		}
	}
}