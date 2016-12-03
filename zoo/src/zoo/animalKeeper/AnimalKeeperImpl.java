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
 * Concrete class for the Animal Keeper
 *
 * @author doyenm
 */
public class AnimalKeeperImpl implements AnimalKeeper {

    /**
     * Name of the keeper
     */
    @Getter
    private final String name;
    /**
     * Map of the paddocks on which the keeper is affected and the proportion of
     * time he spend in this paddock
     */
    @Getter
    private Map<IPaddock, Double> timedPaddocks;
    /**
     * Map of the {paddock + task} on which the keeper is affected and the
     * proportion of time he spend on this combination
     */
    @Getter
    private Map<TaskPaddock, Double> timedTaskPerPaddock;
    /**
     * Map on the competence of the keeper with the families
     */
    @Getter
    private Map<Integer, Double> managedFamilies;
    /**
     * Map on the competence of the keeper with the tasks
     */
    private Map<Integer, Double> managedTasks;

    /**
     * Option used to know the current language
     */
    private Option option;

    /**
     * Constructor
     *
     * @param name its name
     * @param timedPaddocks its Map of paddock and time
     * @param timedTaskPerPaddock its Map of TaskPaddock and time
     * @param managedFamilies its map of families and grade
     * @param managedTasks its map of tasks and grade
     * @param option the option
     * @throws EmptyNameException if name is empty
     * @throws UnauthorizedNameException if name is part of the unauthorized
     * names
     */
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

    /**
     * Check if a time is greater than 0.0 and lower or equals than 100.0
     *
     * @param time the time to check
     * @return true if the time check the conditions, false else
     */
    private boolean checkIndiviualTime(Double time) {
        return time > 0.0 && time <= 100.0;
    }

    /**
     * Check if the sum of Double containing in a list is greater or equals than
     * 0.0 and lower or equals than 100.0
     *
     * @param times the list to check
     * @return true if it verifies the conditions
     */
    private boolean checkCumulativeTime(List<Double> times) {
        Double cumulativeTime = 0.0;
        cumulativeTime = times.stream().map((time) -> time)
                .reduce(cumulativeTime, (accumulator, _item) -> accumulator + _item);
        return cumulativeTime >= 0.0 && cumulativeTime <= 100.0;
    }

    /**
     * Extract the value of a map
     *
     * @param occupations the map to parse
     * @return a list of the values of the map, parse to Double
     */
    private List<Double> extractTimes(Map<IPaddock, Double> occupations) {
        Object[] times = occupations.values().toArray();
        List<Double> timesList = new ArrayList<>();
        for (Object time : times) {
            timesList.add((Double) time);
        }
        return timesList;
    }

    /**
     * Add several timed paddocks to the ones of the keeper
     *
     * @param paddocks the list of paddocks
     * @param times the list of the corresponding times
     * @throws IncorrectDataException if the times are incorrect (individualy ou
     * collectively)
     */
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

    /**
     * Add a timed paddock to the ones of the keeper
     *
     * @param paddock the paddock to add
     * @param time the corresponding time
     * @throws IncorrectDataException if time is incorrect (individualy or in
     * addition with the others)
     */
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

    /**
     * Find the map of the timed tasks for the given paddock
     *
     * @param paddock the paddock for which we search the timed tasks
     * @return the map of the timed tasks for paddock
     */
    private Map<Integer, Double> findTimedTasksByPaddock(IPaddock paddock) {
        Map<Integer, Double> timedTasks = new HashMap<>();
        for (Map.Entry<TaskPaddock, Double> entry : this.timedTaskPerPaddock.entrySet()) {
            if (entry.getKey().getPaddock().equals(paddock)) {
                timedTasks.put(entry.getKey().getTask(), entry.getValue());
            }
        }
        return timedTasks;
    }

    /**
     * Find the map of the timed PaddockTask which do not corresponded to a
     * given paddock
     *
     * @param paddock the paddock for which we do not search the timed tasks
     * @return the map of the timed TaskPaddock
     */
    private Map<TaskPaddock, Double> findTimedTasksForOtherPaddocks(IPaddock paddock) {
        Map<TaskPaddock, Double> timedTasks = new HashMap<>();
        for (Map.Entry<TaskPaddock, Double> entry : this.timedTaskPerPaddock.entrySet()) {
            if (!entry.getKey().getPaddock().equals(paddock)) {
                timedTasks.put(entry.getKey(), entry.getValue());
            }
        }
        return timedTasks;
    }

    /**
     * Ciompose a map of timed tasks by taking the ones of askedTimedTasks and
     * the ones of oldTimedTasks which are NOT in askedTimedTasks
     *
     * @param oldTimedTasks the old timed tasks use d to complete
     * askedTimedTAsks
     * @param askedTimedTasks map of timed tasks we must take and complete
     * @return the corresponding map
     */
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

