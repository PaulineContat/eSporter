package ihm;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import connexion.ConnexionBDD;
import modele.Arbitre;
import modele.Compte;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class AjoutArbitre extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	
	private ConnexionBDD conxBDD;
	private Connection conx;
	private JTextField textFieldNom;
	private JTextField textFieldPrenom;
	private JPasswordField pwdField;
	private JPasswordField pwdConfirmField;
	private JTextField textFieldTelephone;

	/**
	 * Create the dialog.
	 * @throws SQLException 
	 */
	public AjoutArbitre() throws SQLException {
		
		this.conx = ConnexionBDD.connecting();
		
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
			JPanel panelPrenom = new JPanel();
			contentPanel.add(panelPrenom);
			{
				JLabel lblPrenom = new JLabel("Prenom");
				panelPrenom.add(lblPrenom);
			}
			{
				textFieldPrenom = new JTextField();
				textFieldPrenom.setColumns(10);
				panelPrenom.add(textFieldPrenom);
			}
		}
		{
			JPanel panelTel = new JPanel();
			contentPanel.add(panelTel);
			{
				JLabel lblTel = new JLabel("Telephone");
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
				JLabel lblPwd = new JLabel("Mot de passe");
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
				JLabel lblPwdConfirm = new JLabel("Confirmation mot de passe");
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
				okButton.addActionListener(new ActionListener() {
					 public void actionPerformed(ActionEvent e) {
						 ajouterArbitre();
						 dispose();
					 }
				});
			}
			{
				JButton cancelButton = new JButton("Annuler");
				cancelButton.setActionCommand("Annuler");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private boolean nomEntre() {
		return textFieldNom.getText() != null;
	}
	
	private boolean prenomEntre() {
		return textFieldPrenom.getText() != null;
	}
	
	private boolean telephoneEntre() {
		return textFieldTelephone.getText() != null;
	}
	
	private boolean mdpCorrect() {
		return this.getMDP().equals(this.getConfirmMDP()) && this.getMDP() != null;
	}
	
	private boolean tousLesChampsCorrects() {
		return this.nomEntre() && this.prenomEntre() && this.telephoneEntre() && this.mdpCorrect();
	}
	
	
	private String getNom() {
		return textFieldNom.getText();
	}
	
	private String getPrenom() {
		return textFieldPrenom.getText();
	}
	
	private String getTelephone() {
		return textFieldTelephone.getText();
	}
	
	private String getMDP() {
		return String.valueOf(pwdField.getPassword());
	}
	
	private String getConfirmMDP() {
		return String.valueOf(pwdConfirmField.getPassword());
	}
	
	private void ajouterArbitre() {
		if (this.tousLesChampsCorrects()) {
			ConnexionBDD.enregistrerArbitre(new Arbitre(this.getNom(), this.getPrenom(), this.getTelephone(), Compte.passwordHash(getMDP())));
		}
	}

	
	public static void main(String[] args) throws SQLException {
		AjoutArbitre r = new AjoutArbitre();
		r.setVisible(true);
	}

}
