package zoo.animal.specie;

import exception.name.UnknownNameException;
import java.io.File;
import java.io.IOException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaders;
import zoo.animal.Names;
import zoo.animal.conservation.ConservationStatus;
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
        BiomeAttributes biome = biomeParser(root);
        int diet = dietParser(root);
        int region = ecoregionParser(root);
        int family = familyParser(root);
        FeedingAttributes feeding = feedingParser(root);
        ReproductionAttributes repro = reproductionParser(root);
        LifeSpanAttributes lifeSpan = lifeSpanParser(root);
        ConservationStatus conservation = conservationParser(root);
        SocialAttributes social = socialParser(root);
        TerritoryAttributes territory = territoryParser(root);
        Specie spec = new Specie(names, biome, feeding, diet, repro, lifeSpan,
                conservation, social, territory, region, family);
        return spec;
    }

    private static Names namesParser(Element root) {
        Element nameEl = root.getChild("names");
        return new Names(nameEl.getChildText("fr"), nameEl.getChildText("en"),
                nameEl.getChildText("scientific"));
    }

    private static BiomeAttributes biomeParser(Element root) {
        BiomeAttributes biome = new BiomeAttributes(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        return biome;
    }

    private static FeedingAttributes feedingParser(Element root) {
        FeedingAttributes feeding = new FeedingAttributes(0.0);
        return feeding;
    }

    private static ReproductionAttributes reproductionParser(Element root) {
        Element reproEl = root.getChild("reproduction");
        return new ReproductionAttributes(Integer.parseInt(reproEl.getChildText("femaleMaturity")),
                Integer.parseInt(reproEl.getChildText("maleMaturity")),
                Double.parseDouble(reproEl.getChildText("gestationFrequency")),
                Integer.parseInt(reproEl.getChildText("litterSize")));
    }

    private static LifeSpanAttributes lifeSpanParser(Element root) {
        Element lifeEl = root.getChild("lifespan");
        return new LifeSpanAttributes(Integer.parseInt(lifeEl.getChildText("femaleLifespan")),
                Integer.parseInt(lifeEl.getChildText("maleLifespan")));
    }

    private static ConservationStatus conservationParser(Element root) throws UnknownNameException {
        Element consEl = root.getChild("general");
        return ConservationStatus.UNKNOWN.findById(Integer.parseInt(consEl.getChildText("uicn")));
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

    private static int dietParser(Element root) {
        return Integer.parseInt(root.getChild("feeding").getChildText("diet"));
    }

}
