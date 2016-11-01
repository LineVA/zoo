package zoo.animalKeeper;

import backup.save.SaveImpl;
import exception.IncorrectDataException;
import exception.name.EmptyNameException;
import exception.name.UnauthorizedNameException;
import exception.name.UnknownNameException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import launch.options.Option;
import lombok.Getter;
import utils.Utils;
import zoo.NameVerifications;
import zoo.animal.specie.Family;
import zoo.paddock.IPaddock;

/**
 *
 * @author doyenm
 */
public class AnimalKeeperImpl implements AnimalKeeper {

    @Getter
    private final String name;
    @Getter
    private Map<IPaddock, Double> timedPaddocks;
    @Getter
    private Map<TaskPaddock, Double> timedTaskPerPaddock;
    @Getter
    private Map<Integer, Double> managedFamilies;
    private Map<Integer, Double> managedTasks;

    private Option option;

    public AnimalKeeperImpl(String name, Map<IPaddock, Double> timedPaddocks,
            Map<TaskPaddock, Double> timedTaskPerPaddock,
            Map<Integer, Double> managedFamilies, Map<Integer, Double> managedTasks,
            Option option) throws EmptyNameException, UnauthorizedNameException {
        this.option = option;
        NameVerifications.verify(name, this.option.getKeeperBundle());
        this.name = name;
        this.timedPaddocks = timedPaddocks;
        this.managedFamilies = managedFamilies;
        this.managedTasks = managedTasks;
        this.timedTaskPerPaddock = timedTaskPerPaddock;
    }

    private boolean checkIndiviualTime(Double time) {
        return time > 0.0 && time <= 100.0;
    }

    private boolean checkCumulativeTime(List<Double> times) {
        Double cumulativeTime = 0.0;
        cumulativeTime = times.stream().map((time) -> time)
                .reduce(cumulativeTime, (accumulator, _item) -> accumulator + _item);
        return cumulativeTime >= 0.0 && cumulativeTime <= 100.0;
    }

    private List<Double> extractTimes(Map<IPaddock, Double> occupations) {
        Object[] times = occupations.values().toArray();
        List<Double> timesList = new ArrayList<>();
        for (Object time : times) {
            timesList.add((Double) time);
        }
        return timesList;
    }

    @Override
    public void addTimedPaddocks(List<IPaddock> paddocks, List<Double> times)
            throws IncorrectDataException {
        Map<IPaddock, Double> oldTimedPaddocks = new HashMap<>();
        oldTimedPaddocks.putAll(this.timedPaddocks);
        try {
            if (paddocks.size() == times.size()) {
                for (int i = 0; i < paddocks.size(); i++) {
                    addAPaddock(paddocks.get(i), times.get(i));
                }
            }
        } catch (IncorrectDataException ex) {
            this.timedPaddocks = oldTimedPaddocks;
            throw ex;
        }
    }

    private void addAPaddock(IPaddock paddock, Double time) throws IncorrectDataException {
        if (checkIndiviualTime(time)) {
            this.timedPaddocks.put(paddock, time);
            if (!this.checkCumulativeTime(extractTimes(this.timedPaddocks))) {
                throw new IncorrectDataException(
                        this.option.getKeeperBundle().getString("ERROR_CUMULATIVE_TIMES"));
            }
        } else {
            throw new IncorrectDataException(
                    this.option.getKeeperBundle().getString("ERROR_INDIVIDUAL_TIME"));
        }
    }

    private Map<Integer, Double> findTimedTasksByPaddock(IPaddock paddock) {
        Map<Integer, Double> timedTasks = new HashMap<>();
        for (Map.Entry<TaskPaddock, Double> entry : this.timedTaskPerPaddock.entrySet()) {
            if (entry.getKey().getPaddock().equals(paddock)) {
                timedTasks.put(entry.getKey().getTask(), entry.getValue());
            }
        }
        return timedTasks;
    }
    
    private Map<TaskPaddock, Double> findTimedTasksForOtherPaddocks(IPaddock paddock){
          Map<TaskPaddock, Double> timedTasks = new HashMap<>();
        for (Map.Entry<TaskPaddock, Double> entry : this.timedTaskPerPaddock.entrySet()) {
            if (!entry.getKey().getPaddock().equals(paddock)) {
                timedTasks.put(entry.getKey(), entry.getValue());
            }
        }
        return timedTasks;
    }

    private Map<Integer, Double> expectTimedTasks(
            Map<Integer, Double> oldTimedTasks, Map<Task, Double> askedTimedTasks) {
        Map<Integer, Double> actualTimedTasks = new HashMap<>();
        for (Task task : Task.values()) {
            if (askedTimedTasks.containsKey(task)) {
                actualTimedTasks.put(task.getId(), askedTimedTasks.get(task));
            } else if (oldTimedTasks.containsKey(task.getId())) {
                actualTimedTasks.put(task.getId(), oldTimedTasks.get(task.getId()));
            }
        }
        return actualTimedTasks;
    }

