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

import controleurs.ControleurAdminJeux;
import modele.Jeu;
import modeles.ModeleAdminJeux;

@SuppressWarnings("serial")
public class VueAdminJeux extends JDialog {

		private ControleurAdminJeux controleur;
		private ModeleAdminJeux modele;
		
		private final JPanel contentPanel;
		private JTable table;
		private DefaultTableModel tableModel;

		public VueAdminJeux() {

			this.modele = new ModeleAdminJeux();
			this.controleur = new ControleurAdminJeux(this, this.modele);
			this.contentPanel = new JPanel();
			this.tableModel = new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
						"Identifiant", "Nom", "Nombre joueurs"
					}
				) {
					Class[] columnTypes = new Class[] {
						String.class, String.class, Integer.class
					};
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
			
			this.setTitle("Administrer les jeux");
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
					table.addMouseListener(this.controleur);
					table.setModel(this.tableModel);
					
					this.afficherJeux();
					
					scrollPane.setViewportView(table);
				}
			}
			{
				JPanel buttonPane = new JPanel();
				buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
				getContentPane().add(buttonPane, BorderLayout.SOUTH);
				{
					JButton ajouterButton = new JButton("Ajouter un jeu");
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
		
		public void afficherJeux() {
			this.modele.updateData();
			int n = table.getRowCount();
			for (int i=n-1 ; i>=0 ; --i) {
			    this.tableModel.removeRow(i);
			}
			for(Jeu jeu : this.modele.getJeux()) {	
					this.tableModel.addRow(new Object[]{jeu.getIdJeu(), jeu.getNom(), jeu.getNbJoueurs()});
			}
		}
}
