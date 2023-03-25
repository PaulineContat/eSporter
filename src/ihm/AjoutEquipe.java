package ihm;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import connexion.ConnexionBDD;
import modele.Compte;
import modele.Ecurie;
import modele.Equipe;
import modele.Jeu;
import modele.Joueur;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;

public class AjoutEquipe extends JDialog {
	
	private static final int MAXTAILLEEQUIPE = 10;

	private final JPanel contentPanel = new JPanel();
	private ArrayList<JComboBox> comboBoxJoueurs;
	private JComboBox comboBoxJeux;
	private JComboBox comboBoxEcuries;
	private JTextField textFieldNom;
	private JPasswordField pwdField;
	private JPasswordField pwdConfirmField;
	
	private Ecurie ecurie;
	
	private List<Jeu> lesJeux;
	private List<Joueur> lesJoueurs;
	private List<Ecurie> lesEcuries;
	
	private ConnexionBDD conxBDD;
	private Connection conx;


	public AjoutEquipe(Ecurie ecurie) throws SQLException {
		this.ecurie = ecurie;
		
		this.conx = ConnexionBDD.connecting();
		lesJeux = ConnexionBDD.getJeux();
		lesJoueurs = ConnexionBDD.getJoueurs();
		
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
			JPanel panelJeu = new JPanel();
			contentPanel.add(panelJeu);
			{
				JLabel lblJeu = new JLabel("Jeu");
				panelJeu.add(lblJeu);
			}
			{
				comboBoxJeux = new JComboBox(lesJeux.toArray());
				comboBoxJeux.setName("comboBoxJeux");
				comboBoxJeux.setSelectedIndex(-1);
				panelJeu.add(comboBoxJeux);
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
						 ajouterEquipe();
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
	
	public AjoutEquipe() throws SQLException {
		
		this.conx = ConnexionBDD.connecting();
		lesJeux = ConnexionBDD.getJeux();
		lesJoueurs = ConnexionBDD.getJoueurs();
		lesEcuries = ConnexionBDD.getEcuries();
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		{
			JPanel panelEcurie = new JPanel();
			contentPanel.add(panelEcurie);
			{
				JLabel lblEcurie = new JLabel("Ecurie");
				panelEcurie.add(lblEcurie);
			}
			{
				comboBoxEcuries = new JComboBox(lesEcuries.toArray());
				comboBoxEcuries.setName("comboBoxEcuries");
				comboBoxEcuries.setSelectedIndex(-1);
				comboBoxEcuries.addActionListener(new ActionListener() {
					 public void actionPerformed(ActionEvent e) {
						 ecurie = lesEcuries.get((int) comboBoxEcuries.getSelectedItem());
					 }
				});
				panelEcurie.add(comboBoxEcuries);
			}
		}
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
			JPanel panelJeu = new JPanel();
			contentPanel.add(panelJeu);
			{
				JLabel lblJeu = new JLabel("Jeu");
				panelJeu.add(lblJeu);
			}
			{
				String[] array = new String[lesJeux.size()];
				for(int i = 0; i < array.length; i++) {
				    array[i] = lesJeux.get(i).getNom();
				}
				comboBoxJeux = new JComboBox(array);
				comboBoxJeux.setName("comboBoxJeux");
				comboBoxJeux.setSelectedIndex(-1);
				panelJeu.add(comboBoxJeux);
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
						 ajouterEquipe();
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
	

	public boolean nomEntre() {
		return textFieldNom.getText() != null;
	}
	
	public boolean mdpCorrect() {
		return getPwd().equals(getPwdConfirm());
	}
	
	public boolean jeuChoisi() {
		return this.comboBoxJeux.getSelectedIndex() != -1;
	}
	
	public boolean ecurieChoisie() {
		return this.comboBoxEcuries.getSelectedIndex() != -1;
	}
	
	public boolean tousLesChampsCorrects() {
		return nomEntre() && mdpCorrect() && jeuChoisi() && (this.ecurie != null || this.ecurieChoisie());
	}
	
	public String getNom() {
		return textFieldNom.getText();
	}
	
	private String getPwd() {
		return String.valueOf(this.pwdField.getPassword());
	}
	
	private String getPwdConfirm() {
		return String.valueOf(this.pwdConfirmField.getPassword());
	}
	
	public Jeu getJeu() {
		return lesJeux.get((int) this.comboBoxJeux.getSelectedItem());
	}
	
	private boolean champsRemplis() {
		if(String.valueOf(this.pwdField.getPassword()) == null) {
			throw new IllegalArgumentException("Remplir le mot de passe");
		}else {
			
			return true;
		}
		
	}

	public void ajouterEquipe() {
		if (tousLesChampsCorrects() & champsRemplis()) {
			ConnexionBDD.enregistrerEquipe(new Equipe(getNom(), Compte.passwordHash(getPwd()), getJeu(), ecurie));
			dispose();
		}
	}
	
	public static void main(String[] args) throws SQLException {
		Connection conx = ConnexionBDD.connecting();
		Ecurie ecurie1 = ConnexionBDD.getEcurieFromId("ecurie1");
		AjoutEquipe AE = new AjoutEquipe(ecurie1);
		AE.setVisible(true);	
	}



}
