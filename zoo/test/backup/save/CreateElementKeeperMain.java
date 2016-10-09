package backup.save;

import exception.name.EmptyNameException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import launch.options.Option;
import zoo.IZoo;
import zoo.Zoo;
import zoo.animal.Animal;
import zoo.animalKeeper.AnimalKeeper;
import zoo.animalKeeper.AnimalKeeperImpl;
import zoo.animalKeeper.TaskPaddock;
import zoo.paddock.IPaddock;
import zoo.paddock.Paddock;
import zoo.paddock.PaddockCoordinates;

/**
 * Not really a test, more a way to chek the creation of element animalKeepers
 *
 * @author doyenm
 */
public class CreateElementKeeperMain {

    public static void main(String[] args) throws EmptyNameException, IOException {
        IZoo zoo = new Zoo();
        Option option = new Option();
        zoo.initiateZoo("zooName", 10, 10, null, 1, 2, 3);
        zoo.setOption(option);
        ArrayList<IPaddock> neightbourhood = new ArrayList<>();
        Map<String, Animal> animals = new HashMap<>();
        IPaddock pad1 = new Paddock("pad1", new PaddockCoordinates(1, 1, 1, 1), neightbourhood, animals, 0, 1, option);
        IPaddock pad2 = new Paddock("pad2", new PaddockCoordinates(2, 2, 2, 2), neightbourhood, animals, 0, 1, option);

        Map<IPaddock, Double> timedPaddocks = new HashMap<>();
        timedPaddocks.put(pad1, 10.0);
        timedPaddocks.put(pad2, 20.0);
        Map<TaskPaddock, Double> timedTasksPerPaddock = new HashMap<>();
        timedTasksPerPaddock.put(new TaskPaddock(pad1, 1), 11.0);
        timedTasksPerPaddock.put(new TaskPaddock(pad1, 2), 12.0);
        timedTasksPerPaddock.put(new TaskPaddock(pad2, 1), 21.0);
        AnimalKeeper keeper = new AnimalKeeperImpl("keeper1", timedPaddocks, timedTasksPerPaddock, null, null);

        zoo.addPaddock(pad1);
        zoo.addPaddock(pad2);
        zoo.addKeeper(keeper);

        Save save = new SaveImpl();
        save.saveZoo(zoo, "keeper");
    }
}
