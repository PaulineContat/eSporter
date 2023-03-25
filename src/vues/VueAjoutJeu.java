package vues;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controleurs.ControleurAjoutJeu;
import modele.Jeu;
import modeles.ModeleAjoutJeu;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
	
public class VueAjoutJeu extends JDialog {
	
	private ControleurAjoutJeu controleur;
	private ModeleAjoutJeu modele;
	
	private final JPanel contentPanel;
	private JTextField textFieldNom;
	private JSpinner spinnerNbJoueurs;

	public VueAjoutJeu(VueAdminJeux vue) {
		this.modele = new ModeleAjoutJeu(vue);
		this.controleur = new ControleurAjoutJeu(this, this.modele);
		
		this.contentPanel = new JPanel();
		this.setTitle("Ajouter un jeu");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));

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
				spinnerNbJoueurs.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(Jeu.NB_MAX_JOUEUR), Integer.valueOf(1)));
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
				okButton.addActionListener(this.controleur);
			}
			{
				JButton cancelButton = new JButton("Annuler");
				cancelButton.setActionCommand("Annuler");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(this.controleur);
			}
		}
	}
	
	public String getNomJeu() {
		return textFieldNom.getText();
	}
	
	public int getNbJoueurs() {
		return (int) spinnerNbJoueurs.getValue();
	}
	
	public boolean nomEntre() {
		return !textFieldNom.getText().isEmpty();
	}
	
	public void erreurIdentifiantExisteDeja() {
		JOptionPane.showMessageDialog(null, "Ce jeu existe déjà", "Ce jeu existe déjà", 1);
	}
	
	public void erreurChampsNonRemplis() {
		JOptionPane.showMessageDialog(null, "Veuillez saisir un nom.", "Champs non remplis", 1);
	}
}
