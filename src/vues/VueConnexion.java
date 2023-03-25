package vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import controleurs.ControleurConnexion;
import modele.Compte;
import modeles.ModeleConnexion;

@SuppressWarnings("serial")
public class VueConnexion extends JFrame {

	private JFrame frame;
	private ModeleConnexion modele;
	private ControleurConnexion controleur;

	private JTextField idField;
	private JPasswordField pwdField;

	public VueConnexion() {
		this.modele = new ModeleConnexion();
		this.controleur = new ControleurConnexion(this, this.modele);
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel_ENSEMBLE = new JPanel();
		frame.getContentPane().add(panel_ENSEMBLE, BorderLayout.CENTER);
		panel_ENSEMBLE.setLayout(new BorderLayout(0, 200));

		JPanel panel_LOGS = new JPanel();
		panel_ENSEMBLE.add(panel_LOGS, BorderLayout.CENTER);
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
		JLabel lblNewLabel_MDP = new JLabel("Mot de passe :");
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
		validerBtn.addActionListener(controleur);

		JPanel panel_DROITE = new JPanel();
		panel_DROITE.setBackground(Color.WHITE);
		FlowLayout fl_panel_DROITE = (FlowLayout) panel_DROITE.getLayout();
		fl_panel_DROITE.setAlignment(FlowLayout.LEFT);
		panel_ENSEMBLE.add(panel_DROITE, BorderLayout.EAST);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("src/ressources/coupe_login.jpg"));
		panel_DROITE.add(lblNewLabel);

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
	}

	public JFrame getFrame() {
		return frame;
	}

	public String getId() {
		return idField.getText();
	}

	public String getPwd() {
		return String.valueOf(pwdField.getPassword());
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

	public void ouvreAccueil(Compte compte) {
		switch(compte.getType()){
		case ARBITRE:
			VueAccueilArbitre accueil = new VueAccueilArbitre(compte.getIdentifiant());
			accueil.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			accueil.setVisible(true);
			frame.dispose();
			break;
		case GESTIONNAIRE:
			VueAccueilGestionnaire accueilG = new VueAccueilGestionnaire(compte.getIdentifiant());
			accueilG.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			accueilG.setVisible(true);
			frame.dispose();
			break;
			/*case RESPONSABLE:
					VueAccueilResponsable accueil = new VueAccueilResponsable(compte.getIdentifiant());
					accueil.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					accueil.setVisible(true);
					frame.dispose();
					break;
				case JOUEUR:
					VueAccueilJoueur accueil = new VueAccueilJoueur(compte.getIdentifiant());
					accueil.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					accueil.setVisible(true);
					frame.dispose();
					break;*/
		case EQUIPE:
			VueAccueilEquipe accueilE = new VueAccueilEquipe(compte.getIdentifiant());
			accueilE.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			accueilE.setVisible(true);
			frame.dispose();
			break;
			/*case ECURIE:
					VueAccueilResponsable accueil = new VueAccueilResponsable(compte.getIdentifiant());
					accueil.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					accueil.setVisible(true);
					frame.dispose();
					break;*/
		default:
			JOptionPane.showMessageDialog(null, "Problème de type de compte", "Identifiant non valide", 1);
			break;
		}
	}
}