    private List<Double> extractTimesFromTimedTasks(Map<Integer, Double> timedTasks) {
        Object[] times = timedTasks.values().toArray();
        List<Double> timesList = new ArrayList<>();
        for (Object time : times) {
            timesList.add((Double) time);
        }
        return timesList;
    }

    private boolean checkTimes(List<Double> times) {
        for (Double time : times) {
            if (!this.checkIndiviualTime(time)) {
                return false;
            }
        }
        return this.checkCumulativeTime(times);
    }

    private Map<TaskPaddock, Double>
            reconstructTimedTasksPerPaddock(IPaddock paddock, Map<Integer, Double> timedTasks) {
        Map<TaskPaddock, Double> timedTasksPerPaddock = new HashMap<>();
        for (Map.Entry<Integer, Double> entry : timedTasks.entrySet()) {
            timedTasksPerPaddock.put(new TaskPaddock(paddock, entry.getKey()), entry.getValue());
        }
        return timedTasksPerPaddock;
    }

    private Map<Task, Double> prepareMap(List<Task> tasks, List<Double> times) {
        Map<Task, Double> timedTasks = new HashMap<>();
        for (int i = 0; i < tasks.size(); i++) {
            timedTasks.put(tasks.get(i), times.get(i));
        }
        return timedTasks;
    }

