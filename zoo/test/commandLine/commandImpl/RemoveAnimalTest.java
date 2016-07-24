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
public class RemoveAnimalTest {

    String[] cmd;
    Boolean actual;
    RemoveAnimal cmdImpl;

    @Before
    public void setUpClass() {
        cmdImpl = new RemoveAnimal(null);
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
        cmd = "animal remove name".split(" ");
        // When
        actual = cmdImpl.canExecute(cmd);
        // Then
        assertTrue(actual);
    }

    @Test
    public void shouldReturnFalseWhenTheCommandIsTooLong() {
        // Given
        cmd = "animal remove name long".split(" ");
        // When
        actual = cmdImpl.canExecute(cmd);
        // Then
        assertFalse(actual);
    }

    @Test
    public void shouldReturnFalseWhenTheCommandIsNotEnoughLong() {
        // Given
        cmd = "animal remove".split(" ");
        // When
        actual = cmdImpl.canExecute(cmd);
        // Then
        assertFalse(actual);
    }
    
     @Test
    public void shouldReturnFalseWhenTheFirstElementIsNotAnimal() {
        // Given
        cmd = "animal2 remove name".split(" ");
        // When
        actual = cmdImpl.canExecute(cmd);
        // Then
        assertFalse(actual);
    }
    
     @Test
    public void shouldReturnFalseWhenTheSecondElementIsNotRemove() {
        // Given
        cmd = "animal remove2 name".split(" ");
        // When
        actual = cmdImpl.canExecute(cmd);
        // Then
        assertFalse(actual);
    }
}
