package commandLine.commandManagerImpl;

import commandLine.Command;
import commandLine.CommandManager;
import commandLine.SplitDoubleQuotes;
import commandLine.commandImpl.BiomeAttributesPaddock;
import commandLine.commandImpl.BiomePad;
import commandLine.commandImpl.CreateAnimal;
import commandLine.commandImpl.CreatePaddock;
import commandLine.commandImpl.CreateZoo;
import commandLine.commandImpl.DetailAnimal;
import commandLine.commandImpl.DetailPad;
import commandLine.commandImpl.DetailSpecie;
import commandLine.commandImpl.DetailZoo;
import commandLine.commandImpl.Evaluate;
import commandLine.commandImpl.FeedingAnimal;
import commandLine.commandImpl.LoadZoo;
import commandLine.commandImpl.LsAnimal;
import commandLine.commandImpl.LsBiome;
import commandLine.commandImpl.LsEcoregion;
import commandLine.commandImpl.LsFeeding;
import commandLine.commandImpl.LsPaddock;
import commandLine.commandImpl.LsSpecie;
import commandLine.commandImpl.MapZoo;
import commandLine.commandImpl.Options;
import commandLine.commandImpl.RemoveAnimal;
import commandLine.commandImpl.RemovePaddock;
import commandLine.commandImpl.SaveZoo;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import launch.play.Play;
import launch.play.Step;
import lombok.Getter;

/**
 *
 * @author doyenm
 */
public class TutorialCommandLineManager implements CommandManager {

    ArrayList<Step> steps;
    Play play;
    Iterable<Command> playCommands;
    int i = 0;
    @Getter
    private String firstLine;

    public TutorialCommandLineManager(Play play, ArrayList<Step> steps) {
        this.steps = steps;
        this.firstLine = steps.get(0).getPrevious();
        this.play = play;
        // For Paddock and Animal : Ls must be before Detail
        playCommands = asList(new CreateZoo(play), new DetailZoo(play),
                new CreatePaddock(play),
                new LsPaddock(play), new MapZoo(play), new DetailPad(play),
                new Evaluate(play), new BiomePad(play), new BiomeAttributesPaddock(play),
                new CreateAnimal(play), new LsAnimal(play), new DetailAnimal(play),
                new FeedingAnimal(play),
                new RemoveAnimal(play), new RemovePaddock(play), 
                new LsBiome(play), new LsEcoregion(play),
                new LsSpecie(play), new DetailSpecie(play),
                new LsFeeding(play),
                new SaveZoo(play), new LoadZoo(play), new Options(play));
    }

    @Override
    public String run(String cmd) {
        if (!steps.isEmpty() && i < steps.size()) {
            String[] parse = SplitDoubleQuotes.split(cmd);
            for (Command command : playCommands) {
                if (command.canExecute(parse)) {
                    String result = command.execute(parse);
                    // If the player uses the correct command line
                    // && no execution has been thrown 
                    if (steps.get(i).check() && command.isSuccess()) {
                        if (i < steps.size() - 1) {
                            i += 1;
                            return result + "\n" + steps.get(i).getPrevious();
                        } else {
                            return result + steps.get(i).getSuccess();
                        }
                        // If an exception has been thrown (expected or unexpected command line)
                    } else {
                        return result + steps.get(i).getFail();
                    }
                }
            }
        }
        return steps.get(i).getFail();
    }

}
