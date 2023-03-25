package controleurs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTable;

import ihm.SupprModifArbitre;
import modele.Arbitre;
import modele.Tournoi;
import modeles.ModeleAdminArbitres;
import vues.VueAdminArbitres;
import vues.VueAjoutArbitre;
import vues.VueSupprModifArbitre;

public class ControleurAdminArbitres implements MouseListener {

	private VueAdminArbitres vue;
	private ModeleAdminArbitres modele;

	public ControleurAdminArbitres(VueAdminArbitres vue, ModeleAdminArbitres modele) {
		this.vue = vue;
		this.modele = modele;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		Object source = e.getSource();
		if (source instanceof JTable) {
			Arbitre arbitre = vue.getSelectedArbitre();
			VueSupprModifArbitre vueSupprModifArbitre = new VueSupprModifArbitre(arbitre.getCompte().getIdentifiant(), this.vue);
			vueSupprModifArbitre.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			vueSupprModifArbitre.setVisible(true);
		} else {
			JButton button = (JButton) source;
			String buttonText = button.getText();
			if (buttonText == "Fermer") {
				this.vue.dispose();
			} else {
				VueAjoutArbitre vueAjoutArbitre = new VueAjoutArbitre(this.vue);
				vueAjoutArbitre.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				vueAjoutArbitre.setVisible(true);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
