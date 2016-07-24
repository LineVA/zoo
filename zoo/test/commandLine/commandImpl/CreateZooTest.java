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
public class CreateZooTest {

    String[] cmd;
    Boolean actual;
    CreateZoo create;
    
    @Before
    public void setUpClass() {
        create = new CreateZoo(null);
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
        cmd = "zoo create foo 1 1".split(" ");
        // When
        actual = create.canExecute(cmd);
        // Then
        assertTrue(actual);
    }
    
    @Test
    public void shouldReturnFalseWhenTheCommandIsTooLong() {
        // Given
        cmd = "zoo create foo 1 1 long".split(" ");
        // When
        actual = create.canExecute(cmd);
        // Then
        assertFalse(actual);
    }
    
    @Test
    public void shouldReturnFalseWhenTheCommandIsNotEnoughLong() {
        // Given
        cmd = "zoo create foo 1".split(" ");
        // When
        actual = create.canExecute(cmd);
        // Then
        assertFalse(actual);
    }
    
    @Test
    public void shouldReturnFalseWhenTheCommandDoesNotBeginWithZoo() {
        // Given
        cmd = "zoo2 create foo 1 1".split(" ");
        // When
        actual = create.canExecute(cmd);
        // Then
        assertFalse(actual);
    }
    
    @Test
    public void shouldReturnFalseWhenTheCommandDoesNotHaveCreateInSecondPosition() {
        // Given
        cmd = "zoo create2 create 1 1".split(" ");
        // When
        actual = create.canExecute(cmd);
        // Then
        assertFalse(actual);
    }
}
