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
public class DetailSpecieTest {

    String[] cmd;
    Boolean actual;
    DetailSpecie create;

    @Before
    public void setUpClass() {
        create = new DetailSpecie(null);
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
    public void shouldReturnTrueWhenTheCommandIsCorrectWithSpecie() {
        // Given
        cmd = "specie name".split(" ");
        // When
        actual = create.canExecute(cmd);
        // Then
        assertTrue(actual);
    }
    
     @Test
    public void shouldReturnTrueWhenTheCommandIsCorrectWithSpec() {
        // Given
        cmd = "spec name".split(" ");
        // When
        actual = create.canExecute(cmd);
        // Then
        assertTrue(actual);
    }

    @Test
    public void shouldReturnFalseWhenTheCommandIsTooLong() {
        // Given
        cmd = "spec name long".split(" ");
        // When
        actual = create.canExecute(cmd);
        // Then
        assertFalse(actual);
    }

    @Test
    public void shouldReturnFalseWhenTheCommandIsNotEnoughLong() {
        // Given
        cmd = "spec".split(" ");
        // When
        actual = create.canExecute(cmd);
        // Then
        assertFalse(actual);
    }

    @Test
    public void shouldReturnFalseWhenTheCommandDoesNotBeginWithSpecOrSpecie() {
        // Given
        cmd = "spec2 name".split(" ");
        // When
        actual = create.canExecute(cmd);
        // Then
        assertFalse(actual);
    }
}
