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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import controleurs.ControleurAjoutEcurie;
import modeles.ModeleAjoutEcurie;
import modele.Ecurie;
import modele.Ecurie.Statut;

public class VueAjoutEcurie extends JDialog {

	private ControleurAjoutEcurie controleur;
	private ModeleAjoutEcurie modele;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldNom;
	private JComboBox<String> comboBoxStatut;
	private JPasswordField pwdField;
	private JPasswordField pwdConfirmField;

	public VueAjoutEcurie(VueAdminEcuries vue) {
		this.modele = new ModeleAjoutEcurie(vue);
		this.controleur = new ControleurAjoutEcurie(this, this.modele);
		
		this.setTitle("Ajouter une écurie");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		{
			JPanel panelNom = new JPanel();
			contentPanel.add(panelNom);
			{
				JLabel lblNom = new JLabel("Nom");
				panelNom.add(lblNom);
			}
			{
				textFieldNom = new JTextField();
				panelNom.add(textFieldNom);
				textFieldNom.setColumns(10);
			}
		}
		{
			JPanel panelPwd = new JPanel();
			contentPanel.add(panelPwd);
			{
				JLabel lblPwd = new JLabel("Mot de passe");
				panelPwd.add(lblPwd);
			}
			{
				this.pwdField = new JPasswordField(10);
				panelPwd.add(this.pwdField);
			}
		}
		{
			JPanel panelPwdConfirm = new JPanel();
			contentPanel.add(panelPwdConfirm);
			{
				JLabel lblPwdConfirm = new JLabel("Confirmation mot de passe");
				panelPwdConfirm.add(lblPwdConfirm);
			}
			{
				this.pwdConfirmField = new JPasswordField( 10);
				panelPwdConfirm.add(this.pwdConfirmField);
			}
		}
		{
			JPanel panelStatut = new JPanel();
			contentPanel.add(panelStatut);
			{
				JLabel lblStatut = new JLabel("Statut");
				panelStatut.add(lblStatut);
			}
			{
				comboBoxStatut = new JComboBox<String>();
				for (Statut s : Ecurie.Statut.values()) {
					comboBoxStatut.addItem(s.name());
				}
				comboBoxStatut.setName("comboBoxStatut");
				comboBoxStatut.setSelectedIndex(-1);
				panelStatut.add(comboBoxStatut);
			}
		}

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Valider");
				okButton.setActionCommand("Valider");
				okButton.setName("valider");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(this.controleur);
			}
			{
				JButton cancelButton = new JButton("Annuler");
				cancelButton.addActionListener(this.controleur);
				buttonPane.add(cancelButton);
			}
		}
	}

	
	public boolean nomEntre() {
		return !textFieldNom.getText().isEmpty();
	}
	
	public boolean mdpCorrect() {
		return getPwd().equals(getPwdConfirm());
	}
	
	public boolean statutEntre() {
		return comboBoxStatut.getSelectedItem() != null;
	}
	
	public String getNom() {
		return textFieldNom.getText();
	}
	
	public String getPwd() {
		return String.valueOf(this.pwdField.getPassword());
	}
	
	private String getPwdConfirm() {
		return String.valueOf(this.pwdConfirmField.getPassword());
	}
	
	public Statut getStatut() {
		return Ecurie.Statut.valueOf(comboBoxStatut.getSelectedItem().toString());
	}
	
	public boolean tousLesChampsCorrects() {
		return this.nomEntre() && this.statutEntre() && this.mdpCorrect();
	}
	
	public void erreurChampsIncorrects() {
		JOptionPane.showMessageDialog(null, "Champs Incorrects", "Champs Incorrects", 1);
	}
	
	public void infoEcurieEnregistree() {
		JOptionPane.showMessageDialog(null, "Ecurie bien enregistrée", "Confirmation", 1);
	}
	
	public void erreurEcurieExisteDeja() {
		JOptionPane.showMessageDialog(null, "L'écurie existe déjà", "Erreur", 1);
	}

	public void erreurEcurieNonEnregistree() {
		JOptionPane.showMessageDialog(null, "L'écurie n'a pas été enregistrée", "L'écurie n'a pas été enregistrée", 1);
		
	}
	
	public void erreurMdpDifferents() {
		JOptionPane.showMessageDialog(null, "Les mots de passe sont différents", "Erreur", 1);
		
	}
	
	public void erreurStatutNonEntre() {
		JOptionPane.showMessageDialog(null, "Statut non entré", "Erreur", 1);
	}

	public void erreurNomNonEntre() {
		JOptionPane.showMessageDialog(null, "Nom non entré", "Erreur", 1);
		
	}


	public void erreurPwdNonEntre() {
		JOptionPane.showMessageDialog(null, "Mot de passe non entré", "Erreur", 1);
	}
}
