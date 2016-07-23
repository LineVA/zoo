package backup.save;

import exception.name.EmptyNameException;
import java.io.IOException;
import java.util.HashMap;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import zoo.Zoo;
import zoo.animal.specie.Specie;

/**
 *
 * @author doyenm
 */
public class FriendClassTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void ShouldGetNameOfZooThrowAnExceptionWhenFriendIsNull()
            throws EmptyNameException, IOException {
        // Given
        String expectedName = "name";
        int expectedWidth = 1;
        int expectedHeight = 1;
        HashMap<String, Specie> expectedSpecies = null;
        int expectedAge = 0;
        int expectedMonthsPerEval = 0;
        int expectedHorizon = 0;
        Zoo zoo = new Zoo();
        zoo.initiateZoo(expectedName, expectedWidth, expectedHeight, expectedSpecies, 
                expectedAge, expectedMonthsPerEval, expectedHorizon);
        thrown.expect(NullPointerException.class);
        // When
        String actualName = zoo.getName(null);
        // Then

    }

}
