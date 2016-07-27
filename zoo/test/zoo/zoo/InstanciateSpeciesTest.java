package zoo.zoo;

import exception.IncorrectDimensionsException;
import exception.name.AlreadyUsedNameException;
import exception.name.EmptyNameException;
import java.io.IOException;
import launch.InstanciateSpecies;
import org.jdom2.JDOMException;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import zoo.animal.specie.Specie;
import static zoo.zoo.AddPaddockTest.zoo;

/**
 *
 * @author doyenm
 */
public class InstanciateSpeciesTest {

    @Before
    public void setUpClass() throws AlreadyUsedNameException,
            IncorrectDimensionsException, EmptyNameException, IOException {
    }

    @After
    public void tearDownClass() {
        zoo = null;
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldReturnAnHAshMapOfTwoElements() throws EmptyNameException, IOException, JDOMException {
        // Given
        String resource = "./test/zoo/zoo/resourceSpeciesTest";
        // When
        InstanciateSpecies.instanciateSpecies(resource);
        // Then
        assertEquals(2, zoo.getSpecies(null).size());
        assertTrue(zoo.getSpecies(null).containsKey("Blue kangoroo"));
        Specie blue = zoo.getSpecies(null).get("Blue kangoroo");
        int expectedBlueFemaleMat = 1;
        int expectedBlueMaleMat = 2;
        int expectedBlueLitterSize = 4;
        double expectedBlueGestation = 3.0;
        int expectedBlueFemaleLife = 5;
        int expectedBlueMaleLife = 6;
        assertEquals(expectedBlueFemaleMat, blue.getSpecieReproduction().getFemaleMaturityAge());
        assertEquals(expectedBlueMaleMat, blue.getSpecieReproduction().getMaleMaturityAge());
        assertEquals(expectedBlueLitterSize, blue.getSpecieReproduction().getLitterSize());
        assertEquals(expectedBlueGestation, blue.getSpecieReproduction().getGestationFrequency(), 0.0);
        assertTrue(zoo.getSpecies(null).containsKey("Blue kangoroo"));
        Specie red = zoo.getSpecies(null).get("Red kangoroo");
        int expectedRedFemaleMat = 17;
        int expectedRedMaleMat = 22;
        int expectedRedLitterSize = 1;
        double expectedRedGestation = 1.5;
        assertEquals(expectedRedFemaleMat, red.getSpecieReproduction().getFemaleMaturityAge());
        assertEquals(expectedRedMaleMat, red.getSpecieReproduction().getMaleMaturityAge());
        assertEquals(expectedRedLitterSize, red.getSpecieReproduction().getLitterSize());
        assertEquals(expectedRedGestation, red.getSpecieReproduction().getGestationFrequency(), 0.0);
    }
}
