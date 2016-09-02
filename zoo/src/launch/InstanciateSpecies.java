package launch;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;
import org.jdom2.JDOMException;
import zoo.animal.specie.ParserSpecie;
import zoo.animal.specie.Specie;

/**
 *
 * @author doyenm
 */
public class InstanciateSpecies {

    public static Map<String, Specie> instanciateSpecies(String resource) throws IOException, JDOMException {
        Map<String, Specie> species = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        Stream<Path> files = Files.list(Paths.get(resource));
        files.forEach((Path file) -> {
            try{ 
                if (file.toString().endsWith(".xml")) {
                    Specie tmpSpec = ParserSpecie.mainParserSpecie(file.toFile());
                    species.put(tmpSpec.getNames().getEnglishName(), tmpSpec);
                } else {
                    
                }
            } catch (IOException | JDOMException ex) {
                ex.printStackTrace();
            } 
        });

        return species;
    }
}
