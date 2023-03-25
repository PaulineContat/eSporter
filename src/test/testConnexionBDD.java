package test;

import static org.junit.Assert.*;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import connexion.ConnexionBDD;
import modele.Arbitre;
import modele.Compte;
import modele.Ecurie;
import modele.Equipe;
import modele.Gestionnaire;
import modele.Jeu;
import modele.Joueur;
import modele.Rencontre;
import modele.Responsable;
import modele.Tournoi;
import modele.Tournoi.Categorie;

public class testConnexionBDD {

	ConnexionBDD connect;
	Connection connx;
	Statement st = null;
	PreparedStatement ps = null;
	ResultSet rs;
	Jeu j;
	//Jeu j2;
	//Jeu j3;
	Responsable r;
	//Responsable r2;
	Gestionnaire g;
	Arbitre a;
	//Arbitre a2;
	Tournoi t;
	Ecurie e;
	Ecurie e2;
	Equipe eq;
	Equipe eq2;
	Equipe eq3;
	Rencontre re;
	Joueur jo;

	@Before
	public void setUp() throws SQLException {
		this.connect = new ConnexionBDD("CNP3364A", "$iutinfo", "jdbc:oracle:thin:@telline.univ-tlse3.fr:1521:ETUPRE");
		this.connx = connect.connecting();
		this.st=connx.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
		this.j = new Jeu("LOL",12);
		//this.j2 = new Jeu("Spyro",1);
		this.r = new Responsable("Boude", "Francoise", "0652334281", "mdp123");
		//this.r2 = new Responsable("Bof", "Sabine", "0909090909", "mdp123");
		this.g = new Gestionnaire("Terrieur", "Alex", "0652334290", "mdp123");
		this.a = new Arbitre("Fej", "Francois", "0652334280", Compte.passwordHash("mdp123"));
		//this.a2 = new Arbitre("Mahe", "Yanis", "0909090909", "mdp123");
		this.t = new Tournoi("BenoitCup", Categorie.LOCAL, this.j, "01/01/2023", "02/01/2023", "11/10/2022", "10h00", this.r);
		this.e = new Ecurie("Faze", "123mdp", null);
		this.e2 = new Ecurie("Ecurie", "mdp123", null);
		this.eq = new Equipe("Faze#LOL", "mdp123", this.j, e);
		this.eq2 = new Equipe("Ecurie#LOL", "mdp123", this.j, e);
		this.eq3 = new Equipe("Ecurie#LOL2", "mdp123", this.j, e);
		this.re = new Rencontre(this.eq3, this.eq2, this.t, "8:00", this.a);
		this.jo = new Joueur("Mouais", "Helene", "0752334208", "Helelene", "mdp123", this.eq);
		//this.j3 = new Jeu("Pokemon",10);
	}

	@Test
	public void testEnregistrerArbitre() throws SQLException {
		connect.enregistrerArbitre(connx, this.a);
		this.rs = this.st.executeQuery("SELECT * FROM esporter_arbitre WHERE id_arbitre = '"+this.a.getCompte().getIdentifiant()+"'");
		String nom = "vide";
		String tel = "vide";
		String prenom = "vide";
		String mdp = "vide";
		if(rs.next()) {
			nom = rs.getString(3);
			prenom = rs.getString(4);
			tel = rs.getString(5);
			mdp = rs.getString("mdp");
		}
		assertEquals("Fej", nom);
		assertEquals("Francois", prenom);
		assertEquals("0652334280", tel);
		assertEquals(Compte.passwordHash("mdp123"), mdp);
	}

//	@Test
//	public void testGetArbitres() throws SQLException{
//		//connect.enregistrerArbitre(connx, this.a);
//		Map<String, Arbitre> arbitres;
//		arbitres = connect.getArbitres(connx);
//		this.rs=this.st.executeQuery("SELECT * FROM esporter_arbitre WHERE id_arbitre ='"+this.a.getCompte().getIdentifiant()+"'");
//		//assertEquals(arbitres.get(this.a.getCompte().getIdentifiant()), this.a); //marche pas : redefinir equals + hashcode
//		assertEquals(arbitres.get(this.a.getCompte().getIdentifiant()).getCompte().getIdentifiant(), this.a.getCompte().getIdentifiant()); //marchait mais ne marche plus jsp pourquoi
//	}

	@Test
	public void testEnregistrerJeu() throws SQLException {
		connect.enregistrerJeu(connx, this.j);
		this.rs = this.st.executeQuery("SELECT * FROM esporter_jeu WHERE nom = '" + this.j.getNom() + "'");
		String nom = "vide";
		int nbJoueurs = 0;
		if(rs.next()) {
			nom = rs.getString(2);
			nbJoueurs=rs.getInt(3);
		}
		assertEquals(this.j.getNom(), nom);
		assertEquals(this.j.getNbJoueurs(), nbJoueurs);
	}

