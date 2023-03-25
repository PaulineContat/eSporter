package vues;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controleurs.ControleurAjoutArbitre;
import modeles.ModeleAjoutArbitre;

public class VueAjoutArbitre extends JDialog {

	private ModeleAjoutArbitre modele;
	private ControleurAjoutArbitre controleur;
	private final JPanel contentPanel = new JPanel();
	private Container contentPane;
	
	
	
	private JTextField textFieldNom;
	private JTextField textFieldPrenom;
	private JTextField textFieldTelephone;
	private JPasswordField pwdField;
	private JPasswordField pwdConfirmField;

	public VueAjoutArbitre(VueAdminArbitres vue) {
		
		this.modele = new ModeleAjoutArbitre(vue);
		this.controleur = new ControleurAjoutArbitre(this, this.modele);
		
		this.setTitle("Ajouter un arbitres");

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		{
			JPanel panelPrenom = new JPanel();
			contentPanel.add(panelPrenom);
			{
				JLabel lblPrenom = new JLabel("Prenom : ");
				panelPrenom.add(lblPrenom);
			}
			{
				textFieldPrenom = new JTextField();
				textFieldPrenom.setColumns(10);
				panelPrenom.add(textFieldPrenom);
			}
		}
		{
			JPanel panelNom = new JPanel();
			contentPanel.add(panelNom);
			{
				JLabel lblNom = new JLabel("Nom : ");
				panelNom.add(lblNom);
			}
			{
				textFieldNom = new JTextField();
				panelNom.add(textFieldNom);
				textFieldNom.setColumns(10);
			}
		}
		{
			JPanel panelTel = new JPanel();
			contentPanel.add(panelTel);
			{
				JLabel lblTel = new JLabel("Telephone : ");
				panelTel.add(lblTel);
			}
			{
				textFieldTelephone = new JTextField();
				textFieldTelephone.setColumns(10);
				panelTel.add(textFieldTelephone);
			}
		}
		{
			JPanel panelPwd = new JPanel();
			contentPanel.add(panelPwd);
			{
				JLabel lblPwd = new JLabel("Mot de passe : ");
				panelPwd.add(lblPwd);
			}
			{
				pwdField = new JPasswordField(10);
				panelPwd.add(pwdField);
			}
		}
		{
			JPanel panelPwdConfirm = new JPanel();
			contentPanel.add(panelPwdConfirm);
			{
				JLabel lblPwdConfirm = new JLabel("Confirmation mot de passe : ");
				panelPwdConfirm.add(lblPwdConfirm);
			}
			{
				pwdConfirmField = new JPasswordField(10);
				panelPwdConfirm.add(pwdConfirmField);
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
				cancelButton.setActionCommand("Annuler");
				cancelButton.addActionListener(this.controleur);
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public String getNom() {
		return textFieldNom.getText();
	}
	
	public String getPrenom() {
		return textFieldPrenom.getText();
	}
	
	public String getTelephone() {
		return textFieldTelephone.getText();
	}
	
	public String getMDP() {
		return String.valueOf(pwdField.getPassword());
	}
	
	private String getConfirmMDP() {
		return String.valueOf(pwdConfirmField.getPassword());
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
	
	private boolean mdpCorrect() {
		return this.getMDP().equals(this.getConfirmMDP()) && this.getMDP() != null;
	}
	
	public boolean tousLesChampsCorrects() {
		return this.nomEntre() && this.prenomEntre() && this.telephoneEntre() && this.mdpCorrect();
	}

	public void erreurIdentifiantExisteDeja() {
		JOptionPane.showMessageDialog(null, "Cet identifiant existe déjà", "Cet identifiant existe déjà", 1);
	}
	
	public void erreurChampsNonRemplis() {
		JOptionPane.showMessageDialog(null, "Champs non remplis", "Champs non remplis", 1);
	}

}
