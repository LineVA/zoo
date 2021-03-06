package backup.load;

import exception.IncorrectLoadException;
import exception.UnexistedFileException;
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
import utils.Constants;
import zoo.FakeZoo;
import zoo.animal.FakeAnimal;
import zoo.animal.death.LifeSpanLightAttributes;
import zoo.animal.feeding.FeedingAttributes;
import zoo.animal.personality.PersonalityAttributes;
import zoo.animal.reproduction.AnimalReproductionAttributes;
import zoo.animal.reproduction.ContraceptionMethods;
import zoo.animal.reproduction.Sex;
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
     * @throws exception.UnexistedFileException if we try to load an unexisted
     * file
     * @throws IOException if if an I/O error prevents a document from being
     * fully parsed
     * @throws JDOMException if error occurs during parsing
     */
    public ParserBackUp(File file) throws UnexistedFileException, IOException, JDOMException {
        SAXBuilder sax = new SAXBuilder();
        Document document;
        try {
            document = sax.build(file);
        } catch (IOException ex) {
            throw new UnexistedFileException(ex.getMessage());
        }
        this.zooEl = document.getRootElement();
    }

    /**
     * Parse the characteristics of the zoo (not including paddocks, animals,
     * keepers and language)
     *
     * @return The corresponding FakeZoo
     * @throws IOException
     * @throws JDOMException if there is a problem inside the XML to parse
     */
    public FakeZoo parserZoo() throws IOException, JDOMException {
        Element dimEl = zooEl.getChild(Constants.DIMENSIONS);
        return new FakeZoo(zooEl.getAttributeValue(Constants.NAME),
                Integer.parseInt(dimEl.getChild(Constants.WIDTH).getText()),
                Integer.parseInt(dimEl.getChild(Constants.HEIGHT).getText()),
                Integer.parseInt(zooEl.getChild(Constants.AGE).getText()),
                Integer.parseInt(zooEl.getChild(Constants.MONTHSPEREVALUATION).getText()),
                Integer.parseInt(zooEl.getChild(Constants.HORIZON).getText())
        );
    }

    /**
     * Parse the language of the save
     *
     * @return Code of the language
     */
    public String parserLanguage() {
        return zooEl.getChildText(Constants.LANGUAGE);
    }

    /**
     * Method used to parse paddocks
     *
     * @return The list of FakePaddocks extracted from the parsed file
     */
    public List<FakePaddock> parserPaddocks() {
        Element paddocksEl = zooEl.getChild(Constants.PADDOCKS);
        List<Element> paddocksElList = paddocksEl.getChildren(Constants.PADDOCK);
        List<FakePaddock> paddocksList = new ArrayList<>();
        for (Element tmpPadEl : paddocksElList) {
            paddocksList.add(new FakePaddock(tmpPadEl.getAttributeValue(Constants.NAME),
                    Integer.parseInt(tmpPadEl.getChildText(Constants.X)),
                    Integer.parseInt(tmpPadEl.getChildText(Constants.Y)),
                    Integer.parseInt(tmpPadEl.getChildText(Constants.WIDTH)),
                    Integer.parseInt(tmpPadEl.getChildText(Constants.HEIGHT)),
                    Integer.parseInt(tmpPadEl.getChildText(Constants.BIOME)),
                    Integer.parseInt(tmpPadEl.getChildText(Constants.PADDOCKTYPE))));
        }
        return paddocksList;
    }

    /**
     * Extract the list of elements "animals" from the parsed file
     *
     * @return the list
     */
    private List<Element> findElementsAnimals() {
        List<Element> animalsEl = new ArrayList<>();
        Element paddocksEl = zooEl.getChild(Constants.PADDOCKS);
        List<Element> paddocksElList = paddocksEl.getChildren(Constants.PADDOCK);
        paddocksElList.stream().forEach((Element el) -> animalsEl.add(el.getChild(Constants.ANIMALS)));
        return animalsEl;
    }

    /**
     * The method used to parse the animals element
     *
     * @return The list of FakeAnimal in the file
     */
    public List<FakeAnimal> parserAnimals() throws IncorrectLoadException {
        List<Element> animalsElList = findElementsAnimals();
        List<FakeAnimal> animalList = new ArrayList<>();
        for (Element el : animalsElList) {
            animalList.addAll(parserAnimal(el));
        }
        return animalList;
    }

    /**
     * Extract the list of fake animals inside an element "animals"
     *
     * @param animalsEl the "animals" element to parse
     * @returnthe correpsonding list
     * @throws IncorrectLoadException if a parsed element does not respect the
     * limitations of the animal attributes
     */
    private List<FakeAnimal> parserAnimal(Element animalsEl) throws IncorrectLoadException {
        List<Element> animalsElList = animalsEl.getChildren(Constants.ANIMAL);
        List<FakeAnimal> animalsList = new ArrayList<>();
        String spec;
        String pad = animalsEl.getParentElement().getAttributeValue(Constants.NAME);
        int sex;
        int age;
        BiomeAttributes biome;
        FeedingAttributes optFeed;
        FeedingAttributes actualFeed;
        int diet;
        AnimalReproductionAttributes repro;
        LifeSpanLightAttributes life;
        SocialAttributes social;
        TerritoryAttributes territory;
        double wellBeing;
        int starvation;
        int drowning;
        String mother;
        String father;
        int contraceptionMethod;
        for (Element tmpAnimalEl : animalsElList) {
            PersonalityAttributes personality;
            spec = tmpAnimalEl.getChildText(Constants.SPECIE);
            sex = parserSex(tmpAnimalEl);
            age = Integer.parseInt(tmpAnimalEl.getChildText(Constants.AGE));
            biome = parserBiomeAttributes(tmpAnimalEl);
            optFeed = parserOptimalFeedingAttributes(tmpAnimalEl);
            actualFeed = parserActualFeedingAttributes(tmpAnimalEl);
            diet = parserDiet(tmpAnimalEl);
            repro = parserReproductionAttributes(tmpAnimalEl);
            life = parserLifeSpanAttributes(tmpAnimalEl);
            social = parserSocialAttributes(tmpAnimalEl);
            territory = parserTerritoryAttributes(tmpAnimalEl);
            personality = parserPersonalityAttributes(tmpAnimalEl);
            wellBeing = parserWellBeing(tmpAnimalEl);
            starvation = parserStarvation(tmpAnimalEl);
            drowning = parserDrowning(tmpAnimalEl);
            mother = parserMother(tmpAnimalEl);
            father = parserFather(tmpAnimalEl);
            contraceptionMethod = parserContraceptionMethod(tmpAnimalEl);
            animalsList.add(new FakeAnimal(spec,
                    tmpAnimalEl.getAttributeValue(Constants.NAME),
                    pad, sex, age, biome, optFeed, actualFeed, diet, repro,
                    life, social, territory, personality, wellBeing, starvation, drowning,
                    mother, father, contraceptionMethod));
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
     * Parse the turn's number of starvation of an animal
     *
     * @param tmpAnimalEl the "animal" element to parse
     * @return the corresponding number of turns
     */
    private int parserStarvation(Element tmpAnimalEl)
            throws IncorrectLoadException {
        return Integer.parseInt(tmpAnimalEl.getChildText(Constants.STARVATION));
    }

    /**
     * Parse the turn's number of drowning of an animal
     *
     * @param tmpAnimalEl the "animal" element to parse
     * @return the corresponding number of turns
     */
    private int parserDrowning(Element tmpAnimalEl)
            throws IncorrectLoadException {
        return Integer.parseInt(tmpAnimalEl.getChildText(Constants.DROWNING));
    }

    /**
     * Parse the currently gestation duration of an animal
     *
     * @param tmpAnimalEl the "animal" element to parse
     * @return the corresponding number
     */
    private int parserGestationDuration(Element tmpAnimalEl)
            throws IncorrectLoadException {
        return Integer.parseInt(tmpAnimalEl.getChildText(Constants.GESTATIONDURATION));
    }

    /**
     * Parse the mothers name of an animal
     *
     * @param tmpAnimalEl the "animal" element to parse
     * @return the corresponding mother
     */
    private String parserMother(Element tmpAnimalEl)
            throws IncorrectLoadException {
        return tmpAnimalEl.getChildText(Constants.MOTHER);
    }

    /**
     * Parse the name of the father of an animal
     *
     * @param tmpAnimalEl the "animal" element to parse
     * @return the corresponding father
     */
    private String parserFather(Element tmpAnimalEl)
            throws IncorrectLoadException {
        return tmpAnimalEl.getChildText(Constants.FATHER);
    }

    /**
     * Parse the well-being of an animal
     *
     * @param tmpAnimalEl the "animal" element to parse
     * @return the corresponding well-being
     */
    private double parserWellBeing(Element tmpAnimalEl)
            throws IncorrectLoadException {
        return Double.parseDouble(tmpAnimalEl.getChildText(Constants.WELLBEING));
    }

    /**
     * Parse the optimal feeding attributes of an animal
     *
     * @param tmpAnimalEl the "animal" element to parse
     * @return the corresponding attributes
     * @throws IncorrectLoadException if the values of the attributes are
     * incorrect
     */
    private FeedingAttributes parserOptimalFeedingAttributes(Element tmpAnimalEl)
            throws IncorrectLoadException {
        Element feedEl = tmpAnimalEl.getChild(Constants.OPTIMALFEEDING_ATT);
        return new FeedingAttributes(
                Double.parseDouble(feedEl.getChildText(Constants.QUANTITY)), 0);
    }

    /**
     * Parse the actual feeding attributes of an animal
     *
     * @param tmpAnimalEl the "animal" element to parse
     * @return the corresponding attributes
     * @throws IncorrectLoadException if the values of the attributes are
     * incorrect
     */
    private FeedingAttributes parserActualFeedingAttributes(Element tmpAnimalEl)
            throws IncorrectLoadException {
        Element feedEl = tmpAnimalEl.getChild(Constants.ACTUALFEEDING_ATT);
        return new FeedingAttributes(
                Double.parseDouble(feedEl.getChildText(Constants.QUANTITY)),
                Integer.parseInt(feedEl.getChildText(Constants.FASTDAYS)));
    }

    /**
     * Parse the diet of an animal
     *
     * @param tmpAnimalEl the "animal" element to parse
     * @return the corresponding attributes
     */
    private int parserDiet(Element tmpAnimalEl) {
        Element feedEl = tmpAnimalEl.getChild(Constants.ACTUALFEEDING_ATT);
        return Integer.parseInt(feedEl.getChildText(Constants.DIET));
    }

    /**
     * Parse the reproduction attributes of an animal
     *
     * @param tmpAnimalEl the "animal" element to parse
     * @return the corresponding attributes
     * @throws IncorrectLoadException if the values of the attributes are
     * incorrect
     */
    private AnimalReproductionAttributes parserReproductionAttributes(Element tmpAnimalEl) {
        Element reproEl = tmpAnimalEl.getChild(Constants.ACTUALREPRODUCTION_ATT);
        AnimalReproductionAttributes repro = new AnimalReproductionAttributes(
                Integer.parseInt(reproEl.getChildText(Constants.FEMALEMATURITYAGE)),
                Integer.parseInt(reproEl.getChildText(Constants.MALEMATURITYAGE)),
                Double.parseDouble(reproEl.getChildText(Constants.GESTATIONFREQUENCY)),
                Integer.parseInt(reproEl.getChildText(Constants.LITTERSIZE)), 
                Integer.parseInt(reproEl.getChildText(Constants.GESTATIONDURATION)),
                ContraceptionMethods.NONE, 
                Sex.UNKNOWN,
                Integer.parseInt(reproEl.getChildText(Constants.CURRENTLYGESTATIONDURATION))
        );
        return repro;
    }

    private int parserContraceptionMethod(Element tmpAnimalEl) {
        Element reproEl = tmpAnimalEl.getChild(Constants.ACTUALREPRODUCTION_ATT);
        return Integer.parseInt(reproEl.getChildText(Constants.CONTRACEPTIONMETHOD));
    }
    
      private int parserSex(Element tmpAnimalEl) {
        Element reproEl = tmpAnimalEl.getChild(Constants.ACTUALREPRODUCTION_ATT);
        return Integer.parseInt(reproEl.getChildText(Constants.SEX));
    }

    /**
     * Parse the lifespan attributes of an animal
     *
     * @param tmpAnimalEl the "animal" element to parse
     * @return the corresponding attributes
     * @throws IncorrectLoadException if the values of the attributes are
     * incorrect
     */
    private LifeSpanLightAttributes parserLifeSpanAttributes(Element tmpAnimalEl)
            throws IncorrectLoadException {
        Element lifeEl = tmpAnimalEl.getChild(Constants.ACTUALLIFESPAN_ATT);
        LifeSpanLightAttributes life = new LifeSpanLightAttributes(
                Integer.parseInt(lifeEl.getChildText(Constants.LIFESPAN)));
        return life;
    }

    /**
     * Parse the optimal social attributes of an animal
     *
     * @param tmpAnimalEl the "animal" element to parse
     * @return the corresponding attributes
     * @throws IncorrectLoadException if the values of the attributes are
     * incorrect
     */
    private SocialAttributes parserSocialAttributes(Element tmpAnimalEl) throws IncorrectLoadException {
        Element socialEl = tmpAnimalEl.getChild(Constants.OPTIMALSOCIAL_ATT);
        SocialAttributes social = new SocialAttributes(
                Integer.parseInt(socialEl.getChildText(Constants.GROUPSIZE)));
        return social;
    }

    /**
     * Parse the optimal territory attributes of an animal
     *
     * @param tmpAnimalEl the "animal" element to parse
     * @return the corresponding attributes
     * @throws IncorrectLoadException if the values of the attributes are
     * incorrect
     */
    private TerritoryAttributes parserTerritoryAttributes(Element tmpAnimalEl) throws IncorrectLoadException {
        Element territoryEl = tmpAnimalEl.getChild(Constants.OPTIMALTERRITORY_ATT);
        TerritoryAttributes territory = new TerritoryAttributes(
                Double.parseDouble(territoryEl.getChildText(Constants.TERRITORYSIZE)));
        return territory;
    }

    /**
     * Parse the personality attributes of an animal
     *
     * @param tmpAnimalEl the "animal" element to parse
     * @return the corresponding attributes
     * @throws IncorrectLoadException if the values of the attributes are
     * incorrect
     */
    private PersonalityAttributes parserPersonalityAttributes(Element tmpAnimalEl) {
        Element persoEl = tmpAnimalEl.getChild(Constants.PERSONALITY);
        return new PersonalityAttributes(
                Double.parseDouble(persoEl.getChildText(Constants.BRAVERY)),
                Double.parseDouble(persoEl.getChildText(Constants.INTELLIGENCE)),
                Double.parseDouble(persoEl.getChildText(Constants.METICULOUSNESS)),
                Double.parseDouble(persoEl.getChildText(Constants.GREED)),
                Double.parseDouble(persoEl.getChildText(Constants.CURIOSITY)));
    }

    /**
     * Extract the list of fake keepers of the zoo
     *
     * @return the list of fake animal keepers in the parsed file
     */
    public List<FakeAnimalKeeper> parserAnimalKeepers() {
        List<FakeAnimalKeeper> keepers = new ArrayList<>();
        Element keepersEl = this.zooEl.getChild(Constants.ANIMALKEEPERS);
        List<Element> keeperListEl = keepersEl.getChildren(Constants.ANIMALKEEPER);
        keeperListEl.stream().forEach((el) -> {
            keepers.add(parserAnimalKeeper(el));
        });
        return keepers;
    }

    /**
     * Parse an element "animalKeeper"
     *
     * @param el the element to parse
     * @return the corresponding fake keeper
     */
    private FakeAnimalKeeper parserAnimalKeeper(Element el) {
        return new FakeAnimalKeeper(el.getAttributeValue(Constants.NAME),
                this.parserTimedPaddocks(el),
                this.parserTimedTasksPerPaddock(el),
                this.parserManagedFamilies(el),
                this.parserManagedTasks(el));
    }

    /**
     * Parse an element "timedPaddocks"
     *
     * @param el the element to parse
     * @return the corresponding map of the timed paddocks identifying by the
     * name
     */
    private Map<String, Double> parserTimedPaddocks(Element el) {
        Map<String, Double> timedPaddocks = new HashMap<>();
        for (Element padEl : el.getChild(Constants.TIMEDPADDOCKS).getChildren(Constants.TIMEDPADDOCK)) {
            timedPaddocks.put(padEl.getChildText(Constants.PADDOCK), Double.parseDouble(padEl.getChildText(Constants.TIME)));
        }
        return timedPaddocks;
    }

    /**
     * Parse an element "timedTasksPerPaddock"
     *
     * @param el the element to parse
     * @return the corresponding map of the timed tasks per paddock identifying
     * by the name and id
     */
    private Map<FakeTaskPaddock, Double> parserTimedTasksPerPaddock(Element el) {
        Map<FakeTaskPaddock, Double> timedPaddocks = new HashMap<>();
        for (Element padEl : el.getChild(Constants.TIMEDTASKS).getChildren(Constants.TIMEDTASK)) {
            timedPaddocks.put(
                    new FakeTaskPaddock(padEl.getChildText(Constants.PADDOCK),
                            Integer.parseInt(padEl.getChildText(Constants.TASK))),
                    Double.parseDouble(padEl.getChildText(Constants.TIME)));
        }
        return timedPaddocks;
    }

    /**
     * Parse an element "managedFamilies"
     *
     * @param el the element to parse
     * @return the corresponding map of the managed families identifying by the
     * id
     */
    private Map<Integer, Double> parserManagedFamilies(Element el) {
        Map<Integer, Double> managed = new HashMap<>();
        for (Element managedEl : el.getChild(Constants.MANAGEDFAMILIES)
                .getChildren(Constants.MANAGEDFAMILY)) {
            managed.put(Integer.parseInt(managedEl.getChildText(Constants.FAMILY)),
                    Double.parseDouble(managedEl.getChildText(Constants.TIME)));
        }
        return managed;
    }

    /**
     * Parse an element "managedTasks"
     *
     * @param el the element to parse
     * @return the corresponding map of the managed tasks identifying by the id
     */
    private Map<Integer, Double> parserManagedTasks(Element el) {
        Map<Integer, Double> managed = new HashMap<>();
        for (Element managedEl : el.getChild(Constants.MANAGEDTASKS).getChildren(Constants.MANAGEDTASK)) {
            managed.put(Integer.parseInt(managedEl.getChildText(Constants.TASK)),
                    Double.parseDouble(managedEl.getChildText(Constants.TIME)));
        }
        return managed;
    }
}
