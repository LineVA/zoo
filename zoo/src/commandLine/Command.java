package commandLine;

/**
 *
 * @author doyenm
 */
public interface Command {
    
  //  IZoo zoo = new Zoo();
    
    public String execute(String[] cmd);
    
    public boolean canExecute(String[] cmd);
    
    public boolean hasInitiateAZoo();
    
    public boolean isSuccess();
    
}
