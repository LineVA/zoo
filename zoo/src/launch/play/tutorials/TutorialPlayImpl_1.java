package launch.play.tutorials;

import commandLine.commandManagerImpl.TutorialCommandLineManager;
import java.util.ArrayList;
import java.util.ResourceBundle;
import launch.options.Option;
import launch.play.Play;
import launch.play.Step;
import zoo.animal.Animal;

/**
 *
 * @author doyenm
 */
public class TutorialPlayImpl_1 extends Play {

    public static final class FriendScenario {

        private FriendScenario() {
        }
    }
    private static final FriendScenario friendScenario = new FriendScenario();

    public TutorialPlayImpl_1(ResourceBundle bundle, Option option) {
        super(bundle, option);
        super.setManager(new TutorialCommandLineManager(this, this.buildTutorial()));
    }

    public ArrayList<Step> buildTutorial() {
        ArrayList<Step> steps = new ArrayList<>();
        // Step 1 : creation of the zoo
        steps.add(new Step(super.getZoo(), "First, you need to create your zoo", "Help","") {
                    @Override
                    public boolean check() {
                        return super.getZoo().getName(friendScenario) != null;
                    }
                });
        // Step 2 : creation of a paddock
        steps.add(new Step(super.getZoo(), "Second, you need to create a paddock", "Help", "") {
                    @Override
                    public boolean check() {
                        return super.getZoo().getPaddocks(friendScenario).size() == 1;
                    }
                });
        // Step 3 : first animal
//        steps.add(new Step(super.getZoo(), "Third, you can install an animal in this paddock ;"
//                + " to see the list of available species, you can use the command '[specie|spec] ls'",
//                "You now have an animal in the paddock",
//                "Use the command 'animal create <name> <paddock> <specie> <sex>' ; "
//                + "see 'man animal' for more information") {
//                    @Override
//                    public boolean check() {
//                        return super.getZoo().getAnimals(friendScenario).size() == 1;
//                    }
//                });
////        // Step 4 : animal info
//        steps.add(new Step(super.getZoo(), "Fourthly, you can see the personal information of this animal "
//                + "by using the command 'animal <name>'."
//                + "If you do not remember the name of the animal, "
//                + "you can see the list of your animals by using the command 'animal ls'",
//                "On the result of the command, you have several information about the animal : "
//                + "its name, age, sex, specie, paddock, its actual diet, daily food quantity, its reproduction and lifespan data...",
//                "") {
//                    @Override
//                    public boolean check() {
//                        return true;
//                    }
//                });
//        // Step 5 : a second animal of the opposite sex
//        steps.add(new Step(super.getZoo(), "Fithly, in order to have new borns, you need to install an second "
//                + "animal in the same paddock, of the same specie but of the opposite sex",
//                "You now have a couple in your paddock",
//                "Use the command 'animal create <name> <paddock> <specie> <sex>' ; "
//                + "see 'man animal' for more information") {
//                    @Override
//                    public boolean check() {
//                        ArrayList<Animal> animals = super.getZoo().getAnimals(friendScenario);
//                        for (Animal animal : animals) {
//                            for (Animal animal2 : animals) {
//                                if ((animal.getSex() != animal2.getSex())
//                                && (animal.getPaddock().equals(animal2.getPaddock()))
//                                && animal.getSpecie().equals(animal2.getSpecie())) {
//                                    return true;
//                                }
//                            }
//                        }
//                        return false;
//                    }
//                });
//        // Step 6 : evaluate, until having a new born
        return steps;
    }
}
