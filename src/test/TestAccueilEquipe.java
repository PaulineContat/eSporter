package test;

import connexion.ConnexionBDD;
import vues.VueAccueilEquipe;

public class TestAccueilEquipe {

	public static void main(String[] args) throws Exception {
		ConnexionBDD.connecting();
		VueAccueilEquipe vueAccueilEquipe = new VueAccueilEquipe("equipe1");
		vueAccueilEquipe.setVisible(true);
	}
}