    private boolean checkTask(List<Task> tasks) {
        for (Task task : tasks) {
            if (task.equals(Task.UNKNOWN)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void addTaskToAPaddock(IPaddock paddock, List<Task> tasks, List<Double> times)
            throws IncorrectDataException, UnknownNameException {
        // Check if this animal keeper is associated to pad
        if (this.timedPaddocks.containsKey(paddock)) {
            if (this.checkTask(tasks)) {
                // We retrieve all the timed tasks associated to this paddock
                Map<Integer, Double> oldTimedTasks = findTimedTasksByPaddock(paddock);
                  // We retrieve all the timed tasks associated to the other paddocks
                Map<TaskPaddock, Double> otherTimedTasks = findTimedTasksForOtherPaddocks(paddock);
                // Prepare Map of the new TimedTasks
                Map<Task, Double> newTimedTasks = prepareMap(tasks, times);
                // We remplace the old tasks by the new ones
                Map<Integer, Double> askedTimedTasks = expectTimedTasks(oldTimedTasks, newTimedTasks);
                // We check if the timer are consistent or not
                List<Double> askedTimes = extractTimesFromTimedTasks(askedTimedTasks);
                if (checkTimes(askedTimes)) {
                    this.timedTaskPerPaddock = reconstructTimedTasksPerPaddock(paddock, askedTimedTasks);
                    this.timedTaskPerPaddock.putAll(otherTimedTasks);
                } else {
                    throw new IncorrectDataException(
                            this.option.getKeeperBundle().getString("ERROR_CUMULATIVE_TIMES"));
                }
            } else {
                throw new IncorrectDataException(
                        this.option.getKeeperBundle().getString("ERROR_TASK_UNKNOWN"));
            }
        } else {
            throw new IncorrectDataException(
                    this.option.getKeeperBundle().getString("ERROR_PADDOCK_NO_TIME"));
        }
    }

    private double computeFamilies(IPaddock paddock) {
        return this.timedPaddocks.get(paddock) / 100.0;
    }

    private void evaluateByFamily() {
        for (Map.Entry<IPaddock, Double> paddock : this.timedPaddocks.entrySet()) {
            List<Integer> familiesList = paddock.getKey().listFamiliesById();
            for (Integer family : familiesList) {
                double init = 0.0;
                if (this.managedFamilies.containsKey(family)) {
                    init = this.managedFamilies.get(family);
                }
                init += computeFamilies(paddock.getKey());
                this.managedFamilies.put(family, init);
            }
        }
    }

    private Double computeTasks(TaskPaddock taskPaddock) {
        return this.timedPaddocks.get(taskPaddock.getPaddock()) / 100.0
                * this.timedTaskPerPaddock.get(taskPaddock) / 100.0;
    }

    private void evaluateByTask() {
        double init = 0.0;
        for (Map.Entry<TaskPaddock, Double> entry : this.timedTaskPerPaddock.entrySet()) {
            if (this.managedTasks.containsKey(entry.getKey().getTask())) {
                init = this.managedTasks.get(entry.getKey().getTask());
                this.managedTasks.put(entry.getKey().getTask(), init + computeTasks(entry.getKey()));
            }
        }
    }

    @Override
    public List<String> info() throws UnknownNameException {
        List<String> info = new ArrayList<>();
        info.add(name);
        info.add(this.formattingPaddocks());
        info.add(this.formattingManagedFamilies());
        info.add(this.formattingManagedTasks());
        return info;
    }

    private double computeTimedTaskUnknown(IPaddock paddock) {
        double unknownTask = 100.0;
        for (Map.Entry<TaskPaddock, Double> timedTask : this.timedTaskPerPaddock.entrySet()) {
            if (timedTask.getKey().getPaddock().equals(paddock)) {
                unknownTask -= timedTask.getValue();
            }
        }
        return unknownTask;
    }

    private String formattingPaddocks() {
        String info = "";
        String subsInfo = "";
        for (Map.Entry<IPaddock, Double> entry : this.timedPaddocks.entrySet()) {
            subsInfo = "Enclos " + entry.getKey().getName();
            subsInfo += " (" + Utils.truncate(entry.getValue()) + "%)";
            subsInfo += " : ";
            for (Map.Entry<TaskPaddock, Double> entry2 : this.timedTaskPerPaddock.entrySet()) {
                if (entry2.getKey().getPaddock().equals(entry.getKey())) {
                    subsInfo += "tâche " + entry2.getKey().getTask() + " : " + Utils.truncate(entry2.getValue()) + "%, ";
                }
            }
            subsInfo += "tâche " + Task.UNKNOWN.getId() + " : " 
                    + Utils.truncate(computeTimedTaskUnknown(entry.getKey())) + "%";
            info += subsInfo + "\n";
        }
        return info;
    }

    private String formattingManagedFamilies() throws UnknownNameException {
        String info = "";
        String subsInfo;
        for (Map.Entry<Integer, Double> entry : this.managedFamilies.entrySet()) {
            subsInfo = Family.UNKNOWN.findById(entry.getKey()).toStringByLanguage();
            subsInfo += " (";
            subsInfo += Utils.truncate(entry.getValue()) + ") ; ";
            info += subsInfo;
        }
        return "Familles gérées : " + info;
    }

    private String formattingManagedTasks() throws UnknownNameException {
        String info = "";
        String subsInfo;
        for (Map.Entry<Integer, Double> entry : this.managedTasks.entrySet()) {
            subsInfo = Task.UNKNOWN.findById(entry.getKey()).toString();
            subsInfo += " (";
            subsInfo += Utils.truncate(entry.getValue()) + ") ; ";
            info += subsInfo;
        }
        return "Tâches gérées : " + info;
    }

    @Override
    public void evolve() {
        this.evaluateByFamily();
        this.evaluateByTask();
    }

    @Override
    public void removeTimedPaddocks(List<IPaddock> paddocks) {
        for (IPaddock pad : paddocks) {
            if (this.timedPaddocks.containsKey(pad)) {
                this.timedPaddocks.remove(pad);
                this.removeAllTimedTaskForAPaddock(pad);
            }
        }
    }

    private void removeAllTimedTaskForAPaddock(IPaddock paddock) {
        List<TaskPaddock> tasksPaddockList = new ArrayList<>();
        for (Task task : Task.values()) {
            tasksPaddockList.add(new TaskPaddock(paddock, task.getId()));
        }
        removeTimedTasksPerPaddock(tasksPaddockList);
    }

    @Override
    public void removeTimedTasksPerPaddock(List<TaskPaddock> tasksPaddock) {
        for (TaskPaddock taskPad : tasksPaddock) {
            if (this.timedTaskPerPaddock.containsKey(taskPad)) {
                this.timedTaskPerPaddock.remove(taskPad);
            }
        }
    }

    @Override
    public boolean workInGivenPaddock(IPaddock paddock) {
        return this.timedPaddocks.containsKey(paddock);
    }

    @Override
    public boolean isMakingCleaningInThePaddock(IPaddock paddock) {
        return this.timedTaskPerPaddock.containsKey(new TaskPaddock(paddock, Task.CLEANING.getId()));
    }

    @Override
    public boolean isMakingFeedingInThePaddock(IPaddock paddock) {
        return this.timedTaskPerPaddock.containsKey(new TaskPaddock(paddock, Task.FEEDING.getId()));
    }

    @Override
    public boolean isMakingMedicalTrainingInThePaddock(IPaddock paddock) {
        return this.timedTaskPerPaddock.containsKey(new TaskPaddock(paddock, Task.MEDICALTRAINING.getId()));
    }

    @Override
    public boolean isMakingEnrichmentInThePaddock(IPaddock paddock) {
        return this.timedTaskPerPaddock.containsKey(new TaskPaddock(paddock, Task.ENRICHMENT.getId()));
    }

    /**
     * Getters
     */
    @Override
    public String getName(SaveImpl.FriendSave friend) {
        friend.hashCode();
        return this.name;
    }

    @Override
    public Map<IPaddock, Double> getTimedPaddocks(SaveImpl.FriendSave friend) {
        friend.hashCode();
        return this.timedPaddocks;
    }

    @Override
    public Map<TaskPaddock, Double> getTimedTaskPerPaddock(SaveImpl.FriendSave friend) {
        friend.hashCode();
        return this.timedTaskPerPaddock;
    }

    @Override
    public Map<Integer, Double> getManagedFamilies(SaveImpl.FriendSave friend) {
        friend.hashCode();
        return this.managedFamilies;
    }

    @Override
    public Map<Integer, Double> getManagedTasks(SaveImpl.FriendSave friend) {
        friend.hashCode();
        return this.managedTasks;
    }

}
