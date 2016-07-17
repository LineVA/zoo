package zoo.animal;

import exception.IncorrectDataException;
import exception.IncorrectDimensionsException;
import exception.name.AlreadyUsedNameException;
import java.util.ArrayList;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
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
import zoo.paddock.IPaddock;
import zoo.paddock.Paddock;
import zoo.paddock.PaddockCoordinates;
import zoo.paddock.TerritoryAttributes;
import zoo.paddock.biome.BiomeAttributes;

/**
 *
 * @author doyenm
 */
public class FindRoomatesOfTheSameSpecieTest {

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

    Names names1 = new Names("english1", "french1", "scientific1");
    Specie spec1 = new Specie(names1, biome, feed,
            0, repro, life, conservation, social, terri);
    Names names2 = new Names("english2", "french2", "scientific2");
    Specie spec2 = new Specie(names2, biome, feed,
            0, repro, life, conservation, social, terri);

    /**
     * One paddock, two animals, one specie
     */
    @Test
    public void shouldReturnAnArrayOf2WhenThereIsTwoAnimalsOfTheSameSpecieInThePaddock()
            throws IncorrectDataException, AlreadyUsedNameException, IncorrectDimensionsException {
        // Given
        IPaddock pad1 = new Paddock("pad1", new PaddockCoordinates(0, 0, 1, 1));
        AnimalImpl animal1 = new AnimalImpl(spec1, "animal1", pad1, null, 0, null,
                null, null, 0, null, null, null, null);
        AnimalImpl animal2 = new AnimalImpl(spec1, "animal2", pad1, null, 0, null,
                null, null, 0, null, null, null, null);
        pad1.addAnimal(animal1);
        pad1.addAnimal(animal2);
        // When
        ArrayList<Animal> expectedList = new ArrayList<>();
        expectedList.add(animal1);
        expectedList.add(animal2);
        ArrayList<Animal> actualList = animal1.findRoommatesOfTheSameSpecie();
        // Then
        assertEquals(2, actualList.size());
        assertTrue(actualList.contains(animal1));
        assertTrue(actualList.contains(animal2));
    }

    /**
     * One Paddock, two animals, two species
     */
    @Test
    public void shouldReturnAnArrayOf1WhenThereIsOneAnimalsOfTheSameSpecieInThePaddock()
            throws IncorrectDataException, AlreadyUsedNameException, IncorrectDimensionsException {
        // Given
        IPaddock pad1 = new Paddock("pad1", new PaddockCoordinates(0, 0, 1, 1));
        AnimalImpl animal1 = new AnimalImpl(spec1, "animal1", pad1, null, 0, null,
                null, null, 0, null, null, null, null);
        AnimalImpl animal2 = new AnimalImpl(spec2, "animal2", pad1, null, 0, null,
                null, null, 0, null, null, null, null);
        pad1.addAnimal(animal1);
        pad1.addAnimal(animal2);
        // When
        ArrayList<Animal> expectedList = new ArrayList<>();
        expectedList.add(animal1);
        ArrayList<Animal> actualList = animal1.findRoommatesOfTheSameSpecie();
        // Then
        assertEquals(1, actualList.size());
        assertTrue(actualList.contains(animal1));
    }

    /**
     * two Paddocks : - two animals, two species - one animal, the researched
     * specie
     */
    @Test
    public void shouldReturnAnArrayOf1WhenTheOthersOfTheSameSpecieAreNotInTheSamePaddock()
            throws IncorrectDataException, AlreadyUsedNameException, IncorrectDimensionsException {
        // Given
        IPaddock pad1 = new Paddock("pad1", new PaddockCoordinates(0, 0, 1, 1));
        IPaddock pad2 = new Paddock("pad2", new PaddockCoordinates(0, 0, 1, 1));
        AnimalImpl animal1 = new AnimalImpl(spec1, "animal1", pad1, null, 0, null,
                null, null, 0, null, null, null, null);
        AnimalImpl animal2 = new AnimalImpl(spec2, "animal2", pad1, null, 0, null,
                null, null, 0, null, null, null, null);
        AnimalImpl animal3 = new AnimalImpl(spec1, "animal3", pad2, null, 0, null,
                null, null, 0, null, null, null, null);
        pad1.addAnimal(animal1);
        pad1.addAnimal(animal2);
        pad2.addAnimal(animal3);
        // When
        ArrayList<Animal> expectedList = new ArrayList<>();
        expectedList.add(animal1);
        ArrayList<Animal> actualList = animal1.findRoommatesOfTheSameSpecie();
        // Then
        assertEquals(1, actualList.size());
        assertTrue(actualList.contains(animal1));
    }
    
      @Test
    public void shouldReturnNullWhenThePaddockIsNull()
            throws IncorrectDataException, AlreadyUsedNameException, IncorrectDimensionsException {
        // Given
        IPaddock pad1 = new Paddock("pad1", new PaddockCoordinates(0, 0, 1, 1));
        AnimalImpl animal1 = new AnimalImpl(spec1, "animal1", null, null, 0, null,
                null, null, 0, null, null, null, null);
        pad1.addAnimal(animal1);
        // When
        ArrayList<Animal> expectedList = new ArrayList<>();
        ArrayList<Animal> actualList = animal1.findRoommatesOfTheSameSpecie();
        // Then
        assertNull(actualList);
    }
    
    @Test
    public void shouldReturnNullWhenTheSpecieIsNull()
            throws IncorrectDataException, AlreadyUsedNameException, IncorrectDimensionsException {
        // Given
        IPaddock pad1 = new Paddock("pad1", new PaddockCoordinates(0, 0, 1, 1));
        AnimalImpl animal1 = new AnimalImpl(null, "animal1", pad1, null, 0, null,
                null, null, 0, null, null, null, null);
        pad1.addAnimal(animal1);
        // When
        ArrayList<Animal> expectedList = new ArrayList<>();
        ArrayList<Animal> actualList = animal1.findRoommatesOfTheSameSpecie();
        // Then
        assertNull(actualList);
    }

}
