package modeles;

import java.util.List;

import connexion.ConnexionBDD;
import modele.Arbitre;
import modele.Tournoi;

public class ModeleAdminArbitres {

	private List<Arbitre> arbitres;

	public ModeleAdminArbitres() {
		this.arbitres = ConnexionBDD.getArbitres();
	}

	public List<Arbitre> getArbitres() {
		return this.arbitres;
	}
	
	public Arbitre getArbitreOfIndex(int selectedIndex) {
		return this.arbitres.get(selectedIndex);
	}

	public void updateData() {
		this.arbitres = ConnexionBDD.getArbitres();
	}

}
