package commandLine.commandImpl;

import org.junit.After;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author doyenm
 */
public class MapZooTest {

    String[] cmd;
    Boolean actual;
    MapZoo cmdImpl;

    @Before
    public void setUpClass() {
        cmdImpl = new MapZoo();
    }

    @After
    public void tearDownClass() {

    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * canExecute tests
     */
    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrect() {
        // Given
        cmd = "zoo map".split(" ");
        // When
        actual = cmdImpl.canExecute(cmd);
        // Then
        assertTrue(actual);
    }

    @Test
    public void shouldReturnFalseWhenTheCommandIsTooLong() {
        // Given
        cmd = "zoo map long".split(" ");
        // When
        actual = cmdImpl.canExecute(cmd);
        // Then
        assertFalse(actual);
    }

    @Test
    public void shouldReturnFalseWhenTheCommandIsNotEnoughLong() {
        // Given
        cmd = "zoo".split(" ");
        // When
        actual = cmdImpl.canExecute(cmd);
        // Then
        assertFalse(actual);
    }
    
     @Test
    public void shouldReturnFalseWhenTheFirstElementIsNotZoo() {
        // Given
        cmd = "zoo2 map".split(" ");
        // When
        actual = cmdImpl.canExecute(cmd);
        // Then
        assertFalse(actual);
    }
    
     @Test
    public void shouldReturnFalseWhenTheSecondElementISNotMap() {
        // Given
        cmd = "zoo map2".split(" ");
        // When
        actual = cmdImpl.canExecute(cmd);
        // Then
        assertFalse(actual);
    }
}
