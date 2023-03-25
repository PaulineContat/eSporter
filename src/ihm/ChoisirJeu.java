package ihm;
import java.sql.Connection;

import javax.swing.JDialog;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;

import connexion.ConnexionBDD;

public class ChoisirJeu extends JDialog {
		Connection connx = ConnexionBDD.connecting();	
		private JTable table;

	public ChoisirJeu() {
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"", "", "", ""
			}
		));
		panel.add(table);
	}

	public static void main(String[] args) {	
	}

}
