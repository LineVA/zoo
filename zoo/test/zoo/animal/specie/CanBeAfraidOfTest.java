package zoo.animal.specie;

import exception.IncorrectDataException;
import exception.name.UnknownNameException;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import zoo.animal.Names;
import zoo.animal.conservation.ConservationStatus;
import zoo.animal.death.LifeSpanAttributes;
import zoo.animal.feeding.Diet;
import zoo.animal.feeding.FeedingAttributes;
import zoo.animal.feeding.Size;
import zoo.animal.reproduction.ReproductionAttributes;
import zoo.animal.social.SocialAttributes;
import zoo.paddock.TerritoryAttributes;
import zoo.paddock.biome.BiomeAttributes;
import zoo.paddock.biome.Ecoregion;

/**
 *
 * @author doyenm
 */
public class CanBeAfraidOfTest {

    Specie specie1;
    Specie specie2;

    ReproductionAttributes reproAtt;
    BiomeAttributes biomeAtt;
    FeedingAttributes feedingAtt;
    SocialAttributes socialAtt;
    TerritoryAttributes terriAtt;
    ConservationStatus status;
    Names names;
    LifeSpanAttributes lifespanAtt;
    int family = 0;
    int biome = 0;
    int ecoregion1;
    int size1;
    int diet1;
    int ecoregion2;
    int size2;
    int diet2;

