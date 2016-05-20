package commandLine;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author doyenm
 */
public class SplitDoubleQuotesTest {

    String cmd;
    String[] split;

    @Before
    public void setUpClass() {
    }

    @After
    public void tearDownClass() {

    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void case1() {
        // Given
        cmd = "a b c d";
        // When
        split = SplitDoubleQuotes.split(cmd);
        // Then
        assertEquals(4, split.length);
        assertEquals("a", split[0]);
        assertEquals("b", split[1]);
        assertEquals("c", split[2]);
        assertEquals("d", split[3]);
    }

    /**
     * There is a pair of double quotes aroud one word without any space between
     * the both quotes.
     */
    @Test
    public void case2() {
        // Given
        cmd = "a b \"c\" d";
        // When
        split = SplitDoubleQuotes.split(cmd);
        // Then
        assertEquals(4, split.length);
        assertEquals("a", split[0]);
        assertEquals("b", split[1]);
        assertEquals("c", split[2]);
        assertEquals("d", split[3]);
    }
    
    @Test
    public void case3() {
        // Given
        cmd = "a b \"c \" d";
        // When
        split = SplitDoubleQuotes.split(cmd);
        // Then
        assertEquals(4, split.length);
        assertEquals("a", split[0]);
        assertEquals("b", split[1]);
        assertEquals("c", split[2]);
        assertEquals("d", split[3]);
    }
    
    @Test
    public void case4() {
        // Given
        cmd = "a b \" c\" d";
        // When
        split = SplitDoubleQuotes.split(cmd);
        // Then
        assertEquals(4, split.length);
        assertEquals("a", split[0]);
        assertEquals("b", split[1]);
        assertEquals("c", split[2]);
        assertEquals("d", split[3]);
    }
    
    @Test
    public void case5() {
        // Given
        cmd = "a b \"c e\" d";
        // When
        split = SplitDoubleQuotes.split(cmd);
        // Then
        assertEquals(4, split.length);
        assertEquals("a", split[0]);
        assertEquals("b", split[1]);
        assertEquals("c e", split[2]);
        assertEquals("d", split[3]);
    }
    
    @Test
    public void case6() {
        // Given
        cmd = "a b \"c e\" \"d f\"";
        // When
        split = SplitDoubleQuotes.split(cmd);
        // Then
        assertEquals(4, split.length);
        assertEquals("a", split[0]);
        assertEquals("b", split[1]);
        assertEquals("c e", split[2]);
        assertEquals("d f", split[3]);
    }
}
