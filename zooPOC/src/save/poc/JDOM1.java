package save.poc;

import java.io.FileOutputStream;
import org.jdom2.*;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author doyenm
 */
public class JDOM1 {

    static Element pers = new Element("personnes");
    static org.jdom2.Document document = new Document(pers);

    static void display() {
        try {
            //On utilise ici un affichage classique avec getPrettyFormat()
            XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
            sortie.output(document, System.out);
        } catch (java.io.IOException e) {
        }
    }

    static void save(String fichier) {
        try {
            //On utilise ici un affichage classique avec getPrettyFormat()
            XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
      //Remarquez qu'il suffit simplement de créer une instance de FileOutputStream
            //avec en argument le nom du fichier pour effectuer la sérialisation.
            sortie.output(document, new FileOutputStream(fichier));
        } catch (java.io.IOException e) {
        }
    }

    public static void main(String[] args) {
        Element etudiant = new Element("etudiant");
        pers.addContent(etudiant);
        Attribute classe = new Attribute("classe", "P2");
        etudiant.setAttribute(classe);
        Element nom = new Element("nom");
        nom.setText("Line");
        etudiant.addContent(nom);
        display();
        save("./src/save/poc/JDOM1.xml");
    }
}
