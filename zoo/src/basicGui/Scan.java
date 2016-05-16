package basicGui;

import commandLine.CommandManager;
import java.util.Scanner;

/**
 *
 * @author doyenm
 */
public class Scan {

    Scanner scan;

    public Scan() {
        this.scan = new Scanner(System.in);
    }

    public void read() {
        CommandManager manager = new CommandManager();
        while (true) {
            System.out.println(manager.run(scan.nextLine()));
        }
    }
}
