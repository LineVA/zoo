package backup.load;

import exception.IncorrectLoadException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
import zoo.animal.personality.PersonalityAttributes;
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
     * Parse the characteristics of the zoo (not including paddocks, 
     * animals, keepers and language)
     *
     * @return The corresponding FakeZoo
     * @throws IOException
     * @throws JDOMException if there is a problem inside the XML to parse
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
     * @return The list of FakePaddocks extracted from the parsed file
     */
    public ArrayList<FakePaddock> parserPaddocks() {
        Element paddocksEl = zooEl.getChild("paddocks");
        List<Element> paddocksElList = paddocksEl.getChildren("paddock");
        ArrayList<FakePaddock> paddocksList = new ArrayList<>();
        for (Element tmpPadEl : paddocksElList) {
            paddocksList.add(new FakePaddock(tmpPadEl.getAttributeValue("name"),
                    Integer.parseInt(tmpPadEl.getChildText("x")),
                    Integer.parseInt(tmpPadEl.getChildText("y")),
                    Integer.parseInt(tmpPadEl.getChildText("width")),
                    Integer.parseInt(tmpPadEl.getChildText("height")),
                    Integer.parseInt(tmpPadEl.getChildText("biome")),
                    Integer.parseInt(tmpPadEl.getChildText("paddockType"))));
        }
        return paddocksList;
    }

    /**
     * Extract the list of elements "animals" from the parsed file
     * @return the list
     */
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
    public ArrayList<FakeAnimal> parserAnimals() throws IncorrectLoadException {
        ArrayList<Element> animalsElList = findElementsAnimals();
        ArrayList<FakeAnimal> animalList = new ArrayList<>();
        for (Element el : animalsElList) {
            animalList.addAll(parserAnimal(el));
        }
        return animalList;
    }

    /**
     * Extract the list of fake animals inside an element "animals"
     * @param animalsEl the "animals" element to parse
     * @returnthe correpsonding list
     * @throws IncorrectLoadException if a parsed element does not respect the limitations 
     * of the animal attributes 
     */
    private ArrayList<FakeAnimal> parserAnimal(Element animalsEl) throws IncorrectLoadException {
        List<Element> animalsElList = animalsEl.getChildren("animal");
        ArrayList<FakeAnimal> animalsList = new ArrayList<>();
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
        for(Element tmpAnimalEl : animalsElList){
        PersonalityAttributes personality;
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
            personality = parserPersonalityAttributes(tmpAnimalEl);
            animalsList.add(new FakeAnimal(spec,
                    tmpAnimalEl.getAttributeValue("name"),
                    pad, sex, age, biome, optFeed, actualFeed, diet, repro,
                    life, social, territory, personality));
        }
        return animalsList;
    }

    /**
     * @deprecated 
     */
    private BiomeAttributes parserBiomeAttributes(Element tmpAnimalEl) {
        BiomeAttributes biome = new BiomeAttributes(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        return biome;
    }

    /**
     * Parse the optimal feeding attributes of an animal
     * @param tmpAnimalEl the "animal" element to parse
     * @return the corresponding attributes
     * @throws IncorrectLoadException if the values of the attributes are incorrect
     */
    private FeedingAttributes parserOptimalFeedingAttributes(Element tmpAnimalEl)
            throws IncorrectLoadException {
        Element feedEl = tmpAnimalEl.getChild("optimalFeedingAttributes");
        return new FeedingAttributes(
                Double.parseDouble(feedEl.getChildText("quantity")));
    }

        /**
     * Parse the actual feeding attributes of an animal
     * @param tmpAnimalEl the "animal" element to parse
     * @return the corresponding attributes
     * @throws IncorrectLoadException if the values of the attributes are incorrect
     */
    private FeedingAttributes parserActualFeedingAttributes(Element tmpAnimalEl)
            throws IncorrectLoadException {
        Element feedEl = tmpAnimalEl.getChild("actualFeedingAttributes");
        return new FeedingAttributes(
                Double.parseDouble(feedEl.getChildText("quantity")));
    }

        /**
     * Parse the dietof an animal
     * @param tmpAnimalEl the "animal" element to parse
     * @return the corresponding attributes
     */
    private int parserDiet(Element tmpAnimalEl) {
        Element feedEl = tmpAnimalEl.getChild("actualFeedingAttributes");
        return Integer.parseInt(feedEl.getChildText("diet"));
    }

        /**
     * Parse the reproduction attributes of an animal
     * @param tmpAnimalEl the "animal" element to parse
     * @return the corresponding attributes
     * @throws IncorrectLoadException if the values of the attributes are incorrect
     */
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

        /**
     * Parse the lifespan attributes of an animal
     * @param tmpAnimalEl the "animal" element to parse
     * @return the corresponding attributes
     * @throws IncorrectLoadException if the values of the attributes are incorrect
     */
    private LifeSpanLightAttributes parserLifeSpanAttributes(Element tmpAnimalEl)
            throws IncorrectLoadException {
        Element lifeEl = tmpAnimalEl.getChild("actualLifeSpanAttributes");
        LifeSpanLightAttributes life = new LifeSpanLightAttributes(
                Integer.parseInt(lifeEl.getChildText("lifeSpan")));
        return life;
    }

        /**
     * Parse the optimal social attributes of an animal
     * @param tmpAnimalEl the "animal" element to parse
     * @return the corresponding attributes
     * @throws IncorrectLoadException if the values of the attributes are incorrect
     */
    private SocialAttributes parserSocialAttributes(Element tmpAnimalEl) throws IncorrectLoadException {
        Element socialEl = tmpAnimalEl.getChild("optimalSocialAttributes");
        SocialAttributes social = new SocialAttributes(
                Integer.parseInt(socialEl.getChildText("groupSize")));
        return social;
    }

        /**
     * Parse the optimal territory attributes of an animal
     * @param tmpAnimalEl the "animal" element to parse
     * @return the corresponding attributes
     * @throws IncorrectLoadException if the values of the attributes are incorrect
     */
    private TerritoryAttributes parserTerritoryAttributes(Element tmpAnimalEl) throws IncorrectLoadException {
        Element territoryEl = tmpAnimalEl.getChild("optimalTerritoryAttributes");
        TerritoryAttributes territory = new TerritoryAttributes(
                Double.parseDouble(territoryEl.getChildText("territorySize")));
        return territory;
    }

        /**
     * Parse the personality attributes of an animal
     * @param tmpAnimalEl the "animal" element to parse
     * @return the corresponding attributes
     * @throws IncorrectLoadException if the values of the attributes are incorrect
     */
    private PersonalityAttributes parserPersonalityAttributes(Element tmpAnimalEl) {
        Element persoEl = tmpAnimalEl.getChild("personality");
        return new PersonalityAttributes(
                Double.parseDouble(persoEl.getChildText("bravery")));
    }

    /**
     * Extract the list of fake keepers of the zoo
     * @return the list of fake animal keepers in the parsed file
     */
    public ArrayList<FakeAnimalKeeper> parserAnimalKeepers() {
        ArrayList<FakeAnimalKeeper> keepers = new ArrayList<>();
        Element keepersEl = this.zooEl.getChild("animalKeepers");
        List<Element> keeperListEl = keepersEl.getChildren("animalKeeper");
        for (Element el : keeperListEl) {
            keepers.add(parserAnimalKeeper(el));
        }
        return keepers;
    }

    /**
     * Parse an element "animalKeeper" 
     * @param el the element to parse
     * @return the corresponding fake keeper
     */
    private FakeAnimalKeeper parserAnimalKeeper(Element el) {
        return new FakeAnimalKeeper(el.getAttributeValue("name"),
                this.parserTimedPaddocks(el),
                this.parserTimedTasksPerPaddock(el),
                this.parserManagedFamilies(el),
                this.parserManagedTasks(el));
    }

    /**
     * Parse an element "timedPaddocks"
     * @param el the element to parse
     * @return the corresponding map of the timed paddocks identifying by the name
     */
    private Map<String, Double> parserTimedPaddocks(Element el) {
        HashMap<String, Double> timedPaddocks = new HashMap<>();
        for (Element padEl : el.getChild("timedPaddocks").getChildren("timedPaddock")) {
            timedPaddocks.put(padEl.getChildText("paddock"), Double.parseDouble(padEl.getChildText("time")));
        }
        return timedPaddocks;
    }

    /**
     * Parse an element "timedTasksPerPaddock"
     * @param el the element to parse
     * @return the corresponding map of the timed tasks per paddock identifying by the name and id
     */
    private Map<FakeTaskPaddock, Double> parserTimedTasksPerPaddock(Element el) {
        HashMap<FakeTaskPaddock, Double> timedPaddocks = new HashMap<>();
        for (Element padEl : el.getChild("timedTasksPerPaddock").getChildren("timedTaskPerPaddock")) {
            timedPaddocks.put(
                    new FakeTaskPaddock(padEl.getChildText("paddock"), Integer.parseInt(padEl.getChildText("task"))),
                    Double.parseDouble(padEl.getChildText("time")));
        }
        return timedPaddocks;
    }

    /**
     * Parse an element "managedFamilies"
     * @param el the element to parse
     * @return the corresponding map of the managed families identifying by the id
     */
    private Map<Integer, Double> parserManagedFamilies(Element el) {
        HashMap<Integer, Double> managed = new HashMap<>();
        for (Element managedEl : el.getChild("managedFamilies").getChildren("managedFamily")) {
            managed.put(Integer.parseInt(managedEl.getChildText("family")),
                    Double.parseDouble(managedEl.getChildText("time")));
        }
        return managed;
    }

       /**
     * Parse an element "managedTasks"
     * @param el the element to parse
     * @return the corresponding map of the managed tasks identifying by the id
     */
    private Map<Integer, Double> parserManagedTasks(Element el) {
        HashMap<Integer, Double> managed = new HashMap<>();
        for (Element managedEl : el.getChild("managedTasks").getChildren("managedTask")) {
            managed.put(Integer.parseInt(managedEl.getChildText("task")),
                    Double.parseDouble(managedEl.getChildText("time")));
        }
        return managed;
    }
}
