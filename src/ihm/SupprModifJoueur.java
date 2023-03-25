package ihm;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import connexion.ConnexionBDD;
import modele.Joueur;

import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SupprModifJoueur extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldNom;
	private JTextField textFieldPrenom;
	private JTextField textFieldTelephone;
	private JTextField textFieldPseudo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ConnexionBDD.connecting();
			Joueur joueur = ConnexionBDD.getJoueurs().get(3);
			SupprModifJoueur dialog = new SupprModifJoueur(joueur);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SupprModifJoueur(Joueur joueur) {
		ConnexionBDD.connecting();
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
				JLabel lblChoix = new JLabel("Modifier ou supprimer un joueur");
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
						JPanel panel = new JPanel();
						panel_nom.add(panel);
						{
							JLabel lblNom = new JLabel("Nom");
							lblNom.setHorizontalAlignment(SwingConstants.CENTER);
							panel.add(lblNom);
						}
					}
					{
						JPanel panel = new JPanel();
						panel_nom.add(panel);
						{
							textFieldNom = new JTextField();
							textFieldNom.setText(joueur.getNom());
							textFieldNom.setHorizontalAlignment(SwingConstants.TRAILING);
							textFieldNom.setColumns(10);
							panel.add(textFieldNom);
						}
					}
				}
				{
					JPanel panel_prenom = new JPanel();
					panel_formulaire.add(panel_prenom);
					panel_prenom.setLayout(new GridLayout(0, 2, 0, 0));
					{
						JPanel panel = new JPanel();
						panel_prenom.add(panel);
						{
							JLabel lblPrenom = new JLabel("Prénom");
							lblPrenom.setHorizontalAlignment(SwingConstants.LEFT);
							panel.add(lblPrenom);
						}
					}
					{
						JPanel panel = new JPanel();
						panel_prenom.add(panel);
						{
							textFieldPrenom = new JTextField();
							textFieldPrenom.setText(joueur.getPrenom());
							textFieldPrenom.setHorizontalAlignment(SwingConstants.TRAILING);
							textFieldPrenom.setColumns(10);
							panel.add(textFieldPrenom);
						}
					}
				}
				{
					JPanel panel_telephone = new JPanel();
					panel_formulaire.add(panel_telephone);
					panel_telephone.setLayout(new GridLayout(0, 2, 0, 0));
					{
						JPanel panel = new JPanel();
						panel_telephone.add(panel);
						{
							JLabel lblTelephone = new JLabel("Téléphone");
							panel.add(lblTelephone);
						}
					}
					{
						JPanel panel = new JPanel();
						panel_telephone.add(panel);
						{
							textFieldTelephone = new JTextField();
							textFieldTelephone.setText(joueur.getTel());
							textFieldTelephone.setHorizontalAlignment(SwingConstants.TRAILING);
							textFieldTelephone.setColumns(10);
							panel.add(textFieldTelephone);
						}
					}
				}
				{
					JPanel panel_pseudo = new JPanel();
					panel_formulaire.add(panel_pseudo);
					panel_pseudo.setLayout(new GridLayout(0, 2, 0, 0));
					{
						JPanel panel = new JPanel();
						panel_pseudo.add(panel);
						{
							JLabel lblPseudo = new JLabel("Pseudo");
							panel.add(lblPseudo);
						}
					}
					{
						JPanel panel = new JPanel();
						panel_pseudo.add(panel);
						{
							textFieldPseudo = new JTextField();
							textFieldPseudo.setText(joueur.getPseudo());
							textFieldPseudo.setHorizontalAlignment(SwingConstants.TRAILING);
							textFieldPseudo.setColumns(10);
							panel.add(textFieldPseudo);
						}
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
						joueur.setNom(getNom());
						joueur.setPrenom(getPrenom());
						joueur.setTel(getTel());
						joueur.setPseudo(getPseudo());
						if (joueur.getNom() != null && joueur.getPrenom() != null && joueur.getTel() != null && joueur.getPseudo() != null) {
							ConnexionBDD.modifierJoueur(joueur);
							JOptionPane.showMessageDialog(null, "Modification bien enregistrée", "Confirmation", 1);
						}
					}
				});
				buttonPane.add(btnValiderLesModifications);
			}
			{
				JButton btnSupprimerLeJoueur = new JButton("Supprimer le joueur");
				btnSupprimerLeJoueur.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ConnexionBDD.supprimerJoueur(joueur);
						dispose();
					}
				});
				buttonPane.add(btnSupprimerLeJoueur);
			}
			{
				JButton cancelButton = new JButton("Annuler");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public String getNom() {
		return textFieldNom.getText();
	}
	
	public String getPrenom() {
		return textFieldPrenom.getText();
	}
	
	public String getTel() {
		return textFieldTelephone.getText();
	}
	
	public String getPseudo() {
		return textFieldPseudo.getText();
	}

}
