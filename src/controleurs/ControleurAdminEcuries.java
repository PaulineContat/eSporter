package controleurs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;

import modele.Ecurie;
import modeles.ModeleAdminEcuries;
import vues.VueAdminEcuries;
import vues.VueAjoutEcurie;
import vues.VueSupprModifEcurie;

public class ControleurAdminEcuries implements MouseListener {

	private VueAdminEcuries vue;
	private ModeleAdminEcuries modele;

	public ControleurAdminEcuries(VueAdminEcuries vue, ModeleAdminEcuries modele) {
		this.vue = vue;
		this.modele = modele;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		Object source = e.getSource();
		if (source instanceof JTable) {
			JTable table = (JTable) source;
			int row = table.getSelectedRow();
			Ecurie ecurie = (Ecurie) this.modele.getEcuries().toArray()[row];
			VueSupprModifEcurie vue = new VueSupprModifEcurie(ecurie, this.vue);
			vue.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			vue.setVisible(true);
		} else if (source instanceof JButton) {
			JButton button = (JButton) source;
			String buttonText = button.getText();
			
			switch(buttonText) {
			case "Ajouter une écurie":
				VueAjoutEcurie ajoutEcurie = new VueAjoutEcurie(this.vue);
				ajoutEcurie.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				ajoutEcurie.setVisible(true);
				break;
			case "Fermer":
				this.vue.dispose();
				break;
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