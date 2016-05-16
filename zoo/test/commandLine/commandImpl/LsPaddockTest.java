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
public class LsPaddockTest {

    String[] cmd;
    Boolean actual;
    LsPaddock cmdImpl;

    @Before
    public void setUpClass() {
        cmdImpl = new LsPaddock();
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
    public void shouldReturnTrueWhenTheCommandIsCorrectWithPad() {
        // Given
        cmd = "pad ls".split(" ");
        // When
        actual = cmdImpl.canExecute(cmd);
        // Then
        assertTrue(actual);
    }

    @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectWithPaddock() {
        // Given
        cmd = "paddock ls".split(" ");
        // When
        actual = cmdImpl.canExecute(cmd);
        // Then
        assertTrue(actual);
    }

    @Test
    public void shouldReturnFalseWhenTheCommandIsTooLong() {
        // Given
        cmd = "pad ls long".split(" ");
        // When
        actual = cmdImpl.canExecute(cmd);
        // Then
        assertFalse(actual);
    }

    @Test
    public void shouldReturnFalseWhenTheCommandIsNotEnoughLong() {
        // Given
        cmd = "pad".split(" ");
        // When
        actual = cmdImpl.canExecute(cmd);
        // Then
        assertFalse(actual);
    }

    @Test
    public void shouldReturnFalseWhenTheFirstElementIsNotPadOrPaddock() {
        // Given
        cmd = "pad2 ls".split(" ");
        // When
        actual = cmdImpl.canExecute(cmd);
        // Then
        assertFalse(actual);
    }

    @Test
    public void shouldReturnFalseWhenTheSecondElementIsNotCreate() {
        // Given
        cmd = "pad ls2".split(" ");
        // When
        actual = cmdImpl.canExecute(cmd);
        // Then
        assertFalse(actual);
    }
}
