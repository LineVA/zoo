package zoo;

import java.util.Observable;
import java.util.Scanner;

/**
 *
 * @author doyenm
 */
public class BirthObservable extends Observable {

    public String name = "a";

    public void askAndWait(String mother, String father, String sex) {
        Scanner scan = new Scanner(System.in);
        boolean waiting = true;
        System.out.println("A new baby, a "+sex+", is born from "+ mother + " and "+father+". Its name will be : " );
        while (waiting) {
            name = scan.nextLine();
            waiting = false;
        }
        this.setChanged();
        this.notifyObservers(name);
    }
}
