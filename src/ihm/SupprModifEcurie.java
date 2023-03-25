package ihm;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import connexion.ConnexionBDD;
import modele.Ecurie;
import modele.Tournoi;

import javax.swing.JTextArea;

public class SupprModifEcurie extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldNom;
	private JComboBox comboBoxStatut;
	
	private ConnexionBDD conxBDD;
	private Connection conx;

	public SupprModifEcurie(Ecurie ecurie) {
		this.conx = conxBDD.connecting();
		
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
				JLabel lblChoix = new JLabel("Modifier ou supprimer une écurie");
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
						panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
						{
							JLabel lblNom = new JLabel("Nom");
							lblNom.setHorizontalAlignment(SwingConstants.CENTER);
							panel.add(lblNom);
						}
					}
					{
						JPanel panel = new JPanel();
						panel_nom.add(panel);
						panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
						{
							textFieldNom = new JTextField();
							textFieldNom.setText(ecurie.getNom());
							panel.add(textFieldNom);
							textFieldNom.setColumns(10);
						}
					}
				}
				{
					JPanel panel_statut = new JPanel();
					panel_formulaire.add(panel_statut);
					panel_statut.setLayout(new GridLayout(0, 2, 0, 0));
					{
						JPanel panel = new JPanel();
						panel_statut.add(panel);
						panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
						{
							JLabel lblStatut = new JLabel("Catégorie");
							lblStatut.setHorizontalAlignment(SwingConstants.CENTER);
							panel.add(lblStatut);
						}
					}
					{
						JPanel panel = new JPanel();
						panel_statut.add(panel);
						panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
						{
							comboBoxStatut = new JComboBox();
							for (Ecurie.Statut c : Ecurie.Statut.values()) {
								comboBoxStatut.addItem(c.name());
							}
							comboBoxStatut.setName("comboBoxStatut");
							comboBoxStatut.setSelectedItem(ecurie.getStatut());
							panel.add(comboBoxStatut);
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
						 ecurie.setNom(getNom());
						 ecurie.setStatut(getStatut());
						 if (textFieldNom.getText() != null) {
							 try {
								 ConnexionBDD.modifierEcurie(ecurie);
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
				JButton btnSupprimerEcurie = new JButton("Supprimer l'écurie");
				btnSupprimerEcurie.addActionListener(new ActionListener() {
					 public void actionPerformed(ActionEvent e) {
						 ConnexionBDD.supprimerEcurie(ecurie);
						dispose();
					 }
				});
				buttonPane.add(btnSupprimerEcurie);
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
	
	public Ecurie.Statut getStatut(){
		return Ecurie.Statut.valueOf((String) this.comboBoxStatut.getSelectedItem());
	}
	
}
