package controleurs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;

import modele.Equipe;
import modeles.ModeleAdminEquipes;
import vues.VueAdminEquipes;
import vues.VueSupprModifEquipe;

public class ControleurAdminEquipes implements MouseListener {

	VueAdminEquipes vue;
	ModeleAdminEquipes modele;
	
	public ControleurAdminEquipes(VueAdminEquipes vue, ModeleAdminEquipes modele) {
		this.vue = vue;
		this.modele = modele;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Object source = e.getSource();
		if (source instanceof JTable) {
			JTable table = (JTable) source;
			int row = table.getSelectedRow();
			Equipe equipe = (Equipe) this.modele.getEquipes().toArray()[row];
			VueSupprModifEquipe vue = new VueSupprModifEquipe(equipe, this.vue);
			vue.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			vue.setVisible(true);
		} else if (source instanceof JButton) {
			JButton button = (JButton) source;
			String buttonText = button.getText();
			switch(buttonText) {
			case "Fermer":
				this.vue.dispose();
				break;
			}
		}
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
