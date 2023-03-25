package ihm;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modele.Ecurie;
import modele.Equipe;
import modele.Jeu;

import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

import connexion.ConnexionBDD;

import javax.swing.JScrollPane;
import java.awt.GridLayout;

public class AccueilEcurie extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private Connection conx;

	/**
	 * Create the dialog.
	 * @throws SQLException 
	 */
	public AccueilEcurie(Ecurie ecurie) throws SQLException {
		
		this.conx = ConnexionBDD.connecting();
		
		Map<String, Equipe> test = ConnexionBDD.getEquipesFromEcurie(ecurie);
		for (Equipe equipe : test.values()) {
			System.out.println("Equipe " + equipe.getNom());
			ecurie.addEquipe(equipe);
		}
		
		this.setTitle("Profil Ecurie");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JSplitPane splitPane = new JSplitPane();
			contentPanel.add(splitPane);
			{
				JPanel panel = new JPanel();
				splitPane.setLeftComponent(panel);
				panel.setLayout(new GridLayout(0, 1, 0, 0));
				{
					JLabel lblNomEcurie = new JLabel("Nom : " + ecurie.getNom());
					panel.add(lblNomEcurie);
				}
				{
					JButton btnAjoutEquipe = new JButton("Ajouter Equipe");
					btnAjoutEquipe.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							try {
								AjoutEquipe ajoutEquipe = new AjoutEquipe(ecurie);
								ajoutEquipe.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
								ajoutEquipe.setVisible(true);
							} catch (SQLException e1) {
								
								e1.printStackTrace();
							}
						}
					});
					panel.add(btnAjoutEquipe);
				}
			}
			{
				JSplitPane splitPane_1 = new JSplitPane();
				splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
				splitPane.setRightComponent(splitPane_1);
				{
					JLabel lblListeEquipes = new JLabel("Equipes :");
					splitPane_1.setLeftComponent(lblListeEquipes);
				}
				{
					JScrollPane scrollPane = new JScrollPane();
					splitPane_1.setRightComponent(scrollPane);
					{
						table = new JTable();
						table.addMouseListener(new MouseAdapter() {
							@Override
							public void mousePressed(MouseEvent e) {
								int row = table.getSelectedRow();
					            Equipe equipe = ecurie.getEquipes().get(row);
					            AccueilEquipe a;
								try {
									a = new AccueilEquipe(equipe);
									a.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
									a.setVisible(true);
								} catch (SQLException e1) {
									
									e1.printStackTrace();
								}
								
							}
						});
						table.setModel(new DefaultTableModel(
							new Object[][] {
							},
							new String[] {
								"Identifiant", "Nom", "Jeu"
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
						for (Equipe equipe : ecurie.getEquipes()) {
							model.addRow(new Object[]{equipe.getCompte().getIdentifiant(), equipe.getNom(), equipe.getJeu().getNom()});
						}
						scrollPane.setViewportView(table);
					}
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton BtnFermer = new JButton("Fermer");
				BtnFermer.setActionCommand("Close");
				BtnFermer.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(BtnFermer);
				getRootPane().setDefaultButton(BtnFermer);
			}
		}
	}
	
	
	
	public static void main(String[] args) throws SQLException {
		Connection conx = ConnexionBDD.connecting();
		Ecurie ecurie = ConnexionBDD.getEcurieFromId("ecurie1");
		
		AccueilEcurie e = new AccueilEcurie(ecurie);
		e.setVisible(true);
	}
}
