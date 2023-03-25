package ihm;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

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
import modele.Arbitre;
import modele.Jeu;
import modele.Rencontre;

public class SupprModifRencontre extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldHeure;
	private JComboBox comboBoxArbitres;
	private Map<String, Arbitre> lesArbitres;
	
	private Connection conx;
	private Rencontre rencontre;

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
	 * @throws SQLException 
	 */
	public SupprModifRencontre(Rencontre rencontre) throws SQLException {
		
		this.conx = ConnexionBDD.connecting();
		lesArbitres = ConnexionBDD.getArbitres();
		this.rencontre = rencontre;
		
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
				JLabel lblChoix = new JLabel("Modifier ou supprimer une rencontre");
				lblChoix.setHorizontalAlignment(SwingConstants.CENTER);
				splitPane.setLeftComponent(lblChoix);
			}
			{
				JPanel panel_formulaire = new JPanel();
				splitPane.setRightComponent(panel_formulaire);
				panel_formulaire.setLayout(new GridLayout(0, 1, 0, 0));
				{
					JPanel panel_heure = new JPanel();
					panel_formulaire.add(panel_heure);
					panel_heure.setLayout(new GridLayout(0, 2, 0, 0));
					{
						JPanel panel = new JPanel();
						panel_heure.add(panel);
						panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
						{
							JLabel lblHeure = new JLabel("Heure");
							lblHeure.setHorizontalAlignment(SwingConstants.CENTER);
							panel.add(lblHeure);
						}
						{
							textFieldHeure = new JTextField();
							textFieldHeure.setText(this.rencontre.getHeure());
							panel.add(textFieldHeure);
							textFieldHeure.setColumns(10);
						}
					}
				}
				{
					JPanel panel_arbitre = new JPanel();
					panel_formulaire.add(panel_arbitre);
					panel_arbitre.setLayout(new GridLayout(0, 2, 0, 0));
					{
						JLabel lblArbitre = new JLabel("Arbitre");
						lblArbitre.setHorizontalAlignment(SwingConstants.CENTER);
						panel_arbitre.add(lblArbitre);
					}
					{
						comboBoxArbitres = new JComboBox(lesArbitres.keySet().toArray());
						comboBoxArbitres.setName("comboBoxJeux");
						comboBoxArbitres.setSelectedItem(rencontre.getArbitre().getNom());;
						panel_arbitre.add(comboBoxArbitres);
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
						 rencontre.setHeure(getHeure());
						 Arbitre arbitre = getArbitre();
						 rencontre.setArbitre(arbitre);
						 if (getHeure() != null) {
							 try {
								 ConnexionBDD.modifierRencontre(rencontre);
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
	
	public String getHeure() {
		return this.textFieldHeure.getText();
	}
	
	public Arbitre getArbitre() {
		return this.lesArbitres.get(this.comboBoxArbitres.getSelectedItem());
	}
	
}
