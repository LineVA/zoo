package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import org.jdom2.JDOMException;
import zoo.Zoo;
import zoo.animal.specie.ParserSpecie;
import zoo.animal.specie.Specie;

/**
 *
 * @author doyenm
 */
public class InstanciateSpecies {
  public static HashMap<String, Specie> instanciateSpecies(String resource) throws IOException, JDOMException {
        HashMap<String,Specie> species = new HashMap<>();
        Stream<Path> files = Files.list(Paths.get(resource));
        files.forEach((Path file) -> {
            try {
                if(file.endsWith(".xml")){
                Specie tmpSpec = ParserSpecie.mainParserSpecie(file.toFile());
                species.put(tmpSpec.getNames().getEnglishName(), tmpSpec);
                } 
            } catch (IOException ex) {
                Logger.getLogger(Zoo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JDOMException ex) {
                Logger.getLogger(Zoo.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        return species;
    }
}
