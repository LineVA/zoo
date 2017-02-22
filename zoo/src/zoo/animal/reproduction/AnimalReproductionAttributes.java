package zoo.animal.reproduction;

import lombok.Getter;
import zoo.animal.FakeAnimal;

/**
 *
 * @author doyenm
 */
public class AnimalReproductionAttributes extends ReproductionAttributes {

    @Getter
    private ContraceptionMethods contraceptionMethod;
    @Getter
    private Sex sex;

    public AnimalReproductionAttributes(int female, int male, double frequency, int litter, int duration,
            ContraceptionMethods method, Sex sex) {
        super(female, male, frequency, litter, duration);
        this.contraceptionMethod = method;
        this.sex = sex;
    }
    
    public int getMaturityAge(){
        if(this.sex.isFemale()){
            return super.getFemaleMaturityAge();
        } else if (this.sex.isMale()){
            return super.getMaleMaturityAge();
        } else {
            return -1;
        }
    }
    
    public boolean isFemale(){
        return this.sex.isFemale();
    }
    
    public boolean isMale(){
        return this.sex.isMale();
    }
    
    public void setSex(FakeAnimal.Friend friend, Sex sex){
        friend.hashCode();
        this.sex = sex;
    }

}
