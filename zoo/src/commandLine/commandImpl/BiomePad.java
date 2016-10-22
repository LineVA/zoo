package commandLine.commandImpl;

import commandLine.Command;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import exception.name.EmptyNameException;
import exception.name.UnknownNameException;
import launch.play.Play;
import zoo.paddock.IPaddock;

/**
 *Command line : 
 * @author doyenm
 */
public class BiomePad implements Command {

    Play play;
    boolean success = false;

    @Override
    public boolean isSuccess() {
        return this.success;
    }

    public BiomePad(Play play) {
        this.play = play;
    }

    @Override
    public boolean hasInitiateAZoo() {
        return false;
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        try {
            IPaddock pad = this.play.getZoo().findPaddockByName(cmd[1]);
            pad.setBiome(cmd[3]);
            this.success = true;
            return new ReturnExec(this.play.getOption().getGeneralCmdBundle().getString("BIOMES_PADDOCK") + cmd[3], TypeReturn.SUCCESS);
        } catch (UnknownNameException | EmptyNameException ex) {
            return new ReturnExec(ex.getMessage(), TypeReturn.ERROR);
        } 
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 4) {
            if (cmd[0].equals("pad") || cmd[0].equals("paddock")) {
                if (cmd[2].equals("--biome") || cmd[2].equals("-b")) {
                    return true;
                }
            }
        }
        return false;
    }
}
