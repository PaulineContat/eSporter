package ihm;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import connexion.ConnexionBDD;
import modele.Gestionnaire;
import modele.Responsable;
import vues.VueAdminJoueurs;

import javax.swing.JSplitPane;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class AccueilGestionnaire extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Connection conx;

	/**
	 * Create the dialog.
	 */
	public AccueilGestionnaire(Gestionnaire g) {
		this.conx = ConnexionBDD.connecting();
		
		this.setTitle("Profil Gestionnaire");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JSplitPane splitPane = new JSplitPane();
			contentPanel.add(splitPane, BorderLayout.CENTER);
			{
				JPanel panel = new JPanel();
				splitPane.setLeftComponent(panel);
				panel.setLayout(new GridLayout(0, 1, 0, 0));
				{
					JLabel lblPrenomGestionnaire = new JLabel("Prenom : " + g.getPrenom());
					lblPrenomGestionnaire.setFont(new Font("Tahoma", Font.PLAIN, 15));
					panel.add(lblPrenomGestionnaire);
				}
				{
					JLabel lblNomGestionnaire = new JLabel("Nom : " + g.getNom());
					lblNomGestionnaire.setFont(new Font("Tahoma", Font.PLAIN, 15));
					panel.add(lblNomGestionnaire);
				}
			}
			{
				JSplitPane splitPane_1 = new JSplitPane();
				splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
				splitPane.setRightComponent(splitPane_1);
				{
					JLabel lblChoix = new JLabel("Choisissez une catégorie :");
					lblChoix.setFont(new Font("Tahoma", Font.PLAIN, 15));
					lblChoix.setHorizontalAlignment(SwingConstants.CENTER);
					splitPane_1.setLeftComponent(lblChoix);
				}
				{
					JPanel panel = new JPanel();
					splitPane_1.setRightComponent(panel);
					panel.setLayout(new GridLayout(0, 4, 0, 0));
					{
						JButton btnJeux = new JButton("Jeux");
						btnJeux.setFont(new Font("Tahoma", Font.PLAIN, 15));
						btnJeux.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								try {
									AdminJeux adminJeux = new AdminJeux();
									adminJeux.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
									adminJeux.setVisible(true);
								} catch (SQLException e1) {
									
									e1.printStackTrace();
								}
							}
						});
						btnJeux.setFont(new Font("Tahoma", Font.PLAIN, 15));
						panel.add(btnJeux);
					}
					{
						JButton btnTournois = new JButton("Tournois");
						btnTournois.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								try {
									AdminTournois adminTournois = new AdminTournois();
									adminTournois.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
									adminTournois.setVisible(true);
								} catch (SQLException e1) {
									
									e1.printStackTrace();
								}
							}
						});
						btnTournois.setFont(new Font("Tahoma", Font.PLAIN, 15));
						panel.add(btnTournois);
					}
					{
						JButton btnEquipes = new JButton("Equipes");
						btnEquipes.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								try {
									AdminEquipes adminEquipes = new AdminEquipes();
									adminEquipes.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
									adminEquipes.setVisible(true);
								} catch (SQLException e1) {
									
									e1.printStackTrace();
								}
							}
						});
						btnEquipes.setFont(new Font("Tahoma", Font.PLAIN, 15));
						panel.add(btnEquipes);
					}
					{
						JButton btnEcuries = new JButton("Ecuries");
						btnEcuries.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								try {
									AdminEcuries adminEcuries = new AdminEcuries();
									adminEcuries.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
									adminEcuries.setVisible(true);
								} catch (SQLException e1) {
									
									e1.printStackTrace();
								}
							}
						});
						btnEcuries.setFont(new Font("Tahoma", Font.PLAIN, 15));
						panel.add(btnEcuries);
					}
					{
						JButton btnJoueurs = new JButton("Joueurs");
						btnJoueurs.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								try {
									VueAdminJoueurs adminJoueurs = new VueAdminJoueurs();
									adminJoueurs.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
									adminJoueurs.setVisible(true);
								} catch (SQLException e1) {
									
									e1.printStackTrace();
								}
							}
						});
						btnJoueurs.setFont(new Font("Tahoma", Font.PLAIN, 15));
						panel.add(btnJoueurs);
					}
					{
						JButton btnJoueurs = new JButton("Arbitres");
						btnJoueurs.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								try {
									AdminArbitres adminArbitres = new AdminArbitres();
									adminArbitres.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
									adminArbitres.setVisible(true);
								} catch (SQLException e1) {
									
									e1.printStackTrace();
								}
							}
						});
						btnJoueurs.setFont(new Font("Tahoma", Font.PLAIN, 15));
						panel.add(btnJoueurs);
					}
					{
						JButton btnResponsables = new JButton("Responsables");
						btnResponsables.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								try {
									AdminResponsables adminResponsables = new AdminResponsables();
									adminResponsables.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
									adminResponsables.setVisible(true);
								} catch (SQLException e1) {
									
									e1.printStackTrace();
								}
							}
						});
						btnResponsables.setFont(new Font("Tahoma", Font.PLAIN, 15));
						panel.add(btnResponsables);
					}
					{
						JButton btnResultats = new JButton("Classement général");
						btnResultats.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								ConsulterClassement classement;
								try {
									classement = new ConsulterClassement();
									classement.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
									classement.setVisible(true);
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
							}
						});
						btnResultats.setFont(new Font("Tahoma", Font.PLAIN, 15));
						panel.add(btnResultats);
					}
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
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
	
	public static void main(String[] args) throws SQLException {
		ConnexionBDD.connecting();
		Gestionnaire g = ConnexionBDD.getGestionnaires().get("alex.terrieur");
		AccueilGestionnaire a = new AccueilGestionnaire(g);
		a.setVisible(true);
	}
}
