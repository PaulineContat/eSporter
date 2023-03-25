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

import controleurs.ControleurSupprModifArbitre;
import modele.Arbitre;
import modeles.ModeleSupprModifArbitre;

public class VueSupprModifArbitre extends JDialog {

	private ModeleSupprModifArbitre modele;
	private ControleurSupprModifArbitre controleur;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldPrenom;
	private JTextField textFieldNom;
	private JTextField textFieldTelephone;

	public VueSupprModifArbitre(String identifiantArbitre, VueAdminArbitres vue) {
		this.modele = new ModeleSupprModifArbitre(vue, identifiantArbitre);
		this.controleur = new ControleurSupprModifArbitre(this, modele);
		
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
				JLabel lblChoix = new JLabel("Modifier ou supprimer un arbitre");
				lblChoix.setHorizontalAlignment(SwingConstants.CENTER);
				splitPane.setLeftComponent(lblChoix);
			}
			{
				JPanel panel_formulaire = new JPanel();
				splitPane.setRightComponent(panel_formulaire);
				panel_formulaire.setLayout(new GridLayout(0, 1, 0, 0));
				{
					JPanel panel_prenom = new JPanel();
					panel_formulaire.add(panel_prenom);
					panel_prenom.setLayout(new GridLayout(0, 2, 0, 0));
					{
						JLabel lblPrenom = new JLabel("Prenom");
						lblPrenom.setHorizontalAlignment(SwingConstants.CENTER);
						panel_prenom.add(lblPrenom);
					}
					{
						textFieldPrenom = new JTextField();
						textFieldPrenom.setText(this.modele.getPrenom());
						panel_prenom.add(textFieldPrenom);
						textFieldPrenom.setColumns(10);
					}
				}
				{
					JPanel panel_nom = new JPanel();
					panel_formulaire.add(panel_nom);
					panel_nom.setLayout(new GridLayout(0, 2, 0, 0));
					{
						JLabel lblNom = new JLabel("Nom");
						lblNom.setHorizontalAlignment(SwingConstants.CENTER);
						panel_nom.add(lblNom);
					}
					{
						textFieldNom = new JTextField();
						textFieldNom.setText(this.modele.getNom());
						panel_nom.add(textFieldNom);
						textFieldNom.setColumns(10);
					}
				}
				{
					JPanel panel_tel = new JPanel();
					panel_formulaire.add(panel_tel);
					panel_tel.setLayout(new GridLayout(0, 2, 0, 0));
					{
						JLabel lblTel = new JLabel("Téléphone");
						lblTel.setHorizontalAlignment(SwingConstants.CENTER);
						panel_tel.add(lblTel);
					}
					{
						textFieldTelephone = new JTextField();
						textFieldTelephone.setText(this.modele.getTel());
						panel_tel.add(textFieldTelephone);
						textFieldTelephone.setColumns(10);
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
				JButton btnSupprimerArbitre = new JButton("Supprimer");
				btnSupprimerArbitre.addActionListener(this.controleur);
				buttonPane.add(btnSupprimerArbitre);
			}
			{
				JButton cancelButton = new JButton("Fermer");
				cancelButton.addActionListener(this.controleur);
				buttonPane.add(cancelButton);
			}
		}
	}

	public String getNom() {
		return this.textFieldNom.getText();
	}
	
	public String getPrenom() {
		return this.textFieldPrenom.getText();
	}
	
	public String getTelephone() {
		return this.textFieldTelephone.getText();
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
	
	public void infoArbitreSupprime() {
		JOptionPane.showMessageDialog(null, "L'arbitre a été supprimé", "Confirmation", 1);
	}
	
	private boolean nomEntre() {
		return this.getNom() != null;
	}
	
	private boolean prenomEntre() {
		return this.getPrenom() != null;
	}
	
	private boolean telephoneEntre() {
		return this.getTelephone() != null;
	}

	public boolean tousLesChampsCorrects() {
		return this.nomEntre() && this.prenomEntre() && this.telephoneEntre();
	}

}
