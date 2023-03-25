package modele;
import java.util.LinkedList;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

import modele.Compte.Type;


public class Responsable extends Personne {
	
	private LinkedList<Tournoi> tournois;

    public Responsable(String id, String nom, String prenom, String tel, String mdp) {
    	super(Compte.Type.RESPONSABLE, id, nom, prenom, tel, mdp);
    	this.tournois = new LinkedList<Tournoi>();
    }

	public LinkedList<Tournoi> getTournois() {
		return tournois;
	}
    
    public void addTournoi(Tournoi t) {
    	this.tournois.add(t);
    }
}
