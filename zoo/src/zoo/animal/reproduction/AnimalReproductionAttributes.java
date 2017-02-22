package zoo.animal.reproduction;

import lombok.Getter;

/**
 *
 * @author doyenm
 */
public class AnimalReproductionAttributes extends ReproductionAttributes{
    
     @Getter
    private ContraceptionMethods contraceptionMethod;
    
    public AnimalReproductionAttributes(int female, int male, double frequency, int litter, int duration,
            ContraceptionMethods method){
        super(female, male, frequency, litter, duration);
        this.contraceptionMethod = method;
    }

}
