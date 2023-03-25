package modele;
import com.modeliosoft.modelio.javadesigner.annotations.objid;


public class Jeu {
	
	public static final int NB_MAX_JOUEUR = 10;
	
    @objid ("e2951c71-4a19-4b4c-a5fd-aabf646277d0")

    private String idJeu;
    private String nom;
    private int nbJoueurs;

	@objid ("7e3353d9-1303-4138-bfd9-2e52ee6edf42")
    public Jeu(String idJeu, String nom, int nbJoueurs) {
    	this.idJeu=idJeu;
		this.nom = nom;
    	this.nbJoueurs=nbJoueurs;
    }

	public String getIdJeu() {
		return this.idJeu;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public int getNbJoueurs() {
		return this.nbJoueurs;
	}
	
    public void setNbJoueurs(int nbJoueurs) {
		this.nbJoueurs = nbJoueurs;
	}
    
    public static String generateId(String str) {
        String id = str.replaceAll("\s", "").toLowerCase();
        return id;
    }
}
