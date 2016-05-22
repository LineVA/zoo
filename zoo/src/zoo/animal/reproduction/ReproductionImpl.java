package zoo.animal.reproduction;

import exception.IncorrectDataException;
import java.util.ArrayList;
import java.util.Iterator;
import zoo.statistics.Uniform;
import zoo.animal.Animal;
import zoo.animal.specie.Specie;
import zoo.paddock.IPaddock;

/**
 * First implementation of a reproduction : 
 * the selected male is the first one we found in the paddock
 * which is mature and from the same specie as the mother
 * @author doyenm
 */
public class ReproductionImpl implements Reproduction {

    protected Uniform uniform;

    public ReproductionImpl(){
        this.uniform = new Uniform();
    }
    
    public ReproductionImpl(Uniform uni){
        this.uniform = uni;
    }
    
    @Override
    public ArrayList<Animal> reproducte(Animal animal) throws IncorrectDataException{
        if (canFemaleReproducte(animal)) {
            Animal father = whichMale(animal.getPaddock().animalsOfTheSameSpecie(animal.getSpecie()));
            if (father != null) {
                return generateFamily(animal, father);
            }
        }
        return null;
    }

    
    /**
     * Create a family with a father and an statistically determined babies
     * @param mother the mother of the family
     * @param father the father
     * @return an ArrayList ; the first element is the fater, the following
     * are the babies
     */
    public ArrayList<Animal> generateFamily(Animal mother, Animal father) throws IncorrectDataException {
        ArrayList<Animal> family = new ArrayList<>();
        family.add(mother);
        family.add(father);
        int litterSize = uniform.intAverage(mother.getActualReproduction().getLitterSize());
        for (int i = 0; i < litterSize ; i++) {
            family.add(generateAnimal(mother.getSpecie(), mother.getName()
                    + father.getName() + i, mother.getPaddock()));
        }
        return family;
    }

    /**
     * Generate randomly a baby with the specified parameters
     * @param spec the specie of the baby
     * @param name its name
     * @param pad its paddock
     * @return the baby
     */
    public Animal generateAnimal(Specie spec, String name, IPaddock pad) throws IncorrectDataException {
        Sex sex;
        if (uniform.nextBoolean()) {
            sex = Sex.FEMALE;
        } else {
            sex = Sex.MALE;
        }
        return new Animal(spec, name, pad, sex, 0);
    }

    /**
     * Check if the animal is a female and if it is sexually mature
     *
     * @param animal the animal we check
     * @return true if it can reproducte
     */
    public boolean canFemaleReproducte(Animal animal) {
        if (animal.getSex().isFemale()) {
            if (animal.isMature()) {
                return true && isGestation(animal);
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * Check if the studying female can statistically have a gestation
     * @param animal the animal we test
     * @return true if it can, false else
     */
    public boolean isGestation(Animal animal) {
        return uniform.isGreaterOrEquals(animal.getActualReproduction().getGestationFrequency());
    }

    /**
     * Check if the studying animal is a mature male
     * @param animal the animal we test
     * @return true if it is, false else
     */
    public boolean canMaleReproducte(Animal animal) {
        if (animal.getSex().isMale()) {
            if (animal.isMature()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if a male is available for the reproduction of the specie in the
     * same paddock as the female Now : the first mature male of the same specie
     * is selected
     * @param spec the expected specie of the male
     * @param animals the animals in the paddock
     * @return the male if one has been found, null else.
     */
    public Animal whichMale(ArrayList<Animal> animals) {
        Iterator it = animals.iterator();
        Animal potentialMale;
        while(it.hasNext()){
            potentialMale = (Animal)it.next();
                if (canMaleReproducte(potentialMale)) {
                    return potentialMale;
                }
        }
        return null;
    }

}
