package modele;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.modeliosoft.modelio.javadesigner.annotations.objid;


public class Tournoi {

	private String idTournoi;
	
	public enum Categorie {
		LOCAL, NATIONAL, INTERNATIONAL
	}


	private String nom;

	private Categorie categorie;


	private Jeu jeu;

	private String dateDebut;

	private String dateFin;

	private String dateLimiteInscription;

	private String heure;

	private Responsable responsable;

	private Arbitre arbitre;

	private List<Equipe> equipes;

	private List<Rencontre> rencontres;

	public Tournoi(String idTournoi, String nom, Categorie categorie, Jeu jeu, String dateDebut, String dateFin, String dateLimiteInscription, String heure, Responsable responsable) {
		this.idTournoi=idTournoi;
		this.nom = nom;
		this.categorie = categorie;
		this.jeu = jeu;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.dateLimiteInscription = dateLimiteInscription;
		this.heure = heure;
		this.responsable = responsable;
		this.rencontres = new ArrayList<Rencontre>();
		this.equipes = new ArrayList<Equipe>();
	}

	public String getIdTournoi() {
		return this.idTournoi;
	}
	
	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	public String getDateFin() {
		return dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	public Categorie getCategorie() {
		return this.categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Jeu getJeu() {
		return this.jeu;
	}

	public void setJeu(Jeu jeu) {
		this.jeu = jeu;
	}

	public Responsable getResponsable() {
		return this.responsable;
	}

	public void setResponsable(Responsable responsable) {
		this.responsable = responsable;
	}

	public String getDateLimiteInscription() {
		return this.dateLimiteInscription;
	}

	public void setDateLimiteInscription(String dateLimiteInscription) {
		this.dateLimiteInscription = dateLimiteInscription;
	}

	public String getHeure() {
		return heure;
	}

	public void setHeure(String heure) {
		this.heure = heure;
	}

	public List<Equipe> getEquipes() {
		return this.equipes;
	}

	public void addEquipe(Equipe e) throws Exception{
		if (this.equipes.size()==16) {
			throw new Exception("Pas plus de 16 équipes dans un tournoi");
		}
		this.equipes.add(e);
	}

	public void trimEquipes() {
		int nbToRemove = this.equipes.size()%4;
		for (int i = 0; i<nbToRemove; i++) {
			this.equipes.remove(this.equipes.size()-1);
		}

	}

	public List<List<Equipe>> genererPoules() {

		List<Equipe> e = new ArrayList<>();

		e.addAll(this.equipes);
		List<List<Equipe>> poules = new ArrayList<>();
		for (int i = 0; i<e.size()/4; i++) {
			poules.add(new ArrayList<Equipe>());	
		}

		e.sort((a, b) -> b.getPoints() - a.getPoints());

		while (e.size() > 0) {
			for (int i = 0; i<poules.size(); i++) {
				poules.get(i).add(e.get(0));
				e.remove(0);
			}

			Collections.reverse(e);
		}
		for (List<Equipe> poule : poules) {
			poule.sort((a, b) -> b.getPoints() - a.getPoints());
		}
		return poules;

	}

	public List<Rencontre> getRencontres() {
		return this.rencontres;
	}
	
	public String generateId() {
	    String id = this.nom.replaceAll("\s", "").toLowerCase();
	    return id;
	}

	public void generateRencontres(List<Arbitre> arbitres) {

		//Generation premiere phase
		if (this.rencontres.isEmpty()) {
			List<List<Equipe>> poules = this.genererPoules();
			int i = 0;
			for (List<Equipe> equipes : poules) {
				this.rencontres.add(new Rencontre(equipes.get(0), equipes.get(1), this, this.heure, arbitres.get(i%arbitres.size())));
				i++;
				this.rencontres.add(new Rencontre(equipes.get(2), equipes.get(3), this, this.heure, arbitres.get(i%arbitres.size())));
				i++;
			}
		} else { //Generation autre phase
			List<Equipe> gagnants = new ArrayList<>();
			for (Rencontre r:this.rencontres) {
				gagnants.add(r.getEquipeGagnante());
			}
			String[] heureMinuteTab = this.rencontres.get(0).getHeure().split("h");
			this.rencontres = new ArrayList<>();
			for (int i = 0; i<gagnants.size()/2; i++) {
				
				int heure = Integer.valueOf(heureMinuteTab[0]);
				int minute = Integer.valueOf(heureMinuteTab[1]) + 30;
				if (minute >= 60) {
					heure++;
					minute -= 60;
				}
				String heureMinute;
				if (minute >= 10) {
					heureMinute = heure+"h"+minute;
				} else {
					heureMinute = heure+"h0"+minute;
				}

				this.rencontres.add(new Rencontre(gagnants.get(i*2), gagnants.get(i*2+1), this, heureMinute, arbitres.get(i%arbitres.size())));
			}
		}
	}

	public static void main(String[] args) throws Exception {
		Ecurie ecurie = new Ecurie("G1", "mdp123", Ecurie.Statut.ASSOCIATIVE);

		Equipe equipe1 = new Equipe("G2", "mdp123", new Jeu("Valorant", 5), ecurie);
		equipe1.setPoints(8);
		Equipe equipe2 = new Equipe("G3", "mdp123", new Jeu("CSGO", 5), ecurie);
		equipe2.setPoints(12);
		Equipe equipe3 = new Equipe("G4", "mdp123", new Jeu("Spyro", 1), ecurie);
		equipe3.setPoints(18);
		Equipe equipe4 = new Equipe("G5", "mdp123", new Jeu("MinecraftSpeedrunnerVS5hunters", 6), ecurie);
		equipe4.setPoints(7);

		Equipe equipe5 = new Equipe("G6", "mdp123", new Jeu("Valorant", 5), ecurie);
		equipe5.setPoints(11);
		Equipe equipe6 = new Equipe("G7", "mdp123", new Jeu("CSGO", 5), ecurie);
		equipe6.setPoints(8);
		Equipe equipe7 = new Equipe("G8", "mdp123", new Jeu("Spyro", 1), ecurie);
		equipe7.setPoints(18);
		Equipe equipe8 = new Equipe("G9", "mdp123", new Jeu("MinecraftSpeedrunnerVS5hunters", 6), ecurie);
		equipe8.setPoints(2);

		Equipe equipe9 = new Equipe("G10", "mdp123", new Jeu("Valorant", 5), ecurie);
		equipe9.setPoints(2);
		Equipe equipe10 = new Equipe("G11", "mdp123", new Jeu("CSGO", 5), ecurie);
		equipe10.setPoints(6);
		Equipe equipe11 = new Equipe("G12", "mdp123", new Jeu("Spyro", 1), ecurie);
		equipe11.setPoints(9);
		Equipe equipe12 = new Equipe("G13", "mdp123", new Jeu("MinecraftSpeedrunnerVS5hunters", 6), ecurie);
		equipe12.setPoints(16);

		Equipe equipe13 = new Equipe("G14", "mdp123", new Jeu("Valorant", 5), ecurie);
		equipe13.setPoints(9);
		Equipe equipe14 = new Equipe("G15", "mdp123", new Jeu("CSGO", 5), ecurie);
		equipe14.setPoints(17);
		Equipe equipe15 = new Equipe("G16", "mdp123", new Jeu("Spyro", 1), ecurie);
		equipe15.setPoints(1);
		Equipe equipe16 = new Equipe("G17", "mdp123", new Jeu("MinecraftSpeedrunnerVS5hunters", 6), ecurie);
		equipe16.setPoints(9);

		Tournoi tournoi = new Tournoi(null, null, null, null, null, null, "10h30", null);
		tournoi.addEquipe(equipe1);
		tournoi.addEquipe(equipe2);
		tournoi.addEquipe(equipe3);
		tournoi.addEquipe(equipe4);
		tournoi.addEquipe(equipe5);
		tournoi.addEquipe(equipe6);
		tournoi.addEquipe(equipe7);
		tournoi.addEquipe(equipe8);
		tournoi.addEquipe(equipe9);
		tournoi.addEquipe(equipe10);
		tournoi.addEquipe(equipe11);
		tournoi.addEquipe(equipe12);
		tournoi.addEquipe(equipe13);
		tournoi.addEquipe(equipe14);
		tournoi.addEquipe(equipe15);
		tournoi.addEquipe(equipe16);

		List<Arbitre> arbitres = new ArrayList<Arbitre>();
		arbitres.add(null);
		
		tournoi.generateRencontres(arbitres);
		
		System.out.println("PREMIER GENERATION");

		for (Rencontre r: tournoi.getRencontres()) {
			System.out.println(r.getEquipe1().getNom() + ", " + r.getEquipe2().getNom() + ", " + r.getHeure());
			r.setEquipeGagnante(r.getEquipe1());
		}

		tournoi.generateRencontres(arbitres);
		
		System.out.println("\nDEUXIEME GENERATION");
		
		for (Rencontre r: tournoi.getRencontres()) {
			System.out.println(r.getEquipe1().getNom() + ", " + r.getEquipe2().getNom() + ", " + r.getHeure());
			r.setEquipeGagnante(r.getEquipe1());
		}

		tournoi.generateRencontres(arbitres);

		System.out.println("\nTROISIEME GENERATION");
		
		for (Rencontre r: tournoi.getRencontres()) {
			System.out.println(r.getEquipe1().getNom() + ", " + r.getEquipe2().getNom() + ", " + r.getHeure());
			r.setEquipeGagnante(r.getEquipe1());
		}

		tournoi.generateRencontres(arbitres);

		System.out.println("\nQUATRIEME GENERATION");
		
		for (Rencontre r: tournoi.getRencontres()) {
			System.out.println(r.getEquipe1().getNom() + ", " + r.getEquipe2().getNom() + ", " + r.getHeure());
		}



	}



}
