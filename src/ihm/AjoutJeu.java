package ihm;

import java.awt.BorderLayout;
import java.awt.Component;
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
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class AjoutJeu extends JDialog {
	

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldNom;
	private JSpinner spinnerNbJoueurs;
	private JTextField textFieldId;
	private Connection conx;
	
	/**
	 * Create the dialog.
	 * @throws SQLException 
	 */
	public AjoutJeu() throws SQLException {
		
		this.conx = ConnexionBDD.connecting();
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		{
			JPanel panelId = new JPanel();
			contentPanel.add(panelId);
			{
				JLabel lblId = new JLabel("Identifiant : ");
				panelId.add(lblId);
			}
			{
				textFieldId = new JTextField();
				panelId.add(textFieldId);
				textFieldId.setColumns(10);
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
			JPanel panelNbJoueurs = new JPanel();
			contentPanel.add(panelNbJoueurs);
			{
				JLabel lblNbJoueurs = new JLabel("Nombre de joueurs : ");
				panelNbJoueurs.add(lblNbJoueurs);
			}
			{
				spinnerNbJoueurs = new JSpinner();
				spinnerNbJoueurs.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
				panelNbJoueurs.add(spinnerNbJoueurs);
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
						 ajouterJeu();
					 }
				});
			}
			{
				JButton cancelButton = new JButton("Annuler");
				cancelButton.setActionCommand("Annuler");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
			}
		}
	}


	
	public boolean nomEntre() {
		return textFieldNom.getText() != null;
	}
	
	
	private String getNomJeu() {
		return textFieldNom.getText();
	}
	
	public int getNbJoueurs() {
		return (int) spinnerNbJoueurs.getValue();
	}
	
	private String getIdJeu() {
		return textFieldId.getText();
	}


	public void ajouterJeu() {
		System.out.println("nomEntre : " + getNomJeu());
		System.out.println("nbJoueurs : " + getNbJoueurs());
		System.out.println("idJeu : " + getIdJeu());
		if (nomEntre()) {
			ConnexionBDD.enregistrerJeu(new Jeu(getIdJeu(), getNomJeu(), getNbJoueurs()));
			dispose();
		}
	}
	




	public static void main(String[] args) throws SQLException {
		Connection conx = ConnexionBDD.connecting();
		AjoutJeu e = new AjoutJeu();
		e.setVisible(true);
	}

}
