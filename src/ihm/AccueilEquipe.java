package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modele.Equipe;
import modele.Joueur;
import modele.Tournoi;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.DefaultTableModel;

import connexion.ConnexionBDD;

import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AccueilEquipe extends JDialog {

	private enum Onglet{JOUEURS, TOURNOIS};
	private Onglet onglet;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JButton btnOngletJoueurs;
	private JButton btnOngletTournois;
	private Connection conx;


	/**
	 * Create the dialog.
	 * @throws SQLException 
	 */
	public AccueilEquipe(Equipe equipe) throws SQLException {
		conx = ConnexionBDD.connecting();
		List<Tournoi> tournois = ConnexionBDD.getTournois();
		this.setTitle("Profil Equipe");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JSplitPane splitPane = new JSplitPane();
			contentPanel.add(splitPane);
			{
				JPanel panel = new JPanel();
				splitPane.setLeftComponent(panel);
				panel.setLayout(new GridLayout(0, 1, 0, 0));
				{
					JLabel lblEcurie = new JLabel("Ecurie : " + equipe.getEcurie().getNom());
					panel.add(lblEcurie);
				}
				{
					JLabel lblNomEquipe = new JLabel("Nom : " + equipe.getNom());
					panel.add(lblNomEquipe);
				}
				{
					JLabel lblJeu = new JLabel("Jeu : " + equipe.getJeu().getNom());
					panel.add(lblJeu);
				}
			}
			{
				JSplitPane splitPane_1 = new JSplitPane();
				splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
				splitPane.setRightComponent(splitPane_1);
				{
					JScrollPane scrollPane = new JScrollPane();
					splitPane_1.setRightComponent(scrollPane);
					{
						table = new JTable();
						table.addMouseListener(new MouseAdapter() {
							@Override
							public void mousePressed(MouseEvent e) {
								if (onglet == Onglet.TOURNOIS) {
									int row = table.getSelectedRow();
									try {
										if (ConnexionBDD.estInscrit(equipe, tournois.get(row))) {
											JOptionPane.showMessageDialog(null, "Vous etes deja inscrit a ce tournoi", "Deja Inscrit", JOptionPane.INFORMATION_MESSAGE);
										} else {
											int YesOrNo = JOptionPane.showConfirmDialog(null,"Voulez vous vous incrire au tournoi " + tournois.get(row).getNom(),"Inscription", JOptionPane.YES_NO_OPTION);
											if (YesOrNo == 0){
												ConnexionBDD.inscrireEquipeTournoi(equipe, tournois.get(row));
											}
										}
									} catch (HeadlessException e2) {
										// TODO Auto-generated catch block
										e2.printStackTrace();
									} 									
								}
							}
						});
						table.setModel(new DefaultTableModel(
								new Object[][] {
								},
								new String[] {
										"Identifiant", "Nom", "Prenom", "Tel", "Pseudo"
								}
								) {
							Class[] columnTypes = new Class[] {
									String.class, String.class, String.class, String.class, String.class
							};
							public Class getColumnClass(int columnIndex) {
								return columnTypes[columnIndex];
							}
						});

						this.onglet = Onglet.JOUEURS;
						DefaultTableModel model = (DefaultTableModel) table.getModel();
						if (equipe.getJoueurs()[0] != null) {
							for (int i = 0; i<equipe.getJoueurs().length; i++) {
								System.out.println(equipe.getJoueurs()[i]);
								model.addRow(new Object[]{equipe.getJoueurs()[i].getCompte().getIdentifiant(), equipe.getJoueurs()[i].getNom(), equipe.getJoueurs()[i].getPrenom(), equipe.getJoueurs()[i].getTel(), equipe.getJoueurs()[i].getPseudo()});
							}						
							scrollPane.setViewportView(table);
						}
					}
				}
				{
					JPanel panelOnglets = new JPanel();
					splitPane_1.setLeftComponent(panelOnglets);
					panelOnglets.setLayout(new BoxLayout(panelOnglets, BoxLayout.X_AXIS));
					{
						btnOngletJoueurs = new JButton("Joueurs");
						panelOnglets.add(btnOngletJoueurs);
					}
					{
						btnOngletTournois = new JButton("Tournois");
						panelOnglets.add(btnOngletTournois);
					}
				}
			}
		}

		btnOngletJoueurs.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				onglet = Onglet.JOUEURS;
				table.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
								"Identifiant", "Nom", "Prenom", "Tel", "Pseudo"
						}
						) {
					Class[] columnTypes = new Class[] {
							String.class, String.class, String.class, String.class, String.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
				DefaultTableModel model = (DefaultTableModel) table.getModel();

				btnOngletJoueurs.setBackground(Color.LIGHT_GRAY);
				btnOngletTournois.setBackground(Color.WHITE);
				int n = table.getRowCount();
				for (int i=n-1 ; i>=0 ; --i) {
					model.removeRow(i);
				}
				for (Joueur joueur : equipe.getJoueurs()) {
					model.addRow(new Object[]{joueur.getCompte().getIdentifiant(), joueur.getNom(), joueur.getPrenom(), joueur.getTel(), joueur.getPseudo()});
				}	

			}
		});

		btnOngletTournois.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				onglet = Onglet.TOURNOIS;
				table.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
								"Nom", "Categorie", "Date Debut", "Date Fin", "Date Limite Inscription"
						}
						) {
					Class[] columnTypes = new Class[] {
							String.class, String.class, String.class, String.class, String.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});

				DefaultTableModel model = (DefaultTableModel) table.getModel();
				btnOngletTournois.setBackground(Color.LIGHT_GRAY);
				btnOngletJoueurs.setBackground(Color.WHITE);			
				int n = table.getRowCount();
				for (int i=n-1 ; i>=0 ; --i) {
					model.removeRow(i);
				}


				for (Tournoi tournoi:tournois) {

					if (peutInscrire(equipe, tournoi)) {						
						model.addRow(new Object[]{tournoi.getNom(), tournoi.getCategorie().name(), tournoi.getDateDebut(), tournoi.getDateFin(), tournoi.getDateLimiteInscription()}); 

					}					

				}	

			}


		});

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton BtnAjouterJoueur = new JButton("Ajouter Joueur");
				BtnAjouterJoueur.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							AjoutJoueur ajoutJoueur = new AjoutJoueur(equipe);
							ajoutJoueur.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
							ajoutJoueur.setVisible(true);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				buttonPane.add(BtnAjouterJoueur);
			}
			{
				JButton BtnFermer = new JButton("Fermer");
				BtnFermer.setActionCommand("Close");
				BtnFermer.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(BtnFermer);
				getRootPane().setDefaultButton(BtnFermer);
			}
		}

	}

	private static boolean peutInscrire(Equipe equipe, Tournoi tournoi) {
		Date dateLimiteTournoi = null;
		Date dateAujourdhui = new Date();
		try {
			dateLimiteTournoi=new SimpleDateFormat("dd/MM/yyyy").parse(tournoi.getDateLimiteInscription());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return tournoi.getJeu().getNom().equals(equipe.getJeu().getNom()) && dateLimiteTournoi.after(dateAujourdhui);
	}

	public static void main(String[] args) throws SQLException {
		Connection conx;

		conx = ConnexionBDD.connecting();

		Equipe equipe= ConnexionBDD.getEquipeFromId("jeanequipe");

		AccueilEquipe e = new AccueilEquipe(equipe);
		e.setVisible(true);
	}

}
