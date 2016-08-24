package launch;

import commandLine.CommandManager;
import commandLine.commandManagerImpl.TutorialCommandLineManager;
import java.util.ArrayList;
import launch.play.Play;
import launch.play.Step;
import lombok.Getter;
import lombok.Setter;
import zoo.IZoo;
import zoo.Zoo;

/**
 *
 * @author doyenm
 */
public class ScenarioPlayImpl implements Play {

    @Getter
    @Setter
    public IZoo zoo;
    @Getter
    private CommandManager manager;

    public static final class FriendScenario {

        private FriendScenario() {
        }
    }
    private static final FriendScenario friendScenario = new FriendScenario();

    public ScenarioPlayImpl() {
        this.zoo = new Zoo();
        this.manager = new TutorialCommandLineManager(this, this.buildTutorial());
    }

    public ArrayList<Step> buildTutorial() {
        ArrayList<Step> steps = new ArrayList<>();
        steps.add(new Step(zoo, "First, you need to create your zoo", "Your zoo is now created",
                "Use the command 'zoo create <name> <width> <height>' ; "
                + "see 'man zoo' for more information") {
                    @Override
                    public boolean check() {
                        return zoo.getName(friendScenario) != null;
                    }
                });
        steps.add(new Step(zoo, "Second, you need to create a paddock", "You have created a paddock",
                "Use the command '[pad|paddock] create <name> <x> <y> <width> <height>' ; "
                + "see 'man paddock' for more information") {
                    @Override
                    public boolean check() {
                        return zoo.getPaddocks(friendScenario).size() == 1;
                    }
                });
        steps.add(new Step(zoo, "Third, you can install an animal in this paddock ;"
                + " to see the list of available species, you can use the command '[specie|spec] ls'",
                "You now have an animal in the paddock",
                "Use the command 'animal create <name> <paddock> <specie> <sex>' ; "
                + "see 'man paddock' for more information") {
                    @Override
                    public boolean check() {
                        return zoo.getAnimals(friendScenario).size() == 1;
                    }
                });
        return steps;
    }
}
