package modeles;

import java.util.List;

import connexion.ConnexionBDD;
import modele.Responsable;
import modele.Tournoi;

public class ModeleAccueilResponsable {

	private Responsable responsable;

	private List<Tournoi> tournois;

	public ModeleAccueilResponsable(String identifiantResponsable) {
		responsable = ConnexionBDD.getResponsableFromIdResponsable(identifiantResponsable);
		tournois = ConnexionBDD.getTournoisFromResponsable(responsable);
	}
	
	public Responsable getResponsable() {
		return responsable;
	}

	public List<Tournoi> getTournois() {
		return tournois;
	}

	public String getPrenomResponsable() {
		return this.responsable.getPrenom();
	}
	
	public String getNomResponsable() {
		return this.responsable.getNom();
	}
	
	public String getIdentifiantResponsable() {
		return this.responsable.getCompte().getIdentifiant();
	}

}
