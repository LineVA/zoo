package zoo.zoo;

import exception.IncorrectDimensionsException;
import exception.name.AlreadyUsedNameException;
import exception.name.EmptyNameException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import org.jdom2.JDOMException;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import zoo.Zoo;
import zoo.animal.specie.Specie;

/**
 *
 * @author doyenm
 */
public class InstanciateSpeciesTest {

    static Zoo zoo;

    @Before
    public void setUpClass() throws AlreadyUsedNameException,
            IncorrectDimensionsException, EmptyNameException, IOException {
        zoo = new Zoo("foo", 10, 10);
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
        zoo.instanciateSpecie(resource);
        // Then
        assertEquals(2, zoo.getSpecies().size());
        assertTrue(zoo.getSpecies().containsKey("Blue kangoroo"));
        Specie blue = zoo.getSpecies().get("Blue kangoroo");
        int expectedBlueFemaleMat = 1;
        int expectedBlueMaleMat = 2;
        int expectedBlueLitterSize = 4;
        double expectedBlueGestation = 3.0;
        int expectedBlueFemaleLife = 5;
        int expectedBlueMaleLife = 6;
        assertEquals(expectedBlueFemaleMat, blue.getReproduction().getFemaleMaturityAge());
        assertEquals(expectedBlueMaleMat, blue.getReproduction().getMaleMaturityAge());
        assertEquals(expectedBlueLitterSize, blue.getReproduction().getLitterSize());
        assertEquals(expectedBlueGestation, blue.getReproduction().getGestationFrequency(), 0.0);
        assertTrue(zoo.getSpecies().containsKey("Blue kangoroo"));
        Specie red = zoo.getSpecies().get("Red kangoroo");
        int expectedRedFemaleMat = 17;
        int expectedRedMaleMat = 22;
        int expectedRedLitterSize = 1;
        double expectedRedGestation = 1.5;
        assertEquals(expectedRedFemaleMat, red.getReproduction().getFemaleMaturityAge());
        assertEquals(expectedRedMaleMat, red.getReproduction().getMaleMaturityAge());
        assertEquals(expectedRedLitterSize, red.getReproduction().getLitterSize());
        assertEquals(expectedRedGestation, red.getReproduction().getGestationFrequency(), 0.0);
    }
}
