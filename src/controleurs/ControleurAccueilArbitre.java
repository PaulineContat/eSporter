package controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import ihm.ChoisirEquipeGagnante;
import vues.VueAccueilArbitre;

public class ControleurAccueilArbitre implements MouseListener{

	private VueAccueilArbitre vue;
	
	public ControleurAccueilArbitre(VueAccueilArbitre vue) {
		this.vue = vue;
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		ChoisirEquipeGagnante choisirEquipeGagnante = new ChoisirEquipeGagnante(vue.getSelectedRencontre());
		choisirEquipeGagnante.setModal(true);
		choisirEquipeGagnante.setVisible(true);
	}

	@Override
	public void mousePressed(MouseEvent e) {
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
