package backup.load;

import exception.IncorrectDimensionsException;
import exception.name.EmptyNameException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import main.InstanciateSpecies;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import zoo.IZoo;
import zoo.Zoo;
import zoo.animal.FakeAnimal;
import zoo.animal.death.LifeSpanLightAttributes;
import zoo.animal.feeding.FeedingAttributes;
import zoo.animal.reproduction.ReproductionAttributes;
import zoo.animal.social.SocialAttributes;
import zoo.animal.specie.Specie;
import zoo.paddock.IPaddock;
import zoo.paddock.Paddock;
import zoo.paddock.PaddockCoordinates;
import zoo.paddock.TerritoryAttributes;
import zoo.paddock.biome.BiomeAttributes;

/**
 *
 * @author doyenm
 */
public class ParserBackUp {

    Element zooEl;

    public ParserBackUp(File file) throws IOException, JDOMException {
        SAXBuilder sax = new SAXBuilder();
        Document document;
        document = sax.build(file);
        this.zooEl = document.getRootElement();
    }

    public IZoo parserZoo() throws EmptyNameException, IOException, JDOMException {
        IZoo zoo = new Zoo();
        Element dimEl = zooEl.getChild("dimensions");
        Map<String, Specie> spec = InstanciateSpecies.instanciateSpecies("resources/species");
        zoo.initiateZoo(zooEl.getAttributeValue("name"),
                Integer.parseInt(dimEl.getChild("width").getText()),
                Integer.parseInt(dimEl.getChild("height").getText()), spec,
                Integer.parseInt(zooEl.getChild("age").getText()),
                Integer.parseInt(zooEl.getChild("monthsPerEvaluation").getText()));
        return zoo;
    }

    public ArrayList<IPaddock> parserPaddocks() throws IncorrectDimensionsException {
        Element paddocksEl = zooEl.getChild("paddocks");
        List<Element> paddocksElList = paddocksEl.getChildren("paddock");
        ArrayList<IPaddock> paddocksList = new ArrayList<>();
        Iterator it = paddocksElList.iterator();
        Element tmpPadEl;
        PaddockCoordinates tmpCoor;
        while (it.hasNext()) {
            tmpPadEl = (Element) it.next();
            tmpCoor = new PaddockCoordinates(Integer.parseInt(tmpPadEl.getChildText("x")),
                    Integer.parseInt(tmpPadEl.getChildText("y")),
                    Integer.parseInt(tmpPadEl.getChildText("width")),
                    Integer.parseInt(tmpPadEl.getChildText("height")));
            paddocksList.add(new Paddock(tmpPadEl.getAttributeValue("name"), tmpCoor));
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

    public ArrayList<FakeAnimal> parserAnimals() {
        ArrayList<Element> animalsElList = findElementsAnimals();
        ArrayList<FakeAnimal> animalList = new ArrayList<>();
        animalsElList.stream().forEach((Element el) -> animalList.addAll(parserAnimal(el)));
        return animalList;
    }

    public ArrayList<FakeAnimal> parserAnimal(Element animalsEl) {
      //  Element animalsEl = zooEl.getChild("animals");
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
}
