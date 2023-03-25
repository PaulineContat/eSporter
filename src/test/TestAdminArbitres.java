package test;

import connexion.ConnexionBDD;
import vues.VueAdminArbitres;

public class TestAdminArbitres {

	public static void main(String[] args) throws Exception {
		ConnexionBDD.connecting();
		VueAdminArbitres vueAdminArbitres = new VueAdminArbitres();
		vueAdminArbitres.setVisible(true);
	}
}
