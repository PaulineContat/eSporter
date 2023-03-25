package modeles;

import java.util.List;

import connexion.ConnexionBDD;
import modele.Ecurie;

public class ModeleAdminEcuries {

	private List<Ecurie> ecuries;
	
	public ModeleAdminEcuries() {
		this.ecuries = ConnexionBDD.getEcuries();
	}
	
	public List<Ecurie> getEcuries(){
		return this.ecuries;
	}

	public void updateData() {
		this.ecuries = ConnexionBDD.getEcuries();
	}
}