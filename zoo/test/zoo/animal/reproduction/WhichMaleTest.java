package zoo.animal.reproduction;

import exception.IncorrectDataException;
import exception.name.AlreadyUsedNameException;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import zoo.animal.Animal;
import zoo.animal.conservation.ConservationStatus;
import zoo.animal.death.LifeSpanAttributes;
import zoo.animal.feeding.FeedingAttributes;
import zoo.animal.social.SocialAttributes;
import zoo.animal.specie.Specie;
import zoo.paddock.Paddock;
import zoo.paddock.TerritoryAttributes;
import zoo.paddock.biome.BiomeAttributes;

/**
 *
 * @author doyenm
 */
public class WhichMaleTest {

    ReproductionAttributes repro;
    Paddock pad;
    Specie specie1;
    Specie specie2;

    @Before
    public void setUpClass() {
        // Mock getFemaleMaturityAge() and getMaleMaturityAge()
        repro = mock(ReproductionAttributes.class);
        when(repro.getFemaleMaturityAge()).thenReturn(12);
        when(repro.getMaleMaturityAge()).thenReturn(13);
        // Create a paddock 
        pad = new Paddock(null, null);
        BiomeAttributes biome = new BiomeAttributes(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0);
        FeedingAttributes feeding = new FeedingAttributes(1.0);
        SocialAttributes social = new SocialAttributes(1);
        LifeSpanAttributes lifeSpan = new LifeSpanAttributes(1, 1);
        TerritoryAttributes territory = new TerritoryAttributes(1.0);
        specie1 = new Specie(null, biome, feeding, repro, lifeSpan,
                ConservationStatus.UNKNOWN, social, territory);
        specie2 = new Specie(null, biome, feeding, repro, lifeSpan,
                ConservationStatus.UNKNOWN, social, territory);
    }

    @After
    public void tearDownClass() {

    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldReturnNullWhenThereIsNoMatureMaleOfTheSameSpecie() 
            throws AlreadyUsedNameException, IncorrectDataException {
        // Given
        Animal female1 = new Animal(specie1, "female1", pad, Sex.FEMALE, 14);
        Animal male1 = new Animal(specie1, "male1", pad, Sex.MALE, 10);
        Animal male2 = new Animal(specie2, "male2", pad, Sex.MALE, 20);

        pad.addAnimal(male1);
        pad.addAnimal(male2);
        pad.addAnimal(female1);
        ReproductionImpl reproduction = new ReproductionImpl();
        // When
        Animal father = reproduction.whichMale(female1.getPaddock()
                .animalsOfTheSameSpecie(female1.getSpecie()));
        // Then
        Assert.assertNull(father);
    }

    // This test does not check anymore : pb with the mock.
    @Test
    public void shouldReturnTheFirstMatureMaleOfTheSameSpecieWhenThereIsSeveralMales()
            throws AlreadyUsedNameException, IncorrectDataException {
        // Given
        Animal female1 = new Animal(specie1, "female1", pad, Sex.FEMALE, 14);
        Animal male1 = new Animal(specie1, "male1", pad, Sex.MALE, 10);
        Animal male2 = new Animal(specie2, "male2", pad, Sex.MALE, 20);
        Animal male3 = new Animal(specie1, "male3", pad, Sex.MALE, 15);
        pad.addAnimal(female1);
        pad.addAnimal(male1);
        pad.addAnimal(male2);
        pad.addAnimal(male3);
        ReproductionImpl reproduction = new ReproductionImpl();
        // When
        Animal father = reproduction.whichMale(female1.getPaddock()
                .animalsOfTheSameSpecie(female1.getSpecie()));
        // Then
        assertEquals(male3, father);
    }
}
