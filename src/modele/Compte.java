package modele;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.modeliosoft.modelio.javadesigner.annotations.objid;


public class Compte {
	
	public enum Type {
		ARBITRE, RESPONSABLE, GESTIONNAIRE, EQUIPE, ECURIE, JOUEUR
	}
	
	private Type type;
	
    @objid ("b4e0cfbb-7095-46b3-8517-d3d3bc78e33f")
    private String identifiant;

    private String mdp;
    
    private int nbEssais;

	// mdp correspond au mot de passe crypt� avec MD5
    public Compte(Type type, String identifiant, String mdp) {
    	this.setType(type);
    	this.identifiant = identifiant;
    	this.mdp = mdp;
    	this.nbEssais = 3;
    }

    public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = Compte.passwordHash(mdp);
	}
	
	public int connexion(String mdp) {
		mdp = Compte.passwordHash(mdp);
		if (this.nbEssais == 0) {
			return -1;
		}
		if (mdp.equals(this.mdp)) {
			this.nbEssais = 3;
			return 1;
		}
		this.nbEssais -= 1;
		return 0;
    	
	}
	
	@Override
    public boolean equals(Object o) {
		
		if (o == null) {
			return false;
		}
		
         if (o == this) {
            return true;
        }
 
        if (!(o instanceof Personne)) {
            return false;
        }
         
        Compte c = (Compte) o;
         
        return identifiant.equals(c.identifiant) && mdp.equals(c.mdp)
                && nbEssais==c.nbEssais;
    }
	
	@Override
	public final int hashCode() {
	    return this.getIdentifiant().hashCode();
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
	public int getNbEssais() {
		return nbEssais;
	}

	public void setNbEssais(int nbEssais) {
		this.nbEssais = nbEssais;
	}

	
	public static String passwordHash(String mdp) {
    	
        try 
        {
          MessageDigest md = MessageDigest.getInstance("MD5");

          md.update(mdp.getBytes());

          byte[] bytes = md.digest();

          StringBuilder sb = new StringBuilder();
          for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
          }

          mdp = sb.toString();
        } catch (NoSuchAlgorithmException e) {
          e.printStackTrace();
        }
        
        return mdp;
    }
}
