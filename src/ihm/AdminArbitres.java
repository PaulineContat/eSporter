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
import modele.Arbitre;
import modele.Ecurie;
import modele.Equipe;
import modele.Responsable;

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

public class AdminArbitres extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Connection connx = ConnexionBDD.connecting();
	private JTable table;
	private Map<String, Arbitre> arbitres;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AdminArbitres dialog = new AdminArbitres();
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
	public AdminArbitres() throws SQLException {
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
				JLabel lblBienvenue = new JLabel("Administrer les arbitres");
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
						"Identifiant", "Nom", "Prenom", "Tel"
					}
				) {
					Class[] columnTypes = new Class[] {
						String.class, String.class, String.class, String.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
					boolean[] columnEditables = new boolean[] {
							false, false, false, false
					};
					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				
				this.arbitres = ConnexionBDD.getArbitres();
				for(Arbitre a : this.arbitres.values()) {
							model.addRow(new Object[]{a.getCompte().getIdentifiant(), a.getNom(), a.getPrenom(), a.getTel()});
				}
				scrollPane.setViewportView(table);
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int row = table.getSelectedRow();
						table.getValueAt(row, 1);
						SupprModifArbitre jd = new SupprModifArbitre((Arbitre) arbitres.values().toArray()[row]);
						jd.setModal(true);
			            jd.setVisible(true); 
					}
				});
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton ajoutButton = new JButton("Ajouter un arbitre");
				ajoutButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							AjoutArbitre ajoutArbitre = new AjoutArbitre();
							ajoutArbitre.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
							ajoutArbitre.setVisible(true);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				});
				buttonPane.add(ajoutButton);
				getRootPane().setDefaultButton(ajoutButton);
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

}
