package zoo.animal.reproduction;

import java.util.HashMap;
import java.util.Iterator;
import zoo.animal.Animal;

/**
 *
 * @author doyenm
 */
public class ReproductionImpl implements Reproduction {

    @Override
    public Animal reproducte(Animal animal) {
        if (canFemaleReproducte(animal)) {
            return whichMale(animal.getPaddock().getAnimals());
        }
        return null;
    }

    /**
     * Check if the animal is a female and if it is sexually mature
     *
     * @param animal the animal we check
     * @return true if it can reproducte
     */
    public boolean canFemaleReproducte(Animal animal) {
        if (animal.getSex().isFemale()) {
            if (animal.getAge() > animal.getOptimals()
                    .getReproductionAttributes().getFemaleMaturityAge()) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public boolean canMaleReproducte(Animal animal) {
        if (animal.getSex().isMale()) {
            if (animal.getAge() > animal.getOptimals()
                    .getReproductionAttributes().getMaleMaturityAge()) {
                return true;
            }
        }
        return false;
    }

    public Animal whichMale(HashMap<String, Animal> animals) {
        for (HashMap.Entry<String, Animal> entry : animals.entrySet()) {
            if (canMaleReproducte(entry.getValue())) {
                return entry.getValue();
            }
        }
        return null;
    }

}
