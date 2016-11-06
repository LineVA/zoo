package commandLine.commandImpl;

import commandLine.AbstractCommand;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import launch.play.Play;
import utils.Constants;
import utils.ReadingMan;

/**
 *
 * @author doyenm
 */
public class Man extends AbstractCommand {
    
    Play play;
    String arg = "";
    
    public Man(Play play) {
       super(play);
    }
    
    @Override
    public ReturnExec execute(String[] cmd) {
        try {
            if (Constants.ZOO.equalsIgnoreCase(this.arg)) {
                super.setSuccess(true);
                return new ReturnExec(ReadingMan.load(new File("doc/man/manZoo")), TypeReturn.SUCCESS);
            } else if(Arrays.asList(Constants.PAD_OR_PADDOCK).contains(cmd[0])){
                   super.setSuccess(true);
                return new ReturnExec(ReadingMan.load(new File("doc/man/manPaddock")), TypeReturn.SUCCESS);
            } else if("cmd".equalsIgnoreCase(arg) || "command".equalsIgnoreCase(arg)){
                   super.setSuccess(true);
                return new ReturnExec(ReadingMan.load(new File("doc/man/cmdLines")), TypeReturn.SUCCESS);
            } else if("specie".equalsIgnoreCase(arg) || "spec".equalsIgnoreCase(arg)){
                   super.setSuccess(true);
                return new ReturnExec(ReadingMan.load(new File("doc/man/manSpecie")), TypeReturn.SUCCESS);
            }                       
            return new ReturnExec("", TypeReturn.SUCCESS);
        } catch (IOException ex) {
            return new ReturnExec(ex.getMessage(), TypeReturn.ERROR);
        }
    }
    
    private boolean checkSecondArg(String cmd) {
        if (Constants.ZOO.equalsIgnoreCase(cmd) || 
               Arrays.asList(Constants.PAD_OR_PADDOCK).contains(cmd) || 
                "cmd".equalsIgnoreCase(cmd) || "command".equalsIgnoreCase(cmd) ||
                "specie".equalsIgnoreCase(cmd) || "spec".equalsIgnoreCase(cmd)) {
            this.arg = cmd;
            return true;            
        }
        return false;
    }
    
    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 2) {
            if (cmd[0].equalsIgnoreCase("man") && checkSecondArg(cmd[1])) {
                return true;
            }
        }
        return false;
    }
}
