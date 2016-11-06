package commandLine;

import launch.play.Play;

/**
 *
 * @author doyenm
 */
public abstract class NewZoo extends AbstractCommand{

    public NewZoo(Play play) {
        super(play);
    }
    
    private String[] creationCmd = null;
    
      public ReturnExec confirmLoading(String[] cmd) {
        super.setLoading(false);
        if (cmd.length == 1) {
            if ("yes".equalsIgnoreCase(cmd[0]) || "y".equalsIgnoreCase(cmd[0])) {
                return executeLoading(cmd);
            }
        }
        return new ReturnExec("Le zoo n'a pas été sauvegardé", TypeReturn.ERROR);
    }
      
      private ReturnExec executeLoading(String[] cmd){
          return null;
      }

}
