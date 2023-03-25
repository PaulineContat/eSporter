package vues;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controleurs.ControleurSupprModifJeu;
import modele.Jeu;
import modeles.ModeleSupprModifJeu;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class VueSupprModifJeu extends JDialog {

	private ModeleSupprModifJeu modele;
	private ControleurSupprModifJeu controleur;
	
	private final JPanel contentPanel;
	private JTextField textFieldNom;
	private JSpinner spinnerNbJoueurs;

	public VueSupprModifJeu(Jeu jeu, VueAdminJeux vue) {

		this.modele = new ModeleSupprModifJeu(jeu, vue);
		this.controleur = new ControleurSupprModifJeu(this, modele);
		
		this.contentPanel = new JPanel();
		
		this.setTitle("Modifier ou supprimer un jeu");
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JSplitPane splitPane = new JSplitPane();
			splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
			contentPanel.add(splitPane, BorderLayout.CENTER);
			{
				JLabel lblChoix = new JLabel("Modifier ou supprimer un jeu");
				lblChoix.setHorizontalAlignment(SwingConstants.CENTER);
				splitPane.setLeftComponent(lblChoix);
			}
			{
				JPanel panel_formulaire = new JPanel();
				splitPane.setRightComponent(panel_formulaire);
				panel_formulaire.setLayout(new GridLayout(0, 1, 0, 0));
				{
					JPanel panel_nom = new JPanel();
					panel_formulaire.add(panel_nom);
					panel_nom.setLayout(new GridLayout(0, 2, 0, 0));
					{
						JLabel lblNom = new JLabel("Nom");
						lblNom.setHorizontalAlignment(SwingConstants.CENTER);
						panel_nom.add(lblNom);
					}
					{
						textFieldNom = new JTextField();
						textFieldNom.setText(jeu.getNom());
						panel_nom.add(textFieldNom);
						textFieldNom.setColumns(10);
					}
				}
				{
					JPanel panel_nbJoueurs = new JPanel();
					panel_formulaire.add(panel_nbJoueurs);
					panel_nbJoueurs.setLayout(new GridLayout(0, 2, 0, 0));
					{
						JLabel lblNbJoueurs = new JLabel("Nombre de joueurs");
						lblNbJoueurs.setHorizontalAlignment(SwingConstants.CENTER);
						panel_nbJoueurs.add(lblNbJoueurs);
					}
					{
						spinnerNbJoueurs = new JSpinner();
						spinnerNbJoueurs.setModel(new SpinnerNumberModel(jeu.getNbJoueurs(), 1, Jeu.NB_MAX_JOUEUR, 1));
						panel_nbJoueurs.add(spinnerNbJoueurs);
					}
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnValiderLesModifications = new JButton("Valider les modifications");
				btnValiderLesModifications.addActionListener(this.controleur);
				buttonPane.add(btnValiderLesModifications);
			}
			{
				JButton btnSupprimerEquipe = new JButton("Supprimer le jeu");
				buttonPane.add(btnSupprimerEquipe);
				btnSupprimerEquipe.addActionListener(this.controleur);
			}
			{
				JButton cancelButton = new JButton("Fermer");
				cancelButton.addActionListener(this.controleur);
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public String getNom() {
		return textFieldNom.getText();
	}
	
	public int getNbJoueurs() {
		return (int) spinnerNbJoueurs.getValue();
	}
	
	public void infoModifEnregistree() {
		JOptionPane.showMessageDialog(null, "Modification bien enregistrée", "Confirmation", 1);
	}
	
	public void erreurModifNonEnregistree() {
		JOptionPane.showMessageDialog(null, "Une erreur est survenue. Vos modifications n'ont pas été enregistrées.", "Erreur", 1);
	}
	
	public void erreurChampsNonRemplis() {
		JOptionPane.showMessageDialog(null, "Veuillez remplir le nom du jeu.", "Erreur", 1);
	}
	
	public void infoJeuSupprime() {
		JOptionPane.showMessageDialog(null, "Le jeu a été supprimé", "Confirmation", 1);
	}

}
