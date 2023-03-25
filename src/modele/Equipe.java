package modele;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import modele.Tournoi.Categorie;


public class Equipe {

	private String nom;

	private int nbJoueurs;

	private Compte compte;

	private Jeu jeu;

	private int points;

	private Ecurie ecurie;

	private Joueur[] joueurs;

	private List<Rencontre> rencontres;

	public Equipe(String id, String nom, String mdp, Jeu jeu, Ecurie ecurie) {
		this.nom = nom;
		this.ecurie = ecurie;
		this.nbJoueurs = 0;
		this.compte = new Compte(Compte.Type.EQUIPE, id, mdp);
		this.jeu = jeu;
		this.joueurs = new Joueur[jeu.getNbJoueurs()];
	}

	public Compte getCompte() {
		return this.compte;
	}

	public Ecurie getEcurie() {
		return this.ecurie;
	}

	public Jeu getJeu() {
		return this.jeu;
	}

	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}

	public Joueur[] getJoueurs() {
		return joueurs;
	}

	public void addJoueur(Joueur j) {
		if (this.nbJoueurs < this.jeu.getNbJoueurs()) {
			this.nbJoueurs++;
			this.joueurs[this.nbJoueurs-1]=j;
		}
	}

	public int getPoints() {
		return this.points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getPositionTournoi(Tournoi t) {
		List<Rencontre> rencontresTournoi = t.getRencontres();
		LinkedList<Equipe> equipesTournoi = new LinkedList<>();
		
		
		int maxVictoires = log2(equipesTournoi.size());
		if (this.nbMatchsGagnes(t) == maxVictoires) {
			return 1;
		}
		if (this.nbMatchsGagnes(t) == maxVictoires-1) {
			List<Rencontre> rencontresPremier;
			Equipe premier = null;
			for (Equipe e : equipesTournoi) {
				if (e.nbMatchsGagnes(t) == maxVictoires) {
					premier = e;
				}
			}
			rencontresPremier = premier.getRencontresDeLEquipePourUnTournoi(t);
			Collections.sort(rencontresPremier, Rencontre.comparatorHeureRencontre());
			Equipe deuxieme; 
			if (rencontresPremier.get(0).getEquipeGagnante() == rencontresPremier.get(0).getEquipe1()) {
				deuxieme = rencontresPremier.get(0).getEquipe2();
			} else {
				deuxieme = rencontresPremier.get(0).getEquipe1();
			}
			
			if (this == deuxieme) {
				return 2;
			}
			return 3;
			
			
		}
		if (this.nbMatchsPerdus(t) == 2) {
			return 4;
		}
		
		return 0;
		
	}

	public List<Rencontre> getRencontresDeLEquipePourUnTournoi(Tournoi t){
		List<Rencontre> rencontresTournoi = t.getRencontres();
		List<Rencontre> rencontresEquipeTournoi = new LinkedList<>();
		for (Rencontre r : rencontresTournoi) {
			//if(r.getTournoi().equals(t)) { //ou prendre l'id si ça marche pas
			if((r.getEquipe1().getCompte().getIdentifiant().equals(this.getCompte().getIdentifiant())) || (r.getEquipe2().getCompte().getIdentifiant().equals(this.getCompte().getIdentifiant()))){
				rencontresEquipeTournoi.add(r);
			}
		}
		return rencontresEquipeTournoi;
	}


	public int nbMatchsGagnes(Tournoi t) {
		List<Rencontre> rencontresTournoi = t.getRencontres();
		List<Rencontre> rencontresGagnees = new LinkedList<>();
		for (Rencontre r : rencontresTournoi) {
			//if(r.getEquipeGagnante().equals(this)) { //ou l'id
			if(r.getEquipeGagnante().getCompte().getIdentifiant().equals(this.getCompte().getIdentifiant())) {
				rencontresGagnees.add(r);
			}
		}
		return rencontresGagnees.size();
	}
	

	public int nbMatchsPerdus(Tournoi t) {
		List<Rencontre> rencontresTournoi = t.getRencontres();
		List<Rencontre> rencontresPerdues = new LinkedList<>();
		for (Rencontre r : rencontresTournoi) {

			if(!r.getEquipeGagnante().getCompte().getIdentifiant().equals(this.getCompte().getIdentifiant())) {
				rencontresPerdues.add(r);
			}
		}
		return rencontresPerdues.size();
	}

	public int calculPointsTournoi(Tournoi t) {

		Categorie c = t.getCategorie();
		int position = this.getPositionTournoi(t);
		int nbMatchsGagnes = this.nbMatchsGagnes(t);

		int pointsGagnes=0;

		if (position == 1) {
			pointsGagnes=100;
		} else if (position == 2){
			pointsGagnes=60;
		} else if (position == 3){
			pointsGagnes=30;
		} else if (position == 4){
			pointsGagnes=10;
		}

		if (c.name() == "NATIONAL") {
			pointsGagnes *= 2;
		} else if (c.name() == "INTERNATIONAL"){
			pointsGagnes*=3;
		}

		pointsGagnes += 5*nbMatchsGagnes;
		this.points+=pointsGagnes;

		return this.points;
	}

	public int calculPointsTotaux(List<Tournoi> tournois) {
			
		int pointsTotaux = 0;		
		for (Tournoi t : tournois){
			List<Rencontre> rencontres = t.getRencontres();
			for(Rencontre r : rencontres) {
				if(r.getEquipe1().equals(this)||r.getEquipe2().equals(this)) {
					pointsTotaux+=this.calculPointsTournoi(r.getTournoi());
				}
			}
		}
		return pointsTotaux;
	}

	public static Comparator<Equipe> comparateurPositionTournoi(Tournoi t) {
		return (Equipe e1, Equipe e2)->e1.nbMatchsGagnes(t)-e2.nbMatchsGagnes(t);
	}
	
	
	//On a besoin de cette méthode pour déterminer la position d'une équipe dans un tournoi
	public static int log2(int x) {
		return (int) (Math.log(x) / Math.log(2));
	}
	
	public static String gererateId(String nom) {
		return nom.toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
	}
}



