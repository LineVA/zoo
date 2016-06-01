/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package save;

import exception.name.EmptyNameException;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.*;
import org.junit.rules.ExpectedException;

/**
 *
 * @author doyenm
 */
public class CreateFileNameTest {

    private static Save save;
    
    @BeforeClass
    public static void setUpClass() {
        save = new Save();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

     @Rule
     public ExpectedException thrown= ExpectedException.none();
    
    /**
     * Test of createNameFile method, of class Save.
     */
    @Test
    public void should_TestCreateNameFile_returnFooXml_when_nameIsFoo() throws EmptyNameException {
        //Given
        String name = "foo";
        // When
        String actualNameFile = save.createFileName(name);
        // Then 
        String expectedNameFile = "foo.xml";
        assertEquals(expectedNameFile, actualNameFile);
    }
    
     @Test
    public void should_TestCreateNameFile_throw_anEmptyNameException_when_nameIsSpace() throws EmptyNameException {
        //Given
        String name = " ";
        // When
        // Then 
        thrown.expect(EmptyNameException.class);
        String actualName = save.createFileName(name);
    }
}
