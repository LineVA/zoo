package backup.load;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
import zoo.animalKeeper.FakeAnimalKeeper;
import zoo.animalKeeper.FakeTaskPaddock;
import zoo.paddock.FakePaddock;
import zoo.paddock.TerritoryAttributes;
import zoo.paddock.biome.BiomeAttributes;

/**
 * The parser of a zoo file
 *
 * @author doyenm
 */
public class ParserBackUp {

    Element zooEl;

    /**
     * The constructor of the parser
     *
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
     *
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
     *
     * @return Code of the language
     */
    public String parserLanguage() {
        return zooEl.getChildText("language");
    }

    /**
     * Method used to parse paddocks
     *
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
     *
     * @return The list of FakeAnimal in the file
     */
    public ArrayList<FakeAnimal> parserAnimals() {
        ArrayList<Element> animalsElList = findElementsAnimals();
        ArrayList<FakeAnimal> animalList = new ArrayList<>();
        animalsElList.stream().forEach((Element el) -> animalList.addAll(parserAnimal(el)));
        return animalList;
    }

    private ArrayList<FakeAnimal> parserAnimal(Element animalsEl) {
        List<Element> animalsElList = animalsEl.getChildren("animal");
        ArrayList<FakeAnimal> animalsList = new ArrayList<>();
        Iterator it = animalsElList.iterator();
        Element tmpAnimalEl;
        String spec;
        String pad = animalsEl.getParentElement().getAttributeValue("name");
        String sex;
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
            sex = tmpAnimalEl.getChildText("sex");
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

    private LifeSpanLightAttributes parserLifeSpanAttributes(Element tmpAnimalEl) {
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

    public ArrayList<FakeAnimalKeeper> parserAnimalKeepers() {
        ArrayList<FakeAnimalKeeper> keepers = new ArrayList<>();
        Element keepersEl = this.zooEl.getChild("animalKeepers");
        List<Element> keeperListEl = keepersEl.getChildren("animalKeeper");
        for (Element el : keeperListEl) {
            keepers.add(parserAnimalKeeper(el));
        }
        return keepers;
    }

    private FakeAnimalKeeper parserAnimalKeeper(Element el) {
        return new FakeAnimalKeeper(el.getAttributeValue("name"),
                this.parserTimedPaddocks(el),
                this.parserTimedTasksPerPaddock(el),
                this.parserManagedFamilies(el),
                this.parserManagedTasks(el));
    }

    private Map<String, Double> parserTimedPaddocks(Element el) {
        HashMap<String, Double> timedPaddocks = new HashMap<>();
        for (Element padEl : el.getChild("timedPaddocks").getChildren("timedPaddock")) {
            timedPaddocks.put(padEl.getChildText("paddock"), Double.parseDouble(padEl.getChildText("time")));
        }
        return timedPaddocks;
    }

    private Map<FakeTaskPaddock, Double> parserTimedTasksPerPaddock(Element el) {
        HashMap<FakeTaskPaddock, Double> timedPaddocks = new HashMap<>();
        for (Element padEl : el.getChild("timedTasksPerPaddock").getChildren("timedTaskPerPaddock")) {
            timedPaddocks.put(
                    new FakeTaskPaddock(padEl.getChildText("paddock"), Integer.parseInt(padEl.getChildText("task"))),
                    Double.parseDouble(padEl.getChildText("time")));
        }
        return timedPaddocks;
    }

    private Map<Integer, Double> parserManagedFamilies(Element el) {
        HashMap<Integer, Double> managed = new HashMap<>();
        for (Element managedEl : el.getChild("managedFamilies").getChildren("managedFamily")) {
            managed.put(Integer.parseInt(managedEl.getChildText("family")),
                    Double.parseDouble(managedEl.getChildText("time")));
        }
        return managed;
    }

    private Map<Integer, Double> parserManagedTasks(Element el) {
        HashMap<Integer, Double> managed = new HashMap<>();
        for (Element managedEl : el.getChild("managedTasks").getChildren("managedTask")) {
            managed.put(Integer.parseInt(managedEl.getChildText("task")),
                    Double.parseDouble(managedEl.getChildText("time")));
        }
        return managed;
    }
}
