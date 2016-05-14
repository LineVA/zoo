package zoo.animal.reproduction;

import java.util.ArrayList;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import zoo.Statistics.Uniform;
import zoo.animal.Animal;
import zoo.animal.specie.Specie;
import zoo.paddock.Paddock;

/**
 *
 * @author doyenm
 */
public class GenerateFamilyTest {

    Uniform uniform;

    @Before
    public void setUpClass() {
        uniform = mock(Uniform.class);
        when(uniform.intAverage(any(int.class))).thenReturn(2);
    }

    @After
    public void tearDownClass() {

    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldReturnAnArrayListWithThreeElements() {
        // Given
        Paddock expectedPad = new Paddock(null, null);
        Specie expectedSpecie = new Specie(null, null, null, null);
        Animal mother = new Animal(expectedSpecie, "mother", expectedPad, Sex.FEMALE, 0);
        Animal father = new Animal(expectedSpecie, "father", expectedPad, Sex.MALE, 0);
        // When
        ReproductionImpl repro = new ReproductionImpl(uniform);
        ArrayList<Animal> actual = repro.generateFamily(mother, father);
        // Then
        String expectedName1 = "motherfather0";
        String expectedName2 = "motherfather1";

        assertEquals(3, actual.size());
        assertEquals(father, actual.get(0));
        assertEquals(expectedSpecie, actual.get(1).getSpecie());
        assertEquals(expectedPad, actual.get(1).getPaddock());
        assertEquals(expectedName1, actual.get(1).getName());
        assertEquals(expectedSpecie, actual.get(2).getSpecie());
        assertEquals(expectedPad, actual.get(2).getPaddock());
        assertEquals(expectedName2, actual.get(2).getName());
    }
}
