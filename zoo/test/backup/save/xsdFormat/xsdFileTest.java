package save.xsdFormat;

import java.io.File;
import java.io.IOException;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaders;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * We test several xml well- bad formatted in order to check if our xsd schema
 * is correct
 *
 * @author doyenm
 */
public class xsdFileTest {

    static org.jdom2.Document doc;

    @BeforeClass
    public static void setUpClass() {
        doc = new Document();
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void should_throwNoExceptionWhenTheXmlIsWellFormatted() throws JDOMException, IOException {
        //Given
        String testPath = "./test/save/xsdFormat/wellFormatted.xml"; 
        // When
        SAXBuilder sax = new SAXBuilder(XMLReaders.XSDVALIDATING);
        // Then 
        doc = sax.build(new File(testPath));
    }
}
