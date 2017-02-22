package zoo.animal.specie;

import exception.IncorrectDataException;
import exception.IncorrectLoadException;
import exception.name.UnknownNameException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaders;
import utils.Constants;
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
        List<Integer> diet = dietParser(root);
        int region = ecoregionParser(root);
        int family = familyParser(root);
        List<Integer> biomes = biomeParser(root);
        FeedingAttributes feeding = feedingParser(root);
        ReproductionAttributes repro = reproductionParser(root);
        LifeSpanAttributes lifeSpan = lifeSpanParser(root);
        int conservation = conservationParser(root);
        SocialAttributes social = socialParser(root);
        TerritoryAttributes territory = territoryParser(root);
        int size = sizeParser(root);
        List<Integer> continents = continentParser(root);
        DocumentationURI docu = documentationParser(root);
        int programme = breedingProgrammeParser(root);
        Tags tags = tagsParser(root);
        Specie spec = new Specie(names, docu, biomeAtt, feeding, diet, repro, lifeSpan,
                conservation, social, territory, region, family, biomes, size, continents, programme, tags);
        return spec;
    }

    private static List<String> additionalNames(Element root) {
        List<String> additional = new ArrayList<>();
        List<Element> names = root.getChildren(Constants.NAME);
        for (Element name : names) {
            additional.add(name.getText());
        }
        return additional;
    }

    private static Names namesParser(Element root) {
        Element nameEl = root.getChild(Constants.NAMES);
        return new Names(nameEl.getChildText(Constants.FR), nameEl.getChildText(Constants.EN),
                nameEl.getChildText(Constants.SCIENTIFIC), additionalNames(nameEl.getChild(Constants.ADDITIONALFRENCHNAMES)),
                additionalNames(nameEl.getChild(Constants.ADDITIONALENGLISHNAMES)));
    }

    /**
     * @deprecated @param root
     * @return
     */
    private static BiomeAttributes biomeAttributesParser(Element root) {
        BiomeAttributes biome = new BiomeAttributes(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        return biome;
    }

    private static List<Integer> biomeParser(Element root) {
        List<Integer> biomes = new ArrayList<>();
        Element continentsEl = root.getChild(Constants.TERRITORY).getChild(Constants.BIOMES);
        List<Element> continentEl = continentsEl.getChildren(Constants.BIOME);
        for (Element cont : continentEl) {
            biomes.add(Integer.parseInt(cont.getText()));
        }
        return biomes;
    }

    private static List<Integer> dietParser(Element root) {
        List<Integer> diets = new ArrayList<>();
        Element dietsEl = root.getChild(Constants.FEEDING).getChild(Constants.DIETS);
        List<Element> dietEl = dietsEl.getChildren(Constants.DIET);
        for (Element cont : dietEl) {
            diets.add(Integer.parseInt(cont.getText()));
        }
        return diets;
    }

    private static int fastDaysParser(Element root) {
        Element fastDaysEl = root.getChild(Constants.FEEDING).getChild(Constants.FASTDAYS);
        if (fastDaysEl != null) {
            return Integer.parseInt(fastDaysEl.getText());
        }
        return 0;
    }

    private static List<Integer> continentParser(Element root) {
        List<Integer> continents = new ArrayList<>();
        Element continentsEl = root.getChild(Constants.GENERAL).getChild(Constants.CONTINENTS);
        List<Element> continentEl = continentsEl.getChildren(Constants.CONTINENT);
        for (Element cont : continentEl) {
            continents.add(Integer.parseInt(cont.getText()));
        }
        return continents;
    }

    private static FeedingAttributes feedingParser(Element root) throws IncorrectLoadException {
        Element feedingEl = root.getChild(Constants.FEEDING);
        if (feedingEl.getChildText(Constants.FASTDAYS) != null) {
            return new FeedingAttributes(Double.parseDouble(feedingEl.getChildText(Constants.QUANTITY)),
                    Integer.parseInt(feedingEl.getChildText(Constants.FASTDAYS)));
        } else {
            return new FeedingAttributes(Double.parseDouble(feedingEl.getChildText(Constants.QUANTITY)), 0);
        }
    }

    private static ReproductionAttributes reproductionParser(Element root) {
        Element reproEl = root.getChild(Constants.REPRODUCTION);
        if(reproEl.getChildText(Constants.GESTATIONDURATION) != null){
        return new ReproductionAttributes(Integer.parseInt(reproEl.getChildText(Constants.FEMALEMATURITYAGE)),
                Integer.parseInt(reproEl.getChildText(Constants.MALEMATURITYAGE)),
                Double.parseDouble(reproEl.getChildText(Constants.GESTATIONFREQUENCY)),
                Integer.parseInt(reproEl.getChildText(Constants.LITTERSIZE)), 
                Integer.parseInt(reproEl.getChildText(Constants.GESTATIONDURATION)));
        } else {
              return new ReproductionAttributes(Integer.parseInt(reproEl.getChildText(Constants.FEMALEMATURITYAGE)),
                Integer.parseInt(reproEl.getChildText(Constants.MALEMATURITYAGE)),
                Double.parseDouble(reproEl.getChildText(Constants.GESTATIONFREQUENCY)),
                Integer.parseInt(reproEl.getChildText(Constants.LITTERSIZE)), 
                0);
        }
    }

    private static LifeSpanAttributes lifeSpanParser(Element root) throws IncorrectDataException, IncorrectLoadException {
        Element lifeEl = root.getChild(Constants.LIFESPAN);
        return new LifeSpanAttributes(Integer.parseInt(lifeEl.getChildText(Constants.FEMALELIFESPAN)),
                Integer.parseInt(lifeEl.getChildText(Constants.MALELIFESPAN)));
    }

    private static int conservationParser(Element root) throws UnknownNameException {
        Element consEl = root.getChild(Constants.GENERAL);
        return Integer.parseInt(consEl.getChildText(Constants.UICN));
    }

    private static int ecoregionParser(Element root) {
        Element genEl = root.getChild(Constants.GENERAL);
        return Integer.parseInt(genEl.getChildText(Constants.ECOREGION));
    }

    private static int familyParser(Element root) {
        Element genEl = root.getChild(Constants.GENERAL);
        return Integer.parseInt(genEl.getChildText(Constants.FAMILY));
    }

    private static int breedingProgrammeParser(Element root) {
        Element genEl = root.getChild(Constants.GENERAL);
        return Integer.parseInt(genEl.getChildText(Constants.BREEDING));
    }

    private static SocialAttributes socialParser(Element root) throws IncorrectLoadException {
        Element socialEl = root.getChild(Constants.SOCIAL);
        return new SocialAttributes(Integer.parseInt(socialEl.getChildText(Constants.GROUPSIZE)));
    }

    private static TerritoryAttributes territoryParser(Element root) throws IncorrectLoadException {
        Element terriEl = root.getChild(Constants.TERRITORY);
        return new TerritoryAttributes(Double.parseDouble(terriEl.getChildText(Constants.TERRITORYSIZE)));
    }

    private static int sizeParser(Element root) {
        return Integer.parseInt(root.getChild(Constants.FEEDING).getChildText(Constants.SIZE));
    }

    private static DocumentationURI documentationParser(Element root) {
        Element docEl = root.getChild(Constants.DOCUMENTATION);
        return new DocumentationURI(docEl.getChildText(Constants.FRENCHWIKI),
                docEl.getChildText(Constants.ENGLISHWIKI), docEl.getChildText(Constants.ANIMALDIVERSITY));
    }

    private static Tags tagsParser(Element root) {
        Element tagsEl = root.getChild(Constants.TAGS);
        if (tagsEl != null) {
            return new Tags(languageTagsParser(tagsEl.getChild(Constants.FRENCHTAGS)),
                    languageTagsParser(tagsEl.getChild(Constants.ENGLISHTAGS))
            );
        } else {
            return new Tags(new HashSet<String>(), new HashSet<String>());
        }
    }

    private static Set<String> languageTagsParser(Element languageTag) {
        Set<String> tagsSet = new HashSet<>();
        List<Element> tagEl = languageTag.getChildren(Constants.TAG);
        for (Element el : tagEl) {
            tagsSet.add(el.getText());
        }
        return tagsSet;
    }
}
