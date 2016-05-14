package zoo.animal.specie;

import java.io.File;
import java.io.IOException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaders;
import zoo.animal.Names;
import zoo.animal.feeding.FeedingAttributes;
import zoo.animal.reproduction.ReproductionAttributes;
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
        FeedingAttributes feeding = feedingParser(root);
        ReproductionAttributes repro = reproductionParser(root);
        Specie spec = new Specie(names, biome, feeding, repro);
        return spec;

        // return null;
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
}
