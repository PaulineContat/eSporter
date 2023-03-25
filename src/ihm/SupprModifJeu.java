package ihm;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import connexion.ConnexionBDD;
import modele.Jeu;

import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class SupprModifJeu extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldNom;
	private JSpinner spinnerNbJoueurs;
	private String ancienNom;
	
	private Connection conx;

	/**
	 * Create the dialog.
	 */
	public SupprModifJeu(Jeu jeu) {
		this.conx = ConnexionBDD.connecting();
		
		 ancienNom = jeu.getNom();
		
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
				btnValiderLesModifications.addActionListener(new ActionListener() {
					 public void actionPerformed(ActionEvent e) {
						 jeu.setNom(getNom());
						 jeu.setNbJoueurs(getNbJoueurs());
						 if (textFieldNom.getText() != null) {
							 try {
								 ConnexionBDD.modifierJeu(jeu, ancienNom);
								ancienNom = jeu.getNom();
								JOptionPane.showMessageDialog(null, "Modification bien enregistrée", "Confirmation", 1);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						 }
					 }
				});
				buttonPane.add(btnValiderLesModifications);
			}
			{
				JButton btnSupprimerEquipe = new JButton("Supprimer l'équipe");
				buttonPane.add(btnSupprimerEquipe);
				btnSupprimerEquipe.addActionListener(new ActionListener() {
					 public void actionPerformed(ActionEvent e) {
						 ConnexionBDD.supprimerJeu(jeu);
						dispose();
					 }
				});
			}
			{
				JButton cancelButton = new JButton("Fermer");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
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
	
}
