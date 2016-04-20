/**
 * According to : 
 * http://cynober.developpez.com/tutoriel/java/xml/jdom/
 * and 
 * http://www.developpez.net/forums/d1488867/java/general-java/xml/extraire-informations-filter/
 */

package save.poc;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author doyenm
 */
public class JDOM2 {

    static org.jdom2.Document document;
    static Element root;

    static void displayAll() {
        List listEtudiant = root.getChildren("etudiant");
        Iterator it = listEtudiant.iterator();
        Element current;
        while (it.hasNext()) {
            current = (Element) it.next();
            System.out.println(current.getChild("nom").getText());
        }
    }

    static void displayFilter() {
        root.getChildren("etudiant").stream()
                .filter(e -> e.getChild("nom").getTextTrim().equals("CynO"))
                .filter(e -> {
                    Element prenoms = e.getChild("prenoms");
                    return prenoms != null && prenoms.getChildren("prenom").stream()
                    .anyMatch(p -> p.getTextTrim().equals("Laurent"));
                })
                .forEach(e -> System.out.println(e.getAttributeValue("classe")));
    }

    public static void main(String[] args) {
        SAXBuilder sax = new SAXBuilder();
        try {
            document = sax.build(new File("JDOM2.xml"));

        } catch (JDOMException | IOException e) {
            e.printStackTrace();
        }
        root = document.getRootElement();
        displayFilter();
    }
}
