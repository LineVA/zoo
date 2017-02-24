package zoo.animal.reproduction;

/**
 *
 * @author doyenm
 */
public class ContraceptionMethodsValidator {
    
    public boolean validate(ContraceptionMethods newMethod, 
            AnimalReproductionAttributes attributes, int age){
        boolean bool;
        bool = this.isOldEnoughForContraception(age, attributes.getMaturityAge());
        bool  &= this.isSexConsistentWithContraception(newMethod, attributes.getSex());
        bool &= this.isTheEvolutionConsistent(newMethod, attributes.getContraceptionMethod());
        return bool;
    }
    
    private boolean isOldEnoughForContraception(int age, int maturityAge){
        return age >= maturityAge;
    }
    
    private boolean isSexConsistentWithContraception(ContraceptionMethods method, Sex sex){
        return ContraceptionMethods.NONE.getAuthorizedSexes(method).contains(sex);
    }
    
    private boolean isTheEvolutionConsistent (ContraceptionMethods newMethod, ContraceptionMethods oldMethod){
        if(oldMethod.isDefinitiv()){
            if(oldMethod != newMethod){
                return false;
            }
        } 
        return true;
    }

}
