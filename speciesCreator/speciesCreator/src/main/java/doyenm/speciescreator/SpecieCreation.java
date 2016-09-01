package doyenm.speciescreator;

import java.io.FileOutputStream;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author doyenm
 */
public class SpecieCreation {

    public void saveSpecie(String english, String french, String scientific,
            String uicn, String family, String ecoregion,
            String femaleMat, String maleMat, String gestation, String litter,
            String leaving, String femaleLife, String maleLife,
            String biome, String territory, String diet, String foodQuantity, String group) {
        Element specieEl = new Element("animal");
//        specieEl.setAttribute(new Attribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance"));
//        specieEl.setAttribute(new Attribute("xsi:noNamespaceSchemaLocation", "file:./resources/species/specie.xsd"));
        org.jdom2.Document doc = new Document(specieEl);
        specieEl.addContent(this.createElementNames(english, french, scientific));
        specieEl.addContent(this.createElementGeneral(uicn, family, ecoregion));
        specieEl.addContent(this.createElementReproduction(femaleMat, maleMat, gestation, litter, leaving));
        specieEl.addContent(this.createElementLifeSpan(femaleLife, maleLife));
        specieEl.addContent(this.createElementTerriory(biome, territory));
        specieEl.addContent(this.createElementFeeding(diet, foodQuantity));
        specieEl.addContent(this.createElementSocial(group));
        saveInFile(doc);
    }

    public Element createElement(String name, String value) {
        return new Element(name).setText(value);
    }

    public Element createElementNames(String english, String french, String scientific) {
        Element names = new Element("names");
        names.addContent(createElement("fr", french));
        names.addContent(createElement("en", english));
        names.addContent(createElement("scientific", scientific));
        return names;
    }

    public Element createElementGeneral(String uicn, String family, String ecoregion) {
        Element general = new Element("general");
        general.addContent(createElement("uicn", uicn));
        general.addContent(createElement("family", family));
        general.addContent(createElement("ecoregion", ecoregion));
        return general;
    }

    public Element createElementReproduction(String femaleMaturity, String maleMaturity, String gestationFrequency,
            String litterSize, String leavingAge) {
        Element reproduction = new Element("reproduction");
        reproduction.addContent(createElement("femaleMaturity", femaleMaturity));
        reproduction.addContent(createElement("maleMaturity", maleMaturity));
        reproduction.addContent(createElement("gestationFrequency", gestationFrequency));
        reproduction.addContent(createElement("litterSize", litterSize));
        reproduction.addContent(createElement("leavingAge", leavingAge));
        return reproduction;
    }

    public Element createElementLifeSpan(String femaleLifeSpan, String maleLifeSpan) {
        Element lifeSpan = new Element("lifespan");
        lifeSpan.addContent(createElement("femaleLifespan", femaleLifeSpan));
        lifeSpan.addContent(createElement("maleLifespan", maleLifeSpan));
        return lifeSpan;
    }

    public Element createElementTerriory(String biome, String territorySize) {
        Element territory = new Element("territory");
        territory.addContent(createElement("biome", biome));
        territory.addContent(createElement("territorySize", territorySize));
        return territory;
    }

    public Element createElementFeeding(String diet, String quantity) {
        Element feeding = new Element("territory");
        feeding.addContent(createElement("diet", diet));
        feeding.addContent(createElement("quantity", quantity));
        return feeding;
    }

    public Element createElementSocial(String groupSize) {
        Element social = new Element("territory");
        social.addContent(createElement("groupSize", groupSize));
        return social;
    }

    public String createFileName(String name) {
        return "./" + name + ".xml";
    }

    public void saveInFile(Document doc) {
        try {
            XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
            sortie.output(doc, new FileOutputStream("./toto.xml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
