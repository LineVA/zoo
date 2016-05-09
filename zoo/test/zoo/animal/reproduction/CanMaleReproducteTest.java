package zoo.animal.reproduction;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import zoo.animal.Animal;
import zoo.animal.Species;

/**
 *
 * @author doyenm
 */
public class CanMaleReproducteTest {

    ReproductionAttributes repro;

    @Before
    public void setUpClass() {
        // Mock getFemaleMaturityAge()
        repro = mock(ReproductionAttributes.class);
        when(repro.getMaleMaturityAge()).thenReturn(12);
    }

    @After
    public void tearDownClass() {

    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldReturnTrueWhenTheAnimalIsAMatureMale() {
        // Given
        Animal animal = new Animal(Species.CAT, null, null, Sex.MALE, 36, null, null, repro);
        // When
        ReproductionImpl reproduction = new ReproductionImpl();
        boolean actual = reproduction.canMaleReproducte(animal);
        // Then
        assertTrue(actual);
    }

    @Test
    public void shouldReturnFalseWhenTheAnimalIsANonMatureMale() {
        // Given
        Animal animal = new Animal(Species.CAT, null, null, Sex.MALE, 10, null, null, repro);
        // When
        ReproductionImpl reproduction = new ReproductionImpl();
        boolean actual = reproduction.canMaleReproducte(animal);
        // Then
        assertFalse(actual);
    }

    @Test
    public void shouldReturnFalseWhenTheAnimalIsAFemale() {
        // Given
        Animal animal = new Animal(Species.CAT, null, null, Sex.FEMALE, 36, null, null, repro);
        // When
        ReproductionImpl reproduction = new ReproductionImpl();
        boolean actual = reproduction.canMaleReproducte(animal);
        // Then
        assertFalse(actual);
    }

}
