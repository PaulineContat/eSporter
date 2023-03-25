package vues;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import connexion.ConnexionBDD;
import controleurs.ControleurAdminJoueur;
import modele.Ecurie;
import modele.Equipe;
import modele.Joueur;

import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VueAdminJoueurs extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private ControleurAdminJoueur controleur;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VueAdminJoueurs dialog = new VueAdminJoueurs();
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
	public VueAdminJoueurs() throws SQLException {
		this.controleur = new ControleurAdminJoueur(this);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JSplitPane splitPane = new JSplitPane();
			splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
			contentPanel.add(splitPane);
			{
				JLabel lblBienvenue = new JLabel("Administrer les joueurs");
				lblBienvenue.setHorizontalAlignment(SwingConstants.CENTER);
				splitPane.setLeftComponent(lblBienvenue);
			}
			JScrollPane scrollPane = new JScrollPane();
			splitPane.setRightComponent(scrollPane);
			{
				table = new JTable();
				table.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"Identifiant", "Nom", "Prenom", "Tel", "Pseudo"
					}
				) {
					Class[] columnTypes = new Class[] {
						String.class, String.class, String.class, String.class, String.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				controleur.remplirTableauJoueurs(model);
				
				scrollPane.setViewportView(table);
				table.addMouseListener(controleur.getMouseListenerTableau(table));
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
//			{
//				JButton ajouterButton = new JButton("Ajouter un joueur");
//				ajouterButton.addActionListener(new ActionListener() {
//					public void actionPerformed(ActionEvent e) {
//						try {
//							AjoutJoueur ajoutJoueur = new AjoutJoueur();
//							ajoutJoueur.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//							ajoutJoueur.setVisible(true);
//						} catch (SQLException e1) {
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						}
//					}
//				});;
//				buttonPane.add(ajouterButton);
//				getRootPane().setDefaultButton(ajouterButton);
//			}
			{
				JButton cancelButton = new JButton("Fermer");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
