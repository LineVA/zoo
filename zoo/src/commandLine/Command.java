package commandLine;

/**
 * The interface for the different commands
 * @author doyenm
 */
public interface Command {
    
    /**
     * Method used to execute the command line
     * @param cmd the command line,
     * must have been parse before in order to place each string into the array without space
     * @return A message indicating if the command has been successfull or not
     */
    public ReturnExec execute(String[] cmd);
    
    /**
     * State if the command line correspond to a well-known format
     * @param cmd the command line,
     * must have been parse before in order to place each string into the array without space
     * @return  true if it correspond to a well-known format, false else
     */
    public boolean canExecute(String[] cmd);
    
    /**
     * Indicates if a zoo has been initiated
     * @return  true if it has been initiated
     */
    public boolean hasInitiateAZoo();
    
    /**
     * Indicates if the operation is a success or not
     * @return true if it is a success, false else
     */
    public boolean isSuccess();
    
}
