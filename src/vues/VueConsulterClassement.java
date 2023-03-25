package vues;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Connection;

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

import controleurs.ControleurConsulterClassement;
import modele.Equipe;
import modeles.ModeleConsulterClassement;

public class VueConsulterClassement extends JDialog {

	private ModeleConsulterClassement modele;
	private ControleurConsulterClassement controleur;
	
	private final JPanel contentPanel = new JPanel();
	private JTable table;

	@SuppressWarnings("serial")
	public VueConsulterClassement() {

		this.modele = new ModeleConsulterClassement();
		this.controleur = new ControleurConsulterClassement(this);

		this.setTitle("Classement");
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
				JLabel lblBienvenue = new JLabel("Classement de la saison");
				lblBienvenue.setHorizontalAlignment(SwingConstants.CENTER);
				splitPane.setLeftComponent(lblBienvenue);
			}
			JScrollPane scrollPane = new JScrollPane();
			splitPane.setRightComponent(scrollPane);
			{
				table = new JTable();
				setModel();
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				afficherClassement(model);
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Fermer");
				cancelButton.addActionListener(this.controleur);;
				buttonPane.add(cancelButton);
			}
		}
	}

	@SuppressWarnings("serial")
	private void setModel() {
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Place", "Equipe", "Nombre de points"
				}
				) {
			Class[] columnTypes = new Class[] {
					Integer.class, String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
	}

	private void afficherClassement(DefaultTableModel model) {
		int place = 0;
		for(Equipe e : this.modele.getEquipes()) {
			place ++;
			model.addRow(new Object[]{place, e.getNom(), e.calculPointsTotaux(this.modele.getTournoisFromEquipe(e))});
		}
	}
}
