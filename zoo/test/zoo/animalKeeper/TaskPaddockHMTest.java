package zoo.animalKeeper;

import exception.name.EmptyNameException;
import exception.name.NameException;
import java.util.HashMap;
import launch.options.Option;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import zoo.paddock.IPaddock;
import zoo.paddock.PaddockBuilder;

/**
 *
 * @author doyenm
 */
public class TaskPaddockHMTest {

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

    @Before
    public void setUpClass() throws EmptyNameException, NameException {
        Option option = new Option();
        pad1 = new PaddockBuilder().name("pad1").animals(null).coordinates(null).option(option).buildPaddock();
        pad2 = new PaddockBuilder().name("pad2").animals(null).coordinates(null).option(option).buildPaddock();
         tP1 = new TaskPaddock(pad1, task1);
         tP2 = new TaskPaddock(pad1, task2);
         tP3 = new TaskPaddock(pad2, task1);
    }

//    <pad1, task1>, <pad1, task2>, <pad2, task1>
    @Test
    public void shouldAcceptTheFollowingMap() {
        // Given
        // When
        Double expectedTask1 = 100.0;
        Double expectedTask2 = 100.0;
        timedPerPaddock.putIfAbsent(tP1, time1);
        expectedTask1 = timedPerPaddock.putIfAbsent(tP2, time2);
        expectedTask2 = timedPerPaddock.putIfAbsent(tP3, time3);
        // Then
        Assert.assertNull(expectedTask1);
        Assert.assertNull(expectedTask2);

    }

}
