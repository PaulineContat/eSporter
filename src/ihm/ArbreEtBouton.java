package ihm;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import connexion.ConnexionBDD;
import modele.Arbitre;
import modele.Rencontre;
import modele.Tournoi;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionEvent;

public class ArbreEtBouton extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private ConnexionBDD conxBDD;
	private Connection conx;

	List<Arbitre> arbitres=new ArrayList<>();
	public ArbreEtBouton(Tournoi t) {
		this.conx = ConnexionBDD.connecting();
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton Arbre = new JButton("Arbre");
				Arbre.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						ArbreTournoi at;
						
						try {
							at = new ArbreTournoi(t);
							at.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
							at.setVisible(true);
						} catch (SQLException e1) {
						
							e1.printStackTrace();
						}
}
				});
				buttonPane.add(Arbre);
			}
			{
				JButton btnPhasePro = new JButton("Phase suivante");
				btnPhasePro.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					

						try {
							for (Arbitre a : ConnexionBDD.getArbitres().values()) {
								arbitres.add(a);
								t.generateRencontres(arbitres);
								for (Rencontre r : t.getRencontres()) {
									ConnexionBDD.enregistrerRencontre(r);
								}
							}
						} catch (SQLException e1) {
						
							e1.printStackTrace();
						}
						
						
					}
				});
				buttonPane.add(btnPhasePro);
			}

		}
	}



	public static void main(String[] args) throws SQLException {
		Connection conx;
		
		List<Arbitre> arbitres=new ArrayList<>();
		conx = ConnexionBDD.connecting();
		Tournoi t = ConnexionBDD.getTournoiFromId(1);
		try {

			ArbreEtBouton dialog = new ArbreEtBouton(t);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}