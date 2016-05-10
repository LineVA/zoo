package zoo.animal.reproduction;

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
import zoo.animal.Species;
import zoo.paddock.Paddock;

/**
 *
 * @author doyenm
 */
public class WhichMaleTest {

    ReproductionAttributes repro;
    Paddock pad;

    @Before
    public void setUpClass() {
        // Mock getFemaleMaturityAge() and getMaleMaturityAge()
        repro = mock(ReproductionAttributes.class);
        when(repro.getFemaleMaturityAge()).thenReturn(12);
        when(repro.getMaleMaturityAge()).thenReturn(13);
        // Create a paddock 
        pad = new Paddock(null, null);
    }

    @After
    public void tearDownClass() {

    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldReturnMaleWhenThereIsNoMatureMaleOfTheSameSpecie() {
        // Given
        Animal female1 = new Animal(Species.CAT, null, pad, Sex.FEMALE, 14, null, null, repro);
        Animal male1 = new Animal(Species.CAT, null, pad, Sex.MALE, 10, null, null, repro);
        Animal male2 = new Animal(Species.DOG, null, pad, Sex.MALE, 20, null, null, repro);

        pad.addAnimal(male1);
        pad.addAnimal(male2);
        pad.addAnimal(female1);
        ReproductionImpl reproduction = new ReproductionImpl();
        // When
        Animal father = reproduction.whichMale(female1.getSpecie(), female1.getPaddock().getAnimals());
        // Then
        Assert.assertNull(father);
    }

    @Test
    public void shouldReturnTheFirstMatureMaleOfTheSameSpecieWhenThereIsSeveralMales() {
        // Given
        Animal female1 = new Animal(Species.CAT, null, pad, Sex.FEMALE, 14, null, null, repro);
        Animal male1 = new Animal(Species.CAT, null, pad, Sex.MALE, 10, null, null, repro);
        Animal male2 = new Animal(Species.DOG, null, pad, Sex.MALE, 20, null, null, repro);
        Animal male3 = new Animal(Species.CAT, null, pad, Sex.MALE, 15, null, null, repro);
        pad.addAnimal(female1);
        pad.addAnimal(male1);
        pad.addAnimal(male2);
        pad.addAnimal(male3);
        ReproductionImpl reproduction = new ReproductionImpl();
        // When
        Animal father = reproduction.whichMale(female1.getSpecie(), female1.getPaddock().getAnimals());
        // Then
        assertEquals(male3, father);
    }
}
