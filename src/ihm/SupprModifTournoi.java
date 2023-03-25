package ihm;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import connexion.ConnexionBDD;
import modele.Responsable;
import modele.Tournoi;

public class SupprModifTournoi extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JComboBox comboBoxCategorie;
	private JComboBox comboBoxResponsable;
	private JTextField textFieldHeure;
	private JTextField textFieldDateLimite;
	private JTextField textFieldDateFin;
	private JTextField textFieldDateDebut;
	private JTextField textFieldNom;
	
	private Map<String, Responsable> lesResponsables;
	private ConnexionBDD conxBDD;
	private Connection conx;
	private String ancienNom;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SupprModifTournoi dialog = new SupprModifTournoi(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws SQLException 
	 */
	public SupprModifTournoi(Tournoi tournoi) throws SQLException {
		
		this.conx = ConnexionBDD.connecting();
		lesResponsables = ConnexionBDD.getResponsables();
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JSplitPane splitPane = new JSplitPane();
			splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
			contentPanel.add(splitPane, BorderLayout.CENTER);
			{
				JLabel lblChoix = new JLabel("Modifier ou supprimer un tournoi");
				lblChoix.setHorizontalAlignment(SwingConstants.CENTER);
				splitPane.setLeftComponent(lblChoix);
			}
			{
				JPanel panel_formulaire = new JPanel();
				splitPane.setRightComponent(panel_formulaire);
				panel_formulaire.setLayout(new GridLayout(0, 1, 0, 0));
				{
					JPanel panel_nom = new JPanel();
					panel_formulaire.add(panel_nom);
					panel_nom.setLayout(new GridLayout(0, 2, 0, 0));
					{
						JPanel panel = new JPanel();
						panel_nom.add(panel);
						panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
						{
							JLabel lblNom = new JLabel("Nom");
							lblNom.setHorizontalAlignment(SwingConstants.CENTER);
							panel.add(lblNom);
						}
					}
					{
						JPanel panel = new JPanel();
						panel_nom.add(panel);
						panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
						{
							textFieldNom = new JTextField();
							textFieldNom.setText(tournoi.getNom());
							panel.add(textFieldNom);
							textFieldNom.setColumns(10);
						}
					}
				}
				
				{
					JPanel panel_categorie = new JPanel();
					panel_formulaire.add(panel_categorie);
					panel_categorie.setLayout(new GridLayout(0, 2, 0, 0));
					{
						JPanel panel = new JPanel();
						panel_categorie.add(panel);
						panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
						{
							JLabel lblCategorie = new JLabel("Catégorie");
							lblCategorie.setHorizontalAlignment(SwingConstants.CENTER);
							panel.add(lblCategorie);
						}
					}
					{
						JPanel panel = new JPanel();
						panel_categorie.add(panel);
						panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
						{
							comboBoxCategorie = new JComboBox();
							for (Tournoi.Categorie c : Tournoi.Categorie.values()) {
								comboBoxCategorie.addItem(c.name());
							}
							comboBoxCategorie.setName("comboBoxCategorie");
							comboBoxCategorie.setSelectedItem(tournoi.getCategorie());
							panel.add(comboBoxCategorie);
						}
					}
				}
				{
					JPanel panel_date_debut = new JPanel();
					panel_formulaire.add(panel_date_debut);
					panel_date_debut.setLayout(new GridLayout(0, 2, 0, 0));
					{
						JPanel panel = new JPanel();
						panel_date_debut.add(panel);
						panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
						{
							JLabel lblDateDebut = new JLabel("Date début");
							lblDateDebut.setHorizontalAlignment(SwingConstants.CENTER);
							panel.add(lblDateDebut);
						}
					}
					{
						JPanel panel = new JPanel();
						panel_date_debut.add(panel);
						panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
						{
							textFieldDateDebut = new JTextField();
							textFieldDateDebut.setText(tournoi.getDateDebut());
							panel.add(textFieldDateDebut);
							textFieldDateDebut.setColumns(10);
						}
					}
				}
				
				{
					JPanel panel_date_fin = new JPanel();
					panel_formulaire.add(panel_date_fin);
					panel_date_fin.setLayout(new GridLayout(0, 2, 0, 0));
					{
						JPanel panel = new JPanel();
						panel_date_fin.add(panel);
						panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
						{
							JLabel lblDateFin = new JLabel("Date fin");
							lblDateFin.setHorizontalAlignment(SwingConstants.CENTER);
							panel.add(lblDateFin);
						}
					}
					{
						JPanel panel = new JPanel();
						panel_date_fin.add(panel);
						panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
						{
							textFieldDateFin = new JTextField();
							textFieldDateFin.setText(tournoi.getDateFin());
							panel.add(textFieldDateFin);
							textFieldDateFin.setColumns(10);
						}
					}
				}
				
				{
					JPanel panel_heure = new JPanel();
					panel_formulaire.add(panel_heure);
					panel_heure.setLayout(new GridLayout(0, 2, 0, 0));
					{
						JPanel panel = new JPanel();
						panel_heure.add(panel);
						panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
						{
							JLabel lblHeure = new JLabel("Heure");
							lblHeure.setHorizontalAlignment(SwingConstants.CENTER);
							panel.add(lblHeure);
						}
					}
					{
						JPanel panel = new JPanel();
						panel_heure.add(panel);
						panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
						{
							textFieldHeure = new JTextField();
							textFieldHeure.setText(tournoi.getHeure());
							panel.add(textFieldHeure);
							textFieldHeure.setColumns(10);
						}
					}
				}
				
				{
					JPanel panel_date_limite = new JPanel();
					panel_formulaire.add(panel_date_limite);
					panel_date_limite.setLayout(new GridLayout(0, 2, 0, 0));
					{
						JPanel panel = new JPanel();
						panel_date_limite.add(panel);
						panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
						{
							JLabel lblDateLimite = new JLabel("Date limite d'inscription");
							lblDateLimite.setHorizontalAlignment(SwingConstants.CENTER);
							panel.add(lblDateLimite);
						}
					}
					{
						JPanel panel = new JPanel();
						panel_date_limite.add(panel);
						panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
						{
							textFieldDateLimite = new JTextField();
							textFieldDateLimite.setText(tournoi.getDateLimiteInscription());
							panel.add(textFieldDateLimite);
							textFieldDateLimite.setColumns(10);
						}
					}
				}
				
				{
					JPanel panel_responsable = new JPanel();
					panel_formulaire.add(panel_responsable);
					panel_responsable.setLayout(new GridLayout(0, 2, 0, 0));
					{
						JPanel panel = new JPanel();
						panel_responsable.add(panel);
						panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
						{
							JLabel lblResponsable = new JLabel("Responsable");
							lblResponsable.setHorizontalAlignment(SwingConstants.CENTER);
							panel.add(lblResponsable);
						}
					}
					{
						JPanel panel = new JPanel();
						panel_responsable.add(panel);
						panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
						{
							comboBoxResponsable = new JComboBox(lesResponsables.keySet().toArray());
							comboBoxResponsable.setName("comboBoxResponsable");
							comboBoxResponsable.setSelectedItem(tournoi.getResponsable().getCompte().getIdentifiant());
							panel.add(comboBoxResponsable);
						}
					}
				}

			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnValiderLesModifications = new JButton("Valider les modifications");
				btnValiderLesModifications.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						tournoi.setNom(getNom());
						tournoi.setCategorie(getCategorie());
						tournoi.setDateDebut(getDateDebut());
						tournoi.setDateFin(getDateFin());
						tournoi.setHeure(getHeure());
						tournoi.setResponsable(getResponsable());
						if (tousLesChampsCorrects()) {
							try {
								ConnexionBDD.modifierTournoi(tournoi, ancienNom);
								JOptionPane.showMessageDialog(null, "Modification bien enregistrée", "Confirmation", 1);
								ancienNom = tournoi.getNom();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
					}
				});
				buttonPane.add(btnValiderLesModifications);
			}
			{
				JButton btnSupprimerTournoi = new JButton("Supprimer le tournoi");
				btnSupprimerTournoi.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ConnexionBDD.supprimerTournoi(tournoi);
						dispose();
					}
				});
				buttonPane.add(btnSupprimerTournoi);
			}
			{
				JButton cancelButton = new JButton("Fermer");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public String getNom() {
		return this.textFieldNom.getText();
	}
	
	public Tournoi.Categorie getCategorie() {
		return Tournoi.Categorie.valueOf((String) this.comboBoxCategorie.getSelectedItem());
	}
	
	public String getDateDebut() {
		return this.textFieldDateDebut.getText();
	}
	
	public String getDateFin() {
		return this.textFieldDateFin.getText();
	}
	
	public String getHeure() {
		return this.textFieldHeure.getText();
	}
	
	public Responsable getResponsable() {
		return this.lesResponsables.get(this.comboBoxResponsable.getSelectedItem());
	}
	
	public boolean tousLesChampsCorrects() {
		return (this.getNom() != null & this.getDateDebut() != null & this.getDateFin() != null & this.getHeure() != null);
	}
	
}
