package launch;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import launch.options.Option;
import org.jdom2.JDOMException;
import zoo.animal.specie.ParserSpecie;
import zoo.animal.specie.Specie;

/**
 *
 * @author doyenm
 */
public class InstanciateSpecies {

    public static Map<String, Specie> instanciateSpecies(String resource, Option option)
            throws IOException, JDOMException {
        Map<String, Specie> species = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        List<Path> files = Files.list(Paths.get(resource)).collect(Collectors.toList());
        files.stream().forEach((file) -> {
            try {
                if (file.toString().endsWith(".xml")) {
                    Specie tmpSpec = ParserSpecie.mainParserSpecie(file.toFile());
                    if (option.getLocale().getLanguage().equals("fr")) {
                        species.put(tmpSpec.getNames().getFrenchName(), tmpSpec);
                    } else {
                        species.put(tmpSpec.getNames().getEnglishName(), tmpSpec);
                    }
                }
            } catch (IOException | JDOMException ex) {
                ex.getMessage();
            }
        });
        return species;
    }
}