    /**
     * Extract the values of a map of timed tasks
     *
     * @param timedTasks the map to parse
     * @return the values of the map
     */
    private List<Double> extractTimesFromTimedTasks(Map<Integer, Double> timedTasks) {
        Object[] times = timedTasks.values().toArray();
        List<Double> timesList = new ArrayList<>();
        for (Object time : times) {
            timesList.add((Double) time);
        }
        return timesList;
    }

    /**
     * Check the validity of times, collectively and individualy
     *
     * @param times the times to check
     * @return true if the sum is greater or equals than 0.0 and lower or equals
     * of 100.0 and if each time is greater than 0.0 and lower or equand than
     * 100.0
     */
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

    /**
     * Add timed tasks to a paddock
     *
     * @param paddock the paddock in which we are
     * @param tasks the tasks to add
     * @param times the corresponding times
     * @throws IncorrectDataException if the times are incorrect
     * @throws UnknownNameException if the tasks are unknown
     */
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

    private double computeFamilies(IPaddock paddock, int monthsPerEvaluation) {
        return this.timedPaddocks.get(paddock) / 100.0 * monthsPerEvaluation;
    }

    private void evaluateByFamily(int monthsPerEvaluation) {
        for (Map.Entry<IPaddock, Double> paddock : this.timedPaddocks.entrySet()) {
            List<Integer> familiesList = paddock.getKey().listFamiliesById();
            for (Integer family : familiesList) {
                double init = 0.0;
                if (this.managedFamilies.containsKey(family)) {
                    init = this.managedFamilies.get(family);
                }
                init += computeFamilies(paddock.getKey(), monthsPerEvaluation);
                this.managedFamilies.put(family, init);
            }
        }
    }

    private Double computeTasks(TaskPaddock taskPaddock, int monthsPerEvaluation) {
        return this.timedPaddocks.get(taskPaddock.getPaddock()) / 100.0
                * this.timedTaskPerPaddock.get(taskPaddock) / 100.0
                * monthsPerEvaluation;
    }

    private void evaluateByTask(int monthsPerEvaluation) {
        double init = 0.0;
        for (Map.Entry<TaskPaddock, Double> entry : this.timedTaskPerPaddock.entrySet()) {
            if (this.managedTasks.containsKey(entry.getKey().getTask())) {
                init = this.managedTasks.get(entry.getKey().getTask());
            }
            this.managedTasks.put(entry.getKey().getTask(), init
                    + computeTasks(entry.getKey(), monthsPerEvaluation));
    }
}

/**
 * Informations of the keeper
 *
 * @return the list of this informations
 * @throws UnknownNameException if a task or a family is unknown
 */
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

    /**
     * Re-compute the grades of the keeper by tasks and families
     */
    @Override
        public void evolve(int monthsPerEvaluation) {
        this.evaluateByFamily(monthsPerEvaluation);
        this.evaluateByTask(monthsPerEvaluation);
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

    /**
     * Remove a timed paddock
     * @param paddock  the paddock to remove
     */
    private void removeAllTimedTaskForAPaddock(IPaddock paddock) {
        List<TaskPaddock> tasksPaddockList = new ArrayList<>();
        for (Task task : Task.values()) {
            tasksPaddockList.add(new TaskPaddock(paddock, task.getId()));
        }
        removeTimedTasksPerPaddock(tasksPaddockList);
    }

    /**
     * remove a list of paddocks
     * @param tasksPaddock  the list to remove
     */
    @Override
        public void removeTimedTasksPerPaddock(List<TaskPaddock> tasksPaddock) {
        for (TaskPaddock taskPad : tasksPaddock) {
            if (this.timedTaskPerPaddock.containsKey(taskPad)) {
                this.timedTaskPerPaddock.remove(taskPad);
            }
        }
    }

    /**
     * Check if the keeper works in a given paddock
     * @param paddock the paddock to check
     * @return true if he works in it
     */
    @Override
        public boolean workInGivenPaddock(IPaddock paddock) {
        return this.timedPaddocks.containsKey(paddock);
    }

    /**
     * Check if the keeper is making cleaning in the given paddock
     * @param paddock the paddock to check
     * @return true if he does
     */
    @Override
        public boolean isMakingCleaningInThePaddock(IPaddock paddock) {
        return this.timedTaskPerPaddock.containsKey(new TaskPaddock(paddock, Task.CLEANING.getId()));
    }

      /**
     * Check if the keeper is making feeding in the given paddock
     * @param paddock the paddock to check
     * @return true if he does
     */
    @Override
        public boolean isMakingFeedingInThePaddock(IPaddock paddock) {
        return this.timedTaskPerPaddock.containsKey(new TaskPaddock(paddock, Task.FEEDING.getId()));
    }

      /**
     * Check if the keeper is making medical training in the given paddock
     * @param paddock the paddock to check
     * @return true if he does
     */
    @Override
        public boolean isMakingMedicalTrainingInThePaddock(IPaddock paddock) {
        return this.timedTaskPerPaddock.containsKey(new TaskPaddock(paddock, Task.MEDICALTRAINING.getId()));
    }

      /**
     * Check if the keeper is making enrichment in the given paddock
     * @param paddock the paddock to check
     * @return true if he does
     */
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
