package modele;

public class Gestionnaire extends Personne {

    public Gestionnaire(String id, String nom, String prenom, String tel, String mdp) {
    	super(Compte.Type.GESTIONNAIRE, id, nom, prenom, tel, mdp);
    }
}
