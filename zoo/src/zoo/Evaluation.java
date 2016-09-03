package zoo.evaluation;

import exception.IncorrectDataException;
import exception.name.EmptyNameException;
import exception.name.NameException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import zoo.animal.specie.Family;
import zoo.animal.specie.Specie;
import zoo.paddock.IPaddock;
import zoo.paddock.Paddock;

/**
 *
 * @author doyenm
 */
public class Evaluation {

    public Evaluation() {

    }

    public int evaluate(Map<String, IPaddock> paddocks) {
        ArrayList<Specie> presentedSpecies = new ArrayList<>();
        ArrayList<Specie> localPresentedSpecies = new ArrayList<>();
        int kidsNb = 0;
        for (Map.Entry<String, IPaddock> padEntry : paddocks.entrySet()) {
            kidsNb += padEntry.getValue().countNonMatureAnimals();
            localPresentedSpecies = padEntry.getValue().listSpecies(presentedSpecies);
        }
        return kidsNb * 1 + presentedSpecies.size() * 1 + familyEvaluation(presentedSpecies);
    }

    private int familyEvaluation(ArrayList<Specie> species) {
        int evaluation = 0;
        int[] families = familiesArrayFilling(species);
        int familiesNb = 0;
        int byThree = 0;
        for (int i = 1; i < families.length; i++) {
            if (families[i] > 0) {
                familiesNb += 1;
            }
            if (families[i] >= 3) {
                byThree += 1;
            }
        }
        return familiesNb / 3 + byThree;
    }

    private int[] familiesArrayFilling(ArrayList<Specie> species) {
        int[] families = new int[Family.values().length];
        for (Specie spec : species) {
            int fam = spec.getFamily();
            families[fam] += 1;
        }
        return families;
    }

    public ArrayList<String> ageing(Map<String, IPaddock> paddocks, int monthsPerEvaluation)
            throws IncorrectDataException, EmptyNameException, NameException {
        ArrayList<String> info = new ArrayList<>();
        for (HashMap.Entry<String, IPaddock> padEntry : paddocks.entrySet()) {
            padEntry.getValue().ageing(monthsPerEvaluation);
            info.addAll(padEntry.getValue().birth());
            info.addAll(padEntry.getValue().death());
        }
        return info;
    }
}
