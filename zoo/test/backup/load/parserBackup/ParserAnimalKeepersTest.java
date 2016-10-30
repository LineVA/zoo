package backup.load.parserBackup;

import backup.load.ParserBackUp;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jdom2.JDOMException;
import org.junit.Assert;
import org.junit.Test;
import zoo.animalKeeper.FakeAnimalKeeper;

/**
 *
 * @author doyenm
 */
public class ParserAnimalKeepersTest {

    @Test
       public void shouldParseAnAnimalKeeper()
            throws IOException, JDOMException {
        // Given
        ParserBackUp parser = new ParserBackUp(new File("gameBackUps/keeper.xml"));
        // When
        List<FakeAnimalKeeper> keepers = parser.parserAnimalKeepers();
        // Then
        List<FakeAnimalKeeper> expectedKeepers = new ArrayList<>();
        Assert.assertEquals(expectedKeepers, keepers);
    }
    
}
