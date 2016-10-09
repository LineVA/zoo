package backup.load;

import exception.IncorrectDataException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import zoo.FakeZoo;
import zoo.animal.FakeAnimal;
import zoo.animal.death.LifeSpanLightAttributes;
import zoo.animal.feeding.FeedingAttributes;
import zoo.animal.reproduction.ReproductionAttributes;
import zoo.animal.social.SocialAttributes;
import zoo.paddock.FakePaddock;
import zoo.paddock.TerritoryAttributes;
import zoo.paddock.biome.BiomeAttributes;

/**
 * The parser of a zoo file
 * @author doyenm
 */
public class ParserBackUp {

    Element zooEl;

    /**
     * The constructor of the parser
     * @param file the file to parse
     * @throws IOException
     * @throws JDOMException 
     */
    public ParserBackUp(File file) throws IOException, JDOMException {
        SAXBuilder sax = new SAXBuilder();
        Document document;
        document = sax.build(file);
        this.zooEl = document.getRootElement();
    }

    /**
     * Parse the characteristics of the zoo (not including paddocks and animals)
     * @return The corresponding FakeZoo
     * @throws IOException
     * @throws JDOMException 
     */
    public FakeZoo parserZoo() throws IOException, JDOMException {
        Element dimEl = zooEl.getChild("dimensions");
        return new FakeZoo(zooEl.getAttributeValue("name"),
                Integer.parseInt(dimEl.getChild("width").getText()),
                Integer.parseInt(dimEl.getChild("height").getText()),
                Integer.parseInt(zooEl.getChild("age").getText()),
                Integer.parseInt(zooEl.getChild("monthsPerEvaluation").getText()),
                Integer.parseInt(zooEl.getChild("horizon").getText())
        );
    }
    
    /**
     * Parse the language of the save
     * @return Code of the language
     */
    public String parserLanguage(){
        return zooEl.getChildText("language");
    }

    /**
     * Method used to parse paddocks
     * @return The list of FakePaddocks
     */
    public ArrayList<FakePaddock> parserPaddocks() {
        Element paddocksEl = zooEl.getChild("paddocks");
        List<Element> paddocksElList = paddocksEl.getChildren("paddock");
        ArrayList<FakePaddock> paddocksList = new ArrayList<>();
        Iterator it = paddocksElList.iterator();
        Element tmpPadEl;
        while (it.hasNext()) {
            tmpPadEl = (Element) it.next();
            paddocksList.add(new FakePaddock(tmpPadEl.getAttributeValue("name"),
                    Integer.parseInt(tmpPadEl.getChildText("x")),
                    Integer.parseInt(tmpPadEl.getChildText("y")),
                    Integer.parseInt(tmpPadEl.getChildText("width")),
                    Integer.parseInt(tmpPadEl.getChildText("height")),
                    Integer.parseInt(tmpPadEl.getChildText("biome")),
                    Integer.parseInt(tmpPadEl.getChildText("paddockType"))
            ));
        }
        return paddocksList;
    }

    private ArrayList<Element> findElementsAnimals() {
        ArrayList<Element> animalsEl = new ArrayList<>();
        Element paddocksEl = zooEl.getChild("paddocks");
        List<Element> paddocksElList = paddocksEl.getChildren("paddock");
        paddocksElList.stream().forEach((Element el) -> animalsEl.add(el.getChild("animals")));
        return animalsEl;
    }

    /**
     * The method used to parse the animals element
     * @return The list of FakeAnimal in the file
     */
    public ArrayList<FakeAnimal> parserAnimals() throws IncorrectDataException{
        ArrayList<Element> animalsElList = findElementsAnimals();
        ArrayList<FakeAnimal> animalList = new ArrayList<>();
        for(Element el : animalsElList){
            animalList.addAll(parserAnimal(el));
        }
        return animalList;
    }

