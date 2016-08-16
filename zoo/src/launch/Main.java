package launch;

import launch.play.FreePlayImpl;
import launch.play.Play;
import exception.name.EmptyNameException;
import gui.MainGUI;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import launch.options.Option;
import org.jdom2.JDOMException;

/**
 *
 * @author doyenm
 */
public class Main {

    public static void main(String[] args) throws EmptyNameException, IOException, JDOMException {
         Locale locale = Locale.getDefault();
        ResourceBundle bundle = ResourceBundle.getBundle("i18n.info", locale);
        Option options = new Option();
        Play play = new FreePlayImpl(bundle, options);
        MainGUI mainGUI = new MainGUI(play);
//        Transmission transmission = new Transmission();
//        CommandLineParser parser = new CommandLineParser(transmission);
    }
}
