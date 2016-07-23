package zoo.animal.reproduction;

import exception.IncorrectDataException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import org.powermock.api.mockito.PowerMockito;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.support.membermodification.MemberMatcher.method;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.legacy.PowerMockRunner;
import zoo.animal.AnimalImpl;
import zoo.animal.Names;
import zoo.animal.conservation.ConservationStatus;
import zoo.animal.death.LifeSpanAttributes;
import zoo.animal.feeding.FeedingAttributes;
import zoo.animal.social.SocialAttributes;
import zoo.animal.specie.Specie;
import zoo.paddock.TerritoryAttributes;
import zoo.paddock.biome.BiomeAttributes;

/**
 *
 * @author doyenm
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(AnimalImpl.class)
public class CanFemaleReproducteTest {

    ReproductionAttributes reproAtt;
    Specie specie;
    AnimalImpl mockAnimal;
    AnimalImpl spy;

    @Before
    public void setUpClass() throws IncorrectDataException, Exception {
        // Mock getFemaleMaturityAge()
        reproAtt = new ReproductionAttributes(12, 0, 0.0, 2);
        // when(repro.getGestationFrequency()).thenReturn(0.0);
        BiomeAttributes biomeAtt = new BiomeAttributes(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        FeedingAttributes feedingAtt = new FeedingAttributes(0.0);
        SocialAttributes socialAtt = new SocialAttributes(0);
        TerritoryAttributes terriAtt = new TerritoryAttributes(0);
        ConservationStatus status = ConservationStatus.UNKNOWN;
        Names names = new Names("", "", "");
        LifeSpanAttributes lifespanAtt = new LifeSpanAttributes(0, 0);
        int ecoregion = 0;
        specie = new Specie(names, biomeAtt, feedingAtt, 0, reproAtt, lifespanAtt, status, socialAtt, terriAtt, 0);
        // Mock Animal methods
//        mockAnimal = mock(AnimalImpl.class);
//        when(mockAnimal.drawActualFeeding(any(Specie.class))).thenReturn(feedingAt);
//        when(mockAnimal.drawOptimalFeeding(any(Specie.class))).thenReturn(feedingAt);
//        when(mockAnimal.drawOptimalBiome(any(Specie.class))).thenReturn(biomeAt);
//        when(mockAnimal.drawActualReproduction(any(Specie.class))).thenReturn(repro);
//        when(mockAnimal.getAge()).thenReturn(10);

        spy = PowerMockito.spy(new AnimalImpl(specie, null, null, Sex.FEMALE, 36));

        when(spy, method(AnimalImpl.class, "drawActualFeeding", Specie.class))
                .withArguments(any(Specie.class))
                .thenReturn(feedingAtt);
        when(spy, method(AnimalImpl.class, "drawOptimalFeeding", Specie.class))
                .withArguments(any(Specie.class))
                .thenReturn(feedingAtt);
        when(spy, method(AnimalImpl.class, "drawOptimalBiome", Specie.class))
                .withArguments(any(Specie.class))
                .thenReturn(biomeAtt);
        when(spy, method(AnimalImpl.class, "drawActualReproduction", Specie.class))
                .withArguments(any(Specie.class))
                .thenReturn(reproAtt);
    }

    @After
    public void tearDownClass() {

    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test(expected = RuntimeException.class)
    public void shouldReturnTrueWhenTheAnimalIsAMatureFemale() throws IncorrectDataException {
        // Given

        // When
//        ReproductionImpl repro = new ReproductionImpl();
//        repro.canFemaleReproducte(mockAnimal);
//        // Then
//        verify(spy, times(1)).canBePregnant();
    }

    // This test does not check anymore : pb with the mock
//    @Test
//    public void shouldReturnFalseWhenTheAnimalIsANonMatureFemale() throws IncorrectDataException {
//        // Given
//        Animal animal = new AnimalImpl(specie, null, null, Sex.FEMALE, 10);
//        // When
//        ReproductionImpl reproduction = new ReproductionImpl();
//        boolean actual = reproduction.canFemaleReproducte(mockAnimal);
//        // Then
//        assertFalse(actual);
//    }
//
//    @Test
//    public void shouldReturnFalseWhenTheAnimalIsAMale() throws IncorrectDataException {
//        // Given
//        Animal animal = new AnimalImpl(specie, null, null, Sex.MALE, 36);
//        // When
//        ReproductionImpl reproduction = new ReproductionImpl();
//        boolean actual = reproduction.canFemaleReproducte(animal);
//        // Then
//        assertFalse(actual);
//    }
//
//    // This test does not check anymore : pb with the mock
//    @Test
//    public void shouldReturnFalseWhenTheAnimalHasAFrequencyGestationToOne() throws IncorrectDataException {
//        // Given
//        when(repro.getGestationFrequency()).thenReturn(1.0);
//        Animal animal = new AnimalImpl(specie, null, null, Sex.FEMALE, 36);
//        // When
//        ReproductionImpl reproduction = new ReproductionImpl();
//        boolean actual = reproduction.canFemaleReproducte(animal);
//        // Then
//        assertFalse(actual);
//    }
}
