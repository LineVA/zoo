package launch;

import launch.play.tutorials.TutorialPlayImpl_1;
import launch.play.FreePlayImpl;
import launch.play.Play;
import basicGui.Scan;
import exception.name.EmptyNameException;
import gui.MainGUI;
import java.io.IOException;
import org.jdom2.JDOMException;

/**
 *
 * @author doyenm
 */
public class Main {

    public static void main(String[] args) throws EmptyNameException, IOException, JDOMException {
        Play play = new FreePlayImpl();
        MainGUI mainGUI = new MainGUI(play);
//        Transmission transmission = new Transmission();
//        CommandLineParser parser = new CommandLineParser(transmission);
    }
}
