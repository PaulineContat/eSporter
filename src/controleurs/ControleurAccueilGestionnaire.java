package controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import modeles.ModeleAccueilGestionnaire;
import vues.VueAccueilGestionnaire;
import vues.VueAdminEcuries;
import vues.VueAdminEquipes;
import vues.VueAdminJeux;
import vues.VueConnexion;
import vues.VueConsulterClassement;

public class ControleurAccueilGestionnaire implements ActionListener {

	private VueAccueilGestionnaire vue;

	public ControleurAccueilGestionnaire(VueAccueilGestionnaire vue) {
		this.vue=vue;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source instanceof JButton) {
			JButton button = (JButton) source;
			String buttonText = button.getText();
			switch(buttonText) {
			case "Jeux":
				VueAdminJeux adminJeux;
				adminJeux = new VueAdminJeux();
				adminJeux.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				adminJeux.setVisible(true);
				break;
//			case "Tournois":
//				VueAdminTournois adminTournois = new VueAdminTournois();
//				adminTournois.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//				adminTournois.setVisible(true);
//				break;
			case "Equipes":
				VueAdminEquipes adminEquipes = new VueAdminEquipes();
				adminEquipes.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				adminEquipes.setVisible(true);
				break;
			case "Ecuries":
				VueAdminEcuries adminEcuries = new VueAdminEcuries();
				adminEcuries.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				adminEcuries.setVisible(true);
				break;
//			case "Joueurs":
//				VueAdminJoueurs adminJoueurs = new VueAdminJoueurs();
//				adminJoueurs.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//				adminJoueurs.setVisible(true);
//				break;
//			case "Arbitres":
//				VueAdminArbitres adminArbitres = new VueAdminArbitres();
//				adminArbitres.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//				adminArbitres.setVisible(true);
//				break;
//			case "Responsables":
//				VueAdminResponsables adminResponsables = new VueAdminResponsables();
//				adminResponsables.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//				adminResponsables.setVisible(true);
//				break;
			case "Classement général":
				VueConsulterClassement classement;
				classement = new VueConsulterClassement();
				classement.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				classement.setVisible(true);
				break;
			case "Se Déconnecter":
				VueConnexion connexion = new VueConnexion();
				connexion.getFrame().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				connexion.getFrame().setVisible(true);
				this.vue.dispose();
				break;
			}
		}
	}
}
