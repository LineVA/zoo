package zoo.biome;

import exception.IncorrectDimensionsException;
import exception.name.AlreadyUsedNameException;
import exception.name.EmptyNameException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import zoo.Biome;

/**
 *
 * @author doyenm
 */
public class BiomeTest {

    Biome biome;
    String expectedName;
    double expectedNight;
    double expectedDay;
    double expectedPluvio;
    double expectedTreeD;
    double expectedTreeH;
    double expectedDrop;
    double expectedSalinity;
    double expectedHumidity;

    @Before
    public static void setUpClass() throws AlreadyUsedNameException,
            IncorrectDimensionsException, EmptyNameException {
    }

    @After
    public static void tearDownClass() {
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    // Not useful
    @Test
    public void shouldConstructABiomeWithTheExpectedFields() {
        // Given
        expectedName = "foo";
        expectedNight = 1.0;
        expectedDay = 2.0;
        expectedPluvio = 3.0;
        expectedTreeD = 4.0;
        expectedTreeH = 5.0;
        expectedDrop = 6.0;
        expectedSalinity = 7.0;
        expectedHumidity = 0.8;
        // When
//        biome = new Biome(expectedName, expectedNight, expectedDay,
//                expectedPluvio, expectedTreeD, expectedTreeH, expectedDrop,
//                expectedSalinity, expectedHumidity);
    }

}
