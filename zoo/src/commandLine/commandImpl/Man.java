package commandLine.commandImpl;

import commandLine.Command;
import java.io.File;
import java.io.IOException;
import launch.play.Play;
import utils.ReadingMan;

/**
 *
 * @author doyenm
 */
public class Man implements Command {
    
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
    public String execute(String[] cmd) {
        try {
            if (this.arg.equals("zoo")) {
                this.success = true;
                return ReadingMan.load(new File("doc/man/manZoo"));
            } else if("pad".equals(arg) || "paddock".equals(arg)){
                   this.success = true;
                return ReadingMan.load(new File("doc/man/manPaddock"));
            }           
            return "";
        } catch (IOException ex) {
            return ex.getMessage();
        }
    }
    
    private boolean checkSecondArg(String cmd) {
        if ("zoo".equals(cmd) || "paddock".equals(cmd) || "pad".equals(cmd)) {
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
