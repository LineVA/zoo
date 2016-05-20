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
public class LsAnimalTest {

    String[] cmd;
    Boolean actual;
    LsAnimal cmdImpl;

    @Before
    public void setUpClass() {
        cmdImpl = new LsAnimal();
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
        cmd = "animal ls".split(" ");
        // When
        actual = cmdImpl.canExecute(cmd);
        // Then
        assertTrue(actual);
    }

    @Test
    public void shouldReturnFalseWhenTheCommandIsTooLong() {
        // Given
        cmd = "animal ls long".split(" ");
        // When
        actual = cmdImpl.canExecute(cmd);
        // Then
        assertFalse(actual);
    }

    @Test
    public void shouldReturnFalseWhenTheCommandIsNotEnoughLong() {
        // Given
        cmd = "animal".split(" ");
        // When
        actual = cmdImpl.canExecute(cmd);
        // Then
        assertFalse(actual);
    }

    @Test
    public void shouldReturnFalseWhenTheFirstElementIsNotAnimal() {
        // Given
        cmd = "animal2 ls".split(" ");
        // When
        actual = cmdImpl.canExecute(cmd);
        // Then
        assertFalse(actual);
    }

    @Test
    public void shouldReturnFalseWhenTheSecondElementIsNotLs(){
        cmd = "animal ls2".split(" ");
        // When
        actual = cmdImpl.canExecute(cmd);
        // Then
        assertFalse(actual);
    }
}
