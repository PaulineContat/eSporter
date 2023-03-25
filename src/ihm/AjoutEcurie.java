package ihm;

import java.awt.BorderLayout;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import connexion.ConnexionBDD;
import modele.Compte;
import modele.Ecurie;
import modele.Ecurie.Statut;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class AjoutEcurie extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JComboBox<String> comboBoxStatut;
	private JTextField textFieldNom;
	private JPasswordField pwdField;
	private JPasswordField pwdConfirmField;
	
	private Connection conx;

	public AjoutEcurie() throws SQLException {
		
		this.conx = ConnexionBDD.connecting();
		
		/*
		 * Cr�ation d'un JDialog d'une seule colonne
		 */
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		{
			JPanel panelNom = new JPanel();
			contentPanel.add(panelNom);
			/*
			 * Ajout des diff�rents labels au panel 
			 * Dans l'ordre suivant : Nom, Mot de passe et confirmation du mdp
			 */
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
				pwdConfirmField = new JPasswordField( 10);
				panelPwdConfirm.add(pwdConfirmField);
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
				okButton.addActionListener(new ActionListener() {
					 public void actionPerformed(ActionEvent e) {
						 System.out.println("Ajout");
						 ajouterEcurie();
					 }
				});
			}
			{
				JButton cancelButton = new JButton("Annuler");
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
	
	public boolean statutEntre() {
		return comboBoxStatut.getSelectedItem() != null;
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
	
	public Statut getStatut() {
		return Ecurie.Statut.valueOf(comboBoxStatut.getSelectedItem().toString());
	}
	
	private boolean tousLesChampsCorrects() {
		return this.nomEntre() && this.statutEntre() && this.mdpCorrect();
	}

	public void ajouterEcurie() {
		System.out.println("Nom :" + getNom());
		System.out.println("pwd :" + getPwd());
		System.out.println("Statut :" + getStatut().name());;
		Ecurie e =  new Ecurie(getNom(), Compte.passwordHash(getPwd()), getStatut());

		if (tousLesChampsCorrects()) {
			System.out.println("Enregistrement");
			ConnexionBDD.enregistrerEcurie(e);
			dispose();
		}
		
	}
	public static void main(String[] args) throws SQLException {
		Connection conx = ConnexionBDD.connecting();
		AjoutEcurie e = new AjoutEcurie();
		e.setVisible(true);
	}


}
