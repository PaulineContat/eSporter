package modele;
import java.util.ArrayList;
import java.util.List;


public class Ecurie {
	
	public enum Statut {
		ASSOCIATIVE, PROFESSIONNELLE
	}
	

    private String nom;


    private Compte compte;


    private List<Equipe> equipes;
    
    private Statut statut;


    public Ecurie(String id, String nom, String mdp, Statut statut) {
    	this.nom = nom;
    	this.compte = new Compte(Compte.Type.ECURIE, id, mdp);
    	this.equipes = new ArrayList<Equipe>();
    	this.statut=statut;
    }

    public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public Compte getCompte() {
		return this.compte;
	}
	
	public Statut getStatut() {
		return this.statut;
	}
	
	public void setStatut(Statut statut) {
		this.statut = statut;
	}
	
	public List<Equipe> getEquipes() {
		return this.equipes;
	}
	
	public void addEquipe(Equipe equipe) {
		this.equipes.add(equipe);
	}
	
	public void removeEquipe(Equipe equipe) {
		this.equipes.remove(equipe);
	}
	
	public static String generateId(String nom) {
		return nom.toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
	}

}
