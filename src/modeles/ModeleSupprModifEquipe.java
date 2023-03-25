package modeles;

import connexion.ConnexionBDD;
import modele.Equipe;
import vues.VueAdminEquipes;

public class ModeleSupprModifEquipe {

	private VueAdminEquipes vue;
	private Equipe equipe;
	private Equipe equipeModifiee;
	
	public ModeleSupprModifEquipe(Equipe equipe, VueAdminEquipes vue){
		this.equipe=equipe;
		this.vue = vue;
	}
	
	public Equipe getEquipe() {
		return this.equipe;
	}
	
	public void refresh() {
		this.vue.afficherEquipes();
	}
	
	public void modifierEquipe(Equipe equipeModifiee) {
		this.equipeModifiee = equipeModifiee;
		ConnexionBDD.modifierEquipe(equipeModifiee);
	}
	
	public void supprimerEquipe() {
		ConnexionBDD.supprimerEquipe(this.equipe);
	}
	
	public boolean modifEnregistree() {
		Equipe equipeBDD = ConnexionBDD.getEquipeFromId(this.equipeModifiee.getCompte().getIdentifiant());
		return (equipeBDD.getNom().equals(this.equipeModifiee.getNom()));
	}
}
