package vues;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controleurs.ControleurAccueilArbitre;
import modele.Arbitre;
import modele.Rencontre;
import modeles.ModeleAccueilArbitre;

public class VueAccueilArbitre extends JDialog{
	
	private ModeleAccueilArbitre modele;
	private ControleurAccueilArbitre controleur;
	
	private JTable table;

	public VueAccueilArbitre(String identifiantArbitre) {
		this.modele = new ModeleAccueilArbitre(identifiantArbitre);
		this.controleur = new ControleurAccueilArbitre(this);
		
		List<Rencontre> rencontres = modele.getRencontres();
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));
		{
			JPanel p1 = new JPanel();
			getContentPane().add(p1);
			p1.setLayout(new BoxLayout(p1, BoxLayout.X_AXIS));

			JSplitPane splitPane = new JSplitPane();
			p1.add(splitPane);

			JSplitPane splitPane_1 = new JSplitPane();
			splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
			splitPane.setRightComponent(splitPane_1);

			JScrollPane scrollPane = new JScrollPane();

			splitPane_1.setRightComponent(scrollPane);

			table = new JTable();

			table.setModel(new DefaultTableModel(
					new Object[][] {

					},
					new String[] {
							"Tournoi", "Horaire", "Equipe 1", "Equipe 2", "R\u00E9sultat"
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

			for(int i=0; i<rencontres.size(); i++) {
				if(rencontres.get(i).getEquipeGagnante()!=null) {
				model.addRow(new Object[] {rencontres.get(i).getTournoi().getNom(),rencontres.get(i).getHeure(),rencontres.get(i).getEquipe1().getNom(), rencontres.get(i).getEquipe2().getNom(),rencontres.get(i).getEquipeGagnante().getNom()});
				}else {
					model.addRow(new Object[] {rencontres.get(i).getTournoi().getNom(),rencontres.get(i).getHeure(),rencontres.get(i).getEquipe1().getNom(), rencontres.get(i).getEquipe2().getNom(),"selectionner equipe gagnante"});
				}
			}
			scrollPane.setViewportView(table);
			table.addMouseListener(this.controleur);
			
			
			JLabel labelListeMatchs = new JLabel("Liste des matchs à arbitrer : ");
			splitPane_1.setLeftComponent(labelListeMatchs);

			JPanel informationsArbitre = new JPanel();
			splitPane.setLeftComponent(informationsArbitre);
			informationsArbitre.setLayout(new GridLayout(3, 1, 0, 0));
			
			JLabel lblPrenom = new JLabel("Prenom : " + modele.getPrenom());
			informationsArbitre.add(lblPrenom);
			
			JLabel lblNom = new JLabel( " Nom : " + modele.getNom());
			informationsArbitre.add(lblNom);
		}
	}
		
	public Rencontre getSelectedRencontre() {
		return modele.getRencontre(table.getSelectedRow());
	}
	
}
