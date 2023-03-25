package modele;

abstract public class Personne {

    private Compte.Type type;

    private String nom;
    
    private String prenom;

    private String tel;

    private Compte compte;

    public Personne(Compte.Type type, String id, String nom, String prenom, String tel, String mdp) {
    	this.setType(type);
    	this.nom = nom;
    	this.prenom = prenom;
    	this.tel = tel;
    	this.compte = new Compte(type, id , mdp);
    }

	public Compte.Type getType() {
		return type;
	}

	public void setType(Compte.Type type) {
		this.type = type;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Compte getCompte() {
		return compte;
	}

	@Override
	public String toString() {
		return nom + " " + prenom;
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
         
        Personne c = (Personne) o;
         
        return nom.equals(c.nom) && prenom.equals(c.prenom)
                && tel.equals(c.tel)
                	&& compte.equals(c.compte);
    }
	
	public static String generateId(String prenom, String nom) {
		return prenom.toLowerCase().replaceAll("[^a-zA-Z]", "") + "." + nom.toLowerCase().replaceAll("[^a-zA-Z]", "");
	}
}
