package zoo.animal.specie;

import exception.IncorrectDataException;
import exception.name.EmptyNameException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import launch.InstanciateSpecies;
import launch.options.Option;
import org.jdom2.JDOMException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Fragmented tests of the method compare() of class Specie
 *
 * @author doyenm
 */
public class Specie_CompareTest {

    Map<String, Specie> species;
    Specie manul;
    LightSpecie lightSpecie;
    List<Integer> biomes;

    @Before
    public void setUpClass() throws IncorrectDataException, Exception {
        Option option = new Option();
        species = InstanciateSpecies.instanciateSpecies("./test/zoo/animal/specie", option);
        manul = species.get("Manul");
        lightSpecie = new LightSpecie(null, null, null, null, null, null, null, null, null);
        biomes = new ArrayList<>();
    }

    @Test
    public void shouldReturnTrueWhenTheSpecieContainsAllTheSearchedBiomes()
            throws EmptyNameException, IOException, JDOMException {
        // Given
        // When
        biomes.add(1);
        biomes.add(2);
        lightSpecie.setBiome(biomes);
        // Then
        Assert.assertTrue(manul.compare(lightSpecie));
    }

    @Test
    public void shouldReturnFalseWhenTheSpecieContainsOnlyAPartOfTheSearchedBiomes()
            throws EmptyNameException, IOException, JDOMException {
        // Given
        // When
        biomes.add(1);
        biomes.add(14);
        lightSpecie.setBiome(biomes);
        // Then
        Assert.assertFalse(manul.compare(lightSpecie));
    }
    
      @Test
    public void shouldReturnFalseWhenTheSpecieContainsNoneOfTheSearchedBiomes()
            throws EmptyNameException, IOException, JDOMException {
        // Given
        // When
        biomes.add(12);
        biomes.add(14);
        lightSpecie.setBiome(biomes);
        // Then
        Assert.assertFalse(manul.compare(lightSpecie));
    }
}
