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
import modele.Equipe;

import javax.swing.JTextArea;

public class SupprModifEquipe extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldNom;

	private ConnexionBDD conxBDD;
	private Connection conx;

	public SupprModifEquipe(Equipe equipe) {
		this.conx = ConnexionBDD.connecting();

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
				JLabel lblChoix = new JLabel("Modifier ou supprimer une équipe");
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
							textFieldNom.setText(equipe.getNom());
							panel.add(textFieldNom);
							textFieldNom.setColumns(10);
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
						equipe.setNom(getNom());
						if (textFieldNom.getText() != null) {
							ConnexionBDD.modifierEquipe(equipe);
							JOptionPane.showMessageDialog(null, "Modification bien enregistrée", "Confirmation", 1);


						}
					}
				});
				buttonPane.add(btnValiderLesModifications);
			}
			{
				JButton btnSupprimerEquipe = new JButton("Supprimer l'équipe");
				btnSupprimerEquipe.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ConnexionBDD.supprimerEquipe(equipe);
						dispose();
					}
				});
				buttonPane.add(btnSupprimerEquipe);
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

}
