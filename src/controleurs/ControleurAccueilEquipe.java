package controleurs;

import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import modele.Tournoi;
import modeles.ModeleAccueilEquipe;
import vues.VueAccueilEquipe;

public class ControleurAccueilEquipe implements MouseListener{

	private enum Etat {JOUEURS, TOURNOIS};
	
	private VueAccueilEquipe vue;
	private ModeleAccueilEquipe modele;
	private Etat etat;

	public ControleurAccueilEquipe(VueAccueilEquipe vueAccueilEquipe, ModeleAccueilEquipe modeleAccueilEquipe) {
		this.vue = vueAccueilEquipe;
		this.modele = modeleAccueilEquipe;
		this.etat = Etat.JOUEURS;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() instanceof JButton) {
			if (((JButton) e.getSource()).isEnabled()) {
				if (this.etat == Etat.JOUEURS) {
					this.etat = Etat.TOURNOIS;
					this.vue.afficherTournois();
				} else {
					this.etat = Etat.JOUEURS;
					this.vue.afficherJoueurs();
				}
			}
			
		}
		else if ( this.etat == Etat.TOURNOIS) {
			Tournoi tournoi = vue.getSelectedTournoi();
			try {
				if (modele.estInscrit(tournoi)) {
					JOptionPane.showMessageDialog(null, "Vous etes deja inscrit a ce tournoi", "Deja Inscrit", JOptionPane.INFORMATION_MESSAGE);
				} else {
					int YesOrNo = JOptionPane.showConfirmDialog(null,"Voulez vous vous incrire au tournoi " + tournoi.getNom(),"Inscription", JOptionPane.YES_NO_OPTION);
					if (YesOrNo == 0){
						modele.inscrireEquipeTournoi(tournoi);
					}
				}
			} catch (HeadlessException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
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
