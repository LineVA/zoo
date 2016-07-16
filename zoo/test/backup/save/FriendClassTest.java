package backup.save;

import exception.name.EmptyNameException;
import java.io.IOException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import zoo.Zoo;

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
        Zoo zoo = new Zoo();
        zoo.initiateZoo("name", 1, 1, null, 0, 0);
        thrown.expect(NullPointerException.class);
        // When
        String actualName = zoo.getName(null);
        // Then

    }

}
