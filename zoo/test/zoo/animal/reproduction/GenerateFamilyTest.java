package zoo.animal.reproduction;

import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import zoo.animal.Animal;
import zoo.animal.Species;
import zoo.paddock.Paddock;
/**
 *
 * @author doyenm
 */
public class GenerateFamilyTest {

    ReproductionImpl repro;

    @Before
    public void setUpClass() {
        repro = mock(ReproductionImpl.class);
        when(repro.uniform.intAverage(any(int.class))).thenReturn(2);
    }

    @After
    public void tearDownClass() {

    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldReturnAnArrayListWithThreeElements() {
        // Given
        Paddock pad = new Paddock(null, null);
        Animal mother = new Animal(Species.CAT, "mother", pad, Sex.FEMALE, 0);
        Animal father = new Animal(Species.CAT, "father", pad, Sex.MALE, 0);
        // When
        ArrayList<Animal> actual = repro.generateFamily(mother, father);
        // Then
        AssertEquals(3, actual.size());
        AssertEquals(father, actual.get(0));
    }
}
