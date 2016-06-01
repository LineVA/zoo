package save;

import exception.IncorrectDimensionsException;
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
import zoo.PaddockCoordinates;
import zoo.Zoo;

/**
 *
 * @author doyenm
 */
public class Save {

    /**
     * Create the name of the file in wich the backup of the zoo will be saved
     * @param name the name of the file without any extension
     * @return the name of the file with the extension ".xml"
     * @throws EmptyNameException throws if the name is empty
     */
    public String createFileName(String name) throws EmptyNameException {
        if (name.trim().equals("")) {
            throw new EmptyNameException("This name's zoo is the empty string");
        }
        return "./gameBackUps/" + name + ".xml";
    }

    
    public Element createElementCoordinate(String name, int value) {
        Element coorEl = new Element(name);
        coorEl.setText(Integer.toString(value));
        return coorEl;
    }

    public Element createElementDimensionsZoo(int width, int height) {
        Element dimEl = new Element("dimensions");
        dimEl.addContent(createElementCoordinate("width", width));
        dimEl.addContent(createElementCoordinate("height", height));
        return dimEl;
    }

    public Element createElementZoo(Zoo zoo) throws EmptyNameException {
        if (zoo.getName().trim().equals("")) {
            throw new EmptyNameException("This name's zoo is the empty string");
        }
        Element zooEl = new Element("zoo");
        Attribute nameAtt = new Attribute("name", zoo.getName());
        zooEl.setAttribute(nameAtt);
        zooEl.addContent(createElementDimensionsZoo(zoo.getWidth(), zoo.getHeight()));
        return zooEl;
    }

    public Element createElementNamePaddock(String name) throws EmptyNameException {
        if (name.trim().equals("")) {
            throw new EmptyNameException("This name's zoo is the empty string");
        }
        Element nameEl = new Element("name");
        nameEl.setText(name);
        return nameEl;
    }

    public Element createElementCoordinatesPaddock(PaddockCoordinates coor) throws EmptyNameException, IncorrectDimensionsException {
        if (coor == null) {
            throw new IncorrectDimensionsException("The coordinates of this paddock are null.");
        }
        Element coorEl = new Element("coordinates");
        coorEl.addContent(createElementCoordinate("x", coor.getX()));
        coorEl.addContent(createElementCoordinate("y", coor.getY()));
        coorEl.addContent(createElementCoordinate("width", coor.getWidth()));
        coorEl.addContent(createElementCoordinate("height", coor.getHeight()));
        return coorEl;
    }

    public Element createElementPaddock(Paddock pad) throws EmptyNameException, IncorrectDimensionsException {
        Element padEl = new Element("paddock");
        padEl.addContent(createElementNamePaddock(pad.getName()));
        padEl.addContent(createElementCoordinatesPaddock(pad.getCoordinates()));
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
            } catch (EmptyNameException | IncorrectDimensionsException ex) {
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
