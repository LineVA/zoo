package gui.visual.onAction;

import gui.visual.CommandVisual;
import java.io.IOException;
import java.util.Map;
import launch.InstanciateSpecies;
import org.jdom2.JDOMException;
import zoo.animal.specie.Specie;

/**
 *
 * @author doyenm
 */
public class VisualCreateZoo implements CommandVisual {

    String[] cmd;

    public VisualCreateZoo(String[] cmd) {
        this.cmd = cmd;
    }

    @Override
    public void execute(String[] cmd) {
        try {
            Map<String, Specie> species = InstanciateSpecies.instanciateSpecies("resources/species");
            int monthsPerEvaluation = 6;
            int horizon = 5;
            int age = 0;
            this.zoo.initiateZoo(cmd[0], Integer.parseInt(cmd[1]),
                    Integer.parseInt(cmd[2]), species, age, monthsPerEvaluation, horizon);
        } catch (IOException | JDOMException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
