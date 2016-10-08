package zoo.animalKeeper;

import exception.IncorrectDataException;
import exception.name.EmptyNameException;
import exception.name.NameException;
import exception.name.UnknownNameException;
import java.util.HashMap;
import launch.options.Option;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import zoo.paddock.IPaddock;
import zoo.paddock.PaddockBuilder;

/**
 *
 * @author doyenm
 */
public class AddTaskToAPaddock {

    public HashMap<TaskPaddock, Double> timedPerPaddock = new HashMap<>();
    public IPaddock pad1;
    public IPaddock pad2;
    public int task1 = 1;
    public int task2 = 2;
    public TaskPaddock tP1;
    public TaskPaddock tP2;
    public TaskPaddock tP3;
    public Double time1 = 1.0;
    public Double time2 = 2.0;
    public Double time3 = 3.0;
    public AnimalKeeper keeper;
    public HashMap<IPaddock, Double> timedPaddocks;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUpClass() throws EmptyNameException, NameException {
        Option option = new Option();
        pad1 = new PaddockBuilder().name("pad1").animals(null).coordinates(null).option(option).buildPaddock();
        pad2 = new PaddockBuilder().name("pad2").animals(null).coordinates(null).option(option).buildPaddock();
        tP1 = new TaskPaddock(pad1, task1);
        tP2 = new TaskPaddock(pad1, task2);
        tP3 = new TaskPaddock(pad2, task1);
        timedPaddocks = new HashMap<>();
        timedPaddocks.put(pad1, 100.0);

    }

    @Test
    public void shouldThrowAExceptionWhenTheCumulativeTimesAreGreaterThan100_1()
            throws IncorrectDataException, UnknownNameException {
        // Given
        keeper = new AnimalKeeperBuilder().name("keeper1")
                .timedPaddocks(timedPaddocks)
                .buildAnimalKeeper();
        // When
        thrown.expect(IncorrectDataException.class);
        HashMap<Task, Double> timedTasks = new HashMap<>();
        timedTasks.put(Task.CLEANING, 101.0);
        keeper.addTaskToAPaddock(pad1, timedTasks);
        // Then
    }

    @Test
    public void shouldThrowAExceptionWhenTheCumulativeTimesAreGreaterThan100_2()
            throws IncorrectDataException, UnknownNameException {
        // Given
        keeper = new AnimalKeeperBuilder().name("keeper1")
                .timedPaddocks(timedPaddocks)
                .buildAnimalKeeper();
        // When
        thrown.expect(IncorrectDataException.class);
        HashMap<Task, Double> timedTasks = new HashMap<>();
        timedTasks.put(Task.CLEANING, 10.0);
        timedTasks.put(Task.ENRICHMENT, 91.0);
        keeper.addTaskToAPaddock(pad1, timedTasks);
        // Then
    }

    @Test
    public void shouldThrowAExceptionWhenTheCumulativeTimesAreGreaterThan100_3()
            throws IncorrectDataException, UnknownNameException {
        // Given
        keeper = new AnimalKeeperBuilder().name("keeper1")
                .timedPaddocks(timedPaddocks)
                .buildAnimalKeeper();
        // When
        thrown.expect(IncorrectDataException.class);
        HashMap<Task, Double> timedTasks1 = new HashMap<>();
        HashMap<Task, Double> timedTasks2 = new HashMap<>();
        timedTasks1.put(Task.CLEANING, 10.0);
        keeper.addTaskToAPaddock(pad1, timedTasks1);
        timedTasks2.put(Task.ENRICHMENT, 91.0);
        keeper.addTaskToAPaddock(pad1, timedTasks2);
        // Then
    }
    
    @Test
    public void shouldNotThrowAExceptionWhenTheCumulativeTimesAreGreaterThan100WithTheSameTask()
            throws IncorrectDataException, UnknownNameException {
        // Given
        keeper = new AnimalKeeperBuilder().name("keeper1")
                .timedPaddocks(timedPaddocks)
                .buildAnimalKeeper();
        // When
        HashMap<Task, Double> timedTasks1 = new HashMap<>();
        HashMap<Task, Double> timedTasks2 = new HashMap<>();
        timedTasks1.put(Task.CLEANING, 10.0);
        keeper.addTaskToAPaddock(pad1, timedTasks1);
        timedTasks2.put(Task.CLEANING, 91.0);
        keeper.addTaskToAPaddock(pad1, timedTasks2);
        // Then
    }
    
     @Test
    public void shouldNotThrowAExceptionWhenTheCumulativeTimesAreGreaterThan100WithTwoPaddocks()
            throws IncorrectDataException, UnknownNameException {
        // Given
        keeper = new AnimalKeeperBuilder().name("keeper1")
                .timedPaddocks(timedPaddocks)
                .buildAnimalKeeper();
        // When
        thrown.expect(IncorrectDataException.class);
        HashMap<Task, Double> timedTasks1 = new HashMap<>();
        HashMap<Task, Double> timedTasks2 = new HashMap<>();
        timedTasks1.put(Task.CLEANING, 10.0);
        keeper.addTaskToAPaddock(pad1, timedTasks1);
        timedTasks2.put(Task.CLEANING, 91.0);
        keeper.addTaskToAPaddock(pad2, timedTasks2);
        // Then
    }
}
