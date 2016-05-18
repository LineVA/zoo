package zoo.animal.death;

import zoo.statistics.Uniform;
import zoo.animal.Animal;

/**
 *
 * @author doyenm
 */
public class DieImpl implements IDie{

    Uniform uniform;
    
    public DieImpl(){
        this.uniform = new Uniform();
    }
    
    @Override
    public boolean isDied(Animal animal) {
        return mustDie(animal);
    }
    
    public boolean mustDie(Animal animal){
        return animal.isTooOld();
    }
}
