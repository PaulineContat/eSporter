package ihm;

import java.awt.BorderLayout;


import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import modele.Arbitre;
import modele.Ecurie;
import modele.Equipe;
import modele.Jeu;
import modele.Joueur;
import modele.Rencontre;
import modele.Tournoi;

import java.awt.GridLayout;
import java.awt.Panel;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import java.awt.Rectangle;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;

import connexion.ConnexionBDD;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AccueilArbitre extends JDialog {
	private JTable table;
	Connection connx = ConnexionBDD.connecting();
	ResultSet rs;
	List<Rencontre> rencontres ;
	

	public static void main(String[] args) throws Exception {
		Arbitre jn = new Arbitre("tronc", "jeannoel", "0652334280", "mdp");
		AccueilArbitre ac = new AccueilArbitre(jn);
		ac.setVisible(true);
		Ecurie ecurie = new Ecurie("G1", "mdp123", Ecurie.Statut.ASSOCIATIVE);

		Equipe equipe1 = new Equipe("G2", "mdp123", new Jeu("Valorant", 5), ecurie);
		equipe1.setPoints(8);
		Equipe equipe2 = new Equipe("G3", "mdp123", new Jeu("CSGO", 5), ecurie);
		equipe2.setPoints(12);
		Equipe equipe3 = new Equipe("G4", "mdp123", new Jeu("Spyro", 1), ecurie);
		equipe3.setPoints(18);
		Equipe equipe4 = new Equipe("G5", "mdp123", new Jeu("MinecraftSpeedrunnerVS5hunters", 6), ecurie);
		equipe4.setPoints(7);

		Equipe equipe5 = new Equipe("G6", "mdp123", new Jeu("Valorant", 5), ecurie);
		equipe5.setPoints(11);
		Equipe equipe6 = new Equipe("G7", "mdp123", new Jeu("CSGO", 5), ecurie);
		equipe6.setPoints(8);
		Equipe equipe7 = new Equipe("G8", "mdp123", new Jeu("Spyro", 1), ecurie);
		equipe7.setPoints(18);
		Equipe equipe8 = new Equipe("G9", "mdp123", new Jeu("MinecraftSpeedrunnerVS5hunters", 6), ecurie);
		equipe8.setPoints(2);
		
		Equipe equipe9 = new Equipe("G10", "mdp123", new Jeu("Valorant", 5), ecurie);
		equipe9.setPoints(2);
		Equipe equipe10 = new Equipe("G11", "mdp123", new Jeu("CSGO", 5), ecurie);
		equipe10.setPoints(6);
		Equipe equipe11 = new Equipe("G12", "mdp123", new Jeu("Spyro", 1), ecurie);
		equipe11.setPoints(9);
		Equipe equipe12 = new Equipe("G13", "mdp123", new Jeu("MinecraftSpeedrunnerVS5hunters", 6), ecurie);
		equipe12.setPoints(16);
		
		Equipe equipe13 = new Equipe("G14", "mdp123", new Jeu("Valorant", 5), ecurie);
		equipe13.setPoints(9);
		Equipe equipe14 = new Equipe("G15", "mdp123", new Jeu("CSGO", 5), ecurie);
		equipe14.setPoints(17);
		Equipe equipe15 = new Equipe("G16", "mdp123", new Jeu("Spyro", 1), ecurie);
		equipe15.setPoints(1);
		Equipe equipe16 = new Equipe("G17", "mdp123", new Jeu("MinecraftSpeedrunnerVS5hunters", 6), ecurie);
		equipe16.setPoints(9);

		
		Tournoi tournoi = new Tournoi(null, null, null, null, null, null, null, null);
		tournoi.addEquipe(equipe1);
		tournoi.addEquipe(equipe2);
		tournoi.addEquipe(equipe3);
		tournoi.addEquipe(equipe4);
		tournoi.addEquipe(equipe5);
		tournoi.addEquipe(equipe6);
		tournoi.addEquipe(equipe7);
		tournoi.addEquipe(equipe8);
		tournoi.addEquipe(equipe9);
		tournoi.addEquipe(equipe10);
		tournoi.addEquipe(equipe11);
		tournoi.addEquipe(equipe12);
		tournoi.addEquipe(equipe13);
		tournoi.addEquipe(equipe14);
		tournoi.addEquipe(equipe15);
		tournoi.addEquipe(equipe16);

	}
	public AccueilArbitre(Arbitre a) {
		initialize(a);

	}
	private void initialize(Arbitre a) {
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
			try {
				rencontres = ConnexionBDD.getRencontresDunArbitre(a);
				for(int i=0; i<rencontres.size();i++) {
					
					if(rencontres.get(i).getEquipeGagnante()!=null) {
					model.addRow(new Object[] {rencontres.get(i).getTournoi().getNom(),rencontres.get(i).getHeure(),rencontres.get(i).getEquipe1().getNom(), rencontres.get(i).getEquipe2().getNom(),rencontres.get(i).getEquipeGagnante().getNom()});
					}else {
						model.addRow(new Object[] {rencontres.get(i).getTournoi().getNom(),rencontres.get(i).getHeure(),rencontres.get(i).getEquipe1().getNom(), rencontres.get(i).getEquipe2().getNom(),"selectionner equipe gagnante"});
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			scrollPane.setViewportView(table);
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int row = table.getSelectedRow();
					table.getValueAt(row, 1);
					ChoisirEquipeGagnante jd = new ChoisirEquipeGagnante(rencontres.get(row));
					jd.setModal(true);
		            jd.setVisible(true); 
				}
			});
			
			
			JLabel numPhase = new JLabel("Liste des matchs à arbitrer : ");
			splitPane_1.setLeftComponent(numPhase);

			JPanel panel = new JPanel();
			splitPane.setLeftComponent(panel);
			panel.setLayout(new GridLayout(3, 1, 0, 0));
			
			JLabel lblPrenom = new JLabel("Prenom : " + a.getPrenom());
			panel.add(lblPrenom);
			
			JLabel lblNom = new JLabel( " Nom : " +a.getNom());
			panel.add(lblNom);
			{

			}
		}


	}
	private String getEquipeGagnante(DefaultTableModel model, Rencontre r) {
		int result = r.getResultat();

		if (result==1) {
			return r.getEquipe1().getNom();
		}
		else if(result ==2) {
			return r.getEquipe2().getNom();
		}
		else {

			return "Equipe gagnante non définie" ;
		}
	}
}







