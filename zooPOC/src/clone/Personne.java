package clone;

/**
 * 
 * http://ydisanto.developpez.com/tutoriels/java/cloneable/
 */
public class Personne implements Cloneable {

	public Patronyme patronyme;
	public int age;

	public Personne(Patronyme patronyme, int age) {
		this.patronyme = patronyme;
	    this.age = age;
	}
	  
	public Object clone() {
	    Personne personne = null;
	    try {
	    	// On récupère l'instance à renvoyer par l'appel de la 
	      	// méthode super.clone()
	      	personne = (Personne) super.clone();
	    } catch(CloneNotSupportedException cnse) {
	      	// Ne devrait jamais arriver car nous implémentons 
	      	// l'interface Cloneable
	      	cnse.printStackTrace(System.err);
	    }
	    
	    // On clone l'attribut de type Patronyme qui n'est pas immuable.
	    personne.patronyme = (Patronyme) patronyme.clone();
	    
	    // on renvoie le clone
	    return personne;
	}
}
