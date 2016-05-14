package zoo.animal.reproduction;

import org.junit.After;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import zoo.animal.Animal;
import zoo.animal.feeding.FeedingAttributes;
import zoo.animal.specie.Specie;
import zoo.paddock.biome.BiomeAttributes;

/**
 *
 * @author doyenm
 */
public class CanFemaleReproducteTest {

    ReproductionAttributes repro;
    Specie specie;
    Animal mockAnimal;

    @Before
    public void setUpClass() {
        // Mock getFemaleMaturityAge()
        repro = new ReproductionAttributes(12, 0, 0.0, 2);
        // when(repro.getGestationFrequency()).thenReturn(0.0);
        BiomeAttributes biomeAt = new BiomeAttributes(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        FeedingAttributes feedingAt = new FeedingAttributes(0.0);
        specie = new Specie(null, biomeAt, feedingAt, repro);
        // Mock Animal methods
        mockAnimal = mock(Animal.class);
        when(mockAnimal.drawActualFeeding(any(Specie.class))).thenReturn(feedingAt);
        when(mockAnimal.drawOptimalFeeding(any(Specie.class))).thenReturn(feedingAt);
        when(mockAnimal.drawOptimalBiome(any(Specie.class))).thenReturn(biomeAt);
        when(mockAnimal.drawActualReproduction(any(Specie.class))).thenReturn(repro);
        when(mockAnimal.getAge()).thenReturn(10);

    }

    @After
    public void tearDownClass() {

    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldReturnTrueWhenTheAnimalIsAMatureFemale() {
        // Given
        Animal animal = new Animal(specie, null, null, Sex.FEMALE, 36);
        // When
        ReproductionImpl reproduction = new ReproductionImpl();
        boolean actual = reproduction.canFemaleReproducte(animal);
        // Then
        assertTrue(actual);
    }

    // This test does not check anymore : pb with the mock
    @Test
    public void shouldReturnFalseWhenTheAnimalIsANonMatureFemale() {
        // Given
        Animal animal = new Animal(specie, null, null, Sex.FEMALE, 10);
        // When
        ReproductionImpl reproduction = new ReproductionImpl();
        boolean actual = reproduction.canFemaleReproducte(mockAnimal);
        // Then
        assertFalse(actual);
    }

    @Test
    public void shouldReturnFalseWhenTheAnimalIsAMale() {
        // Given
        Animal animal = new Animal(specie, null, null, Sex.MALE, 36);
        // When
        ReproductionImpl reproduction = new ReproductionImpl();
        boolean actual = reproduction.canFemaleReproducte(animal);
        // Then
        assertFalse(actual);
    }

    // This test does not check anymore : pb with the mock
    @Test
    public void shouldReturnFalseWhenTheAnimalHasAFrequencyGestationToOne() {
        // Given
        when(repro.getGestationFrequency()).thenReturn(1.0);
        Animal animal = new Animal(specie, null, null, Sex.FEMALE, 36);
        // When
        ReproductionImpl reproduction = new ReproductionImpl();
        boolean actual = reproduction.canFemaleReproducte(animal);
        // Then
        assertFalse(actual);
    }

}
