package ihm;

import modele.Arbitre;

import modele.Compte;
import modele.Ecurie;
import modele.Equipe;
import modele.Gestionnaire;
import modele.Joueur;
import modele.Responsable;

//import controleurs.ControleurConnexion;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
import javax.swing.DropMode;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import connexion.ConnexionBDD;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class Connexion {

	private JFrame frame;
	
	private Map<String,Compte> lesComptes= new HashMap<String,Compte>();
	
	ConnexionBDD conxBDD;
	Connection conx;
	
	private List<Arbitre> lesArbitres;
	private Map<Compte, Arbitre> compteVersArbitre;
	private List<Gestionnaire> lesGestionnaires;
	private Map<Compte, Gestionnaire> compteVersGestionnaire;
	private List<Responsable> lesResponsables;
	private Map<Compte, Responsable> compteVersResponsable;
	private List<Joueur> lesJoueurs;
	private Map<Compte, Joueur> compteVersJoueur;
	private List<Ecurie> lesEcuries;
	private Map<Compte, Ecurie> compteVersEcurie;
	private List<Equipe> lesEquipes;
	private Map<Compte, Equipe> compteVersEquipe;
	
	private JTextField idField;
	private JPasswordField pwdField;
	
	private Compte compte;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Connexion window = new Connexion();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Connexion() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		this.conx = ConnexionBDD.connecting();
		this.lesArbitres = ConnexionBDD.getArbitres();
		this.lesGestionnaires = ConnexionBDD.getGestionnaires();
		this.lesResponsables = ConnexionBDD.getResponsables();
		this.lesJoueurs = ConnexionBDD.getJoueurs();
		this.lesEcuries = ConnexionBDD.getEcuries();
		this.lesEquipes = ConnexionBDD.getEquipes();
		this.compteVersArbitre = new HashMap<Compte,Arbitre>();
		this.compteVersGestionnaire = new HashMap<Compte,Gestionnaire>();
		this.compteVersResponsable = new HashMap<Compte,Responsable>();
		this.compteVersJoueur = new HashMap<Compte,Joueur>();
		this.compteVersEcurie = new HashMap<Compte,Ecurie>();
		this.compteVersEquipe = new HashMap<Compte,Equipe>();
		
		
		for (Arbitre a : lesArbitres.values()) {
			lesComptes.put(a.getCompte().getIdentifiant(), a.getCompte());
			compteVersArbitre.put(a.getCompte(), a);
		}
		for (Gestionnaire g : lesGestionnaires.values()) {
			lesComptes.put(g.getCompte().getIdentifiant(), g.getCompte());
			compteVersGestionnaire.put(g.getCompte(), g);
		}
		for (Responsable r : lesResponsables.values()) {
			lesComptes.put(r.getCompte().getIdentifiant(), r.getCompte());
			compteVersResponsable.put(r.getCompte(), r);
		}
		for (Joueur j : lesJoueurs.values()) {
			lesComptes.put(j.getCompte().getIdentifiant(), j.getCompte());
			compteVersJoueur.put(j.getCompte(), j);
		}
		for (Ecurie e : lesEcuries.values()) {
			lesComptes.put(e.getCompte().getIdentifiant(), e.getCompte());
			compteVersEcurie.put(e.getCompte(), e);
		}
		for (Equipe e : lesEquipes.values()) {
			lesComptes.put(e.getCompte().getIdentifiant(), e.getCompte());
			compteVersEquipe.put(e.getCompte(), e);
		}
		
		JPanel panel_ENSEMBLE = new JPanel();
		frame.getContentPane().add(panel_ENSEMBLE, BorderLayout.CENTER);
		panel_ENSEMBLE.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_LOGS = new JPanel();
		panel_ENSEMBLE.add(panel_LOGS);
		panel_LOGS.setLayout(new BoxLayout(panel_LOGS, BoxLayout.PAGE_AXIS));
		
		JPanel panelID = new JPanel();
		panelID.setBackground(Color.WHITE);
		panelID.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_LOGS.add(panelID);
		panelID.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		// Identifiant
		JLabel lblNewLabel_ID = new JLabel("Identifiant");
		lblNewLabel_ID.setHorizontalAlignment(SwingConstants.CENTER);
		panelID.add(lblNewLabel_ID);
		idField = new JTextField();
		panelID.add(idField);
		idField.setHorizontalAlignment(SwingConstants.LEFT);
		idField.setColumns(10);
		idField.setName("idField");
		
		// Mot de passe
		JLabel lblNewLabel_MDP = new JLabel("mot de passe :");
		panelID.add(lblNewLabel_MDP);
		pwdField = new JPasswordField(10);
		pwdField.setHorizontalAlignment(SwingConstants.LEFT);
		panelID.add(pwdField);
		pwdField.setName("pwdField");
		
		// Bouton valider
		JPanel panelValider = new JPanel();
		panelValider.setBackground(Color.WHITE);
		panel_LOGS.add(panelValider);
		JButton validerBtn = new JButton("Valider");
		
		validerBtn.setBackground(Color.WHITE);
		panelValider.add(validerBtn);
		validerBtn.setName("valider");
		validerBtn.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 if (idEntre() && pwdEntre()) {
					 compte = getCompte(getId());
					 if (compte == null) {
						 erreurCompteInexistant();
					 }
					 else {
						 switch (compte.connexion(getPwd())) {
						 case -1 :
							 erreurCompteBloque();
							 break;
						 case 0 :
							 erreurMauvaisMdp(compte.getNbEssais());
							 resetPwd();
							 break;
						 case 1 :
							 try {
								connexionReussie(compte);
							} catch (SQLException e1) {
								
								e1.printStackTrace();
							}
						 }
					 }
				 }
			 }
		});
		
		JPanel panel_HAUT = new JPanel();
		panel_HAUT.setBackground(Color.WHITE);
		frame.getContentPane().add(panel_HAUT, BorderLayout.NORTH);
		
		JTextArea txtrEsporter = new JTextArea();
		txtrEsporter.setBackground(Color.WHITE);
		txtrEsporter.setFont(new Font("Microsoft JhengHei Light", Font.BOLD, 16));
		txtrEsporter.setEnabled(false);
		txtrEsporter.setEditable(false);
		txtrEsporter.setText("E-Sporter");
		panel_HAUT.add(txtrEsporter);
		
		JPanel panel_IMAGE = new JPanel();
		frame.getContentPane().add(panel_IMAGE, BorderLayout.EAST);
		JLabel lb_image = new JLabel("");
		lb_image.setIcon(new ImageIcon(Connexion.class.getResource("/ressources/coupe_login.jpg")));
		lb_image.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_IMAGE.add(lb_image);
	
	}
	
	public String getId() {
		return idField.getText();
	}
	
	public String getPwd() {
		return String.valueOf(pwdField.getPassword());
	}

	public Compte getCompte(String id) {
		return lesComptes.get(id);
	}
	
	public boolean idEntre() {
		return getId() != null;
	}
	
	public boolean pwdEntre() {
		return getPwd() !=  null;
	}

	public void resetPwd() {
		this.pwdField.setText("");
	}
	
	public void erreurCompteInexistant() {
		JOptionPane.showMessageDialog(null, "Cet identifiant ne correspond à aucun compte", "Identifiant non valide", 1);
	}

	public void erreurCompteBloque() {
		 JOptionPane.showMessageDialog(null, "Erreur : Ce compte est bloqué", "Compte bloqué", 1);
	}

	public void erreurMauvaisMdp(int nbEssais) {
		JOptionPane.showMessageDialog(null, "Erreur : Mauvais mot de passe, " + nbEssais + " essais restants", "Mauvais mot de passe", 1);
	}

	public void connexionReussie(Compte compte) throws SQLException {
		if (this.compteVersArbitre.get(compte) != null) {
			AccueilArbitre accueil = new AccueilArbitre(this.compteVersArbitre.get(compte));
			accueil.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			accueil.setVisible(true);
			frame.dispose();
		} else if (this.compteVersGestionnaire.get(compte) != null) {
			AccueilGestionnaire accueil = new AccueilGestionnaire(this.compteVersGestionnaire.get(compte));
			accueil.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			accueil.setVisible(true);
			frame.dispose();
		} else if (this.compteVersResponsable.get(compte) != null) {
			AccueilResponsable accueil = new AccueilResponsable(this.compteVersResponsable.get(compte));
			accueil.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			accueil.setVisible(true);
			frame.dispose();
		} else if (this.compteVersJoueur.get(compte) != null) {
			AccueilJoueur accueil = new AccueilJoueur(this.compteVersJoueur.get(compte));
			accueil.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			accueil.setVisible(true);
			frame.dispose();
		} else if (this.compteVersEcurie.get(compte) != null) {
			AccueilEcurie accueil = new AccueilEcurie(this.compteVersEcurie.get(compte));
			accueil.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			accueil.setVisible(true);
			frame.dispose();
		} else if (this.compteVersEquipe.get(compte) != null) {
			AccueilEquipe accueil = new AccueilEquipe(this.compteVersEquipe.get(compte));
			accueil.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			accueil.setVisible(true);
			frame.dispose();
		}
		
	}

}
