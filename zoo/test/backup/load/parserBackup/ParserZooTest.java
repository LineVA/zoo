package backup.load.parserBackup;

import backup.load.ParserBackUp;
import java.io.File;
import java.io.IOException;
import org.jdom2.JDOMException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import zoo.FakeZoo;

/**
 *
 * @author doyenm
 */
public class ParserZooTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * We use test1.xml
     */
    @Test
    public void shouldParseAZooWithTheExpectedValues()
            throws IOException, JDOMException {
        // Given
        ParserBackUp parser = new ParserBackUp(new File("test/backup/load/parserBackup/testZoo/test_OK.xml"));
        // When
        FakeZoo zoo = parser.parserZoo();
        // Then
        int expectedWidth = 9;
        int expectedHeight = 10;
        int expectedAge = 11;
        int expectedMonths = 12;
        int expectedHorizon = 13;
        String expectedName = "zooTest";
        FakeZoo expectedZoo = new FakeZoo(expectedName, expectedWidth, expectedHeight,
                expectedAge, expectedMonths, expectedHorizon);
        Assert.assertTrue(expectedZoo.equals(zoo));
    }

    @Test
    public void shouldParseAZooWithAllValuesToZeroWhenTheyAllAreZero()
            throws IOException, JDOMException {
        // Given
        ParserBackUp parser = new ParserBackUp(new File("test/backup/load/parserBackup/testZoo/test_OK_zero.xml"));
        // When
        FakeZoo zoo = parser.parserZoo();
        // Then
        int expectedWidth = 0;
        int expectedHeight = 0;
        int expectedAge = 0;
        int expectedMonths = 0;
        int expectedHorizon = 0;
        String expectedName = "zooTest";
        FakeZoo expectedZoo = new FakeZoo(expectedName, expectedWidth, expectedHeight,
                expectedAge, expectedMonths, expectedHorizon);
        Assert.assertTrue(expectedZoo.equals(zoo));
    }

    @Test
    public void shouldParseAZooWithAllValuesNegativWhenTheyAllAreNegativ()
            throws IOException, JDOMException {
        // Given
        ParserBackUp parser = new ParserBackUp(new File("test/backup/load/parserBackup/testZoo/test_OK_negativ.xml"));
        // When
        FakeZoo zoo = parser.parserZoo();
        // Then
        int expectedWidth = -9;
        int expectedHeight = -10;
        int expectedAge = -11;
        int expectedMonths = -12;
        int expectedHorizon = -13;
        String expectedName = "zooTest";
        FakeZoo expectedZoo = new FakeZoo(expectedName, expectedWidth, expectedHeight,
                expectedAge, expectedMonths, expectedHorizon);
        Assert.assertTrue(expectedZoo.equals(zoo));
    }

    @Test(expected = NumberFormatException.class)
    public void shouldThrowANumberFormatExceptionWhenTheWidthIsNotANumber()
            throws IOException, JDOMException {
        // Given
        ParserBackUp parser = new ParserBackUp(new File("test/backup/load/parserBackup/testZoo/test_KO_width.xml"));
        // When
        FakeZoo zoo = parser.parserZoo();
        // Then
    }

     @Test(expected = NumberFormatException.class)
    public void shouldThrowANumberFormatExceptionWhenTheHeightIsNotANumber()
            throws IOException, JDOMException {
        // Given
        ParserBackUp parser = new ParserBackUp(new File("test/backup/load/parserBackup/testZoo/test_KO_height.xml"));
        // When
        FakeZoo zoo = parser.parserZoo();
        // Then
    }
    
     @Test(expected = NumberFormatException.class)
    public void shouldThrowANumberFormatExceptionWhenTheAgeIsNotANumber()
            throws IOException, JDOMException {
        // Given
        ParserBackUp parser = new ParserBackUp(new File("test/backup/load/parserBackup/testZoo/test_KO_age.xml"));
        // When
        FakeZoo zoo = parser.parserZoo();
        // Then
    }
    
     @Test(expected = NumberFormatException.class)
    public void shouldThrowANumberFormatExceptionWhenTheMonthsPerEvaluationIsNotANumber()
            throws IOException, JDOMException {
        // Given
        ParserBackUp parser = new ParserBackUp(new File("test/backup/load/parserBackup/testZoo/test_KO_months.xml"));
        // When
        FakeZoo zoo = parser.parserZoo();
        // Then
    }
    
     @Test(expected = NumberFormatException.class)
    public void shouldThrowANumberFormatExceptionWhenTheHorizonIsNotANumber()
            throws IOException, JDOMException {
        // Given
        ParserBackUp parser = new ParserBackUp(new File("test/backup/load/parserBackup/testZoo/test_KO_horizon.xml"));
        // When
        FakeZoo zoo = parser.parserZoo();
        // Then
    }
}
