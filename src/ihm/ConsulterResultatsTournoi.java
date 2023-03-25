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
import modele.Equipe;
import modele.Rencontre;

import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ConsulterResultatsTournoi extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Connection connx = ConnexionBDD.connecting();
	private JTable table;
	private List<Rencontre> rencontres;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ConsulterResultatsTournoi dialog = new ConsulterResultatsTournoi();
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
	public ConsulterResultatsTournoi() throws SQLException {
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
				JLabel lblBienvenue = new JLabel("Résultats du tournoi");
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
							"Equipe 1", "Equipe 2", "Tournoi", "Heure", "Arbitre"
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
				
				this.rencontres = ConnexionBDD.getRencontres();
				for(Rencontre r : this.rencontres) {
							model.addRow(new Object[]{r.getEquipe1().getNom(), r.getEquipe2().getNom(), r.getTournoi().getNom(), r.getHeure(), r.getArbitre().toString()});
				}
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
