package basicGui;

import commandLine.CommandManager;
import commandLine.commandManagerImpl.FreeCommandManager;
import java.util.Scanner;
import launch.play.Play;

/**
 *
 * @author doyenm
 */
public class Scan {

    Scanner scan;
    Play play;
    
    public Scan(Play play) {
        this.scan = new Scanner(System.in);
        this.play = play;
    }

    public void read() {
        CommandManager manager = new FreeCommandManager(play);
        while (true) {
            System.out.println(manager.run(scan.nextLine()));
        }
    }
}
