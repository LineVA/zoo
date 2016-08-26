package zoo.animal.reproduction;

import exception.IncorrectDataException;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import zoo.animal.Animal;
import zoo.animal.AnimalImpl;
import zoo.animal.Names;
import zoo.animal.conservation.ConservationStatus;
import zoo.animal.death.LifeSpanAttributes;
import zoo.animal.death.LifeSpanLightAttributes;
import zoo.animal.feeding.FeedingAttributes;
import zoo.animal.social.SocialAttributes;
import zoo.animal.specie.Specie;
import zoo.paddock.TerritoryAttributes;
import zoo.paddock.biome.BiomeAttributes;

/**
 *
 * @author doyenm
 */
public class IsInGestationTest {

    Specie specie;
    BiomeAttributes biomeAtt = new BiomeAttributes(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
    FeedingAttributes feedingAtt = new FeedingAttributes(0.0);
    SocialAttributes socialAtt = new SocialAttributes(0);
    TerritoryAttributes terriAtt = new TerritoryAttributes(0);
    ConservationStatus status = ConservationStatus.UNKNOWN;
    Names names = new Names("", "", "");
    LifeSpanAttributes lifespanAtt = new LifeSpanAttributes(0, 0);
    int ecoregion = 0;

    @Before
    public void setUpClass() {
    }

    @Test
    public void shouldReturnTrueWhenTheAnimalMustBeGestating() throws IncorrectDataException {
        // Given
        ReproductionAttributes reproAtt = new ReproductionAttributes(12, 0, 1, 2);
        specie = new Specie(names, biomeAtt, feedingAtt, 0, reproAtt, lifespanAtt, status, socialAtt, terriAtt, 0);
        Animal animal = new AnimalImpl(specie, "name", null, Sex.FEMALE, 0,
                biomeAtt, feedingAtt,
                feedingAtt, 0,
                reproAtt,
                new LifeSpanLightAttributes(0), socialAtt,
                terriAtt);
        // When
        ReproductionImpl reproduction = new ReproductionImpl();
        boolean actual = reproduction.isInGestation(animal);
        // Then
        assertTrue(actual);
    }
    
      @Test
    public void shouldReturnFalseWhenTheAnimalCannotBeGestating() throws IncorrectDataException {
        // Given
        ReproductionAttributes reproAtt = new ReproductionAttributes(12, 0, 0, 2);
        specie = new Specie(names, biomeAtt, feedingAtt, 0, reproAtt, lifespanAtt, status, socialAtt, terriAtt, 0);
        Animal animal = new AnimalImpl(specie, "name", null, Sex.FEMALE, 0,
                biomeAtt, feedingAtt,
                feedingAtt, 0,
                reproAtt,
                new LifeSpanLightAttributes(0), socialAtt,
                terriAtt);
        // When
        ReproductionImpl reproduction = new ReproductionImpl();
        boolean actual = reproduction.isInGestation(animal);
        // Then
        assertFalse(actual);
    }
}
