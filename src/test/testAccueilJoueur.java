package test;

import connexion.ConnexionBDD;
import vues.VueAccueilJoueur;

public class testAccueilJoueur {

	public static void main(String[] args) throws Exception {
		ConnexionBDD.connecting();
		VueAccueilJoueur vueAccueilJoueur = new VueAccueilJoueur("pauline.contat");
		vueAccueilJoueur.setVisible(true);
	}
}
