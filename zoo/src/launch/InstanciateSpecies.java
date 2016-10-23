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
 * Class providing static method to instanciate the list of allowing species
 * @author doyenm
 */
public class InstanciateSpecies {

    /**
     * Intanciate of the allowing species
     * @param resource the repository where to find the xml files of the species
     * @param option the options of Play
     * @return the map of the allowed species ;
     * the key in the main name of the specie in the current language
     * @throws IOException in case of problem to read the resource files
     * @throws JDOMException if a resource file has a problem with the xml structure
     */
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
                System.out.println(ex.getMessage());
                ex.getMessage();
            }
        });
        return species;
    }
}
