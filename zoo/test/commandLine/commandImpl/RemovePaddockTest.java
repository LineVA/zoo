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
public class RemovePaddockTest {

    String[] cmd;
    Boolean actual;
    RemovePaddock cmdImpl;

    @Before
    public void setUpClass() {
        cmdImpl = new RemovePaddock(null);
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
    public void shouldReturnTrueWhenTheCommandIsCorrectWithPaddock() {
        // Given
        cmd = "paddock remove name".split(" ");
        // When
        actual = cmdImpl.canExecute(cmd);
        // Then
        assertTrue(actual);
    }
    
     @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectWithPad() {
        // Given
        cmd = "pad remove name".split(" ");
        // When
        actual = cmdImpl.canExecute(cmd);
        // Then
        assertTrue(actual);
    }

    @Test
    public void shouldReturnFalseWhenTheCommandIsTooLong() {
        // Given
        cmd = "pad remove name long".split(" ");
        // When
        actual = cmdImpl.canExecute(cmd);
        // Then
        assertFalse(actual);
    }

    @Test
    public void shouldReturnFalseWhenTheCommandIsNotEnoughLong() {
        // Given
        cmd = "pad remove".split(" ");
        // When
        actual = cmdImpl.canExecute(cmd);
        // Then
        assertFalse(actual);
    }
    
     @Test
    public void shouldReturnFalseWhenTheFirstElementIsNotPadNorPaddock() {
        // Given
        cmd = "pad2 remove name".split(" ");
        // When
        actual = cmdImpl.canExecute(cmd);
        // Then
        assertFalse(actual);
    }
    
     @Test
    public void shouldReturnFalseWhenTheSecondElementIsNotRemove() {
        // Given
        cmd = "pad remove2 name".split(" ");
        // When
        actual = cmdImpl.canExecute(cmd);
        // Then
        assertFalse(actual);
    }
}
