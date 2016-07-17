package zoo.animal;

import exception.IncorrectDataException;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import zoo.animal.conservation.ConservationStatus;
import zoo.animal.death.LifeSpanAttributes;
import zoo.animal.feeding.FeedingAttributes;
import zoo.animal.reproduction.ReproductionAttributes;
import zoo.animal.social.SocialAttributes;
import zoo.animal.specie.Specie;
import zoo.paddock.TerritoryAttributes;
import zoo.paddock.biome.BiomeAttributes;

/**
 *
 * @author doyenm
 */
public class IsfromTheSameSpecieTest {

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    BiomeAttributes biome = new BiomeAttributes(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
    FeedingAttributes feed = new FeedingAttributes(0.0);
    ReproductionAttributes repro = new ReproductionAttributes(0, 0, 0.0, 0);
    SocialAttributes social = new SocialAttributes(0);
    TerritoryAttributes terri = new TerritoryAttributes(0);
    LifeSpanAttributes life = new LifeSpanAttributes(0, 0);
    ConservationStatus conservation = ConservationStatus.UNKNOWN;

    @Test
    public void shouldReturnFalseWhenTheSpeciesAreNotTheSame() throws IncorrectDataException {
        // Given
        Names names1 = new Names("english1", "french1", "scientific1");
        Specie spec1 = new Specie(names1, biome, feed,
                0, repro, life, conservation, social, terri);
        Names names2 = new Names("english2", "french2", "scientific2");
        Specie spec2 = new Specie(names2, biome, feed,
                0, repro, life, conservation, social, terri);
        AnimalImpl animal = new AnimalImpl(spec1, "", null, null, 0, null,
                null, null, 0, null, null, null, null);
        // When
        boolean expectedResult = animal.isFromTheSameSpecie(spec2);
        // Then
        assertFalse(expectedResult);
    }

    @Test
    public void shouldReturnTrueWhenTheSpeciesAreTheSame() throws IncorrectDataException {
        // Given
        Names names1 = new Names("english1", "french1", "scientific1");
        Specie spec1 = new Specie(names1, biome, feed,
                0, repro, life, conservation, social, terri);
        Specie spec2 = new Specie(names1, biome, feed,
                0, repro, life, conservation, social, terri);
        AnimalImpl animal = new AnimalImpl(spec1, "", null, null, 0, null,
                null, null, 0, null, null, null, null);
        // When
        boolean expectedResult = animal.isFromTheSameSpecie(spec2);
        // Then
        assertTrue(expectedResult);
    }

    @Test
    public void shouldReturnFalseWhenTheSpecieIsNull() throws IncorrectDataException {
        // Given
        Names names1 = new Names("english1", "french1", "scientific1");
        Specie spec1 = new Specie(names1, biome, feed,
                0, repro, life, conservation, social, terri);
        AnimalImpl animal = new AnimalImpl(spec1, "", null, null, 0, null,
                null, null, 0, null, null, null, null);
        // When
        boolean expectedResult = animal.isFromTheSameSpecie(null);
        // Then
        assertFalse(expectedResult);
    }
    
    @Test
    public void shouldReturnFalseWhenTheSpecieOfTheAnimalIsNull() throws IncorrectDataException {
        // Given
        Names names1 = new Names("english1", "french1", "scientific1");
        Specie spec1 = new Specie(names1, biome, feed,
                0, repro, life, conservation, social, terri);
        Specie spec2 = new Specie(names1, biome, feed,
                0, repro, life, conservation, social, terri);
        AnimalImpl animal = new AnimalImpl(null, "", null, null, 0, null,
                null, null, 0, null, null, null, null);
        // When
        boolean expectedResult = animal.isFromTheSameSpecie(spec2);
        // Then
        assertFalse(expectedResult);
    }
}
