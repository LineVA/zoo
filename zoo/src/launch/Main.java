package launch;

import launch.play.Play;
import gui.MainGUI;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import launch.options.Option;
import launch.play.tutorials.TutorialPlayImpl_1;
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
         Locale locale = Locale.getDefault();
        ResourceBundle bundle = ResourceBundle.getBundle("i18n.info", locale);
        Option options = new Option();
        Play play = new TutorialPlayImpl_1(bundle, options);
        MainGUI mainGUI = new MainGUI(play);
    }
}