    @Before
    public void setUpClass() throws IncorrectDataException, Exception {
        reproAtt = new ReproductionAttributes(0, 0, 0.0, 0);
        biomeAtt = new BiomeAttributes(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        feedingAtt = new FeedingAttributes(0.0);
        socialAtt = new SocialAttributes(0);
        terriAtt = new TerritoryAttributes(0);
        status = ConservationStatus.UNKNOWN;
        names = new Names("", "", "");
        lifespanAtt = new LifeSpanAttributes(0, 0);
    }

    @Test
    public void shouldReturnFalseWhenTheyHaveTheSameEcoregionButCannotEatTheOther() throws UnknownNameException {
        // Given
        ecoregion1 = Ecoregion.UNKNOWN.getId();
        ecoregion2 = ecoregion1;
        diet1 = Diet.APHIDIPHAGOUS.getId();
        diet2 = Diet.BACCIVOROUS.getId();
        // We check if the cannot eat themselves
        assertFalse(Diet.NONE.findDietById(diet1).canBeEatenBy(diet2));
        assertFalse(Diet.NONE.findDietById(diet2).canBeEatenBy(diet1));
        size1 = Size.UNKNOWN.getId();
        size2 = size1;
        specie1 = new Specie(names, biomeAtt, feedingAtt, diet1, reproAtt, lifespanAtt, status, socialAtt, terriAtt, ecoregion1, family, biome, size1);
        specie2 = new Specie(names, biomeAtt, feedingAtt, diet2, reproAtt, lifespanAtt, status, socialAtt, terriAtt, ecoregion2, family, biome, size2);
        // When
        boolean result1 = specie1.canBeAfraidOf(specie2);
        boolean result2 = specie2.canBeAfraidOf(specie1);
        // Then
        assertFalse(result1);
        assertFalse(result2);
    }

    @Test
    public void shouldReturnFalseWhenTheyDoNotHaveTheSameEcoregionButCannotEatTheOther() throws UnknownNameException {
        // Given
        ecoregion1 = Ecoregion.UNKNOWN.getId();
        ecoregion2 = Ecoregion.UNKNOWN2.getId();
        diet1 = Diet.APHIDIPHAGOUS.getId();
        diet2 = Diet.BACCIVOROUS.getId();
        // We check if the cannot eat themselves
        assertFalse(Diet.NONE.findDietById(diet1).canBeEatenBy(diet2));
        assertFalse(Diet.NONE.findDietById(diet2).canBeEatenBy(diet1));
        size1 = Size.UNKNOWN.getId();
        size2 = size1;
        specie1 = new Specie(names, biomeAtt, feedingAtt, diet1, reproAtt, lifespanAtt, status, socialAtt, terriAtt, ecoregion1, family, biome, size1);
        specie2 = new Specie(names, biomeAtt, feedingAtt, diet2, reproAtt, lifespanAtt, status, socialAtt, terriAtt, ecoregion2, family, biome, size2);
        // When
        boolean result1 = specie1.canBeAfraidOf(specie2);
        boolean result2 = specie2.canBeAfraidOf(specie1);
        // Then
        assertFalse(result1);
        assertFalse(result2);
    }

    @Test
    public void shouldReturnFalseWhenTheyDoNotHaveTheSameEcoregionButCanEatTheOther() throws UnknownNameException {
        // Given
        ecoregion1 = Ecoregion.UNKNOWN.getId();
        ecoregion2 = Ecoregion.UNKNOWN2.getId();
        diet1 = Diet.CARNIVOROUS.getId();
        diet2 = Diet.CARNIVOROUS.getId();
        // We check if the cannot eat themselves
        assertTrue(Diet.NONE.findDietById(diet1).canBeEatenBy(diet2));
        assertTrue(Diet.NONE.findDietById(diet2).canBeEatenBy(diet1));
        size1 = Size.UNKNOWN.getId();
        size2 = size1;
        specie1 = new Specie(names, biomeAtt, feedingAtt, diet1, reproAtt, lifespanAtt, status, socialAtt, terriAtt, ecoregion1, family, biome, size1);
        specie2 = new Specie(names, biomeAtt, feedingAtt, diet2, reproAtt, lifespanAtt, status, socialAtt, terriAtt, ecoregion2, family, biome, size2);
        // When
        boolean result1 = specie1.canBeAfraidOf(specie2);
        boolean result2 = specie2.canBeAfraidOf(specie1);
        // Then
        assertFalse(result1);
        assertFalse(result2);
    }

    @Test
    public void shouldReturnFalseWhenTheyHaveTheSameEcoregionCanEatTheOtherButHaveIncompatibleSIzes() throws UnknownNameException {
        // Given
        ecoregion1 = Ecoregion.UNKNOWN.getId();
        ecoregion2 = Ecoregion.UNKNOWN.getId();
        diet1 = Diet.CARNIVOROUS.getId();
        diet2 = Diet.CARNIVOROUS.getId();
        // We check if the cannot eat themselves
        assertTrue(Diet.NONE.findDietById(diet1).canBeEatenBy(diet2));
        assertTrue(Diet.NONE.findDietById(diet2).canBeEatenBy(diet1));
        size1 = Size.M.getId();
        size2 = Size.XXXL.getId();
        assertFalse(Size.UNKNOWN.findSizeById(size1).areCloseEnough(size2));
        assertFalse(Size.UNKNOWN.findSizeById(size2).areCloseEnough(size1));
        specie1 = new Specie(names, biomeAtt, feedingAtt, diet1, reproAtt, lifespanAtt, status, socialAtt, terriAtt, ecoregion1, family, biome, size1);
        specie2 = new Specie(names, biomeAtt, feedingAtt, diet2, reproAtt, lifespanAtt, status, socialAtt, terriAtt, ecoregion2, family, biome, size2);
        // When
        boolean result1 = specie1.canBeAfraidOf(specie2);
        boolean result2 = specie2.canBeAfraidOf(specie1);
        // Then
        assertFalse(result1);
        assertFalse(result2);
    }
    
       @Test
    public void shouldReturnTrueWhenTheyHaveTheSameEcoregionCanEatTheOtherAndCompatibleSIzes() throws UnknownNameException {
        // Given
        ecoregion1 = Ecoregion.UNKNOWN.getId();
        ecoregion2 = Ecoregion.UNKNOWN.getId();
        diet1 = Diet.CARNIVOROUS.getId();
        diet2 = Diet.CARNIVOROUS.getId();
        // We check if the cannot eat themselves
        assertTrue(Diet.NONE.findDietById(diet1).canBeEatenBy(diet2));
        assertTrue(Diet.NONE.findDietById(diet2).canBeEatenBy(diet1));
        size1 = Size.M.getId();
        size2 = Size.L.getId();
        assertTrue(Size.UNKNOWN.findSizeById(size1).areCloseEnough(size2));
        assertTrue(Size.UNKNOWN.findSizeById(size2).areCloseEnough(size1));
        specie1 = new Specie(names, biomeAtt, feedingAtt, diet1, reproAtt, lifespanAtt, status, socialAtt, terriAtt, ecoregion1, family, biome, size1);
        specie2 = new Specie(names, biomeAtt, feedingAtt, diet2, reproAtt, lifespanAtt, status, socialAtt, terriAtt, ecoregion2, family, biome, size2);
        // When
        boolean result1 = specie1.canBeAfraidOf(specie2);
        boolean result2 = specie2.canBeAfraidOf(specie1);
        // Then
        assertTrue(result1);
        assertTrue(result2);
    }
}
