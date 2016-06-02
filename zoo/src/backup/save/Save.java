package backup.save;

import exception.name.EmptyNameException;
import zoo.IZoo;

/**
 * @author doyenm
 */

public interface Save {
    public void saveZoo(IZoo zoo, String fileName) throws EmptyNameException;
}


//package save;
//
//import exception.IncorrectDimensionsException;
//import exception.name.EmptyNameException;
//import java.io.FileOutputStream;
//import java.util.HashMap;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import org.jdom2.Attribute;
//import org.jdom2.Document;
//import org.jdom2.Element;
//import org.jdom2.output.Format;
//import org.jdom2.output.XMLOutputter;
//import zoo.IZoo;
//import zoo.paddock.Paddock;
//import zoo.paddock.PaddockCoordinates;
//import zoo.Zoo;
//import zoo.paddock.IPaddock;
//
///**
// *
// * @author doyenm
// */
//public class Save {
//
//    /**
//     * Create the name of the file in wich the backup of the zoo will be saved
//     * @param name the name of the file without any extension
//     * @return the name of the file with the extension ".xml"
//     * @throws EmptyNameException throws if the name is empty
//     */
//    public String createFileName(String name) throws EmptyNameException {
//        if (name.trim().equals("")) {
//            throw new EmptyNameException("This name's zoo is the empty string");
//        }
//        return "./gameBackUps/" + name + ".xml";
//    }
//
//    
//    public Element createElementWithText(String name, String value) {
//        Element el = new Element(name);
//        el.setText(value);
//        return el;
//    }
//    
//    public Attribute createAttribute(String name, String value){
//        return new Attribute(name, value);
//    }
//
//    public Element createElementDimensionsZoo(int width, int height) {
//        Element dimEl = new Element("dimensions");
//        dimEl.addContent(createElementWithText("width", Integer.toString(width)));
//        dimEl.addContent(createElementWithText("height", Integer.toString(height)));
//        return dimEl;
//    }
//
//    public Element createElementZoo(Zoo zoo) throws EmptyNameException {
//        if (zoo.getName().trim().equals("")) {
//            throw new EmptyNameException("This name's zoo is the empty string");
//        }
//        Element zooEl = new Element("zoo");
//        zooEl.setAttribute(createAttribute("name", zoo.getName().trim()));
//        zooEl.addContent(createElementDimensionsZoo(zoo.getWidth(), zoo.getHeight()));
//        return zooEl;
//    }
//
//    public Element createElementNamePaddock(String name) throws EmptyNameException {
//        if (name.trim().equals("")) {
//            throw new EmptyNameException("This name's zoo is the empty string");
//        }
//        return createElementWithText("name", name);
//    }
//
//    public Element createElementCoordinatesPaddock(PaddockCoordinates coor) throws EmptyNameException, IncorrectDimensionsException {
//        if (coor == null) {
//            throw new IncorrectDimensionsException("The coordinates of this paddock are null.");
//        }
//        Element coorEl = new Element("coordinates");
//        coorEl.addContent(createElementWithText("x", Integer.toString(coor.getX())));
//        coorEl.addContent(createElementWithText("y", Integer.toString(coor.getY())));
//        coorEl.addContent(createElementWithText("width", Integer.toString(coor.getWidth())));
//        coorEl.addContent(createElementWithText("height", Integer.toString(coor.getHeight())));
//        return coorEl;
//    }
//
//    public Element createElementPaddock(Paddock pad) throws EmptyNameException, IncorrectDimensionsException {
//        Element padEl = new Element("paddock");
//        padEl.addContent(createElementNamePaddock(pad.getName()));
//        padEl.addContent(createElementCoordinatesPaddock(pad.getCoordinates()));
//        return padEl;
//    }
//
//    public Element createElementAnimal() {
//        return null;
//    }
//
//    public Element createElementCaretaker() {
//        return null;
//    }
//
//    public void saveInFile(Document doc, String fichier) {
//        try {
//            //On utilise ici un affichage classique avec getPrettyFormat()
//            XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
//            //Remarquez qu'il suffit simplement de créer une instance de FileOutputStream
//            //avec en argument le nom du fichier pour effectuer la sérialisation.
//            sortie.output(doc, new FileOutputStream(fichier));
//        } catch (java.io.IOException e) {
//        }
//    }
//
//    public void save(IZoo zoo, String fileName) throws EmptyNameException {
////        HashMap<String, IPaddock> paddocks = zoo.getPaddocks();
////        Element zooEl = createElementZoo(zoo);
////        org.jdom2.Document doc = new Document(zooEl);
////        paddocks.entrySet().stream().forEach((pad) -> {
////            try {
////                zooEl.addContent(createElementPaddock((Paddock) pad.getValue()));
////            } catch (EmptyNameException | IncorrectDimensionsException ex) {
////                Logger.getLogger(Save.class.getName()).log(Level.SEVERE, null, ex);
////            }
////        });
////        try {
////            saveInFile(doc, createFileName(zoo.getName()));
////        } catch (EmptyNameException ex) {
////            ex.printStackTrace();
////        }
//    }
//}


