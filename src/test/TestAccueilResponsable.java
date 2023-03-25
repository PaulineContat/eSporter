package test;

import java.sql.SQLException;

import connexion.ConnexionBDD;
import vues.VueAccueilResponsable;

public class TestAccueilResponsable {
	public static void main(String[] args) throws SQLException {
		ConnexionBDD.connecting();
		VueAccueilResponsable a = new VueAccueilResponsable("gwen.jesaistout");
		a.setVisible(true);
	}
}
