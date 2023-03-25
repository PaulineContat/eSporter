package vues;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import connexion.ConnexionBDD;
import controleurs.ControleurAccueilArbitre;
import controleurs.ControleurAccueilJoueur;
import ihm.ChoisirEquipeGagnante;
import modele.Arbitre;
import modele.Joueur;
import modele.Rencontre;
import modeles.ModeleAccueilArbitre;

@SuppressWarnings("serial")
public class VueAccueilJoueur extends JDialog{
	
	private ControleurAccueilJoueur controleur;
	
	private JTable table;

	public VueAccueilJoueur(String identifiantJoueur) {
		this.controleur = new ControleurAccueilJoueur(this, identifiantJoueur);
		
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));
		{
			JPanel p1 = new JPanel();
			getContentPane().add(p1);
			p1.setLayout(new BoxLayout(p1, BoxLayout.X_AXIS));

			JSplitPane splitPane = new JSplitPane();
			p1.add(splitPane);

			JSplitPane paneRencontres = new JSplitPane();
			paneRencontres.setOrientation(JSplitPane.VERTICAL_SPLIT);
			splitPane.setRightComponent(paneRencontres);

			JScrollPane scrollPane = new JScrollPane();

			paneRencontres.setRightComponent(scrollPane);

			table = new JTable();
			
			

			table.setModel(new DefaultTableModel(
					new Object[][] {

					},
					new String[] {
							"Tournoi", "Horaire", "Equipe 1", "Equipe 2"
					}
					) {
				boolean[] columnEditables = new boolean[] {
						false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
			DefaultTableModel model = (DefaultTableModel) table.getModel();	
			controleur.remplirTableauRencontres(model);

			
			scrollPane.setViewportView(table);
			
			
			JLabel numPhase = new JLabel("Liste des rencontres : ");
			paneRencontres.setLeftComponent(numPhase);

			JPanel paneInfo = new JPanel();
			splitPane.setLeftComponent(paneInfo);
			paneInfo.setLayout(new GridLayout(3, 1, 0, 0));
			
			JLabel lblPrenom = new JLabel("Prenom : " + controleur.getJoueur().getPrenom());
			paneInfo.add(lblPrenom);
			
			JLabel lblNom = new JLabel( "Nom : " + controleur.getJoueur().getNom());
			paneInfo.add(lblNom);
			
			JLabel lblPseudo = new JLabel("Pseudo : " + controleur.getJoueur().getPseudo());
			paneInfo.add(lblPseudo);
			
			JLabel lblEquipe = new JLabel("Equipe : " + controleur.getJoueur().getEquipe().getNom());
			paneInfo.add(lblEquipe);
		}
	}
		
	
}
