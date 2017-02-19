package zoo.animal.reproduction;

import exception.IncorrectDataException;
import exception.IncorrectLoadException;
import exception.name.EmptyNameException;
import exception.name.NameException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import zoo.statistics.Uniform;
import zoo.animal.Animal;
import zoo.animal.AnimalImpl;

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
//                updateGestation(animal, monthsPerEvaluation);
                return generateFamily(animal, father);
            }
        } else if (animal.isAlreadyPregnant()) {
            updateGestation(animal, monthsPerEvaluation);
        }
        return null;
    }

    private List<Animal> updateGestation(Animal animal, int monthsPerEvaluation)
            throws IncorrectDataException, NameException, EmptyNameException, IncorrectLoadException {
        if (animal.updateGestationDuration(monthsPerEvaluation)) {
            return generateFamily(animal, null);
        }
        return null;
    }

    /**
     * Create a family with a father and an statistically determined babies
     *
     * @param mother the mother of the family
     * @param father the father
     * @return a List ; the first element is the fater, the following are the
     * babies
     */
    public List<Animal> generateFamily(Animal mother, Animal father)
            throws IncorrectDataException, EmptyNameException, NameException, IncorrectLoadException {
        List<Animal> family = new ArrayList<>();
        family.add(mother);
        family.add(father);
        int litterSize = uniform.intAverage(mother.getActualLitterSize());
        System.out.println(litterSize);
        int rand = 0;
        Random random = new Random();
        for (int i = 0; i < litterSize; i++) {
            rand = random.nextInt();
            family.add(generateAnimal(String.valueOf(rand), mother, father));
        }
        return family;
    }

    /**
     * Generate randomly a baby with the specified parameters
     *
     * @param mother
     * @param name its name
     * @param father
     * @return the baby
     * @throws exception.IncorrectDataException
     * @throws exception.name.EmptyNameException
     * @throws exception.IncorrectLoadException
     */
    public Animal generateAnimal(String name, Animal mother, Animal father)
            throws IncorrectDataException, EmptyNameException, NameException, IncorrectLoadException {
        Sex sex;
        if (uniform.nextBoolean()) {
            sex = Sex.FEMALE;
        } else {
            sex = Sex.MALE;
        }
        return new AnimalImpl(mother.getSpecie(), name, mother.getPaddock(), sex, 0,
                mother.getName(), father.getName(), mother.getPaddock().getOption());
    }

    /**
     * Check if the animal is a female and if it is sexually mature
     *
     * @param animal the animal we check
     * @param monthsPerEvaluation the number of months spent at every evaluation
     * @return true if it can reproducte
     */
    public boolean canFemaleReproducte(Animal animal, int monthsPerEvaluation) {
        return animal.canBePregnant() ;
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
