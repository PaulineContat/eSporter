package controleurs;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import connexion.ConnexionBDD;
import modele.Joueur;
import modeles.ModeleAdminJoueur;
import ihm.SupprModifJoueur;
import vues.VueAccueilJoueur;
import vues.VueAdminJoueurs;

public class ControleurAdminJoueur {

	private ModeleAdminJoueur modele;

	public ControleurAdminJoueur(VueAdminJoueurs vueAdminJoueurs) {
		ConnexionBDD.connecting();
		this.modele = new ModeleAdminJoueur();
	}

	public void remplirTableauJoueurs(DefaultTableModel model) {		
		for(Joueur joueur : modele.getJoueurs()) {
			model.addRow(new Object[]{joueur.getCompte().getIdentifiant(), joueur.getNom(), joueur.getPrenom(), joueur.getTel(), joueur.getPseudo()});
		}
	}
	
	public MouseAdapter getMouseListenerTableau(JTable table) {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				table.getValueAt(row, 1);
				SupprModifJoueur jd = new SupprModifJoueur(modele.getJoueurs().get(row));
				jd.setModal(true);
	            jd.setVisible(true); 
			}
		};
	}

}
