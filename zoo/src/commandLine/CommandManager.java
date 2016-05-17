package commandLine;

import commandLine.commandImpl.BiomeAttributesPaddock;
import commandLine.commandImpl.BiomePad;
import commandLine.commandImpl.CreateAnimal;
import commandLine.commandImpl.CreatePaddock;
import commandLine.commandImpl.CreateZoo;
import commandLine.commandImpl.DetailPad;
import commandLine.commandImpl.Evaluate;
import commandLine.commandImpl.LsPaddock;
import commandLine.commandImpl.MapZoo;
import static java.util.Arrays.asList;

/**
 *
 * @author doyenm
 */
public class CommandManager {

    private final Iterable<Command> commands = asList(new CreateZoo(), 
            new CreatePaddock(), new LsPaddock(), new MapZoo(), new DetailPad(),
            new Evaluate(), new BiomePad(), new BiomeAttributesPaddock(),
            new CreateAnimal());
    
    public String run(String cmd){
        String[] parse = cmd.split(" ");
        for(Command command: commands){
            if(command.canExecute(parse)){
                return command.execute(parse);
            }
        }
        return "Unknown command";
    }
}
