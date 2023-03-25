package modeles;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import connexion.ConnexionBDD;
import modele.Equipe;
import modele.Joueur;
import modele.Tournoi;

public class ModeleAccueilEquipe {

	private Equipe equipe;
	private List<Tournoi> tournois;
	private List<Joueur> joueurs;

	public ModeleAccueilEquipe(String identifiantEquipe) {
		this.equipe = ConnexionBDD.getEquipeFromId(identifiantEquipe);
		this.tournois = ConnexionBDD.getTournois();
		//System.out.println(tournois.get(0).getIdTournoi());
		this.joueurs = ConnexionBDD.getJoueursFromEquipe(this.equipe);
	}

	public String getNomEcurie() {
		return this.equipe.getEcurie().getNom();
	}
	
	public String getNomEquipe() {
		return this.equipe.getNom();
	}
	
	public String getNomJeu() {
		return this.equipe.getJeu().getNom();
	}
	
	public List<Joueur> getJoueurs() {
		return this.joueurs;
	}
	
	public List<Tournoi> getTournois() {
		return this.tournois;
	}

	public boolean peutInscrire(Tournoi tournoi) {
		Date dateLimiteTournoi = null;
		Date dateAujourdhui = new Date();
		try {
			dateLimiteTournoi=new SimpleDateFormat("dd/MM/yyyy").parse(tournoi.getDateLimiteInscription());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return tournoi.getJeu().getNom().equals(equipe.getJeu().getNom()) && dateLimiteTournoi.after(dateAujourdhui);
	}

	public Tournoi getTournoiOfIndex(int selectedIndex) {
		return this.tournois.get(selectedIndex);
	}

	public boolean estInscrit(Tournoi tournoi) {
		return ConnexionBDD.estInscrit(this.equipe, tournoi);
	}

	public void inscrireEquipeTournoi(Tournoi tournoi) {
		ConnexionBDD.inscrireEquipeTournoi(this.equipe, tournoi);
	}
}
