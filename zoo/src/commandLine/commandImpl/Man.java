package commandLine.commandImpl;

import commandLine.AbstractCommand;
import commandLine.Command;
import commandLine.ReturnExec;
import commandLine.TypeReturn;
import java.io.File;
import java.io.IOException;
import launch.play.Play;
import utils.ReadingMan;

/**
 *
 * @author doyenm
 */
public class Man extends AbstractCommand implements Command {
    
    Play play;
    String arg = "";
    
    public Man(Play play) {
        this.play = play;
    }
    
    boolean success = false;
    
    @Override
    public boolean hasInitiateAZoo() {
        return false;
    }
    
    @Override
    public ReturnExec execute(String[] cmd) {
        try {
            if ("zoo".equals(this.arg)) {
                this.success = true;
                return new ReturnExec(ReadingMan.load(new File("doc/man/manZoo")), TypeReturn.SUCCESS);
            } else if("pad".equals(arg) || "paddock".equals(arg)){
                   this.success = true;
                return new ReturnExec(ReadingMan.load(new File("doc/man/manPaddock")), TypeReturn.SUCCESS);
            } else if("cmd".equals(arg) || "command".equals(arg)){
                   this.success = true;
                return new ReturnExec(ReadingMan.load(new File("doc/man/cmdLines")), TypeReturn.SUCCESS);
            } else if("specie".equals(arg) || "spec".equals(arg)){
                   this.success = true;
                return new ReturnExec(ReadingMan.load(new File("doc/man/manSpecie")), TypeReturn.SUCCESS);
            }                       
            return new ReturnExec("", TypeReturn.SUCCESS);
        } catch (IOException ex) {
            return new ReturnExec(ex.getMessage(), TypeReturn.ERROR);
        }
    }
    
    private boolean checkSecondArg(String cmd) {
        if ("zoo".equals(cmd) || 
                "paddock".equals(cmd) || "pad".equals(cmd) || 
                "cmd".equals(cmd) || "command".equals(cmd) ||
                "specie".equals(cmd) || "spec".equals(cmd)) {
            this.arg = cmd;
            return true;            
        }
        return false;
    }
    
    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 2) {
            if (cmd[0].equals("man") && checkSecondArg(cmd[1])) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean isSuccess() {
        return this.success;
    }
}
