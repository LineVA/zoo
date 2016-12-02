package zoo.animal.reproduction;

import exception.IncorrectDataException;
import exception.IncorrectLoadException;
import exception.name.EmptyNameException;
import exception.name.NameException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import zoo.statistics.Uniform;
import zoo.animal.Animal;
import zoo.animal.AnimalImpl;
import zoo.animal.specie.Specie;
import zoo.paddock.IPaddock;

/**
 * First implementation of a reproduction : the selected male is the first one
 * we found in the paddock which is mature and from the same specie as the
 * mother
 *
 * @author doyenm
 */
public class ReproductionImpl implements Reproduction {

    protected Uniform uniform;

    public ReproductionImpl() {
        this.uniform = new Uniform();
    }

    public ReproductionImpl(Uniform uni) {
        this.uniform = uni;
    }

    @Override
    public List<Animal> reproducte(Animal animal, int monthsPerEvaluation)
            throws IncorrectDataException, NameException, EmptyNameException, IncorrectLoadException {
        if (canFemaleReproducte(animal, monthsPerEvaluation)) {
            Animal father = whichMale(animal.findRoommatesOfTheSameSpecie());
            if (father != null) {
                return generateFamily(animal, father);
            }
        }
        return null;
    }

    /**
     * Create a family with a father and an statistically determined babies
     *
     * @param mother the mother of the family
     * @param father the father
     * @return a List ; the first element is the fater, the following are
     * the babies
     */
    public List<Animal> generateFamily(Animal mother, Animal father)
            throws IncorrectDataException, EmptyNameException, NameException, IncorrectLoadException {
        List<Animal> family = new ArrayList<>();
        family.add(mother);
        family.add(father);
        int litterSize = uniform.intAverage(mother.getActualLitterSize());
        System.out.println(litterSize);
        for (int i = 0; i < litterSize; i++) {
            family.add(generateAnimal(mother.getSpecie(), "", mother.getPaddock()));
        }
        return family;
    }

    /**
     * Generate randomly a baby with the specified parameters
     *
     * @param spec the specie of the baby
     * @param name its name
     * @param pad its paddock
     * @return the baby
     */
    public Animal generateAnimal(Specie spec, String name, IPaddock pad)
            throws IncorrectDataException, EmptyNameException, NameException, IncorrectLoadException {
        Sex sex;
        if (uniform.nextBoolean()) {
            sex = Sex.FEMALE;
        } else {
            sex = Sex.MALE;
        }
        return new AnimalImpl(spec, name, pad, sex, pad.getOption());
    }

    /**
     * Check if the animal is a female and if it is sexually mature
     *
     * @param animal the animal we check
     * @param monthsPerEvaluation the number of months spent at every evaluation
     * @return true if it can reproducte
     */
    public boolean canFemaleReproducte(Animal animal, int monthsPerEvaluation) {
        return animal.canBePregnant() && isInGestation(animal, monthsPerEvaluation) && ! animal.isAlreadyPregnant();
    }

    /**
     * Check if the studying female can statistically have a gestation
     *
     * @param animal the animal we test
     * @param monthsPerEvaluation the number of months spent at every evaluation
     * @return true if it can, false else
     */
    public boolean isInGestation(Animal animal, int monthsPerEvaluation) {
        return uniform.isLowerOrEquals(animal.getActualGestationFrequency() * monthsPerEvaluation / 12);
    }

    /**
     * Check if the studying animal is a mature male
     *
     * @param animal the animal we test
     * @return true if it is, false else
     */
    public boolean canMaleReproducte(Animal animal) {
        return animal.canFecundateAFemale();
    }

    /**
     * Check if a male is available for the reproduction of the specie in the
     * same paddock as the female Now : the first mature male of the same specie
     * is selected
     *
     * @param animals the animals in the paddock
     * @return the male if one has been found, null else.
     */
    public Animal whichMale(List<Animal> animals) {
        Iterator it = animals.iterator();
        Animal potentialMale;
        while (it.hasNext()) {
            potentialMale = (Animal) it.next();
            if (canMaleReproducte(potentialMale)) {
                return potentialMale;
            }
        }
        return null;
    }

}
