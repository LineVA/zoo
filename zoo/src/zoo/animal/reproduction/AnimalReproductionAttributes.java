package zoo.animal.reproduction;

import exception.IncorrectDataException;
import exception.IncorrectLoadException;
import java.util.ResourceBundle;
import launch.options.Option;
import lombok.Getter;
import utils.Utils;
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
    @Getter
    private int currentlyGestationDuration;

    public AnimalReproductionAttributes(int female, int male, double frequency, int litter, int duration,
            ContraceptionMethods method, Sex sex, int currentlyGestationDuration) {
        super(female, male, frequency, litter, duration);
        this.contraceptionMethod = method;
        this.sex = sex;
        this.currentlyGestationDuration = currentlyGestationDuration;
    }

    public int getMaturityAge() {
        if (this.sex.isFemale()) {
            return super.getFemaleMaturityAge();
        } else if (this.sex.isMale()) {
            return super.getMaleMaturityAge();
        } else {
            return -1;
        }
    }

    public boolean isFemale() {
        return this.sex.isFemale();
    }

    public boolean isMale() {
        return this.sex.isMale();
    }

    public void setSex(FakeAnimal.Friend friend, Sex sex) {
        friend.hashCode();
        this.sex = sex;
    }

    public String femaleToStringByLanguage(Option option) throws IncorrectDataException {
        String info = "";
        ResourceBundle bundle = option.getAnimalBundle();
        info += bundle.getString("REPRODUCTION.AGE_FEMALE") + Utils.infoAge(super.getFemaleMaturityAge(), bundle) + ", ";
        info += bundle.getString("REPRODUCTION.GESTATION_FREQUENCY") + Utils.truncate(super.getGestationFrequency()) + ", ";
        info += bundle.getString("REPRODUCTION.GESTATION_DURATION") + Utils.infoAge(super.getGestationDuration(), bundle) + ", ";
        info += bundle.getString("REPRODUCTION.LITTER_SIZE") + super.getLitterSize();
        return info;
    }
    
    public boolean updateGestationDuration(int months) {
        this.currentlyGestationDuration += months;
        if (this.currentlyGestationDuration >= super.getGestationDuration()) {
            this.currentlyGestationDuration = 0;
            return true;
        }
        return false;
    }

    public boolean isAlreadyPregnant(){
        return this.currentlyGestationDuration != 0;
    }
    
    public void setContraceptionMethod(ContraceptionMethods method, int age) throws IncorrectLoadException{
        ContraceptionMethodsValidator validator = new ContraceptionMethodsValidator();
        if(validator.validate(method, this, age)){
            this.contraceptionMethod = method;
        } else {
            throw new IncorrectLoadException("");
        }
    }
}
