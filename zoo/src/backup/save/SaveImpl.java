package backup.save;

import exception.name.EmptyNameException;
import java.io.FileOutputStream;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import zoo.IZoo;
import zoo.animal.Animal;
import zoo.paddock.IPaddock;

/**
 *
 * @author doyenm
 */
public class SaveImpl implements Save {

    public static final class FriendSave {

        private FriendSave() {
        }
    }
    private static final FriendSave friendSave = new FriendSave();

    @Override
    public void saveZoo(IZoo zoo, String fileName) throws EmptyNameException {
        Element zooEl = createElementZoo(zoo);
        org.jdom2.Document doc = new Document(zooEl);
        zooEl.addContent(createElementPaddocks(zoo));
        saveInFile(doc, createFileName(fileName));
    }

    /**
     * Create the name of the file in wich the backup of the zoo will be saved
     *
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

    public Element createElementWithText(String name, String value) {
        Element el = new Element(name);
        el.setText(value);
        return el;
    }

    public Attribute createAttribute(String name, String value) {
        return new Attribute(name, value);
    }

    public Element createElementPaddocks(IZoo zoo) {
        Element el = new Element("paddocks");
        zoo.getPaddocks(friendSave).entrySet().stream().forEach((pad) -> {
            el.addContent(createElementPaddock((IPaddock) pad.getValue()));
        });
        return el;
    }

    public Element createElementPaddock(IPaddock pad) {
        Element el = new Element("paddock");
        el.setAttribute(createAttribute("name", pad.getName()));
        el.addContent(createElementWithText("x", Integer.toString(pad.getCoordinates().getX())));
        el.addContent(createElementWithText("y", Integer.toString(pad.getCoordinates().getY())));
        el.addContent(createElementWithText("width", Integer.toString(pad.getCoordinates().getWidth())));
        el.addContent(createElementWithText("height", Integer.toString(pad.getCoordinates().getHeight())));
        // el.addContent(createElementWithText("biome", pad.getBiome());
        el.addContent(createElementAnimals(pad));
        return el;
    }

    public Element createElementAnimals(IPaddock pad) {
        Element el = new Element("animals");
        pad.getAnimals(friendSave).entrySet().stream().forEach((animal) -> {
            el.addContent(createElementAnimal((Animal) animal.getValue()));
        });
        return el;
    }

    public Element createElementAnimal(Animal animal) {
        Element el = new Element("animal");
        el.setAttribute(createAttribute("name", animal.getName()));
        el.addContent(createElementWithText("specie", animal.getSpecie().getNames().getEnglishName()));
        el.addContent(createElementWithText("sex", animal.getSex().toString()));
        return el;
    }

    public Element createElementDimensionsZoo(int width, int height) {
        Element dimEl = new Element("dimensions");
        dimEl.addContent(createElementWithText("width", Integer.toString(width)));
        dimEl.addContent(createElementWithText("height", Integer.toString(height)));
        return dimEl;
    }

    public Element createElementZoo(IZoo zoo){
        Element zooEl = new Element("zoo");
        zooEl.setAttribute(createAttribute("name", zoo.getName(friendSave).trim()));
        zooEl.addContent(createElementDimensionsZoo(zoo.getWidth(friendSave), zoo.getHeight(friendSave)));
        return zooEl;
    }
}
