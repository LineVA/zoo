package main;

import basicGui.Scan;
import exception.name.EmptyNameException;
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
//        MainGUI mainGUI = new MainGUI(parser);
        //HashMap<String, Specie> species = InstanciateSpecies.instanciateSpecies("resources/species");
//        IZoo zoo = new Zoo();
  //      zoo.initiateZoo("mainZoo", 50, 50, species);
        Scan scan = new Scan();
        scan.read();
    }
}
