package zoo.animalKeeper;

import exception.IncorrectDataException;
import exception.name.EmptyNameException;
import exception.name.NameException;
import exception.name.UnknownNameException;
import java.util.ArrayList;
import java.util.HashMap;
import launch.options.Option;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import zoo.animal.Animal;
import zoo.animal.AnimalImpl;
import zoo.animal.Names;
import zoo.animal.conservation.ConservationStatus;
import zoo.animal.death.LifeSpanAttributes;
import zoo.animal.feeding.Diet;
import zoo.animal.feeding.FeedingAttributes;
import zoo.animal.feeding.Size;
import zoo.animal.reproduction.ReproductionAttributes;
import zoo.animal.reproduction.Sex;
import zoo.animal.social.SocialAttributes;
import zoo.animal.specie.Family;
import zoo.animal.specie.Specie;
import zoo.paddock.IPaddock;
import zoo.paddock.PaddockBuilder;
import zoo.paddock.TerritoryAttributes;
import zoo.paddock.biome.Biome;
import zoo.paddock.biome.BiomeAttributes;
import zoo.paddock.biome.Ecoregion;

/**
 *
 * @author doyenm
 */
public class EvaluateByFamilyTest {

    public HashMap<TaskPaddock, Double> timedPerPaddock = new HashMap<>();
    public IPaddock pad1;
    public IPaddock pad2;
    public int task1 = 1;
    public int task2 = 2;
    public TaskPaddock tP1;
    public TaskPaddock tP2;
    public TaskPaddock tP3;
    public Double time1 = 1.0;
    public Double time2 = 2.0;
    public Double time3 = 3.0;
    public AnimalKeeperImpl keeper;
    public HashMap<IPaddock, Double> timedPaddocks;
    HashMap<Integer, Double> managedFamilies;
    Specie specie1;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUpClass() throws EmptyNameException, NameException, IncorrectDataException {
        Option option = new Option();
        HashMap<String, Animal> animals = new HashMap<>();
        ReproductionAttributes reproAtt = new ReproductionAttributes(0, 0, 0.0, 0);
        BiomeAttributes biomeAtt = new BiomeAttributes(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        FeedingAttributes feedingAtt = new FeedingAttributes(0.0);
        SocialAttributes socialAtt = new SocialAttributes(0);
        TerritoryAttributes terriAtt = new TerritoryAttributes(0);
        int status = ConservationStatus.UNKNOWN.getId();
        Names names = new Names("", "", "");
        LifeSpanAttributes lifespanAtt = new LifeSpanAttributes(0, 0);
        int diet = Diet.NONE.getId();
        int ecoregion = Ecoregion.UNKNOWN.getId();
        int biome = Biome.NONE.getId();
        int size = Size.M.getId();
        int family1 = Family.ANTILOCAPRIDAE.getId();
        ArrayList<Integer> continents = new ArrayList<>();
       specie1 = new Specie(names, biomeAtt, feedingAtt, diet, reproAtt, lifespanAtt, status, 
                socialAtt, terriAtt, ecoregion, family1, biome, size, continents);
        Animal animal1 = new AnimalImpl(specie1, "animal1", pad1, Sex.MALE, option);
        animals.put("animal1", animal1);
        pad1 = new PaddockBuilder().name("pad1").animals(animals).coordinates(null).option(option).buildPaddock();
        pad2 = new PaddockBuilder().name("pad2").animals(null).coordinates(null).option(option).buildPaddock();
        tP1 = new TaskPaddock(pad1, task1);
        tP2 = new TaskPaddock(pad1, task2);
        tP3 = new TaskPaddock(pad2, task1);
        timedPaddocks = new HashMap<>();
        managedFamilies = new HashMap<>();
    }

    @Test
    public void shouldReturnZeroWhenTheKeeperDoesNotWorkInThePaddock()
            throws IncorrectDataException, UnknownNameException {
        // Given
        keeper = new AnimalKeeperImpl("keeper1",timedPaddocks, null, managedFamilies, null);
        // When
        keeper.evaluateByFamily(pad1);
        // Then
        Assert.assertTrue(keeper.getManagedFamilies().isEmpty());
    }
    
     @Test
    public void shouldNotReturnZeroWhenTheKeeperWorksInThePaddockAndWithThisFamilyForTheFirstTime()
            throws IncorrectDataException, UnknownNameException {
        // Given
        timedPaddocks.put(pad1, time1);
        keeper = new AnimalKeeperImpl("keeper1",timedPaddocks, null, managedFamilies, null);
        // When
        keeper.evaluateByFamily(pad1);
        // Then
        Assert.assertEquals(1, keeper.getManagedFamilies().size());
        Assert.assertEquals(time1/100.0, keeper.getManagedFamilies().get(specie1.getFamily()), 0.0);
    }
    
      @Test
    public void shouldNotReturnZeroWhenTheKeeperWorksInThePaddockAndWithThisFamilyNotForTheFirstTime()
            throws IncorrectDataException, UnknownNameException {
        // Given
        timedPaddocks.put(pad1, time1);
        managedFamilies.put(specie1.getFamily(), 1.0);
        keeper = new AnimalKeeperImpl("keeper1",timedPaddocks, null, managedFamilies, null);
        // When
        keeper.evaluateByFamily(pad1);
        // Then
        Assert.assertEquals(1, keeper.getManagedFamilies().size());
        Assert.assertEquals(time1/100.0 + 1.0, keeper.getManagedFamilies().get(specie1.getFamily()), 0.0);
    }
}
