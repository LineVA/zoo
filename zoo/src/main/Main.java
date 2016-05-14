package main;

import exception.name.EmptyNameException;
import java.io.IOException;
import java.util.HashMap;
import org.jdom2.JDOMException;
import zoo.Zoo;
import zoo.animal.specie.Specie;

/**
 *
 * @author doyenm
 */
public class Main {

    public static void main(String[] args) throws EmptyNameException, IOException, JDOMException {
//        Transmission transmission = new Transmission();
//        CommandLineParser parser = new CommandLineParser(transmission);
//        MainGUI mainGUI = new MainGUI(parser);
        HashMap<String, Specie> species = InstanciateSpecies.instanciateSpecies("resources/species");
        Zoo zoo = new Zoo("mainZoo", 50, 50, species);
        System.out.println(zoo.getSpecies().size());

    }
}
