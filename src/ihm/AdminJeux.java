package ihm;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import connexion.ConnexionBDD;
import modele.Ecurie;
import modele.Equipe;
import modele.Jeu;

public class AdminJeux extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private Connection connx = ConnexionBDD.connecting();
	private Map<String, Jeu> jeux;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AdminJeux dialog = new AdminJeux();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AdminJeux() throws SQLException {
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
				JLabel lblBienvenue = new JLabel("Administrer les jeux");
				lblBienvenue.setHorizontalAlignment(SwingConstants.CENTER);
				splitPane.setLeftComponent(lblBienvenue);
			}
			JScrollPane scrollPane = new JScrollPane();
			splitPane.setRightComponent(scrollPane);
			{
				table = new JTable();
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						int row = table.getSelectedRow();
			            Jeu jeu = (Jeu) jeux.values().toArray()[row];
			            System.out.println(jeu.getNom());
			            SupprModifJeu s = new SupprModifJeu(jeu);
						s.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						s.setVisible(true);
					}
				});
				table.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"Nom", "Nombre joueurs"
					}
				) {
					Class[] columnTypes = new Class[] {
						String.class, String.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
					boolean[] columnEditables = new boolean[] {
							false, false
					};
					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				
				this.jeux = ConnexionBDD.getJeux();
				for(Jeu j : this.jeux.values()) {	
						model.addRow(new Object[]{j.getNom(), j.getNbJoueurs()});
				}
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton ajouterButton = new JButton("Ajouter un jeu");
				ajouterButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							AjoutJeu ajoutJeu = new AjoutJeu();
							ajoutJeu.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
							ajoutJeu.setVisible(true);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				buttonPane.add(ajouterButton);
				getRootPane().setDefaultButton(ajouterButton);
			}
			{
				JButton cancelButton = new JButton("Fermer");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}

}
