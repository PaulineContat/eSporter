package vues;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controleurs.ControleurAccueilResponsable;
import modele.Tournoi;
import modeles.ModeleAccueilResponsable;

public class VueAccueilResponsable extends JDialog{
	
	private ModeleAccueilResponsable modele;
	private ControleurAccueilResponsable controleur;
	private JTable table;

	public VueAccueilResponsable(String identifiantResponsable) {
		
		this.modele = new ModeleAccueilResponsable(identifiantResponsable);
		this.controleur = new ControleurAccueilResponsable(this, modele);
		
		this.setTitle("Profil Responsable");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));
		{
			JSplitPane splitPaneFenetre = new JSplitPane();
			getContentPane().add(splitPaneFenetre);
			{
				JPanel panelGauche = new JPanel();
				splitPaneFenetre.setLeftComponent(panelGauche);
				panelGauche.setLayout(new GridLayout(0, 1, 0, 0));
				{
					JLabel lblPrenomJoueur = new JLabel("Prenom : " + this.modele.getPrenomResponsable());
					panelGauche.add(lblPrenomJoueur);
				}
				{
					JLabel lblNomJoueur = new JLabel("Nom : " + this.modele.getNomResponsable());
					panelGauche.add(lblNomJoueur);
				}
				{
					JButton btnAjoutEquipe = new JButton("Ajouter Tournoi");
					btnAjoutEquipe.addMouseListener(this.controleur);
					panelGauche.add(btnAjoutEquipe);
				}
			}
			{
				JSplitPane splitPaneDroit = new JSplitPane();
				splitPaneDroit.setOrientation(JSplitPane.VERTICAL_SPLIT);
				splitPaneFenetre.setRightComponent(splitPaneDroit);
				{
					JLabel lblListeEquipes = new JLabel("Tournois :");
					splitPaneDroit.setLeftComponent(lblListeEquipes);
				}
				{
					JScrollPane scrollPane = new JScrollPane();
					splitPaneDroit.setRightComponent(scrollPane);
					{
						this.table = new JTable();
						table.addMouseListener(this.controleur);
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

						for (Tournoi tournoi : this.modele.getTournois()) {
							model.addRow(new Object[]{tournoi.getNom(), tournoi.getCategorie(), tournoi.getJeu().getNom(), tournoi.getDateDebut(), tournoi.getDateFin(), tournoi.getDateLimiteInscription(), tournoi.getHeure()});
						}
						scrollPane.setViewportView(table);
					}
				}
			}
		}
	}
}
