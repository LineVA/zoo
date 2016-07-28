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
//        Transmission transmission = new Transmission();
//        CommandLineParser parser = new CommandLineParser(transmission);
        MainGUI mainGUI = new MainGUI();
        //HashMap<String, Specie> species = InstanciateSpecies.instanciateSpecies("resources/species");
//        IZoo zoo = new Zoo();
        //      zoo.initiateZoo("mainZoo", 50, 50, species);
        Play play = new FreePlayImpl();
        Scan scan = new Scan(play);
        scan.read();
    }
}
