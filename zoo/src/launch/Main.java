package launch;

import launch.play.FreePlayImpl;
import launch.play.Play;
import gui.MainGUI;
import java.io.IOException;
import launch.options.Option;
import org.jdom2.JDOMException;

/**
 * The Main class
 * @author doyenm
 */
public class Main {

    /**
     * The main method : 
     * @param args the args of the cmd line used to launch the application
     * @throws IOException in case of problems with the instanciation of the species
     * @throws JDOMException in case of problems with the instanciation of the species
     */
    public static void main(String[] args) throws IOException, JDOMException {
        Option options = new Option();
        Play play = new FreePlayImpl(options);
        MainGUI mainGUI = new MainGUI(play);
    }
}
