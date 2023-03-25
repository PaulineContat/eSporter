package vues;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controleurs.ControleurSupprModifEquipe;
import modele.Equipe;
import modeles.ModeleSupprModifEquipe;

public class VueSupprModifEquipe extends JDialog {

	private final JPanel contentPanel;
	private JTextField textFieldNom;
	private ModeleSupprModifEquipe modele;
	private ControleurSupprModifEquipe controleur;
	
	public VueSupprModifEquipe(Equipe equipe, VueAdminEquipes vue) {
		
		this.modele = new ModeleSupprModifEquipe(equipe, vue);
		this.controleur = new ControleurSupprModifEquipe(this, modele);
		this.contentPanel = new JPanel();
			
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
					JLabel lblChoix = new JLabel("Modifier ou supprimer une équipe");
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
								this.textFieldNom = new JTextField();
								this.textFieldNom.setText(equipe.getNom());
								panel.add(textFieldNom);
								this.textFieldNom.setColumns(10);
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
					JButton btnSupprimerEquipe = new JButton("Supprimer l'équipe");
					btnSupprimerEquipe.addActionListener(this.controleur);
					buttonPane.add(btnSupprimerEquipe);
				}
				{
					JButton cancelButton = new JButton("Fermer");
					cancelButton.addActionListener(this.controleur);
					buttonPane.add(cancelButton);
				}
		}
	}
		
	public String getNom() {
		return textFieldNom.getText();
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
	
	public void infoEquipeSupprime() {
		JOptionPane.showMessageDialog(null, "L'équipe a été supprimée", "Confirmation", 1);
	}
		
}