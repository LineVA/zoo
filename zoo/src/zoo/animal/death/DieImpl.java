package zoo.animal.death;

import zoo.statistics.Uniform;
import zoo.animal.Animal;

/**
 * An animal die only if it is too old (its age is greater or equals to its lifespan).
 * If its well-being is greater than 80% of the maximum, it is a fifty-fifty chance to survive to this turn.
 * @author doyenm
 */
public class DieImpl implements IDie{

    Uniform uniform;
    
    public DieImpl(){
        this.uniform = new Uniform();
    }
    
    @Override
    public boolean isDied(Animal animal) {
        return isInStarvation(animal) || mustDie(animal) || animal.isTooDrowning();
    }
    
    private boolean mustDie(Animal animal){
        if(animal.isTooOld()){
            if(animal.isEnoughHappy()){
                return this.uniform.isGreaterOrEquals(0.5);
            }
        }
        return false;
    }
    
    private boolean isInStarvation(Animal animal){
        return animal.isTooStarving();
    }
}
