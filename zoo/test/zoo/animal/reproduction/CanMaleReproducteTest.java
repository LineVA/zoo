package zoo.animal.reproduction;

import exception.IncorrectDataException;
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
import zoo.animal.feeding.FeedingAttributes;
import zoo.animal.specie.Specie;
import zoo.paddock.biome.BiomeAttributes;

/**
 *
 * @author doyenm
 */
public class CanMaleReproducteTest {

    ReproductionAttributes repro;
    Specie specie;

    @Before
    public void setUpClass() {
        // Mock getFemaleMaturityAge()
        repro = mock(ReproductionAttributes.class);
        when(repro.getMaleMaturityAge()).thenReturn(12);
         BiomeAttributes biomeAt = new BiomeAttributes(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        FeedingAttributes feedingAt = new FeedingAttributes(0.0);
        specie = new Specie(null, biomeAt, feedingAt, repro, null, null, null, null);
    }

    @After
    public void tearDownClass() {

    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldReturnTrueWhenTheAnimalIsAMatureMale() throws IncorrectDataException {
        // Given
        Animal animal = new Animal(specie, null, null, Sex.MALE, 36);
        // When
        ReproductionImpl reproduction = new ReproductionImpl();
        boolean actual = reproduction.canMaleReproducte(animal);
        // Then
        assertTrue(actual);
    }

    // This test does not check anymore : pb with the mock
    @Test
    public void shouldReturnFalseWhenTheAnimalIsANonMatureMale() throws IncorrectDataException {
        // Given
        Animal animal = new Animal(specie, null, null, Sex.MALE, 10);
        // When
        ReproductionImpl reproduction = new ReproductionImpl();
        boolean actual = reproduction.canMaleReproducte(animal);
        // Then
        assertFalse(actual);
    }

    @Test
    public void shouldReturnFalseWhenTheAnimalIsAFemale() throws IncorrectDataException {
        // Given
        Animal animal = new Animal(specie, null, null, Sex.FEMALE, 36);
        // When
        ReproductionImpl reproduction = new ReproductionImpl();
        boolean actual = reproduction.canMaleReproducte(animal);
        // Then
        assertFalse(actual);
    }

}
