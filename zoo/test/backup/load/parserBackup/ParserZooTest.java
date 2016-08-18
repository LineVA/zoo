package backup.load.parserBackup;

import backup.load.ParserBackUp;
import java.io.File;
import java.io.IOException;
import org.jdom2.JDOMException;
import org.junit.Assert;
import org.junit.Test;
import zoo.FakeZoo;

/**
 *
 * @author doyenm
 */
public class ParserZooTest {

    @Test
    /**
     * We use test1.xml
     */
    public void shouldParseAZooWithTheExpectedValues()
            throws IOException, JDOMException {
        // Given
        ParserBackUp parser = new ParserBackUp(new File("test/backup/load/parserBackup/test1.xml"));
        // When
        FakeZoo zoo = parser.parserZoo();
        // Then
        int expectedWidth = 9;
        int expectedHeight = 10;
        int expectedAge = 11;
        int expectedMonths = 12;
        int expectedHorizon = 13;
        String expectedLanguage = "fr";
        String expectedName = "zooTest";
        Assert.assertEquals(expectedAge, zoo.getAge());
        Assert.assertEquals(expectedHeight, zoo.getHeight());
        Assert.assertEquals(expectedWidth, zoo.getWidth());
        Assert.assertEquals(expectedMonths, zoo.getMonthsPerEvaluation());
    }

}
