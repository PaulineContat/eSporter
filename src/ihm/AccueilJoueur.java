package ihm;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modele.Ecurie;
import modele.Equipe;
import modele.Jeu;
import modele.Joueur;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import connexion.ConnexionBDD;

import javax.swing.JScrollPane;

public class AccueilJoueur extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tableRencontres;

	private Connection conx;
	


	/**
	 * Create the dialog.
	 * @throws SQLException 
	 */
	public AccueilJoueur(Joueur j) throws SQLException {
		this.conx = ConnexionBDD.connecting();
		
		this.setTitle("Profil Joueur");
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
					JLabel lblNomEcurie = new JLabel("Ecurie : " + j.getEquipe().getEcurie().getNom());
					panel.add(lblNomEcurie);
				}
				{
					JLabel lblNomEquipe = new JLabel("Equipe : " + j.getEquipe().getNom());
					panel.add(lblNomEquipe);
				}
				{
					JLabel lblPrenomJoueur = new JLabel("Prenom : " + j.getPrenom());
					panel.add(lblPrenomJoueur);
				}
				{
					JLabel lblNomJoueur = new JLabel("Nom : " + j.getNom());
					panel.add(lblNomJoueur);
				}
				{
					JLabel lblPseudo = new JLabel("Pseudo : " + j .getPseudo());
					panel.add(lblPseudo);
				}
			}
			{
				JSplitPane splitPane_1 = new JSplitPane();
				splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
				splitPane.setRightComponent(splitPane_1);
				{
					JLabel lblListeRencontres = new JLabel("Rencontres à venir : ");
					splitPane_1.setLeftComponent(lblListeRencontres);
				}
				{
					JScrollPane scrollPane = new JScrollPane();
					splitPane_1.setRightComponent(scrollPane);
					{
						tableRencontres = new JTable();
						tableRencontres.setModel(new DefaultTableModel(
							new Object[][] {
							},
							new String[] {
								"Adversaire", "Tournoi", "Heure"
							}
						) {
							Class[] columnTypes = new Class[] {
								String.class, String.class, String.class
							};
							public Class getColumnClass(int columnIndex) {
								return columnTypes[columnIndex];
							}
						});
						
						DefaultTableModel model = (DefaultTableModel) tableRencontres.getModel();
						List<modele.Rencontre> rencontres = ConnexionBDD.getRencontresDunJoueur(j);
						for (modele.Rencontre r : rencontres) {
							Equipe adversaire;
							if (j.getEquipe().getNom().equals(r.getEquipe2().getNom())) {
								adversaire = r.getEquipe1();
							} else {
								adversaire = r.getEquipe2();
							}
							
							model.addRow(new Object[]{adversaire.getNom(), r.getTournoi().getNom(), r.getHeure()});
						}
							
						scrollPane.setViewportView(tableRencontres);
					}
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
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
	
	public static void main(String[] args) throws SQLException {
		Connection conx;
		
		conx = ConnexionBDD.connecting();
		
		Joueur joueur = ConnexionBDD.getJoueurs().get("yanis.mahe");
		
		AccueilJoueur e = new AccueilJoueur(joueur);
		e.setVisible(true);
	}

}
