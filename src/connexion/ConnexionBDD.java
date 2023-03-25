package connexion;
import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import modele.Arbitre;
import modele.Compte;
import modele.Ecurie;
import modele.Ecurie.Statut;
import modele.Equipe;
import modele.Gestionnaire;
import modele.Jeu;
import modele.Joueur;
import modele.Rencontre;
import modele.Responsable;
import modele.Tournoi;
import modele.Compte.Type;
import modele.Tournoi.Categorie;


public class ConnexionBDD {

	private static PreparedStatement ps;
	private static Connection connx;

	private ConnexionBDD() {}


	/**
	 * Si la connection n'est pas encore établie, établie la connection avec les identifiants de Pauline
	 * Renvoie la connection établie
	 * @return Connection une connection (de type Connection) à la base de donnée
	 */
	public static Connection connecting() {

		if (connx == null) {
			try {
				connx = DriverManager.getConnection("jdbc:oracle:thin:@telline.univ-tlse3.fr:1521:ETUPRE","CNP3364A","$iutinfo");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return connx;
	}

	/**
	 * Supprime un jeu de la base de donnée à partir de son nom
	 * Le jeu à supprimer doit donc avoir un nom défini
	 * @param jeu un jeu à supprimer de la base de donnée
	 */
	public static void supprimerJeu(Jeu jeu) {

		try {
			Statement st = connx.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM esporter_jeu WHERE id_jeu = '" + jeu.getIdJeu() + "'");
			if (rs.next()) {
				ps = connx.prepareStatement("delete from esporter_jeu where nom = ?");
				ps.setString(1, jeu.getNom());
				ps.executeUpdate();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Supprime un joueur de la base de donnée à partir de son id
	 * Le joueur à supprimer doit donc avoir un id défini
	 * @param joueur un joueur à supprimer de la base de donnée
	 */
	public static void supprimerJoueur(Joueur joueur) {

		try {
			Statement st = connx.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM esporter_joueur WHERE id_joueur = '" + joueur.getCompte().getIdentifiant()+"'");
			if (rs.next()) {
				ps = connx.prepareStatement("delete from esporter_joueur where id_joueur = ?");
				ps.setString(1, joueur.getCompte().getIdentifiant());
				ps.executeUpdate();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Supprime un arbitre de la base de donnée à partir de son id
	 * L'arbitre à supprimer doit donc avoir un id défini
	 * @param arbitre un arbitre à supprimer de la base de donnée
	 */
	public static void supprimerArbitre(Arbitre arbitre) {

		try {
			Statement st = connx.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM esporter_arbitre WHERE id_arbitre = '" + arbitre.getCompte().getIdentifiant()+"'");
			if (rs.next()) {
				ps = connx.prepareStatement("delete from esporter_arbitre where id_arbitre = ?");
				ps.setString(1, arbitre.getCompte().getIdentifiant());
				ps.executeUpdate();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Supprime un responsable de la base de donnée à partir de son id
	 * Le responsable à supprimer doit donc avoir un id défini
	 * @param responsable un responsable à supprimer de la base de donnée
	 */
	public static void supprimerResponsable(Responsable responsable) {

		try {
			Statement st = connx.createStatement();
			st = connx.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM esporter_responsable WHERE id_responsable = '" + responsable.getCompte().getIdentifiant()+"'");
			if (rs.next()) {
				ps = connx.prepareStatement("delete from esporter_responsable where id_responsable = ?");
				ps.setString(1, responsable.getCompte().getIdentifiant());
				ps.executeUpdate();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Supprime une équipe de la base de donnée à partir de son id
	 * L'équipe à supprimer doit donc avoir un id défini
	 * @param équipe une équipe à supprimer de la base de donnée
	 */
	public static void supprimerEquipe(Equipe equipe) {

		try {
			Statement st = connx.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM esporter_equipe WHERE id_equipe = '" + equipe.getCompte().getIdentifiant()+"'");
			if (rs.next()) {
				ps = connx.prepareStatement("delete from esporter_equipe where id_equipe = ?");
				ps.setString(1, equipe.getCompte().getIdentifiant());
				ps.executeUpdate();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Supprime une écurie de la base de donnée à partir de son id
	 * L'écurie à supprimer doit donc avoir un id défini
	 * @param écurie une écurie à supprimer de la base de donnée
	 */
	public static void supprimerEcurie(Ecurie ecurie) {

		try {
			Statement st = connx.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM esporter_ecurie WHERE id_ecurie = '" + ecurie.getCompte().getIdentifiant()+"'");
			if (rs.next()) {
				ps = connx.prepareStatement("delete from esporter_ecurie where id_ecurie = ?");
				ps.setString(1, ecurie.getCompte().getIdentifiant());
				ps.executeUpdate();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Supprime une rencontre de la base de donnée à partir de son id
	 * La rencontre à supprimer doit donc avoir un id défini
	 * @param rencontre une rencontre à supprimer de la base de donnée
	 */
	public static void supprimerRencontre(Rencontre rencontre) {

		try {
			Statement st = connx.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM esporter_rencontre WHERE id_equipe1 = '" + rencontre.getEquipe1() +"' AND id_equipe2 = '" + rencontre.getEquipe2() +"' AND id_tournoi = '" + rencontre.getTournoi().getIdTournoi() + "'");
			if(rs.next()) {
				ps = connx.prepareStatement("delete from esporter_rencontre where id_equipe1 = ? AND id_equipe2 = ? AND id_tournoi = ?");
				ps.setString(1, rencontre.getEquipe1().getCompte().getIdentifiant());
				ps.setString(2, rencontre.getEquipe2().getCompte().getIdentifiant());
				ps.setString(3, rencontre.getTournoi().getIdTournoi());
				ps.executeUpdate();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Supprime un tournoi de la base de donnée à partir de son id <br>
	 * Le tournoi à supprimer doit donc avoir un id défini
	 * @param tournoi un tournoi à supprimer de la base de donnée
	 */
	public static void supprimerTournoi(Tournoi tournoi) {

		try {
			Statement st = connx.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM esporter_tournoi WHERE id_tournoi = '" + tournoi.getIdTournoi() + "'");
			if(rs.next()) {
				ps = connx.prepareStatement("delete from esporter_tournoi where id_tournoi = ?");
				ps.setString(1, tournoi.getIdTournoi());
				ps.executeUpdate();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Modifie le nom et le nombre de joueurs d'un jeu à partir de son ancien id dans la base de donnée
	 * @param j le jeu à modifier dans la base de donnée
	 */
	public static void modifierJeu(Jeu jeu) {		
		try {
			Statement st = connx.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM esporter_jeu WHERE id_jeu = '" + jeu.getIdJeu() + "'");
			
			if (rs.next()) {
				st.executeUpdate("UPDATE esporter_jeu "
						+ "SET nom = '" + jeu.getNom() + "', "
						+ "nb_joueurs = '" + jeu.getNbJoueurs() + "' "
						+ "WHERE id_jeu = '" + jeu.getIdJeu() + "'");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Modifie le nom, la date de début, la date de fin, la catégorie, la date limite d'inscription, l'heure, le jeu et le responsable d'un tournoi à partir de son ancien nom dans la base de donnée
	 * @param t le tournoi à modifier dans la base de donnée
	 * @param ancienNom l'ancien nom du tournoi à modifier
	 */
	public static void modifierTournoi(Tournoi t) {
		try {
			Statement st = connx.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM esporter_tournoi WHERE id_tournoi = '" + t.getIdTournoi() + "'");
			if (rs.next()) {
				st.executeUpdate("UPDATE esporter_tournoi "
						+ "SET nom = '" + t.getNom() + "', "
						+ "date_debut = '" + t.getDateDebut() + "', "
						+ "date_fin = '" + t.getDateFin() + "', "
						+ "categorie = '" + t.getCategorie().name() + "', "
						+ "date_limite_inscription = '" + t.getDateLimiteInscription() + "', "
						+ "heure = '" + t.getHeure() + "', "
						+ "jeu = '" + t.getJeu().getNom() + "', "
						+ "id_responsable = '" + t.getResponsable().getCompte().getIdentifiant() + "' "
						+ "WHERE id_tournoi = '" + t.getIdTournoi() + "'");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Modifie l'heure et l'arbitre d'une rencontre à partir de ses deux équipes et de son tournoi dans la base de donnée
	 * @param r la rencontre à modifier dans la base de donnée
	 */
	public static void modifierRencontre(Rencontre r) {

		try {
			Statement st = connx.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM esporter_rencontre WHERE id_equipe1 = '" + r.getEquipe1() +"' AND id_equipe2 = '" + r.getEquipe2() +"' AND id_tournoi = '" + r.getTournoi().getIdTournoi() + "'");
			if (rs.next()) {
				st.executeUpdate("UPDATE esporter_rencontre "
						+ "SET heure = '" + r.getHeure() + "',"
						+ "id_arbitre = '" + r.getArbitre().getCompte().getIdentifiant() + "' "
						+ "WHERE id_equipe1 = '" + r.getEquipe1().getCompte().getIdentifiant()+"' "
						+ "AND id_equipe2 = '" + r.getEquipe2().getCompte().getIdentifiant() + "' "
						+ "AND id_tournoi = '" + r.getTournoi().getIdTournoi() + "'");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Modifier le nom d'une équipe à partir de son ancien nom dans la base de donnée
	 * @param e l'équipe à modifier dans la base de donnée
	 */
	public static void modifierEquipe(Equipe e) {

		try {
			Statement st = connx.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM esporter_equipe WHERE id_equipe = '" + e.getCompte().getIdentifiant()+"'");
			if (rs.next()) {
				st.executeUpdate("UPDATE esporter_equipe "
						+ "SET nom = '" + e.getNom() + "' "
						+ "WHERE id_equipe = '" + e.getCompte().getIdentifiant() + "'");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	/**
	 * Modifier le nom, le prénom, le tel et le pseudo d'un joueur à partir de son id dans la base de donnée
	 * @param j le joueur à modifier dans la base de donnée
	 */
	public static void modifierJoueur(Joueur j) {

		try {
			Statement st = connx.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM esporter_joueur WHERE id_joueur = '" + j.getCompte().getIdentifiant()+"'");
			if (rs.next()) {
				st.executeUpdate("UPDATE esporter_joueur "
						+ "SET nom = '" + j.getNom() + "', "
						+ "prenom = '" + j.getPrenom() + "', "
						+ "tel = '" + j.getTel() + "', "
						+ "pseudo = '" + j.getPseudo() + "' "
						+ "WHERE id_joueur = '" + j.getCompte().getIdentifiant() + "'");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Modifier le nom, le prénom et le tel d'un arbitre à partir de son id dans la base de donnée
	 * @param a l'arbitre à modifier dans la base de donnée
	 */
	public static void modifierArbitre(Arbitre a) {

		try {
			Statement st = connx.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM esporter_arbitre WHERE id_arbitre = '" + a.getCompte().getIdentifiant()+"'");
			if (rs.next()) {
				st.executeUpdate("UPDATE esporter_arbitre "
						+ "SET nom = '" + a.getNom() + "', "
						+ "prenom = '" + a.getPrenom() + "', "
						+ "tel = '" + a.getTel() + "' "
						+ "WHERE id_arbitre = '" + a.getCompte().getIdentifiant() + "'");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Modifier le nom, le prénom et le tel d'un responsable à partir de son id dans la base de donnée
	 * @param r le responsable à modifier dans la base de donnée
	 */
	public static void modifierResponsable(Responsable r) {
		Statement st;

		try {
			st = connx.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM esporter_responsable WHERE id_responsable = '" + r.getCompte().getIdentifiant()+"'");
			if (rs.next()) {
				st.executeUpdate("UPDATE esporter_responsable "
						+ "SET nom = '" + r.getNom() + "', "
						+ "prenom = '" + r.getPrenom() + "', "
						+ "tel = '" + r.getTel() + "' "
						+ "WHERE id_responsable = '" + r.getCompte().getIdentifiant() + "'");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Modifier le nom et le statut d'une écurie à partir de son id dans la base de donnée
	 * @param e l'écurie à modifier dans la base de donnée
	 */
	public static void modifierEcurie(Ecurie e) {

		try {
			Statement st = connx.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM esporter_ecurie WHERE id_ecurie = '" + e.getCompte().getIdentifiant()+"'");
			if (rs.next()) {
				st.executeUpdate("UPDATE esporter_ecurie "
						+ "SET nom = '" + e.getNom() + "', "
						+ "statut = '" + e.getStatut().name() + "' "
						+ "WHERE id_ecurie = '" + e.getCompte().getIdentifiant() + "'");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
	}

	/**
	 * Enregistre un jeu dans la base de donnée
	 * @param jeu le jeu à enregistrer
	 */
	public static void enregistrerJeu(Jeu jeu) {

		try {
			ps = connx.prepareStatement("insert into esporter_jeu values (?,?,?)");
			ps.setString(1, jeu.getIdJeu());
			ps.setString(2, jeu.getNom());
			ps.setInt(3, jeu.getNbJoueurs());
			ps.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Enregistre un arbitre dans la base de donnée
	 * @param arbitre l'arbitre à enregistrer
	 */
	public static void enregistrerArbitre(Arbitre arbitre) {

		try {
			CallableStatement call=connx.prepareCall("{? = call AJOUTER_ARBITRE(?,?,?,?,?)}");
			call.registerOutParameter(1, Types.INTEGER);
			call.setString(2, arbitre.getCompte().getIdentifiant());
			call.setString(3, arbitre.getNom());
			call.setString(4, arbitre.getPrenom());
			call.setString(5, arbitre.getTel());
			call.setString(6, arbitre.getCompte().getMdp());
			call.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Enregistre un responsable dans la base de donnée
	 * @param responsable le responsable à enregistrer
	 */
	public static void enregistrerResponsable(Responsable responsable) {

		try {
			CallableStatement call=connx.prepareCall("{? = call ajouter_responsable(?,?,?,?,?)}");
			call.registerOutParameter(1, Types.INTEGER);
			call.setString(2, responsable.getCompte().getIdentifiant());
			call.setString(3, responsable.getNom());
			call.setString(4, responsable.getPrenom());
			call.setString(5, responsable.getTel());
			call.setString(6, responsable.getCompte().getMdp());
			call.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Enregistre un tounroi dans la base de donnée
	 * @param tournoi le tournoi à enregistrer
	 */
	public static void enregistrerTournoi(Tournoi tournoi) {

		try {
			ps = connx.prepareStatement("insert into esporter_tournoi values (?,?,?,?,?,?,?,?,?)");
			ps.setString(1, tournoi.getCategorie().name());
			ps.setString(2, tournoi.getNom());
			ps.setString(3, tournoi.getDateDebut());
			ps.setString(4, tournoi.getDateFin());
			ps.setString(5, tournoi.getDateLimiteInscription());
			ps.setString(6, tournoi.getHeure());
			ps.setString(7, tournoi.getJeu().getNom());
			ps.setString(8, tournoi.getResponsable().getCompte().getIdentifiant());
			ps.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Enregistre une écurie dans la base de donnée
	 * @param ecurie l'écurie à enregistrer
	 */
	public static void enregistrerEcurie(Ecurie ecurie) {

		try {
			CallableStatement call=connx.prepareCall("{? = call AJOUTER_ECURIE(?,?,?,?)}");
			call.registerOutParameter(1, Types.INTEGER);
			call.setString(2, ecurie.getCompte().getIdentifiant());
			call.setString(3, ecurie.getNom());
			call.setString(4, ecurie.getStatut().name());
			call.setString(5, ecurie.getCompte().getMdp());
			call.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Enregistre une équipe dans la base de donnée
	 * @param equipe l'équipe à enregistrer
	 */
	public static void enregistrerEquipe(Equipe equipe) {

		try {
			CallableStatement call=connx.prepareCall("{? = call ajouter_equipe(?,?,?,?,?)}");
			call.registerOutParameter(1, Types.INTEGER);
			call.setString(2, equipe.getCompte().getIdentifiant());
			call.setString(3, equipe.getNom());
			call.setString(4, equipe.getEcurie().getCompte().getIdentifiant());
			call.setString(5, equipe.getJeu().getIdJeu());
			call.setString(6, equipe.getCompte().getMdp());
			call.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Enregistre une rencontre dans la base de donnée <br>
	 * Il est indispensable que les deux équipes et le tournoi soient renseignés dans la rencontre avant de l'enregistrer
	 * @param rencontre la rencontre à enregistrer
	 */
	public static void enregistrerRencontre(Rencontre rencontre) {
		
		try {
			ps = connx.prepareStatement("insert into esporter_rencontre values (?,?,?,?,?,?)");
			ps.setString(1, rencontre.getEquipe1().getCompte().getIdentifiant());
			ps.setString(2, rencontre.getEquipe2().getCompte().getIdentifiant());
			ps.setString(3, rencontre.getTournoi().getIdTournoi());
			ps.setString(4, rencontre.getHeure());
			ps.setInt(5, 0);
			ps.setString(6, rencontre.getArbitre().getCompte().getIdentifiant());
			ps.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Enregistre un joueur dans la base de donnée
	 * @param joueur le joueur à enregistrer
	 */
	public static void enregistrerJoueur(Joueur joueur) {

		try {
			CallableStatement call=connx.prepareCall("{? = call ajouter_joueur(?,?,?,?,?,?,?)}");
			call.registerOutParameter(1, Types.INTEGER);
			call.setString(2, joueur.getCompte().getIdentifiant());
			call.setString(3, joueur.getNom());
			call.setString(4, joueur.getPrenom());
			call.setString(5, joueur.getTel());
			call.setString(6, joueur.getPseudo());
			call.setString(7, joueur.getEquipe().getCompte().getIdentifiant());
			call.setString(8, joueur.getCompte().getMdp());
			call.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Enregistre le résultat d'une rencontre à partir de ses équipes et de son tournoi
	 * @param rencontre la rencontre à modifier
	 * @param resultat le résultat à enregistrer
	 */
	public static void enregistrerResultatRencontre(Rencontre rencontre, int resultat) {

		try {
			connx.createStatement();
			ps = connx.prepareStatement("update esporter_rencontre set resultat = ? WHERE id_equipe1 = ? AND id_equipe2 = ? AND id_tournoi = ?");
			ps.setInt(1, resultat);
			ps.setString(2, rencontre.getEquipe1().getCompte().getIdentifiant());
			ps.setString(3, rencontre.getEquipe2().getCompte().getIdentifiant());
			ps.setString(4, rencontre.getTournoi().getIdTournoi());
			ps.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Inscrit une équipe à un tournoi
	 * @param e l'équipe à inscrire
	 * @param t le tournoi d'inscription
	 */
	public static void inscrireEquipeTournoi(Equipe e, Tournoi t) {

		try {
			ps = connx.prepareStatement("insert into esporter_inscription values (?,?)");
			ps.setString(1, e.getCompte().getIdentifiant());
			ps.setString(2, t.getIdTournoi());
			ps.executeUpdate();
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	

	/** Renvoie un responsable à partir de son id
	 * @param id l'id du responsable
	 * @return le responsable correspondant à cet id
	 */
	public static Responsable getReponsableFromId(String id) {
		
		try {
			Statement st = connx.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM esporter_responsable, esporter_compte WHERE id_responsable = '"+id+"' AND id_compte = '"+id+"'");
			if(rs.next()) {
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String tel = rs.getString("tel");
				String mdp = rs.getString("mdp");
				return new Responsable(id, nom, prenom, tel, mdp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return null;
	}	
	
	/**
	 * Renvoie un tournoi à partir de son id
	 * @param idTournoi l'id du tournoi
	 * @return le tournoi correspondant à cet id
	 */
	public static Tournoi getTournoiFromId(String idTournoi) {
		
		try {
			Statement st = connx.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM esporter_tournoi WHERE id_tournoi='"+idTournoi+"'");
			if(rs.next()){
				String nom = rs.getString("nom");
				Categorie categorie = Categorie.valueOf(rs.getString("categorie"));
				Jeu jeu = getJeuFromIdJeu(rs.getString("id_jeu"));
				String dateDebut = rs.getString("date_debut");
				String dateFin = rs.getString("date_fin");
				String dateLimiteInscription = rs.getString("date_limite_inscription");
				String heure = rs.getString("heure");
				Responsable responsable = getReponsableFromId(rs.getString("id_responsable"));
				return new Tournoi(idTournoi, nom, categorie, jeu, dateDebut, dateFin, dateLimiteInscription, heure, responsable);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}


	/**
	 * Renvoie une écurie à partir de son id
	 * @param id l'id de l'écurie
	 * @return l'écurie correspondante à cet id
	 */
	public static Ecurie getEcurieFromId(String id) {		
		
		try {
			Statement st = connx.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM esporter_ecurie, esporter_compte WHERE id_ecurie = '"+id+"' AND id_compte = '"+id+"'");
			if(rs.next()) {
				String nom = rs.getString("nom");
				String mdp = rs.getString("mdp");
				Statut s = Statut.valueOf(rs.getString("statut"));
				return new Ecurie(id, nom, mdp, s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return null;
	}

	/**
	 * Renvoie une équipe à partir de son id
	 * @param id l'id de l'équipe
	 * @return l'équipe correspondante à cet id
	 */
	public static Equipe getEquipeFromId(String id) {
		
		try {
			Statement st = connx.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM esporter_equipe, esporter_compte WHERE id_equipe  = '"+id+"' AND id_compte = '"+id+"'");
			if(rs.next()) {
				String mdp = rs.getString("mdp");
				String nom = rs.getString("nom");
				Jeu jeu = getJeuFromIdJeu(rs.getString("id_jeu"));
				Ecurie ecurie = getEcurieFromId(rs.getString("id_ecurie"));
				Equipe e = new Equipe(id, nom, mdp, jeu, ecurie);
				for(Joueur j : getJoueursFromEquipe(e)) {
					e.addJoueur(j);
				}
				return e;
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null; 
	}
	
	/**
	 * Renvoie un jeu depuis son id
	 * @param idJeu l'id du jeu
	 * @return le jeu correspondant à ce nom
	 */
	public static Jeu getJeuFromIdJeu(String idJeu) {
		try {
			Statement st = connx.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM esporter_jeu WHERE id_jeu='"+idJeu+"'");
			if(rs.next()){
				String nom = rs.getString("nom");
				int nbJoueurs = rs.getInt("nb_joueurs");
				return new Jeu(idJeu, nom, nbJoueurs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}


	/**
	 * Renvoie la liste de toutes les rencontres enregistrées dans la base de donnée
	 * @return la liste de toutes les rencontres enregistrées dans la base de donnée
	 */
	public static List<Rencontre> getRencontres() {
		Statement st;

		List<Rencontre> rencontres = new LinkedList<>();
		try {
			st = connx.createStatement();

			ResultSet rs = st.executeQuery("SELECT * FROM esporter_rencontre");
			while (rs.next()){
				Rencontre r = new Rencontre(getEquipeFromId(rs.getString("id_Equipe1")), getEquipeFromId(rs.getString("id_Equipe2")), getTournoiFromId(rs.getString("id_tournoi")), rs.getString("heure"), getArbitreFromIdArbitre(rs.getString("id_arbitre")));
				if(rs.getInt("resultat")==1) {
					r.setEquipeGagnante(getEquipeFromId(rs.getString("id_equipe1")));
				} else if(rs.getInt("resultat")==2) {
					r.setEquipeGagnante(getEquipeFromId(rs.getString("id_equipe2")));
				}
				rencontres.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rencontres;
	}


	/**
	 * Renvoie toutes les rencontres affectées à un arbitre
	 * @param a l'arbitre dont on cherche les rencontres
	 * @return la liste des rencontres de l'arbitre
	 */
	public static List<Rencontre> getRencontresDunArbitre(Arbitre a) {

		List<Rencontre> rencontres = new LinkedList<>();
		try {
			Statement st = connx.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM esporter_rencontre WHERE id_arbitre = '"+a.getCompte().getIdentifiant()+"'");
			while (rs.next()){
				Rencontre rencontre = new Rencontre(getEquipeFromId(rs.getString("id_equipe1")), getEquipeFromId(rs.getString("id_equipe2")), getTournoiFromId(rs.getString("id_tournoi")), rs.getString("heure"), a);
				int res = rs.getInt("resultat");
				if (res!=0) {
					rencontre.setEquipeGagnante(getEquipeFromId(rs.getString("id_equipe"+res)));
				}
				rencontres.add(rencontre);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rencontres;
	}


	/**
	 * Renvoie toutes les rencontres affectées à un joueur
	 * @param a le joueur dont on cherche les rencontres
	 * @return la liste des rencontres du joueur
	 */
	public static List<Rencontre> getRencontresDunJoueur(Joueur j) {

		List<Rencontre> rencontres = new LinkedList<>();
		try {
			Statement st = connx.createStatement();
			String idEquipe = j.getEquipe().getCompte().getIdentifiant();
			ResultSet rs = st.executeQuery("SELECT * FROM esporter_rencontre WHERE id_equipe1 = '"+idEquipe+"' OR id_equipe2 = '"+idEquipe+"'");

			while(rs.next()) {
				rencontres.add(new Rencontre(getEquipeFromId(rs.getString("id_equipe1")), getEquipeFromId(rs.getString("id_equipe2")), getTournoiFromId(rs.getString("id_tournoi")), rs.getString("heure"), getArbitreFromIdArbitre(rs.getString("id_arbitre"))));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rencontres;
	}


	/**
	 * Renvoie un arbitre de la base de donnée à partir de son id
	 * @param idArbitre l'id de l'arbitre
	 * @return l'arbitre correspondant à cet id
	 */
	public static Arbitre getArbitreFromIdArbitre(String idArbitre) {

		try {
			Statement st = connx.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM esporter_arbitre, esporter_compte WHERE id_arbitre = '"+idArbitre+"' AND id_compte = '"+idArbitre+"'");
			if (rs.next()) {
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String tel = rs.getString("tel");
				String mdp = rs.getString("mdp");
				return new Arbitre(idArbitre, nom, prenom, tel, mdp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};	
		return null;
	}
	
	/**
	 * Renvoie un joueur de la base de donnée à partir de son id
	 * @param idJoueur l'id du joueur
	 * @return le joueur correspondant à cet id
	 */
	public static Joueur getJoueurFromIdJoueur(String idJoueur) {
		
		Joueur joueur = null;
		try {
			ps = connx.prepareStatement("SELECT * FROM esporter_joueur, esporter_compte WHERE id_joueur = ? AND id_compte = id_joueur");
			ps.setString(1, idJoueur);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				joueur = new Joueur(idJoueur, rs.getString("nom"), rs.getString("prenom"), rs.getString("tel"), rs.getString("mdp"), rs.getString("pseudo"), getEquipeFromId(rs.getString("id_Equipe")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return joueur;
	}
	
	/**
	 * Renvoie un compte de la base de donnée à partir de son id
	 * @param id l'id du compte
	 * @return le compte correspondant à cet id
	 */
	
	public static Compte getCompteFromIdCompte(String id) {

		Compte compte=null;
		try {
			ps = connx.prepareStatement("SELECT * FROM esporter_compte WHERE id_compte = ?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				compte = new Compte(Compte.Type.valueOf(rs.getString("type_c")), rs.getString("id_compte"), rs.getString("mdp"));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return compte;
	}
	
	/**
	 * Renvoie un responsable de la base de donnée à partir de son id
	 * @param id l'id du responsable
	 * @return le responsable correspondant à cet id
	 */
	
	public static Responsable getResponsableFromIdResponsable(String id) {

		Responsable responsable = null;
		try {
			ps = connx.prepareStatement("SELECT nom, prenom, tel, mdp FROM esporter_responsable, esporter_compte WHERE id_responsable = ? AND id_responsable = id_compte");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				responsable = new Responsable(id, rs.getString("nom"), rs.getString("prenom"), rs.getString("tel"), rs.getString("mdp"));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return responsable;
	}
	
	/**
	 * Renvoie un gestionnaire de la base de donnée à partir de son id
	 * @param id l'id du gestionnaire
	 * @return le gestionnaire correspondant à cet id
	 */
	
	public static Gestionnaire getGestionnaireFromIdGestionnaire(String id) {

		Gestionnaire gestionnaire = null;
		try {
			ps = connx.prepareStatement("SELECT nom, prenom, tel, mdp FROM esporter_gestionnaire, esporter_compte WHERE id_gestionnaire = ? AND id_gestionnaire = id_compte");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				gestionnaire = new Gestionnaire(id, rs.getString("nom"), rs.getString("prenom"), rs.getString("tel"), rs.getString("mdp"));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return gestionnaire;
	}

	/**
	 * Renvoie tous les jeux de la base de donnée
	 * @return la liste des Jeu
	 */
	public static List<Jeu> getJeux() {

		List<Jeu> jeux = new LinkedList<>();
		try {
			Statement st = connx.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM esporter_jeu");
			while (rs.next()){
				jeux.add(new Jeu(rs.getString("id_jeu"), rs.getString("nom"), rs.getInt("nb_joueurs")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jeux;
	}


	/**
	 * Renvoie tous les arbitres de la base de donnée
	 * @return la liste des Arbitre
	 */
	public static List<Arbitre> getArbitres() {

		List<Arbitre> arbitres = new LinkedList<>();
		try {
			Statement st = connx.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM esporter_arbitre, esporter_compte WHERE id_arbitre = id_compte");
			while (rs.next()){
				arbitres.add(new Arbitre(rs.getString("id_arbitre"), rs.getString("nom"), rs.getString("prenom"), rs.getString("tel"), rs.getString("mdp")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return arbitres;
	}

	/**
	 * Renvoie tous les gestionnaires de la base de donnée
	 * @return la liste des Gestionnaire
	 */
	public static List<Gestionnaire> getGestionnaires() {

		List<Gestionnaire> gestionnaires = new LinkedList<>();
		try {
			Statement st = connx.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM esporter_gestionnaire, esporter_compte WHERE id_gestionnaire = id_compte");
			while (rs.next()){
				gestionnaires.add(new Gestionnaire(rs.getString("id_gestionnaire"), rs.getString("nom"), rs.getString("prenom"), rs.getString("tel"), rs.getString("mdp")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return gestionnaires;
	}

	/**
	 * Renvoie tous les responsables de la base de donnée
	 * @return la liste des Responsable
	 */
	public static List<Responsable> getResponsables() {

		List<Responsable> responsables = new LinkedList<>();
		try {
			Statement st = connx.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM esporter_responsable, esporter_compte WHERE id_responsable = id_compte");
			while (rs.next()){
				responsables.add(new Responsable(rs.getString("id_responsable"), rs.getString("nom"), rs.getString("prenom"), rs.getString("tel"), rs.getString("mdp")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return responsables;
	}

	/**
	 * Renvoie tous les joueurs de la base de donnée
	 * @return la liste des Joueur
	 */
	public static List<Joueur> getJoueurs() {

		List<Joueur> joueurs = new LinkedList<>();
		try {
			Statement st = connx.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM esporter_joueur, esporter_compte WHERE id_joueur = id_compte");

			while (rs.next()){
				joueurs.add(new Joueur(rs.getString("id_joueur"), rs.getString("nom"), rs.getString("prenom"), rs.getString("tel"),  rs.getString("pseudo"), rs.getString("mdp"), getEquipeFromId(rs.getString("id_equipe"))));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return joueurs;
	}

	/**
	 * Renvoie toutes les écuries de la base de donnée
	 * @return la liste des Ecurie
	 */
	public static List<Ecurie> getEcuries() {

		List<Ecurie> ecuries = new LinkedList<>();
		try {
			Statement st = connx.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM esporter_ecurie, esporter_compte WHERE id_ecurie = id_compte");
			while (rs.next()){
				ecuries.add(new Ecurie(rs.getString("id_ecurie"), rs.getString("nom"), rs.getString("mdp"), Statut.valueOf(rs.getString("statut"))));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ecuries;
	}


	/**
	 * Renvoie vrai si l'équipe e est inscrite au tournoi t
	 * @param e l'équipe dont on vérifie l'inscription
	 * @param t le tournoi d'inscription
	 * @return true si l'équipe e est inscrite au tournoi t, faux sinon
	 */
	public static boolean estInscrit(Equipe e, Tournoi t) {

		try {
			Statement st = connx.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM esporter_equipe, esporter_inscription WHERE esporter_equipe.id_equipe = '" + e.getCompte().getIdentifiant() + "' AND esporter_equipe.id_equipe = esporter_inscription.id_equipe AND esporter_inscription.id_tournoi = '" + t.getIdTournoi() + "'");
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return false;
	}

	/**
	 * Renvoie la liste des équipes de la base de donnée triées par nombre de points
	 * @return une liste d'équipes triées par nombre de points
	 */
	public static List<Equipe> getEquipesTri() {

		List<Equipe> equipes = new LinkedList<>();
		try {
			Statement st = connx.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM esporter_equipe, esporter_compte WHERE id_equipe = id_compte");
			while (rs.next()){
				Equipe e = new Equipe(rs.getString("id_equipe"), rs.getString("nom"), rs.getString("mdp"), getJeuFromIdJeu(rs.getString("id_jeu")), getEcurieFromId(rs.getString("id_ecurie")));
				for(Joueur j : getJoueursFromEquipe(e)) {
					e.addJoueur(j);
				}
				equipes.add(e);
			}
			equipes.sort(new Comparator<Equipe>() {
				public int compare(Equipe e1, Equipe e2) {
					if(e1.calculPointsTotaux(getTournoisFromEquipe(e1))!=(e2.calculPointsTotaux(getTournoisFromEquipe(e2)))) {
						return e1.calculPointsTotaux(getTournoisFromEquipe(e1))-(e2.calculPointsTotaux(getTournoisFromEquipe(e2)));
					} else {
						return e1.getNom().compareTo(e2.getNom());
					}
				}
			});
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		
		return equipes;
	}

	/**
	 * Renvoie toutes les équipes de la base de donnée
	 * @return la liste des Equipe
	 */
	public static List<Equipe> getEquipes() {

		List<Equipe> equipes = new LinkedList<>();
		try {
			Statement st = connx.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM esporter_equipe, esporter_compte WHERE id_equipe = id_compte");
			while (rs.next()){
				Equipe e = new Equipe(rs.getString("id_equipe"), rs.getString("nom"), rs.getString("mdp"), getJeuFromIdJeu(rs.getString("id_jeu")), getEcurieFromId(rs.getString("id_ecurie")));
				for(Joueur j : getJoueursFromEquipe(e)) {
					e.addJoueur(j);
				}
				equipes.add(e);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return equipes;
	}

	/**
	 * Renvoie toutes les équipes appartenant à une écurie dans la base de donnée
	 * @param ecurie l'écurie dont on cherche les équipes
	 * @return la liste des Equipe appartenant à l'écurie
	 */
	public static List<Equipe> getEquipesFromEcurie(Ecurie ecurie) {

		List<Equipe> equipes = new LinkedList<>();
		try {
			Statement st = connx.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM esporter_equipe , esporter_compte WHERE id_equipe = id_compte AND id_ecurie ='"+ecurie.getCompte().getIdentifiant()+"'");
			while (rs.next()){
				equipes.add(new Equipe(rs.getString("id_equipe"), rs.getString("nom"), rs.getString("mdp"), getJeuFromIdJeu(rs.getString("id_jeu")), getEcurieFromId(rs.getString("id_ecurie"))));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return equipes;
	}

	/**
	 * Renvoie tous les joueurs appartenant à une équipe dans la base de donnée
	 * @param equipe l'équipe dont on cherche les joueurs
	 * @return la liste des Joueur appartenant à l'équipe
	 */
	public static List<Joueur> getJoueursFromEquipe(Equipe equipe) {

		List<Joueur> joueurs = new LinkedList<>();
		try {
			Statement st = connx.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM esporter_joueur, esporter_compte WHERE id_joueur = id_compte AND id_equipe ='"+equipe.getCompte().getIdentifiant()+"'");
			while (rs.next()){
				joueurs.add(new Joueur(rs.getString("id_joueur"), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(2), equipe));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return joueurs;
	}

	/**
	 * Renvoie toutes les rencontres appartenant à un tournoi dans la base de donnée
	 * @param t le tournoi dont on cherche les rencontres
	 * @return la liste des Rencontre correspondant au tournoi t
	 */
	public static List<Rencontre> getRencontresFromTournoi(Tournoi t) {
		
		List<Rencontre> rencontres = new LinkedList<>();
		try {
			Statement st = connx.createStatement();
			Statement st2 = connx.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM esporter_rencontre WHERE id_tournoi = '" + t.getIdTournoi() + "'");
			while (rs.next()) {
				Rencontre r = new Rencontre(getEquipeFromId(rs.getString("id_equipe1")), getEquipeFromId(rs.getString("id_equipe2")), getTournoiFromId(rs.getString("id_tournoi")), rs.getString("heure"), getArbitreFromIdArbitre(rs.getString("id_arbitre")));
				ResultSet rs2 = st2.executeQuery("SELECT resultat FROM esporter_rencontre WHERE id_tournoi = '" + t.getIdTournoi() +"' ");
				if(rs2.next()) {
					if(rs2.getInt("resultat")==1) {
						r.setEquipeGagnante(getEquipeFromId(rs.getString("id_equipe1")));
					} else if(rs2.getInt("resultat")==2) {
						r.setEquipeGagnante(getEquipeFromId(rs.getString("id_equipe2")));
					}
				}
				rencontres.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rencontres;
	}

	/**
	 * Renvoie toutes les équipes appartenant à un tournoi dans la base de donnée
	 * @param t le tournoi dont on cherche les équipes
	 * @return la liste des Equipe correspondant au tournoi t
	 */
	public static List<Equipe> getEquipesFromTournoi(Tournoi t) {

		List<Equipe> equipes = new LinkedList<>();
		try {
			Statement st = connx.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM esporter_equipe, esporter_compte, esporter_inscription WHERE esporter_equipe.id_equipe = id_compte AND esporter_equipe.id_equipe = esporter_inscription.id_equipe AND esporter_inscription.id_tournoi = '" +  t.getIdTournoi() + "'");
			while (rs.next()) {
				equipes.add(new Equipe(rs.getString("id_equipe"), rs.getString("nom"), rs.getString("mdp"), getJeuFromIdJeu(rs.getString("id_jeu")), getEcurieFromId(rs.getString("id_ecurie"))));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return equipes;
	}

	/**
	 * Renvoie tous les tournois affecté à un responsable dans la base de donnée
	 * @param responsable le responsable dont on cherche les tournois
	 * @return la liste des Tournoi correspondant au reponsable
	 */
	public static List<Tournoi> getTournoisFromResponsable(Responsable responsable) {

		List<Tournoi> tournois = new LinkedList<>();
		try {
			Statement st = connx.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM esporter_tournoi WHERE id_responsable = '" + responsable.getCompte().getIdentifiant() + "'");
			while (rs.next()) {
				Tournoi t = new Tournoi(rs.getString("id_tournoi"), rs.getString("nom"), Categorie.valueOf(rs.getString("categorie")), getJeuFromIdJeu(rs.getString("id_jeu")), rs.getString("date_debut"), rs.getString("date_fin"), rs.getString("date_limite_inscription"), rs.getString("heure"), getReponsableFromId(rs.getString("id_responsable")));
				for (Rencontre r : getRencontresFromTournoi(t)) {
					t.getRencontres().add(r);
				}
				for (Equipe e : getEquipesFromTournoi(t)) {
					t.getEquipes().add(e);
				}
				tournois.add(t);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return tournois;
	}


	/**
	 * Renvoie tous les tournois de la base de donnée
	 * @return la liste de tous les Tournoi
	 */
	public static List<Tournoi> getTournois() {

		List<Tournoi> tournois = new LinkedList<>();
		try {
			Statement st = connx.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM esporter_tournoi");
			while (rs.next()) {
				tournois.add(new Tournoi(rs.getString("id_tournoi"), rs.getString("nom"), Categorie.valueOf(rs.getString("categorie")), getJeuFromIdJeu(rs.getString("id_jeu")), rs.getString("date_debut"), rs.getString("date_fin"), rs.getString("date_limite_inscription"), rs.getString("heure"), getReponsableFromId(rs.getString("id_responsable"))));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tournois;
	}


	/**
	 * Renvoie tous les tournois d'un équipe dans la base de donnée
	 * @param e l'équipe dont on cherche les tournois
	 * @return la liste des Tournoi correspondant à l'équipe
	 */
	public static List<Tournoi> getTournoisFromEquipe(Equipe e) {
		Statement st;

		List<Tournoi> tournois = new LinkedList<>();
		//ajout try catch
		try {
			st = connx.createStatement();

			ResultSet rs = st.executeQuery("SELECT * FROM esporter_tournoi t, esporter_rencontre r WHERE t.id_tournoi = r.id_tournoi AND r.id_equipe1 = '" + e.getCompte().getIdentifiant() + "' OR r.id_equipe2 = '" + e.getCompte().getIdentifiant() + "'");
			while (rs.next()) {
				tournois.add(new Tournoi(rs.getString("id_tournoi"), rs.getString("nom"), Categorie.valueOf(rs.getString("categorie")), getJeuFromIdJeu(rs.getString("id_jeu")), rs.getString("date_debut"), rs.getString("date_fin"), rs.getString("date_limite_inscription"), rs.getString("heure"), getReponsableFromId(rs.getString("id_responsable"))));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return tournois;
	}
	
	public static List<Compte> getComptes() {
		Statement st;

		List<Compte> comptes = new LinkedList<>();
		try {
			st = connx.createStatement();

			ResultSet rs = st.executeQuery("SELECT * FROM esporter_compte");
			while (rs.next()) {
				comptes.add(new Compte(Compte.Type.valueOf(rs.getString("type_c")), rs.getString("id_compte"), rs.getString("mdp")));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return comptes;
	}


	public static Compte getCompteFromId(String id) {

		Compte compte=null;
		try {
			ps = connx.prepareStatement("SELECT * FROM esporter_compte WHERE id_compte = ?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				compte = new Compte(Compte.Type.valueOf(rs.getString("type_c")), rs.getString("id_compte"), rs.getString("mdp"));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return compte;
	}

}
