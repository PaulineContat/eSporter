package modele;

public class Arbitre extends Personne {

    public Arbitre(String id, String nom, String prenom, String tel, String mdp) {
    	super(Compte.Type.ARBITRE, id, nom, prenom, tel, mdp);
    }

	@Override
	public int hashCode() {
		return super.hashCode();
	}

}
