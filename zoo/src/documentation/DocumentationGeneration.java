package documentation;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Static methods to open a browser
 * @author doyenm
 */
public class DocumentationGeneration {
    
    /**
     * Open a browser with a given URL
     * @param uri the URI where to open the browser
     * @throws IOException 
     */
    private static void display(URI uri) throws IOException{
        Desktop desk = Desktop.getDesktop();
        desk.browse(uri);
    }
    
    /**
     * Open a browser with a given URI
     * @param url the String to convert in URI and where to open the browser
     * @throws IOException
     * @throws URISyntaxException 
     */
    public static void display(String url) throws IOException, URISyntaxException{
        display(new URI(url));
    }
}
