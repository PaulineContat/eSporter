package ihm;
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
import modele.Tournoi;
import modele.Equipe;

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
import java.util.List;

public class AdminTournois extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Connection connx = ConnexionBDD.connecting();
	private JTable table;
	private List<Tournoi> tournois;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AdminTournois dialog = new AdminTournois();
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
	public AdminTournois() throws SQLException {
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
				JLabel lblBienvenue = new JLabel("Administrer les tournois");
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
						"Nom", "Catégorie", "Jeu", "Date Début", "Date Fin", "Heure", "Date limite inscription", "Responsable"
					}
				) {
					Class[] columnTypes = new Class[] {
						String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
					boolean[] columnEditables = new boolean[] {
							false, false, false, false, false, false, false, false
					};
					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				
				this.tournois = ConnexionBDD.getTournois();
				for(Tournoi t : this.tournois) {
							model.addRow(new Object[]{t.getNom(), t.getCategorie().name(), t.getJeu().getNom(), t.getDateDebut(), t.getDateFin(), t.getHeure(), t.getDateLimiteInscription(), t.getResponsable().toString()});
				}
				scrollPane.setViewportView(table);
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int row = table.getSelectedRow();
						table.getValueAt(row, 1);
						SupprModifTournoi jd;
						try {
							jd = new SupprModifTournoi(tournois.get(row));
							jd.setModal(true);
				            jd.setVisible(true); 
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				});
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
//			{
//				JButton ajoutButton = new JButton("Ajouter un Tournoi");
//				ajoutButton.addActionListener(new ActionListener() {
//					public void actionPerformed(ActionEvent e) {
//						try {
//							AjoutTournoi ajoutTournoi = new AjoutTournoi();
//							ajoutTournoi.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//							ajoutTournoi.setVisible(true);
//						} catch (SQLException e1) {
//							e1.printStackTrace();
//						}
//					}
//				});
//				buttonPane.add(ajoutButton);
//				getRootPane().setDefaultButton(ajoutButton);
//			}
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

}
