package ihm;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import connexion.ConnexionBDD;
import modele.Arbitre;
import modele.Rencontre;
import modele.Responsable;
import modele.Tournoi;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class AccueilResponsable extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private ConnexionBDD conxBDD;
	private Connection conx;
	List<Tournoi> tournois;
	
	/**
	 * Create the dialog.
	 * @throws SQLException 
	 */
	public AccueilResponsable(Responsable responsable) throws SQLException {
		conx = ConnexionBDD.connecting();
		tournois = ConnexionBDD.getTournoisFromResponsable(responsable);
		
		this.setTitle("Profil Responsable");
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
					JLabel lblPrenomJoueur = new JLabel("Prenom : " + responsable.getPrenom());
					panel.add(lblPrenomJoueur);
				}
				{
					JLabel lblNomJoueur = new JLabel("Nom : " + responsable.getNom());
					panel.add(lblNomJoueur);
				}
				{
					JButton btnAjoutEquipe = new JButton("Ajouter Tournoi");
					btnAjoutEquipe.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								AjoutTournoi ajoutTournoi = new AjoutTournoi(responsable);
								ajoutTournoi.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
								ajoutTournoi.setVisible(true);
							} catch (SQLException e1) {
								
								e1.printStackTrace();
							}
						}
					});
					panel.add(btnAjoutEquipe);
				}
			}
			{
				JSplitPane splitPane_1 = new JSplitPane();
				splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
				splitPane.setRightComponent(splitPane_1);
				{
					JLabel lblListeEquipes = new JLabel("Tournois :");
					splitPane_1.setLeftComponent(lblListeEquipes);
				}
				{
					JScrollPane scrollPane = new JScrollPane();
					splitPane_1.setRightComponent(scrollPane);
					{
						table = new JTable();
						table.addMouseListener(new MouseAdapter() {
							@Override
							public void mousePressed(MouseEvent e) {
								int row = table.getSelectedRow();
								Tournoi tournoi = tournois.get(row);
								if (tournoi.getRencontres().isEmpty()) {
									int YesOrNo = JOptionPane.showConfirmDialog(null,"Lancer le tournoi " + tournoi.getNom(),"Lancement tournoi", JOptionPane.YES_NO_OPTION);
									if(YesOrNo == 0) {
										System.out.println("test");
										tournoi.trimEquipes();
										List<Arbitre> arbitres = new ArrayList<>();
										try {
											for (Arbitre a : ConnexionBDD.getArbitres().values()) {
												arbitres.add(a);									
											}
											tournoi.generateRencontres(arbitres);
											for (Rencontre r : tournoi.getRencontres()) {
												ConnexionBDD.enregistrerRencontre(r);
											}
										} catch (SQLException e1) {
											
											e1.printStackTrace();
										}
									}
								}else {
									ArbreEtBouton aet = new ArbreEtBouton(tournoi);
									aet.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
									aet.setVisible(true);
									
								}

							}

						});
						table.setModel(new DefaultTableModel(
								new Object[][] {
								},
								new String[] {
										"Nom", "Categorie", "Jeu", "Date début", "Date fin", "Date limite\nd'inscription", "Heure début"
								}
								) {
							Class[] columnTypes = new Class[] {
									String.class, String.class, String.class, String.class, String.class, String.class, String.class
							};
							public Class getColumnClass(int columnIndex) {
								return columnTypes[columnIndex];
							}
							boolean[] columnEditables = new boolean[] {
									false, false, false, false, false, false, false, false
							};
							public boolean isCellEditable(int row, int column) {
								return columnEditables[column];
							}
						});
						DefaultTableModel model = (DefaultTableModel) table.getModel();

						for (Tournoi tournoi : tournois) {
							model.addRow(new Object[]{tournoi.getNom(), tournoi.getCategorie(), tournoi.getJeu().getNom(), tournoi.getDateDebut(), tournoi.getDateFin(), tournoi.getDateLimiteInscription(), tournoi.getHeure()});
						}
						scrollPane.setViewportView(table);
					}
				}
			}
		}
	}

	public static void main(String[] args) throws SQLException {
		Connection conx = ConnexionBDD.connecting();
		Responsable r = ConnexionBDD.getResponsables().get("francoise.boude");
		AccueilResponsable a = new AccueilResponsable(r);
		a.setVisible(true);
	}

}
