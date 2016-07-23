package backup.save;

import zoo.Zoo;
import exception.IncorrectDimensionsException;
import exception.name.EmptyNameException;
import java.io.IOException;
import java.util.HashMap;
import org.jdom2.Element;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import zoo.IZoo;
import zoo.animal.specie.Specie;

/**
 *
 * @author doyenm
 */
public class CreateElementZooTest {

    private static SaveImpl save;

    @Before
    public void setUpClass() {
        save = new SaveImpl();
    }

    @After
    public void tearDownClass() {
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void should_returnElementZooWithCorrectAttributesAndChildren()
            throws EmptyNameException, IncorrectDimensionsException, IOException {
        //Given
        IZoo zoo = new Zoo();
        String expectedName = "foo";
        int expectedWidth = 1;
        int expectedHeight = 2;
        HashMap<String, Specie> expectedSpecies = null;
        int expectedAge = 0;
        int expectedMonthsPerEval = 0;
        int expectedHorizon = 0;
        zoo.initiateZoo("foo", expectedWidth, expectedHeight, expectedSpecies,
                expectedAge, expectedMonthsPerEval, expectedHorizon);
        // When
        Element actualEl = save.createElementZoo(zoo);
        // Then 
        // It only has one attribute
        assertEquals(1, actualEl.getAttributes().size());
        // This attribute is "name" and its value is "foo"
        assertEquals(expectedName, actualEl.getAttributeValue("name"));

        // It has only four children
        assertEquals(4, actualEl.getChildren().size());
        // The first one is  "dimensions"
        assertEquals("dimensions", actualEl.getChildren().get(0).getName());
        Element actualDimensionEl = actualEl.getChildren().get(0);
        assertEquals(2, actualDimensionEl.getChildren().size());
        assertEquals("width", actualDimensionEl.getChildren().get(0).getName());
        assertEquals("height", actualDimensionEl.getChildren().get(1).getName());
        assertEquals(expectedWidth, Integer.parseInt(actualDimensionEl.getChildren().get(0).getText()));
        assertEquals(expectedHeight, Integer.parseInt(actualDimensionEl.getChildren().get(1).getText()));
        ;        // The second one is "age"
        assertEquals("age", actualEl.getChildren().get(1).getName());
        assertEquals(expectedAge, Integer.parseInt(actualEl.getChildren().get(1).getText()));
        // The third one is "monthsPerEvaluation"
        assertEquals("monthsPerEvaluation", actualEl.getChildren().get(2).getName());
        assertEquals(expectedMonthsPerEval, Integer.parseInt(actualEl.getChildren().get(2).getText()));
        // The second one is "horizon"
        assertEquals("horizon", actualEl.getChildren().get(3).getName());
        assertEquals(expectedHorizon, Integer.parseInt(actualEl.getChildren().get(3).getText()));

    }
}
