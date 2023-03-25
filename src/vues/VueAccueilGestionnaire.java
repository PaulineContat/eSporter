package vues;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controleurs.ControleurAccueilGestionnaire;
import modeles.ModeleAccueilGestionnaire;

import javax.swing.JSplitPane;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class VueAccueilGestionnaire extends JDialog {

	private ModeleAccueilGestionnaire modele;
	private ControleurAccueilGestionnaire controleur;

	private final JPanel contentPanel;

	public VueAccueilGestionnaire(String identifiantGestionnaire) {

		this.contentPanel = new JPanel();
		this.modele = new ModeleAccueilGestionnaire(identifiantGestionnaire);
		this.controleur = new ControleurAccueilGestionnaire(this);

		this.setTitle("Profil Gestionnaire");
		setBounds(100, 100, 850, 300);
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
					JLabel lblPrenomGestionnaire = new JLabel("Prenom : " + this.modele.getPrenom());
					lblPrenomGestionnaire.setFont(new Font("Tahoma", Font.PLAIN, 15));
					panel.add(lblPrenomGestionnaire);
				}
				{
					JLabel lblNomGestionnaire = new JLabel("Nom : " + this.modele.getNom());
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
						btnJeux.addActionListener(this.controleur);
						btnJeux.setFont(new Font("Tahoma", Font.PLAIN, 15));
						panel.add(btnJeux);
					}
					{
						JButton btnTournois = new JButton("Tournois");
						btnTournois.addActionListener(this.controleur);
						btnTournois.setFont(new Font("Tahoma", Font.PLAIN, 15));
						panel.add(btnTournois);
					}
					{
						JButton btnEquipes = new JButton("Equipes");
						btnEquipes.addActionListener(this.controleur);
						btnEquipes.setFont(new Font("Tahoma", Font.PLAIN, 15));
						panel.add(btnEquipes);
					}
					{
						JButton btnEcuries = new JButton("Ecuries");
						btnEcuries.addActionListener(this.controleur);
						btnEcuries.setFont(new Font("Tahoma", Font.PLAIN, 15));
						panel.add(btnEcuries);
					}
					{
						JButton btnJoueurs = new JButton("Joueurs");
						btnJoueurs.addActionListener(this.controleur);
						btnJoueurs.setFont(new Font("Tahoma", Font.PLAIN, 15));
						panel.add(btnJoueurs);
					}
					{
						JButton btnJoueurs = new JButton("Arbitres");
						btnJoueurs.addActionListener(this.controleur);
						btnJoueurs.setFont(new Font("Tahoma", Font.PLAIN, 15));
						panel.add(btnJoueurs);
					}
					{
						JButton btnResponsables = new JButton("Responsables");
						btnResponsables.addActionListener(this.controleur);
						btnResponsables.setFont(new Font("Tahoma", Font.PLAIN, 15));
						panel.add(btnResponsables);
					}
					{
						JButton btnResultats = new JButton("Classement général");
						btnResultats.addActionListener(this.controleur);		
						btnResultats.setFont(new Font("Tahoma", Font.PLAIN, 15));
						panel.add(btnResultats);
					}
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			contentPanel.add(buttonPane, BorderLayout.NORTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			{
				JButton btnSeDconnecter = new JButton("Se Déconnecter");
				buttonPane.add(btnSeDconnecter);
				btnSeDconnecter.addActionListener(this.controleur);
			}
		}
	}
}