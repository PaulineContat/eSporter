package vues;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

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

import controleurs.ControleurSupprModifEcurie;
import modele.Ecurie;
import modeles.ModeleSupprModifEcurie;

public class VueSupprModifEcurie extends JDialog {

	private ModeleSupprModifEcurie modele;
	private ControleurSupprModifEcurie controleur;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldNom;
	private JComboBox<String> comboBoxStatut;

	public VueSupprModifEcurie(Ecurie ecurie, VueAdminEcuries vue) {

		this.modele = new ModeleSupprModifEcurie(ecurie, vue);
		this.controleur = new ControleurSupprModifEcurie(this, modele);
		
		this.setTitle("Supprimer ou modifier une écurie");

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
				JLabel lblChoix = new JLabel("Modifier ou supprimer une ecurie");
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
							textFieldNom.setText(ecurie.getNom());
							panel.add(textFieldNom);
							textFieldNom.setColumns(10);
						}
					}
				}
				{
					JPanel panel_statut = new JPanel();
					panel_formulaire.add(panel_statut);
					panel_statut.setLayout(new GridLayout(0, 2, 0, 0));
					{
						JPanel panel = new JPanel();
						panel_statut.add(panel);
						panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
						{
							JLabel lblStatut = new JLabel("Catégorie");
							lblStatut.setHorizontalAlignment(SwingConstants.CENTER);
							panel.add(lblStatut);
						}
					}
					{
						JPanel panel = new JPanel();
						panel_statut.add(panel);
						panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
						{
							this.comboBoxStatut = new JComboBox<>();
							for (Ecurie.Statut c : Ecurie.Statut.values()) {
								this.comboBoxStatut.addItem(c.name());
							}
							this.comboBoxStatut.setName("comboBoxStatut");
							this.comboBoxStatut.setSelectedItem(ecurie.getStatut().name());
							panel.add(this.comboBoxStatut);
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
				btnValiderLesModifications.addActionListener(this.controleur);
				buttonPane.add(btnValiderLesModifications);
			}
			{
				JButton btnSupprimerEquipe = new JButton("Supprimer l'écurie");
				buttonPane.add(btnSupprimerEquipe);
				btnSupprimerEquipe.addActionListener(this.controleur);
			}
			{
				JButton cancelButton = new JButton("Fermer");
				cancelButton.addActionListener(this.controleur);
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public boolean nomEntre() {
		return !textFieldNom.getText().isEmpty();
	}
	
	public boolean statutEntre() {
		return comboBoxStatut.getSelectedItem() != null;
	}
	
	public boolean tousLesChampsCorrects() {
		return this.nomEntre() && this.statutEntre();
	}
	
	public String getNom() {
		return textFieldNom.getText();
	}
	
	public Ecurie.Statut getStatut(){
		return Ecurie.Statut.valueOf((String) this.comboBoxStatut.getSelectedItem());
	}
	
	public void infoModifEnregistree() {
		JOptionPane.showMessageDialog(null, "Modification bien enregistrée", "Confirmation", 1);
	}
	
	public void erreurModifNonEnregistree() {
		JOptionPane.showMessageDialog(null, "Une erreur est survenue. Vos modifications n'ont pas été enregistrées.", "Erreur", 1);
	}
	
	public void erreurChampsNonRemplis() {
		JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.", "Erreur", 1);
	}
	
	public void infoEcurieSupprime() {
		JOptionPane.showMessageDialog(null, "L'écurie a été supprimée", "Confirmation", 1);
	}

}