    private ArrayList<FakeAnimal> parserAnimal(Element animalsEl) throws IncorrectDataException {
        List<Element> animalsElList = animalsEl.getChildren("animal");
        ArrayList<FakeAnimal> animalsList = new ArrayList<>();
        Iterator it = animalsElList.iterator();
        Element tmpAnimalEl;
        String spec;
        String pad = animalsEl.getParentElement().getAttributeValue("name");
        int sex;
        int age;
        BiomeAttributes biome;
        FeedingAttributes optFeed;
        FeedingAttributes actualFeed;
        int diet;
        ReproductionAttributes repro;
        LifeSpanLightAttributes life;
        SocialAttributes social;
        TerritoryAttributes territory;
        while (it.hasNext()) {
            tmpAnimalEl = (Element) it.next();
            spec = tmpAnimalEl.getChildText("specie");
            sex = Integer.parseInt(tmpAnimalEl.getChildText("sex"));
            age = Integer.parseInt(tmpAnimalEl.getChildText("age"));
            biome = parserBiomeAttributes(tmpAnimalEl);
            optFeed = parserOptimalFeedingAttributes(tmpAnimalEl);
            actualFeed = parserActualFeedingAttributes(tmpAnimalEl);
            diet = parserDiet(tmpAnimalEl);
            repro = parserReproductionAttributes(tmpAnimalEl);
            life = parserLifeSpanAttributes(tmpAnimalEl);
            social = parserSocialAttributes(tmpAnimalEl);
            territory = parserTerritoryAttributes(tmpAnimalEl);
            animalsList.add(new FakeAnimal(spec,
                    tmpAnimalEl.getAttributeValue("name"),
                    pad, sex, age, biome, optFeed, actualFeed, diet, repro,
                    life, social, territory));
        }
        return animalsList;
    }

    private BiomeAttributes parserBiomeAttributes(Element tmpAnimalEl) {
        BiomeAttributes biome = new BiomeAttributes(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        return biome;
    }

    private FeedingAttributes parserOptimalFeedingAttributes(Element tmpAnimalEl) {
        Element feedEl = tmpAnimalEl.getChild("optimalFeedingAttributes");
        return new FeedingAttributes(
                Double.parseDouble(feedEl.getChildText("quantity")));
    }

    private FeedingAttributes parserActualFeedingAttributes(Element tmpAnimalEl) {
        Element feedEl = tmpAnimalEl.getChild("actualFeedingAttributes");
        return new FeedingAttributes(
                Double.parseDouble(feedEl.getChildText("quantity")));
    }

    private int parserDiet(Element tmpAnimalEl) {
        Element feedEl = tmpAnimalEl.getChild("actualFeedingAttributes");
        return Integer.parseInt(feedEl.getChildText("diet"));
    }

    private ReproductionAttributes parserReproductionAttributes(Element tmpAnimalEl) {
        Element reproEl = tmpAnimalEl.getChild("actualReproductionAttributes");
        ReproductionAttributes repro = new ReproductionAttributes(
                Integer.parseInt(reproEl.getChildText("femaleMaturityAge")),
                Integer.parseInt(reproEl.getChildText("maleMaturityAge")),
                Double.parseDouble(reproEl.getChildText("gestationFrequency")),
                Integer.parseInt(reproEl.getChildText("litterSize"))
        );
        return repro;
    }

    private LifeSpanLightAttributes parserLifeSpanAttributes(Element tmpAnimalEl) 
            throws IncorrectDataException {
        Element lifeEl = tmpAnimalEl.getChild("actualLifeSpanAttributes");
        LifeSpanLightAttributes life = new LifeSpanLightAttributes(
                Integer.parseInt(lifeEl.getChildText("lifeSpan")));
        return life;
    }

    private SocialAttributes parserSocialAttributes(Element tmpAnimalEl) {
        Element socialEl = tmpAnimalEl.getChild("optimalSocialAttributes");
        SocialAttributes social = new SocialAttributes(
                Integer.parseInt(socialEl.getChildText("groupSize")));
        return social;
    }

    private TerritoryAttributes parserTerritoryAttributes(Element tmpAnimalEl) {
        Element territoryEl = tmpAnimalEl.getChild("optimalTerritoryAttributes");
        TerritoryAttributes territory = new TerritoryAttributes(
                Double.parseDouble(territoryEl.getChildText("territorySize")));
        return territory;
    }
}
