package modeles;

import connexion.ConnexionBDD;
import modele.Arbitre;
import modele.Jeu;
import vues.VueAdminArbitres;

public class ModeleSupprModifArbitre {

	private Arbitre arbitre;
	private VueAdminArbitres vueAdmin;

	public ModeleSupprModifArbitre(VueAdminArbitres vue, String identifiantArbitre) {
		this.vueAdmin = vue;
		this.arbitre = ConnexionBDD.getArbitreFromIdArbitre(identifiantArbitre);
	}

	public String getPrenom() {
		return this.arbitre.getPrenom();
	}

	public String getNom() {
		return this.arbitre.getNom();
	}

	public String getTel() {
		return this.arbitre.getTel();
	}

	public void modifierArbitre(Arbitre arbitreModifie) {
		this.arbitre = arbitreModifie;
		ConnexionBDD.modifierArbitre(arbitreModifie);
	}

	public String getId() {
		return this.arbitre.getCompte().getIdentifiant();
	}

	public String getMdp() {
		return this.arbitre.getCompte().getMdp();
	}

	public boolean modifEnregistree() {
		Arbitre arbitreBDD = ConnexionBDD.getArbitreFromIdArbitre(this.getId());
		return (arbitreBDD.getNom().equals(this.arbitre.getNom()) && arbitreBDD.getPrenom().equals(this.arbitre.getPrenom()) && arbitreBDD.getTel().equals(this.arbitre.getTel()));
	}
	
	public void refresh() {
		this.vueAdmin.afficherArbitre();
	}

	public void supprimerArbitre() {
		ConnexionBDD.supprimerArbitre(this.arbitre);
	}
}
