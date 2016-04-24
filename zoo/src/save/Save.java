package save;

import exception.name.EmptyNameException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import zoo.Paddock;
import zoo.Zoo;

/**
 *
 * @author doyenm
 */
public class Save {

    public String createFileName(String name) throws EmptyNameException {
        if (name.trim().equals("")) {
            throw new EmptyNameException("This name's zoo is the empty string");
        }
        return "./gameBackUps/" + name + ".xml";
    }

    public Element createElementZoo(Zoo zoo) throws EmptyNameException {
        if (zoo.getName().trim().equals("")) {
            throw new EmptyNameException("This name's zoo is the empty string");
        }
        Element zooEl = new Element("zoo");
        Attribute nameAtt = new Attribute("name", zoo.getName());
        zooEl.setAttribute(nameAtt);
        return zooEl;
    }

    public Element createElementPaddock(Paddock pad) throws EmptyNameException {
        if (pad.getName().trim().equals("")) {
            throw new EmptyNameException("This name's zoo is the empty string");
        }
        Element padEl = new Element("paddock");
        Element nameEl = new Element("name");
        nameEl.setText(pad.getName());
        padEl.addContent(nameEl);
        return padEl;
    }

    public Element createElementAnimal() {
        return null;
    }

    public Element createElementCaretaker() {
        return null;
    }

    public void saveInFile(Document doc, String fichier) {
        try {
            //On utilise ici un affichage classique avec getPrettyFormat()
            XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
            //Remarquez qu'il suffit simplement de créer une instance de FileOutputStream
            //avec en argument le nom du fichier pour effectuer la sérialisation.
            sortie.output(doc, new FileOutputStream(fichier));
        } catch (java.io.IOException e) {
        }
    }

    public void save(Zoo zoo) throws EmptyNameException {
        HashMap<String, Paddock> paddocks = zoo.getPaddocks();
        Element zooEl = createElementZoo(zoo);
        org.jdom2.Document doc = new Document(zooEl);
        paddocks.entrySet().stream().forEach((pad) -> {
            try {
                zooEl.addContent(createElementPaddock((Paddock) pad.getValue()));
            } catch (EmptyNameException ex) {
                Logger.getLogger(Save.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        try {
            saveInFile(doc, createFileName(zoo.getName()));
        } catch (EmptyNameException ex) {
            ex.printStackTrace();
        }
    }
}
