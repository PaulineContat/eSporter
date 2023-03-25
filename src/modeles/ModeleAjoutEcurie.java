package modeles;

import connexion.ConnexionBDD;
import modele.Compte;
import modele.Ecurie;
import vues.VueAdminEcuries;

public class ModeleAjoutEcurie {

	private Ecurie ecurieAjoutee;
	private VueAdminEcuries vue;
	
	public ModeleAjoutEcurie(VueAdminEcuries vue) {
		this.vue = vue;
	}
	
	public void ajouterEcurie(String nom, String mdp, Ecurie.Statut statut) {
		this.ecurieAjoutee = new Ecurie(Ecurie.generateId(nom), nom, Compte.passwordHash(mdp), statut);
		ConnexionBDD.enregistrerEcurie(this.ecurieAjoutee);
	}

	public void refresh() {
		this.vue.afficherEcuries();
	}
	
	public boolean idAlreadyExists(String id) {
		return ConnexionBDD.getEcurieFromId(id) != null;
	}
	
	public boolean ajoutEnregistree() {
		Ecurie ecurieBDD = ConnexionBDD.getEcurieFromId(this.ecurieAjoutee.getCompte().getIdentifiant());
		return ecurieBDD != null;
	}

}
