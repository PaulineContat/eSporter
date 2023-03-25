package vues;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import controleurs.ControleurAccueilEquipe;
import modele.Joueur;
import modele.Tournoi;
import modeles.ModeleAccueilEquipe;

@SuppressWarnings("serial")
public class VueAccueilEquipe extends JDialog{
	private ModeleAccueilEquipe modele;
	private ControleurAccueilEquipe controleur;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnOngletTournois;
	private JButton btnOngletJoueurs;

	public VueAccueilEquipe(String identifiantEquipe) {
		
		this.modele = new ModeleAccueilEquipe(identifiantEquipe);
		this.controleur = new ControleurAccueilEquipe(this, modele);
		
		this.setTitle("Profil Equipe");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));
		{
			JSplitPane splitPaneFenetre = new JSplitPane();
			getContentPane().add(splitPaneFenetre);
			{
				JPanel panelInfo = new JPanel();
				splitPaneFenetre.setLeftComponent(panelInfo);
				panelInfo.setLayout(new GridLayout(0, 1, 0, 0));
				{
					JLabel lblEcurie = new JLabel("Ecurie : " + modele.getNomEcurie());
					panelInfo.add(lblEcurie);
				}
				{
					JLabel lblNomEquipe = new JLabel("Nom : " + modele.getNomEquipe());
					panelInfo.add(lblNomEquipe);
				}
				{
					JLabel lblJeu = new JLabel("Jeu : " + modele.getNomJeu());
					panelInfo.add(lblJeu);
				}
			}
			{
				JSplitPane splitPaneTableaux = new JSplitPane();
				splitPaneTableaux.setOrientation(JSplitPane.VERTICAL_SPLIT);
				splitPaneFenetre.setRightComponent(splitPaneTableaux);
				{
					scrollPane = new JScrollPane();
					splitPaneTableaux.setRightComponent(scrollPane);
					{
						table = new JTable();
						table.addMouseListener(this.controleur);
					}
				}
				{
					JPanel panelOnglets = new JPanel();
					splitPaneTableaux.setLeftComponent(panelOnglets);
					panelOnglets.setLayout(new BoxLayout(panelOnglets, BoxLayout.X_AXIS));
					{
						btnOngletJoueurs = new JButton("Joueurs");
						btnOngletJoueurs.addMouseListener(this.controleur);
						panelOnglets.add(btnOngletJoueurs);
					}
					{
						btnOngletTournois = new JButton("Tournois");
						btnOngletTournois.addMouseListener(this.controleur);
						panelOnglets.add(btnOngletTournois);
					}
				}
			}
		}
		
		this.afficherJoueurs();
	}
	
	public void afficherJoueurs() {
		this.btnOngletJoueurs.setEnabled(false);
		this.btnOngletTournois.setEnabled(true);

		this.table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Identifiant", "Nom", "Prenom", "Tel", "Pseudo"
				}
				) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, String.class, String.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			};
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();	
		
		int n = table.getRowCount();
		for (int i=n-1 ; i>=0 ; --i) {
			model.removeRow(i);
		}
		
		for (Joueur joueur : this.modele.getJoueurs()) {
			model.addRow(new Object[]{joueur.getCompte().getIdentifiant(), joueur.getNom(), joueur.getPrenom(), joueur.getTel(), joueur.getPseudo()});
		}
		
		this.scrollPane.setViewportView(table);
		
	}
	
	public void afficherTournois() {
		this.btnOngletTournois.setEnabled(false);
		this.btnOngletJoueurs.setEnabled(true);
		
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Nom", "Categorie", "Date Debut", "Date Fin", "Date Limite Inscription"
				}
				) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, String.class, String.class
			};
			@SuppressWarnings("unchecked")
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			};
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();	
		
		int n = table.getRowCount();
		for (int i=n-1 ; i>=0 ; --i) {
			model.removeRow(i);
		}

		for (Tournoi tournoi:this.modele.getTournois()) {
			System.out.println("test");
			if (modele.peutInscrire(tournoi)) {	
				System.out.println("test2");
				model.addRow(new Object[]{tournoi.getNom(), tournoi.getCategorie().name(), tournoi.getDateDebut(), tournoi.getDateFin(), tournoi.getDateLimiteInscription()}); 
			}
		}
		
		scrollPane.setViewportView(table);
	}

	private int getSelectedIndex() {
		return table.getSelectedRow();
	}

	public Tournoi getSelectedTournoi() {
		return modele.getTournoiOfIndex(this.getSelectedIndex());
	}
}
