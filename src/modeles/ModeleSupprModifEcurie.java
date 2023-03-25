package modeles;

import connexion.ConnexionBDD;
import modele.Ecurie;
import vues.VueAdminEcuries;

public class ModeleSupprModifEcurie {

	private Ecurie ecurie;
	private VueAdminEcuries vue;
	private Ecurie ecurieModifiee;
	
	public ModeleSupprModifEcurie(Ecurie ecurie, VueAdminEcuries vue) {
		this.ecurie = ecurie;
		this.vue = vue;
	}
	
	public Ecurie getEcurie() {
		return this.ecurie;
	}
	
	public void refresh() {
		this.vue.afficherEcuries();
	}
	
	public void modifierEcurie(Ecurie ecurieModifiee) {
		this.ecurieModifiee = ecurieModifiee;
		ConnexionBDD.modifierEcurie(ecurieModifiee);
	}
	
	public void supprimerEcurie() {
		ConnexionBDD.supprimerEcurie(this.ecurie);
	}
	
	public boolean modifEnregistree() {
		Ecurie ecurieBDD = ConnexionBDD.getEcurieFromId(this.ecurie.getCompte().getIdentifiant());
		return (ecurieBDD.getNom().equals(this.ecurieModifiee.getNom()) && ecurieBDD.getStatut() == this.ecurieModifiee.getStatut());
	}

}
