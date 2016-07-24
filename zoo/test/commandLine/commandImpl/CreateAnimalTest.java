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
public class CreateAnimalTest {

    String[] cmd;
    Boolean actual;
    CreateAnimal create;

    @Before
    public void setUpClass() {
        create = new CreateAnimal(null);
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
        cmd = "animal create pad name specie sex".split(" ");
        // When
        actual = create.canExecute(cmd);
        // Then
        assertTrue(actual);
    }

    @Test
    public void shouldReturnFalseWhenTheCommandIsTooLong() {
        // Given
        cmd = "animal create pad name specie sex long".split(" ");
        // When
        actual = create.canExecute(cmd);
        // Then
        assertFalse(actual);
    }

    @Test
    public void shouldReturnFalseWhenTheCommandIsNotEnoughLong() {
        // Given
        cmd = "animal create pad name specie".split(" ");
        // When
        actual = create.canExecute(cmd);
        // Then
        assertFalse(actual);
    }

    @Test
    public void shouldReturnFalseWhenTheCommandDoesNotBeginWithAnimal() {
        // Given
        cmd = "animal2 create pad name specie sex".split(" ");
        // When
        actual = create.canExecute(cmd);
        // Then
        assertFalse(actual);
    }

    @Test
    public void shouldReturnFalseWhenTheCommandDoesNotHaveCreateInSecondPosition() {
        // Given
        cmd = "animal create2 pad name specie sex".split(" ");
        // When
        actual = create.canExecute(cmd);
        // Then
        assertFalse(actual);
    }
}