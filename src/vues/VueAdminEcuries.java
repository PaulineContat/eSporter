package vues;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

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

import controleurs.ControleurAdminEcuries;
import modele.Ecurie;
import modeles.ModeleAdminEcuries;

@SuppressWarnings("serial")
public class VueAdminEcuries extends JDialog {

	private ControleurAdminEcuries controleur;
	private ModeleAdminEcuries modele;

	private final JPanel contentPanel;
	private JTable table;
	private DefaultTableModel tableModel;

	public VueAdminEcuries() {

		this.modele = new ModeleAdminEcuries();
		this.controleur = new ControleurAdminEcuries(this, this.modele);
		this.contentPanel = new JPanel();
		this.tableModel = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Identifiant", "Nom", "Statut"
				}
				) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
					String.class, String.class, String.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
					false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		this.setTitle("Administrer les écuries");
		
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
				JLabel lblBienvenue = new JLabel("Administrer les écuries");
				lblBienvenue.setHorizontalAlignment(SwingConstants.CENTER);
				splitPane.setLeftComponent(lblBienvenue);
			}
			JScrollPane scrollPane = new JScrollPane();
			splitPane.setRightComponent(scrollPane);
			{
				table = new JTable();
				table.addMouseListener(this.controleur);
				table.setModel(this.tableModel);

				this.afficherEcuries();
				
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton ajouterButton = new JButton("Ajouter une écurie");
				ajouterButton.addMouseListener(this.controleur);
				buttonPane.add(ajouterButton);
				getRootPane().setDefaultButton(ajouterButton);
			}
			{
				JButton cancelButton = new JButton("Fermer");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addMouseListener(this.controleur);
				buttonPane.add(cancelButton);
			}
		}
	}

	public void afficherEcuries() {
		this.modele.updateData();
		int n = table.getRowCount();
		for (int i=n-1 ; i>=0 ; --i) {
			this.tableModel.removeRow(i);
		}
		for(Ecurie ecurie : this.modele.getEcuries()) {	
			this.tableModel.addRow(new Object[]{ecurie.getCompte().getIdentifiant(), ecurie.getNom(), ecurie.getStatut()});
		}
	}
}
