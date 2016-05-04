package clone;

/**
 *
 * @author doyenm
 */
public class Test {

    public static void main(String[] args) {
        Personne personne1 = new Personne(new Patronyme("Jean", "Dupond"), 30);
        Personne personne2 = (Personne) personne1.clone();
        System.out.println("1 : " + personne1);
        System.out.println("2 : " + personne2);
        personne2.patronyme.prenom = "Coco";
        System.out.println("1 : " + personne1.patronyme.prenom);
        System.out.println("2 : " + personne2.patronyme.prenom);
    }
}
