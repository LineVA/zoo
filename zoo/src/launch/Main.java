package launch;

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
        Play play = new Play();
        MainGUI mainGUI = new MainGUI(play);
    }
}
