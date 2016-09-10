package zoo.paddock;

import exception.IncorrectDataException;
import exception.name.NameException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import launch.options.Option;
import org.jdom2.JDOMException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import zoo.animal.Animal;
import zoo.animal.AnimalImpl;
import zoo.animal.reproduction.Sex;
import zoo.animal.specie.Family;
import zoo.animal.specie.ParserSpecie;
import zoo.animal.specie.Specie;
import zoo.paddock.biome.Ecoregion;

/**
 *
 * @author doyenm
 */
public class listAnimalTest {

    Option opt;

    Specie specie1;
    Specie specie2;

    IPaddock paddock;

    ParserSpecie parser = new ParserSpecie();

    @Before
    public void setUpClass() throws IOException, JDOMException {
        opt = new Option();
        specie1 = parser.mainParserSpecie(new File("./test/zoo/paddock/speciesForListAnimalTest/zebre1.xml"));
        specie2 = parser.mainParserSpecie(new File("./test/zoo/paddock/speciesForListAnimalTest/zebre2.xml"));
        paddock = new Paddock("name", null, null, opt);
    }

    @Test
    public void shouldReturnTwoAnimalsWhenNoCriteriaIsSpecified()
            throws IncorrectDataException, NameException {
        // Given
        Animal animal1 = new AnimalImpl(specie1, "animal1", paddock, Sex.UNKNOWN, 0, opt);
        Animal animal2 = new AnimalImpl(specie2, "animal2", paddock, Sex.UNKNOWN, 0, opt);
        paddock.addAnimal(animal1);
        paddock.addAnimal(animal2);
        // When
        ArrayList<Animal> results = paddock.listAnimal(null, null, null);
        // Then 
        assertEquals(2, results.size());
        assertTrue(results.contains(animal1));
        assertTrue(results.contains(animal2));
    }
    
    @Test
    public void shouldReturnOneAnimalWhenEcoregionIsSpecified()
            throws IncorrectDataException, NameException {
        // Given
        Animal animal1 = new AnimalImpl(specie1, "animal1", paddock, Sex.UNKNOWN, 0, opt);
        Animal animal2 = new AnimalImpl(specie2, "animal2", paddock, Sex.UNKNOWN, 0, opt);
        paddock.addAnimal(animal1);
        paddock.addAnimal(animal2);
        // When
        ArrayList<Animal> results = paddock.listAnimal(null, Ecoregion.UNKNOWN, null);
        // Then 
        assertEquals(1, results.size());
        assertTrue(results.contains(animal2));
    }
    
    @Test
    public void shouldReturnOneAnimalWhenSpecieIsSpecified()
            throws IncorrectDataException, NameException {
        // Given
        Animal animal1 = new AnimalImpl(specie1, "animal1", paddock, Sex.UNKNOWN, 0, opt);
        Animal animal2 = new AnimalImpl(specie2, "animal2", paddock, Sex.UNKNOWN, 0, opt);
        paddock.addAnimal(animal1);
        paddock.addAnimal(animal2);
        // When
        ArrayList<Animal> results = paddock.listAnimal(specie2, null, null);
        // Then 
        assertEquals(1, results.size());
        assertTrue(results.contains(animal2));
    }
    
    @Test
    public void shouldReturnOneAnimalWhenFamilyIsSpecified()
            throws IncorrectDataException, NameException {
        // Given
        Animal animal1 = new AnimalImpl(specie1, "animal1", paddock, Sex.UNKNOWN, 0, opt);
        Animal animal2 = new AnimalImpl(specie2, "animal2", paddock, Sex.UNKNOWN, 0, opt);
        paddock.addAnimal(animal1);
        paddock.addAnimal(animal2);
        // When
        ArrayList<Animal> results = paddock.listAnimal(null, null, Family.MUSTELIDAE);
        // Then 
        assertEquals(1, results.size());
        assertTrue(results.contains(animal2));
    }
}