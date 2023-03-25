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
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import connexion.ConnexionBDD;
import modele.Ecurie;
import modele.Equipe;
import modele.Jeu;

import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

public class AdminEquipes extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private Connection connx = ConnexionBDD.connecting();
	private Map<String, Equipe> equipes;

	public static void main(String[] args) {
		try {
			AdminEquipes dialog = new AdminEquipes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public AdminEquipes() throws SQLException {
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
				JLabel lblBienvenue = new JLabel("Administrer les équipes");
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
						"Nom", "Jeu", "Ecurie"
					}
				) {
					Class[] columnTypes = new Class[] {
						String.class, String.class, String.class
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
				});
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				
				this.equipes = ConnexionBDD.getEquipes();
				for(Equipe equipe : equipes.values()) {
					model.addRow(new Object[]{equipe.getNom(), equipe.getJeu().getNom(), equipe.getEcurie().getNom()});
				}
				scrollPane.setViewportView(table);
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int row = table.getSelectedRow();
						table.getValueAt(row, 1);
						SupprModifEquipe jd = new SupprModifEquipe((Equipe) equipes.values().toArray()[row]);
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
				JButton ajouterButton = new JButton("Ajouter une équipe");
				ajouterButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							AjoutEquipe ajoutEquipe = new AjoutEquipe();
							ajoutEquipe.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
							ajoutEquipe.setVisible(true);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				});
				buttonPane.add(ajouterButton);
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
