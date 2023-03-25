package modele;

import java.time.LocalTime;
import java.util.Comparator;

import com.modeliosoft.modelio.javadesigner.annotations.objid;


public class Rencontre {

    private Equipe equipeGagnante;


    private Arbitre arbitre;


    private Equipe equipe2;


    private Equipe equipe1;

	private String heure;
	
	private Tournoi tournoi;
	

    public Rencontre(Equipe equipe1, Equipe equipe2, Tournoi tournoi, String heure, Arbitre arbitre) {
    	this.equipe1 = equipe1;
    	this.equipe2 = equipe2;
    	this.tournoi=tournoi;
    	this.heure=heure;
    	this.arbitre = arbitre; 
    }
    
    public void entrerResultat(int equipeGagnante) {
    	if (equipeGagnante == 1) {
    		this.equipeGagnante = equipe1;	
    	} else {
    		this.equipeGagnante = equipe2;
    	}
    }
    
    public int getResultat() {
    	if(this.equipeGagnante==this.equipe1) {
    		return 1;
    	} else {
    		if (this.equipeGagnante==this.equipe1) { 
    			return 2;
    		} else {
    			return 0;
    		}
    	}
    }
    
    public void setHeure(String heure) {
    	this.heure=heure;
    }
    
    public String getHeure() {
    	return this.heure;
    }

	public Arbitre getArbitre() {
		return this.arbitre;
	}

	public void setEquipe1(Equipe e1) {
		this.equipe1=e1;
	}

	public Equipe getEquipe1() {
		return this.equipe1;
	}
	
	public void setEquipe2(Equipe e2) {
		this.equipe2=e2;
	}
	
	public Equipe getEquipe2() {
		return this.equipe2;
	}

	public void setEquipeGagnante(Equipe equipeGagnante) {
		this.equipeGagnante = equipeGagnante;
	}

	public void setArbitre(Arbitre arbitre) {
		this.arbitre = arbitre;
	}

	public Equipe getEquipeGagnante() {
		return equipeGagnante;
	}

	public Tournoi getTournoi() {
		return tournoi;
	}
	
	public static Comparator<Rencontre> comparatorHeureRencontre() {		
		return (Rencontre r1, Rencontre r2)->LocalTime.parse(r1.getHeure()).compareTo(LocalTime.parse(r2.getHeure()));
	}
}
