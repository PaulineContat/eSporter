package ihm;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.AbstractButton;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import connexion.ConnexionBDD;
import modele.Compte;
import modele.Ecurie;
import modele.Ecurie.Statut;
import modele.Equipe;
import modele.Jeu;
import modele.Joueur;
import modele.Responsable;
import modele.Tournoi;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;

public class AjoutTournoi extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JComboBox comboBoxCategorie;
	private JComboBox<Jeu> comboBoxJeux;
	private JTextField textFieldNom;
	private List<Jeu> lesJeux;
	private Connection conx;
	private JTextField textFieldDateDebut;
	private JTextField textFieldDateFin;
	private JTextField textFieldDateLimiteInscription;
	private JTextField textFieldHeureDebut;
	private Responsable responsable;
	
	/**
	 * Create the dialog.
	 * @throws SQLException 
	 */
	public AjoutTournoi(Responsable responsable) throws SQLException {
		
		this.conx = ConnexionBDD.connecting();
		lesJeux = ConnexionBDD.getJeux();
		this.responsable = responsable;
		
		
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
			JPanel panelCategorie = new JPanel();
			contentPanel.add(panelCategorie);
			{
				JLabel lblCategorie = new JLabel("Categorie");
				panelCategorie.add(lblCategorie);
			}
			{
				comboBoxCategorie = new JComboBox<Tournoi.Categorie>();
				for (Tournoi.Categorie categorieTournoi : Tournoi.Categorie.values()) {
					comboBoxCategorie.addItem(categorieTournoi.name());
				}
				comboBoxCategorie.setName("comboBoxCategorie");
				comboBoxCategorie.setSelectedIndex(-1);
				panelCategorie.add(comboBoxCategorie);
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
				comboBoxJeux = new JComboBox<Jeu>();
				for (Jeu jeu : lesJeux) {
					comboBoxJeux.addItem(jeu);
				}
				comboBoxJeux.setName("comboBoxJeux");
				comboBoxJeux.setSelectedIndex(-1);
				panelJeu.add(comboBoxJeux);				
			}
		}
		{
			
			JPanel panelDateDebut = new JPanel();
			contentPanel.add(panelDateDebut);

			{

			
				try {
				    JLabel lblDateDebut = new JLabel("Date de début");
				    MaskFormatter formatter = new MaskFormatter("##/##/####");
				    formatter.setPlaceholderCharacter('_');
				    JFormattedTextField formattedTextField = new JFormattedTextField(formatter);
				    formattedTextField.setColumns(10);
				    lblDateDebut.setLabelFor(formattedTextField);
				    panelDateDebut.add(lblDateDebut);
				    panelDateDebut.add(formattedTextField);

				} catch (ParseException e) {
				    System.err.println("erreur");
				    e.printStackTrace();
				}
				
			}
			
		}
		{
			JPanel panelDateFin = new JPanel();
			contentPanel.add(panelDateFin);

			{
		
			try {	
		
				
			    JLabel lblDateFin = new JLabel("Date de fin");
			    MaskFormatter formatter = new MaskFormatter("##/##/####");
			    formatter.setPlaceholderCharacter('_');
			    JFormattedTextField formattedTextField = new JFormattedTextField(formatter);
			    formattedTextField.setColumns(10);
			    lblDateFin.setLabelFor(formattedTextField);
			    panelDateFin.add(lblDateFin);
			    panelDateFin.add(formattedTextField);

			} catch (ParseException e) {
			    System.err.println("erreur");
			    e.printStackTrace();
			}
			}
		}
		{
			JPanel panelDateLimiteInscription = new JPanel();
			contentPanel.add(panelDateLimiteInscription);

			try {	
		
				
			    JLabel lblDateLimiteInscription = new JLabel("Date de limite d'inscription");
			    MaskFormatter formatter = new MaskFormatter("##/##/####");
			    formatter.setPlaceholderCharacter('_');
			    JFormattedTextField formattedTextField = new JFormattedTextField(formatter);
			    formattedTextField.setColumns(10);
			    lblDateLimiteInscription.setLabelFor(formattedTextField);
			    panelDateLimiteInscription.add(lblDateLimiteInscription);
			    panelDateLimiteInscription.add(formattedTextField);

			} catch (ParseException e) {
			    System.err.println("erreur");
			    e.printStackTrace();
			}
		}
		{
			JPanel panelHeureDebut = new JPanel();
			contentPanel.add(panelHeureDebut);

			
			try {	
		
				
			    JLabel lblHeureDebut = new JLabel("Heure de debut");
			    MaskFormatter formatter = new MaskFormatter("##h##");
			    formatter.setPlaceholderCharacter('_');
			    JFormattedTextField formattedTextField = new JFormattedTextField(formatter);
			    formattedTextField.setColumns(10);
			    lblHeureDebut.setLabelFor(formattedTextField);
			    panelHeureDebut.add(lblHeureDebut);
			    panelHeureDebut.add(formattedTextField);
			    
			} catch (ParseException e) {
			    System.err.println("erreur");
			    e.printStackTrace();
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
						 ajouterTournoi();
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
	
	private boolean nomEntre() {
		return textFieldNom.getText() != null;
	}
	
	private boolean categorieEntre() {
		return comboBoxCategorie.getSelectedItem() != null;
	}
	
	private boolean dateDebutEntre() {
		return textFieldDateDebut.getText() != null;
	}
	
	private boolean dateFinEntre() {
		return textFieldDateFin.getText() != null;
	}
	
	private boolean dateLimiteInscriptionEntre() {
		return textFieldDateLimiteInscription.getText() != null;
	}
	
	private boolean heureDebutEntre() {
		return textFieldHeureDebut.getText() != null;
	}
	
	private boolean jeuEntre() {
		return comboBoxJeux.getSelectedItem() != null;
	}
	
	private boolean tousLesChampsCorrects() {
		return nomEntre() && categorieEntre() && dateDebutEntre() && dateFinEntre() && dateLimiteInscriptionEntre() && heureDebutEntre() && jeuEntre();
	}
	
	public String getNom() {
		return textFieldNom.getText();
	}
	
	private Tournoi.Categorie getCategorie() {
		return Tournoi.Categorie.valueOf(comboBoxCategorie.getSelectedItem().toString());
	}
	
	private String getDateDebut() {
		return textFieldDateDebut.getText();
	}
	
	private String getDateFin() {
		return textFieldDateFin.getText();
	}
	
	private String getDateLimiteInscription() {
		return textFieldDateLimiteInscription.getText();
	}
	
	private String getHeureDebut() {
		return textFieldHeureDebut.getText();
	}
	
	private Jeu getJeu() {
		return (Jeu) comboBoxJeux.getSelectedItem();
	}
	public String getId() {
		return (String) this.comboBoxJeux.getSelectedItem();
	}

	private void ajouterTournoi() {
		System.out.println("Nom :" + getNom());
		System.out.println("Categorie :" + getCategorie().name());
		System.out.println("Date de debut :" + getDateDebut());
		System.out.println("Date de fin :" + getDateFin());
		System.out.println("Date de limite d'inscription :" + getDateLimiteInscription());
		System.out.println("Heure de debut :" + getHeureDebut());
		System.out.println("Jeu :" + getJeu().getNom());
		Tournoi t =  new Tournoi(getId(), getNom(), getCategorie(), getJeu(), getDateDebut(), getDateFin(), getDateLimiteInscription(), getHeureDebut(), this.responsable);
		if (tousLesChampsCorrects()) {
			System.out.println("Enregistrement");
			ConnexionBDD.enregistrerTournoi(t);
			dispose();
		}
		
	}
	
	public static void main(String[] args) throws SQLException {
		Connection conx = ConnexionBDD.connecting();
		Responsable r = ConnexionBDD.getReponsableFromId("monique.bof");
		AjoutTournoi e = new AjoutTournoi(r);
		e.setVisible(true);
	}

}
