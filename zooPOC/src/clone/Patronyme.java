package clone;

/**
 * 
 * http://ydisanto.developpez.com/tutoriels/java/cloneable/
 */
public class Patronyme implements Cloneable {

	public String prenom;
	public String nom;

	public Patronyme(String prenom, String nom) {
		this.prenom = prenom;
		this.nom = nom;
	}

	public Object clone() {
		Object o = null;
		try {
			// On récupère l'instance à renvoyer par l'appel de la 
			// méthode super.clone()
			o = super.clone();
		} catch(CloneNotSupportedException cnse) {
			// Ne devrait jamais arriver car nous implémentons 
			// l'interface Cloneable
			cnse.printStackTrace(System.err);
		}
		// on renvoie le clone
		return o;
	}
}