	@Test
	public void testEnregistrerResponsable() throws SQLException {
		connect.enregistrerResponsable(connx, this.r);
		this.rs = this.st.executeQuery("SELECT * FROM esporter_responsable WHERE id_responsable ='"+this.r.getCompte().getIdentifiant()+"'");
		String nom = "vide";
		String prenom = "vide";
		String tel = "vide";
		String id = "vide";
		if(rs.next()) {
			nom = rs.getString(3);
			prenom = rs.getString(4);
			tel = rs.getString(5);
			id = rs.getString(1);
		}
		assertEquals("Boude", nom);
		assertEquals("Francoise", prenom);
		assertEquals("0652334281", tel);
		assertEquals("francoise.boude", id);
	}


	@Test
	public void testEnregistrerGestionnaire() throws SQLException {
		connect.enregistrerGestionnaire(connx, this.g);
		rs = st.executeQuery("SELECT * FROM esporter_gestionnaire WHERE id_gestionnaire ='"+this.g.getCompte().getIdentifiant()+"'");
		String nom = "vide";
		String prenom = "vide";
		String tel = "vide";
		if(rs.next()) {
			nom = rs.getString(3);
			prenom = rs.getString(4);
			tel = rs.getString(5);
		}
		assertEquals("Terrieur", nom);
		assertEquals("Alex", prenom);
		assertEquals("0652334290", tel);
	}

	@Test
	public void testEnregistrerTournoi() throws SQLException {
		connect.enregistrerTournoi(connx, this.t);
		rs = st.executeQuery("SELECT * FROM esporter_tournoi WHERE nom ='"+this.t.getNom()+"'");
		String categorie = "vide";
		String nom = "vide";
		String dateDebut = "vide";
		String dateFin = "vide";
		String dateLimiteInscription ="vide";
		String heure ="vide";
		String idResponsable = "vide";
		String jeu = "vide";
		if(rs.next()) {
			categorie = rs.getString("categorie");
			nom = rs.getString("nom");
			dateDebut = rs.getString("date_Debut");
			dateFin = rs.getString("date_Fin");
			dateLimiteInscription=rs.getString("date_Limite_Inscription");
			heure = rs.getString("heure");
			idResponsable = rs.getString("id_Responsable");
			jeu = rs.getString("jeu");
		}
		assertEquals(this.t.getCategorie().name(), categorie);
		assertEquals(this.t.getNom(), nom);
		assertEquals(this.t.getDateDebut(), dateDebut);
		assertEquals(this.t.getDateFin(), dateFin);
		assertEquals(this.t.getDateLimiteInscription(), dateLimiteInscription);
		assertEquals(this.t.getHeure(), heure);
		assertEquals(this.t.getResponsable().getCompte().getIdentifiant(), idResponsable);
		assertEquals(this.t.getJeu().getNom(), jeu);
	}


	@Test
	public void testEnregistrerEcurie() throws SQLException {
		connect.enregistrerEcurie(connx, this.e);
		rs = st.executeQuery("SELECT * FROM esporter_ecurie WHERE id_ecurie = '"+this.e.getCompte().getIdentifiant()+"'");
		String nom = "vide";
		if(rs.next()) {
			nom = rs.getString(3);
		}
		assertEquals("Faze", nom);
	}

	@Test
	public void testEnregistrerEquipe() throws SQLException {
		connect.enregistrerEquipe(connx, this.eq);
		rs = st.executeQuery("SELECT * FROM esporter_equipe WHERE id_equipe = '"+this.eq.getCompte().getIdentifiant()+"'");
		String idEquipe="vide";
		String nom = "vide";
		String idEcurie ="vide";
		int jeu = 0;
		if(rs.next()) {
			idEquipe=rs.getString(1);
			nom = rs.getString(3);
			idEcurie = rs.getString(4);
			jeu = rs.getInt(5);
		}
		assertEquals(this.eq.getCompte().getIdentifiant(),idEquipe);
		assertEquals(this.eq.getNom(), nom);
		assertEquals(this.e.getCompte().getIdentifiant(), idEcurie);
		assertEquals(1, jeu);
	}

	@Test
	public void testEnregistrerRencontre() throws SQLException {
		connect.enregistrerEcurie(connx, this.e2);
		connect.enregistrerEquipe(connx, this.eq3);
		connect.enregistrerEquipe(connx, this.eq2);
		connect.enregistrerRencontre(connx, this.re);
		//connect.enregistrerRencontre(connx, this.re, this.t);
		this.st=connx.createStatement();
		ResultSet rs = st.executeQuery("SELECT id_tournoi FROM esporter_tournoi WHERE nom = '"+this.re.getTournoi().getNom()+"'");
		int idTournoi = 0;
		if(rs.next()) {
			idTournoi = rs.getInt("id_tournoi");
		}
		rs = st.executeQuery("SELECT * FROM esporter_Rencontre WHERE id_equipe1 = '" + this.re.getEquipe1().getCompte().getIdentifiant()+"' AND id_equipe2 ='"+this.re.getEquipe2().getCompte().getIdentifiant()+"' AND id_tournoi = '"+idTournoi+"' AND heure = '"+this.re.getHeure()+"'");
		String idEq1 = "vide";
		String idEq2 = "vide";
		int idTournoii = 0;
		String heure = "vide";
		String idArbitre = "vide";
		if(rs.next()) {
			idEq1 = rs.getString(1);
			idEq2 = rs.getString(2);
			idTournoii = rs.getInt(3);
			heure = rs.getString(4);
			idArbitre = rs.getString(6);
		}
		assertEquals(this.re.getEquipe1().getCompte().getIdentifiant(), idEq1);
		assertEquals(this.re.getEquipe2().getCompte().getIdentifiant(), idEq2);
		assertEquals(idTournoi, idTournoii);
		assertEquals(this.re.getHeure(), heure);
		assertEquals(0,this.re.getResultat());
		assertEquals(this.re.getArbitre().getCompte().getIdentifiant(), idArbitre);
	}
	
