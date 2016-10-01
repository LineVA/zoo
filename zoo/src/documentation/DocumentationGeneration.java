package documentation;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 *
 * @author doyenm
 */
public class DocumentationGeneration {
    private static void display(URI uri) throws IOException{
        Desktop desk = Desktop.getDesktop();
        desk.browse(uri);
    }
    
    public static void displayWikipedia(String url) throws IOException, URISyntaxException{
        display(new URI(url));
    }
}
