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
import modele.Arbitre;

public class SupprModifArbitre extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldTelephone;
	private JTextField textFieldPrenom;
	private JTextField textFieldNom;
	
	private ConnexionBDD conxBDD;
	private Connection conx;
	private Arbitre arbitre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SupprModifArbitre dialog = new SupprModifArbitre(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SupprModifArbitre(Arbitre arbitre) {
		
		this.conx = conxBDD.connecting();
		this.arbitre = arbitre;
		
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
				JLabel lblChoix = new JLabel("Modifier ou supprimer un arbitre");
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
						textFieldNom.setText(arbitre.getNom());
						panel_nom.add(textFieldNom);
						textFieldNom.setColumns(10);
					}
				}
				{
					JPanel panel_prenom = new JPanel();
					panel_formulaire.add(panel_prenom);
					panel_prenom.setLayout(new GridLayout(0, 2, 0, 0));
					{
						JLabel lblPrenom = new JLabel("Prenom");
						lblPrenom.setHorizontalAlignment(SwingConstants.CENTER);
						panel_prenom.add(lblPrenom);
					}
					{
						textFieldPrenom = new JTextField();
						textFieldPrenom.setText(arbitre.getPrenom());
						panel_prenom.add(textFieldPrenom);
						textFieldPrenom.setColumns(10);
					}
				}
				{
					JPanel panel_tel = new JPanel();
					panel_formulaire.add(panel_tel);
					panel_tel.setLayout(new GridLayout(0, 2, 0, 0));
					{
						JLabel lblTel = new JLabel("Téléphone");
						lblTel.setHorizontalAlignment(SwingConstants.CENTER);
						panel_tel.add(lblTel);
					}
					{
						textFieldTelephone = new JTextField();
						textFieldTelephone.setText(arbitre.getTel());
						panel_tel.add(textFieldTelephone);
						textFieldTelephone.setColumns(10);
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
						 arbitre.setNom(getNom());
						 arbitre.setPrenom(getPrenom());
						 arbitre.setTel(getTelephone());
						 if (tousLesChampsCorrects()) {
							 try {
								 ConnexionBDD.modifierArbitre(arbitre);
								JOptionPane.showMessageDialog(null, "Modification bien enregistrée", "Confirmation", 1);
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						 }
					 }
				});
				buttonPane.add(btnValiderLesModifications);
			}
			{
				JButton btnSupprimerArbitre = new JButton("Supprimer");
				btnSupprimerArbitre.addActionListener(new ActionListener() {
					 public void actionPerformed(ActionEvent e) {
						 ConnexionBDD.supprimerArbitre(arbitre);
						dispose();
					 }
				});
				buttonPane.add(btnSupprimerArbitre);
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
		return this.textFieldNom.getText();
	}
	
	public String getPrenom() {
		return this.textFieldPrenom.getText();
	}
	
	public String getTelephone() {
		return this.textFieldTelephone.getText();
	}
	
	public boolean tousLesChampsCorrects() {
		return (this.getNom() != null & this.getPrenom() != null & this.getTelephone() != null);
	}
	
}