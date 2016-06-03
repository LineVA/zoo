package commandLine;

import commandLine.commandImpl.BiomeAttributesPaddock;
import commandLine.commandImpl.BiomePad;
import commandLine.commandImpl.CreateAnimal;
import commandLine.commandImpl.CreatePaddock;
import commandLine.commandImpl.CreateZoo;
import commandLine.commandImpl.DetailAnimal;
import commandLine.commandImpl.DetailPad;
import commandLine.commandImpl.DetailSpecie;
import commandLine.commandImpl.Evaluate;
import commandLine.commandImpl.LoadZoo;
import commandLine.commandImpl.LsAnimal;
import commandLine.commandImpl.LsPaddock;
import commandLine.commandImpl.LsSpecie;
import commandLine.commandImpl.MapZoo;
import commandLine.commandImpl.SaveZoo;
import commandLine.commandImpl.DetailZoo;
import static java.util.Arrays.asList;
import main.Play;

/**
 *
 * @author doyenm
 */
public class CommandManager {

    Play play;
    private final Iterable<Command> commands;

    public CommandManager(Play play) {
        this.play = play;
        // For Paddock and Animal : Ls must be before Detail
        commands = asList(new CreateZoo(play), new DetailZoo(play), 
                new CreatePaddock(play),
                new LsPaddock(play), new MapZoo(play), new DetailPad(play),
                new Evaluate(play), new BiomePad(play), new BiomeAttributesPaddock(play),
                new CreateAnimal(play), new LsAnimal(play), new DetailAnimal(play),
                new LsSpecie(play), new DetailSpecie(play),
                new SaveZoo(play), new LoadZoo(play));
    }

    public String run(String cmd) {
        // String[] parse = cmd.split(" ");
        String[] parse = SplitDoubleQuotes.split(cmd);
        for (Command command : commands) {
            if (command.canExecute(parse)) {
                return command.execute(parse);
            }
        }
        return "Unknown command";
    }
}
