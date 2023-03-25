package test;

import connexion.ConnexionBDD;
import modele.Arbitre;
import vues.VueAccueilArbitre;

public class TestAccueilArbitre {

	public static void main(String[] args) throws Exception {
		ConnexionBDD.connecting();
		VueAccueilArbitre vueAccueilArbitre = new VueAccueilArbitre("michelle.mouais");
		vueAccueilArbitre.setVisible(true);
	}
}
