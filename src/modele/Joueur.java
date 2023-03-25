package modele;

public class Joueur extends Personne {

    private String pseudo;
    private Equipe equipe;

    public Joueur(String id, String nom, String prenom, String tel, String pseudo, String mdp, Equipe equipe) {
    	super(Compte.Type.JOUEUR, id, nom, prenom, tel, mdp);
    	this.pseudo = pseudo;
    	this.equipe=equipe;
    }
	
	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	public Equipe getEquipe() {
		return this.equipe;
	}

}
