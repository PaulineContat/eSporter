package controleurs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.JButton;

import ihm.AjoutTournoi;
import modeles.ModeleAccueilResponsable;
import vues.VueAccueilResponsable;
import javax.swing.WindowConstants;

public class ControleurAccueilResponsable implements MouseListener {

	private VueAccueilResponsable vue;
	private ModeleAccueilResponsable modele;

	public ControleurAccueilResponsable(VueAccueilResponsable vue, ModeleAccueilResponsable modele) {
		this.vue = vue;
		this.modele = modele;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() instanceof JButton) {
			try {
				AjoutTournoi ajoutTournoi = new VueAjoutTournoi(this.modele.getIdentifiantResponsable());
				ajoutTournoi.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				ajoutTournoi.setVisible(true);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
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
