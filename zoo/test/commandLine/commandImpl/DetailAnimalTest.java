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
public class DetailAnimalTest {

    String[] cmd;
    Boolean actual;
    DetailAnimal create;

    @Before
    public void setUpClass() {
        create = new DetailAnimal();
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
        cmd = "animal name".split(" ");
        // When
        actual = create.canExecute(cmd);
        // Then
        assertTrue(actual);
    }

    @Test
    public void shouldReturnFalseWhenTheCommandIsTooLong() {
        // Given
        cmd = "animal name long".split(" ");
        // When
        actual = create.canExecute(cmd);
        // Then
        assertFalse(actual);
    }

    @Test
    public void shouldReturnFalseWhenTheCommandIsNotEnoughLong() {
        // Given
        cmd = "animal".split(" ");
        // When
        actual = create.canExecute(cmd);
        // Then
        assertFalse(actual);
    }

    @Test
    public void shouldReturnFalseWhenTheCommandDoesNotBeginWithAnimal() {
        // Given
        cmd = "animal2 name".split(" ");
        // When
        actual = create.canExecute(cmd);
        // Then
        assertFalse(actual);
    }

}
