package zoo.paddock;

import exception.IncorrectDimensionsException;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import zoo.Biome;
import zoo.Paddock;
import zoo.PaddockCoordinates;

/**
 *
 * @author doyenm
 */
public class SetBiomeTest {

    @Before
    public void setUpClass() {
    }

    @After
    public void tearDownClass() {
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldNotChangeTheDefaultValueOfABiomeWhenChangeThemForOnePaddock()
            throws IncorrectDimensionsException {
        // Given
        PaddockCoordinates coor = new PaddockCoordinates(1, 1, 1, 1);
        Paddock pad1 = new Paddock("A", coor);
        // When
        pad1.setBiome(Biome.DESERT);
        pad1.setDayTemperature(33.0);
        // Then
        assertEquals(0.0, Biome.DESERT.getDayTemperature(), 0.0);
    }

}
