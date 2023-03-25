package vues;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import controleurs.ControleurAdminArbitres;
import modele.Arbitre;
import modeles.ModeleAdminArbitres;

public class VueAdminArbitres extends JDialog {
	
	private ModeleAdminArbitres modele;
	private ControleurAdminArbitres controleur;
	
	private JPanel contentPanel;
	private Container contentPane;
	private JTable table;
	private DefaultTableModel tableModel;

	public VueAdminArbitres() throws SQLException {
		
		this.modele = new ModeleAdminArbitres();
		this.controleur = new ControleurAdminArbitres(this, this.modele);
		this.contentPanel = new JPanel();
		this.contentPane = this.getContentPane();
		this.tableModel = new DefaultTableModel(
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
			};
		
		this.setTitle("Administrer les arbitres");
		setBounds(100, 100, 450, 300);
		this.contentPane.setLayout(new BorderLayout(0, 0));
		{
			JSplitPane splitPane = new JSplitPane();
			splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
			this.contentPane.add(splitPane);
			{
				JLabel lblBienvenue = new JLabel("Administrer les arbitres");
				lblBienvenue.setHorizontalAlignment(SwingConstants.CENTER);
				splitPane.setLeftComponent(lblBienvenue);
			}
			JScrollPane scrollPane = new JScrollPane();
			splitPane.setRightComponent(scrollPane);
			{
				this.table = new JTable();
				this.table.addMouseListener(this.controleur);
				this.table.setModel(this.tableModel);
				
				this.afficherArbitre();
				
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			this.contentPane.add(buttonPane, BorderLayout.SOUTH);
			{
				JButton ajoutButton = new JButton("Ajouter un arbitre");
				ajoutButton.addMouseListener(this.controleur);
				buttonPane.add(ajoutButton);
				getRootPane().setDefaultButton(ajoutButton);
			}
			{
				JButton cancelButton = new JButton("Fermer");
				cancelButton.addMouseListener(this.controleur);
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public void afficherArbitre() {
		this.modele.updateData();
		int n = table.getRowCount();
		for (int i=n-1 ; i>=0 ; --i) {
			this.tableModel.removeRow(i);
		}
		for(Arbitre a : this.modele.getArbitres()) {
			this.tableModel.addRow(new Object[]{a.getCompte().getIdentifiant(), a.getNom(), a.getPrenom(), a.getTel()});
		}
		
	}
	
	private int getSelectedIndex() {
		return this.table.getSelectedRow();
	}
	
	public Arbitre getSelectedArbitre() {
		return modele.getArbitreOfIndex(this.getSelectedIndex());
	}

	
}