	@Test
	public void testEnregistrerResultat() throws SQLException {
		connect.enregistrerEcurie(connx, this.e2);
		connect.enregistrerEquipe(connx, this.eq3);
		connect.enregistrerEquipe(connx, this.eq2);
		connect.enregistrerResultatRencontre(connx, this.re, 2);
		//connect.enregistrerRencontre(connx, this.re, this.t);
		this.st=connx.createStatement();
		ResultSet rs = st.executeQuery("SELECT id_tournoi FROM esporter_tournoi WHERE nom = '"+this.re.getTournoi().getNom()+"'");
		int idTournoi = 0;
		if(rs.next()) {
			idTournoi = rs.getInt("id_tournoi");
		}
		rs = st.executeQuery("SELECT * FROM esporter_Rencontre WHERE id_equipe1 = '" + this.re.getEquipe1().getCompte().getIdentifiant()+"' AND id_equipe2 ='"+this.re.getEquipe2().getCompte().getIdentifiant()+"' AND id_tournoi = '"+idTournoi+"' AND heure = '"+this.re.getHeure()+"'");
		String idEq1 = "vide";
		String idEq2 = "vide";
		int idTournoii = 0;
		String heure = "vide";
		String idArbitre = "vide";
		if(rs.next()) {
			idEq1 = rs.getString(1);
			idEq2 = rs.getString(2);
			idTournoii = rs.getInt(3);
			heure = rs.getString(4);
			idArbitre = rs.getString(6);
		}
		assertEquals(this.re.getEquipe1().getCompte().getIdentifiant(), idEq1);
		assertEquals(this.re.getEquipe2().getCompte().getIdentifiant(), idEq2);
		assertEquals(idTournoi, idTournoii);
		assertEquals(this.re.getHeure(), heure);
		assertEquals(2,this.re.getResultat());
		assertEquals(this.re.getArbitre().getCompte().getIdentifiant(), idArbitre);
	}

	@Test
	public void testEnregistrerJoueur() throws SQLException {
		connect.enregistrerJoueur(connx, this.jo);
		rs = st.executeQuery("SELECT * FROM esporter_Joueur WHERE id_joueur = '"+this.jo.getCompte().getIdentifiant()+"'");
		String idJoueur = "vide";
		String nom = "vide";
		String prenom = "vide";
		String tel = "vide";
		String pseudo = "vide";
		String idEquipe = "vide";
		if(rs.next()) {
			idJoueur = rs.getString(1);
			nom = rs.getString(3);
			prenom = rs.getString(4);
			tel = rs.getString(5);
			pseudo = rs.getString(6);
			idEquipe = rs.getString(7);
		}
		assertEquals(this.jo.getCompte().getIdentifiant(), idJoueur);
		assertEquals(this.jo.getNom(), nom);
		assertEquals(this.jo.getPrenom(), prenom);
		assertEquals(this.jo.getTel(), tel);
		assertEquals(this.jo.getPseudo(), pseudo);
		assertEquals(this.jo.getEquipe().getCompte().getIdentifiant(), idEquipe);
	}
	
	@Test
	public void testGetJeux() throws SQLException {
		
	}
	
	@Test
	public void testGetJoueurs() throws SQLException {
		Map<String, Joueur> joueurs;
		joueurs = connect.getJoueurs(connx);
		rs = st.executeQuery("SELECT * FROM esporter_Joueur WHERE id_joueur = '"+this.jo.getCompte().getIdentifiant()+"'");
		String idJoueur = "vide";
		String nom = "vide";
		String prenom = "vide";
		String tel = "vide";
		String pseudo = "vide";
		String idEquipe = "vide";
		if(rs.next()) {
			idJoueur = rs.getString(1);
			nom = rs.getString(3);
			prenom = rs.getString(4);
			tel = rs.getString(5);
			pseudo = rs.getString(6);
			idEquipe = rs.getString(7);
		}
		assertEquals(idJoueur, joueurs.get(idJoueur).getCompte().getIdentifiant());
		assertEquals(nom, joueurs.get(idJoueur).getNom());
		assertEquals(prenom, joueurs.get(idJoueur).getPrenom());
		assertEquals(tel, joueurs.get(idJoueur).getTel());
		assertEquals(pseudo, joueurs.get(idJoueur).getPseudo());
		assertEquals(idEquipe, joueurs.get(idJoueur).getEquipe().getCompte().getIdentifiant());
	}
	
}