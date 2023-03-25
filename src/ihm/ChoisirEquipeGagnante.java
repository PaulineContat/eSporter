package ihm;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import connexion.ConnexionBDD;
import modele.Rencontre;
import javax.swing.JComboBox;

public class ChoisirEquipeGagnante extends JDialog {
	
	JComboBox comboEquipes;
	Connection connx = ConnexionBDD.connecting();
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public ChoisirEquipeGagnante(Rencontre r) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			String[] equipes = {r.getEquipe1().getNom(), r.getEquipe2().getNom()};
			comboEquipes = new JComboBox(equipes);	
			comboEquipes.setSelectedIndex(-1);
			contentPanel.add(comboEquipes);
		}

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton validerButton = new JButton("Valider");
				validerButton.setActionCommand("OK");
				buttonPane.add(validerButton);
				getRootPane().setDefaultButton(validerButton);
				validerButton.addActionListener(new ActionListener() {		
					@Override
					public void actionPerformed(ActionEvent e) {
						if(comboEquipes.getSelectedIndex()!=-1) {
							ConnexionBDD.enregistrerResultatRencontre(r, comboEquipes.getSelectedIndex()+1);	
							dispose();
						}	
						
					}
				});
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
			}
		}
	}

}
