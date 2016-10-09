package zoo.animal.specie;

import exception.IncorrectDataException;
import exception.name.UnknownNameException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaders;
import zoo.animal.Names;
import zoo.animal.death.LifeSpanAttributes;
import zoo.animal.feeding.FeedingAttributes;
import zoo.animal.reproduction.ReproductionAttributes;
import zoo.animal.social.SocialAttributes;
import zoo.paddock.TerritoryAttributes;
import zoo.paddock.biome.BiomeAttributes;

/**
 *
 * @author doyenm
 */
public class ParserSpecie {

    public static Specie mainParserSpecie(File file) throws IOException, JDOMException {
        SAXBuilder sax = new SAXBuilder(XMLReaders.XSDVALIDATING);
        Document document;
        Element root;
        document = sax.build(file);
        root = document.getRootElement();
        Names names = namesParser(root);
        BiomeAttributes biomeAtt = biomeAttributesParser(root);
        ArrayList<Integer> diet = dietParser(root);
        int region = ecoregionParser(root);
        int family = familyParser(root);
        ArrayList<Integer> biomes = biomeParser(root);
        FeedingAttributes feeding = feedingParser(root);
        ReproductionAttributes repro = reproductionParser(root);
        LifeSpanAttributes lifeSpan = lifeSpanParser(root);
        int conservation = conservationParser(root);
        SocialAttributes social = socialParser(root);
        TerritoryAttributes territory = territoryParser(root);
        int size = sizeParser(root);
        ArrayList<Integer> continents = continentParser(root);
        DocumentationURI docu = documentationParser(root);
        Specie spec = new Specie(names, docu, biomeAtt, feeding, diet, repro, lifeSpan,
                conservation, social, territory, region, family, biomes, size, continents);
        return spec;
    }

    private static ArrayList<String> additionalNames(Element root) {
        ArrayList<String> additional = new ArrayList<>();
        List<Element> names = root.getChildren("name");
        for (Element name : names) {
            additional.add(name.getText());
        }
        return additional;
    }

    private static Names namesParser(Element root) {
        Element nameEl = root.getChild("names");
        return new Names(nameEl.getChildText("fr"), nameEl.getChildText("en"),
                nameEl.getChildText("scientific"), additionalNames(nameEl.getChild("additionalFrenchNames")),
                additionalNames(nameEl.getChild("additionalEnglishNames")));
    }

    private static BiomeAttributes biomeAttributesParser(Element root) {
        BiomeAttributes biome = new BiomeAttributes(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        return biome;
    }

    private static ArrayList<Integer> biomeParser(Element root) {
        ArrayList<Integer> biomes = new ArrayList<>();
        Element continentsEl = root.getChild("territory").getChild("biomes");
        List<Element> continentEl = continentsEl.getChildren("biome");
        for (Element cont : continentEl) {
            biomes.add(Integer.parseInt(cont.getText()));
        }
        return biomes;
    }
    
     private static ArrayList<Integer> dietParser(Element root) {
        ArrayList<Integer> diets = new ArrayList<>();
        Element dietsEl = root.getChild("feeding").getChild("diets");
        List<Element> dietEl = dietsEl.getChildren("diet");
        for (Element cont : dietEl) {
            diets.add(Integer.parseInt(cont.getText()));
        }
        return diets;
    }


    private static ArrayList<Integer> continentParser(Element root) {
        ArrayList<Integer> continents = new ArrayList<>();
        Element continentsEl = root.getChild("general").getChild("continents");
        List<Element> continentEl = continentsEl.getChildren("continent");
//        Iterator it = continent.iterator();
//        Element next;
//        while (it.hasNext()) {
//            next = (Element) it.next();
//            continents.add(Integer.parseInt(next.getText()));
//        }
        for (Element cont : continentEl) {
            continents.add(Integer.parseInt(cont.getText()));
        }
        return continents;
    }

    private static FeedingAttributes feedingParser(Element root) {
        Element feedingEl = root.getChild("feeding");
        return new FeedingAttributes(Double.parseDouble(feedingEl.getChildText("quantity")));
    }

    private static ReproductionAttributes reproductionParser(Element root) {
        Element reproEl = root.getChild("reproduction");
        return new ReproductionAttributes(Integer.parseInt(reproEl.getChildText("femaleMaturity")),
                Integer.parseInt(reproEl.getChildText("maleMaturity")),
                Double.parseDouble(reproEl.getChildText("gestationFrequency")),
                Integer.parseInt(reproEl.getChildText("litterSize")));
    }

    private static LifeSpanAttributes lifeSpanParser(Element root) throws IncorrectDataException {
        Element lifeEl = root.getChild("lifespan");
        return new LifeSpanAttributes(Integer.parseInt(lifeEl.getChildText("femaleLifespan")),
                Integer.parseInt(lifeEl.getChildText("maleLifespan")));
    }

    private static int conservationParser(Element root) throws UnknownNameException {
        Element consEl = root.getChild("general");
        return Integer.parseInt(consEl.getChildText("uicn"));
    }

    private static int ecoregionParser(Element root) {
        Element genEl = root.getChild("general");
        return Integer.parseInt(genEl.getChildText("ecoregion"));
    }

    private static int familyParser(Element root) {
        Element genEl = root.getChild("general");
        return Integer.parseInt(genEl.getChildText("family"));
    }

    private static SocialAttributes socialParser(Element root) {
        Element socialEl = root.getChild("social");
        return new SocialAttributes(Integer.parseInt(socialEl.getChildText("groupSize")));
    }

    private static TerritoryAttributes territoryParser(Element root) {
        Element terriEl = root.getChild("territory");
        return new TerritoryAttributes(Double.parseDouble(terriEl.getChildText("territorySize")));
    }

    private static int sizeParser(Element root) {
        return Integer.parseInt(root.getChild("feeding").getChildText("size"));
    }

    private static DocumentationURI documentationParser(Element root) {
        Element docEl = root.getChild("documentation");
        return new DocumentationURI(docEl.getChildText("frenchWikipedia"),
                docEl.getChildText("englishWikipedia"), docEl.getChildText("animalDiversity"));
    }
}
