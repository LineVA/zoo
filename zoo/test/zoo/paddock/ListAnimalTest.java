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
import zoo.animal.feeding.Diet;
import zoo.animal.reproduction.Sex;
import zoo.animal.specie.LightSpecie;
import zoo.animal.specie.ParserSpecie;
import zoo.animal.specie.Specie;
import zoo.paddock.biome.Biome;

/**
 *
 * @author doyenm
 */
public class ListAnimalTest {

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
        LightSpecie light = new LightSpecie(null, -1, -1, -1, -1, -1, -1, -1);
        ArrayList<Animal> results = paddock.listAnimal(light, null, null, null);
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
        LightSpecie light = new LightSpecie(null, -1, -1, 0, -1, -1, -1, -1);
        ArrayList<Animal> results = paddock.listAnimal(light, null, null, null);
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
        LightSpecie light = new LightSpecie(specie2.getNames(), -1, -1, -1, -1, -1, -1, -1);
        ArrayList<Animal> results = paddock.listAnimal(light, null, null, null);
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
        LightSpecie light = new LightSpecie(null, -1, -1, -1, 2, -1, -1, -1);
        ArrayList<Animal> results = paddock.listAnimal(light, null, null, null);
        // Then 
        assertEquals(1, results.size());
        assertTrue(results.contains(animal2));
    }

    @Test
    public void shouldReturnOneAnimalWhenSizeIsSpecified()
            throws IncorrectDataException, NameException {
        // Given
        Animal animal1 = new AnimalImpl(specie1, "animal1", paddock, Sex.UNKNOWN, 0, opt);
        Animal animal2 = new AnimalImpl(specie2, "animal2", paddock, Sex.UNKNOWN, 0, opt);
        paddock.addAnimal(animal1);
        paddock.addAnimal(animal2);
        // When
        LightSpecie light = new LightSpecie(null, -1, -1, -1, -1, -1, 2, -1);
        ArrayList<Animal> results = paddock.listAnimal(light, null, null, null);
        // Then 
        assertEquals(1, results.size());
        assertTrue(results.contains(animal2));
    }

    @Test
    public void shouldReturnOneAnimalWhenConservationIsSpecified()
            throws IncorrectDataException, NameException {
        // Given
        Animal animal1 = new AnimalImpl(specie1, "animal1", paddock, Sex.UNKNOWN, 0, opt);
        Animal animal2 = new AnimalImpl(specie2, "animal2", paddock, Sex.UNKNOWN, 0, opt);
        paddock.addAnimal(animal1);
        paddock.addAnimal(animal2);
        // When
        LightSpecie light = new LightSpecie(null, -1, 2, -1, -1, -1, -1, -1);
        ArrayList<Animal> results = paddock.listAnimal(light, null, null, null);
        // Then 
        assertEquals(1, results.size());
        assertTrue(results.contains(animal2));
    }

    @Test
    public void shouldReturnOneAnimalWhenDietIsSpecified()
            throws IncorrectDataException, NameException {
        // Given
        Animal animal1 = new AnimalImpl(specie1, "animal1", paddock, Sex.UNKNOWN, 0, opt);
        animal1.changeDiet("1");
        Animal animal2 = new AnimalImpl(specie2, "animal2", paddock, Sex.UNKNOWN, 0, opt);
        animal2.changeDiet("2");
        paddock.addAnimal(animal1);
        paddock.addAnimal(animal2);
        // When
        LightSpecie light = new LightSpecie(null, -1, -1, -1, -1, -1, -1, -1);
        ArrayList<Animal> results = paddock.listAnimal(light, null, Diet.BACCIVOROUS, null);
        // Then 
        assertEquals(1, results.size());
        assertTrue(results.contains(animal2));
    }

    @Test
    public void shouldReturnOneAnimalWhenSexIsSpecified()
            throws IncorrectDataException, NameException {
        // Given
        Animal animal1 = new AnimalImpl(specie1, "animal1", paddock, Sex.MALE, 0, opt);
        Animal animal2 = new AnimalImpl(specie2, "animal2", paddock, Sex.FEMALE, 0, opt);
        paddock.addAnimal(animal1);
        paddock.addAnimal(animal2);
        // When
        LightSpecie light = new LightSpecie(null, -1, -1, -1, -1, -1, -1, -1);
        ArrayList<Animal> results = paddock.listAnimal(light, Sex.FEMALE, null, null);
        // Then 
        assertEquals(1, results.size());
        assertTrue(results.contains(animal2));
    }

    @Test
    public void shouldReturnNoAnimalWhenBiomeIsSpecified()
            throws IncorrectDataException, NameException {
        // Given
        Animal animal1 = new AnimalImpl(specie1, "animal1", paddock, Sex.UNKNOWN, 0, opt);
        Animal animal2 = new AnimalImpl(specie2, "animal2", paddock, Sex.UNKNOWN, 0, opt);
        paddock.addAnimal(animal1);
        paddock.addAnimal(animal2);
        paddock.setBiome("1");
        // When
        LightSpecie light = new LightSpecie(null, -1, -1, -1, -1, -1, -1, -1);
        ArrayList<Animal> results = paddock.listAnimal(light, null, null, Biome.DESERT);
        // Then 
        assertEquals(0, results.size());
    }

    @Test
    public void shouldReturnOneAnimalWhenContinentIsSpecified()
            throws IncorrectDataException, NameException {
        // Given
        Animal animal1 = new AnimalImpl(specie1, "animal1", paddock, Sex.UNKNOWN, 0, opt);
        Animal animal2 = new AnimalImpl(specie2, "animal2", paddock, Sex.UNKNOWN, 0, opt);
        paddock.addAnimal(animal1);
        paddock.addAnimal(animal2);
        // When
        LightSpecie light = new LightSpecie(null, -1, -1, -1, -1, -1, -1, 2);
        ArrayList<Animal> results = paddock.listAnimal(light, null, null, null);
        // Then 
        assertEquals(1, results.size());
        assertTrue(results.contains(animal2));
    }
}
