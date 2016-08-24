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
import commandLine.commandImpl.LsFeeding;
import commandLine.commandImpl.LsPaddock;
import commandLine.commandImpl.LsSpecie;
import commandLine.commandImpl.MapZoo;
import commandLine.commandImpl.RemoveAnimal;
import commandLine.commandImpl.RemovePaddock;
import commandLine.commandImpl.SaveZoo;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import launch.play.Play;
import launch.play.Step;

/**
 *
 * @author doyenm
 */
public class TutorialCommandLineManager implements CommandManager {

    ArrayList<Step> steps;
    Play play;
    Iterable<Command> playCommands;
    int i = 0;

    public TutorialCommandLineManager(Play play, ArrayList<Step> steps) {
        this.steps = steps;
        this.play = play;
        // For Paddock and Animal : Ls must be before Detail
        playCommands = asList(new CreateZoo(play), new DetailZoo(play),
                new CreatePaddock(play),
                new LsPaddock(play), new MapZoo(play), new DetailPad(play),
                new Evaluate(play), new BiomePad(play), new BiomeAttributesPaddock(play),
                new CreateAnimal(play), new LsAnimal(play), new DetailAnimal(play),
                new FeedingAnimal(play),
                new RemoveAnimal(play), new RemovePaddock(play),
                new LsSpecie(play), new DetailSpecie(play),
                new LsFeeding(play),
                new SaveZoo(play), new LoadZoo(play));
    }

    @Override
    public String run(String cmd) {
        String[] parse = SplitDoubleQuotes.split(cmd);
        System.out.println(steps.get(i).getPrevious());
        for (Command command : playCommands) {
            if (command.canExecute(parse)) {
                String result = command.execute(parse);
                // If the player uses the correct command line
                // && no execution has been thrown 
                if (steps.get(i).check() && command.isSuccess()) {
                    i += 1;
                    return steps.get(i-1).getSuccess() + "\n" + steps.get(i).getPrevious();
                    // If an exception has been thrown (expected or unexpected command line)
                } else if (!command.isSuccess()){
                    return result;
                }
            }
        }
        return steps.get(i).getFail();
    }

}
