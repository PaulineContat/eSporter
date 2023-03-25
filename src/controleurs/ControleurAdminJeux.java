package controleurs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;

import modele.Jeu;
import modeles.ModeleAdminJeux;
import vues.VueAdminJeux;
import vues.VueAjoutJeu;
import vues.VueSupprModifJeu;

public class ControleurAdminJeux implements MouseListener {

	private VueAdminJeux vue;
	private ModeleAdminJeux modele;

	public ControleurAdminJeux(VueAdminJeux vue, ModeleAdminJeux modele) {
		this.vue = vue;
		this.modele = modele;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		Object source = e.getSource();
		if (source instanceof JTable) {
			JTable table = (JTable) source;
			int row = table.getSelectedRow();
			Jeu jeu = (Jeu) this.modele.getJeux().toArray()[row];
			VueSupprModifJeu vue = new VueSupprModifJeu(jeu, this.vue);
			vue.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			vue.setVisible(true);
		} else if (source instanceof JButton) {
			JButton button = (JButton) source;
			String buttonText = button.getText();
			switch(buttonText) {
			case "Ajouter un jeu":
				VueAjoutJeu ajoutJeu = new VueAjoutJeu(this.vue);
				ajoutJeu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				ajoutJeu.setVisible(true);
				break;
			case "Fermer":
				this.vue.dispose();
				break;
			}
		}
	}
	
	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub

	}

}