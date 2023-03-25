package ihm;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import connexion.ConnexionBDD;
import modele.Compte;
import modele.Equipe;
import modele.Jeu;
import modele.Joueur;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JComboBox;

public class AjoutJoueur extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	private JComboBox comboBoxEquipes;
	private JTextField textFieldNom;
	private JPasswordField pwdField;
	private JPasswordField pwdConfirmField;
	
	private Equipe equipe;

	private Connection conx;
	private JTextField textFieldPrenom;
	private JTextField textFieldTelephone;
	private JTextField textFieldPseudo;

	/**
	 * Create the dialog.
	 * @throws SQLException 
	 */
	public AjoutJoueur(Equipe equipe) throws SQLException {
		
		this.conx = ConnexionBDD.connecting();
		this.equipe = equipe;
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		{
			JPanel panelNom = new JPanel();
			contentPanel.add(panelNom);
			{
				JLabel lblNom = new JLabel("Nom", 10);
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
			JPanel panelPseudo = new JPanel();
			contentPanel.add(panelPseudo);
			{
				JLabel lblPseudo = new JLabel("Pseudo");
				panelPseudo.add(lblPseudo);
			}
			{
				textFieldPseudo = new JTextField();
				textFieldPseudo.setColumns(10);
				panelPseudo.add(textFieldPseudo);
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
				JLabel lblPwdConfirm = new JLabel("Confirmation mot de passe",10);
				panelPwdConfirm.add(lblPwdConfirm);
			}
			{
				//Mettre la taille max du mdp dans la BDD 
				pwdConfirmField = new JPasswordField(20);
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
					 public void actionPerformed(ActionEvent e)  {
						 try {

								 ajouterJoueur();
								 dispose();

							 
						 }catch(Exception exMauvaisMdp){
							 exMauvaisMdp.printStackTrace();
						 }
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
		return textFieldNom.getText() != null;
	}
	
	private boolean telephoneEntre() {
		return textFieldNom.getText() != null;
	}
	
	private boolean pseudoEntre() {
		return textFieldNom.getText() != null;
	}
	
	
	private boolean mdpCorrect() {
		return this.getMDP().equals(this.getConfirmMDP()) && this.getMDP() != null;
	}
	
	private boolean tousLesChampsCorrects() {
		return this.nomEntre() && this.prenomEntre() && this.telephoneEntre() && this.pseudoEntre() && this.mdpCorrect();
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
	
	private String getPseudo() {
		return textFieldPseudo.getText();
	}
	
	private String getMDP() {
		return String.valueOf(pwdField.getPassword());
	}
	
	private String getConfirmMDP() {
		return String.valueOf(pwdConfirmField.getPassword());
	}
	

	
	private Equipe getEquipe() {
		return this.equipe;
	}

	private void ajouterJoueur() {
		if (this.tousLesChampsCorrects()) {
			ConnexionBDD.enregistrerJoueur(new Joueur(this.getNom(), this.getPrenom(), this.getTelephone(), this.getPseudo(), Compte.passwordHash(getMDP()), getEquipe()));
		}
	}

	
	public static void main(String[] args) throws SQLException {
		Connection conx = ConnexionBDD.connecting();
		Equipe equipe = ConnexionBDD.getEquipeFromId("test");
		AjoutJoueur j = new AjoutJoueur(equipe);
		j.setVisible(true);
		
	}

}
