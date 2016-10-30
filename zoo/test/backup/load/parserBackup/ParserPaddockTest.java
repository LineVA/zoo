package backup.load.parserBackup;

import backup.load.ParserBackUp;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jdom2.JDOMException;
import org.junit.Assert;
import org.junit.Test;
import zoo.paddock.FakePaddock;

/**
 *
 * @author doyenm
 */
public class ParserPaddockTest {

    @Test
    /**
     * We use test2.xml
     */
    public void shouldParseAZooWithNoPaddock()
            throws IOException, JDOMException {
        // Given
        ParserBackUp parser = new ParserBackUp(new File("test/backup/load/parserBackup/test2.xml"));
        // When
        List<FakePaddock> paddocks = parser.parserPaddocks();
        // Then
        List<FakePaddock> expectedPaddocks = new ArrayList<>();
        Assert.assertEquals(expectedPaddocks, paddocks);
    }

    @Test
    /**
     * We use test3.xml
     */
    public void shouldParseAZooWithTwoPaddockAndTheExpectedValues()
            throws IOException, JDOMException {
        // Given
        ParserBackUp parser = new ParserBackUp(new File("test/backup/load/parserBackup/test3.xml"));
        // When
        List<FakePaddock> paddocks = parser.parserPaddocks();
        // Then
        FakePaddock expectedPad1 = new FakePaddock("pad1", 1, 2, 3, 4);
        FakePaddock expectedPad2 = new FakePaddock("pad2", 5, 6, 7, 8);
        List<FakePaddock> expectedPaddocks = new ArrayList<>();
        expectedPaddocks.add(expectedPad1);
        expectedPaddocks.add(expectedPad2);
        Assert.assertEquals(expectedPaddocks.size(), paddocks.size());
        // Pad1
        Assert.assertEquals(expectedPad1.getHeight(), paddocks.get(0).getHeight());
        Assert.assertEquals(expectedPad1.getWidth(), paddocks.get(0).getWidth());
        Assert.assertEquals(expectedPad1.getX(), paddocks.get(0).getX());
        Assert.assertEquals(expectedPad1.getY(), paddocks.get(0).getY());
        Assert.assertEquals(expectedPad1.getName(), paddocks.get(0).getName());
          // Pad2
        Assert.assertEquals(expectedPad2.getHeight(), paddocks.get(1).getHeight());
        Assert.assertEquals(expectedPad2.getWidth(), paddocks.get(1).getWidth());
        Assert.assertEquals(expectedPad2.getX(), paddocks.get(1).getX());
        Assert.assertEquals(expectedPad2.getY(), paddocks.get(1).getY());
        Assert.assertEquals(expectedPad2.getName(), paddocks.get(1).getName());

    }

}
