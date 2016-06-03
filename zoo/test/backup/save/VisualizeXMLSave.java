package backup.save;

import zoo.Zoo;
import exception.IncorrectDimensionsException;
import exception.name.AlreadyUsedNameException;
import exception.name.EmptyNameException;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import zoo.IZoo;
import zoo.animal.Animal;
import zoo.animal.Names;
import zoo.animal.conservation.ConservationStatus;
import zoo.animal.death.LifeSpanAttributes;
import zoo.animal.feeding.FeedingAttributes;
import zoo.animal.reproduction.ReproductionAttributes;
import zoo.animal.reproduction.Sex;
import zoo.animal.social.SocialAttributes;
import zoo.animal.specie.Specie;
import zoo.paddock.IPaddock;
import zoo.paddock.TerritoryAttributes;
import zoo.paddock.biome.BiomeAttributes;

/**
 *
 * @author doyenm
 */
public class VisualizeXMLSave {

    private static Save save;

    @BeforeClass
    public static void setUpClass() {
        save = new SaveImpl();
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * Not really an unit test It is more like an integration tests except the
     * assert must be made by hand
     *
     * @throws exception.name.EmptyNameException
     * @throws exception.IncorrectDimensionsException
     */
    @Test
    public void should_CreateAFileWithTheCorectNameAndContent()
            throws EmptyNameException, IncorrectDimensionsException, IOException {
        IZoo zoo = new Zoo();
        HashMap<String, Specie> species = new HashMap<>();
        BiomeAttributes biome = new BiomeAttributes(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        FeedingAttributes feeding = new FeedingAttributes(0.0);
        ReproductionAttributes reproduction = new ReproductionAttributes(0, 0, 0.0, 0);
        LifeSpanAttributes lifeSpan = new LifeSpanAttributes(0, 0);
        SocialAttributes social = new SocialAttributes(0);
        TerritoryAttributes territory = new TerritoryAttributes(0.0);

        Specie spec1 = new Specie(new Names("Zèbre", "Mountain zebra", "scientific1"), biome, feeding, reproduction, lifeSpan, ConservationStatus.UNKNOWN, social, territory);
        Specie spec2 = new Specie(new Names("Lion", "Lion", "scientific2"), biome, feeding, reproduction, lifeSpan, ConservationStatus.UNKNOWN, social, territory);
        species.put("spec1", spec1);
        species.put("spec2", spec2);
        zoo.initiateZoo("myZoo2", 10, 10, species, 90);
        try {
            zoo.addPaddock("padName1", 1, 2, 3, 4);
            zoo.addPaddock("padName2", 5, 8, 1, 1);
            IPaddock pad1 = zoo.findPaddockByName("padName1");
            IPaddock pad2 = zoo.findPaddockByName("padName2");
            pad1.addAnimal(new Animal(spec1, "animal1", pad1, Sex.MALE, 1));
            pad1.addAnimal(new Animal(spec2, "animal2", pad1, Sex.MALE, 2));
            pad2.addAnimal(new Animal(spec1, "animal3", pad2, Sex.MALE, 3));

        } catch (AlreadyUsedNameException ex) {
            Logger.getLogger(VisualizeXMLSave.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        save.saveZoo(zoo, "test3");
    }

}
