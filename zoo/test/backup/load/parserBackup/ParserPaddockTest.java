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
    public void shouldParseAZooWithNoPaddock()
            throws IOException, JDOMException {
        // Given
        ParserBackUp parser = new ParserBackUp(new File("test/backup/load/parserBackup/testPaddock/test_OK_noPaddock.xml"));
        // When
        List<FakePaddock> paddocks = parser.parserPaddocks();
        // Then
        List<FakePaddock> expectedPaddocks = new ArrayList<>();
        Assert.assertEquals(expectedPaddocks, paddocks);
    }

    @Test
    public void shouldParseAZooWithTwoPaddockAndTheExpectedValues()
            throws IOException, JDOMException {
        // Given
        ParserBackUp parser
                = new ParserBackUp(new File("test/backup/load/parserBackup/testPaddock/test_OK_positivValues.xml"));
        // When
        List<FakePaddock> paddocks = parser.parserPaddocks();
        // Then
        FakePaddock expectedPad1 = new FakePaddock("pad1", 1, 2, 3, 4, 9, 10);
        FakePaddock expectedPad2 = new FakePaddock("pad2", 5, 6, 7, 8, 11, 12);
        List<FakePaddock> expectedPaddocks = new ArrayList<>();
        expectedPaddocks.add(expectedPad1);
        expectedPaddocks.add(expectedPad2);
        Assert.assertEquals(expectedPaddocks.size(), paddocks.size());
        Assert.assertTrue(expectedPad1.equals(paddocks.get(0)));
        Assert.assertTrue(expectedPad2.equals(paddocks.get(1)));
    }

    @Test
    public void shouldParseAZooWithOnePaddockWhenTheBiomeAndThePaddockTypeDoNotCheckAnyExisting()
            throws IOException, JDOMException {
        // Given
        ParserBackUp parser
                = new ParserBackUp(new File("test/backup/load/parserBackup/testPaddock/test_OK_unknownBiomeAndPaddockType.xml"));
        // When
        List<FakePaddock> paddocks = parser.parserPaddocks();
        // Then
        FakePaddock expectedPad1 = new FakePaddock("pad1", 1, 2, 3, 4, 99, 100);
        List<FakePaddock> expectedPaddocks = new ArrayList<>();
        expectedPaddocks.add(expectedPad1);
        Assert.assertEquals(expectedPaddocks.size(), paddocks.size());
        Assert.assertTrue(expectedPad1.equals(paddocks.get(0)));
    }

    @Test
    public void shouldParseAZooWithOnePaddockWhenAllTheValuesAreZero()
            throws IOException, JDOMException {
        // Given
        ParserBackUp parser
                = new ParserBackUp(new File("test/backup/load/parserBackup/testPaddock/test_OK_zero.xml"));
        // When
        List<FakePaddock> paddocks = parser.parserPaddocks();
        // Then
        FakePaddock expectedPad1 = new FakePaddock("pad1", 0, 0, 0, 0, 0, 0);
        List<FakePaddock> expectedPaddocks = new ArrayList<>();
        expectedPaddocks.add(expectedPad1);
        Assert.assertEquals(expectedPaddocks.size(), paddocks.size());
        Assert.assertTrue(expectedPad1.equals(paddocks.get(0)));
    }

    @Test
    public void shouldParseAZooWithOnePaddockWhenAllTheValuesAreNegativ()
            throws IOException, JDOMException {
        // Given
        ParserBackUp parser
                = new ParserBackUp(new File("test/backup/load/parserBackup/testPaddock/test_OK_negativ.xml"));
        // When
        List<FakePaddock> paddocks = parser.parserPaddocks();
        // Then
        FakePaddock expectedPad1 = new FakePaddock("pad1", -1, -2, -3, -4, -5, -6);
        List<FakePaddock> expectedPaddocks = new ArrayList<>();
        expectedPaddocks.add(expectedPad1);
        Assert.assertEquals(expectedPaddocks.size(), paddocks.size());
        Assert.assertTrue(expectedPad1.equals(paddocks.get(0)));
    }

    @Test
    public void shouldParseAZooWithTwoPaddockWhenTheyHaveTheSameName()
            throws IOException, JDOMException {
        // Given
        ParserBackUp parser
                = new ParserBackUp(new File("test/backup/load/parserBackup/testPaddock/test_OK_sameNames.xml"));
        // When
        List<FakePaddock> paddocks = parser.parserPaddocks();
        // Then
        FakePaddock expectedPad1 = new FakePaddock("pad", 1, 2, 3, 4, 9, 10);
        FakePaddock expectedPad2 = new FakePaddock("pad", 5, 6, 7, 8, 11, 12);
        List<FakePaddock> expectedPaddocks = new ArrayList<>();
        expectedPaddocks.add(expectedPad1);
        expectedPaddocks.add(expectedPad2);
        Assert.assertEquals(expectedPaddocks.size(), paddocks.size());
        Assert.assertTrue(expectedPad1.equals(paddocks.get(0)));
        Assert.assertTrue(expectedPad2.equals(paddocks.get(1)));
    }

    @Test
    public void shouldParseAZooWithTwoPaddockWhenTheyHaveTheSameCoordinates()
            throws IOException, JDOMException {
        // Given
        ParserBackUp parser
                = new ParserBackUp(new File("test/backup/load/parserBackup/testPaddock/test_OK_sameCoordinates.xml"));
        // When
        List<FakePaddock> paddocks = parser.parserPaddocks();
        // Then
        FakePaddock expectedPad1 = new FakePaddock("pad1", 1, 2, 3, 4, 9, 10);
        FakePaddock expectedPad2 = new FakePaddock("pad2", 1, 2, 3, 4, 11, 12);
        List<FakePaddock> expectedPaddocks = new ArrayList<>();
        expectedPaddocks.add(expectedPad1);
        expectedPaddocks.add(expectedPad2);
        Assert.assertEquals(expectedPaddocks.size(), paddocks.size());
        Assert.assertTrue(expectedPad1.equals(paddocks.get(0)));
        Assert.assertTrue(expectedPad2.equals(paddocks.get(1)));
    }

    @Test
    public void shouldParseAZooWithTwoPaddockWhenTheyAreTheSame()
            throws IOException, JDOMException {
        // Given
        ParserBackUp parser
                = new ParserBackUp(new File("test/backup/load/parserBackup/testPaddock/test_OK_same.xml"));
        // When
        List<FakePaddock> paddocks = parser.parserPaddocks();
        // Then
        FakePaddock expectedPad1 = new FakePaddock("pad1", 1, 2, 3, 4, 9, 10);
        FakePaddock expectedPad2 = new FakePaddock("pad1", 1, 2, 3, 4, 9, 10);
        List<FakePaddock> expectedPaddocks = new ArrayList<>();
        expectedPaddocks.add(expectedPad1);
        expectedPaddocks.add(expectedPad2);
        Assert.assertEquals(expectedPaddocks.size(), paddocks.size());
        Assert.assertTrue(expectedPad1.equals(paddocks.get(0)));
        Assert.assertTrue(expectedPad2.equals(paddocks.get(1)));
    }
    
     @Test(expected = NumberFormatException.class)
    public void shouldThrowAnNumberFormatExceptionWhenXIsNotANumber()
            throws IOException, JDOMException {
        // Given
        ParserBackUp parser
                = new ParserBackUp(new File("test/backup/load/parserBackup/testPaddock/test_KO_x.xml"));
        // When
        List<FakePaddock> paddocks = parser.parserPaddocks();
        // Then
    }
    
      @Test(expected = NumberFormatException.class)
    public void shouldThrowAnNumberFormatExceptionWhenYIsNotANumber()
            throws IOException, JDOMException {
        // Given
        ParserBackUp parser
                = new ParserBackUp(new File("test/backup/load/parserBackup/testPaddock/test_KO_y.xml"));
        // When
        List<FakePaddock> paddocks = parser.parserPaddocks();
        // Then
    }
    
      @Test(expected = NumberFormatException.class)
    public void shouldThrowAnNumberFormatExceptionWhenHeightIsNotANumber()
            throws IOException, JDOMException {
        // Given
        ParserBackUp parser
                = new ParserBackUp(new File("test/backup/load/parserBackup/testPaddock/test_KO_height.xml"));
        // When
        List<FakePaddock> paddocks = parser.parserPaddocks();
        // Then
    }
    
      @Test(expected = NumberFormatException.class)
    public void shouldThrowAnNumberFormatExceptionWhenWidthIsNotANumber()
            throws IOException, JDOMException {
        // Given
        ParserBackUp parser
                = new ParserBackUp(new File("test/backup/load/parserBackup/testPaddock/test_KO_width.xml"));
        // When
        List<FakePaddock> paddocks = parser.parserPaddocks();
        // Then
    }
    
      @Test(expected = NumberFormatException.class)
    public void shouldThrowAnNumberFormatExceptionWhenBiomeIsNotANumber()
            throws IOException, JDOMException {
        // Given
        ParserBackUp parser
                = new ParserBackUp(new File("test/backup/load/parserBackup/testPaddock/test_KO_biome.xml"));
        // When
        List<FakePaddock> paddocks = parser.parserPaddocks();
        // Then
    }
    
      @Test(expected = NumberFormatException.class)
    public void shouldThrowAnNumberFormatExceptionWhenPaddockTypeIsNotANumber()
            throws IOException, JDOMException {
        // Given
        ParserBackUp parser
                = new ParserBackUp(new File("test/backup/load/parserBackup/testPaddock/test_KO_paddockType.xml"));
        // When
        List<FakePaddock> paddocks = parser.parserPaddocks();
        // Then
    }
    

}
